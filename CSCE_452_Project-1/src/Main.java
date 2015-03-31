import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;


public class Main {
	public static Boolean CLIENT_MODE=false;//default values
	public static Boolean DELAY_ENABLED=false;
	public static int PORT_NUM=1234;
	public static String HOST="localhost";
	public static Client CLIENT;//only used in client mode
	
	public static Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//gets system screen size;

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new ServerPopUp());
	}
}
