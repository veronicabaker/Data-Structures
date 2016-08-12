import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeesTests
{
	@Test
	public void simpleTest()
	{
		int[] bosses = {-1, 0, 0, 0, 0};
		String[] names = {"A","B","C","D","E"};
		//"A" is boss, everyone works for "A"
		assertEquals(4,Functions.numEmployees(bosses, names, "A"));
		assertEquals(0,Functions.numEmployees(bosses, names, "B"));
		assertEquals(0,Functions.numEmployees(bosses, names, "F"));
	}

	@Test
	public void simpleTestAA()
	{
		int[] bosses = {-1, 0, 0, 0, 0};
		String[] names = {"AA","AA","AA","AA","AA"};
		//Everyone is AA
		assertEquals(4,Functions.numEmployees(bosses, names, "AA"));
	}

	@Test
	public void otherTest()
	{
		int[] bosses = {-1, 5, 5, 5, 5, 0};
		String[] names = {"A","B","C","D","E","F"};
		assertEquals(5,Functions.numEmployees(bosses, names, "A"));
		assertEquals(4,Functions.numEmployees(bosses, names, "F"));
		assertEquals(0,Functions.numEmployees(bosses, names, "C"));
	}
	
	@Test
	public void binaryTest()
	{
		int[] bosses = {-1, 0, 0, 1, 1, 2, 2};
		String[] names = {"A","B","C","D","E","F","G"};
		//A is boss of B,C.  D,E work for B and F,G work for C.
		assertEquals(6,Functions.numEmployees(bosses, names, "A"));
		assertEquals(2,Functions.numEmployees(bosses, names, "B"));
		assertEquals(2,Functions.numEmployees(bosses, names, "C"));
		assertEquals(0,Functions.numEmployees(bosses, names, "D"));
		assertEquals(0,Functions.numEmployees(bosses, names, "E"));
		assertEquals(0,Functions.numEmployees(bosses, names, "F"));
		assertEquals(0,Functions.numEmployees(bosses, names, "G"));
	}
	
	@Test
	public void multiNameTest()
	{
		int[] bosses = {-1, 0, 0, 0, 0, 1,2,3,4};
		String[] names = {"A","B","B","C","C","D","E","F","G"};
		assertEquals(8,Functions.numEmployees(bosses, names, "A"));
		assertEquals(2,Functions.numEmployees(bosses, names, "B"));
		assertEquals(2,Functions.numEmployees(bosses, names, "C"));
		assertEquals(0,Functions.numEmployees(bosses, names, "D"));		
	}
	
	@Test
	public void towerTest()
	{
		int N = 100000;
		int[] bosses = new int[N];
		String[] names = new String[N];
		for (int i = 0; i < N; ++i)
		{
			bosses[i] = i-1;
			names[i] = i+"";
		}
		long time = System.nanoTime();
		for (int i = 0; i < 10; ++i)
		{
			System.out.println(i);
			assertEquals(N-i-1,Functions.numEmployees(bosses, names, names[i]));
		}
		double elapsed = (System.nanoTime() - time)*1e-9;
		if (elapsed > 3)
			throw new AssertionError("Time must be less than 3s: "+elapsed+"s");
	}
}
