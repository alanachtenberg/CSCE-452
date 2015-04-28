import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Alan on 4/13/2015.
 */
public class MainFrame extends JFrame implements Runnable {
    private Environment environment;
    private Controls control;

    MainFrame(String s){
        super(s);
        this.setMinimumSize(new Dimension(1000,500));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);//sets the window to maximized state
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit program when main window closess
        this.setLayout(new GridLayout(1,2));//sets layout to a grid with two columns
        initComponents();
    }

    private void initComponents() {
        environment = new Environment();
        control = new Controls(environment);
        environment.addMouseListener(environmentListener);//add listener after controls are constructed, since listener access controls
        this.add(environment);
        this.add(control);
    }

    public void run(){
        MainFrame mainF= new MainFrame("Duct Tape & WD-40");
        //use GridLayout to place controls on right side of canvas later
        //then add to main frame
        mainF.setVisible(true);
    }

    private MouseListener environmentListener= new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Point p=e.getPoint();
            control.setLocation(p.x,p.y);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //dont need these methods but they must be defined
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

}