import static org.junit.Assert.*;

import org.junit.Test;

public class ReachedTests
{
	@Test
	public void oneNodeTest()
	{
		assertEquals(1,Functions.numReachable(1,0,new int[0][2]));
	}
	@Test
	public void noRoadTest()
	{
		assertEquals(1,Functions.numReachable(2,0,new int[0][2]));		
		assertEquals(1,Functions.numReachable(2,1,new int[0][2]));		
	}
	@Test
	public void complete4Nodes()
	{
		int[][] rs = { {0,1},{0,2},{0,3},{1,2},{1,3},{2,3} };
		assertEquals(4, Functions.numReachable(4, 0, rs));
		assertEquals(4, Functions.numReachable(4, 3, rs));
	}
	@Test
	public void someIslands()
	{
		int[][] rs = {{0,1},{2,3}};
		assertEquals(2,Functions.numReachable(5, 0, rs));
		assertEquals(2,Functions.numReachable(5, 1, rs));
		assertEquals(2,Functions.numReachable(5, 2, rs));
		assertEquals(2,Functions.numReachable(5, 3, rs));
		assertEquals(1,Functions.numReachable(5, 4, rs));
	}
	@Test
	public void bigPath()
	{
		int[][] rs = new int[99999][2];
		for (int i = 0; i < rs.length; ++i)
		{
			rs[i][0] = i;
			rs[i][1] = i+1;
		}
		
		long time = System.nanoTime();
		assertEquals(100000,Functions.numReachable(100000, 0, rs));
		assertEquals(100000,Functions.numReachable(100000, 50000, rs));
		assertEquals(100000,Functions.numReachable(100000, 99999, rs));
		double elap = (System.nanoTime() - time)*1e-9;
		if (elap > 3)
			throw new AssertionError("Time must be less than 3s: "+ elap+"s");
	}
}
