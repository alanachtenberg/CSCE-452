import java.awt.*;
import java.util.Vector;

/**
 * Created by Alan on 4/13/2015.
 */
public class Environment extends Canvas{
    public static final Dimension MIN_SIZE= new Dimension(800,600);
    private Vector<Robot> robots;
    private Vector<LightSource> lightSources;

    Environment(){
        super();
        setMinimumSize(MIN_SIZE);
    }

}
