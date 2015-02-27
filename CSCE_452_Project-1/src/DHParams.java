import Jama.*;

import java.lang.*;
import java.util.*;

public class DHParams {

  private HashMap<String, ArrayList<Double> > table;
  private int num_joints;

  public DHParams(int num_joints) {
    this.num_joints = num_joints;

    table = new HashMap<String, ArrayList<Double> >();

    table.put("alpha", new ArrayList<Double>());
    table.put("a", new ArrayList<Double>());
    table.put("d", new ArrayList<Double>());
    table.put("theta", new ArrayList<Double>());

    for (int i = 0; i < num_joints+1; ++i) {
      table.get("alpha").add(0.0);
      table.get("a").add(0.0);
      table.get("d").add(0.0);
      table.get("theta").add(0.0);
    }
  }

  public double get(String param, int index) {
    if (param.equals("d") || param.equals("theta")) {
      return table.get(param).get(index - 1);
    }
    return table.get(param).get(index);
  }

  public boolean set(String param, int index, double val) {
    if (param.equals("d") || param.equals("theta")) {
      table.get(param).set(index - 1, val);
    } else {
      table.get(param).set(index ,val);
    }

    return true;
  }

  public Matrix T(int from, int to) {
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

    if (from != high) {
      result = result.inverse();
    }

    return result;
  }

  private double s(double a) {
    return Math.sin(Math.toRadians(a));
  }

  private double c(double a) {
    return Math.cos(Math.toRadians(a));
  }
}
