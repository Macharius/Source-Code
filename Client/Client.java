<<<<<<< HEAD
=======
package Client;

>>>>>>> pr/1
/****************************************
/*        CS5223 - Assignment 1
/*      Apoorva Tyagi / Remi Pradal
/*
/*				Server.java
/* Main class for the Server program
/****************************************/

import java.net.*;
import java.io.*;
import java.util.*;


//Main class for the client program
//the syntax is Client server_address port
//the default server address is 127.0.0.1
//and the default port 9409
public class Client
{
<<<<<<< HEAD
    protected  String m_ipServer; //server ip address
    protected  int m_serverPort; //server port
    private int m_id;
    protected  Game m_game;
      
    public void getUpdate()
    {
        try
        {    
            ServerSocket ss = new ServerSocket(this.m_serverPort + this.m_id + 1);
            
            Socket socket = ss.accept();
            
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            
            try
            {
                this.m_game = (Game)in.readObject();
            }
            catch (ClassNotFoundException e)
            {

            }
            
            System.out.println("New game received from server");
            
            socket.close();
            ss.close();
        }
        catch (IOException e)
        {
            
        }
        
        
        
    }
         
    
    public void main(String args[])
    {
=======
         
    public static void main(String args[])
    {
    	String m_ipServer; //server ip address
    	int m_serverPort; //server port
    	int m_id;
    	Game m_game;
>>>>>>> pr/1
        //we try read the parameters
        try
        {
            m_ipServer = args[0];
            m_serverPort = Integer.parseInt(args[1]);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            //We set default parameters
            m_ipServer = "127.0.0.1";
            m_serverPort = 9409;    
        }
        
        
        Socket socket;      //the socket for the server
        ObjectInputStream in;  //buffer to read server's messages
        
<<<<<<< HEAD
        //We contact the server
=======
        //We contact the server to get the ID
>>>>>>> pr/1
        try
        {
            socket = new Socket(InetAddress.getByName(m_ipServer),m_serverPort);
            in = new ObjectInputStream (socket.getInputStream());
            int serverAnswer;
            
<<<<<<< HEAD
            //We read the server's answer, which the player ID
=======
            //We read the server's answer, which is the player ID
>>>>>>> pr/1
            try
            {
                serverAnswer = (int)in.readObject();
            }
            catch (ClassNotFoundException e)
            {
               System.out.println("Impossible to read given ID");
               serverAnswer = (int)0;
            }
            System.out.println("Connected to Server. Identified with ID : " + serverAnswer);
            
<<<<<<< HEAD
            this.m_id = serverAnswer;
=======
            m_id = serverAnswer;
>>>>>>> pr/1
            
            socket.close();
            try
            {
                Thread.sleep(20*1000);
            }
            catch (InterruptedException e)
            {
                
            }
            
<<<<<<< HEAD
=======
          //Write commands to server as game moves  
>>>>>>> pr/1
            while(true)
            {
                System.out.println("Send new command (Q to leave) :");
                Scanner sc = new Scanner(System.in);
                String str = sc.nextLine();
                
                if (str.charAt(0) == 'Q')
                    break;
                
                socket = new Socket(InetAddress.getByName(m_ipServer),m_serverPort);

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(serverAnswer);
                out.writeObject(str);
                out.flush();

<<<<<<< HEAD
                socket.close();
                
                getUpdate();
=======
               //socket.close();
                
                //getUpdate
                while(true)
                {
                try
                {  
                	//System.out.println("Check PORT on Client.java" + (m_serverPort+m_id+1));
                	socket = null;
                    ServerSocket ss = new ServerSocket(m_serverPort + m_id + 1);
                    
                    socket = ss.accept();

                    in = new ObjectInputStream(socket.getInputStream());
                    
                    try
                    {
                        m_game = (Game)in.readObject();
                        System.out.println("New game received from server" + in.toString());
                        m_game.printGrid();
                        break;
                    }
                    catch (ClassNotFoundException e)
                    {

                    }
                             
                    socket.close();
                    ss.close();
                }
                catch (IOException e)
                {
                 System.out.println("Invalid Entry. Retry.") ;  
                }
                sc.close();
                }
>>>>>>> pr/1
            }
        }
        catch (UnknownHostException e)
        {
            
        }
        catch (IOException e)
        {
            
        }
    }
<<<<<<< HEAD
=======
   
>>>>>>> pr/1
}

