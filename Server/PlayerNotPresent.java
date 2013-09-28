package Server;


//Exception raised when you try to make an operation on a non
//existing player
public class PlayerNotPresent extends Exception
{
	public PlayerNotPresent()
	{
		System.out.println("Player not present");
	}
}
