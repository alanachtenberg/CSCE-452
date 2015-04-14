import javax.swing.*;
import java.awt.*;

/**
 * Created by Alan on 4/13/2015.
 */


public class Main {

    public static Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//gets system screen size;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MainFrame("Duct tape & WD-40"));
    }
}
