package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;


//This class handle the creation of the player list
class ServerConnections implements Runnable
{
 private int m_serverPort;                   // The port we use
 private ServerSocket m_serverSocket;        // The server socket
 private ArrayList<Integer> m_clientListId;  // The list of given IDs
 private ArrayList<String> m_clientListIP;   // The list of the corresponding IPs
 private boolean m_running;                  // true if we listen ton incoming connections
 private int m_clientCount;                  // Counter of the IDs given
	
 
 // Initiate the class.
 public ServerConnections(int portNumber)
 {
     this.m_serverPort = portNumber;
     this.m_clientListId = new ArrayList<Integer>();
     this.m_clientListIP = new ArrayList<String>();
     this.m_running = true;
             
     //We try to create a new socket server
     try
     {
        this.m_serverSocket = new ServerSocket(this.m_serverPort);
     }
     catch (IOException e)
     {
         System.out.println("Impossible to initiate the socket server");
     }
 }
 
 // The default port value is 9409
 public ServerConnections()
 {
     this(9409);
 }
 
 // Method called when we launch the Thread
 // Warning : this is not a synchronized method !
 public void run()
 {
     //We set the running flag as true
     running(true);
     
     // We listen new incoming connections as long as the running flag is not
     // down
     while (running())
     {
         Socket newClientSocket = null;
         // We try to accept the new incoming connection
         try 
         {
             newClientSocket = this.m_serverSocket.accept();
             
                 try 
                 {
                     SocketAddress clientAddress = newClientSocket.getRemoteSocketAddress();
                     boolean validId = false;
                     int id = this.m_clientCount;

                     // We check that the ID is not already given
                     while (!validId)
                     {
                         id+=1;
                         validId = true;
                         for (int i = 0 ; i < this.m_clientListId.size(); i++)
                             if(this.m_clientListId.get(i) == id)
                                 validId = false;
                     }

                     //We update the client counter
                     this.m_clientCount = id;

                     // We add the client informations to the list
                     this.m_clientListId.add(id);
                     String stringIpClient=clientAddress.toString().substring(1);
                     //int separator = stringIpClient.indexOf(":");
                     //stringIpClient=stringIpClient.substring(1,separator);
                     //System.out.println("CHECKING IP ADDRESS OF CLIENT ADDED" + stringIpClient);
                     this.m_clientListIP.add(stringIpClient);

                     // We send the ID to the client
                     ObjectOutputStream out = new ObjectOutputStream(newClientSocket.getOutputStream());
                     out.writeObject(id);
                     out.flush();

                     newClientSocket.close();				
                 } 
                 catch (IOException e) 
                 {
                     System.out.println("Impossible to add the new client to the list");
                 }
         } 
         catch (IOException e) 
         {
             System.out.println("Problem accepting new client");
         }
         
         // We try to add the client ID and IP address in the list and we
         // send him his ID
         
     }
 }
 
 // Change the running flag state
 private synchronized void running(boolean isRunning)
 {
     this.m_running = isRunning;
 }
 
 // Return the running flag state
 private synchronized boolean running()
 {
     return this.m_running;
 }
 
 // Stopping the thread
 public synchronized void stop()
 {
     // We set the running flag as down
     running(false);
     
     // We close the server socket
     try
     {
         this.m_serverSocket.close();
     }
     catch (IOException e)
     {
         System.out.println("Impossible to close the server socket");
     }
 }
 
 // Return a n*2 String table with clients ID and the corresponding IP address
 // [n][0] => ID of the nth client
 // [n][1] => IP address of the nth client
 public String[][] getPlayerList()
 {
     String[][] playerList = new String[this.m_clientListId.size()][2];
         
     for (int i = 0 ; i < this.m_clientListId.size(); i ++)
     {
         playerList[i][0] = this.m_clientListId.get(i).toString();
         playerList[i][1] = this.m_clientListIP.get(i);
     }
     return playerList;
 }
}
		
