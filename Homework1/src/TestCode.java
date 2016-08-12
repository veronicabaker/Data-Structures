import java.io.BufferedReader;
import java.io.FileReader;

public class TestCode 
{
	public final static int MAX_WIDTH = 65;	
	public static void printInput(int num, String[] arr)
	{
		System.out.printf("Case #%d Input:\n",num);
		System.out.print("{");
		int widthLeft = MAX_WIDTH;
		for (int i = 0; i < arr.length; ++i)
		{
			if (i > 0) System.out.print(",");
			String curr = arr[i];
			if (arr[i].length()+2 >= widthLeft)
				curr = '"'+curr.substring(0, widthLeft)+"...";
			else curr = '"'+curr+'"';
			widthLeft -= curr.length();
			System.out.print(curr);
			if (widthLeft < 3) break;
		}
		System.out.println("}");
	}
	public static void printInput(int num, String str)
	{
		System.out.printf("Case #%d Input:\n",num);
		System.out.printf("\"%s\"\n",shorten(str,MAX_WIDTH));
	}
	public static String shorten(String str, int len)
	{
		if (str.length() > len) str = str.substring(0,len)+"...";
		return str;
	}
	public static BufferedReader setupIn(String[] args)
	{
		BufferedReader in = null;
		try 
		{
			String inputFile = args[0];
			in = new BufferedReader(new FileReader(inputFile));
		} catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("Error: Valid filename required as program argument");
			System.exit(1);
		}
		return in;
	}
	
}
