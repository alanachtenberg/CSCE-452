import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Alan on 4/21/2015.
 */
public class Environment extends Canvas{
    public static final Dimension MIN_SIZE= new Dimension(500,500);
    private ArrayList<Obstacle> obstacles;
    private Point start;
    private Point end;
    public static PathFinder pathFinder= new PathFinder();
    Environment(){
        super();
        start=new Point(-1,-1);//default just off the screen, upper left
        end= new Point(501,501);//default just off the screen, bottom right
        this.setMinimumSize(MIN_SIZE);
        obstacles=new ArrayList<Obstacle>(3);
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
        cellDivide();//new obstacle was added so lets run cell division
        this.repaint();
        return true;
    }
    public void removeLastObstacle(){
        if (obstacles.size()>0){
            obstacles.remove(obstacles.size()-1);
            this.repaint();
        }
    }
    public void clearObstacles(){
        obstacles.clear();
        this.repaint();
    }

    public void cellDivide(){
        pathFinder.setObstacles(obstacles);//
        pathFinder.cellDivide();
        this.repaint();
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2D=(Graphics2D)g;
        g2D.setStroke(new BasicStroke(4));
        //draw Border
       // g2D.draw(new Rectangle(this.getX()+2,this.getY()+2,this.getWidth()-5,this.getHeight()-5));
        //draw obstacles
        for (Obstacle obstacle : obstacles){
            g2D.draw(obstacle);
        }
        g2D.setColor(Color.cyan);
        for (Cell cell : PathFinder.cells){
            g2D.fill(cell);
        }
        g2D.setColor(Color.BLACK);
        for (Cell cell : PathFinder.cells){
            g2D.draw(cell);
            Point center=cell.getCenter();
            g2D.drawString(cell.getID(),center.x-cell.width/3,center.y);
        }
    }
}
