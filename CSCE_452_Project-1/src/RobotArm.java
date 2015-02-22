import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Point;

public class RobotArm extends Canvas {
	private static final long serialVersionUID = 1L;
	
	private static final int NUMLINKS=3;
	private final Link[] links= new Link[NUMLINKS];//constant array, all our robot arms will have 3 links
	private final Point[] relativeStarts= new Point[NUMLINKS];
	private final float theta[]= new float[NUMLINKS];//constant array, only array is constant not floats inside
	private Point origin;//origin point, point of arm base
	//TODO
	//Add painter class later
	//private static final painter;
	
	public RobotArm(int w, int h) {
		super();//call default constructor for Canvas
		this.setSize(new Dimension(w, h));
		//Construct links
		links[0]=new Link(150,30);//a new link 150 pixels long and 30 wide
		links[1]= new Link(100,20);
		links[2]= new Link(75,15);
		origin= new Point(0,0);
	}
	private void updateLinks(float thetaOne,float thetaTwo, float thetaThree ){
		links[0].updateLink(origin.x,origin.y, Color.RED);
		links[0].setTheta(thetaOne);//simple test val, set to 0 later;
		
		links[1].updateLink(origin.x,origin.y-150,Color.GREEN);
		links[1].setTheta(thetaTwo);//in degrees;
		
		links[2].updateLink(origin.x,origin.y-250,Color.BLUE);
		links[2].setTheta(thetaThree);
	}
	public void paint(Graphics g){
		origin.setLocation(this.getWidth()/2, this.getHeight());
		
		//draw base
		g.fillOval(origin.x-50, origin.y-10, 100, 20);
		//paint links
		updateLinks(theta[0],theta[1],theta[2]);
		for (int i=0;i<NUMLINKS;++i){
			links[i].paint(g);//paint link i
		}
	}
	public void setTheta(float t1,float t2,float t3){
		theta[0]=t1;
		theta[1]=t2;
		theta[2]=t3;
	}
	public void setStarts(Point p1,Point p2, Point p3){
		relativeStarts[0]=p1;
		relativeStarts[1]=p2;
		relativeStarts[2]=p3;
	}
	public RobotArm(GraphicsConfiguration config) {
		super(config);
	}

}
