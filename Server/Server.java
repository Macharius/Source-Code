<<<<<<< HEAD
=======
package Server;

>>>>>>> pr/1
/****************************************
/*        CS5223 - Assignment 1
/*      Apoorva Tyagi / Remi Pradal
/*
/*				Server.java
/* Main class for the Server program
/****************************************/


<<<<<<< HEAD
import java.net.*;
import java.io.*;
import java.util.*;


=======
>>>>>>> pr/1
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
                
		pc.waitForPlayers();
                
                System.out.println("The game can start");
                
                
                String[][] list = pc.getList();
                String[] listId = new String[list.length];
                
                for (int i = 0 ; i < list.length ; i++)
                    listId[i] = list[i][0];
                
                
                System.out.println("Number of registered players : " + list.length);
                
                System.out.println("List of registered players :");
                for (int i = 0 ; i < list.length ; i++)
                    System.out.println("ID : " + listId[i] + " IP address : " + list[i][1]);
                
                for (int i = 0 ; i < list.length ; i++)
                {
                    try
                    {
                        game.addPlayer(Integer.parseInt(listId[i]));
                    }
                    catch(PlayerAlreadyRegistered e)
                    {
                        
                    }
                }
                
                game.printGrid();
                
                PlayerIOInterface pii = new PlayerIOInterface(game, list, 9409);
                
                System.out.println("Listening messages...");
                
                pii.listen();
<<<<<<< HEAD
                
                
		
		
=======
                		
>>>>>>> pr/1
		
	}
}
