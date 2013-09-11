/****************************************
/*        CS5223 - Assignment 1
/*      Apoorva Tyagi / Remi Pradal
/*
/*				Server.java
/* Main class for the Server program
/****************************************/

import java.net.*;
import java.io.*;
import java.util.*;


public class Server
{

	
	// Program syntax : Server.java grid_length grid_height treasure_number
	public static void main(String[] args)
	{
		int n;
		int m;
		int treasure_number;
		
		try
		{
			n = Integer.parseInt(args[0]);
			m = Integer.parseInt(args[1]);
			treasure_number = Integer.parseInt(args[2]);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			n = 10;
			m = 10;
			treasure_number = 10;
		}
		
		Game game = new Game(n,m,treasure_number);
		
		System.out.println("Game created with characteristics : (length,height,treasure number) = (" + n + "," + m + "," + treasure_number + ")");
		
		try
		{
			game.addPlayer(1);
		}
		catch (PlayerAlreadyRegistered e)
		{
		}
		
		
		game.printGrid();
		
		
		
	}
}
