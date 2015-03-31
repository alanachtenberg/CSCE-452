import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;



public class ClientThread implements Runnable{
	
	private class QueueItem{
		public String mesg;
		public long time;
		QueueItem(String m, long t){
			mesg=m;
			time=t;
		}
	};
	
	private class Consumer implements Runnable{
		public void run(){
				String[] args;
				while(true){
					if(!buffer.isEmpty()){ //buffer not empty	
						QueueItem item;
						synchronized (buffer) {							
							item=buffer.poll();
						}
						long currTime= System.currentTimeMillis();
						while(currTime-item.time<2000 && Main.DELAY_ENABLED)//less than two seconds
							currTime=System.currentTimeMillis();				
					
						args=item.mesg.split(",");
						int numParams=args.length;
		        	
						if (numParams==1){
							if (args[0].equals("DISABLE DELAY"))
								Main.DELAY_ENABLED=false;
							else if (args[0].equals("ENABLE DELAY"))
								Main.DELAY_ENABLED=true;
							else if (Integer.parseInt(args[0])>0)
								arm.setPainterColor(new Color(Integer.parseInt(args[0])));
						}
						else if(numParams==3){//world control
			        		arm.movePainterTo(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			        		if (args[2].equals("T"))
			        			arm.paintPoint();
			        		out.println("OK");//respond to client
			        	 }
			        	 else if(numParams==4){//joint control
			        		 arm.setTheta(Float.parseFloat(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]));
			        		 if (args[3].equals("T"))
			         			arm.paintPoint();
			        		 else
				        		 arm.repaint();
			        		 out.println("OK");//respond to client
			        	 }
			        	 else
			        		 assert(false);
					}	
				}
		}
	}
			
	
	private Socket sock;
	private BufferedReader in;
	private PrintWriter out;
	private String message;
	
	private RobotArm arm;
	
	private ConcurrentLinkedQueue<QueueItem> buffer;
	
	
	
	ClientThread(Socket client, RobotArm robotArm){
		sock=client;	
		arm=robotArm;
		buffer= new ConcurrentLinkedQueue<QueueItem>();
	}
	
	
	public void run(){
		try{
		
		 InputStreamReader streamReader=new InputStreamReader(sock.getInputStream());//clear input stream
         in = new BufferedReader(streamReader);
         out = new PrintWriter(sock.getOutputStream(), true);
         new Thread(new Consumer()).start();;
         
         while(true){
        	 message=in.readLine();//blocking call
        	 if(message==null && sock!=null){
        		 sock.close();//message is equal to null, assume that sock is closed on the client side
        	 	 break;//and close the sock on the server side
        	 	 //finally break to end execution of client thread
        	 }
        	 long time=System.currentTimeMillis();//current time in milliseconds
        	 synchronized (buffer) {		
        		 buffer.add(new QueueItem(message, time));
        	 }
         }
      
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
