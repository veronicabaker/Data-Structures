import java.util.NoSuchElementException;

//import DoublyLinkedList.Node;

/**
 * Implements a DoublyLinkedList using a sentinel node.
 *
 * @param <T>
 */
public class DoublyLinkedList<T>
{
	/** Type of the nodes in the list */
	public static class Node<S>
	{
		/** Points at the next node in the list */
		private Node<S> next;
		/** Points at the previous node  in the list */
		private Node<S> prev;
		/** Value stored in the node */
		private S value;
		
		private Node(S value, Node<S> prev, Node<S> next)
		{
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
		
		/** Gets the value in the node */
		public S getValue() 
		{ 
			return value; 
		}
		/** Sets the value in the node */
		public void setValue(S s) 
		{
			value = s; 
		}
		/** Gets the next node in the list */
		public Node<S> getNext() 
		{ 
			return next;
		}
		/** Gets the previous node in the list */
		public Node<S> getPrev() 
		{ 
			return prev; 
		}
	}
	
	/** Points at the sentinel node */
	private Node<T> sentinel;
	/** Stores the size of the list */
	private int size;
	
	/**
	 * Constructs an empty DoublyLinkedList with its sentinel node.
	 */
	public DoublyLinkedList() 
	{
		sentinel = new Node<T>(null,null,null);
		sentinel.next = sentinel.prev = sentinel;
	}
		
	/**
	 * Returns the sentinel node.  This allows users of the class to know when to stop iterating. 
	 * @return the sentinel node
	 */
	public Node<T> getSentinel() 
	{ 
		return sentinel; 
	}
	
	/**
	 * Returns the number of Nodes in the list. 
	 * @return the number of Nodes in the list
	 */
	public int size() 
	{ 
		return size; 
	}
	
	/**
	 * Returns if the list is empty 
	 * @return if the list is empty
	 */
	public boolean isEmpty() 
	{ 
		return size == 0; 
	}

	/**
	 * Inserts a new value in the list after the given Node.  For example,
	 * if the list is 1,2,3,4, and listNode is 2 then addAfter(listNode,9) should give
	 * 1,2,9,3,4.
	 * @param listNode the new value should be inserted after this node  
	 * @param t the value to insert
	 * @return the newly created Node
	 */
	public Node<T> addAfter(Node<T> listNode, T t) 
	{
		Node<T> newNode = new Node<T>(t, listNode, listNode.getNext());
		listNode.next.prev = newNode;
		listNode.next = newNode;			
		size++;
		return newNode;
	}

	/**
	 * Inserts a new value in the list before the given Node.  For example,
	 * if the list is 1,2,3,4, and listNode is 2 then addBefore(listNode,9) should give
	 * 1,9,2,3,4.
	 * @param listNode the new value should be inserted before this node 
	 * @param t the value to insert
	 * @return the newly created Node
	 */
	public Node<T> addBefore(Node<T> listNode, T t) 
	{
		Node<T> newNode = new Node<T>(t, listNode.prev, listNode);
		listNode.prev.next = newNode;
		listNode.prev = newNode;
		size++;
		return newNode;
	}
	
	/**
	 * Removes the given Node from the list. 
	 * @param listNode the node to remove from the list
	 * @return the value in the removed node
	 * @throws IllegalArgumentException if listNode is the sentinel
	 */
	public T removeNode(Node<T> listNode) throws IllegalArgumentException
	{
		if(listNode.equals(sentinel))
		{
			throw new IllegalArgumentException();
		}
		else
		{
			listNode.prev.next = listNode.next;
			listNode.next.prev =listNode.prev;
			listNode.prev = null;
			listNode.next = null;
			size--;
		}
		return listNode.getValue();
	}

	/** 
	 * Adds a value to the front of the list.
	 * @param t value to be added
	 */
	public Node<T> addFirst(T t) 
	{ 
		return addBefore(sentinel.next, t);
	}

