import java.util.ArrayList;
import java.util.Comparator;

/**
 * Implements a (binary) min heap allowing for Comparable items or a specified Comparator.
 * If no Comparator is supplied, the added items are assumed to be comparable.  If not a 
 * ClassCastException may occur.  Null values should never be added to the min heap. 
 *
 * @param <T>
 */
public class MinHeap<T>
{
	private Comparator<? super T> comp;
	private ArrayList<T> heap = new ArrayList<>();

	/**
	 * Constructs a min heap of Comparable values.
	 */
	public MinHeap()
	{
		this(null);
	}

	/**
	 * Constructs a min heap using the specified Comparator as the ordering.
	 */
	public MinHeap(Comparator<? super T> c)
	{
		comp = c;
	}

	/**
	 * Compares a and b using the appropriate method.
	 * @param a
	 * @param b
	 * @return <0 if a comes before b, >0 if b comes before a, or =0 if equal with
	 * respect to the given ordering
	 */
	private int compare(T a, T b)
	{
		if (comp == null)
		{
			@SuppressWarnings("unchecked")
			Comparable<? super T> ca = (Comparable<? super T>)a;
			return ca.compareTo(b);
		}
		return comp.compare(a,b);
	}

	/**
	 * Returns the number of elements in the min heap.
	 * @return the number of elements in the min heap
	 */
	public int size() 
	{ 
		return heap.size(); 
	}

	/**
	 * Returns (but does not remove) the smallest element of the heap.
	 * @return the smallest element of the heap
	 */
	public T getMin() 
	{ 
		return heap.get(0); 
	}

	/**
	 * Returns if the heap is empty.
	 * @return if the heap is empty
	 */
	public boolean isEmpty() 
	{ 
		return heap.size() == 0; 
	}
	
	/**
	 * Returns and removes the smallest element of the heap.
	 * @return the smallest element of the heap
	 */
	public T removeMin() 
	{ 
		return heap.remove(0);
	}
	
	/**
	 * Adds the given element to the heap.
	 * @param t
	 */
	public void add(T t) 
	{
		heap.add(null);
		int index = heap.size() - 1;
		while(index > 1 && compare(heap.get(index / 2), t) > 0)
		{
			heap.set(index, heap.get(index / 2));
			index = index / 2;
		}
		heap.set(index, t);
		System.out.println(heap);
	}
	
	@SuppressWarnings("unchecked")
	public void siftUp(int index)
	{
		int parentIndex;
		Object tmp;
		if(index != 0)
		{
			parentIndex = index / 2;
			if(compare(heap.get(parentIndex), heap.get(index)) > 0)
			{
				tmp = heap.get(parentIndex);
				heap.set(parentIndex, heap.get(index));
				heap.set(index, (T) tmp);
				siftUp(parentIndex);
			}
		}
	}
	/**
	 * Returns whether the heap contains the given value.
	 * @param t value to find
	 * @return whether the heap contains the given value
	 */
	public boolean contains(T t) 
	{ 
		return heap.contains(t); 
	}	
}
