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
			n = Integer.parseInt(args[1]);
			m = Integer.parseInt(args[2]);
			treasure_number = Integer.parseInt(args[3]);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Not enough Parameters");
			return;
		}
		
		
		Game game = new Game(n,m,treasure_number);
		
		
		
	}
}
