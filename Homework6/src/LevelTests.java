import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class LevelTests
{
	@Test
	public void testLevel1()
	{
		TreeNode<String> s = TestUtils.makeExpressionArbTree("1 1 + 1 1 * 1 1 - 1 1 / + + +");
		String[][] strs = {{"+"},{"+","+"},{"1","1","*","+"},{"1","1","-","/"},{"1","1","1","1"},{}};
		for (int i = 0; i < strs.length; ++i)
		{
			assertEquals(new ArrayList<>(Arrays.asList(strs[i])), Functions.getLevel(i, s));
		}
	}
	@Test
	public void testLevel2()
	{
		TreeNode<String> s = TestUtils.makeExpressionArbTree("1 2 + 8 * 9 4 3 * - +");
		String[][] strs = {{"+"},{"*","-"},{"+","8","9","*"},{"1","2","4","3"},{},{}};
		for (int i = 0; i < strs.length; ++i)
		{
			assertEquals(new ArrayList<>(Arrays.asList(strs[i])), Functions.getLevel(i, s));
		}
	}
	@Test
	public void testLevel3()
	{
		TreeNode<String> s = new TreeNode<>("Hmm",null,null);
		String[][] strs = {{"Hmm"},{},{},{},{},{}};
		for (int i = 0; i < strs.length; ++i)
		{
			assertEquals(new ArrayList<>(Arrays.asList(strs[i])), Functions.getLevel(i, s));
		}
	}
}
