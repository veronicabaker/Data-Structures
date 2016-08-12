import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class MediansTests
{
	@Test
	public void simpleTest()
	{
		int[] arr = {5,9,3,7,10};
		int[] res = Functions.medians(arr);
		assertEquals(3,res.length);
		assertEquals(5,res[0]);
		assertEquals(5,res[1]);
		assertEquals(7,res[2]);
	}
	
	@Test
	public void slowTest()
	{
		Random ran = new Random(1);
		int N = 10000;
		int[] arr = new int[2*N+1];
		for (int i = 0; i < arr.length; ++i) arr[i] = ran.nextInt();
		int[] copy = Arrays.copyOf(arr, arr.length);
		int[] res = new int[N+1];
		for (int i = 0; i < arr.length; i += 2)
		{
			Arrays.sort(copy,0,i+1);
			res[i/2] = copy[i/2];
		}
		int[] user = Functions.medians(arr);
		assertTrue(Arrays.equals(res, user));
	}
	
	@Test
	public void sortedTest()
	{
		int N = 1000000;
		int[] arr = new int[2*N+1];
		for (int i = 0; i < arr.length; ++i) arr[i] = i;
		int[] res = new int[N+1];
		for (int i = 0; i < N+1; ++i) res[i] = arr[i];
		long time = System.nanoTime();
		int[] user = Functions.medians(arr);
		double elapsed = (System.nanoTime() - time)*1e-9;
		if (elapsed > 3)
			throw new AssertionError("Time must be less than 3s: "+elapsed+"s");
		assertTrue(Arrays.equals(res, user));
	}
	
	@Test
	public void reverseSortedTest()
	{
		int N = 1000000;
		int[] arr = new int[2*N+1];
		for (int i = 0; i < arr.length; ++i) arr[i] = arr.length+1-i;
		int[] res = new int[N+1];
		for (int i = 0; i < N+1; ++i) res[i] = arr[i];
		long time = System.nanoTime();
		int[] user = Functions.medians(arr);
		double elapsed = (System.nanoTime() - time)*1e-9;
		if (elapsed > 3)
			throw new AssertionError("Time must be less than 3s: "+elapsed+"s");
		assertTrue(Arrays.equals(res, user));		
	}
}
