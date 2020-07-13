import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }
    Prod(double c){
        args.add(new Constant(c));
    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
    Prod(double c, Node n){
        args.add(new Constant(c));
        args.add(n);
    }


    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        args.add(new Constant(c));
        return this;
    }


    @Override
    double evaluate() {
        double result =1;
        for(Node arg:args){
            result*=arg.evaluate();
        }
        return sign*result;
    }
    int getArgumentsCount(){return args.size();}


    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-");

        this.simplify();

        int i=0;
        boolean val=false;

        for(Node a:args){
            if(a.isZero()){
                val=true;
            }
        }
        if(!val) {
            for (Node arg : args) {

                int argSign = arg.getSign();
                boolean useBracket = false;
                if (argSign < 0) useBracket = true;
                String argString = arg.toString();
                if (useBracket) b.append("(");
                b.append(argString);
                if (useBracket) b.append(")");
                if (i < args.size() - 1) {
                    b.append("*");
                }
                i++;

            }
        }

        return b.toString();
    }

    Node diff(Variable var) {
        Sum r = new Sum();
        for(int i=0;i<args.size();i++){
            Prod m= new Prod();
            for(int j=0;j<args.size();j++){
                Node f = args.get(j);
                if(j==i)m.mul(f.diff(var));
                else m.mul(f);
            }
            r.add(m);
        }
        return r;
    }

    boolean isZero(){
        boolean val = false;
        for(Node arg:args) {
            if (arg.isZero()) {
                val=true;
            }
        }
        return val;
    }

    public Prod simplify(){
        Prod simply = new Prod();
        double count = 1;
        for(int i=0; i<args.size(); i++) {
            if (args.get(i) instanceof Constant){
                count *= args.get(i).evaluate();
            }
        }
        Constant c = new Constant(count);
        simply.args.add(c);
        for(int j=0; j<args.size(); j++) {
            if (!(args.get(j) instanceof Constant)){
                simply.args.add(args.get(j));
            }
        }
        this.args = simply.args;
        return this;
    }
}
