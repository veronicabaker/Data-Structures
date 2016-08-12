import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

public class CommandsTests
{
	private ArrayList<String> cmds = new ArrayList<>();
	private ArrayList<String> data = new ArrayList<>();
	private ArrayList<String> out = new ArrayList<>();
	
	public void add(String name) 
	{ 
		cmds.add("ADD "+name);
		if (!data.contains(name)) data.add(name);
	}
	
	public void getPos(String name) 
	{
		Collections.sort(data);
		cmds.add("GETPOS "+name);
		out.add(""+data.indexOf(name));
	}
	
	public void getPre(String name) 
	{ 
		Collections.sort(data);
		cmds.add("GETPRE "+name); 
		for (String s: data)
			if (s.startsWith(name)) out.add(s);
	}
	
	@Test
	public void testGetPos()
	{
		add("Z");
		add("Wow");
		getPos("Wow");
		getPos("Z");
		assertEquals(out,Functions.processCommands(cmds));
	}

	@Test
	public void testGetPre()
	{
		add("Wow");
		add("Wowa");
		getPre("Wo");
		getPre("Wowa");
		assertEquals(out,Functions.processCommands(cmds));
	}
	
	@Test
	public void testCommands1()
	{
		add("A");
		getPos("A");
		getPre("A");
		getPre("AA");
		assertEquals(out,Functions.processCommands(cmds));
	}
	
	@Test
	public void testCommands2()
	{
		add("ale");
		add("all");
		add("allow");
		add("ball");
		getPos("ale");
		getPos("all");
		getPos("allow");
		getPos("ball");
		getPre("al");
		getPre("a");
		getPre("b");
		getPre("z");
		getPre("all");
		System.out.println(out);
		assertEquals(out,Functions.processCommands(cmds));
	}	
	@Test
	public void testCommands3()
	{
		for (char c = 'A'; c <= 'Z'; ++c)
			for (char d = 'A'; d <= 'Z'; ++d)
				add(d+""+c);
		for (char c = 'A'; c <= 'Z'; ++c)
			for (char d = 'A'; d <= 'Z'; ++d)
			{
				getPos(c+""+d);
				getPre(c+"");
				getPre(c+""+d);
			}
		assertEquals(out,Functions.processCommands(cmds));		
	}
	
	@Test
	public void testLong()
	{
		Random ran = new Random(1);
		for (int i = 10000; i < 100000; ++i) cmds.add("ADD "+i);
		for (int i = 0; i < 10; ++i) 
		{
			int v = ran.nextInt(90000)+10000;
			cmds.add("GETPOS "+v);
			out.add(""+(v-10000));
		}
		for (int i = 100; i <= 999; ++i)
		{
			cmds.add("GETPRE "+i);
			for (int j = 0; j <= 99; ++j) out.add(String.format("%d%02d",i,j));
		}
		for (int i = 1000; i <= 9999; ++i)
		{
			cmds.add("GETPRE "+i);
			for (int j = 0; j <= 9; ++j) out.add(String.format("%d%d",i,j));
		}
		long time = System.nanoTime();
		assertEquals(out,Functions.processCommands(cmds));
		double elap = (System.nanoTime() - time)*1e-9;
		if (elap > 3)
			throw new AssertionError("Elapsed time must be less than 3s: "+elap+"s");
	}
}
