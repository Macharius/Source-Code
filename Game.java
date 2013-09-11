/****************************************
/*        CS5223 - Assignment 1
/*      Apoorva Tyagi / Remi Pradal
/*
/*				Game.java
/* Game class. This class handle all the 
/* game related operations. This class 
/* must be the same in both clien and server
/* program. However, in the client program
/* it used as a "read only" method
/****************************************/


import java.net.*;
import java.io.*;
import java.util.*;


class PlayerAlreadyRegistered extends Exception
{
	public PlayerAlreadyRegistered(int id)
	{
		System.out.println("Player with id " + id + " is already registered");
	}
}


public class Game
{
	public int m_length;	//length of the game grid
	public int m_height; //height of the game grid
	public int m_treasure_number; //number of treasure in the grid
	public ArrayList<int[]>  m_listTreasures;  // list of treasure position
									   // first element => x position
									   // second element => y position
	public ArrayList<int[]> m_players	;		//Player's list
										// first element => Player ID
										// second and third elements => (x,y) position
										// fourth element => number of treasure
	
	
	public Game(int length, int height, int treasure_number) 
	{
		m_length = length;
		m_height = height;
		m_treasure_number = treasure_number;
		
		m_listTreasures = new ArrayList<int[]>();
		m_players = new ArrayList<int[]>();
		
		initialization();
	}
	
	
	//Initialize the grid, the treasure positions
	public void initialization()
	{
		Random random_generator = new Random();
		for (int i = 0 ; i < m_treasure_number ; i++)
		{	
			int x = random_generator.nextInt(m_length);
			int y = random_generator.nextInt(m_height);
			int[ ] line = {x,y};			
			m_listTreasures.add(line);
		}
	}
	
	// Add a new player in the game. He has a random position and no treasure
	// The initial position of the player must be different from any treasure
	// position
	// if idPlayer is already registred, this method raises a PlayerAlreadyPresent
	// exception.
	// idPlayer should be a strictly positive integer
	public void addPlayer(int idPlayer) throws PlayerAlreadyRegistered
	{
		boolean id_not_present = true;
		for (int i = 0; i < m_players.size() ; i++)
		{
			if (m_players.get(i)[0] == idPlayer)
			{
				id_not_present = false;
			}
		}
		if (!id_not_present || idPlayer <= 0)
		{
			throw new PlayerAlreadyRegistered(idPlayer);
		}
		else
		{
			Random random_generator = new Random();
			boolean treasure = true;
			int xPlayer = 0;
			int yPlayer = 0;
			
			while (treasure)
			{
				treasure = false;
				xPlayer = random_generator.nextInt(m_length);
				yPlayer = random_generator.nextInt(m_height);
				
				for (int i = 0 ; i < m_listTreasures.size() ; i++)
					if (xPlayer == m_listTreasures.get(i)[0] && yPlayer == m_listTreasures.get(i)[1])
						treasure = true;
			}	
			
			int[ ] line = {idPlayer,xPlayer, yPlayer, 0};
			m_players.add(line);
		}
		
	}
	
	// Move the player with appropriate id in the given direction
	// If the player move on a cell where there is one (or more)
	// treasure, the corresponding treasures disappear from the grid
	// and the player gets his treasur count incremented
	//
	// direction => "N","S", "E" or "W"
	// return true if the player as moved otherwise rase a WrongDirection
	// exception
	public boolean movePlayer(int idPlayer, String direction)
	{
		//to be implemented
		return true;
	}
	
	// return the number of treasure of the player with given ID
	public int getTreasureNumber(int idPlayer)
	{
		try
		{
			return m_players.get(idPlayer)[1];
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return -1;
		}
	}
	
	// return the length of the game grid
	public int getLength()
	{
		return m_length;
	}
	
	// return the height of the game grid
	public int getheight()
	{
		return m_height;
	}
	
	// print the grid
	// . => empty cell
	// T => treasure(s)
	// P => player
	public void printGrid()
	{
		printGrid(0);
	}
	
	// print the idPlayer's Grid
	// . => empty cell
	// T => treasure(s)
	// P => player
	// C => current player
	public void printGrid(int idPlayer)
	{
		for (int i = 0 ; i < m_height ; i ++)
		{
			String line = new String();
			for (int j = 0 ; j < m_length ; j++)
			{
				String char_to_add = new String(".");
				for (int k = 0 ; k < m_players.size() ; k++)
					if (m_players.get(k)[1] == i && m_players.get(k)[2] == j)
						if (m_players.get(k)[0] == idPlayer)
							char_to_add = "C";
						else
							char_to_add = "P";
				
				for (int k = 0 ; k < m_listTreasures.size() ; k++)
					if (m_listTreasures.get(k)[0] == i && m_listTreasures.get(k)[1] == j)
						char_to_add = "T";
						
				line = line + char_to_add;
			}
			System.out.println(line);
		}
	}
}
