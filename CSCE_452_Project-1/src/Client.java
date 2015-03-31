
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author jacobstone
 */
import java.io.*;
import java.net.*;

import sun.misc.Queue;
public class Client {
	
	private RobotArm arm;
	private Socket socket;
	private BufferedReader 	in;
	private PrintWriter 	out;
	
	public Client(String hostName, int port) {
	try{
		System.out.println("Connecting to '" + hostName + "' on port '" + port + "'");
		socket = new Socket(Inet4Address.getByName(hostName), port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		
	}
	catch(IOException e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	}	
	
	public void SendParams(float t1,float t2, float t3,Boolean P){
			//construct param string
			String m=Float.toString(t1)+","+Float.toString(t2)+","+Float.toString(t3)+",";
			if (P)
				m=m+"T";
			else
				m=m+"F";
			out.println(m);
			
	}
	public void SendParams(int y,int x,Boolean P){
		//construct param string
		String m=Integer.toString(y)+","+Integer.toString(x)+",";
		if (P)
			m=m+"T";
		else
			m=m+"F";
		out.println(m);//send message to server
}
	public void close() {
		if (socket!=null && !socket.isClosed())
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

