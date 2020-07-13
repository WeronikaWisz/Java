import java.util.Random;

public class Matrix {
    double[] data;
    int rows;
    int cols;

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    Matrix(double[][] d) {
        this.rows = d.length;
        this.cols = 1;
        for (int k = 0; k < d.length; k++) {
            if (d[k].length > this.cols) {
                this.cols = d[k].length;
            }
        }
        data = new double[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < d[i].length; j++) {
                data[i*cols + j] = d[i][j];
            }

        }
    }

    double[][] asArray() {
        double[][] d = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                d[i][j] = data[i*cols + j];
            }
        }
        return d;
    }

    double get(int r,int c){
        return data[r*cols+c];
    }

    void set (int r,int c, double value){
        data[r*cols+c] = value;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[\n");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j=0;j<cols;j++){
                buf.append(data[i*cols + j]+" ");
            }
            buf.append("]\n");
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols) {
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));
        }
        this.rows = newRows;
        this.cols = newCols;

    }

    int[] shape(){
        int[] size = {rows,cols};
        return size;
    }


    Matrix add(Matrix m){
        Matrix sum = new Matrix(rows,cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum.data[i * cols + j] = this.data[i * cols + j] + m.data[i * cols + j];
            }
        }
        return sum;
    }

    Matrix sub(Matrix m){
        Matrix sub = new Matrix(rows,cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sub.data[i * cols + j] = this.data[i * cols + j] - m.data[i * cols + j];
            }
        }
        return sub;
    }

    Matrix mul(Matrix m){
        Matrix mul = new Matrix(rows,cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mul.data[i * cols + j] = this.data[i * cols + j] * m.data[i * cols + j];
            }
        }
        return mul;
    }

    Matrix div(Matrix m){
        Matrix div = new Matrix(rows,cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(m.data[i * cols + j] == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                div.data[i * cols + j] = this.data[i * cols + j] / m.data[i * cols + j];
            }
        }
        return div;
    }

    Matrix add(double w){
        Matrix sum = new Matrix(rows,cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum.data[i * cols + j] = this.data[i * cols + j] + w;
            }
        }
        return sum;
    } // dodaje wartość w do każdego elementu

    Matrix sub(double w){
        Matrix sub = new Matrix(rows,cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sub.data[i * cols + j] = this.data[i * cols + j] - w;
            }
        }
        return sub;
    } // odejmuje wartośc w od kazdego elementu

    Matrix mul(double w){
        Matrix mul = new Matrix(rows,cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mul.data[i * cols + j] = this.data[i * cols + j] * w;
            }
        }
        return mul;
    } // mnoży każdy element przez skalar w

    Matrix div(double w){
        if(w==0){
            throw new ArithmeticException("Cannot divide by zero");
        }
        Matrix div = new Matrix(rows,cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                div.data[i * cols + j] = this.data[i * cols + j] / w;
            }
        }
        return div;
    } // dzieli każdy element przez skalar w

    Matrix dot(Matrix m){
        Matrix dot = new Matrix(this.rows, m.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int k = 0; k < m.cols; k++) {
                for (int j = 0; j < m.rows; j++) {
                    dot.data[i * dot.cols + k] += this.data[i * this.cols + j] * m.data[j * m.cols + k];
                }
            }
        }
        return dot;
    }

    double frobenius(){
        double fro = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                fro += Math.pow(data[i * cols + j],2);
            }
        }
        fro = Math.sqrt(fro);
        return fro;
    }

    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.set(i, j, r.nextDouble());
            }
        }
        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i==j){
                    m.set(i,j,1);
                }
            }
        }
        return m;
    }

    public Matrix transpose(){
        Matrix transposed = new Matrix(cols,rows);

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                transposed.set(x, y, get(y, x));
            }
        }

        return transposed;
    }

    public Matrix minor(int row, int col) {
        Matrix minor = new Matrix(rows - 1, cols - 1);

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (y != row && x != col) {
                    minor.set(y < row ? y : y - 1, x < col ? x : x - 1, get(y, x));
                }
            }
        }

        return minor;
    }

    public double determinant() {
        if (rows != cols) {
            throw new IllegalStateException("Non-square matrix");
        }

        if (rows == 1) {
            return get(0, 0);
        }

        if (rows == 2) {
            return get(0, 0) * get(1, 1) - get(0, 1) * get(1, 0);
        }

        double determinant = 0;

        for (int i = 0; i < cols; i++) {
            determinant += Math.pow(-1, i) * get(0, i) * minor(0, i).determinant();
        }

        return determinant;
    }

    public Matrix inverse() {
        double determinant = determinant();

        if (determinant == 0) {
            throw new IllegalStateException("Inverse of matrix with 0 determinant is not possible");
        }

        Matrix inverse = new Matrix(rows, cols);

        for (int y = 0; y < inverse.rows; y++) {
            for (int x = 0; x < inverse.cols; x++) {
                inverse.set(y, x, Math.pow(-1, y + x) * minor(y, x).determinant() + 0.0);
            }
        }

        double det = 1.0 / determinant;
        inverse.mul(det);
        inverse.transpose();
        return inverse;
    }

    //kartkowkaA

    Matrix getColumn(int i){

        if(i >= cols) {
            throw new IllegalStateException(String.format("There is only %d columns", cols));
        }

        Matrix m = new Matrix(1,cols);
            for (int j = 0; j < cols; j++) {
                m.set(0, j, data[j*cols+i]);
            }
        return m;
    }

    public static void main(String[] args) {
        Matrix ma = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix col = ma.getColumn(0);
        System.out.println(col);
        Matrix n = new Matrix(new double[][]{{1,2},{5,6},{7,8}});
        Matrix m = new Matrix(new double[][]{{3,4},{5,6}});
        System.out.println(n);
        System.out.println(m);
        Matrix dot = n.dot(m);
        System.out.println(dot);
        double fro = m.frobenius();
        System.out.println(fro);
        Matrix r = Matrix.random(2,3);
        System.out.println(r);
        Matrix e = Matrix.eye(4);
        System.out.println(e);
        System.out.print(n.transpose());
    }
}
