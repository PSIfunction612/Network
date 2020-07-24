import java.util.Arrays;

public final class Matr{

    public static double[][] sum(double[][] A, double[][] B){
        double[][] C = new double[A.length][A[0].length];
        for (int i = 0; i< A.length; i++){
            for (int j = 0; j< A[0].length; j++){
                C[i][j]=A[i][j]+B[i][j];
            }
        }
        return C;
    }

    public static double[][] min(double[][] A, double[][] B){
        double[][] C = new double[A.length][A[0].length];
        for (int i = 0; i< A.length; i++){
            for (int j = 0; j< A[0].length; j++){
                C[i][j]=A[i][j]-B[i][j];
            }
        }
        return C;
    }



    public static double[][] dot(double[][] A, double k){
        double[][] C = new double[A.length][A[0].length];
        for (int i = 0; i< A.length; i++){
            for (int j = 0; j< A[0].length; j++){
                C[i][j]=A[i][j]*k;
            }
        }
        return C;
    }

    public static double[][] prod(double[][] A, double[][] B){
        double[][] C = new double[A.length][B[0].length];

        for (int k=0; k<A.length; k++) {
            for (int i = 0; i < B[0].length; i++) {
                double sum = 0;
                for (int j = 0; j < A[0].length; j++) {
                    sum += B[j][i] * A[k][j];
                }
                C[k][i] = sum;
            }
        }
        return C;
    }

    public static double[][] transponent(double[][] A) {

        double[][] C = new double[A[0].length][A.length];

        for (int j = 0; j < A.length; j++) {
            for (int i = 0; i < A[0].length; i++) {
                C[i][j] = A[j][i];
            }
        }
        return C;
    }

    public static double det(double[][] A){
        double det=0;
        if (A.length==1) {
            det = A[0][0];
        }
        else if (A.length == 2) {
                det = A[0][0] * A[1][1] - A[1][0] * A[0][1];
            } else {
                for (int i = 0; i < A[0].length; i++) {
                    det += A[0][i] * Math.pow(-1.0, i) * det(Matr.minor(A, 0, i));
                }
            }
        return det;
    }

    public static double[][] inverse(double[][] A){
        if (det(A)!=0){

            double[][] C = new double[A.length][A[0].length];

            for (int i=0; i< C.length; i++){
                for (int j=0; j<C[0].length; j++){
                    C[j][i]=(1/det(A))*Math.pow(-1, i+j)*det(minor(A, i,j));
                }
            }
            return C;
        } else {
            double[][] C = {{0},{0}};
            return C;
        }
    }


    private static double[][] minor(double[][] A, int m, int n){

            double[][] C = new double[A.length-1][A[0].length-1];

            int k=0;
            for (int i=0; i < A.length; i++) {
                if (i != m) {
                    int l=0;
                    for (int j = 0; j < A[0].length; j++) {
                        if (j != n){
                            C[k][l]=A[i][j];
                            l++;
                        }
                    }
                    k++;
                }
            }
            return C;
        }


    public static void show(double[][] A){
        for (int i =0; i< A.length; i++) {
            System.out.println(Arrays.toString(A[i]));
        }
        System.out.println("-------------------------------------------");
    }

    // почленное умножение

    public static double[][] mult(double[][] A, double[][] B){
        double[][] C = new double[A.length][A[0].length];
        for (int i = 0; i< A.length; i++){
            for (int j = 0; j< A[0].length; j++){
                C[i][j]=A[i][j]*B[i][j];
            }
        }
        return C;
    }


    // специальный метод для нейронной сети, позволяет поэлементно вычесть матрицу из единицы
    public static double[][] spec(double[][] A){
        double[][] C = new double[A.length][A[0].length];
        for (int i = 0; i< A.length; i++){
            for (int j = 0; j< A[0].length; j++){
                C[i][j]=1-A[i][j];
            }
        }
        return C;
    }


}
