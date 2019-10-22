import static org.junit.Assert.*;

public class MatrixTest {

    @org.junit.Test
    public void testMatrix(){
        Matrix m = new Matrix(3,2);
        assertEquals(3,m.rows);
        assertEquals(2,m.cols);
    }

   @org.junit.Test
    public void testMatrix2(){
        double[][] d = {{1,2,3},{3},{1,2}};
        Matrix m = new Matrix(d);
        double[][] t = m.asArray();
        assertEquals(3,t.length);
        for(int k=0; k<t.length; k++){
            assertEquals(3,t[k].length);
        }
        for (int i = 0; i < t.length; i++) {
            for (int j = t[i].length; j < d[i].length; j--) {
                assertEquals(0,t[i][j],0.001);
            }
       }
    }

    @org.junit.Test
    public void asArray() {
        double[][] d = {{1,2,3},{3,0,1}};
        Matrix m = new Matrix(d);
        double[][] t = m.asArray();
        for(int i=0; i<t.length-1;i++) {
            assertEquals(t[i].length, t[i+1].length);
        }
    }

    @org.junit.Test
    public void get() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{3,0,1}});
        double t = m.get(0,1);
        assertEquals(2,t,0.01);
    }

    @org.junit.Test
    public void set() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{3,0,1}});
        m.set(0,1,4);
        assertEquals(4,m.data[1],0.01);
    }

    @org.junit.Test
    public void testToString() {
        String s= "[[1.0,2.3,4.56], [12.3,  45, 21.8]]";
        s= s.replaceAll("(\\[|\\]|\\s)+","");
        String[] t = s.split("(,)+");
        for(String x:t){
            System.out.println(String.format("\'%s\'",x ));
        }

        double[]d=new double[t.length];
        for(int i=0;i<t.length;i++) {
            d[i] = Double.parseDouble(t[i]);
        }

        double arr[][]=new double[1][];
        arr[0]=d;

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.println(arr[i][j]);
            }
        }
    }

    @org.junit.Test(expected = RuntimeException.class)
    public void reshape() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{3,0,1}});
        m.reshape(4,2);
    }

    @org.junit.Test
    public void shape() {
    }

    @org.junit.Test
    public void add() {
    }

    @org.junit.Test
    public void sub() {
    }

    @org.junit.Test
    public void mul() {
    }

    @org.junit.Test
    public void div() {
    }

    @org.junit.Test
    public void testAdd() {
    }

    @org.junit.Test
    public void testSub() {
    }

    @org.junit.Test
    public void testMul() {
    }

    @org.junit.Test
    public void testDiv() {
    }

    @org.junit.Test
    public void dot() {
    }

    @org.junit.Test
    public void frobenius() {
    }
}
