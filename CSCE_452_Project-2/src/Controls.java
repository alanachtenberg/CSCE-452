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
    private JTextField createLightX, createLightY, vehicle_size_tfield;
    private JLabel l_start_x_label, l_start_y_label, vehicle_size_label;
    private JButton createVehicle, eraseVehicle;
    private JButton createLight, eraseLight;
    private JButton run, pause, clear;
    private GridBagLayout grid;
    private int vx, vy, vangle, vsize;
    private int lx, ly;
    private double v_k11_double, v_k12_double, v_k21_double, v_k22_double;
    private LayoutHelper lHelper;
    private LightSource lightSource;
    //private Environment environment;



    private Environment environment;//make things happen
    public Controls(Environment environment) {
        this.environment=environment;
        createVehicle = new JButton("Create Vehicle");
        eraseVehicle = new JButton("Erase Last Vehicle");

        createLight = new JButton("Create Light");
        eraseLight = new JButton("Erase Last Light");

        run = new JButton("Run");
        pause = new JButton("Pause");
        clear = new JButton("Clear");

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
        vehicle_size_label = new JLabel("Vehicle Size");

        l_start_x_label.setHorizontalAlignment(JLabel.RIGHT);
        l_start_y_label.setHorizontalAlignment(JLabel.RIGHT);
        vehicle_size_label.setHorizontalAlignment(JLabel.RIGHT);

        startVehicleX = new JTextField("0");
        startVehicleY = new JTextField("0");
        startVehicleAngle = new JTextField("0");

        k11 = new JTextField("1");
        k12 = new JTextField("0");
        k21 = new JTextField("0");
        k22 = new JTextField("1");

        createLightX = new JTextField("0");
        createLightY = new JTextField("0");
        vehicle_size_tfield = new JTextField("1");

        createVehicle.addActionListener(this);
        eraseVehicle.addActionListener(this);

        createLight.addActionListener(lightListener);
        eraseLight.addActionListener(lightListener);

        run.addActionListener(startListener);
        pause.addActionListener(startListener);
        clear.addActionListener(startListener);

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

        lHelper.setPosition(3, 6);
        lHelper.setSize(1,1);
        lHelper.setWeights(0,0);
        this.add(vehicle_size_label,lHelper.getConstraints());

        lHelper.setPosition(4, 6);
        lHelper.setSize(1,1);
        lHelper.setWeights(1,0);
        this.add(vehicle_size_tfield,lHelper.getConstraints());

        //Added line border to fill space and seperate controls
        lHelper.setPosition(0,7);
        lHelper.setSize(5,1);
        this.add(new Line(),lHelper.getConstraints());


		//Seventh row
        lHelper.setPosition(0, 8);
		lHelper.setSize(2,1);
		lHelper.setWeights(0,0);
        this.add(createLight,lHelper.getConstraints());

        lHelper.setPosition(3,8);
        lHelper.setSize(1, 1);
        this.add(l_start_x_label,lHelper.getConstraints());

        lHelper.setPosition(4,8);
        lHelper.setSize(1,1);
		lHelper.setWeights(1,0);
        this.add(createLightX,lHelper.getConstraints());


        //Eighth row
        lHelper.setPosition(0, 9);
        lHelper.setSize(2, 1);
		lHelper.setWeights(0,0);
        this.add(eraseLight,lHelper.getConstraints());

        lHelper.setPosition(3, 9);
        lHelper.setSize(1,1);
        lHelper.setWeights(0,0);
        this.add(l_start_y_label,lHelper.getConstraints());

        lHelper.setPosition(4, 9);
        lHelper.setSize(1,1);
        lHelper.setWeights(1,0);
        this.add(createLightY,lHelper.getConstraints());



        //Ninth row


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

        lHelper.setPosition(4,11);
        lHelper.setSize(2,1);
        this.add(clear,lHelper.getConstraints());

    }
    @Override
    public void actionPerformed(ActionEvent arg0) {     // Car related parameters
        Object src = arg0.getSource();
        if(src == createVehicle)
        {
            String v_x_pos = startVehicleX.getText();
            String v_y_pos = startVehicleY.getText();
            String v_angle_pos = startVehicleAngle.getText();
            String v_k11 = k11.getText();
            String v_k12 = k12.getText();
            String v_k21 = k21.getText();
            String v_k22 = k22.getText();
            String v_size = vehicle_size_tfield.getText();

            vx = Integer.parseInt(v_x_pos);
            vy = Integer.parseInt(v_y_pos);
            vangle = Integer.parseInt(v_angle_pos);
            v_k11_double = Double.parseDouble(v_k11);
            v_k12_double = Double.parseDouble(v_k12);
            v_k21_double = Double.parseDouble(v_k21);
            v_k22_double = Double.parseDouble(v_k22);
            vsize = Integer.parseInt(v_size);
            if(vsize <= 0 || vsize > 100)
            {
                vehicle_size_tfield.setText("Invalid vehicle size");
            }
            else
            {
                environment.addRobot(new Point(vx, vy), v_k11_double, v_k12_double, v_k21_double, v_k22_double);
            }

        }
        else if(src == eraseVehicle)
        {
            environment.removeLastRobot();
        }

    }

    private ActionListener lightListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if(src == createLight)
            {
                String l_x_pos = createLightX.getText();
                String l_y_pos = createLightY.getText();

                lx = Integer.parseInt(l_x_pos);
                ly = Integer.parseInt(l_y_pos);

                environment.addLight(new Point(lx,ly));
            }
            else if(src == eraseLight)
            {
                environment.removeLastLight();
            }
        }
    };

    private ActionListener startListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();

            if(src == run)
            {
                environment.startMovement();
            }
            else if(src == pause)
            {
                environment.stopMovement();
            }
            else if(src == clear)
            {
                environment.clearLights();
                environment.clearRobots();
            }
        }
    };

    public void setLocation(int x, int y) {
        vx = x;
        vy = y;
        lx = x;
        ly = x;
        createLightX.setText(Integer.toString(x));
        createLightY.setText(Integer.toString(y));
        startVehicleX.setText(Integer.toString(x));
        startVehicleY.setText(Integer.toString(y));

    }


}
