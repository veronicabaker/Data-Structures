import static org.junit.Assert.*;

import org.junit.Test;

public class MinStepsTests
{
	@Test
	public void easyTests()
	{
		assertEquals(2,Functions.minSteps(new int[]{10, 10},10));
		assertEquals(-1,Functions.minSteps(new int[]{10, 10},11));
		assertEquals(6,Functions.minSteps(new int[]{10, 11},30));
		assertEquals(3,Functions.minSteps(new int[]{10, 11},1));
	}
	@Test
	public void harderTests()
	{		
		assertEquals(5,Functions.minSteps(new int[]{100, 99},98));
		assertEquals(197,Functions.minSteps(new int[]{100, 99},50));
		assertEquals(97,Functions.minSteps(new int[]{100, 99},75));
		assertEquals(107,Functions.minSteps(new int[]{47, 67},30));
		assertEquals(85,Functions.minSteps(new int[]{47, 67},31));
		assertEquals(51,Functions.minSteps(new int[]{47, 67},32));
		assertEquals(17,Functions.minSteps(new int[]{47, 67},33));
		assertEquals(15,Functions.minSteps(new int[]{47, 67},34));
		assertEquals(49,Functions.minSteps(new int[]{47, 67},35));
		assertEquals(-1,Functions.minSteps(new int[]{2, 4},99));	
	}
}
