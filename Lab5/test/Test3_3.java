import org.junit.Test;

public class Test3_3 {

        @Test
        public void test() {
            Variable x = new Variable("x");
            Node exp = new Prod()
                    .mul(new Power(x,2))
                    .mul(-2)
                    .mul(7);
            System.out.print("exp=");
            System.out.println(exp.toString());
    }
}
