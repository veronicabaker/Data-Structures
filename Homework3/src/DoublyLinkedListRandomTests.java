import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class DoublyLinkedListRandomTests
{
	private Random ran = new Random(1);
	private DoublyLinkedList<Integer> testList = new DoublyLinkedList<>();
	private ArrayList<Integer> trueList = new ArrayList<Integer>();
	
	private void checkConsistency()
	{
		DoublyLinkedListTests.checkConsistency(testList, trueList);
	}
	
	private void bigTest(boolean shouldSplice)
	{
		for (int i = 0; i < 5000; ++i)
		{
			int op = ran.nextInt(9);
			if (op == 0)
			{
				int v = ran.nextInt();
				testList.addLast(new Integer(v));
				trueList.add(new Integer(v));
			} 
			else if (op == 1)
			{
				int v = ran.nextInt();
				testList.addFirst(new Integer(v));
				trueList.add(0,new Integer(v));				
			} 
			else if (op == 2)
			{
				if (trueList.size() > 0)
				{
					int v = ran.nextInt();
					int p = ran.nextInt(trueList.size());
					testList.addBefore(testList.getNode(p),v);
					trueList.add(p,v);
				}
			} 
			else if (op == 3)
			{
				if (trueList.size() > 0)
				{
					int v = ran.nextInt();
					int p = ran.nextInt(trueList.size());
					testList.addAfter(testList.getNode(p),v);
					trueList.add(p+1,v);
				}
			}  
			else if (op == 4)
			{
				if (trueList.size() > 0)
				{
					testList.removeLast();
					trueList.remove(trueList.size()-1);
				}				
			} 
			else if (op == 5)
			{
				if (trueList.size() > 0)
				{
					testList.removeFirst();
					trueList.remove(0);
				}				
			}
			else if (op == 6)
			{
				if (trueList.size() > 0)
				{
					int p = ran.nextInt(trueList.size());
					testList.removeNode(testList.getNode(p));
					trueList.remove(p);
				}								
			}
			else if (op == 7)
			{
				if (trueList.size() > 0)
				{
					int v = ran.nextInt();
					int p = ran.nextInt(trueList.size());
					testList.getNode(p).setValue(v);
					trueList.set(p,v);
				}												
			}
			else if (op == 8)
			{
				if (trueList.size() > 0 & shouldSplice)
				{
					int s = ran.nextInt(10)+10;
					int[] arr = new int[s];
					for (int j = 0; j < s; ++j) arr[j] = ran.nextInt();
					DoublyLinkedList<Integer> newList = new DoublyLinkedList<>();
					for (int j = 0; j < s; ++j) newList.addLast(arr[j]);
					int p = ran.nextInt(trueList.size());
					testList.spliceAfter(testList.getNode(p), newList);
					for (int j = 0; j < s; ++j) trueList.add(p+j+1,arr[j]);
					
					assertEquals(newList.size(),0);
					assertEquals(newList.getSentinel(),newList.getSentinel().getNext());
					assertEquals(newList.getSentinel(),newList.getSentinel().getPrev());
				}
			}
			checkConsistency();
		}
	}
	
	@Test
	public void bigTestWithoutSplice()
	{
		bigTest(false);
	}

	@Test
	public void bigTestWithSplice()
	{
		bigTest(false);
	}
}