import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Alan on 4/22/2015.
 */
//class that calculates path
public class PathFinder {
    private static final int TOTAL_WIDTH=500;
    private static final int TOTAL_HEIGHT=500;
    private static ArrayList<Obstacle> obstacles;
    private static ArrayList<Point> vLines;//only care about x coordinate
    private static ArrayList<Point> hLines;//only care about y coordinate
    public static ArrayList<Cell> cells;

    public PathFinder(){
        obstacles=new ArrayList<Obstacle>();
        cells=new ArrayList<Cell>();
    }

    public void setObstacles(ArrayList<Obstacle> obstacles_){
        obstacles=obstacles_;
    }

    private void generateLines(){
        hLines= new ArrayList<Point>();
        vLines = new ArrayList<Point>();

        for(Obstacle obstacle : obstacles){//get all verticies of obstacles
            hLines.add(obstacle.getTopLeftVertex());//for top line of rectangle
            hLines.add(obstacle.getBottomRightVertex());//for bottomr line of rectangle
            vLines.add(obstacle.getTopLeftVertex());//for left line of rectangle
            vLines.add(obstacle.getBottomRightVertex());//for right line of rectangle
        }
        hLines.sort(new Comparator<Point>() {//sort from top to bottom
            @Override
            public int compare(Point o1, Point o2) {
                return (int)(o1.getY()-o2.getY());
            }
        });
        vLines.sort(new Comparator<Point>() {//sort from left to right
            @Override
            public int compare(Point o1, Point o2) {
                return (int)(o1.getX()-o2.getX());
            }
        });
    }
    private void createAdjacenyList(){
        for (int i=0;i<cells.size();++i){
            Cell currentCell=cells.get(i);
            for (int j=0;j<cells.size();++j){
                if (currentCell!=cells.get(j)){
                    currentCell.addNeighbor(cells.get(j));
                }
            }
        }
    }
    private void addCell(int x, int y, int w, int h){
        Rectangle area= new Rectangle(new Point(x,y), new Dimension(w,h));//avoid constructing a cell here so that id is not incremented
        Boolean collision=false;//do not want to use the cell if it collides with an obstacle
        for (Obstacle obstacle : obstacles){
            if (obstacle.checkForCollision(area)){
                collision=true;
            }
        }
        if (!collision){//if cell does not contain an obstacle
            cells.add(new Cell(new Point(x,y), new Dimension(w,h)));//add it to our list
        }
    }

    //divides up space into cells
    public void cellDivide(){
        generateLines();//determine vertices to use when creating cells, is sorted left to right
        Cell.resetCount();//reset counter for creating cell id's
        int lastVertical=0;//x pos
        int lastHorizontal=0;//y pos
        for (Point hLine : hLines) {
            lastVertical=0;//we are moving from left 2 right, reset every horizontal row
            for (Point vLine : vLines) {
                addCell(lastVertical,lastHorizontal,vLine.x-lastVertical,hLine.y-lastHorizontal);
                lastVertical=(int)vLine.getX();//shift starting x position of next cell
            }
            if (lastVertical<500){//add right boundary cell
                addCell(lastVertical,lastHorizontal,500-lastVertical,hLine.y-lastHorizontal);
            }
            lastHorizontal=(int)hLine.getY();//shift starting y position of next cell
        }
        if (lastHorizontal<500){//add bottom boundary cells
            lastVertical=0;
            for (Point vLine: vLines) {
                addCell(lastVertical, lastHorizontal, vLine.x-lastVertical, 500 - hLines.get(hLines.size() - 1).y);
                lastVertical=vLine.x;
            }
            addCell(lastVertical,lastHorizontal,500-lastVertical,500-hLines.get(hLines.size()-1).y);//add bottom right cell
        }
        createAdjacenyList();
    }

    //use graph search with cells to find path
    public ArrayList<Point> findPath(){



        return null;//if path not found
    }
}
