
public class Composite implements DoubleDoubleFunction
{
	protected DoubleDoubleFunction f;
	protected DoubleDoubleFunction g;
	
	public Composite(DoubleDoubleFunction f, DoubleDoubleFunction g)
	{
		this.f = f;
		this.g = g;
	}

	@Override
	public double apply(double x) {
		double gx = g.apply(x);
		return f.apply(gx);
	}
}
