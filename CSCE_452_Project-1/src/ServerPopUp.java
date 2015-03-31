import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerPopUp extends JFrame implements Runnable{
	
	private GridBagLayout grid;
	private JRadioButton client, server;
	private ButtonGroup bGroup;
	private JLabel typeLabel;
	private JLabel hostLabel, portLabel;
	private JTextField hostField,portField;
	private JButton start;
	
	public static String hostName,portNumber;
	
	ServerPopUp(){
		super();
		grid=new GridBagLayout();
		this.setLayout(grid);
		
		//RADIO BUTTONS
		client= new JRadioButton("Client");
		server= new JRadioButton("Server");
		bGroup= new ButtonGroup();
		bGroup.add(client);
		bGroup.add(server);
		
		typeLabel= new JLabel("Select Mode");
		hostLabel= new JLabel("Host:");
		portLabel = new JLabel("Port:");
		hostField= new JTextField("localhost");
		portField= new JTextField("1234");
		
		start= new JButton("Start");
		
		start.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				start();	
			}
		});
		
		//LAYOUT CONTROLS
		GridBagConstraints c=new GridBagConstraints();
		c.anchor=GridBagConstraints.WEST;
		c.fill= GridBagConstraints.BOTH;
		setGridPos(c,0,0,2,1);
		this.add(typeLabel,c);
		
		setGridPos(c,0,1,1,1);
		this.add(client,c);
		setGridPos(c,1,1,1,1);
		this.add(server,c);
		
		setGridPos(c,0,2,1,1);
		this.add(hostLabel,c);
		setGridPos(c,1,2,1,1);
		this.add(hostField, c);
		
		setGridPos(c,0,3,1,1);
		this.add(portLabel,c);
		setGridPos(c,1,3,1,1);
		this.add(portField, c);
		
		setGridPos(c,0,4,2,1);
		this.add(start, c);
		
		
		
	}
	
	public void run(){
		ServerPopUp popUP= new ServerPopUp();
		popUP.setMinimumSize(new Dimension(300,200));
		popUP.setLocation(Main.screenSize.width/2-(int)popUP.getSize().getWidth()/2
				,Main.screenSize.height*1/3);
		popUP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit program when main window closess
		//use GridLayout to place controls on right side of canvas later						
		//then add to main frame
		popUP.setVisible(true);	
	}
	
	private void setGridPos(GridBagConstraints c,int x, int y, int w, int h){
	        c.gridx=x;
	        c.gridy=y;
	        c.gridwidth=w;
	        c.gridheight=h;
	}
	
	private void start(){
		if(client.isSelected())
			Main.CLIENT_MODE=true;
		else
			Main.CLIENT_MODE=false;
		Main.PORT_NUM=Integer.parseInt(portField.getText());
		Main.HOST= hostField.getText().toLowerCase();
		
		this.dispose();//get rid of window
		//run program
		SwingUtilities.invokeLater(new MainFrame("Duct Tape & WD-40"));
	}
}
