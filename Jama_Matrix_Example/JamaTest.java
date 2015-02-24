import Jama.*;

public class JamaTest {
  public static void main(String[] args) {
    System.out.println("Hello, World!\nHere's a Jama Matrix demo:\n");

    double[][] array = {{1, 2, 3},{4, 5, 6},{7, 8, 10}};
    Matrix A = new Matrix(array);
    Matrix b = Matrix.random(3,1);
    Matrix x = A.solve(b);
    Matrix Residual = A.times(x).minus(b);
    double rnorm = Residual.normInf();

    System.out.println("A =");
    A.print(1,5);

    System.out.println("b =");
    b.print(1,5);

    System.out.println("Solution to Ax = b:");
    x.print(1,5);

    System.out.println("Residual norm: " + rnorm);
  }
}
