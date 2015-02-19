import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
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
						JFrame menu= new MainFrame("Duct Tape & WD-40");
						Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//gets system screen size
						menu.setSize(screenSize.width/2,screenSize.height/2);//sets the main window to half of the screen size
						menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit program when main window closess
						menu.setVisible(true);
					}
				});
				
	}

}
