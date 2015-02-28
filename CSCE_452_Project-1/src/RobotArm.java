import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.Vector;
import java.lang.Math;
import Jama.*;

public class RobotArm extends Canvas {
	private class ColorPoint extends Point{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public Color pointColor;
		
		public ColorPoint(int x, int y, Color c){
			super(x,y);
			pointColor=c;
		}
	}
	private static final long serialVersionUID = 1L;
	private static final int NUMLINKS=3;
	private Point painter;//location of painter
	private static final Vector<ColorPoint> paintVector= new Vector<ColorPoint>();
	private Color color; //current color of painter
	private final Link[] links= new Link[NUMLINKS];//constant array, all our robot arms will have 3 links
	private final Point[] relativeStarts= new Point[NUMLINKS];
	//private final float theta[]= new float[NUMLINKS];//constant array, only array is constant not floats inside
	private Point origin;//origin point, point of arm base
	//TODO
	//Add painter class later
	//private static final painter;

	private DHParams params;
	
	public RobotArm(int w, int h) {
		super();//call default constructor for Canvas
		this.setSize(new Dimension(w, h));
		//Construct links
		links[0]=new Link(150,30);//a new link 150 pixels long and 30 wide
		links[1]= new Link(100,20);
		links[2]= new Link(75,15);
		origin= new Point(0,0);

		params = new DHParams(NUMLINKS);

		for (int i = 1; i <= 4; ++i) {
			params.set("alpha", i-1, 0);
			params.set("d", i, 0);
			params.set("theta", i, 0);
		}

		params.set("a", 0, 0);
		params.set("a", 1, 150);
		params.set("a", 2, 100);
		params.set("a", 3, 75);
	}
	private void updateLinks(){
		links[0].updateLink(origin.x-relativeStarts[0].x,
					origin.y-relativeStarts[0].y, 
					Color.RED);
		
		links[1].updateLink(origin.x-relativeStarts[1].x,
					origin.y-relativeStarts[1].y,
					Color.GREEN);
		
		links[2].updateLink(origin.x-relativeStarts[2].x,
					origin.y-relativeStarts[2].y,
					Color.BLUE);

		for (int i = 0; i < NUMLINKS; ++i) {
			double theta = 0;
			for (int j = 0; j < i+1; ++j) {
				theta += params.get("theta", j+1);
			}

			links[i].setTheta(theta);
		}
	}
	public void paint(Graphics g){
		origin.setLocation(this.getWidth()/2, this.getHeight());
		
		//draw base
		g.fillOval(origin.x-50, origin.y-10, 100, 20);
		//paint links
		updateLinks();
		for (int i=0;i<NUMLINKS;++i){
			links[i].paint(g);//paint link i
		}
		//draw painter
		Graphics2D g2D=(Graphics2D)g;
		g2D.setColor(Color.BLACK);
		g2D.draw(new Ellipse2D.Double(origin.getX()-painter.getX()-5, origin.getY()-painter.getY()-5, 10, 10));
		//draw painted points
		for(int i=0; i<paintVector.size();++i){
			ColorPoint point=paintVector.get(i);
			g2D.setColor(point.pointColor);
			g2D.fill(new Ellipse2D.Double(origin.getX()-point.getX()-5, 
					origin.getY()-point.getY()-5, 10, 10));
		}
			
	}
	public void setTheta(double t1, double t2, double t3){

		double[][] o = {{0},{0},{0},{1}};
		Matrix orig_0 = new Matrix(o);

		Point[] points = new Point[NUMLINKS+1];

		params.set("theta", 1, t1);
		params.set("theta", 2, t2);
		params.set("theta", 3, t3);

		for (int i = 0; i < NUMLINKS+1; ++i) {
			Matrix mp = params.T(i+1, 0).times(orig_0);
			int x = (int)Math.round(mp.get(1,0));
			int y = (int)Math.round(mp.get(0,0));
			points[i] = new Point(x,y);
		}

		setStarts(points[0], points[1], points[2], points[3]);
	}
	public void setStarts(Point p1,Point p2, Point p3, Point paint){
		relativeStarts[0]=p1;
		relativeStarts[1]=p2;
		relativeStarts[2]=p3;
		painter=paint;
	}
	public void setPainterColor(Color c){
		color=c;
	}
	public void paintPoint(){
		paintVector.add(new ColorPoint(painter.x,painter.y,color));
	}
	public void clearPaint(){
		paintVector.clear();//clear points to paint
	}
	public RobotArm(GraphicsConfiguration config) {
		super(config);
	}
	
}
