import java.util.ArrayList;

public class Functions 
{
	/**
	 * Returns the number of occurrences of value in the
	 * binary tree with given root.
	 * 
	 * @param root root of the tree
	 * @param value value to count occurrences of
	 * @return number of occurrences of value in the given tree
	 */
	public static <T> int count(BinaryTreeNode<T> root,T value)
	{
		int count = 0;
		if(root == null)
		{
			return count;
		}
		if(root.getValue().equals(value))
		{
			count += 1;
		}
		count += count(root.getLeft(), value);
		count += count(root.getRight(), value);
		return count;
	}

	/**
	 * Returns the number of occurrences of value in the
	 * tree with given root.
	 * 
	 * @param root root of the tree
	 * @param value value to count occurrences of
	 * @return number of occurrences of value in the given tree
	 */
	public static <T> int count(TreeNode<T> root,T value)
	{
		int count = 0;
		if(root == null)
		{
			return count;
		}
		if(root.getValue().equals(value))
		{
			count += 1;
		}
		
		count += count(root.getFirstChild(), value) + count(root.getNextSibling(), value);
		return count;
	}
	
	/**
	 * Returns a list of all values at depth d in the given
	 * tree.  The order of the values in the list should
	 * be in the order the values would appear (left-to-right)
	 * in a drawing of the tree.  In other words, earlier
	 * siblings before later siblings.  Note, the tree
	 * need not be binary.
	 * 
	 * @param d depth of the nodes to obtain
	 * @param root 
	 * @return returns a list of the values at depth d
	 * of the given tree
	 */
	public static <T> ArrayList<T> getLevel(int d,
			TreeNode<T> root)
	{
		ArrayList<T> level = new ArrayList<>();
		if(root == null)
		{
			return level;
		}
		if(d == 0)
		{
			level.add(root.getValue());
		}
		level.addAll(getLevel(d - 1, root.getFirstChild()));
		level.addAll(getLevel(d, root.getNextSibling()));
		return level;
	}
	
	/**
	 * Evaluates the given expression tree.  Each node
	 * will either be a leaf storing a double in String form,
	 * or a internal node containing "*","+","-" or "/".
	 * 
	 * @param root root node of expression tree
	 * @return value of expression tree
	 */
	public static double eval(BinaryTreeNode<String> root)
	{
		double value = 0.0;
		if(root == null)
		{
			return value;
		}
		if(operators.contains(root.getValue()))
		{
			if(root.getValue().equals("+"))
			{
				value += eval(root.getLeft()) + eval(root.getRight());
			}
			else if(root.getValue().equals("*"))
			{
				value += eval(root.getLeft()) * eval(root.getRight());
			}
			else if(root.getValue().equals("-"))
			{
				value += eval(root.getLeft()) - eval(root.getRight());
			}
			else if(root.getValue().equals("/"))
			{
				value += eval(root.getLeft()) / eval(root.getRight());
			}
		}
		else
		{
			return Double.parseDouble(root.getValue());
		}
		return value;
	}
	
	static String operators = "+*-/";
	
	/**
	 * Returns a reference to the node in the BST which has
	 * the next largest value (or successor). 
	 * If there is no node with a
	 * larger value, return null.  For example, if the tree
	 * has the value 1,2,3,4,5 and the given node contains 3,
	 * then you should return a reference to the node
	 * with 4.  You may assume all values in the BST will
	 * be distinct. 
	 * 
	 * You should optimize the number of steps required
	 * to find the required node.  Reminder: the nodes you are
	 * given have parent links in them to help you navigate
	 * the tree.  [Hint: Going all the way to the root of the
	 * tree every time you look for the next largest node is
	 * too slow. This shouldn't be recursive.]
	 *  
	 * @param node
	 * @return the node of the BST containing the next
	 * largest value, or null if the current node is largest
	 */
	public static SpecialBSTNode<Integer> succ(
			SpecialBSTNode<Integer> node)
	{
		if(node.getRight() != null)
		{
			SpecialBSTNode<Integer> curr = node.getRight();
			while(curr.getLeft() != null)
			{
				curr = curr.getLeft();
			}
			return curr;
		}
		SpecialBSTNode<Integer> p = node.getParent();
		while(p != null && node.equals(p.getRight()))
		{
			node = p;
			p = p.getParent();
		}
		return p;
	}
	
	/**
	 * Returns the kth element (ordered by value) of the BST rooted
	 * at the given node.  Node that each SpecialBSTNode
	 * stores the size of the subtree rooted at that node.
	 * You may assume all values in the BST will be 
	 * distinct.  You may assume k is between 0 and n-1, 
	 * inclusive, where n is the size of the BST.
	 * 
	 * Your worst case runtime should depend only on the
	 * height of the BST (Theta(h) where h is the height).
	 * 
	 * @param k 
	 * @param root root of the BST you must search
	 * @return the kth integer (ordered by value)
	 */
	public static int kthValue(int k,
			SpecialBSTNode<Integer> root)
	{
		int lsize = 0;
		if(root.getLeft() != null)
		{
			lsize = root.getLeft().getSize();
		}
		if(k == lsize)
		{
			return root.getValue();
		}
		if(k <= lsize)
		{
			return kthValue(k, root.getLeft());
		}
		return kthValue(k - lsize - 1, root.getRight());
	}
	
	/**
	 * Extra Credit:
	 * Returns how many positive integers satisfy the 
	 * following requirements:
	 * 1) Divisible by m (you can assume m > 0)
	 * 2) Has exactly k decimal digits with the highest 
	 * order digit non-zero (you can assume 1 <= k <= 15).
	 * 3) Each digit differs by at most 1 from each of its
	 * neighboring digits.
	 * 
	 * For example, 5434545 satisfies the requirements
	 * with m = 5 and k = 7.  On the other hand,
	 * 012345 and 512345 do not satisfy the requirements
	 * when m = 5 and k = 6.
	 * 
	 * Note: You will need longs to represent the numbers.
	 * 
	 * @param k number of digits
	 * @param m required mod
	 * @return the number of positive integers that 
	 * satisfy the requirements.
	 */
	public static int numValues(int k, int m)
	{
		return 0;
	}	
}
