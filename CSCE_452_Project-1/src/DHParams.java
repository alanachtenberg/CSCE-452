import Jama.*;

import java.lang.*;
import java.util.*;


public class DHParams {

  private HashMap<String, ArrayList<Double> > table;
  private int num_joints;

  /**
   * Creates a table representation of the DH parameters for a robot with n 
   * joints. Initally, all values are set to zero in the table and must be 
   * manually assigned.
   *
   * @param n the number of joints the robot will have
   */
  public DHParams(int n) {
    this.num_joints = n;

    table = new HashMap<String, ArrayList<Double> >();

    table.put("alpha", new ArrayList<Double>());
    table.put("a", new ArrayList<Double>());
    table.put("d", new ArrayList<Double>());
    table.put("theta", new ArrayList<Double>());

    // n+1 table entries should be generated because of the end effector
    for (int i = 0; i < n+1; ++i) {
      table.get("alpha").add(0.0);
      table.get("a").add(0.0);
      table.get("d").add(0.0);
      table.get("theta").add(0.0);
    }
  }

  /**
   * Returns the specified DH parameter.
   *
   * @param param the name of the desired parameter
   * @param index the index of the parameter
   * @return      the corresponding value in the DH parameter table
   * @throws IndexOutOfBoundsException if the index is invalid
   */
  public double get(String param, int index) {
    if (param.equals("d") || param.equals("theta")) {
      if (index < 1 || index > num_joints+1) {
        throw new IndexOutOfBoundsException();
      }

      return table.get(param).get(index - 1);
    } else {
      if (index < 0 || index > num_joints) {
        throw new IndexOutOfBoundsException();
      }

     return table.get(param).get(index);
    }
  }

  /**
   * Changes a value in the DH parameter table.
   *
   * @param param the name of the desired parameter
   * @param index the index of the parameter
   * @param val   the new value for the parameter
   * @return      true
   * @throws IndexOutOfBoundsException if the index is invalid
   */
  public boolean set(String param, int index, double val) {
    if (param.equals("d") || param.equals("theta")) {
      if (index < 1 || index > num_joints+1) {
        throw new IndexOutOfBoundsException();
      }

      table.get(param).set(index - 1, val);
    } else {
      if (index < 0 || index > num_joints) {
        throw new IndexOutOfBoundsException();
      }
      table.get(param).set(index ,val);
    }

    return true;
  }

  /**
   * Generates a T matrix.
   *
   * @param from the reference frame, or the leading subscript of the matrix
   * @param to   the target frame, or the leading superscript of the matrix
   * @return     a Jama Matrix that represents the transformation.
   * @throws IllegalArgumentException if from or to are too high or low
   */
  public Matrix T(int from, int to) {
    if (from < 0 || to < 0 || from > num_joints+1 || to > num_joints+1) {
      throw new IllegalArgumentException();
    }

    // Start with a 4x4 indentity matrix so that we can iteratively multiply,
    // which allows us to get matrices where abs(from - to) > 1.
    // Furthermore, if from == to, then we properly return an indentity matrix.
    Matrix result = Matrix.identity(4,4);

    int low  = Math.min(from, to);
    int high = Math.max(from, to);

    for (int i = low; i < high; ++i) {
      double al = table.get("alpha").get(i);
      double a  = table.get("a").get(i);
      double d  = table.get("d").get(i);
      double th = table.get("theta").get(i);
      double[][] values = 
        {{c(th)      , -1*s(th)   , 0       , a         },
         {s(th)*c(al), c(th)*c(al), -1*s(al), -1*s(al)*d},
         {s(th)*s(al), c(th)*s(al), c(al)   , c(al)*d   },
         {0          , 0          , 0       , 1         }};

      Matrix T = new Matrix(values);
      result = result.times(T);
    }

    // If the reference frame is a higher link, we need to invert the result.
    if (from != high) {
      result = result.inverse();
    }

    return result;
  }

  /**
   * Computes the sin of an angle in degrees.
   *
   * @param a the angle, interpreted in degrees
   * @return  the sine of angle a
   */
  private double s(double a) {
    return Math.sin(Math.toRadians(a));
  }

  /**
   * Computes the cosine of an angle in degrees.
   *
   * @param a the angle, interpreted in degrees.
   * @return  the cosine of angle a
   */
  private double c(double a) {
    return Math.cos(Math.toRadians(a));
  }
}
