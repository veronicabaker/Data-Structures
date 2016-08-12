import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.HashSet;

public class Functions
{
	/**
	 * You have been given a list of commands that you must execute.  They have 
	 * the following possible forms:
	 * 1) "ADD Name" : adds the given name.  Adding a previously added name does 
	 * nothing.  This doesn't effect the returned ArrayList. 
	 * 2) "GETPOS Name" : gets the position of name assuming all of the added names 
	 * are ordered as in the dictionary, and adds it to the returned ArrayList.  Name 
	 * will have no spaces and will have been previously added.  It is expected that this 
	 * may require Theta(n) time.
	 * 3) "GETPRE Prefix": adds all added names with the given prefix to the returned 
	 * ArrayList in dictionary order.  This should take Theta(lg n + k) where k is the 
	 * number of names added to the returned ArrayList.
	 * 
	 * Hint: Use the Java TreeSet and look at headSet and tailSet.
	 * 
	 * @param commands the commands to execute
	 * @return the outputs from the commands
	 */
	public static ArrayList<String> processCommands(ArrayList<String> commands)
	{
		ArrayList<String> ret = new ArrayList<>();
		TreeSet<String> names = new TreeSet<>();
		for(int i = 0; i < commands.size(); i++)
		{
			String[] command = commands.get(i).split(" ");
			if(command[0].equals("GETPOS"))
			{
				int count = 0;
				for(Iterator<String> it = names.iterator(); it.hasNext();)
				{
					if(command[1].equals(it.next()))
					{
						ret.add(count + "");
						break;
					}
					count += 1;
				}
			}
			else if(command[0].equals("GETPRE"))
			{
				SortedSet<String> tail = names.tailSet(command[1]);
				Iterator<String> it = tail.iterator();
				while(it.hasNext())
				{
					String element = it.next();
					if(element.substring(0, command[1].length()).equals(command[1]))
					{
						ret.add(element);
					}
					else
					{
						break;
					}
				}
			}
			else
			{
				names.add(command[1]);
			}
		}
		return ret;
	}
	
	/**
	 * You have been given information on n students.  Each element of studentInfo 
	 * has the form "ID,Name,Credits".  Sort studentInfo using the following criteria:
	 * 1) Higher credits should come before lower credits
	 * 2) Break ties in credits with ID, with lower ID coming before higher ID
	 * 3) Break ties in ID with Name in dictionary order.
	 * You may assume ID,Credits are non-negative integers and Name has no 
	 * commas but may have spaces.
	 * 
	 * @param studentInfo as described above
	 */
	public static void sort(ArrayList<String> studentInfo)
	{
		int low = 0;
		int high = studentInfo.size() - 1;
		quickSort(studentInfo, low, high);
 	}
	
	public static void quickSort(ArrayList<String> arr, int low, int high)
	{
		if(arr == null || arr.size() ==0)
		{
			return;
		}
		if(low >= high)
		{
			return;
		}
		int middle = low + (high - low) / 2;
		String pivot = arr.get(middle);
		int i = low;
		int j = high;
		while(i <= j)
		{
			String[] iInfo = arr.get(i).split(",");
			String [] jInfo = arr.get(j).split(",");
			String[] pivotInfo = pivot.split(",");
			while(pivotInfo[2].compareTo(iInfo[2]) > 0
					|| pivotInfo[2].compareTo(iInfo[2]) == 0
					&& pivotInfo[0].compareTo(iInfo[0]) > 0
					|| pivotInfo[2].compareTo(iInfo[2]) == 0
					&& pivotInfo[0].compareTo(iInfo[0]) == 0
					&& pivotInfo[1].compareTo(iInfo[1]) > 1)
			{
				iInfo = arr.get(++i).split(",");
			}
			while(jInfo[2].compareTo(pivotInfo[2]) > 0
						|| jInfo[2].compareTo(pivotInfo[2]) == 0
						&& jInfo[0].compareTo(pivotInfo[0]) > 0
						|| jInfo[2].compareTo(pivotInfo[2]) == 0
						&& jInfo[0].compareTo(pivotInfo[0]) == 0
						&& jInfo[1].compareTo(pivotInfo[1]) > 1)
			{
				jInfo = arr.get(--j).split(",");
			}
			if(i <= j)
			{
				String temp = arr.get(i);
				arr.set(i, arr.get(j));
				arr.set(j, temp);
				i++;
				j--;
			}		
			if(low < j)
			{
				quickSort(arr, low, j);
			}
			if(high > i)
			{
				quickSort(arr, i, high);
			}
		}
	}

