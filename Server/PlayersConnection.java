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
    
    
    // the default port used to connect server and client is 9409
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