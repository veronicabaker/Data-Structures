import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Random;

public class TestUtils
{
	//Creates an expression tree from a postfix expression
	public static BinaryTreeNode<String> makeExpressionTree(String postfix)
	{
		//Used as a stack
		ArrayDeque<BinaryTreeNode<String>> s = new ArrayDeque<>();
		String[] toks = postfix.split("\\s+");
		String operator = "+*-/";
		for (String tok : toks)
		{
			if (operator.indexOf(tok) != -1)
			{
				BinaryTreeNode<String> right = s.pop();
				BinaryTreeNode<String> left = s.pop();
				s.push(new BinaryTreeNode<>(tok,left,right));
			} else s.push(new BinaryTreeNode<>(tok,null,null));
		}
		return s.pop();
	}
	
	//Creates an expression tree from a postfix expression
	public static TreeNode<String> makeExpressionArbTree(String postfix)
	{
		//Used as a stack
		ArrayDeque<TreeNode<String>> s = new ArrayDeque<>();
		String[] toks = postfix.split("\\s+");
		String operator = "+*-/";
		for (String tok : toks)
		{
			if (operator.indexOf(tok) != -1)
			{
				TreeNode<String> right = s.pop();
				TreeNode<String> left = s.pop();
				left.setNextSibling(right);
				s.push(new TreeNode<>(tok,left,null));
			} else s.push(new TreeNode<>(tok,null,null));
		}
		return s.pop();
	}

	public static <K> void put(K key, HashMap<K,Integer> map)
	{
		if (!map.containsKey(key)) map.put(key, 0);
		map.put(key, map.get(key)+1);
	}
	
	public static BinaryTreeNode<Integer> makeRandomBinTree(int size, Random ran, HashMap<Integer,Integer> counts, int max)
	{
		if (size == 0) return null;
		int a = ran.nextInt(max);
		put(a,counts);
		if (size == 1) return new BinaryTreeNode<>(a,null,null);		
		int left = ran.nextInt(size-1), right = size - 1 - left;
		BinaryTreeNode<Integer> leftTree = makeRandomBinTree(left, ran, counts, max);
		BinaryTreeNode<Integer> rightTree = makeRandomBinTree(right, ran, counts, max);
		return new BinaryTreeNode<>(a,leftTree,rightTree);
	}
	
	public static TreeNode<Integer> makeRandomTree(int size, Random ran, HashMap<Integer,Integer> counts, int max, boolean isRoot)
	{
		if (size == 0) return null;
		int a = ran.nextInt(max);
		put(a,counts);
		if (size == 1) return new TreeNode<>(a,null,null);		
		int left = isRoot ? size-1 : ran.nextInt(size-1), right = size - 1 - left;
		TreeNode<Integer> firstChild = makeRandomTree(left, ran, counts, max, false);
		TreeNode<Integer> nextSibling = makeRandomTree(right, ran, counts, max, false);
		return new TreeNode<>(a,firstChild,nextSibling);
	}

	public static BinaryTreeNode<String> makeRandomExpTree(int size, Random ran)
	{
		if (size == 0) return null;
		double d = ran.nextGaussian();
		if (size == 1) return new BinaryTreeNode<>(d+"",null,null);		
		int left = 1, right = 1;
		if (size > 2)
		{
			left = ran.nextInt(size-2)+1; 
			right = size - 1 - left;
		}
		BinaryTreeNode<String> leftTree = makeRandomExpTree(left, ran);
		BinaryTreeNode<String> rightTree = makeRandomExpTree(right, ran);
		return new BinaryTreeNode<>("+*-/".charAt(ran.nextInt(4))+"",leftTree,rightTree);
	}
	
	public static SpecialBSTNode<Integer> makeRandomBST(int[] arr, int start, int end, Random ran)
	{
		if (start > end) return null;
		if (start == end) return new SpecialBSTNode<>(arr[start],null,null,null,1);
		int rootIdx = start+ran.nextInt(end-start+1);
		SpecialBSTNode<Integer> left = makeRandomBST(arr,start,rootIdx-1,ran);
		SpecialBSTNode<Integer> right = makeRandomBST(arr,rootIdx+1,end,ran);
		int ls = left == null ? 0 : left.getSize();
		int rs = right == null ? 0 : right.getSize();
		SpecialBSTNode<Integer> root = new SpecialBSTNode<>(arr[rootIdx], left, right, null, ls + rs + 1);
		if (left != null) left.setParent(root);
		if (right != null) right.setParent(root);
		return root;
	}
}
