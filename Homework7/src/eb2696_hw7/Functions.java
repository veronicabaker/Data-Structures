import java.util.ArrayList;
import java.util.Stack;

public class Functions
{
	/**
	 * Your company has n employees (numbered 0 through n-1).  
	 * You are given an array bosses of length n
	 * that gives each employee's immediate boss.  The CEO of 
	 * the company will have a boss of -1 and will always be in index 0.  
	 * It is guaranteed 
	 * that the hierarchy of employees will form a tree (not 
	 * necessarily binary).  Each employee also has a name given
	 * in the corresponding array names of length n.  You must return
	 * the number of people that have someone named query as a superior.
	 * A superior is a boss, or a boss of a boss, or boss of a boss of a boss, etc.
	 * In other words, a superior is your boss, or an ancestor of your boss in the tree.
	 * Note: There could be many employees with the same name, and many 
	 * employees named query.  There could be no employees named query.
	 * Hint: First build the tree, the traverse it.
	 * 
	 * @param bosses each employee's boss, with -1 for the CEO
	 * @param names index i stores the name of employee i
	 * @param query the name for whom we are looking for subordinates
	 * @return the number of employees with query as superior
	 */
	public static int numEmployees(int[] bosses, String[] names, String query)
	{
		int subordinates = 0;
		ArrayList<ArrayList<Integer>> employees = new ArrayList<>();
		for(int i = 0; i < names.length; i++)
		{
			employees.add(new ArrayList<Integer>());
		}
		for(int i = 1; i < names.length; i++)
		{
			employees.get(bosses[i]).add(i);
		}
		Stack<Integer> index = getIndexHelp(names, query);
		while(!(index.isEmpty()))
		{
			Stack<ArrayList<Integer>> stack = new Stack<ArrayList<Integer>>();
			int employeeIndex = index.pop();
			if(employeeIndex == 0)
			{
				return names.length - 1;
			}
			ArrayList<Integer> employee = employees.get(employeeIndex);
			if(employee.size() == 1)
			{
				stack.push(employees.get((employee.get(0))));
			}
			for(int i = 1; i < employee.size(); i++)
			{
				stack.push(employees.get(employee.get(i)));
			}
			subordinates += numEmployeesHelp(stack, employees, employee);
		}
		return subordinates;
	}
	

	
	public static int numEmployeesHelp(Stack<ArrayList<Integer>> stack, ArrayList<ArrayList<Integer>> employees, ArrayList<Integer> employee)
	{
		int subordinates = 0;
		subordinates += employee.size();
		while(!(stack.isEmpty()))
		{
			employee = stack.pop();
			subordinates += employee.size();
			for(int i = 0; i < employee.size(); i++)
			{
				stack.push(employees.get(employee.get(i)));
			}
		}
		return subordinates;
	}
	
	
	
	public static Stack<Integer> getIndexHelp(String[] names, String name)
	{
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < names.length; i++)
		{
			if(names[i] == name)
			{
				stack.push(i);
			}
		}
		return stack;
	}
	
	
	/**
	 * (Extra Credit)
	 * You are given an array with 2n+1 ints.  You will return an array with n+1 ints.
	 * Index k of the returned array should be the median of the first 2k+1 elements of arr.
	 * Hint: This can be implemented using heaps/PriorityQueues.
	 *  
	 * @param arr array of ints
	 * @return the array of medians as described above
	 */
	public static int[] medians(int[] arr)
	{
		return null;
	}
}
