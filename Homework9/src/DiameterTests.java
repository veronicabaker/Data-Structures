import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class DiameterTests
{
	static final double EPS = 1e-4;
	private double[][] data;
	private void setSize(int N) 
	{ 
		data = new double[N][N]; 
		for (int i = 0; i < N; ++i) Arrays.fill(data[i], Double.POSITIVE_INFINITY);
	}
	private void addEdge(int i, int j, double c) { data[i][j] = data[j][i] = c; }
	
	@Test
	public void testPath()
	{
		setSize(6);
		addEdge(0,1,3);
		addEdge(1,2,3);
		addEdge(2,3,3);
		addEdge(3,4,3);
		addEdge(4,5,3);
		assertEquals(15,Functions.diameter(data),EPS);
	}
	@Test
	public void testCycle()
	{
		setSize(6);
		addEdge(0,1,3);
		addEdge(1,2,3);
		addEdge(2,3,3);
		addEdge(3,4,3);
		addEdge(4,5,3);
		addEdge(5,0,3);
		assertEquals(9,Functions.diameter(data),EPS);
	}
	@Test
	public void testStar()
	{
		setSize(6);
		addEdge(0,1,1);
		addEdge(0,2,2);
		addEdge(0,3,3);
		addEdge(0,4,4);
		addEdge(0,5,5);
		assertEquals(9,Functions.diameter(data),EPS);
	}
	@Test
	public void testComplete()
	{
		setSize(10);
		for (int i = 0; i < 10; ++i)
			for (int j = 0; j < i; ++j)
				addEdge(i,j,i*i);
		assertEquals(81,Functions.diameter(data),EPS);
	}
	@Test
	public void testComplete2()
	{
		setSize(10);
		for (int i = 0; i < 10; ++i)
			for (int j = 0; j < i; ++j)
				addEdge(i,j,i%2==0?i*i:j*j);
		assertEquals(64,Functions.diameter(data),EPS);
	}
	@Test
	public void testComplete3()
	{
		Numbers num = new Numbers(1);
		setSize(100);
		for (int i = 0; i < 100; ++i)
			for (int j = 0; j < i; ++j)
				addEdge(i,j,num.get(10000)+1);
		long time = System.nanoTime();
		assertEquals(1616.0,Functions.diameter(data),EPS);
		double elap = (System.nanoTime()-time)*1e-9;
		if (elap > 1) throw new AssertionError("Time must be at most 1s: "+elap+"s");
	}
}
