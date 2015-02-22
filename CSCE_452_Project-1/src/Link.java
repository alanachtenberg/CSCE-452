
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;



public class Link extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Shape ellipse;//we use shape, because when we transform Ellipse2D it returns path2D, both inherit from shape
	private Color color;
	private Point start;
	private float theta;
	private final double length,width;//only set the length and width once
	
	public Link(int l, int w) {
		ellipse=null;
		start=null;
		length=l; width=w;
	}
	public void updateLink(int x, int y, Color c){
		start= new Point(x, y);
		color=c;
	}
	/*
	 * paint(Graphics g) is an overidden method of JComponent, it handles the drawing logic when placed on a window
	 * ie. no need to call directly.
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		Graphics2D g2D=(Graphics2D)g;
		//subtract width/2 and length to get top left corner
		//user inputs the point that is at the bottom of the ellipse when in its 0 degree position
		ellipse= new Ellipse2D.Double(start.getX()-width/2, start.getY()-length, width, length);//create ellipse object
		//theta is inverted to match our definition of frames, left from vertical is positive, right is negative
		AffineTransform rotationT= AffineTransform.getRotateInstance(-theta, start.getX(), start.getY());
		ellipse=rotationT.createTransformedShape(ellipse);//rotate our ellipse
		
		g2D.setStroke(new BasicStroke(2.0f));//set stroke to 2 pixels
		Paint gradient = new GradientPaint((int)(start.getX()-width),(int)(start.getY()-length),Color.WHITE,
				(int)(start.getX()+width), (int)(start.getY()+length),color);
		g2D.setPaint(gradient);
		g2D.fill(ellipse);//fill elipse with gradient paint
		g2D.setColor(Color.BLACK);//set color to black for our stroke
		g2D.draw(ellipse);//stroke ellipse
	}
	
	public Point getStart(){
		return start;
	}

	public void setTheta(float _theta){
		theta=_theta/180*(float)Math.PI;//convert theta from degrees to radians for transform later
	}
	public void setStart(int x, int y){
		start= new Point (x,y);
	}
}
