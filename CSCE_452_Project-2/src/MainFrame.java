import javax.swing.*;
import java.awt.*;

/**
 * Created by Alan on 4/13/2015.
 */
public class MainFrame extends JFrame implements Runnable {
    private JButton dummyControls;
    private Environment environment;
    private LayoutHelper lHelper;
    private Controls control;

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
        environment.startMovement();
        environment.addRobot(new Point(44/2, 120*5/6));
        dummyControls = new JButton();

        control = new Controls();

        lHelper.setFill(LayoutHelper.BOTH);
        lHelper.setAnchor(LayoutHelper.TOP);

        lHelper.setPosition(0, 0);
        lHelper.setSize(2, 1);
        lHelper.setWeights(1, 1);//tells layout to make component larger when space is available
        //1 is the scale relative to other component weights, for example
        //component A with weight 1 and a component B with wight 2, A would get half the available space that 2 would get
        this.add(environment, lHelper.getConstraints());

        lHelper.setPosition(2, 0);
        lHelper.setSize(1, 1);
        lHelper.setWeights(1,1);//tells layout to make component larger when space is available
        lHelper.setFill(LayoutHelper.HORIZONTAL);
        this.add(control,lHelper.getConstraints());

    }

    public void run(){
        MainFrame mainF= new MainFrame("Duct Tape & WD-40");
        //use GridLayout to place controls on right side of canvas later
        //then add to main frame
        mainF.setVisible(true);

    }

}
