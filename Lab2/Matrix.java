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
                buf.append(data[i*cols + j]);
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
        Matrix div = new Matrix(rows,cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                div.data[i * cols + j] = this.data[i * cols + j] / w;
            }
        }
        return div;
    } // dzieli każdy element przez skalar w

    Matrix dot(Matrix m){

    }

    public static void main(String[] args) {
        Matrix n = new Matrix(10,10);
        System.out.println(n);
    }

}
