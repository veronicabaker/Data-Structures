import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class CountPairTests
{
	@Test
	public void countTest1()
	{
		int[] arr = {1,2,3,4,5,6};
		assertEquals(3,Functions.countPairs(arr, 7));
		assertEquals(2,Functions.countPairs(arr, 6));
		assertEquals(2,Functions.countPairs(arr, 5));
		assertEquals(1,Functions.countPairs(arr, 4));
		assertEquals(0,Functions.countPairs(arr, 2));
	}
	@Test
	public void countTest2()
	{
		int[] arr = {1,10,100,1000,10000,100000};
		assertEquals(1,Functions.countPairs(arr, 11));
		assertEquals(1,Functions.countPairs(arr, 1001));
		assertEquals(1,Functions.countPairs(arr, 101));
		assertEquals(1,Functions.countPairs(arr, 11000));
		assertEquals(0,Functions.countPairs(arr, 111));
	}
	public void swap(int[] arr, int i, int j)
	{
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	@Test
	public void countTest3()
	{
		int[] arr = new int[100001];
		Random ran = new Random(1);
		for (int i = -50000; i <= 50000; ++i) arr[i+50000] = i;
		for (int i = arr.length - 1; i > 0; --i)
			swap(arr,i,ran.nextInt(i+1));
		long time = System.nanoTime();
		assertEquals(50000,Functions.countPairs(arr, 0));
		assertEquals(50000,Functions.countPairs(arr, 1));
		assertEquals(50000,Functions.countPairs(arr, -1));
		assertEquals(49999,Functions.countPairs(arr, 2));
		assertEquals(49999,Functions.countPairs(arr, -2));
		assertEquals(49999,Functions.countPairs(arr, 3));
		assertEquals(49999,Functions.countPairs(arr, -3));
		double elap = (System.nanoTime()-time)*1e-9;
		if (elap > 1)
			throw new AssertionError("Time must be less than 1s: "+elap+"s");
	}
	@Test
	public void countTest4()
	{
		Random ran = new Random(1);
		int[] arr = new int[1000];
		arr[0] = -5000;
		for (int i = 1; i < arr.length; ++i)
		{
			arr[i] = arr[i-1] + ran.nextInt(20) + 1;
		}
		int m = arr[500];
		for (int k = m-10; k <= m+10; ++k)
		{
			int cnt = 0;
			for (int i = 0; i < arr.length; ++i) 
				for (int j = 0; j < i; ++j)
					if (arr[i]+arr[j] == k)
						cnt++;
			assertEquals(cnt, Functions.countPairs(arr, k));
		}
	}
}
