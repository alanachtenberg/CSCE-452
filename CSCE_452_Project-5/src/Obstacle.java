import javax.swing.*;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Alan on 4/21/2015.
 */
//only exposes methods useful to us of Rectangle2D.Double
public class Obstacle extends Rectangle2D.Double{

    Obstacle(Point position, Dimension size){
        super(position.getX(),position.getY(),size.getWidth(),size.getHeight());
    }


    public Point2D getPosition(){
        return new Point2D.Double(this.getX(),this.getY());
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
        Point2D pos=o.getPosition();
        Dimension size=o.getSize();
        return this.contains(pos.getX(),pos.getY(),size.getWidth(),size.getHeight());
    }

}
