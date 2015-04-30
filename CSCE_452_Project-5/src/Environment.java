import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alan on 4/21/2015.
 */
public class Environment extends Canvas{
    public static final Dimension MIN_SIZE= new Dimension(500,500);
    private ArrayList<Obstacle> obstacles;
    private Point start;
    private Point end;
    public static PathFinder pathFinder= new PathFinder();
    private ArrayList<ArrayList<Point>> paths;
    private Boolean drawCells;
    Environment(){
        super();
        start=new Point(-1,-1);//default just off the screen, upper left
        end= new Point(501,501);//default just off the screen, bottom right
        this.setMinimumSize(MIN_SIZE);
        obstacles=new ArrayList<Obstacle>(3);
        paths = new ArrayList<ArrayList<Point>>();
        drawCells=false;
    }

    public Boolean setPath(Point start_,Point end_){
        for (Obstacle obstacle :obstacles) {
            if(obstacle.checkForCollision(start_)||obstacle.checkForCollision(end_)){
                return false;
            }
        }
        this.start = start_;
        this.end = end_;
        pathFinder.setPath(this.start, this.end);
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
        cellDivide();//obstacle was added, do new division
        this.repaint();
        return true;
    }
    public void removeLastObstacle(){
        if (obstacles.size()>0){
            obstacles.remove(obstacles.size()-1);
            cellDivide();//obstacles was changed, do new division
            this.repaint();
        }
    }
    public void clearObstacles(){
        obstacles.clear();
        cellDivide();//removes old cells
        this.repaint();
    }

    public void cellDivide(){
        pathFinder.setObstacles(obstacles);//
        pathFinder.cellDivide();
        this.repaint();
    }
    
    /**
     * Calls getPaths() function in path finder.
     */
    public void getPaths()
    {
         pathFinder.setPath(start,end);
    	 paths = pathFinder.getPaths();
    	 this.repaint();
    }
    public void toggleCells(){
        if (drawCells==false){
            drawCells=true;
        }
        else {
            drawCells = false;
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0,0,500,500);//clear canvas
        Graphics2D g2D=(Graphics2D)g;
        g2D.setStroke(new BasicStroke(4));
        //draw Border
        //g2D.draw(new Rectangle(this.getX()+2,this.getY()+2,this.getWidth()-5,this.getHeight()-5));
        //draw obstacles
        for (Obstacle obstacle : obstacles){
            g2D.draw(obstacle);
        }
        if (drawCells) {
            g2D.setColor(Color.cyan);
            for (Cell cell : PathFinder.cells) {
                g2D.fill(cell);
            }
            g2D.setColor(Color.BLACK);
            for (Cell cell : PathFinder.cells) {
                g2D.draw(cell);
                Point center = cell.getCenter();
                g2D.drawString(cell.getID(), center.x - cell.width / 3, center.y);
            }
        }
        //start and end points move somewhere else
        g2D.setColor(Color.WHITE);
        g2D.fillOval(start.x, start.y, 5, 5);
        g2D.fillOval(end.x,end.y,5,5);
        //draw paths
        g2D.setColor(Color.MAGENTA);
        for(ArrayList<Point> path: paths)
        {
        	if(path==paths.get(0))
        	for(int x = 0; x<(path.size()-1); x++)
        	{
        		g2D.drawLine((int)path.get(x).getX(), (int)path.get(x).getY(), (int)path.get(x+1).getX(), (int)path.get(x+1).getY());
        	}
        }
    }
}
