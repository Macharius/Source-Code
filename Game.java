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

// Exception raised when you try a player that already exists
class PlayerAlreadyRegistered extends Exception
{
	public PlayerAlreadyRegistered(int id)
	{
		System.out.println("Player with id " + id + " is already registered");
	}
}


// Exception raised when you try to make an operation on a non
// existing player
class PlayerNotPresent extends Exception
{
	public PlayerNotPresent()
	{
		System.out.println("Player not present");
	}
}


// Exception raised when the direction whished by the player
// is not acceptable
class WrongDirection extends Exception
{
	public WrongDirection()
	{
		System.out.println("Wrong direction");
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

			//We randomly put treasures over the grid
			//it may have different treasures in the same cell
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
		// We search in the list if there is already a player with the given ID
		boolean id_not_present = true;
		for (int i = 0; i < m_players.size() ; i++)
			if (m_players.get(i)[0] == idPlayer)
				id_not_present = false;
		if (!id_not_present || idPlayer <= 0)
			//if there is we throw an exception
			throw new PlayerAlreadyRegistered(idPlayer);
		
		//if not we can add the player
		else
		{
			//We randomly put the player over the grid
			Random random_generator = new Random();
			boolean treasure = true;
			int xPlayer = 0;
			int yPlayer = 0;
			
			// We make sure that the initial player's position is not
			// the same as a treasure
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
	// return true if the player has moved otherwise raise a WrongDirection
	// exception
	// raise a PlayerNotPresent exception if idPlayer is not registered
	public boolean movePlayer(int idPlayer, char direction) throws PlayerNotPresent, WrongDirection
	{
		//First we make sure that there is a player with the given ID in the list
		int index_player = -1;
		for (int i = 0 ; i < m_players.size() ; i ++)
			if (m_players.get(i)[0] == idPlayer)
				index_player = i;
		
		if (index_player != -1)
			//There is a player in the list. Now we check that the input char is correct
			if (direction == 'N' || direction == 'S' || direction == 'E' || direction == 'W')
			{			
				int xPlayer = m_players.get(index_player)[1];
				int yPlayer = m_players.get(index_player)[2];
				
				boolean border = (direction == 'N' && yPlayer == 0) ||
									 (direction == 'S' && yPlayer == m_height-1) ||
									 (direction == 'W' && xPlayer == 0) ||
									 (direction == 'E' && xPlayer == m_length-1);
					
				// We check that the player is not moving to an impossible direction
				// ie he is on a border
				if (!border)
				{
					if (direction == 'N')
						yPlayer--;
					if (direction == 'S')
						yPlayer++;
					if (direction == 'E')
						xPlayer++;
					if (direction == 'W')
						xPlayer--;
					
					// We collect the treasures of the position and we remove them from the
					// treasure list
					for (int j = 0 ; j < m_listTreasures.size() ; j++)
						if (m_listTreasures.get(j)[0] == xPlayer && m_listTreasures.get(j)[1] == yPlayer)
						{
							m_players.get(index_player)[3]++;
							m_listTreasures.remove(j);
						}
					
					// We update the player's position
					m_players.get(index_player)[1] = xPlayer;
					m_players.get(index_player)[2] = yPlayer;
				
				}
				else
					throw new WrongDirection();
			}
			else 
				throw new WrongDirection();
		else
			throw new PlayerNotPresent();
		
		
		
		return true;
	}
	
	// return the number of treasure of the player with given ID
	public int getTreasureNumber(int idPlayer) throws PlayerNotPresent
	{
		for (int i = 0 ; i < m_players.size() ; i++)
			if (m_players.get(i)[0] == idPlayer)
				return m_players.get(i)[3];
		
		// if the id given has not been found
		throw new PlayerNotPresent();
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
					if (m_players.get(k)[2] == i && m_players.get(k)[1] == j)
							char_to_add = "P";
							
							
				for (int k = 0 ; k < m_players.size() ; k++)
					if (m_players.get(k)[2] == i && m_players.get(k)[1] == j)
						if (m_players.get(k)[0] == idPlayer)
							char_to_add = "C";
				
				for (int k = 0 ; k < m_listTreasures.size() ; k++)
					if (m_listTreasures.get(k)[1] == i && m_listTreasures.get(k)[0] == j)
						char_to_add = "T";
						
				line = line + char_to_add;
			}
			System.out.println(line);
		}
	}
	
	
	// Print the score of all players
	public void printScores()
	{
		for (int i = 0 ; i < m_players.size() ; i++)
			System.out.println("Player with ID " + m_players.get(i)[0] + " has collected " + m_players.get(i)[3] + " treasures.");
	}
}
