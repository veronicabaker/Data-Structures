import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class PortfolioStats 
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		HashMap<String, Integer> trades = new HashMap<>();
		ArrayList<Entry<String, Integer>> finalPositions = new ArrayList<>();
		while(input.hasNext())
		{
			String next = input.nextLine();
			String[] info = next.split(",");
			if(!(trades.containsKey(info[0])))
			{
				trades.put(info[0], Integer.parseInt(info[1]));
			}
			else
			{
				int value = trades.get(info[0]);
				trades.put(info[0], value + Integer.parseInt(info[1]));
			}
		}
		input.close();
		Set<Entry<String, Integer>> s = trades.entrySet();
		Iterator<Entry<String, Integer>> i = s.iterator();
		while(i.hasNext())
		{
			Entry<String, Integer> pair = i.next();
			finalPositions.add(pair);
		}
		Collections.sort(finalPositions, new StockComparator());
		for(int j = finalPositions.size() - 1; j >= 0; j--)
		{
			System.out.println(finalPositions.get(j).getKey()
					+ "," + finalPositions.get(j).getValue());
		}
	}
}