	/**
	 * Given an array arr of ints, return how many pairs of indices i,j 
	 * have the following:
	 * 1) i < j
	 * 2) arr[i] + arr[j] = k
	 * You may assume all elements of arr are distinct.  
	 * There will be at most 100000 elements in arr.
	 * 
	 * @param arr array of ints
	 * @param k the required sum
	 * @return the number of pairs of indices i<j with arr[i] + arr[j] = k
	 */
	public static int countPairs(int[] arr, int k)
	{
		int count = 0;
		HashSet<Integer> set = new HashSet<>();
		for(int i = 0; i < arr.length; i++)
		{
			int temp = k - arr[i];
			if(set.contains(temp))
			{
				count += 1;
			}
			set.add(arr[i]);
		}
		return count;
	}
	
	
	/**
	 * You are given the n x n adjacency matrix of an unweighted, undirected 
	 * graph with n vertices that is a tree.  Return the height assuming the given
	 * vertex is the root.
	 * 
	 * Since the graph is undirected, you may assume adjMat[i][j] == adjMat[j][i].
	 * We also guarantee that adjMat[i][i] == false for all i.
	 * 
	 * @param adjMat adjacency matrix of the graph
	 * @param root the number of the vertex that is the root of the tree
	 * @return height of the tree rooted at root
	 */
	
	
	public static int getHeight(boolean[][] adjMat, int root) 
	{
		int count = 0;
		int node = root;
		if(adjMat.length == 1)
		{
			return 0;
		}
		if(node == adjMat[0].length - 1)
		{
			return count + 1;
		}
		else
		{
			for(int i = root; i < adjMat[0].length; i++)
			{
				if(adjMat[node][i])
				{
					count += 1 + getHeight(adjMat, i);
				}
			}
		}
		return count;
	}


	/**
	 * There are n locations numbered 0,1,...,n-1.  You are also given an m-b-2 array hasRoad
	 * containing data on m roads.
	 * If hasRoad[i][0] = x and hadRoad[i][1] = y then locations x and y have a bidirectional road between them.  
	 * Location v is reachable from location w if there is a sequence of roads taking you from
	 * v to w. Return the number of locations reachable from location start (including start). 
	 * 
	 * There will be up to 100,000 vertices and 100,000 edges.  No roads will be repeated.
	 * 
	 * Hint: Build the graph with each location as a vertex and use DFS.  Use an adjacency list.
	 * 
	 * @param n number of locations
	 * @param start starting location
	 * @param hasRoad described above
	 * @return the number of locations reachable from start (including start)
	 */
	public static int numReachable(int n, int start, int[][] hasRoad)
	{
		int count = 0;
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
		for(int i = 0; i < hasRoad.length; i++)
		{
			for(int j = 0; j < hasRoad[i].length; j++)
			{
				adjList.get(hasRoad[i][0]).add(hasRoad[i][1]);
			}
			
		}
		return count;
	}
	
	public static void dfs(ArrayList<ArrayList<Integer>> adj)
	{
		int[] state = new int[adj.size()];
		for(int v = 0; v < adj.size(); ++v)
		{
			dfs(adj, v, state);
		}
	}
	
	public static void dfs(ArrayList<ArrayList<Integer>> adj, int v, int[] state)
	{
		if(state[v] != 0)
		{
			return;
		}
		state[v] = 1;
		for(int w: adj.get(v))
		{
			dfs(adj, w, state);
		}
		state[v] = 2;
	}
	
	
	
	/**
	 * (Extra credit)
	 * You are given a fully parenthesized infix arithmetic expression using the operations
	 * +,*,/,- and non-negative double values.  Evaluate the expression.  To be precise,
	 * a fully parenthesized infix expression is either:
	 * 1) a non-negative double literal
	 * 2) (expr1 op expr2) where expr1,expr2 are fully parenthesized infix 
	 * expressions and op is one of +,*,/,-.  There will be a single space between expr1 and op, and between
	 * op and expr2.
	 * 
	 * The double values will be in decimal form with no sign.
	 * 
	 * @param expr fully parenthesized infix expression
	 * @return the value of the expression
	 */
	public static double eval(String expr)
	{
		return 0;
	}
}

