import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class SortTests
{
	private ArrayList<String> in = new ArrayList<>();
	private ArrayList<String> out = new ArrayList<>();

	@Test
	public void testSameCredits()
	{
		in.add("3,A B,2");
		in.add("2,A B,2");
		in.add("1,A B,2");
		out.add("1,A B,2");
		out.add("2,A B,2");
		out.add("3,A B,2");
		Functions.sort(in);
		assertEquals(out,in);
	}
	@Test
	public void testSameCreditID()
	{
		in.add("1,E F,1");
		in.add("1,CCC A,1");
		in.add("1,CCD A,1");
		out.add("1,CCC A,1");
		out.add("1,CCD A,1");
		out.add("1,E F,1");
		Functions.sort(in);
		assertEquals(out,in);
	}
	@Test
	public void bigTest()
	{
		for (int credits = 99; credits >= 10; --credits)
		{
			for (int id = 0; id < 99; ++id)
			{
				for (int name = 10; name <= 99; ++name)
				{
					String s = id+","+name+","+credits;
					in.add(s); in.add(s);
					out.add(s); out.add(s);
				}
			}
		}
		Collections.shuffle(in);
		long time = System.nanoTime();
		Functions.sort(in);
		double elap = (System.nanoTime() - time)*1e-9;
		System.out.println(elap);
		if (elap > 15)
			throw new AssertionError("Time must be less than 15s: "+elap+"s");
		assertEquals(out,in);
	}
}