/****************************************
/*        CS5223 - Assignment 1
/*      Apoorva Tyagi / Remi Pradal
/*
/*				Game.java
/* Game class. This class handle all the 
/* game related operations. This class 
/* must be the same in both clien and server
/* program.
/****************************************/


import java.net.*;
import java.io.*;
import java.util.*;


public class Game
{
	public int m_length;	//length of the game grid
	public int m_heigth; //heigth of the game grid
	public int m_treasure_number; //number of treasure in the grid
	public int m_grid[][];		 //the game's grid
	public ArrayList<Integer>  m_listTreasures;  // list of treasure position
									   // first element => x position
									   // second element => y position
	public ArrayList<int[]> m_players	;		//Player's list
										// first element => Player ID
										// second and third elements => (x,y) position
										// fourth element => number of treasure
	
	
	public Game(int length, int heigth, int treasure_number)
	{
		m_length = length;
		m_heigth = heigth;
		m_treasure_number = treasure_number;
		
		initialization();
	}
	
	
	//Initialize the grid, the treasure positions, the list of players
	public void initialization()
	{
		//to be implemented
	}
	
	// Add a new player in the game. He has a random position and no treasure
	// if idPlayer is already registred, this method raises a PlayerAlreadyPresent
	// exception.
	public void addPlayer(int idPlayer)
	{
		//to be implemented
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
		//to be implemented
		return 0;
	}
	
	// return the length of the game grid
	public int getLength()
	{
		//to be implemented
		return 0;
	}
	
	// return the heigth of the game grid
	public int getHeigth()
	{
		//to be implemented
		return 0;
	}
	
	// print the grid
	// . => empty cell
	// T => treasure(s)
	// P => player
	public void printGrid()
	{
		//to be implemented
	}
	
	// print the idPlayer's Grid
	// . => empty cell
	// T => treasure(s)
	// P => player
	// C => current player
	public void printGrid(int idPlayer)
	{

	}
}
