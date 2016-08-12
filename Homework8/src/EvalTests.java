import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EvalTests
{
	public static final double EPS = 1e-4;
	
	@Test
	public void simpleTests()
	{
		assertEquals(9.0,Functions.eval("9"),EPS);
		assertEquals(45.754,Functions.eval("45.754"),EPS);
	}
	@Test
	public void oneOpTests()
	{
		assertEquals(9.0,Functions.eval("(3 * 3.0)"),EPS);
		assertEquals(63.0,Functions.eval("(7.00 * 9.000)"),EPS);
		assertEquals(63.189,Functions.eval("(8.5 * 7.434)"),EPS);	
	}
	@Test
	public void twoOpTests()
	{
		assertEquals(9.0,Functions.eval("(3 + (2 * 3.0))"),EPS);
		assertEquals(39.65,Functions.eval("((9.1 * 2.3) + (7.8 * 2.4))"),EPS);
	}
	@Test
	public void largerTests()
	{
		assertEquals(14.517838,Functions.eval("((((1.686547 + 1.044910) + 0.503085) / (((0.422388 * 2.288513) - (1.302888 * 0.445861)) - (1.077374 - 0.447083))) / ((0.255184 - 0.341905) + (0.235461 - 1.059764)))"),EPS); 
		assertEquals(-6.727699,Functions.eval("(((2.121585 + 0.745477) * 0.744208) * ((((0.340487 - 0.962523) + (0.527692 - 1.916846)) - (1.834783 + (0.251748 - 0.659739))) + (0.232024 / 0.814402)))"),EPS); 
		assertEquals(-1.333594,Functions.eval("(((((2.331027 / 0.476829) * (0.313737 * 1.206786)) + (1.415443 - 0.264072)) - (1.675538 * 1.734653)) + (0.395336 - ((0.690423 + 1.368448) - 0.234157)))"),EPS); 
		assertEquals(-16.810063,Functions.eval("(0.164620 - (((0.847637 / (0.603107 * 0.165571)) - ((0.301439 - 0.461727) * 0.184918)) * ((0.315444 * 0.918053) - (0.162248 - (1.509084 + 0.356337)))))"),EPS); 
	}
}
