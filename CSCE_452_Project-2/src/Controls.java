import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.swing.*;

/**
 * Created by Maurice on 4/14/2015.
 */




public class Controls extends JPanel implements ActionListener {

    //used to draw horizontal line between other components on grid
    //line is centered vertically
    private class Line extends JPanel{ //extend Jpanel so that it resizes itself easily
        @Override
        public void paint(Graphics g){
            g.drawLine(this.getX(),this.getHeight()/2,this.getX()+this.getWidth(),this.getHeight()/2);
        }
    }


    private JTextField k11, k12, k21, k22;
    private JLabel k_11_label, k_12_label, k_21_label, k_22_label;
    private JTextField startVehicleX, startVehicleY, startVehicleAngle;
    private JLabel v_start_x_label, v_start_y_label, v_start_angle_label;
    private JTextField createLightX, createLightY, lightIntensity;
    private JLabel l_start_x_label, l_start_y_label, l_intensity_label;
    private JButton createVehicle, eraseVehicle;
    private JButton createLight, eraseLight;
    private JButton run, pause;
    private GridBagLayout grid;
    private double vx, vy, vangle;
    private double lx, ly, intensity;
    private LayoutHelper lHelper;

    private Environment environment;//make things happen
    public Controls(Environment environment) {
        this.environment=environment;
        createVehicle = new JButton("Create Vehicle");
        eraseVehicle = new JButton("Erase Vehicle");

        createLight = new JButton("Create Light");
        eraseLight = new JButton("Erase Light");

        run = new JButton("Run");
        pause = new JButton("Pause");

        k_11_label = new JLabel("K11");
        k_12_label = new JLabel("K12");
        k_21_label = new JLabel("K21");
        k_22_label = new JLabel("K22");

        k_11_label.setHorizontalAlignment(JLabel.RIGHT);
        k_12_label.setHorizontalAlignment(JLabel.RIGHT);
        k_21_label.setHorizontalAlignment(JLabel.RIGHT);
        k_22_label.setHorizontalAlignment(JLabel.RIGHT);

        v_start_x_label = new JLabel("Car Start X");
        v_start_y_label = new JLabel("Car Start Y");
        v_start_angle_label = new JLabel("Angle");

        v_start_x_label.setHorizontalAlignment(JLabel.RIGHT);
        v_start_y_label.setHorizontalAlignment(JLabel.RIGHT);
        v_start_angle_label.setHorizontalAlignment(JLabel.RIGHT);

        l_start_x_label = new JLabel("Light X");
        l_start_y_label = new JLabel("Light Y");
        l_intensity_label = new JLabel("Intensity");

        l_start_x_label.setHorizontalAlignment(JLabel.RIGHT);
        l_start_y_label.setHorizontalAlignment(JLabel.RIGHT);
        l_intensity_label.setHorizontalAlignment(JLabel.RIGHT);

        startVehicleX = new JTextField("0");
        startVehicleY = new JTextField("0");
        startVehicleAngle = new JTextField("0");

        k11 = new JTextField("0");
        k12 = new JTextField("0");
        k21 = new JTextField("0");
        k22 = new JTextField("0");

        createLightX = new JTextField("0");
        createLightY = new JTextField("0");
        lightIntensity = new JTextField("0");

        createVehicle.addActionListener(this);
        eraseVehicle.addActionListener(this);

        createLight.addActionListener(this);
        eraseVehicle.addActionListener(this);

        run.addActionListener(this);
        pause.addActionListener(this);

        grid = new GridBagLayout();
        this.setLayout(grid);
        //GridBagConstraints c = new GridBagConstraints();
		lHelper = new LayoutHelper();
		lHelper.setFill(LayoutHelper.HORIZONTAL);
        lHelper.setAnchor(LayoutHelper.CENTER);//anchors component inside the grid cell, note does not align items within component, such as text in a label

        //First row
		lHelper.setPosition(0, 0);
        lHelper.setSize(2, 1);
        lHelper.setWeights(0,0);//dont resize button
        this.add(createVehicle,lHelper.getConstraints());

        lHelper.setPosition(2,0);
        lHelper.setWeights(0, 0);//dont resize label
        this.add(v_start_x_label,lHelper.getConstraints());

        lHelper.setPosition(4, 0);
        lHelper.setSize(1, 1);
        lHelper.setWeights(1,1);//lets give available space to the text box, makes it easier to input
        this.add(startVehicleX,lHelper.getConstraints());

        //Second row
        lHelper.setPosition(0, 1);
        lHelper.setSize(2, 1);

        this.add(eraseVehicle,lHelper.getConstraints());

        lHelper.setPosition(2, 1);
        this.add(v_start_y_label,lHelper.getConstraints());

        lHelper.setPosition(4, 1);
        lHelper.setSize(1, 1);
        this.add(startVehicleY, lHelper.getConstraints());

        //Third row
        lHelper.setPosition(3, 2);
        lHelper.setSize(1,1);
		lHelper.setWeights(0,0);
        this.add(v_start_angle_label,lHelper.getConstraints());

        lHelper.setPosition(4, 2);
		lHelper.setSize(1,1);
		lHelper.setWeights(1,0);
        this.add(startVehicleAngle,lHelper.getConstraints());

        //Fourth row skipped for space

		//Fifth row
		lHelper.setPosition(1, 4);
		lHelper.setSize(1,1);
		lHelper.setWeights(0,0);
		this.add(k_11_label,lHelper.getConstraints());
		
		lHelper.setPosition(2,4);
		lHelper.setSize(1,1);
		lHelper.setWeights(1,0);
		this.add(k11,lHelper.getConstraints());
		
		lHelper.setPosition(3, 4);
		lHelper.setSize(1,1);
		lHelper.setWeights(0,0);
		this.add(k_12_label,lHelper.getConstraints());
		
		lHelper.setPosition(4,4);
		lHelper.setSize(1,1);
		lHelper.setWeights(1,0);
		this.add(k12,lHelper.getConstraints());

		
		//Sixth row
		lHelper.setPosition(1, 5);
		lHelper.setSize(1,1);
		lHelper.setWeights(0,0);
		this.add(k_21_label,lHelper.getConstraints());
		
		lHelper.setPosition(2, 5);
		lHelper.setSize(1,1);
		lHelper.setWeights(1,0);
		this.add(k21,lHelper.getConstraints());
		
		lHelper.setPosition(3,5);
		lHelper.setSize(1,1);
		lHelper.setWeights(0,0);
		this.add(k_22_label,lHelper.getConstraints());
		
		lHelper.setPosition(4,5);
		lHelper.setSize(1,1);
		lHelper.setWeights(1,0);
		this.add(k22,lHelper.getConstraints());

        //Added line border to fill space and seperate controls
        lHelper.setPosition(0,6);
        lHelper.setSize(5,1);
        this.add(new Line(),lHelper.getConstraints());


		//Seventh row
        lHelper.setPosition(0, 7);
		lHelper.setSize(2,1);
		lHelper.setWeights(0,0);
        this.add(createLight,lHelper.getConstraints());

        lHelper.setPosition(3,7);
        lHelper.setSize(1, 1);
        this.add(l_start_x_label,lHelper.getConstraints());

        lHelper.setPosition(4,7);
        lHelper.setSize(1,1);
		lHelper.setWeights(1,0);
        this.add(createLightX,lHelper.getConstraints());


        //Eighth row
        lHelper.setPosition(0, 8);
        lHelper.setSize(2, 1);
		lHelper.setWeights(0,0);
        this.add(eraseLight,lHelper.getConstraints());

        lHelper.setPosition(3, 8);
        lHelper.setSize(1,1);
        lHelper.setWeights(0,0);
        this.add(l_start_y_label,lHelper.getConstraints());

        lHelper.setPosition(4, 8);
        lHelper.setSize(1,1);
        lHelper.setWeights(1,0);
        this.add(createLightY,lHelper.getConstraints());



        //Ninth row
        lHelper.setPosition(3, 9);
        lHelper.setSize(1,1);
		lHelper.setWeights(0,0);
        this.add(l_intensity_label,lHelper.getConstraints());

        lHelper.setPosition(4, 9);
		lHelper.setSize(1,1);
		lHelper.setWeights(1,0);
        this.add(lightIntensity,lHelper.getConstraints());

        //Added line border to fill space and seperate controls
        lHelper.setPosition(0,10);
        lHelper.setSize(5,1);
        this.add(new Line(),lHelper.getConstraints());

        //Tenth row
        lHelper.setPosition(0, 11);
        lHelper.setSize(2,1);
        lHelper.setWeights(1,0);
        this.add(run,lHelper.getConstraints());
		
		lHelper.setPosition(2,11);
        lHelper.setSize(2,1);
		this.add(pause,lHelper.getConstraints());

    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }
}
