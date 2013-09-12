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



class ServerConnections implements Runnable
{
    private int m_serverPort;
    private ServerSocket m_serverSocket;
    private ArrayList<Integer> m_clientListId;
    private ArrayList<String> m_clientListIP;
    private boolean m_running;
    private int m_clientCount;
	
	
    public ServerConnections(int portNumber)
    {
        this.m_serverPort = portNumber;
        this.m_clientListId = new ArrayList<Integer>();
        this.m_clientListIP = new ArrayList<String>();
        this.m_running = true;
        
        try
        {
           this.m_serverSocket = new ServerSocket(this.m_serverPort); 
        }
        catch (IOException e)
        {
            
        }
    }
	
    public ServerConnections()
    {
        this(9409);
    }
	
	

    public void run()
    {
        running(true);
        
        while (running())
        {
            Socket newClientSocket = null;
            try 
            {
                newClientSocket = this.m_serverSocket.accept();
            } 
            catch (IOException e) 
            {
                System.out.println("Problem accepting new client");
                return;
            }
           try 
           {
                SocketAddress clientAddress = newClientSocket.getRemoteSocketAddress();
                boolean validId = false;
                int id = this.m_clientCount;

                while (!validId)
                {
                    id+=1;
                    validId = true;
                    for (int i = 0 ; i < this.m_clientListId.size(); i++)
                        if(this.m_clientListId.get(i) == id)
                            validId = false;
                }

                this.m_clientCount = id;

                this.m_clientListId.add(id);
                String stringIpClient=clientAddress.toString();
                int separator = stringIpClient.indexOf(":");
                stringIpClient=stringIpClient.substring(1,separator);
                this.m_clientListIP.add(stringIpClient);

                PrintWriter out = new PrintWriter(newClientSocket.getOutputStream(),true);
                out.println(id);

                out.flush();

                newClientSocket.close();				
            } 
            catch (IOException e) 
            {
                System.out.println("Impossible to add the new client to the list");
            }
        }
    }
			
    private synchronized void running(boolean isRunning)
    {
        this.m_running = isRunning;
    }
	
    private synchronized boolean running()
    {
        return this.m_running;
    }
	
    public synchronized void stop()
    {
        running(false);
        try
        {
            this.m_serverSocket.close();
        }
        catch (IOException e)
        {
            System.out.println("Impossible to close the server socket");
        }
    }
    
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
		


//Class handling the user list creation
public class PlayersConnection
{
    private ServerConnections m_serverConnections;
    private int m_port;

    public PlayersConnection(int port)
    {
        this.m_port = port;
        this.m_serverConnections = new ServerConnections(this.m_port);
        
        System.out.println("Class playerConnection created");
    }
    
    public PlayersConnection()
    {
        this.PlayersConnection(9409);
        
    }

    
    
    // Wait for player connections during the
    // given timeOut (in seconds). This is a
    // blocking call.
    protected void waitForPlayers(int timeOut)
    {      
        long startTime = System.currentTimeMillis();
        
        Thread connectionThread = new Thread(m_serverConnections);
        connectionThread.start();

        while((System.currentTimeMillis() - startTime) < timeOut *1000 );

        this.m_serverConnections.stop();

    }

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