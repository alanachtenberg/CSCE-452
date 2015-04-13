import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

/**
 * Created by Alan on 4/13/2015.
 */
public class Robot extends JComponent{
    public static final Dimension DEFAULT_SIZE=new Dimension(25,75);

    private Vector<LightSource> lightSources;

    private Point2D.Double centerAxel;//ABSOLUTE position of the center of the rear axel
    private double alpha;//orientation of vehicle, relative to the origin

    private Point2D.Double leftSensor,rightSensor; //RELATIVE position of the left and right sensor to centerAxel
    private Point2D.Double leftWheel, rightWheel; //RELATIVE position of the left and right wheel to centerAxel
    private Dimension size;

    private Image picture;

    public Robot(vector<LightSource> sources,double scale){//create a robot vehicle with dimension d
        super();
        lightSources=sources;
        alpha=0;
        size=new Dimension((int)(DEFAULT_SIZE.getWidth()*scale),(int)(DEFAULT_SIZE.getHeight()*scale));
        leftSensor=new Point2D.Double( -size.getWidth() / 4,  size.getHeight());
        rightSensor=new Point2D.Double(size.getWidth()/4,size.getHeight());
        leftWheel=new Point2D.Double(-size.getWidth()/2,0);
        rightWheel=new Point2D.Double(size.getWidth()/2,0);
        centerAxel= new Point2D.Double(size.getWidth()/2,0);
    }

    private Point2D getAbsoultePostion(Point part){
        //TODO fix logic
        return new Point2D.Double(centerAxel.getX()+Math.cos(alpha)*part.getX()
                ,centerAxel.getY()+Math.sin(alpha)*part.getY());
    }


    @Override //how to draw the robot
    public void paint(Graphics g){

    }
}
