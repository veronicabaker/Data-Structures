import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class KthValueTests
{
	@Test
	public void testKth1()
	{
		SpecialBSTNode<Integer> left = new SpecialBSTNode<>(1, null, null, null, 1);
		SpecialBSTNode<Integer> right = new SpecialBSTNode<>(10, null, null, null, 1);
		SpecialBSTNode<Integer> root = new SpecialBSTNode<>(5, left, right, null, 3);
		left.setParent(root);
		right.setParent(root);
		assertEquals(1,Functions.kthValue(0, root));
		assertEquals(5,Functions.kthValue(1, root));
		assertEquals(10,Functions.kthValue(2, root));
	}
	@Test
	public void testKth2()
	{
		//Only left children (root is 19, left child 18, left grandchild 17,...)
		ArrayList<SpecialBSTNode<Integer>> al = new ArrayList<>();
		for (int i = 0; i < 20; ++i) al.add(new SpecialBSTNode<>(i,null,null,null,i+1));
		for (int i = 0; i < 19; ++i) al.get(i).setParent(al.get(i+1));
		for (int i = 1; i < 20; ++i) al.get(i).setLeft(al.get(i-1));
		SpecialBSTNode<Integer> root = al.get(19);
		for (int i = 0; i < 20; ++i) assertEquals(i, Functions.kthValue(i, root));
	}
	@Test
	public void testKth3()
	{
		//Only right children (root is 0, right child 1, right grandchild 2,...)
		ArrayList<SpecialBSTNode<Integer>> al = new ArrayList<>();
		for (int i = 0; i < 20; ++i) al.add(new SpecialBSTNode<>(19-i,null,null,null,i+1));
		for (int i = 0; i < 19; ++i) al.get(i).setParent(al.get(i+1));
		for (int i = 1; i < 20; ++i) al.get(i).setRight(al.get(i-1));
		SpecialBSTNode<Integer> root = al.get(19);
		for (int i = 0; i < 20; ++i) assertEquals(i, Functions.kthValue(i, root));
	}
	@Test
	public void testKth4()
	{
		Random ran = new Random(1);
		int[] arr = new int[1000000];
		for (int i = 1; i < arr.length; ++i) arr[i] = arr[i-1] + ran.nextInt(5) + 1;
		SpecialBSTNode<Integer> root = TestUtils.makeRandomBST(arr, 0, arr.length-1, ran);
		long time = System.nanoTime();
		for (int i = 0; i < arr.length; ++i) assertEquals(arr[i], Functions.kthValue(i, root));
		double elapsed = (System.nanoTime() - time)*1e-9;
		if (elapsed > 5) 
			throw new AssertionError("Time must be less than 5s: "+elapsed);
	}

}
