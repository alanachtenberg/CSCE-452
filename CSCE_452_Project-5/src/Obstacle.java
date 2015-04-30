import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Alan on 4/21/2015.
 */
//only exposes methods useful to us of Rectangle2D.Double
public class Obstacle extends Rectangle2D.Double{

    Obstacle(Point position, Dimension size){
        super(position.getX(),position.getY(),size.getWidth(),size.getHeight());
    }


    public Point getTopLeftVertex(){
        return new Point((int)this.getX(),(int)this.getY());
    }

    public Point getTopRightVertex(){
        return new Point((int)(this.getX()+this.getWidth()),(int)this.getY());
    }
    public Point getBottomLeftVertex(){
        return new Point((int)(this.getX()),(int)(this.getY()+this.getHeight()));
    }
    public Point getBottomRightVertex(){
        return new Point((int)(this.getX()+this.getWidth()),(int)(this.getY()+this.getHeight()));
    }

    public Dimension getSize(){
        return new Dimension((int)this.getWidth(),(int)this.getHeight());
    }

    //checks for collision with points
    public Boolean checkForCollision(Point p){
        return this.contains(p);
    }
    //checks for collision with other obstacle
    public Boolean checkForCollision(Obstacle o){
        Point pos=o.getTopLeftVertex();
        Dimension size=o.getSize();
        return this.intersects(pos.getX(),pos.getY(),size.getWidth(),size.getHeight());
    }
    public Boolean checkForCollision(Rectangle r){
        Point pos=new Point((int)r.getX(),(int)r.getY());
        Dimension size=r.getSize();
        return this.intersects(pos.getX(),pos.getY(),size.getWidth(),size.getHeight());
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