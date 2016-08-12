import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountExtraTests
{
	@Test
	public void testCount1()
	{
		assertEquals(2, Functions.countValidRowsExtra(1));
	}

	@Test
	public void testCount3()
	{
		assertEquals(7, Functions.countValidRowsExtra(3));
	}

	@Test
	public void testCount6()
	{
		assertEquals(44, Functions.countValidRowsExtra(6));
	}
	@Test
	public void testCount20()
	{
		long time = System.nanoTime();
		assertEquals(223317, Functions.countValidRowsExtra(20));
		double elapsed = (System.nanoTime()-time)*1e-9;
		if (elapsed > 2)
			throw new AssertionError(String.format("Required time must be less than 2s, got %.2fs",elapsed));
	}
}
