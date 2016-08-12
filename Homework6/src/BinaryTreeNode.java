/**
 * Node of a binary tree as discussed in class. 
 *
 * @param <T>
 */
public class BinaryTreeNode<T> 
{
	private T value;
	private BinaryTreeNode<T> left, right;
	
	public BinaryTreeNode(T v, 
			BinaryTreeNode<T> l, BinaryTreeNode<T> r)
	{
		value = v;
		left = l;
		right = r;
	}
	public T getValue() { return value; }
	public void setValue(T t) { value = t; }
	public BinaryTreeNode<T> getLeft() { return left; }
	public void setLeft(BinaryTreeNode<T> l) { left = l; }
	public BinaryTreeNode<T> getRight() { return right; }
	public void setRight(BinaryTreeNode<T> r) { right = r; }
}
