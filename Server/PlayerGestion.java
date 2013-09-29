<<<<<<< HEAD
=======
package Server;

>>>>>>> pr/1
/****************************************
/*        CS5223 - Assignment 1
/*      Apoorva Tyagi / Remi Pradal
/*
/*	PlayerGestion.java
/* Manage messages provided bu the main
/* server class
/****************************************/

<<<<<<< HEAD
import java.net.*;
import java.io.*;
import java.util.*;
=======
import java.util.*;
import java.util.concurrent.Callable;
>>>>>>> pr/1

//This class handle the modification of the game according to incoming messages
//each client must have it's corresponding class, which is a Thread/
class PlayerGestion implements Runnable
{
    private int m_id;                           //the ID of the client handled by the class
    Game m_game;                            //the current game
    private boolean m_running;              //true if the thread is running
    private ArrayList<String> m_messageList; // the message queue
<<<<<<< HEAD
    
=======
    PlayerIOInterface m_pii;
>>>>>>> pr/1
    
    public PlayerGestion(int id,Game game)
    {
        this.m_id = id;
        this.m_game = game;
        
        this.m_messageList = new ArrayList<>();
    }
    
    public int getId()
    {
        return this.m_id;
    }
    
    public synchronized void setMessage(String message)
    {
        System.out.println("New message added to stack");
        this.m_messageList.add(message);
    }
    
    public synchronized boolean isRunning()
    {
        return this.m_running;
    }
    
    public synchronized void setRunning(boolean running)
    {
        this.m_running = running;
    }
    
    
    public void run()
    {      
        this.setRunning(true);
        
        while(this.isRunning())
        {
            for (int i = 0 ; i < this.m_messageList.size() ; i++)
            {
                System.out.println("Player with id " + m_id + " wants to move");
                
                boolean moved;
                try
                {
                    this.m_game.movePlayer(this.m_id, this.m_messageList.get(i).charAt(0));
                    moved = true;
                    System.out.println("New grid :");
                    this.m_game.printGrid();
<<<<<<< HEAD

=======
                    m_pii.receiveGameNewState(m_game);              
>>>>>>> pr/1
                }
                catch (WrongDirection e)
                {
                    moved = false;
                }
                catch(PlayerNotPresent e)
                {
                    
                }
            }
            
            try
            {
                //we wait a notification of new incoming messages
                synchronized(this)
                {
                    this.wait();
                }
            }
            catch(InterruptedException e)
            {
            
            }
        }
    }
    
    //We notify the current thread
<<<<<<< HEAD
    public void wakeUp()
    {
        this.notify();
    }
=======
    public void wakeUp(Game game, PlayerIOInterface pii)
    {
    	m_game=game;
    	m_pii = pii;
        this.notify();
    }
    
  
>>>>>>> pr/1
            
}
