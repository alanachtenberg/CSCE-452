import Jama.*;

import java.lang.*;
import java.util.*;


// Mostly making this class to keep damage control contained in my attempt at making this
public class WorldControl {
	 private HashMap<String, ArrayList<Double> > table;
	 private int num_joints;
	 
	 public WorldControl(int n) {
		 
		 table = new HashMap<String, ArrayList<Double> >();
		 table.put("x", new ArrayList<Double>());
		 table.put("y", new ArrayList<Double>());
		 table.put("gamma", new ArrayList<Double>()); //I forget. Did we need to use this? He said don't worry
		 											  // about alpha right?
		 
		 // n+1?
		 for (int i = 0; i < n+1; ++i) {
		      table.get("x").add(0.0);
		      table.get("y").add(0.0);
		      table.get("gamma").add(0.0); 
		  } 
	 }
	 
	 //Mostly same as DHParams
	 public double get(String param, int index) {
		    if (param.equals("x") || param.equals("y")) {
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
	 
	 //Also mostly same as DHParams
	 public boolean set(String param, int index, double val) {
		    if (param.equals("x") || param.equals("y")) {
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
	  
	  //Now starts the part that is different from DHParams
	  public Matrix X(int from, int to) {
		  
		 if (from < 0 || to < 0 || from > num_joints+1 || to > num_joints+1) {
	       throw new IllegalArgumentException();
	     }
		  Matrix result = Matrix.identity(3,1);
		  int low  = Math.min(from, to);
		  int high = Math.max(from, to);
		  
		  
		  return result;
	  }
	
}