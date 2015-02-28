import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.*;

public class Controls extends JFrame implements ActionListener{

	/**
	 * Implements basic controls of PaintBot
	 */
	private static final long serialVersionUID = 1L;
	
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
	
	public Controls(){
		link1Base = new JRadioButton("Link 1");
		link2Base = new JRadioButton("Link 2");
		link3Base = new JRadioButton("Link 3");
		
		//Group links together so only one joint can be manipulated at a time
		joints = new ButtonGroup();
		joints.add(link1Base);
		joints.add(link2Base);
		joints.add(link3Base);
		
		CW = new JButton("Clockwise Rotation");
		CCW = new JButton("Counterclockwise Rotation");
		
		paintCircle = new JButton("Paint Circle");
		
		//Add actionListners for CW, CCW, & paintCircle buttons
		CW.addActionListener(this);
		CCW.addActionListener(this);
		paintCircle.addActionListener(this);
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == paintCircle){
			//Call function to paint circle at whatever location
		}
		else if(arg0.getSource() == CW){
			if(getSelectedButtonText(joints) == "Link 1"){
				//Move joint 1 clockwise one degree
			}
			else if(getSelectedButtonText(joints) == "Link 2"){
				//Move joint 2 clockwise one degree
			}
			else if(getSelectedButtonText(joints) == "Link 3"){
				//Move joint 3 clockwise one degree
			}
			else{
				//Don't do anything
			}
		}
		else if(arg0.getSource() == CCW){
			if(getSelectedButtonText(joints) == "Link 1"){
				//Move joint 1 counterclockwise one degree
			}
			else if(getSelectedButtonText(joints) == "Link 2"){
				//Move joint 2 counterclockwise one degree
			}
			else if(getSelectedButtonText(joints) == "Link 3"){
				//Move joint 3 counterclockwise one degree
			}
			else{
				//Don't do anything
			}
		}
		
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
}
