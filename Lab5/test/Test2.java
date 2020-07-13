import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;
public class Test2 {
    @Test
    public void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            System.out.printf(Locale.US, "f(%f)=%f\n", v, exp.evaluate());
            double v1=exp.evaluate();
            double v2=v*v*v-2*v*v-v+2;
            assertEquals(v1,v2,1e-7);
        }
    }
}