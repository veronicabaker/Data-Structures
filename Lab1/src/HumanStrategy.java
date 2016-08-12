
import java.util.Scanner;

public class HumanStrategy implements PileStrategy
{
	private Scanner in;
	public HumanStrategy(Scanner in)
	{
		this.in = in;
	}
	public String getName() { return "Human Player"; }
	public Move getMove(int[] piles) 
	{
		while (true)
		{
			System.out.printf("Choose a Pile (0 - %d): ", piles.length-1);
			int pile = Integer.parseInt(in.nextLine());
			if (pile < 0 || pile >= piles.length || piles[pile] <= 0) 
			{
				System.out.println("Invalid Pile");
				continue;
			}
			System.out.printf("Choose a number of stones to remove from pile %d (1 - %d): ",pile, piles[pile]);
			int numberOfStones = Integer.parseInt(in.nextLine());
			if (numberOfStones < 1 || numberOfStones > piles[pile])
			{
				System.out.println("Invalid Number of Stones");
				continue;
			}
			return new Move(pile, numberOfStones);
		}
	}
}
