package Server;

//Exception raised when you try a player that already exists

public class PlayerAlreadyRegistered extends Exception
{
	
	public PlayerAlreadyRegistered(int id)
	{
		System.out.println("Player with id " + id + " is already registered");
	}
}
