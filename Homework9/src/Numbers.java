//Created just in case Random changed across Java versions
public class Numbers
{
	private int seed;
	private static final int a = 1664525;
	private static final int b = 1013904223;
	public Numbers(int seed) { this.seed = seed; }
	public int get() { return seed = a*seed+b; }
	public int get(int n) { return (get()&0x7FFFFFFF)%n; }
}
