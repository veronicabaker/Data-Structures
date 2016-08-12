import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class CheapestTripTests
{
	static final double EPS = 1e-4;
	@Test
	public void test0()
	{
		String[] flights = {
				"JFK to LAX for 275.99",
				"LAX to SFO for 96.99",
				"SFO to NRT for 399",
				"NRT to OSA for 91",
				"OSA to BJS for 869"
		};
		assertEquals(275.99, Functions.cheapestTrip(flights, "JFK", "LAX"),EPS);
		assertEquals(275.99+96.99, Functions.cheapestTrip(flights, "JFK", "SFO"),EPS);
		assertEquals(275.99+96.99+399, Functions.cheapestTrip(flights, "JFK", "NRT"),EPS);
		assertEquals(275.99+96.99+399+91, Functions.cheapestTrip(flights, "JFK", "OSA"),EPS);
		assertEquals(275.99+96.99+399+91+869, Functions.cheapestTrip(flights, "JFK", "BJS"),EPS);
		assertEquals(96.99+399+91+869, Functions.cheapestTrip(flights, "LAX", "BJS"),EPS);
	}
	@Test
	public void test1()
	{
		String[] flights = {
				"1 to 3 for 3", "3 to 1 for 3",
				"1 to 2 for 2", "2 to 1 for 2",
				"2 to 3 for 7", "3 to 2 for 7",
				"1 to 4 for 6", "4 to 1 for 6",
				"3 to 4 for 5", "4 to 3 for 5",
				"0 to 2 for 6", "2 to 0 for 6",
				"0 to 4 for 1", "4 to 0 for 1"
		};
		assertEquals(2, Functions.cheapestTrip(flights, "1","2"),EPS);
		assertEquals(3, Functions.cheapestTrip(flights, "1","3"),EPS);
		assertEquals(3, Functions.cheapestTrip(flights, "3","1"),EPS);
		assertEquals(6, Functions.cheapestTrip(flights, "1","4"),EPS);
		assertEquals(6, Functions.cheapestTrip(flights, "4","1"),EPS);
		assertEquals(7, Functions.cheapestTrip(flights, "1","0"),EPS);
		assertEquals(7, Functions.cheapestTrip(flights, "0","1"),EPS);
		assertEquals(2, Functions.cheapestTrip(flights, "2","1"),EPS);
		assertEquals(5, Functions.cheapestTrip(flights, "2","3"),EPS);
		assertEquals(5, Functions.cheapestTrip(flights, "3","2"),EPS);
		assertEquals(6, Functions.cheapestTrip(flights, "2","0"),EPS);
		assertEquals(6, Functions.cheapestTrip(flights, "0","2"),EPS);
		assertEquals(7, Functions.cheapestTrip(flights, "2","4"),EPS);
		assertEquals(7, Functions.cheapestTrip(flights, "4","2"),EPS);
	}
	@Test
	public void bigCycle()
	{
		ArrayList<String> fs = new ArrayList<>();
		for (int i = 0; i < 1000; ++i)
			fs.add(String.format("%d to %d for %f",i,(i+1)%1000,1.0));
		String[] flights = fs.toArray(new String[0]);
		assertEquals(500, Functions.cheapestTrip(flights, "1","501"),EPS);
		assertEquals(999, Functions.cheapestTrip(flights, "1","0"),EPS);
		for (int i = 0; i < 1000; ++i)
			fs.add(String.format("%d to %d for %f",i,(i-1+1000)%1000,1.0));
		flights = fs.toArray(new String[0]);
		assertEquals(40, Functions.cheapestTrip(flights, "1","41"),EPS);
		assertEquals(40, Functions.cheapestTrip(flights, "41","1"),EPS);
	}
	@Test
	public void bigGraph()
	{
		ArrayList<String> fs = new ArrayList<>();
		int[] primes = {2,3,5,7,11,13,17};
		for (int i : primes)
			for (int j = 1; j*i <= 1000; ++j)
				fs.add(String.format("%d to %d for %f",j,i*j,1.0/i));
		for (int i = 1; i <= 999; ++i)
			fs.add(String.format("%d to %d for %f",i,i+1,1.0));
		String[] flights = fs.toArray(new String[0]);
		long time = System.nanoTime();
		assertEquals(2.701681,Functions.cheapestTrip(flights,"1","986"),EPS);
		assertEquals(2.376623,Functions.cheapestTrip(flights,"1","589"),EPS);
		assertEquals(1.324675,Functions.cheapestTrip(flights,"1","848"),EPS);
		assertEquals(2.667832,Functions.cheapestTrip(flights,"1","314"),EPS);
		assertEquals(0.592157,Functions.cheapestTrip(flights,"1","255"),EPS);
		assertEquals(2.742857,Functions.cheapestTrip(flights,"1","905"),EPS);
		assertEquals(2.458824,Functions.cheapestTrip(flights,"1","435"),EPS);
		assertEquals(2.381818,Functions.cheapestTrip(flights,"1","607"),EPS);
		assertEquals(2.324675,Functions.cheapestTrip(flights,"1","979"),EPS);
		assertEquals(2.149733,Functions.cheapestTrip(flights,"1","749"),EPS);
		double elap = (System.nanoTime() - time)*1e-9;
		if (elap > 3) throw new AssertionError("Must be at most 3s:" +elap+"s");
	}
	@Test
	public void randomGraph()
	{
		Numbers num = new Numbers(1);
		ArrayList<String> fs = new ArrayList<>();
		for (int i = 0; i < 1000; ++i)
			for (int j = 0; j < i; ++j) 
			{
				if (num.get(2)==1)
					fs.add(String.format("%d to %d for %d", i,j,num.get(10000)+1));
				if (num.get(2)==1)
					fs.add(String.format("%d to %d for %d", j,i,num.get(10000)+1));
			}
		String[] flights = fs.toArray(new String[0]);
		long time = System.nanoTime();
		assertEquals(75.0, Functions.cheapestTrip(flights, "0", "1"),EPS);
		assertEquals(44.0, Functions.cheapestTrip(flights, "0", "2"),EPS);
		assertEquals(53.0, Functions.cheapestTrip(flights, "0", "3"),EPS);
		assertEquals(60.0, Functions.cheapestTrip(flights, "0", "999"),EPS);
		assertEquals(107.0, Functions.cheapestTrip(flights, "0", "998"),EPS);
		assertEquals(70.0, Functions.cheapestTrip(flights, "0", "997"),EPS);
		assertEquals(127.0, Functions.cheapestTrip(flights, "0", "437"),EPS);
		double elap = (System.nanoTime() - time)*1e-9;
		if (elap > 60) throw new AssertionError("Must be at most 60s:" +elap+"s");
	}
	@Test
	public void randomGraph2()
	{
		Numbers num = new Numbers(1);
		ArrayList<String> fs = new ArrayList<>();
		for (int i = 0; i < 1000; ++i)
			for (int j = 0; j < i; ++j) 
			{
				if (num.get(10)==1)
					fs.add(String.format("%d to %d for %d", i,j,num.get(10000)+1));
				if (num.get(10)==1)
					fs.add(String.format("%d to %d for %d", j,i,num.get(10000)+1));
			}
		String[] flights = fs.toArray(new String[0]);
		long time = System.nanoTime();
		assertEquals(910.0, Functions.cheapestTrip(flights, "0", "1"),EPS);
		assertEquals(816.0, Functions.cheapestTrip(flights, "0", "2"),EPS);
		assertEquals(1039.0, Functions.cheapestTrip(flights, "0", "3"),EPS);
		assertEquals(848.0, Functions.cheapestTrip(flights, "0", "999"),EPS);
		assertEquals(792.0, Functions.cheapestTrip(flights, "0", "998"),EPS);
		double elap = (System.nanoTime() - time)*1e-9;
		if (elap > 10) throw new AssertionError("Must be at most 10s:" +elap+"s");
	}
}
