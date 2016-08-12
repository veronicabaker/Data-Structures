
public class Move
{
	private int pileNumber;
	private int numberOfStones;
	public Move(int p, int n)
	{
		pileNumber = p;
		numberOfStones = n;
	}
	public int getPileNumber() 
	{ 
		return pileNumber; 
	}
	public int getNumberOfStones() 
	{ 
		return numberOfStones; 
	}
	public String toString() 
	{ 
		return String.format("Pile = %d, Number of Stones = %d", pileNumber, numberOfStones); 
	}
}
