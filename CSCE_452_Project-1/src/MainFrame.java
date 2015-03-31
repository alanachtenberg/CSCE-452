
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * @author alan
 *2/18/15
 *MainFrame Class will contain main function for project and acts as application window
 */

public class MainFrame extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RobotArm arm;
	private JPanel panel;
	
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
		panel= new JPanel();
		panel.setLayout(new GridLayout(2,1));
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
		arm.setTheta(0, 0, 0);
		this.add(arm);
	}

	public void initControls()
	{
		Controls control = new Controls(arm);
		control.setVisible(true);
		panel.add(control);
	}
	public void initPaintPanel(){
		PaintPanel pPanel = new PaintPanel(arm);
		panel.add(pPanel);
	}
	public void initialize(){
		if (Main.CLIENT_MODE){
			Main.CLIENT= new Client(Main.HOST,Main.PORT_NUM);//init client socket
			initRobotArm();//initialize robot arm, added to main frame
			initControls();//initialize controls, added to jpanel
			initPaintPanel();//init paint panel, added to jpanel
			this.add(panel);
		}
		else
		{
			initRobotArm();
			Thread serverThread= new Thread(new Server(Main.PORT_NUM,arm));
			serverThread.start();//init server socket
		}
	}
	/**
	 * @param args
	 */
	public void run() {		
						//TODO move main method into its own class
						MainFrame mainF= new MainFrame("Duct Tape & WD-40");
						mainF.setSize(Main.screenSize.width/2,Main.screenSize.height*2/3);//sets the main window to half of the screen size
						mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit program when main window closess
						mainF.setLayout(new GridLayout(1,2));//sets layout to a grid with two columns
						//use GridLayout to place controls on right side of canvas later						
						//then add to main frame
						mainF.setVisible(true);
						mainF.initialize();												
	
	}
				
}

