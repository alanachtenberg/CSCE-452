import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Vector;

/**
 * Created by Alan on 4/13/2015.
 */
public class Environment extends Canvas{
    public static final Dimension MIN_SIZE= new Dimension(800,600);
    private static final Double ROBOT_SCALE=.9;

    private final Vector<Robot> robots=  new Vector<Robot>();
    private final Vector<LightSource> lightSources= new Vector<LightSource>();

    //private ActionListener timerListener; defined later as anonymous type
    private Timer timer;
    private int delay=100;//delay for timer

    Environment(){
        super();
        this.setMinimumSize(MIN_SIZE);
        this.setSize(MIN_SIZE);
        timer= new Timer(delay,timerListener);
        timer.setInitialDelay(1000);//sets initial delay to 1 second
    }

    public void addRobot(Point position){

        // Default to I = K.
        double[][] K = {{1,0},{0,1}};
        Robot robot= new Robot(position,lightSources,ROBOT_SCALE,K);//last argument is the scale, scale=1 means size is DEFAULT_SIZE=(44,120)
        robots.add(robot);
        this.repaint();
    }

    public void addLight(Point position){
        LightSource light= new LightSource(position.x,position.y);
        lightSources.add(light);
        this.repaint();
    }

    public void clearRobots(){
        robots.clear();
    }
    public void clearLights(){
        clearLights();
    }
    public void removeLastRobot(){
        if(robots.size()>0){
            robots.remove(robots.size()-1);
        }
    }
    public void removeLastLight(){
        if (lightSources.size()>0){
            lightSources.remove(lightSources.size()-1);
        }
    }

    public void setTimerDelay(int d){
        delay=d;
        timer.setDelay(d);
    }

    public void startMovement(){
        timer.start();
    }
    public void stopMovement(){
        timer.stop();
    }
    @Override
    public void paint(Graphics g){
        for (LightSource lightSource : lightSources){//paint all lightsources
            lightSource.paint(g);
        }
        for(Robot robot : robots){//paint all robots, paint robots after lights so that they are visible over light
            robot.paint(g);
        }
    }

    private final ActionListener timerListener= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Robot robot : robots){
                //TODO update robot positions here
            }
        }
    };


}
