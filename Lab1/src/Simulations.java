
/**
 * Lab 1
 * @author Emily Veronica Baker
 */

import java.util.Arrays;
import java.util.Random;

public class Simulations 
{
	static Random ran = new Random(1);
	public static void main(String[] args)
	{
		//using run configurations
		int numTeams = Integer.parseInt(args[0]);
		int numSeasons = Integer.parseInt(args[1]);
		double max = averageMax(simulateAll(numTeams, numSeasons));
		System.out.println(max);
	}
	public static int[] simulate(int numTeams)
	{
		int[] scores = new int[numTeams];
		//each team
		for(int i = 0; i < numTeams; i++)
		{
			//each game not double counting games
			for(int j = i; j < numTeams; j++)
			{
				if(j != i)
				{
					boolean result = ran.nextBoolean();
					if(result)
					{
						scores[i] += 1;
					}
					else
					{
						scores[j] += 1;
					}
				}
			}
		}
		return scores;
	}
	static double max(int[] scores)
	{
		Arrays.sort(scores);
		return scores[scores.length - 1];
	}
	//call simulate m times to fill array
	public static double[] simulateAll(int numTeams, int numSeasons)
	{
		double[] maxes = new double[numSeasons];
		for(int i = 0; i < numSeasons; i++)
		{
			int[] scores = simulate(numTeams);
			maxes[i] = max(scores);
		}
		return maxes;
	}
	public static double averageMax(double[] maxes)
	{
		double sum = 0;
		for(int i = 0; i < maxes.length; i++)
		{
			sum += maxes[i];
		}
		return sum / maxes.length;
	}
}

