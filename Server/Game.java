<<<<<<< HEAD
=======
package Server;

>>>>>>> pr/1
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


<<<<<<< HEAD
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


=======
import java.util.*;

>>>>>>> pr/1
public class Game
{
	private int m_length;	//length of the game grid
	private int m_height; //height of the game grid
	private int m_treasure_number; //number of treasure in the grid
	private ArrayList<int[]>  m_listTreasures;  // list of treasure position
									   // first element => x position
									   // second element => y position
	private ArrayList<int[]> m_players	;		//Player's list
										// first element => Player ID
										// second and third elements => (x,y) position
										// fourth element => number of treasure
	
	
	public Game(int length, int height, int treasure_number) 
	{
		this.m_length = length;
		this.m_height = height;
		this.m_treasure_number = treasure_number;
		
		this.m_listTreasures = new ArrayList<int[]>();
		this.m_players = new ArrayList<int[]>();
		
		initialization();
	}
	
	
	//Initialize the grid, the treasure positions
	public synchronized void initialization()
	{
		Random random_generator = new Random();
		
		for (int i = 0 ; i < this.m_treasure_number ; i++)
		{	
			int x = random_generator.nextInt(this.m_length);
			int y = random_generator.nextInt(this.m_height);
			int[ ] line = {x,y};

			//We randomly put treasures over the grid
			//it may have different treasures in the same cell
			this.m_listTreasures.add(line);
		}
	}
	
	// Add a new player in the game. He has a random position and no treasure
	// The initial position of the player must be different from any treasure
	// position
	// if idPlayer is already registred, this method raises a PlayerAlreadyPresent
	// exception.
	// idPlayer should be a strictly positive integer
	public synchronized void addPlayer(int idPlayer) throws PlayerAlreadyRegistered
	{
		// We search in the list if there is already a player with the given ID
		boolean id_not_present = true;
		for (int i = 0; i < this.m_players.size() ; i++)
			if (this.m_players.get(i)[0] == idPlayer)
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
				xPlayer = random_generator.nextInt(this.m_length);
				yPlayer = random_generator.nextInt(this.m_height);
				
				for (int i = 0 ; i < this.m_listTreasures.size() ; i++)
					if (xPlayer == this.m_listTreasures.get(i)[0] && yPlayer == this.m_listTreasures.get(i)[1])
						treasure = true;
			}	
			
			
			int[ ] line = {idPlayer,xPlayer, yPlayer, 0};
			this.m_players.add(line);
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
	public synchronized boolean movePlayer(int idPlayer, char direction) throws PlayerNotPresent, WrongDirection
	{
<<<<<<< HEAD
=======
		
>>>>>>> pr/1
		//First we make sure that there is a player with the given ID in the list
		int index_player = -1;
		for (int i = 0 ; i < this.m_players.size() ; i ++)
			if (this.m_players.get(i)[0] == idPlayer)
				index_player = i;
		
		if (index_player != -1)
			//There is a player in the list. Now we check that the input char is correct
			if (direction == 'N' || direction == 'S' || direction == 'E' || direction == 'W')
			{			
				int xPlayer = this.m_players.get(index_player)[1];
				int yPlayer = this.m_players.get(index_player)[2];
				
				boolean border = (direction == 'N' && yPlayer == 0) ||
									 (direction == 'S' && yPlayer == this.m_height-1) ||
									 (direction == 'W' && xPlayer == 0) ||
									 (direction == 'E' && xPlayer == this.m_length-1);
					
				// We check that the player is not moving to an impossible direction
				// ie he is on a border
<<<<<<< HEAD
=======
				
>>>>>>> pr/1
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
					for (int j = 0 ; j < this.m_listTreasures.size() ; j++)
						if (this.m_listTreasures.get(j)[0] == xPlayer && this.m_listTreasures.get(j)[1] == yPlayer)
						{
							this.m_players.get(index_player)[3]++;
							this.m_listTreasures.remove(j);
						}
					
					// We update the player's position
					this.m_players.get(index_player)[1] = xPlayer;
					this.m_players.get(index_player)[2] = yPlayer;
				
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
		for (int i = 0 ; i < this.m_players.size() ; i++)
			if (this.m_players.get(i)[0] == idPlayer)
				return this.m_players.get(i)[3];
		
		// if the id given has not been found
		throw new PlayerNotPresent();
	}
	
        public int getTreasuresLeft()
        {
            return m_listTreasures.size();
        }
        
	// return the length of the game grid
	public int getLength()
	{
		return this.m_length;
	}
	
	// return the height of the game grid
	public int getheight()
	{
		return this.m_height;
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
<<<<<<< HEAD
	public void printGrid(int idPlayer)
=======
	public synchronized void printGrid(int idPlayer)
>>>>>>> pr/1
	{
		for (int i = 0 ; i < this.m_height ; i ++)
		{
			String line = new String();
			for (int j = 0 ; j < this.m_length ; j++)
			{
				String char_to_add = new String(".");
				
				for (int k = 0 ; k < this.m_players.size() ; k++)
					if (this.m_players.get(k)[2] == i && this.m_players.get(k)[1] == j)
							char_to_add = "P";
							
							
				for (int k = 0 ; k < this.m_players.size() ; k++)
					if (this.m_players.get(k)[2] == i && this.m_players.get(k)[1] == j)
						if (this.m_players.get(k)[0] == idPlayer)
							char_to_add = "C";
				
				for (int k = 0 ; k < this.m_listTreasures.size() ; k++)
					if (this.m_listTreasures.get(k)[1] == i && this.m_listTreasures.get(k)[0] == j)
						char_to_add = "T";
						
				line = line + char_to_add;
			}
			System.out.println(line);
		}
	}
	
	
	// Print the score of all players
	public void printScores()
	{
		for (int i = 0 ; i < this.m_players.size() ; i++)
			System.out.println("Player with ID " + this.m_players.get(i)[0] + " has collected " + this.m_players.get(i)[3] + " treasures.");
	}
}
