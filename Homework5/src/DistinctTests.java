import static org.junit.Assert.*;

import org.junit.Test;

public class DistinctTests
{
	@Test
	public void distinctTest1()
	{
		long[] vals = {1,1,2,1,2,3,1,2,3,4};
		assertEquals(4,Functions.getDistinctLength(vals));
	}
	
	@Test
	public void distinctTest2()
	{
		long[] vals = {-1,0,1,2,3,4,5,6,7,8,9};
		assertEquals(vals.length,Functions.getDistinctLength(vals));
	}

	@Test
	public void distinctTest3()
	{
		long[] vals = {4,3,2,1,1,1,2,3,4,4,4,3,2,1,0,1,1,2,3,4};
		assertEquals(5,Functions.getDistinctLength(vals));
	}

	@Test
	public void distinctTest4()
	{
		long[] vals = {4,3,2,1,1,1,2,3,4,4,4,3,2,1,0,1,1,2,3,4};
		assertEquals(5,Functions.getDistinctLength(vals));
	}

	@Test
	public void distinctTest5()
	{
		long[] vals = {1,2,3,4,1,5};
		assertEquals(5,Functions.getDistinctLength(vals));
	}

	@Test
	public void distinctTest6()
	{
		long[] vals = {1,2,3,4,1,5,1,4,3,2,1};
		assertEquals(5,Functions.getDistinctLength(vals));
	}
	
	@Test
	public void distinctTest7()
	{
		long[] vals = {1,2,3,4,5,4,1,2,3,2,6,1,2,3,4,1,5,6};
		assertEquals(6,Functions.getDistinctLength(vals));
	}

	@Test
	public void distinctTest8()
	{
		long[] vals = {1,2,3,4,5,4,1,2,3,2,6,1,2,3,4,1,5};
		assertEquals(5,Functions.getDistinctLength(vals));
	}
	
	@Test
	public void distinctTest9() throws Exception
	{
		long[] vals = new long[1000000];
		for (int i = 0, n = 0; n < vals.length; ++i)
			for (int j = 0; j <= i && n < vals.length; ++j)
				vals[n++] = j;
		long time = System.nanoTime();
		assertEquals(1413,Functions.getDistinctLength(vals));
		double elapsed = (System.nanoTime()-time)*1e-9;
		if (elapsed > 2) {
			String s = String.format("Time must be less than 2s, your was %.2fs", elapsed);
			throw new AssertionError(s);
		}
	}
	
	@Test
	public void distinctTest10() throws Exception
	{
		long[] vals = new long[1000000];
		for (int i = 0; i < vals.length/2; ++i)
			vals[i] = vals[vals.length-i-1] = i;
		long time = System.nanoTime();
		assertEquals(500000,Functions.getDistinctLength(vals));
		double elapsed = (System.nanoTime()-time)*1e-9;
		if (elapsed > 2) {
			String s = String.format("Time must be less than 2s, your was %.2fs", elapsed);
			throw new AssertionError(s);
		}
	}
}
