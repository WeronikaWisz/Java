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
        Matrix m = new Matrix(new double[][]{{1,2,3},{3,0}});
        Matrix n = new Matrix(2,3);
        int[] sm = m.shape();
        int[] sn = n.shape();
        assertEquals(2, sm[0]);
        assertEquals(3, sm[1]);
        assertEquals(2, sn[0]);
        assertEquals(3, sn[1]);
    }

    @org.junit.Test
    public void add() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{3},{1,2}});
        Matrix n = m.mul(-1);
        Matrix a = m.add(n);
        double fro = a.frobenius();
        assertEquals(0,fro,0.001);
    }

    @org.junit.Test
    public void sub() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{3},{1,2}});
        Matrix s = m.sub(m);
        double fro = s.frobenius();
        assertEquals(0,fro,0.001);
    }

    @org.junit.Test
    public void mul() {
        Matrix m = new Matrix(new double[][]{{0,2,4},{0.5},{1,0}});
        Matrix n = new Matrix(new double[][]{{0,0.5,0.25},{2},{1,0}});
        Matrix s = m.mul(n);
        double fro = s.frobenius();
        assertEquals(2,fro, 0.001);

    }

    @org.junit.Test
    public void div() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{3,6,9},{1,2,5}});
        Matrix d = m.div(m);
        double fro = d.frobenius();
        double n = Math.sqrt(m.rows*m.cols);
        assertEquals(fro,n,0.0001);
    }

    @org.junit.Test
    public void testAdd() {
        Matrix m = new Matrix(new double[][]{{2,4},{0,1,0},{1}});
        Matrix w = new Matrix(new double[][]{{5,7,3},{3,4,3},{4,3,3}});
        Matrix n = m.add(3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(w.data[i * w.cols + j],n.data[i * n.cols + j],0.001);
            }
        }
    }

    @org.junit.Test
    public void testSub() {
        Matrix m = new Matrix(new double[][]{{2,4},{0,1,0},{1}});
        Matrix w = new Matrix(new double[][]{{-1,1,-3},{-3,-2,-3},{-2,-3,-3}});
        Matrix n = m.sub(3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(w.data[i * w.cols + j],n.data[i * n.cols + j],0.001);
            }
        }
    }

    @org.junit.Test
    public void testMul() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{3},{1,2}});
        Matrix n = m.mul(-1);
        Matrix a = m.add(n);
        double fro = a.frobenius();
        assertEquals(0,fro,0.001);
    }

    @org.junit.Test
    public void testDiv() {
        Matrix m = new Matrix(new double[][]{{1,2,8},{-4},{1,-2}});
        Matrix n = m.div(-2);
        Matrix w = new Matrix(new double[][]{{-0.5,-1,-4},{2},{-0.5,1}});
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(w.data[i * w.cols + j],n.data[i * n.cols + j],0.001);
            }
        }
    }

    @org.junit.Test
    public void dot() {
        Matrix m = new Matrix(new double[][]{{3,4,6,1},{0,3,5},{1,7,9,7}});
        Matrix n = new Matrix(new double[][]{{2,4,0},{5,3,7},{0,1,1},{2,6,3}});
        Matrix w = new Matrix(new double[][]{{28,36,37},{15,14,26},{51,76,79}});
        Matrix d = m.dot(n);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(w.data[i * w.cols + j],d.data[i * d.cols + j],0.001);
            }
        }
    }

    @org.junit.Test
    public void frobenius() {
        Matrix m = new Matrix(new double[][]{{0,3},{1,4},{3,1}});
        double f = m.frobenius();
        assertEquals(6,f,0.001);
    }
}
