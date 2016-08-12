import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;

public class TestDoubleDoubleFunction
{
	public static final double EPS = 1e-9;
	public static int total = 0, correct = 0;
	public static void test(DoubleDoubleFunction comp, DoubleDoubleFunction exp, double x)
	{
		total++;
		try
		{
			double computed = comp.apply(x), expected = exp.apply(x);
			boolean isCorrect = Math.abs(computed-expected) < EPS;
			System.out.printf("Test %d: Computed = %f, Expected = %f\n",total,computed,expected);
			System.out.printf("Computed Value Was %s\n", isCorrect ? "Correct" : "Incorrect");
			correct += isCorrect ? 1 : 0;
		} catch (Exception e)
		{
			System.out.printf("Test %d:\n",total);
			e.printStackTrace();
			System.out.println("Computed Value Was Incorrect\n");
		}
	}
	public static void test(DoubleDoubleFunction comp, DoubleDoubleFunction exp, double[] xs)
	{
		total++;
		try
		{
			DoubleUnaryOperator op = new DoubleUnaryOperator() {
				public double applyAsDouble(double x) { return exp.apply(x); }
			};
			double[] cxs = Arrays.copyOf(xs, xs.length);
			double[] computed = comp.apply(cxs);
			double[] expected = Arrays.stream(xs).map(op).toArray();
			boolean isCorrect = true;
			for (int i = 0; i < xs.length; ++i) 
				if (Math.abs(computed[i]-expected[i]) >= EPS) isCorrect = false;
			System.out.printf("Test %d:\n", total);
			System.out.printf("Computed = %s\n", Arrays.toString(computed));
			System.out.printf("Expected = %s\n", Arrays.toString(expected));
			System.out.printf("Computed Value Was %s\n", isCorrect ? "Correct" : "Incorrect");
			correct += isCorrect ? 1 : 0;
		} catch (Exception e)
		{
			System.out.printf("Test %d:\n",total);
			e.printStackTrace();
			System.out.println("Computed Value Was Incorrect\n");
		}
	}
	public static void main(String[] args)
	{
		double[] vals1 = {-2,-1,0,1,2};
		double[] vals2 = {-1000,-10,-9.5,-9,-8,-7.1,-2,-1,0,1,2,7.1,8,9,9.5,10,1000};
		Quadratic c = new Quadratic(0,0,2);
		DoubleDoubleFunction tc = (x)->2;
		Quadratic l = new Quadratic(0,3,4);
		DoubleDoubleFunction tl = (x)->3*x+4;
		Quadratic q = new Quadratic(1,2,3);
		DoubleDoubleFunction tq = (x)->x*x+2*x+3;
		AbsoluteValue av = new AbsoluteValue();
		DoubleDoubleFunction tav = (x)->Math.abs(x);
		DoubleDoubleFunction cp1 = DoubleDoubleFunction.compose(av, q);
		DoubleDoubleFunction tcp1 = (x)->Math.abs(tq.apply(x));
		DoubleDoubleFunction cp2 = DoubleDoubleFunction.compose(q, av);
		DoubleDoubleFunction tcp2 = (x)->tq.apply(Math.abs(x));
		DoubleDoubleFunction cp3 = DoubleDoubleFunction.compose(q, q);
		DoubleDoubleFunction tcp3 = (x)->tq.apply(tq.apply(x));
		DoubleDoubleFunction cp4 = DoubleDoubleFunction.compose(q, cp1);
		DoubleDoubleFunction tcp4 = (x)->tq.apply(tcp1.apply(x));

		System.out.println("Initial Tests");
		test(c,tc,5);
		test(l,tl,1);
		test(l,tl,2);
		test(q,tq,3);
		test(av,tav,-8);
		test(av,tav,9);


		System.out.println("Composite Tests");
		test(cp1,tcp1,2);
		test(cp1,tcp1,-10);

		System.out.println("Vals1 Tests");
		test(cp1,tcp1,vals1);
		test(cp2,tcp2,vals1);
		test(cp3,tcp3,vals1);
		test(cp4,tcp4,vals1);

		System.out.println("Vals2 Tests");
		test(cp1,tcp1,vals2);
		test(cp2,tcp2,vals2);
		test(cp3,tcp3,vals2);
		test(cp4,tcp4,vals2);

		System.out.printf("Got %d out of %d correct for %.02f%%\n",correct,total,correct*100.0/total);
	}
}