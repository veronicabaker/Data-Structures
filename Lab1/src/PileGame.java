import java.util.Arrays;
import java.util.Scanner;

public class PileGame
{
	private int[] piles;
	private boolean isOver;
	private int turn;
	private PileStrategy[] players;
	
	public PileGame(int[] piles, PileStrategy p1, PileStrategy p2)
	{
		this.piles = piles;
		for (int i = 0; i < piles.length; ++i)
		{
			if (piles[i] < 0) throw new RuntimeException("Piles cannot be negative");
		}
		setOver();
		turn = 0;
		piles = Arrays.copyOf(piles, piles.length);
		players = new PileStrategy[] { p1, p2 };
	}
	
	private boolean setOver()
	{
		int sum = 0;
		for (int i = 0; i < piles.length; ++i) sum += piles[i];
		return isOver |= (sum == 0);
	}
	
	private void playMove(Move m)
	{
		int pile = m.getPileNumber();
		int numberOfStones = m.getNumberOfStones();
		if (pile < 0 || pile >= piles.length || numberOfStones < 0 || numberOfStones > piles[pile])
		{			
			System.out.printf("Player %d made an invalid move\n", turn+1);
			isOver = true;
		}
		piles[pile] -= numberOfStones;
		turn = 1-turn;
		setOver();
	}
	
	public void play()
	{
		int round = 1;
		while (!isOver())
		{
			System.out.printf("Round %d: The current piles are:\n%s\n", round, Arrays.toString(piles));
			System.out.printf("Turn Player %d\n", turn+1);
			Move m = players[turn].getMove(Arrays.copyOf(piles,piles.length));
			System.out.printf("Playing Move: %s\n", m);
			playMove(m);
		}
		System.out.printf("Player %d (%s) wins!!\n", getWinner(), players[1-turn].getName());
	}
	
	public boolean isOver() { return isOver; }
	public int getWinner() { return 2-turn; }

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		PileStrategy[] strats = { 
				new HumanStrategy(in),
				//your strategies go here
		};
		PileStrategy[] chosenStrats = new PileStrategy[2];
		for (int i = 0; i < 2; )
		{
			System.out.printf("Choose strategy for player %d:\n", i+1);
			for (int j = 0; j < strats.length; ++j)
			{
				System.out.printf("%d: %s\n", j+1, strats[j].getName());
			}
			int strat = Integer.parseInt(in.nextLine())-1;
			if (strat < 0 || strat >= strats.length)
			{
				System.out.println("Invalid Strategy Choice");
				continue;
			}
			chosenStrats[i++] = strats[strat];
		}
		System.out.println("Give a space-delimited list of positive pile sizes:");
		String[] toks = in.nextLine().trim().split("\\s+");
		int[] piles = new int[toks.length];
		for (int i = 0; i < toks.length; ++i)
		{
			piles[i] = Integer.parseInt(toks[i]);
			if (piles[i] < 0) 
			{
				System.out.printf("Non-Positive Pile Size!: %d\n",piles[i]);
				System.exit(1);
			}
		}
		PileGame g = new PileGame(piles, chosenStrats[0], chosenStrats[1]);
		g.play();
	}
}

