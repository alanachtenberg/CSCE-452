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

    private Point2D.Double centerAxle;//ABSOLUTE position of the center of the rear axel
    private double alpha;//orientation of vehicle, relative to the origin

    private Point2D.Double leftSensor,rightSensor; //RELATIVE position of the left and right sensor to centerAxel
    private Point2D.Double leftWheel, rightWheel; //RELATIVE position of the left and right wheel to centerAxel
    private Dimension size;

    private double k11,k12,k21,k22;
    private double w1,w2;

    private Image robotImage;


    public Robot( Point position, Vector<LightSource> sources, double scale, double[][] K)
    {//create a robot vehicle with dimension d
        super();
        lightSources=sources;
        alpha=45;
        size=new Dimension((int)(DEFAULT_SIZE.getWidth()*scale),(int)(DEFAULT_SIZE.getHeight()*scale));
        leftSensor=new Point2D.Double( -size.getWidth()/4,-size.getHeight()*5/6);//the rear axel is 5/6ths the car length from the headlights/sensors
        rightSensor=new Point2D.Double(size.getWidth()/4,-size.getHeight()*5/6);
        leftWheel=new Point2D.Double(-size.getWidth()/2,0);//relative to the axel the wheels are the same height
        rightWheel=new Point2D.Double(size.getWidth()/2,0);
        centerAxle= new Point2D.Double(position.getX(),position.getY());

        k11 = K[0][0];
        k12 = K[0][1];
        k21 = K[1][0];
        k22 = K[1][1];

        w1 = 0;
        w2 = 0;

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

    private Point2D getAbsolutePosition(Point2D.Double part){

        //apply rotation transformation to get new offsets
        Point2D p = new Point2D.Double(part.getX()*Math.cos(getAlphaInRads())-part.getY()*Math.sin(getAlphaInRads())
                , part.getX()*Math.sin(getAlphaInRads())+part.getY()*Math.cos(getAlphaInRads()));

        return new Point2D.Double(centerAxle.getX()+p.getX()
                ,centerAxle.getY()+p.getY());
    }

    private void setWheelSpeed() {

        double s1 = 0.0, s2 = 0.0;
        Point2D lsensor = getAbsolutePosition(leftSensor);
        Point2D rsensor = getAbsolutePosition(rightSensor);
        int lsx = (int)lsensor.getX(), lsy = (int)lsensor.getY();
        int rsx = (int)rsensor.getX(), rsy = (int)rsensor.getY();

        for (LightSource ls : lightSources) {
            s1 += ls.intensityToLightSource(lsx, lsy);
            s2 += ls.intensityToLightSource(rsx, rsy);
        }

        w1 = k11*s1 + k12*s2;
        w2 = k21*s1 + k22*s2;
    }

    private Point2D.Double rotateAbout(Point2D p, Point2D c, double theta) {

        double x = p.getX() - c.getX();
        double y = p.getY() - c.getY();

        double xp = x*Math.cos(theta) - y*Math.sin(theta);
        double yp = x*Math.sin(theta) + y*Math.cos(theta);

        return new Point2D.Double(xp, yp);
    }

    public void updatePosition(double dt) {

        setWheelSpeed();

        Point2D lwheel = getAbsolutePosition(leftWheel);
        Point2D rwheel = getAbsolutePosition(rightWheel);
        double axleWidth = lwheel.distance(rwheel);

        double f = Math.max(w1,w2);
        double s = Math.min(w1,w2);

        // Calculate turning radius.
        double r = 0;
        if (f != s) {
            r = axleWidth * (f/(f-s));
        }

        double a = f*dt;

        double rdx, rdy, theta;

        if (f == w1) {
            rdx = (r-axleWidth/2)*Math.cos(Math.toRadians(alpha)) * -1;
            rdy = (r-axleWidth/2)*Math.sin(Math.toRadians(alpha));
            theta = a/r * -1;
        } else {
            rdx = (r-axleWidth/2)*Math.cos(Math.toRadians(alpha));
            rdy = (r-axleWidth/2)*Math.sin(Math.toRadians(alpha)) * -1;
            theta = a/r;
        }

        double x = centerAxle.getX();
        double y = centerAxle.getY();
        Point2D C = new Point2D.Double(x+rdx, y+rdy);

        centerAxle = rotateAbout(centerAxle, C, theta);
        alpha += theta;
    }

    @Override //how to draw the robot
    public void paint(Graphics g){
        Graphics2D g2D= (Graphics2D)g;
        AffineTransform rotationT = AffineTransform.getRotateInstance(Math.toRadians(alpha)
                , centerAxle.getX()
                , centerAxle.getY());
        g2D.setTransform(rotationT);//set graphics to automatically transform the image
        g2D.drawImage(robotImage,(int)(centerAxle.getX()-size.getWidth()/2)
                ,(int)(centerAxle.getY()-size.getHeight()*5/6)//offset from the center axel to get the top left corner of the image
                ,this);
                

    }
}
