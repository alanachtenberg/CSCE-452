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
        private JButton Block1;
        private JButton Block2;
        private JButton Block3;
        private JButton Remove_Block;
        private JButton clear_all;
        private JButton Remove_Block1,Remove_Block2, Remove_Block3;
        private JTextField Block1_x_field, Block1_y_field;
        private JTextField Block2_x_field, Block2_y_field;
        private JTextField Block3_x_field, Block3_y_field;
        private JLabel Block1_x_label, Block1_y_label;
        private JLabel Block2_x_label, Block2_y_label;
        private JLabel Block3_x_label, Block3_y_label;
        private int block1_x_int, block1_y_int, block2_x_int, block2_y_int, block3_x_int, block3_y_int;

        ObstacleControl(){
            this.setLayout(new GridBagLayout());
            initComponents();
            layoutComponents();
        }

        private void initComponents(){
            Block1=new JButton("Add Block 1");
            Block2=new JButton("Add Block 2");
            Block3=new JButton("Add Block 3");

            //Remove_Block1=new JButton("Remove Block 1");
            //Remove_Block2=new JButton("Remove Block 2");
            //Remove_Block3=new JButton("Remove Block 3");
            Remove_Block=new JButton("Remove Last Block");
            clear_all=new JButton("Clear all Blocks");

            Block1_x_field=new JTextField("0");
            Block1_y_field=new JTextField("0");
            Block2_x_field=new JTextField("0");
            Block2_y_field=new JTextField("0");
            Block3_x_field=new JTextField("0");
            Block3_y_field=new JTextField("0");

            Block1_x_label=new JLabel("Block 1 X:");
            Block1_y_label=new JLabel("Block 1 Y:");
            Block2_x_label=new JLabel("Block 2 X:");
            Block2_y_label=new JLabel("Block 2 Y:");
            Block3_x_label=new JLabel("Block 3 X:");
            Block3_y_label=new JLabel("Block 3 Y:");

            Block1_x_label.setHorizontalAlignment(JLabel.RIGHT);
            Block1_y_label.setHorizontalAlignment(JLabel.RIGHT);
            Block2_x_label.setHorizontalAlignment(JLabel.RIGHT);
            Block2_y_label.setHorizontalAlignment(JLabel.RIGHT);
            Block3_x_label.setHorizontalAlignment(JLabel.RIGHT);
            Block3_y_label.setHorizontalAlignment(JLabel.RIGHT);

            Block1.addActionListener(buttonListener);
            Block2.addActionListener(buttonListener);
            Block3.addActionListener(buttonListener);
            Remove_Block.addActionListener(buttonListener);
            //Remove_Block1.addActionListener(buttonListener);
            //Remove_Block2.addActionListener(buttonListener);
            //Remove_Block3.addActionListener(buttonListener);
            clear_all.addActionListener(buttonListener);
        }
        private void layoutComponents(){
            lHelper.setFill(LayoutHelper.HORIZONTAL);
            lHelper.setAnchor(LayoutHelper.CENTER);

            //Row 0
            lHelper.setPosition(0,0);
            lHelper.setSize(1,1);
            this.add(Block1,lHelper.getConstraints());
            lHelper.setPosition(1,0);
            this.add(Block1_x_label,lHelper.getConstraints());
            lHelper.setPosition(2,0);
            this.add(Block1_x_field,lHelper.getConstraints());

            //Row 1
            /*lHelper.setPosition(0,1);
            this.add(Remove_Block1,lHelper.getConstraints());*/
            lHelper.setPosition(1,1);
            this.add(Block1_y_label,lHelper.getConstraints());
            lHelper.setPosition(2,1);
            this.add(Block1_y_field,lHelper.getConstraints());

            //Row 2
            lHelper.setPosition(0,2);
            this.add(Block2,lHelper.getConstraints());
            lHelper.setPosition(1,2);
            this.add(Block2_x_label,lHelper.getConstraints());
            lHelper.setPosition(2,2);
            this.add(Block2_x_field,lHelper.getConstraints());

            //Row 3
            /*lHelper.setPosition(0,3);
            this.add(Remove_Block2,lHelper.getConstraints());*/
            lHelper.setPosition(1,3);
            this.add(Block2_y_label,lHelper.getConstraints());
            lHelper.setPosition(2,3);
            this.add(Block2_y_field,lHelper.getConstraints());

            //Row 4
            lHelper.setPosition(0,4);
            this.add(Block3,lHelper.getConstraints());
            lHelper.setPosition(1,4);
            this.add(Block3_x_label,lHelper.getConstraints());
            lHelper.setPosition(2,4);
            this.add(Block3_x_field,lHelper.getConstraints());

            //Row 5
            /*lHelper.setPosition(0,5);
            this.add(Remove_Block3,lHelper.getConstraints());*/
            lHelper.setPosition(1,5);
            this.add(Block3_y_label,lHelper.getConstraints());
            lHelper.setPosition(2,5);
            this.add(Block3_y_field,lHelper.getConstraints());

            //Row 6
            lHelper.setPosition(0,6);
            this.add(Remove_Block,lHelper.getConstraints());
            lHelper.setPosition(2,6);
            this.add(clear_all,lHelper.getConstraints());

        }

        private ActionListener buttonListener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO do something with environment
                Object src = e.getSource();
                if(src == Block1){
                    String block1_x = Block1_x_field.getText();
                    String block1_y = Block1_y_field.getText();

                    block1_x_int = Integer.parseInt(block1_x);
                    block1_y_int = Integer.parseInt(block1_y);

                    environment.addObstacle(new Obstacle(new Point(block1_x_int,block1_y_int),new Dimension(200,200)));
                }
                else if(src == Block2) {
                    String block2_x = Block2_x_field.getText();
                    String block2_y = Block2_y_field.getText();

                    block2_x_int = Integer.parseInt(block2_x);
                    block2_y_int = Integer.parseInt(block2_y);

                    environment.addObstacle(new Obstacle(new Point(block2_x_int,block2_y_int),new Dimension(150,150)));
                }
                else if(src == Block3) {
                    String block3_x = Block3_x_field.getText();
                    String block3_y = Block3_y_field.getText();

                    block3_x_int = Integer.parseInt(block3_x);
                    block3_y_int = Integer.parseInt(block3_y);

                    environment.addObstacle(new Obstacle(new Point(block3_x_int,block3_y_int),new Dimension(100,100)));
                }
                else if(src == Remove_Block) {
                    environment.removeLastObstacle();
                }
                else if(src == clear_all) {
                    environment.clearObstacles();
                }
                //environment.addObstacle(new Obstacle(new Point(50,50),new Dimension(100,100)));//simple test
            }
        };
    }

    //interaction with path display
    private class PathControl extends JPanel{
        private JButton FindPath;
        private JTextField start_x_field, start_y_field;
        private JTextField end_x_field, end_y_field;
        private JLabel start_x_label, start_y_label;
        private JLabel end_x_label, end_y_label;
        private int start_x_int, start_y_int, end_x_int, end_y_int;

        PathControl(){
            this.setLayout(new GridBagLayout());
            initComponents();
            layoutComponents();
        }

        private void initComponents(){
            FindPath=new JButton("Find Path");
            FindPath.addActionListener(buttonListener);

            start_x_field=new JTextField("0");
            start_y_field=new JTextField("0");
            end_x_field=new JTextField("0");
            end_y_field=new JTextField("0");

            start_x_label=new JLabel("Start X:");
            start_y_label=new JLabel("Start Y:");
            end_x_label=new JLabel("End X:");
            end_y_label=new JLabel("End Y:");

            start_x_label.setHorizontalAlignment(JLabel.RIGHT);
            start_y_label.setHorizontalAlignment(JLabel.RIGHT);
            end_x_label.setHorizontalAlignment(JLabel.RIGHT);
            end_y_label.setHorizontalAlignment(JLabel.RIGHT);
        }
        private void layoutComponents(){
            lHelper.setFill(LayoutHelper.HORIZONTAL);
            lHelper.setAnchor(LayoutHelper.CENTER);
            //Row 0
            lHelper.setPosition(0,0);
            lHelper.setSize(1, 1);
            this.add(start_x_label, lHelper.getConstraints());
            lHelper.setPosition(1, 0);
            this.add(start_x_field, lHelper.getConstraints());
            lHelper.setPosition(2, 0);
            this.add(end_x_label, lHelper.getConstraints());
            lHelper.setPosition(3, 0);
            this.add(end_x_field,lHelper.getConstraints());

            //Row 1
            lHelper.setPosition(0,1);
            this.add(start_y_label, lHelper.getConstraints());
            lHelper.setPosition(1, 1);
            this.add(start_y_field, lHelper.getConstraints());
            lHelper.setPosition(2, 1);
            this.add(end_y_label, lHelper.getConstraints());
            lHelper.setPosition(3, 1);
            this.add(end_y_field,lHelper.getConstraints());

            //Row 2
            lHelper.setPosition(1,2);
            lHelper.setSize(2,1);
            this.add(FindPath,lHelper.getConstraints());
        }

        private ActionListener buttonListener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO do something with environment
                Object src = e.getSource();
                if(src == FindPath) {
//<<<<<<< HEAD
                    String start_x = start_x_field.getText();
                    String start_y = start_y_field.getText();
                    String end_x = end_x_field.getText();
                    String end_y = end_y_field.getText();

                    start_x_int = Integer.parseInt(start_x);
                    start_y_int = Integer.parseInt(start_y);
                    end_x_int = Integer.parseInt(end_x);
                    end_y_int = Integer.parseInt(end_y);

                    //environment.setPath(new Point(start_x_int,start_y_int),new Point(end_x_int,end_y_int));

//=======
                	Point start = new Point(start_x_int, start_y_int);
                    Point end = new Point(end_x_int, end_y_int);
                	if(environment.setPath(start, end))
                    {
                    	//start and end points have already been set in setPath().
                        environment.getPaths();
                    }
                    else
                    {
                    	System.out.println("ERROR: Invalid start or end point. Check locations for collisions");
                    }

//>>>>>>> origin/master
                }
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
