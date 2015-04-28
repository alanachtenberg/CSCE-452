import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

/**
 * Created by Alan on 4/22/2015.
 */
//class that calculates path
public class PathFinder {
    private static final int TOTAL_WIDTH=500;
    private static final int TOTAL_HEIGHT=500;
    private static Point start;
    private static Point end;
    private static Cell startCell;
    private static Cell endCell;
    private static ArrayList<Obstacle> obstacles;
    private static ArrayList<Point> vLines;//only care about x coordinate
    private static ArrayList<Point> hLines;//only care about y coordinate
    public static ArrayList<Cell> cells;
    private static ArrayList<ArrayList<Point>> paths;
    private static Stack<Cell> currentPath;
    
    public PathFinder(){
        obstacles=new ArrayList<Obstacle>();
        cells=new ArrayList<Cell>();
        start=new Point();
        end=new Point();
        paths = new ArrayList<ArrayList<Point>>();
        currentPath = new Stack<Cell>();
    }

    public void setObstacles(ArrayList<Obstacle> obstacles_){
        obstacles=obstacles_;
    }
    
    public void setPath(Point start_, Point end_)
    {
    	start = start_;
    	end = end_;
    	for(Cell node: cells)
    	{
    		if(node.isInXRange(start) && node.isInYRange(start))
    		{
    			setStartCell(node);
    		}
    		else if(node.isInXRange(end) && node.isInYRange(end))
    		{
    			setEndCell(node);
    		}
    	}
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
        cells.clear();//insure cells is empty
        if (obstacles.size()==0){//no cells to add
            return;
        }
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
    public static void findPaths(Cell node){
    	//the goal cell will never have visited flag set to true b/c
    	//we want to get there in as many paths possible.
    	if(!(node.getID().equals(endCell.getID())))
    	{
    		//mark node as visited
    		node.setVisited(true);
	    	//add current cell to stack
	    	currentPath.push(node);
	    	//go thru each child and visit
	    	//until goal (endCell) is reached and/or all cells have been visited
    		boolean nodeB4Goal = false;
	    	for(Cell temp: node.getNeighbors())
	    	{
	    		if(temp.getID().equals(endCell.getID()))
	    		{
	    			nodeB4Goal = true;
	    		}
	    		if(!temp.isVisited())
	    		{
	    			findPaths(temp);
	    		}
	    	}
	    	if(nodeB4Goal)
    		{
    			node.setVisited(false);
    		}
	    	currentPath.pop();
    	}
    	else	//if current cell is the goal cell...
    	{
    		//store stack of nodes as a possible path and return
    		currentPath.push(node);
    		addPath(currentPath);
    		currentPath.pop();
    	}
    	
    	//This is not efficient but we need a kill/end case
    	//Let's see if this works and then try to improve it
    	int visitedCounter = 0;
    	for(Cell temp: cells)
    	{
    		if(temp.isVisited())
    		{
    			visitedCounter++;
    		}
    	}
    	if(visitedCounter == (cells.size()-1))//size()-1 b/c goal node will never have visited flag set to true
    	{
    		return;
    	}
    }

	/**
	 * @return the startCell
	 */
	public static Cell getStartCell() {
		return startCell;
	}

	/**
	 * @param startCell the startCell to set
	 */
	public static void setStartCell(Cell startCell) {
		PathFinder.startCell = startCell;
	}

	/**
	 * @return the endCell
	 */
	public static Cell getEndCell() {
		return endCell;
	}

	/**
	 * @param endCell the endCell to set
	 */
	public static void setEndCell(Cell endCell) {
		PathFinder.endCell = endCell;
	}

	/**
	 * @return the paths after generating the paths
	 */
	public ArrayList<ArrayList<Point>> getPaths() {
		findPaths(startCell);
		return paths;
	}

	/**
	 * 
	 * @param cellPath add this to ArrayList<ArrayList<Point>> paths
	 */
	public static void addPath(Stack<Cell> cellPath) {
		ArrayList<Point> newPath = new ArrayList<Point>();
		for(Cell node: cellPath)
		{
			if(node.getID().equals(startCell.getID()))
			{
				newPath.add(start);
			}
			else if(node.getID().equals(endCell.getID()))
			{
				newPath.add(end);
			}
			else
			{
				newPath.add(node.getCenter());
			}
		}
		paths.add(newPath);
	}
}
