import static org.junit.Assert.*;

import org.junit.Test;

public class EvalTests
{
	static final double EPS = 1e-9;
	@Test
	public void evalTest1()
	{
		BinaryTreeNode<String> n = TestUtils.makeExpressionTree("1 2 + 8 * 9 4 3 * - +");
		assertEquals(21.0, Functions.eval(n),EPS);
		assertEquals(24.0, Functions.eval(n.getLeft()), EPS);
		assertEquals(-3.0, Functions.eval(n.getRight()), EPS);
	}
	@Test
	public void evalTest2()
	{
		BinaryTreeNode<String> n = TestUtils.makeExpressionTree("1.0 2.0 3.0 + +");
		assertEquals(6.0, Functions.eval(n),EPS);
	}
	@Test
	public void evalTest3()
	{
		BinaryTreeNode<String> n  = TestUtils.makeExpressionTree("1.0 2.0 3 4 5 6 7 8 9 10 11 - - - - - - - - - /");
		assertEquals(-.2,Functions.eval(n),EPS);
	}
	@Test
	public void evalTest4() 
	{
		BinaryTreeNode<String> n = TestUtils.makeExpressionTree("-1.0");
		assertEquals(-1,Functions.eval(n),EPS);	
	}	
	@Test
	public void evalTest5()
	{
		BinaryTreeNode<String> n = TestUtils.makeExpressionTree("1 2 + 8 * 9 4 3 * - + 1 /");
		assertEquals(21.0, Functions.eval(n),EPS);
	}
}
