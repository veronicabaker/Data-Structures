import static org.junit.Assert.*;

import org.junit.Test;

public class CountTests
{
	@Test
	public void testCount1()
	{
		assertEquals(2, Functions.countValidRows(1));
	}

	@Test
	public void testCount3()
	{
		assertEquals(7, Functions.countValidRows(3));
	}

	@Test
	public void testCount6()
	{
		assertEquals(44, Functions.countValidRows(6));
	}
	@Test
	public void testCount20()
	{
		long time = System.nanoTime();
		assertEquals(223317, Functions.countValidRows(20));
		double elapsed = (System.nanoTime()-time)*1e-9;
		if (elapsed > 2)
			throw new AssertionError(String.format("Required time must be less than 2s, got %.2fs",elapsed));
	}
}
