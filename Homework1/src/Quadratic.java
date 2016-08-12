
public class Quadratic implements DoubleDoubleFunction
{
	private double a;
	private double b;
	private double c;
	
	protected Quadratic(double a, double b, double c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}
	public double apply(double x)
	{
		return a * Math.pow(x, 2) + this.b * x + this.c;
	}
}
