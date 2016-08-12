import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import org.junit.Test;

public class IndirectTests
{
	private HashMap<String,ArrayList<String>> map = new HashMap<>(); 

	private ArrayList<String> makeList(String... acquaintances)
	{
		return new ArrayList<>(Arrays.asList(acquaintances));
	}
	
	private void put(String name, String... acquaintances)
	{
		map.put(name, makeList(acquaintances));
	}
	
	@Test
	public void indirectTest1()
	{
		put("Frank","Frank");
		assertEquals(makeList("Frank"), Functions.indirectAcquaintances(new HashMap<>(map), "Frank"));
	}
	
	@Test
	public void indirectTest2()
	{
		put("A","B","C"); //Says A knows B,C
		put("B","C","D");
		put("C");
		put("D");
		assertEquals(makeList("C","D"), Functions.indirectAcquaintances(new HashMap<>(map), "A"));
		assertEquals(makeList(), Functions.indirectAcquaintances(new HashMap<>(map), "B"));
		assertEquals(makeList(), Functions.indirectAcquaintances(new HashMap<>(map), "C"));
		assertEquals(makeList(), Functions.indirectAcquaintances(new HashMap<>(map), "D"));
		assertEquals(makeList(), Functions.indirectAcquaintances(new HashMap<>(map), "E"));
	}
	
	@Test
	public void indirectTest3()
	{
		put("A","D","C","B");
		put("B","D","C","A");
		put("C","D","B","A");
		put("D","C","B","A");
		assertEquals(makeList("A","B","C","D"), Functions.indirectAcquaintances(new HashMap<>(map), "A"));
		assertEquals(makeList("A","B","C","D"), Functions.indirectAcquaintances(new HashMap<>(map), "B"));
		assertEquals(makeList("A","B","C","D"), Functions.indirectAcquaintances(new HashMap<>(map), "C"));
		assertEquals(makeList("A","B","C","D"), Functions.indirectAcquaintances(new HashMap<>(map), "D"));
	}
	
	@Test
	public void indirectTest4()
	{
		put("A","B","C");
		put("B","D","F","E");
		put("C","G","D","Z");
		assertEquals(makeList("D","E","F","G","Z"), Functions.indirectAcquaintances(new HashMap<>(map), "A"));
		assertEquals(makeList(), Functions.indirectAcquaintances(new HashMap<>(map), "B"));
		assertEquals(makeList(), Functions.indirectAcquaintances(new HashMap<>(map), "C"));
	}
	
	@Test
	public void indirectTest5()
	{
		assertEquals(makeList(), Functions.indirectAcquaintances(new HashMap<>(map), "B"));		
	}

	@Test
	public void indirectTest6()
	{
		put("A","A","B","C");
		put("B","D","F","E");
		put("C","G","D","Z");
		assertEquals(makeList("A","B","C","D","E","F","G","Z"), Functions.indirectAcquaintances(new HashMap<>(map), "A"));
		assertEquals(makeList(), Functions.indirectAcquaintances(new HashMap<>(map), "B"));
		assertEquals(makeList(), Functions.indirectAcquaintances(new HashMap<>(map), "C"));
	}
	
	@Test
	public void indirectTest7()
	{
		put("A","B");
		put("B","C");
		put("C","D");
		put("D","E");
		put("E","F");
		assertEquals(makeList("C"), Functions.indirectAcquaintances(new HashMap<>(map), "A"));
		assertEquals(makeList("D"), Functions.indirectAcquaintances(new HashMap<>(map), "B"));
		assertEquals(makeList("E"), Functions.indirectAcquaintances(new HashMap<>(map), "C"));
		assertEquals(makeList("F"), Functions.indirectAcquaintances(new HashMap<>(map), "D"));
		assertEquals(makeList(), Functions.indirectAcquaintances(new HashMap<>(map), "E"));		
	}
	private String gen(int len, Random ran)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; ++i)
			sb.append((char)('A'+ran.nextInt(26)));
		return sb.toString();
	}
	@Test
	public void indirectTest8()
	{
		String[] arr = new String[500000];
		Random ran = new Random(1);
		for (int i = 0; i < arr.length; ++i) arr[i] = gen(10,ran);
		put(arr[0],arr);
		put(arr[1],arr);
		put(arr[2],arr);
		put(arr[3],arr);
		put(arr[4],arr);
		ArrayList<String> al = new ArrayList<>(Arrays.asList(arr));
		Collections.sort(al);
		long time = System.nanoTime();
		assertEquals(al,Functions.indirectAcquaintances(map, arr[0]));
		double elapsed = (System.nanoTime()-time)*1e-9;
		System.out.println(elapsed);
		if (elapsed > 10)
			throw new AssertionError("Time >10s: "+elapsed+"s");
	}
}
