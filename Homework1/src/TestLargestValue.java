import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestLargestValue
{
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader in = TestCode.setupIn(args);
		String line;
		int testNum = 1, correct = 0, total = 0;
		long runningTime = 0;
		while ((line = in.readLine()) != null)
		{
			if (line.length() == 0) continue;
			String[] arr = line.split("\\s*,\\s*",-1);
			TestCode.printInput(testNum,arr);
			total++;
			for (int i = 0; i < arr.length; ++i) 
				for (int j = 0; j < arr[i].length(); ++j)
					if (arr[i].charAt(j) < 'A' || arr[i].charAt(j) > 'Z') 
						throw new RuntimeException("Invalid Input On Case "+testNum);
			String expect = in.readLine().trim();
			String your = null;
			try 
			{
				long start = System.nanoTime();
				your = Functions.largestValue(arr)+"";
				runningTime += System.nanoTime() - start;
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			System.out.printf("Expected Output = %s\n", expect);
			System.out.printf("Your Output = %s\n", your);
			if (!expect.equals(your))
			{
				System.out.printf("Incorrect answer on case %d\n", testNum);
			}
			else 
			{
				System.out.printf("Correct answer on case %d\n", testNum);
				correct++;
			}
			testNum++;
		}
		System.out.printf("You got %d out of %d correct for %.02f%%\n",correct,total,correct*100.0/total);
		System.out.printf("Total running time is %fs\n", runningTime*1e-9);
	}
}