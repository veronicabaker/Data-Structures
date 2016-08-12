import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Functions
{
	/**
	 * For each person you have a list of the people they 
	 * know in the map acquaintances (i.e., the person is 
	 * the key and their acquaintance list is the value).  
	 * A person p indirectly knows a person q if p knows 
	 * w and w knows q. In other words, an indirect 
	 * acquaintance is an acquaintance of an acquaintance.  
	 * Output a sorted list of all of the distinct indirect 
	 * acquaintances of the person given by name. The list 
	 * should be sorted in alphabetical order (the natural 
	 * ordering of Strings) with no duplicates.  
	 * Note: If a person isn't a key in acquaintances, 
	 * then they don't know anyone.  It is possible for 
	 * person p to know person q, but not vice-versa. 
	 * 
	 * @param acquaintances map of acquaintances
	 * @param name given person
	 * @return sorted list of distinct indirect acquaintances
	 */
	public static ArrayList<String> indirectAcquaintances(HashMap<String, ArrayList<String>> acquaintances, String name)
	{
		HashSet<String> indirectAcquaintances = new HashSet<>();
		ArrayList<String> indirectAcquaintancesArray = new ArrayList<>();
		if(acquaintances.containsKey(name))
		{
			ArrayList<String> directAcquaintances = acquaintances.get(name);
			for(int i = 0; i < directAcquaintances.size(); i++)
			{
				String acquaintance = directAcquaintances.get(i);
				if(acquaintances.containsKey(acquaintance))
				{
					ArrayList<String> indirect = acquaintances.get(acquaintance);
					indirectAcquaintances.addAll(indirect);
				}
			}
			indirectAcquaintancesArray.addAll(indirectAcquaintances);
		}
		Collections.sort(indirectAcquaintancesArray);
		return indirectAcquaintancesArray;
	}
	
	/**
	 * You are given a sequence of numbers vals.  Find the 
	 * length of the longest contiguous subsequence 
	 * containing distinct values (i.e., no repeats).  
	 * Contiguous means that all the entries in your 
	 * subsequence must be adjacent (i.e., no gaps).
	 * Hint: Sliding window (see Lecture 6 exercise 3).  
	 * Keep growing window on right to make larger and 
	 * decrease on left to avoid duplicates.
	 * 
	 * @param vals the list of values
	 * @return length of the longest contiguous subsequence
	 * containing distinct values
	 */
	public static int getDistinctLength(long[] vals)
	{
		HashSet<Long> contiguous = new HashSet<>();
		ArrayDeque<Long> values = new ArrayDeque<>();
		int max = 0;
		for(int i = 0; i < vals.length; i++)
		{
			if(contiguous.contains(vals[i]))
			{
				while(!(values.peekFirst().equals(vals[i])))
				{
					contiguous.remove(values.removeFirst());
					continue;
				}
				values.removeFirst();
				values.add(vals[i]);
			}
			else
			{
				values.add(vals[i]);
			}
			contiguous.add(vals[i]);
			if(values.size() > max)
			{
				max = values.size();
			}
		}
		return max;
	}
	
	
	/**
	 * Consider a row of n boxes lined up one after another.  
	 * Each box is colored red or black.  The row coloring 
	 * is considered valid if there are never 3 or more 
	 * consecutive red boxes.
	 * Considering all possible colorings of the n boxes in 
	 * the row, return the number of valid colorings.  
	 * Here 1 <= n <= 20.
	 * Hint: Try them all with bitsets.
	 * 
	 * @param n number of boxes in the row
	 * @return the number of valid colorings 
	 */
	public static long countValidRows(int n)
	{
		int valid = 0;
		for(int i = 0; i < (1<<(n)); ++i)
		{
			int count = 0;
			boolean isValid = true;
			for(int j = 0; j < n; j++)
			{
				if((i & (1L<<j)) != 0)
				{
					count += 1;
				}
				else
				{
					count = 0;
				}
				if(count > 2)
				{
					isValid = false;
					break;
				}
			}
			if(isValid)
			{
				valid += 1;
			}
		}
		return valid;
	}
	

	/**
	 * For extra credit solve implement the previous function 
	 * for 1 <= n <= 60. 
	 * Hint: You cannot try them all.
	 * 
	 * @param n number of boxes in the row
	 * @return the number of valid colorings 
	 */
	public static long countValidRowsExtra(int n)
	{
		return -1;
	}
}
