import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Random;

import org.junit.Test;

public class BinaryCountTests
{
	@Test
	public void testCount1()
	{
		BinaryTreeNode<String> s = TestUtils.makeExpressionTree("1 1 + 1 1 * 1 1 - 1 1 / + + +");
		assertEquals(8, Functions.count(s, "1"));
		assertEquals(4, Functions.count(s, "+"));
		assertEquals(1, Functions.count(s, "*"));
		assertEquals(1, Functions.count(s, "-"));
		assertEquals(1, Functions.count(s, "/"));
		assertEquals(0, Functions.count(s, "2"));
	}

	@Test
	public void testCount2()
	{
		BinaryTreeNode<String> s = TestUtils.makeExpressionTree("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 + + + + + + + + + + + + + + +");
		assertEquals(16, Functions.count(s, "1"));
		assertEquals(15, Functions.count(s, "+"));
		assertEquals(0, Functions.count(s, "*"));
	}
	
	@Test
	public void testCount3()
	{
		BinaryTreeNode<Integer> n = new BinaryTreeNode<>(40,null,null);
		assertEquals(1, Functions.count(n, 40));
		assertEquals(0, Functions.count(n, 2));
	}
	
	@Test
	public void testCount4()
	{
		Random ran = new Random(1);
		for (int i = 0; i < 10; ++i)
		{
			HashMap<Integer,Integer> counts = new HashMap<>();
			BinaryTreeNode<Integer> n = TestUtils.makeRandomBinTree(10000, ran, counts, 1000);
			for(HashMap.Entry<Integer,Integer> e : counts.entrySet())
				assertEquals(e.getValue().intValue(), Functions.count(n, e.getKey()));
		}
	}
}
