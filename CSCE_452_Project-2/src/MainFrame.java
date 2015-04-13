import javax.swing.*;
import java.awt.*;

/**
 * Created by Alan on 4/13/2015.
 */
public class MainFrame extends JFrame implements Runnable {

    MainFrame(String s){
        super(s);
    }


    public void run(){
        MainFrame mainF= new MainFrame("Duct Tape & WD-40");
        mainF.setSize(Main.screenSize.width*2/3,Main.screenSize.height*2/3);//sets the main window to half of the screen size
        mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit program when main window closess
        mainF.setLayout(new GridLayout(1,2));//sets layout to a grid with two columns
        //use GridLayout to place controls on right side of canvas later
        //then add to main frame
        mainF.setVisible(true);
    }

}
