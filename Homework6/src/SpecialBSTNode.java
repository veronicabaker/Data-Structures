
public class SpecialBSTNode<T extends Comparable<? super T>> 
{
	//Value in node
	private T value;
	//Left child (less than node)
	private SpecialBSTNode<T> left;
	//Right child (greater than node)
	private SpecialBSTNode<T> right; 
	//Parent
	private SpecialBSTNode<T> parent;
	//Number of nodes in subtree rooted at this node
	private int size;
	
	public SpecialBSTNode(T v, 
			SpecialBSTNode<T> l,
			SpecialBSTNode<T> r,
			SpecialBSTNode<T> p,
			int s)
	{
		value = v;
		left = l;
		right = r;
		parent = p;
		size = s;
	}
	public T getValue() { return value; }
	public SpecialBSTNode<T> getLeft() { return left; }
	public SpecialBSTNode<T> getRight() { return right; }
	public SpecialBSTNode<T> getParent() { return parent; }
	public int getSize() { return size; }
	public void setLeft(SpecialBSTNode<T> l) { left = l; }
	public void setRight(SpecialBSTNode<T> r) { right = r; }
	public void setParent(SpecialBSTNode<T> p) { parent = p;}
}
