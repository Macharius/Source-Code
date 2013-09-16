/****************************************
/*        CS5223 - Assignment 1
/*      Apoorva Tyagi / Remi Pradal
/*
/*	PlayerIOInterface.java
/* Manage messages provided bu the main
/* server class
/****************************************/
class PlayerGestion implements Runnable
{
    int m_id;
    Game m_game;
    
    public PlayerGestion(int id,Game game)
    {
        this.m_id = id;
        this.m_game = game;
    }
    
    public int getId()
    {
        return this.m_id;
    }
    
    public synchronized void setMessage(String message)
    {
        
    }
    
    public void run()
    {
       
    }
}
