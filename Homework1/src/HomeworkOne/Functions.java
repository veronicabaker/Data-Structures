
public class Functions
{
	/**
 	 * Suppose the letter 'A' is worth 1, 'B' is worth 2, and so forth, with 'Z' worth 26. 
	 * The value of a word is the sum of all the letter values in it.  Given an array arr
	 * of words composed of capital letters, return the value of the watch with the largest value.
	 * You may assume that arr has length at least 1.
	 * {"AAA","BBB","CCC"} => 9
	 * {"AAAA","B","C"} => 4
	 * {"Z"} => 26
	 * {"",""} => 0
	 * 
	 * Give the following in terms of n, where n is the sum of the lengths of the 
	 * strings in the input.
	 * (Worst-Case) Runtime: theta(n)
	 * (Worst-Case) Space used: theta(1)
	 * 
	 * @param arr array of words
	 * @return the value of the word with the largest value in arr 
	 */
	public static int largestValue(String[] arr)
	{
		//an alpha string to reference
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		//initialize a max variable which we will return
		int max = 0;
		//loop through the array of strings
		for(int i = 0; i < arr.length; i++)
		{
			//initialize a total variable
			int total = 0;
			//for each element of the array,
			//loop through the string
			for(int j = 0; j < arr[i].length(); j++)
			{
				//increase total according to the index
				//only if is a letter
				if(Character.isLetter(arr[i].charAt(j)))
				{
					total += alpha.indexOf(arr[i].charAt(j)) + 1;
				}
			}
			//set max to the first value
			//compare total and max and set max if total is greater
			if(i == 0 || total > max)
			{
				max = total;
			}
		}
		return max;
	}

	/**
	 * Given a String str return the length of the longest substring that is 
	 * entirely the same character.  For example,
	 * "AAABAABC" => 3,
	 * "AAAAAA" => 6,
	 * "" => 0,
	 * "31113444411" => 4,
	 * "aAbBcC" => 1 
	 * 
	 * Give the following in terms of n, where n is the length of str.
	 * (Worst-Case) Runtime: theta(n)
	 * (Worst-Case) Space used: theta(1)
	 * 
	 * @param str the input String
	 * @return length of the longest substring of that is entirely the same character
	 */
	public static int longestRepeat(String str)
	{
		//if it is an empty string, return 0
		if(str.length() == 0)
		{
			return 0;
		}
		//initialize a previous variable to compare to 
		char previous = str.charAt(0);
		//initialize a max variable and a count variable
		int max = 1;
		int count = 1;
		//loop through the string starting at index 1
		for(int i = 1; i < str.length(); i++)
		{
			//compare each character to the previous character
			//if they are the same, increment count
			if(str.charAt(i) == previous)
			{
				count += 1;
				//if we are on the last character
				//check if this is the max sequence and return max
				if(i == str.length() - 1 && count > max)
				{
					return count;
				}
			}
			//if they are not the same and count is greater than 1
			if(str.charAt(i) != previous)
			{
				//compare count and max and adjust max accordingly
				if(count > max)
				{
					max = count;
				}
				//set count back to 1
				count = 1;
			}
			//change previous to current
			previous = str.charAt(i);
		}
		//if we never set max, we need to return count!
		if(count == str.length())
		{
			return count;
		}
		return max;
	}
	
	/**
	 * Given a non-empty String str of digits, return the digit that occurs most 
	 * frequently in str.  In case of a tie, return the lowest digit amongst the most
	 * frequently occurring ones.
	 * "001112222" => 2,
	 * "9876543210" => 0,
	 * "4433221" => 2,
	 * "4431113444411" => 4,
	 * "9" => 9 
	 * Note that if the lowest digit is 4 you should return 4 
	 * and not the char '4'.
	 * 
	 * Give the following in terms of n, where n is the length of str.
	 * (Worst-Case) Runtime: theta(n)
	 * (Worst-Case) Space used: theta(1)
	 * 
	 * @param str the non-empty input String
	 * @return the digit that occurs most frequently; uses lowest digit to break ties 
	 */
	public static int mostFrequent(String str)
	{
		//initialize a count variable and max variable
		int max = 0;
		//initialize an integer to return
		int mostFrequent = 0;
		//loop through the number 9 to 0
		for(int i = 9; i >= 0; i--)
		{
			//initialize count variable
			int count = 0;
			//loop through the string
			for(int j = 0; j < str.length(); j++)
			{
				//if the character is equal to the number, increment count
				if(Character.getNumericValue(str.charAt(j)) == i)
				{
					count += 1;
				}
			}
			//if count is greater than max
			//reset variables
			if(count >= max)
			{
				max = count;
				mostFrequent = i;
			}
		}
		return mostFrequent;
	}
}