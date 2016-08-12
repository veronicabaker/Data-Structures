
public interface DoubleDoubleFunction
{
	/**
	 * Applies the function to the input x.
	 * @param x the argument to the function
	 * @return the value produced by the function on input x
	 */
	double apply(double x);
	
	/**
	 * Produces an double[] array of the same length as xs.  The ith element of
	 * the returned array is obtained by applying the DoubleDoubleFunction to the ith element of xs. 
	 * @param xs the inputs
	 * @return output array of the same length as xs obtained by applying f to each input
	 */
	public default double[] apply(double[] xs)
	{
		double [] results = new double[xs.length];
		for(int i = 0; i < xs.length; i++)
		{
			results[i] = apply(xs[i]);
		}
		return results;
	}
	
	/**
	 * Returns a new function that computes f(g(x)).
	 * @param f the first function to be composed
	 * @param g the second function to be compose
	 * @return a DoubleDoubleFunction that when applied computes f(g(x))
	 */
	public static DoubleDoubleFunction compose(DoubleDoubleFunction f, DoubleDoubleFunction g)
	{
		Composite composed = new Composite(f, g);
		return composed;
	}
}

