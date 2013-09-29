<<<<<<< HEAD
=======
package Server;

>>>>>>> pr/1
/****************************************
/*        CS5223 - Assignment 1
/*      Apoorva Tyagi / Remi Pradal
/*
/*	PlayerIOInterface.java
/* Class handling player client com
/* (for the server)
/****************************************/

import java.net.*;
import java.io.*;
import java.util.*;
<<<<<<< HEAD
=======
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
>>>>>>> pr/1


//This class handle the input and outputs with the clients
public class PlayerIOInterface
{
    private ArrayList<Integer> m_listIDPlayers;        //List of players ID
    private ArrayList<String> m_listIPPlayers;         //List of players IP
    private Game m_game;                               //The current game used
    private ArrayList<Thread> m_threadsPlayerGestion;  //The list of processing threads
    private ArrayList<PlayerGestion> m_playerGestion;  //The list of PlayerGestion class
                                                       // (threads). One for each client
    private int m_port;
    
    
    //Constructor
    //game => the game to use
    //listPlayers => list of clients listPlayers[i][0] -> ID and [1] ->IP
    //port => the base port used for communications
    public PlayerIOInterface(Game game, String[][] listPlayers, int port)
    {
        this.m_game = game;
        this.m_port = port;
        
        this.m_threadsPlayerGestion = new ArrayList<>();
        this.m_playerGestion = new ArrayList<>();
        this.m_listIDPlayers = new ArrayList<>();
        this.m_listIPPlayers = new ArrayList<>();
        
        //For each players we create the appropriate PlayerGestion thread and we run it
        for (int i = 0 ; i < listPlayers.length ; i++ )
        {          
            this.m_listIDPlayers.add(Integer.parseInt(listPlayers[i][0]));
            this.m_playerGestion.add(new PlayerGestion((int)m_listIDPlayers.get(i),this.m_game));
            this.m_threadsPlayerGestion.add(new Thread(m_playerGestion.get(i)));
<<<<<<< HEAD
=======
            //ExecutorService executor = Executors.newSingleThreadExecutor();
            //Future<Game> updatedGames = executor.submit(m_playerGestion.get(i));
>>>>>>> pr/1
            //We launch the processing thread
            synchronized(this.m_threadsPlayerGestion.get(i))
            {
                this.m_threadsPlayerGestion.get(i).start();
            }
            this.m_listIPPlayers.add(listPlayers[i][1]);
            
        }
    }
    
    
    //Add a new player (create the appropriate threaad)
    //player => [0] -> ID [1] -> IP
    public void addPlayer(String player[]) throws PlayerAlreadyRegistered
    {
        //We chack the ID given is not already registered
        boolean nonPresent = true;
        for (int i = 0 ; i < this.m_listIDPlayers.size() ; i++)
            if (this.m_listIDPlayers.get(i) == Integer.parseInt(player[0]))
                nonPresent = false;
        
        if (nonPresent)
        {
            this.m_listIDPlayers.add(Integer.parseInt(player[0]));
            m_playerGestion.add(new PlayerGestion(Integer.parseInt(player[0]),this.m_game));
            m_listIPPlayers.add(player[1]);
            m_threadsPlayerGestion.add(new Thread(m_playerGestion.get(m_playerGestion.size() - 1)));
            m_threadsPlayerGestion.get(m_playerGestion.size()-1).run();
        }
        else
            throw new PlayerAlreadyRegistered(Integer.parseInt(player[0]));
    }
    