	/** 
	 * Adds a value to the end of the list.
	 * @param t value to be added
	 * @return the created Node at the end of the list
	 */
	public Node<T> addLast(T t) 
	{
		return addAfter(sentinel.prev, t); 
	}
	
	/**
	 * Removes the value at the front of the list.
	 * @return the value that was removed.
	 * @throws NoSuchElementException if the list is empty
	 */
	public T removeFirst() throws NoSuchElementException
	{
		if(this.isEmpty())
		{
			throw new NoSuchElementException();
		}
		return removeNode(sentinel.next);
	}

	/**
	 * Removes the value at the end of the list.
	 * @return the value that was removed.
	 * @throws NoSuchElementException if the list is empty
	 */
	public T removeLast() throws NoSuchElementException
	{
		if(this.isEmpty())
		{
			throw new NoSuchElementException();
		}
		return removeNode(sentinel.prev);
	}

	/**
	 * Gets the Node at the front of the list.  If empty return the sentinel.
	 * @return the Node at the front of the list
	 */
	public Node<T> getFirst() 
	{
		return sentinel.getNext();
	}

	/**
	 * Gets the Node at the end of the list. If empty return the sentinel.
	 * @return the Node at the end of the list
	 */
	public Node<T> getLast() 
	{
		return sentinel.getPrev();
	}	
	
	/**
	 * Returns the Node at index i (0-based).
	 * @param i the index of the Node to retrieve
	 * @return the node at index i
	 * @throws IndexOutOfBoundException if the i is an invalid index
	 */
	public Node<T> getNode(int i) throws IndexOutOfBoundsException
	{
		if(i > this.size)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			Node<T> current = sentinel.next;
			for(int j = 0; j < i; j++)
			{
				current = current.getNext();
			}
			return current;
		}
	}
	
	/**
	 * Returns false if o is not a (descendent of) DoublyLinkedList or is null.  Otherwise, returns true
	 * if the two lists contain the same values in the same order.  In other words, the two lists must have
	 * the same size, and each corresponding element must have values that are .equals to each other (do not
	 * use == when comparing the values in the nodes).  Users may put nulls in the list, so make sure to compare
	 * values of null properly.  Should have runtime Theta(n).
	 * @return true if the lists store the same values in the same order, false otherwise
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) 
	{
		if(!(o instanceof DoublyLinkedList<?>))
		{
			return false;
		}
		DoublyLinkedList<?> obj = (DoublyLinkedList<?>) o;
		//empty
		if(this.size == 0 && obj.size == 0)
		{
			return true;
		}
		if(obj.size() != size)
		{
			return false;
		}
		Node<T> node = this.getFirst();
		Node<?> objNode = obj.getFirst();
		while(node != sentinel)
		{
			if(node.value == null)
			{
				if(objNode.value != null)
				{
					return false;
				}
			}
			else 
			{
				if(!node.value.equals(objNode.value))
				{
					return false;
				}
			}
			node = node.getNext();
			objNode = objNode.getNext();
		}
		return true;
	}
		
	/**
	 * Extra Credit:
	 * Inserts the entire given otherList after the given listNode.  This should remove all nodes from
	 * otherList leaving it empty.  Make sure otherList is still in a valid (empty) state after this operation.
	 * The operation must be Theta(1) to receive credit (i.e., no loops, no recursion).
	 * For example, if the current list is 1,2,3,4, otherList is 7,8,9 and the listNode points at 2
	 * then spliceAfter will produce 1,2,7,8,9,3,4.
	 * @param listNode the Node after which otherList must be inserted
	 * @param otherList the list that will be inserted
	 */
	public void spliceAfter(Node<T> listNode, DoublyLinkedList<T> otherList) 
	{
		Node<T> first = otherList.getFirst();
		Node<T> last = otherList.getLast();
		first.prev = listNode;
		last.next = listNode.next;
		listNode.next.prev = last;
		listNode.next = first;			
		size += otherList.size();
		otherList.sentinel.next = otherList.sentinel.prev = otherList.sentinel;
		otherList.size = 0;
	}
}
