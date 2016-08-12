import static org.junit.Assert.*;

import org.junit.Test;

public class NumValuesTests
{
	@Test
	public void testNumValues1()
	{
		assertEquals(3, Functions.numValues(1, 3));
		assertEquals(4, Functions.numValues(1, 2));
		assertEquals(2, Functions.numValues(1, 4));
	}
	
	@Test
	public void testNumValues2()
	{
		assertEquals(1, Functions.numValues(2, 10));
		assertEquals(3, Functions.numValues(3, 10));
	}
	
	@Test
	public void testNumValues3()
	{
		int[] arr = new int[18];
		for (int i = 1000; i <= 9999; ++i)
		{
			int a = i%10, b = i/10%10, c = i/100%10, d = i/1000;
			boolean bad = Math.abs(a-b) > 1 || Math.abs(b-c) > 1 || Math.abs(d-c) > 1;
			for (int j = 1; j < arr.length; ++j) if (!bad && i%j == 0) arr[j]++;
		}
		for (int j = 1; j < arr.length; ++j) assertEquals(arr[j], Functions.numValues(4, j));
	}
	@Test
	public void testNumValues4()
	{
		long time = System.nanoTime();
		assertEquals(27583372, Functions.numValues(15,1));
		assertEquals(13777016, Functions.numValues(15,2));
		assertEquals(9194449, Functions.numValues(15,3));
		assertEquals(27638, Functions.numValues(15,1007));
		double elapsed = (System.nanoTime() - time)*1e-9;
		if (elapsed > 10)
			throw new AssertionError("Time must be less than 10s: "+elapsed);
	}
}
