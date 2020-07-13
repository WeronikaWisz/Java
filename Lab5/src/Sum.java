import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }


    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result =0;
        for(Node arg:args){
            result+=arg.evaluate();
        }
        return sign*result;
    }

    int getArgumentsCount(){return args.size();}

    public String toString() {
        StringBuilder b = new StringBuilder();
        if (sign < 0) b.append("-(");

        int i = 0;

        for (Node arg : args) {

            if (!arg.isZero()) {

                if (i>0 && i<args.size()) {
                    b.append("+");
                }
                int argSign = arg.getSign();
                boolean useBracket = false;
                if (argSign < 0) useBracket = true;
                String argString = arg.toString();
                if (useBracket) b.append("(");
                b.append(argString);
                if (useBracket) b.append(")");

                i++;
            }

        }
            if (sign < 0) b.append(")");
            return b.toString();
        }

    Node diff(Variable var) {
        Sum r = new Sum();
        for(Node n:args){
            r.add(n.diff(var));
        }
        return r;
    }

    boolean isZero(){
        boolean val = true;
        for(Node arg:args) {
            if (!arg.isZero()) {
                val=false;
            }
        }
        return val;
    }

}
