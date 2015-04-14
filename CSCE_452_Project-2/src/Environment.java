import java.awt.*;
import java.util.Vector;

/**
 * Created by Alan on 4/13/2015.
 */
public class Environment extends Canvas{
    public static final Dimension MIN_SIZE= new Dimension(800,600);
    private static final Double ROBOT_SCALE=1.0;

    private final Vector<Robot> robots=  new Vector<Robot>();
    private final Vector<LightSource> lightSources= new Vector<LightSource>();

    Environment(){
        super();
        this.setMinimumSize(MIN_SIZE);
        this.setSize(MIN_SIZE);

    }

    public void addRobot(Point position){
        Robot robot= new Robot(position,lightSources,ROBOT_SCALE);//last argument is the scale, scale=1 means size is DEFAULT_SIZE=(44,120)
        robots.add(robot);
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

}
