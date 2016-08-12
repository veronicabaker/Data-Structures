import static org.junit.Assert.*;

import org.junit.Test;

public class ExitTests
{
	@Test
	public void test0()
	{
		String[] maze = {"S...E"};
		assertEquals(4, Functions.closestExit(maze));
	}
	
	@Test
	public void test1()
	{
		String[] maze = {"S.X.E"};
		assertEquals(-1, Functions.closestExit(maze));
	}

	@Test
	public void test2()
	{
		String[] maze = {
				"S...",
				"...E"
				};
		assertEquals(4, Functions.closestExit(maze));
	}

	@Test
	public void test3()
	{
		String[] maze = {
				"SX..",
				"X..E"
				};
		assertEquals(-1, Functions.closestExit(maze));
	}

	@Test
	public void test4()
	{
		String[] maze = {
				"S........",
				"XXXXXXXX.",
				".........",
				".XXXXXXXX",
				"........E"
				};
		assertEquals(28, Functions.closestExit(maze));
	}
	
	@Test
	public void test5()
	{
		String[] maze = {
				"S...........",
				"XXXXXXXXXXX.",
				"..........X.",
				".XXXXXXXX.X.",
				".XE.......X.",
				".XXXXXXXXXX.",
				"............",
				};
		assertEquals(50, Functions.closestExit(maze));
	}

	@Test
	public void test6()
	{
		String[] maze = {
				"..........S...........",
				".XXXXXXXXXXXXXXXXXXXX.",
				"..........X...........",
				".XXXXXXXX.X.XXXXXXXXX.",
				".XXXXXXXX.X.XXXXXXXXX.",
				".XXXXXXXX.X.XXXXXXXXX.",
				".XXXXXXXX.X.XXXXXXXXX.",
				".....X....X.....X.....",
				"XXXX.X.XXXXXXXX.X.XXXX",
				".....X....E.....X....."
		};
		assertEquals(35, Functions.closestExit(maze));
	}
	
	@Test
	public void bigTest()
	{
		StringBuilder r1 = new StringBuilder(), r2 = new StringBuilder();
		for (int i = 0; i < 100; ++i) r1.append('.');
		for (int i = 0; i < 100; ++i) r2.append(i%2 == 0 ? '.' : 'X');
		String s1 = r1.toString(), s2 = r2.toString();
		r1.setCharAt(0, 'S');
		String[] maze = new String[101];
		maze[0] = r1.toString();
		r1.setCharAt(0, '.');
		r1.setCharAt(r1.length()-1, 'E');
		maze[maze.length-1] = r1.toString();
		for (int i = 1; i <= 99; ++i) maze[i] = i%2==0 ? s1 : s2;
		assertEquals(199, Functions.closestExit(maze));
	}
}
