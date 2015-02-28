
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



/**
 * @author alan
 *2/18/15
 *MainFrame Class will contain main function for project and acts as application window
 */

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//gets system screen size;
	//TODO implement without static
	private RobotArm arm;
	private Controls controls;
	private PaintPanel pPanel;
	/**
	 * @throws HeadlessException
	 */
	public MainFrame() throws HeadlessException {
	}

	/**
	 * @param gc
	 */
	public MainFrame(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public MainFrame(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param gc
	 */
	public MainFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	public void initRobotArm(){
		arm= new RobotArm(this.getWidth()/2,this.getHeight());
		arm.setVisible(true);
		//arm.setStarts(new Point(0,0), new Point(0,150), new Point(0,250), new Point(0,325));
		arm.setTheta(0, 0, 0);
		this.add(arm);
	}

	public void initControls()
	{
		Controls control = new Controls(arm);
		control.setVisible(true);
		this.add(control);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {		
						//TODO move main method into its own class
						MainFrame mainF= new MainFrame("Duct Tape & WD-40");
						mainF.setSize(screenSize.width/2,screenSize.height/2);//sets the main window to half of the screen size
						mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit program when main window closess
						mainF.setLayout(new GridLayout(1,2));//sets layout to a grid with two columns
						//use GridLayout to place controls on right side of canvas later						
						//TODO create control class containing all buttons and input boxes etc
						//then add to main frame
						mainF.setVisible(true);
						mainF.initRobotArm();
						mainF.initControls();
						mainF.arm.setPainterColor(new Color(0,200,100));
						mainF.arm.paintPoint();//test
						//Testing PaintPanel class here
						PaintPanel content = new PaintPanel();
						mainF.add(content);
						//Unfortunately, I think the paintpanel container covers the ellipses
						
						
						
					}
				});
				
	}

}
