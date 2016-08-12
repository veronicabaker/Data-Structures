import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Test;

public class DoublyLinkedListTests
{
	private DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
	private ArrayList<Integer> trueList = new ArrayList<Integer>();
	
	public static void checkConsistency(DoublyLinkedList<Integer> testList, ArrayList<Integer> trueList)
	{
		int size = testList.size();
		assertEquals(trueList.size(),testList.size());
		if (trueList.size() == 0) return;
		DoublyLinkedList.Node<Integer> curr = testList.getFirst();
		for (int i = 0; i < size; ++i)
		{
			assertEquals(testList.getNode(i).getValue(),curr.getValue());
			assertEquals(curr,curr.getNext().getPrev());
			assertEquals(curr,curr.getPrev().getNext());
			curr = curr.getNext();
		}
		assertEquals(testList.getSentinel(),curr);
		
		assertEquals(testList.getSentinel().getNext().getPrev(),testList.getSentinel());
		assertEquals(testList.getSentinel().getPrev().getNext(),testList.getSentinel());
		assertEquals(testList.getSentinel().getNext(),testList.getFirst());
		assertEquals(testList.getSentinel().getPrev(),testList.getLast());	
		
		assertEquals(testList.getFirst().getValue(),trueList.get(0));
		assertEquals(testList.getLast().getValue(),trueList.get(trueList.size()-1));
	}
	
	private void checkConsistency()
	{
		DoublyLinkedListTests.checkConsistency(testList,trueList);
	}

	private void addAll(int[] arr)
	{
		for (int i = 0; i < arr.length; ++i)
		{
			testList.addLast(new Integer(arr[i]));
			trueList.add(new Integer(arr[i]));
		}
	}
	
	private void addAllReverse(int[] arr)
	{
		for (int i = 0; i < arr.length; ++i)
		{
			testList.addFirst(new Integer(arr[i]));
			trueList.add(0,new Integer(arr[i]));
		}
	}

	@Test
	public void testConstructor()
	{
		assertEquals("Size after construction",0,testList.size());
		DoublyLinkedList.Node<Integer> sentinel = testList.getSentinel();
		assertEquals("Sentinel next for empty list",sentinel,sentinel.getNext());
		assertEquals("Sentinel prev for empty list",sentinel,sentinel.getPrev());
	}
	
	@Test
	public void testAddFirst()
	{
		testList.addFirst(5);
		assertEquals("First value",5,testList.getFirst().getValue().intValue());
		assertEquals("Last vs First",testList.getFirst(),testList.getLast());
		assertEquals("Size after addFirst",1,testList.size());
		assertEquals("Sentinel next",testList.getFirst(),testList.getSentinel().getNext());
		assertEquals("Sentinel prev",testList.getFirst(),testList.getSentinel().getPrev());
		assertEquals("First next",testList.getSentinel(),testList.getFirst().getNext());
		assertEquals("First prev",testList.getSentinel(),testList.getFirst().getPrev());
	}

	@Test
	public void testAddLast()
	{
		testList.addLast(5);
		assertEquals("First value",5,testList.getFirst().getValue().intValue());
		assertEquals("Last vs First",testList.getFirst(),testList.getLast());
		assertEquals("Size after addFirst",1,testList.size());
		assertEquals("Sentinel next",testList.getFirst(),testList.getSentinel().getNext());
		assertEquals("Sentinel prev",testList.getFirst(),testList.getSentinel().getPrev());
		assertEquals("First next",testList.getSentinel(),testList.getFirst().getNext());
		assertEquals("First prev",testList.getSentinel(),testList.getFirst().getPrev());
	}
	
	@Test
	public void testGetFirstEmpty()
	{
		assertEquals(testList.getSentinel(),testList.getFirst());
	}
	
	@Test
	public void testGetLastEmpty()
	{
		assertEquals(testList.getSentinel(),testList.getLast());
	}

	@Test
	public void testMultipleAddLasts()
	{
		addAll(new int[]{1,2,3,4,5,6,7});
		checkConsistency();
	}
	
