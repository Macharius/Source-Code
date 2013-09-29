<<<<<<< HEAD
/****************************************
/*        CS5223 - Assignment 1
/*      Apoorva Tyagi / Rémi Pradal
/*
/*	        PlayerConnection.java
/* This class handle the players connection
/* at the beggining of the game. It delivers
/* a list of players with a given ID and
/* their IP adress
/****************************************/

import java.net.*;
import java.io.*;
import java.util.*;


// This class handle the creation of the player list
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
                        String stringIpClient=clientAddress.toString();
                        //int separator = stringIpClient.indexOf(":");
                        //stringIpClient=stringIpClient.substring(1,separator);
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
		


=======
package Server;

/****************************************
/*        CS5223 - Assignment 1
/*      Apoorva Tyagi / Remi Pradal
/*
/*	        PlayerConnection.java
/* This class handle the players connection
/* at the beginning of the game. It delivers
/* a list of players with a given ID and
/* their IP address
/****************************************/

>>>>>>> pr/1
//Class handling the user list creation
public class PlayersConnection
{
    private ServerConnections m_serverConnections;  //the communication class
    private int m_port; // the port to use

    public PlayersConnection(int port)
    {
        this.m_port = port;
        this.m_serverConnections = new ServerConnections(this.m_port);
        
        System.out.println("Class playerConnection created");
    }
    
    
<<<<<<< HEAD
    // the default port used to connect srever and client is 9409
=======
    // the default port used to connect server and client is 9409
>>>>>>> pr/1
    public PlayersConnection()
    {
        this(9409);
        
    }
    
    // Wait for player connections during the
    // given timeOut (in seconds). This is a
    // blocking call.
    protected void waitForPlayers(int timeOut)
    {          
        Thread connectionThread = new Thread(m_serverConnections);
        connectionThread.start();  // We start listening incoming connections

        // We listen during timout secs
        try
        {
            Thread.sleep(timeOut*1000);
        }
        catch (InterruptedException e)
        {
            System.out.println("Client connection listening aborted");
        }
        
        this.m_serverConnections.stop(); // We stop listening

    }

    // The default waiting time for new incoming connections is 20s
    public void waitForPlayers()
    {
        waitForPlayers(20);
    }

    // Return the list of connected players
    // 1st row => Player id
    // 2nd row => IP adress
    public String[][] getList()
    {
        return this.m_serverConnections.getPlayerList();
    }
}