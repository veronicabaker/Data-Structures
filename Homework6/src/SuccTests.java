import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class SuccTests
{
	@Test
	public void testSucc0()
	{
		SpecialBSTNode<Integer> a = new SpecialBSTNode<>(4, null, null, null, 1);
		SpecialBSTNode<Integer> b = new SpecialBSTNode<>(3, null, a, null, 2);
		SpecialBSTNode<Integer> c = new SpecialBSTNode<>(5, b, null, null, 3);
		b.setParent(c);
		a.setParent(b);
		assertEquals(a,Functions.succ(b));
		assertEquals(c,Functions.succ(a));
		assertEquals(null,Functions.succ(c));		
	}
	
	@Test
	public void testSucc1()
	{
		SpecialBSTNode<Integer> left = new SpecialBSTNode<>(1, null, null, null, 1);
		SpecialBSTNode<Integer> right = new SpecialBSTNode<>(10, null, null, null, 1);
		SpecialBSTNode<Integer> root = new SpecialBSTNode<>(5, left, right, null, 3);
		left.setParent(root);
		right.setParent(root);
		assertEquals(root,Functions.succ(left));
		assertEquals(right,Functions.succ(root));
		assertEquals(null,Functions.succ(right));
	}
	@Test
	public void testSucc2()
	{
		//Only left children (root is 19, left child 18, left grandchild 17,...)
		ArrayList<SpecialBSTNode<Integer>> al = new ArrayList<>();
		for (int i = 0; i < 20; ++i) al.add(new SpecialBSTNode<>(i,null,null,null,i+1));
		for (int i = 0; i < 19; ++i) al.get(i).setParent(al.get(i+1));
		for (int i = 1; i < 20; ++i) al.get(i).setLeft(al.get(i-1));
		for (int i = 0; i < 19; ++i) assertEquals(al.get(i+1),Functions.succ(al.get(i)));
		assertEquals(null,Functions.succ(al.get(al.size()-1)));
	}
	@Test
	public void testSucc3()
	{
		//Only right children (root is 0, right child 1, right grandchild 2,...)
		ArrayList<SpecialBSTNode<Integer>> al = new ArrayList<>();
		for (int i = 0; i < 20; ++i) al.add(new SpecialBSTNode<>(19-i,null,null,null,i+1));
		for (int i = 0; i < 19; ++i) al.get(i).setParent(al.get(i+1));
		for (int i = 1; i < 20; ++i) al.get(i).setRight(al.get(i-1));
		for (int i = 1; i < 20; ++i) assertEquals(al.get(i-1),Functions.succ(al.get(i)));
		assertEquals(null,Functions.succ(al.get(0)));
	}
	@Test
	public void testSucc4()
	{
		Random ran = new Random(1);
		int[] arr = new int[1000000];
		for (int i = 1; i < arr.length; ++i) arr[i] = arr[i-1] + ran.nextInt(5) + 1;
		SpecialBSTNode<Integer> node = TestUtils.makeRandomBST(arr, 0, arr.length-1, ran);
		while (node.getLeft() != null) node = node.getLeft();
		long time = System.nanoTime();
		for (int i = 1; i < arr.length; ++i)
		{
			node = Functions.succ(node);
			assertEquals(arr[i],node.getValue().intValue());
		}
		assertEquals(null, Functions.succ(node));
		double elapsed = (System.nanoTime() - time)*1e-9;
		if (elapsed > 5) 
			throw new AssertionError("Time must be less than 5s: "+elapsed);
	}
}