	@Test
	public void testMultipleAddFirsts()
	{
		addAllReverse(new int[]{1,2,3,4,5,6,7});
		checkConsistency();
	}

	@Test
	public void testAddAfter()
	{
		addAll(new int[]{1,2,3,4,5,6,7});
		DoublyLinkedList.Node<Integer> n = testList.getNode(3);
		testList.addAfter(n, 10);
		trueList.add(4,10);
		checkConsistency();
	}

	@Test
	public void testAddBefore()
	{
		addAll(new int[]{1,2,3,4,5,6,7});
		DoublyLinkedList.Node<Integer> n = testList.getNode(3);
		testList.addBefore(n, 10);
		trueList.add(3,10);
		checkConsistency();
	}
	
	@Test
	public void testRemoveFirst()
	{
		addAll(new int[]{1,2,3,4,5,6,7});
		testList.removeFirst();
		trueList.remove(0);
		checkConsistency();
	}

	@Test
	public void testRemoveLast()
	{
		addAll(new int[]{1,2,3,4,5,6,7});
		testList.removeLast();
		trueList.remove(trueList.size()-1);
		checkConsistency();
	}
	
	@Test
	public void testRemoveNode()
	{
		addAll(new int[]{1,2,3,4,5,6,7});
		while (trueList.size() > 3)
		{
			testList.removeNode(testList.getNode(3));
			trueList.remove(3);
			checkConsistency();
		}
	}
	
	@Test
	public void testGetNode()
	{
		addAll(new int[]{1,2,3,4,5,6,7});
		for (int i = 0; i < trueList.size(); ++i)
		{
			assertEquals(trueList.get(i).intValue(),testList.getNode(i).getValue().intValue());
		}
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveLastException()
	{
		testList.removeLast();
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirstException()
	{
		testList.removeFirst();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveSentinelException()
	{
		testList.removeNode(testList.getSentinel());
	}	
	
	@Test
	public void testEquals()
	{
		int[] arr = {1,2,3,4,5,6,7};
		addAll(arr);
		DoublyLinkedList<Integer> other = new DoublyLinkedList<>();
		for (int i = 0; i < arr.length; ++i) other.addLast(new Integer(arr[i]));
		assertEquals(testList,other);
		other.addLast(8);
		assertNotEquals(testList,other);
		testList.addLast(8);
		assertEquals(testList,other);
		testList.addLast(9);
		assertNotEquals(testList,other);		
	}
	
	@Test
	public void testEqualsEmpty()
	{
		DoublyLinkedList<Integer> other = new DoublyLinkedList<Integer>();
		assertTrue(testList.equals(other));
	}

	@Test
	public void testEqualsBadTypes()
	{
		addAll(new int[]{1,2,3,4});
		assertFalse(testList.equals("HMM"));		
		assertFalse(testList.equals(null));
	}

	@Test
	public void testEqualsWithNulls()
	{
		testList.addLast(null);
		DoublyLinkedList<Integer> other = new DoublyLinkedList<>();
		other.addLast(null);
		assertTrue(testList.equals(other));
		testList.addFirst(1);
		other.addFirst(1);
		assertTrue(testList.equals(other));
		testList.addLast(null);		
		assertFalse(testList.equals(other));
		assertFalse(other.equals(testList));
		other.addLast(2);
		assertFalse(testList.equals(other));
		assertFalse(other.equals(testList));
	}	
	
	@Test
	public void testSplice()
	{
		addAll(new int[]{1,2,3,4,5,6});
		int[] arr = {10,11,12,13};
		DoublyLinkedList<Integer> newList = new DoublyLinkedList<>();
		for (int j = 0; j < arr.length; ++j) newList.addLast(arr[j]);
		testList.spliceAfter(testList.getNode(3), newList);
		for (int j = 0; j < arr.length; ++j) trueList.add(3+j+1,arr[j]);
		
		assertEquals(newList.size(),0);
		assertEquals(newList.getSentinel(),newList.getSentinel().getNext());
		assertEquals(newList.getSentinel(),newList.getSentinel().getPrev());
		checkConsistency();
	}
}
