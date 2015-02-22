import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sun.awt.image.ToolkitImage;



/**
 * @author alan
 *2/18/15
 *MainFrame Class will contain main function for project and acts as application window
 */

public class MainFrame extends JFrame {
	
	
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {		
						//TODO move main method into its own class
						MainFrame mainF= new MainFrame("Duct Tape & WD-40");
						Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//gets system screen size
						mainF.setSize(screenSize.width/2,screenSize.height/2);//sets the main window to half of the screen size
						mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit program when main window closess
						mainF.setLayout(new GridLayout(1,2));//sets layout to a grid with two columns
						//use GridLayout to place controls on right side of canvas later
						//TODO create control class containing all buttons and input boxes etc
						//then add to main frame
						mainF.setVisible(true);
						
						//Testing PaintPanel class here
						PaintPanel content = new PaintPanel();
						mainF.setContentPane(content);
						//Unfortunately, I think the paintpanel container covers the ellipses
						
						//TODO create class inherited from canvas to draw all links on.and then add to main frame.
						//Create and add links to window
						Link linkOne= new Link(mainF.getWidth()/4,mainF.getHeight(),150,30);
						linkOne.setTheta(30);//simple test val, set to 0 later
						mainF.add(linkOne);
						
						Link linkTwo= new Link(mainF.getWidth()/4,mainF.getHeight()-150,100,20);
						linkTwo.setTheta(-30);//in degrees
						mainF.add(linkTwo);
						
						Link linkThree= new Link(mainF.getWidth()/4,mainF.getHeight()-250,75,15);
						linkThree.setTheta(15);
						mainF.add(linkThree);
						
						
						
						
					}
				});
				
	}

}
