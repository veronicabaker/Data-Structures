import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class HeightTests
{
	@Test
	public void oneNode()
	{
		assertEquals(0,Functions.getHeight(new boolean[1][1], 0));
	}
	@Test
	public void twoNodes()
	{
		boolean[][] adj = new boolean[2][2];
		adj[0][1] = adj[1][0] = true;
		assertEquals(1,Functions.getHeight(adj, 0));
		assertEquals(1,Functions.getHeight(adj, 1));
	}
	@Test
	public void tall()
	{
		boolean[][] adj = new boolean[1000][1000];
		for (int i = 1; i < adj.length; ++i)
			adj[i][i-1] = adj[i-1][i] = true;
		for (int i = 0; i < 1000; ++i)
			assertEquals(Math.max(999-i, i), Functions.getHeight(adj, i));
	}
	public void swap(int[] arr, int a, int b)
	{
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	@Test
	public void randomTest1()
	{
		Random ran = new Random(1);
		for (int X = 0; X < 15; ++X)
		{
			int[] perm = new int[1000];
			for (int i = 0; i < perm.length; ++i) perm[i] = i;
			for (int i = perm.length-1; i >= 2; --i)
			{
				int j = ran.nextInt(perm.length-1)+1;
				swap(perm,j,i);
			}
			int[] arr = new int[1000], d = new int[1000];
			boolean[][] adj = new boolean[1000][1000];
			d[0] = 0;
			int p = 1, md = 0;;
			for (int i = 0; p < arr.length; ++i)
			{
				int cs = Math.min(ran.nextInt(4)+1, arr.length-p);
				for (int j = 0; j < cs; ++j) {
					arr[perm[p]] = perm[i];
					d[perm[p]] = d[perm[i]]+1;
					md = Math.max(d[perm[p]], md);
					adj[perm[p]][perm[i]] = adj[perm[i]][perm[p]] = true;
					p++;
				}
			}
			long time = System.nanoTime();
			assertEquals(md,Functions.getHeight(adj, 0));
			double elap = (System.nanoTime() - time)*1e-9;
			if (elap > 3)
				throw new AssertionError("Time must be less than 3s: "+elap+"s");
		}
	}
}
