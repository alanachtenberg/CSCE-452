import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Vector;

/**
 * Created by Alan on 4/21/2015.
 */
public class Environment extends Canvas{
    public static final Dimension MIN_SIZE= new Dimension(500,500);
    private Vector<Obstacle> obstacles;
    private Point start;
    private Point end;
    Environment(){
        super();
        start=new Point();
        end= new Point();
        this.setMinimumSize(MIN_SIZE);
        obstacles=new Vector<Obstacle>(3);
    }

    public Boolean setPath(Point start_,Point end_){
        for (Obstacle obstacle :obstacles) {
            if(obstacle.checkForCollision(start_)||obstacle.checkForCollision(end_)){
                return false;
            }
        }
        this.start = start_;
        this.end = end_;
        return true;
    }
    //returns true on success
    //returns false if obstacle has collision
    public Boolean addObstacle(Obstacle o){
        for (Obstacle obstacle : obstacles) {
            if (obstacle.checkForCollision(o))
                return false;
        }

        if (o.checkForCollision(start)||o.checkForCollision(end)){
            return false;
        }

        obstacles.add(o);
        this.repaint();
        return true;
    }
    public void removeLastObstacle(){
        if (obstacles.size()>0){
            obstacles.remove(obstacles.size()-1);
        }
    }
    public void clearObstacles(){
        obstacles.clear();
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2D=(Graphics2D)g;
        g2D.setStroke(new BasicStroke(4));
        //draw Border
        g2D.draw(new Rectangle(this.getX()+2,this.getY()+2,this.getWidth()-5,this.getHeight()-5));
        //draw obstacles
        for (Obstacle obstacle : obstacles){
            g2D.draw(obstacle);
        }
    }
}