    //We send an updated version on the game to the client when its request has
    //been proceed
    //IDplayer -> the ID of the client to update
    public void updateClient(int IDplayer)
    {
        int index = -1;
        for (int i = 0 ; i < this.m_listIDPlayers.size(); i ++)
<<<<<<< HEAD
            if (this.m_listIDPlayers.get(i)==IDplayer)
                index = i;
        
=======
        {
        	//System.out.println("Checking in updateclient:" + m_listIDPlayers.get(i));
            if (this.m_listIDPlayers.get(i)==IDplayer)
                index = i;
        }
>>>>>>> pr/1
        if (index == -1)
            return;
        
        try
        {
<<<<<<< HEAD
            Socket socket = new Socket(InetAddress.getByName(m_listIPPlayers.get(index)),this.m_port + IDplayer + 1);
            
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
=======
        	//System.out.println("Checking PORT before PLAYERIOIF Socket:" + (this.m_port+IDplayer+1));
            Socket socket = new Socket(InetAddress.getByName(m_listIPPlayers.get(index)),this.m_port + IDplayer + 1);
            
            System.out.println("PII SOCKET:" + socket.toString());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
           
            System.out.println("GAME BEFORE WRITEOBJECT:"); m_game.printGrid();
>>>>>>> pr/1
            out.writeObject(m_game);
            out.flush();
            
            socket.close();
<<<<<<< HEAD
=======
            System.out.println("Client" + IDplayer+ "updated.");
>>>>>>> pr/1
        }
        catch (IOException e)
        {
            System.out.println("Impossible to initate sending connexion with id : " + IDplayer);
        }
<<<<<<< HEAD
        
        
        
=======
        catch (Exception e)
        {
        	System.out.println("Error:" +e);
        }
              
    }
    
    public void receiveGameNewState(Game game)
    {
    	m_game = game;
        //System.out.println("CHECK PRINT"); m_game.printGrid();

    	updateAll();
>>>>>>> pr/1
    }
    
    public void updateAll()
    {
        for (int i = 0 ; i < this.m_listIDPlayers.size(); i ++)
            updateClient(this.m_listIDPlayers.get(i));
    }
    
    
    //Main function of the server. Listen to incoming connections. Receive the
    // client message and call the process method.
    public void listen()
    {
       
        try
        {
            //new listening socket
            ServerSocket ss = new ServerSocket(9409);

            //main loop of the server. We listen to new request until all treasures
            //collected
            while (this.m_game.getTreasuresLeft() != 0)
            {
                //we try to get the new request
                try
                {
                    Socket socket = ss.accept();
                    
                    System.out.println("New incoming request !");
                    
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    
                    int idGiven = 0;
                    
                    //After the connexion, the client have to send his ID. (not secured of course)
                    try
                    {
                        idGiven = (int)in.readObject();
                    }
                    catch (ClassNotFoundException e)
                    {
                        
                    }
                    
                    //We check the ID given by the incoming request is registered
                    boolean valid = false;
                    for (int i = 0 ; i < m_listIDPlayers.size() ; i++)
                        if(m_listIDPlayers.get(i) == idGiven)
                            valid = true;
                    
                    
                    if (valid)
                    {
                        //We get the next line sended which has to be the direction
                        //and we call the processing method
                        try
                        {
                            System.out.println("ID received : " + idGiven + "(valid)");
                            String request = (String)in.readObject();
                            System.out.println("request : " + request);
<<<<<<< HEAD
                            
                            this.process(idGiven, request);
=======
                            this.process(idGiven, request);
                            //updateAll();
>>>>>>> pr/1
                        }
                        catch (ClassNotFoundException e)
                        {
                            
                        }
                        
                    }
                    
                    //we close the incoming socket
                    in.close();
                    socket.close();
                }

               catch (IOException e)
               {

               }

            }
            ss.close();
        }
        catch (IOException e)
        {
            
        }
    }
    
    
    //This method send a message to the processing thread of the appropriate client
    //then it wakes up the thread which will compute the message and eventually apply
    //some modifications over the game
    //iDplayer => the ID of the client
    //message => the message
    public boolean process(int iDplayer, String message)
    {
        for (int i = 0 ; i < this.m_listIDPlayers.size() ; i++)
            if (this.m_listIDPlayers.get(i) == iDplayer)
            {
                System.out.println("New message from id " + iDplayer + " :");
                System.out.println(message);
         
                //we send the message to the thread's queue
                m_playerGestion.get(i).setMessage(message);
                
                //we wake up the thread (notify)
                synchronized(m_playerGestion.get(i))
                {
<<<<<<< HEAD
                    m_playerGestion.get(i).wakeUp();
                }
                
            }
        
        //not implemented => send updated game
        return true;
    }
    
=======
                    m_playerGestion.get(i).wakeUp(m_game, this);
                }
                
            }

        //not implemented => send updated game
        return true;
    }

>>>>>>> pr/1
    
}
