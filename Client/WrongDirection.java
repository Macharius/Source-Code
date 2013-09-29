package Client;


//Exception raised when the direction wished by the player
//is not acceptable
class WrongDirection extends Exception
{
	public WrongDirection()
	{
		System.out.println("Wrong direction");
	}
}
