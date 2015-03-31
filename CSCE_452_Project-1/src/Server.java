import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alan on 3/25/2015.
 */
public class Server implements Runnable{
    private ServerSocket serverSocket;//listening port connections

    private Inet4Address localHost;
    private int portNum;

    private RobotArm arm;
    
    public Server(int port,RobotArm robotArm){
        try{
        	arm=robotArm;
            portNum=port;
            localHost=(Inet4Address)Inet4Address.getLocalHost();
            serverSocket = new ServerSocket(portNum);//create a socket listening on port num
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void run(){
        try{       
            while(true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientThread(clientSocket,arm)).start();//starts a new thread to handle client connection
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            try {                
                serverSocket.close();//finally close listening server socket
            }
                catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}