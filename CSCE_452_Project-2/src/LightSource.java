import java.awt.Graphics;
import java.awt.Point;


/**
 *	LightSource Class
 *	Implements Light Source Objects
 */
public class LightSource {

	/**
	 * Each light source object has an associated x and y position 
	 * and a value for intensity of the source
	 */
	private Point position;
	private int intensity;
	
	/**
	 * 
	 * @param x - x location of light source
	 * @param y - y location of light source
	 * intensity set to 100 by default
	 */
	public LightSource(int x, int y) {
		position.setLocation(x, y);
		setIntensity(100);
	}

	/**
	 * 
	 * @param x - x location of light source
	 * @param y - y location of light source
	 * @param intense - set intensity of light source
	 */
	public LightSource(int x, int y, int intense) {
		position.setLocation(x, y);
		setIntensity(intense);
	}

	/**
	 * @return the intensity
	 */
	public int getIntensity() {
		return intensity;
	}

	/**
	 * @param intense the intensity to set
	 */
	public void setIntensity(int intense) {
		intensity = intense;
	}
	
	/**
	 * 
	 * @return the x location as an integer
	 */
	public int getXLocation(){
		return (int) position.getX();
	}
	
	/**
	 * 
	 * @param x - set x position
	 */
	public void setXLocation(int x){
		position.setLocation(x, position.getY());
	}
	
	/**
	 * 
	 * @return the y location as an integer
	 */
	public int getYLocation(){
		return (int) position.getY();
	}
	
	/**
	 * 
	 * @param y - set y position
	 */
	public void setYLocation(int y){
		position.setLocation(position.getX(), y);
	}
	
	/**
	 * 
	 * @param x - set x location
	 * @param y - set y location
	 */
	public void setLocation(int x, int y){
		position.setLocation(x, y);
	}
	
	/**
	 * 
	 * @param x - x location
	 * @param y - y location
	 * @return - intensity of lightsource from location (x,y)
	 */
	public double intensityToLightSource(int x, int y){
		double xSquared = Math.pow(x, 2);
		double ySquared = Math.pow(y, 2);
		double distance = Math.sqrt(ySquared+xSquared);
		return (intensity/distance);
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
}
