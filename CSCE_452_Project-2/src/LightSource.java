import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


/**
 *	LightSource Class
 *	Implements Light Source Objects
 */
public class LightSource extends JComponent{
    private static final String IMAGE_FILE = "light.png";
    private Image lightImage;
    private Dimension size;
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
		position= new Point(x, y);
		setIntensity(100);
        size= new Dimension(46,50);
        try {
            lightImage = ImageIO.read(getClass().getResource(IMAGE_FILE));
            lightImage=lightImage.getScaledInstance(size.width,size.height,Image.SCALE_REPLICATE);
        }
        catch(IOException e){
            System.out.println("ERROR: IMAGE FILE FAILED TO READ");
            e.printStackTrace();
        }
	}

	/**
	 * 
	 * @param x - x location of light source
	 * @param y - y location of light source
	 * @param intense - set intensity of light source
	 */
	public LightSource(int x, int y, int intense) {
		position= new Point(x, y);
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
		double distance = position.distance(x, y);
		return (intensity/distance);
	}

    @Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		//g.fillOval(position.x,position.y,5,5);//testing
        g.drawImage(lightImage,(int)(position.x-size.getWidth()/2),(int)(position.y-size.getHeight()/2),this);
	}
	
}
