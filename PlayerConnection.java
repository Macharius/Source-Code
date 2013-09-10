/****************************************
/*        CS5223 - Assignment 1
/*      Apoorva Tyagi / Rémi Pradal
/*
/*	        PlayerConnection.java
/* This class handle the players connections
/* at the beggining of the game. It delivers
/* a list of players with a given ID and
/* their IP adress
/****************************************/

public class PlayerConnection
{
	ArrayList<int[]> playerList;
	
	public PlayerConnection()
	{
	}
	
	// Wait for player connections during the
	// given timeOut (in seconds). This is a
	// blocking call.
	public void waitForPlayers(int timeOut = 20)
	{
	}
	
	// Return the list of connected players
	// 1st row => Player id
	// 2nd row => IP adress
	public int[2][] getList()
	{
	}
}