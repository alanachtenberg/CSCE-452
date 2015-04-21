import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alan on 4/21/2015.
 */
public class Controls extends JPanel {
    private static LayoutHelper lHelper= new LayoutHelper();

    //modifies obstacles
    private class ObstacleControl extends JPanel{
        private JButton Button1;

        ObstacleControl(){
            this.setLayout(new GridBagLayout());
            initComponents();
            layoutComponents();
        }

        private void initComponents(){
            Button1=new JButton("Dummy");
            Button1.addActionListener(buttonListener);
        }
        private void layoutComponents(){
            lHelper.setPosition(0,0);
            lHelper.setSize(1,1);
            this.add(Button1,lHelper.getConstraints());
        }

        private ActionListener buttonListener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO do something with environment
                environment.addObstacle(new Obstacle(new Point(50,50),new Dimension(100,100)));//simple test
            }
        };
    }

    //interaction with path display
    private class PathControl extends JPanel{
        private JButton Button1;

        PathControl(){
            this.setLayout(new GridBagLayout());
            initComponents();
            layoutComponents();
        }

        private void initComponents(){
            Button1=new JButton("Dummy");
            Button1.addActionListener(buttonListener);
        }
        private void layoutComponents(){
            lHelper.setPosition(0,0);
            lHelper.setSize(1,1);
            this.add(Button1,lHelper.getConstraints());
        }

        private ActionListener buttonListener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO do something with environment
            }
        };
    }

    private Environment environment;//for access to modifying environment

    public Controls(Environment environment){
        super();
        this.environment=environment;
        this.setLayout(new GridLayout(2,1));//vertical 2 cell layout
        this.add(new ObstacleControl());
        this.add(new PathControl());
    }
}
