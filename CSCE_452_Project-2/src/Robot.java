import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Alan on 4/13/2015.
 */
public class Robot extends JComponent{
    public static final Dimension DEFAULT_SIZE=new Dimension(44,120);//default size without distortion
    private static final String IMAGE_FILE = "57belair.png";

    private Vector<LightSource> lightSources;

    private Point2D.Double centerAxel;//ABSOLUTE position of the center of the rear axel
    private double alpha;//orientation of vehicle, relative to the origin

    private Point2D.Double leftSensor,rightSensor; //RELATIVE position of the left and right sensor to centerAxel
    private Point2D.Double leftWheel, rightWheel; //RELATIVE position of the left and right wheel to centerAxel
    private Dimension size;

    private Image robotImage;


    public Robot(Point position,Vector<LightSource> sources,double scale) {//create a robot vehicle with dimension d
        super();
        lightSources=sources;
        alpha=45;
        size=new Dimension((int)(DEFAULT_SIZE.getWidth()*scale),(int)(DEFAULT_SIZE.getHeight()*scale));
        leftSensor=new Point2D.Double( -size.getWidth()/4,-size.getHeight()*5/6);//the rear axel is 5/6ths the car length from the headlights/sensors
        rightSensor=new Point2D.Double(size.getWidth()/4,-size.getHeight()*5/6);
        leftWheel=new Point2D.Double(-size.getWidth()/2,0);//relative to the axel the wheels are the same height
        rightWheel=new Point2D.Double(size.getWidth()/2,0);
        centerAxel= new Point2D.Double(position.getX(),position.getY());
        try {
            robotImage = ImageIO.read(getClass().getResource(IMAGE_FILE));
            robotImage=robotImage.getScaledInstance(size.width,size.height,Image.SCALE_REPLICATE);
        }
        catch(IOException e){
            System.out.println("ERROR: IMAGE FILE FAILED TO READ");
            e.printStackTrace();
        }
    }
    public void setAlpha(double alpha){
        this.alpha=alpha;
    }
    public double getAlphaInRads(){
        return Math.toRadians(alpha);
    }
    public double getAlpha(){
        return alpha;
    }

    private Point2D getAbsoultePostion(Point2D.Double part){

        //apply rotation transformation to get new offsets
        Point2D p = new Point2D.Double(part.getX()*Math.cos(getAlphaInRads())-part.getY()*Math.sin(getAlphaInRads())
                , part.getX()*Math.sin(getAlphaInRads())+part.getY()*Math.cos(getAlphaInRads()));

        return new Point2D.Double(centerAxel.getX()+p.getX()
                ,centerAxel.getY()+p.getY());
    }


    @Override //how to draw the robot
    public void paint(Graphics g){
        Graphics2D g2D= (Graphics2D)g;
        AffineTransform rotationT = AffineTransform.getRotateInstance(Math.toRadians(alpha)
                , centerAxel.getX()
                , centerAxel.getY());
        g2D.setTransform(rotationT);//set graphics to automatically transform the image
        g2D.drawImage(robotImage,(int)(centerAxel.getX()-size.getWidth()/2)
                ,(int)(centerAxel.getY()-size.getHeight()*5/6)//offset from the center axel to get the top left corner of the image
                ,this);
                

    }
}
