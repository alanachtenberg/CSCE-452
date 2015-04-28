import com.sun.javafx.geom.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Alan on 4/22/2015.
 */
public class Cell extends Rectangle{
    public static int count=0; //current number of cells, use to create unique id's

    private ArrayList<Cell> neighbors;
    private int id;
    private boolean visited;//used for search
    
   /*
    *@param loc location of cell, top left corner
    * @param size size of cell
    */
    public Cell(Point loc_,Dimension size_) {
        super(loc_.x,loc_.y,size_.width,size_.height);
        neighbors=new ArrayList<Cell>(4);
        id=count;
        ++count;
        visited = false;
    }

    public static void resetCount(){
        count=0;
    }
    public void addNeighbor(Cell c){
        if(this.contains(c.getLeft())){
            neighbors.add(c);
            return;//end execution of loop, so we do not do unneeded calculations
        }else if (this.contains(c.getRight())){
            neighbors.add(c);
            return;
        }else if (this.contains(c.getTop())){
            neighbors.add(c);
            return;
        }
        else if (this.contains(c.getBottom())){
            neighbors.add(c);
            return;
        }
    }
    public ArrayList<Cell> getNeighbors()
    {
    	return neighbors;
    }
    public String getID(){
        return Integer.toString(id);
    }

    public Point getCenter(){
        return new Point(this.x+(int)this.getWidth()/2,this.y+(int)this.getHeight()/2);
    }
    //returns point that lies just to the left of the cell
    public Point getLeft(){
        return new Point(this.x-1,this.y+1);
    }
    //returns point that lies just to the right of the cell
    public Point getRight(){
        return new Point (this.x+this.width+1,this.y+1);
    }
    public Point getTop(){
        return new Point(this.x+1,this.y-1);
    }
    public Point getBottom(){
        return  new Point(this.x+1,this.y+this.height+1);
    }

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited_) {
		this.visited = visited_;
	}
	
	public Boolean isInXRange(Point p){
        if (this.getX()<=p.getX() && p.getX()<(this.getX()+this.getWidth())){
            return true;
        }
        return false;
    }

    public Boolean isInYRange(Point p){
        if (this.getY()<=p.getY() && p.getY()<(this.getY()+this.getHeight())){
            return true;
        }
        return false;
    }
}