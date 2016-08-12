import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class NetworkTests
{
	static final double EPS = 1e-4;
	@Test
	public void testPath()
	{
		String[] links = {
				"0 1 3",
				"1 2 3",
				"2 3 3",
				"3 4 3",
				"4 5 3"
		};
		assertEquals(15,Functions.minCostNetwork(6, links),EPS);
	}
	@Test
	public void testCycle()
	{
		String[] links = {
				"0 1 3",
				"1 2 3",
				"2 3 3",
				"3 4 3",
				"4 5 3",
				"5 4 3"
		};
		assertEquals(15,Functions.minCostNetwork(6, links),EPS);
	}
	@Test
	public void testStar()
	{
		String[] links = {
				"1 0 3",
				"2 0 4",
				"3 0 5",
				"4 0 6",
				"5 0 7"
		};
		assertEquals(25,Functions.minCostNetwork(6, links),EPS);
	}
	@Test
	public void testComplete()
	{
		ArrayList<String> ls = new ArrayList<>();
		for (int i = 0; i < 20; ++i)
			for (int j = 0; j < i; ++j)
				ls.add(i+" "+j+" "+1);
		assertEquals(19,Functions.minCostNetwork(20, ls.toArray(new String[0])),EPS);
	}
	@Test
	public void testComplete2()
	{
		ArrayList<String> ls = new ArrayList<>();
		for (int i = 0; i < 100; ++i)
			for (int j = 0; j < i; ++j)
			{
				double c = (i*j)%17+1;
				ls.add(i+" "+j+" "+c);
			}
		assertEquals(99,Functions.minCostNetwork(100, ls.toArray(new String[0])),EPS);
	}
	@Test
	public void testComplete3()
	{
		Numbers num = new Numbers(1);
		ArrayList<String> ls = new ArrayList<>();
		for (int i = 0; i < 100; ++i)
			for (int j = 0; j < i; ++j)
			{
				ls.add(i+" "+j+" "+(num.get(10000)+1));
			}
		assertEquals(12123,Functions.minCostNetwork(100, ls.toArray(new String[0])),EPS);
	}
}
