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
		
		PlayersConnection pc = new PlayersConnection();
		
                System.out.println("waiting for players");
                
		pc.waitForPlayers(30);
                
                System.out.println("The game can start");
                
                
                String[][] list = pc.getList();
                
                System.out.println("Number of registered players : " + list.length);
                
                System.out.println("List of registered players :");
                for (int i = 0 ; i < list.length ; i++)
                    System.out.println("ID : " + list[i][0] + " IP address : " + list[i][1]);
                
                
		
		
		
	}
}
