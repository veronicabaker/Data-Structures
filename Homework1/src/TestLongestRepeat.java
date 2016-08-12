import java.io.BufferedReader;

public class TestLongestRepeat 
{
	public static void main(String[] args) throws Exception
	{
		BufferedReader in = TestCode.setupIn(args);
		String line;
		int testNum = 1, correct = 0, total = 0;
		long runningTime = 0;
		while ((line = in.readLine()) != null)
		{
			String input = line;
			TestCode.printInput(testNum,input);
			total++;
			String expect = in.readLine().trim();
			String your = null;
			try 
			{
				long start = System.nanoTime();
				your = Functions.longestRepeat(input)+"";
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