import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

import org.junit.Test;

public class MinHeapTests
{
	private MinHeap<Integer> heap = new MinHeap<>();
	private MinHeap<Integer> rHeap = new MinHeap<>(Collections.reverseOrder());
	private MinHeap<String> sHeap = new MinHeap<>();
	private PriorityQueue<Integer> pq = new PriorityQueue<>();

	@Test
	public void testAdds()
	{
		heap.add(1); heap.add(2); heap.add(3); heap.add(4);
		assertEquals(1,heap.getMin().intValue());
		for (int i = 1; i <= 4; ++i)
		{
			assertTrue(heap.contains(i));
			assertEquals(i,heap.removeMin().intValue());
			assertFalse(heap.contains(i));			
		}
	}

	@Test
	public void testOtherTypes()
	{
		for (int i = 1; i <= 4; ++i)
		{
			rHeap.add(i);
			sHeap.add(i+"");
		}
		assertEquals(4,rHeap.size());
		assertEquals(4,sHeap.size());
		assertTrue(rHeap.contains(3));
		assertTrue(sHeap.contains(3+""));
		for (int i = 1; i <= 4; ++i)
		{
			System.out.println(i);
			assertEquals(4-i+1,rHeap.removeMin().intValue());
			assertEquals(i+"",sHeap.removeMin());
		}
	}

	@Test
	public void testSort()
	{
		Random ran = new Random(1);
		int[] arr = new int[1000000];
		for (int i = 0; i < arr.length; ++i) arr[i] = ran.nextInt();
		for (int i = 0; i < arr.length; ++i) heap.add(arr[i]);
		Arrays.sort(arr);
		for (int i = 0; i < arr.length; ++i) assertEquals(arr[i],heap.removeMin().intValue());
	}

	@Test
	public void testRandom()
	{
		Random ran = new Random(3);
		for (int j = 0; j < 10; ++j)
		{
			for (int i = 0; i < 200000; ++i)
			{
				int t = ran.nextInt(3);
				if (t <= 1)
				{
					int v = ran.nextInt();
					heap.add(v);
					pq.add(v);
				} 
				else if (t > 1)
				{
					if (!pq.isEmpty()) assertEquals(pq.poll(),heap.removeMin());
				}
				assertEquals(pq.size(),heap.size());
				assertEquals(pq.isEmpty(),heap.isEmpty());
			}
			while (!pq.isEmpty()) assertEquals(pq.poll(),heap.removeMin());
		}
	}
}
