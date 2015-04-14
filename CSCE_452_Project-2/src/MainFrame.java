import javax.swing.*;
import java.awt.*;

/**
 * Created by Alan on 4/13/2015.
 */
public class MainFrame extends JFrame implements Runnable {
    private JButton dummyControls;
    private Environment environment;
    private LayoutHelper lHelper;
    MainFrame(String s){
        super(s);
        this.setMinimumSize(Environment.MIN_SIZE);//sets the main window to max screen size
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);//sets the window to maximized state
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit program when main window closess
        this.setLayout(new GridBagLayout());//sets layout to a grid with two columns
        lHelper= new LayoutHelper();
        initComponents();
    }

    private void initComponents() {
        environment = new Environment();
        environment.addRobot(new Point(100, 150));
        dummyControls = new JButton();

        lHelper.setFill(LayoutHelper.BOTH);

        lHelper.setPosition(0, 0);
        lHelper.setSize(2, 1);
        lHelper.setWeights(0, 0);//tells layout to not make component larger when space is available
        this.add(environment, lHelper.getConstraints());

        lHelper.setPosition(2, 0);
        lHelper.setSize(1, 1);
        lHelper.setWeights(1,1);//tells layout to make component larger when space is available
        this.add(dummyControls,lHelper.getConstraints());
    }

    public void run(){
        MainFrame mainF= new MainFrame("Duct Tape & WD-40");
        //use GridLayout to place controls on right side of canvas later
        //then add to main frame
        mainF.setVisible(true);
    }

}
