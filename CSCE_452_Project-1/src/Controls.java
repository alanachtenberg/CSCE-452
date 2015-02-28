import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.*;

public class Controls extends JPanel implements ActionListener{

	/**
	 * Implements basic controls of PaintBot
	 */
	private static final long serialVersionUID = 1L;
	
	boolean testing = false;
	
	private RobotArm arm;
	
	private static final int NUMLINKS = 3;
	
	//Angles of each robot arm link. 0--Link1; 1--Link2; 2--Link3
	private float theta[] = new float[NUMLINKS];
	
	//Choose one of 3 joints on Robot
	private JRadioButton link1Base;
	private JRadioButton link2Base;
	private JRadioButton link3Base;
	
	//ButtonGroup to hold link buttons
	private ButtonGroup joints;
	
	//Each Link can either move 1 degree clockwise or counterclockwise
	private JButton CW;
	private JButton CCW;
	
	//Press to paint circle
	private JButton paintCircle;
	
	public Controls(RobotArm _arm){
		arm = _arm;
		//Set initial angles to 0s
		theta[0] = 0;
		theta[1] = 0;
		theta[2] = 0;
		
		link1Base = new JRadioButton("Link 1");
		link2Base = new JRadioButton("Link 2");
		link3Base = new JRadioButton("Link 3");
		
		//Group links together so only one joint can be manipulated at a time
		joints = new ButtonGroup();
		joints.add(link1Base);
		joints.add(link2Base);
		joints.add(link3Base);
		
		CW = new JButton("Clockwise");
		CCW = new JButton("Counterclockwise");
		
		paintCircle = new JButton("Paint Circle");
		
		//Add actionListners for CW, CCW, & paintCircle buttons
		CW.addActionListener(this);
		CCW.addActionListener(this);
		paintCircle.addActionListener(this);
		
		//Display on screen
		//JPanel panel = new JPanel();
	    this.setLayout(new GridLayout(2,2));
		this.add(CW);
		this.add(CCW);
		this.add(paintCircle);
		this.add(link1Base);
		this.add(link2Base);
		this.add(link3Base);
	}
	
	/**
	 * Helper Function
	 * Determines which joint has been selected by looping through buttons in ButtonGroup
	 * Input:	ButtonGroup
	 * Output: String text of button
	 */
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
	
	/**
	 * Implement actionListner
	 * paintCircle --- paints circle
	 * cw --- move selected link clockwise
	 * ccw --- move selected link counterclockwise
	 */
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == paintCircle){
			//Call function to paint circle at whatever location
			arm.paintPoint();
			arm.repaint();
		}
		else if(arg0.getSource() == CW){
			if(getSelectedButtonText(joints) == "Link 1"){
				//Move joint 1 clockwise one degree
				arm.setTheta(theta[0]-1, theta[1], theta[2]);
				theta[0]--;
				arm.repaint();
				if(testing)
					System.out.println("CW Link1");
			}
			else if(getSelectedButtonText(joints) == "Link 2"){
				//Move joint 2 clockwise one degree
				arm.setTheta(theta[0], theta[1]-1, theta[2]);
				theta[1]--;
				arm.repaint();
				if(testing)
					System.out.println("CW Link2");
			}
			else if(getSelectedButtonText(joints) == "Link 3"){
				//Move joint 3 clockwise one degree
				arm.setTheta(theta[0], theta[1], theta[2]-1);
				theta[2]--;
				arm.repaint();
				if(testing)
					System.out.println("CW Link3");
			}
			else{
				//Don't do anything
				if(testing)
					System.out.println("CW No link selected");
			}
		}
		else if(arg0.getSource() == CCW){
			if(getSelectedButtonText(joints) == "Link 1"){
				//Move joint 1 counterclockwise one degree
				arm.setTheta(theta[0]+1, theta[1], theta[2]);
				theta[0]++;
				arm.repaint();
				if(testing)
					System.out.println("CCW Link1");
			}
			else if(getSelectedButtonText(joints) == "Link 2"){
				//Move joint 2 counterclockwise one degree
				arm.setTheta(theta[0], theta[1]+1, theta[2]);
				theta[1]++;
				arm.repaint();
				if(testing)
					System.out.println("CCW Link2");
			}
			else if(getSelectedButtonText(joints) == "Link 3"){
				//Move joint 3 counterclockwise one degree
				arm.setTheta(theta[0], theta[1], theta[2]+1);
				theta[2]++;
				arm.repaint();
				if(testing)
					System.out.println("CCW Link3");
			}
			else{
				//Don't do anything
				if(testing)
					System.out.println("CCW no link selected");
			}
		}
		
	}
}
