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


public class PlayerIOInterface
{
    private ArrayList<Integer> m_listIDPlayers;
    private Game m_game;
    private ArrayList<Thread> m_threadsPlayerGestion;
    private ArrayList<PlayerGestion> m_playerGestion;
    
    
    public PlayerIOInterface(Game game, String[] listID)
    {
        this.m_game = game;
        
        this.m_threadsPlayerGestion = new ArrayList<>();
        this.m_playerGestion = new ArrayList<>();
        this.m_listIDPlayers = new ArrayList<>();
        
        
        for (int i = 0 ; i < this.m_listIDPlayers.size() ; i++ )
        {
            this.m_listIDPlayers.add(Integer.parseInt(listID[i]));
            m_playerGestion.add(new PlayerGestion((int)m_listIDPlayers.get(i),this.m_game));
            m_threadsPlayerGestion.add(new Thread(m_playerGestion.get(i)));
            
        }
    }
    
    public void addPlayer(String player) throws PlayerAlreadyRegistered
    {
        boolean nonPresent = true;
        for (int i = 0 ; i < this.m_listIDPlayers.size() ; i++)
            if (this.m_listIDPlayers.get(i) == Integer.parseInt(player))
                nonPresent = false;
        
        if (nonPresent)
        {
            this.m_listIDPlayers.add(Integer.parseInt(player));
            m_playerGestion.add(new PlayerGestion(Integer.parseInt(player),this.m_game));
            m_threadsPlayerGestion.add(new Thread(m_playerGestion.get(m_playerGestion.size() - 1)));
        }
        else
            throw new PlayerAlreadyRegistered(Integer.parseInt(player));
    }
    
    public void message(int iDplayer, String message)
    {
        for (int i = 0 ; i < this.m_listIDPlayers.size() ; i++)
            if (this.m_listIDPlayers.get(i) == iDplayer)
            {
                m_playerGestion.get(i).setMessage(message);
                m_threadsPlayerGestion.get(i).notify();
            }
    }
    
    
}
