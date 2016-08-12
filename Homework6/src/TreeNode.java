/**
 * Node of a tree that can have an arbitrary number of 
 * children.  Each node has a link to its first child, and 
 * each child points at its next sibling.  The last child
 * has null for its next sibling.  A leaf will have null
 * for its first child.
 *
 * @param <T>
 */
public class TreeNode<T> 
{
	private T value;
	private TreeNode<T> firstChild;
	private TreeNode<T> nextSibling;
	
	public TreeNode(T t, TreeNode<T> f, TreeNode<T> n)
	{
		value = t;
		firstChild = f;
		nextSibling = n;
	}
	
	public T getValue() { return value; }
	public TreeNode<T> getFirstChild() { return firstChild; }
	public void setFirstChild(TreeNode<T> f)
	{
		firstChild = f;
	}
	public TreeNode<T> getNextSibling() { return nextSibling; }
	public void setNextSibling(TreeNode<T> n)
	{
		nextSibling = n;
	}
}
