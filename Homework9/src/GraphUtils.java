import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class GraphUtils
{
	static class Item implements Comparable<Item>
	{
		private double cost;
		private int v;
		public Item(double c, int v)
		{
			this.cost = c;
			this.v = v;
		}
		@Override
		public int compareTo(Item o) { return Double.compare(cost, o.cost); }
	}
	public static double[] dijkstra(ArrayList<ArrayList<Integer>> adj, 
			ArrayList<ArrayList<Double>> costs, int v)
	{
		int N = adj.size();
		double[] ret = new double[N];
		Arrays.fill(ret, Double.POSITIVE_INFINITY);
		PriorityQueue<Item> pq = new PriorityQueue<>();
		pq.add(new Item(0, v));
		while (!pq.isEmpty())
		{
			Item it = pq.poll();
			if (!Double.isInfinite(ret[it.v])) continue;
			ret[it.v] = it.cost;
			for (int i = 0; i < adj.get(it.v).size(); ++i) {
				double newVal = it.cost + costs.get(it.v).get(i);
				pq.add(new Item(newVal, adj.get(it.v).get(i)));
			}
		}
		return ret;
	}
	public static double prim(ArrayList<ArrayList<Integer>> adj, 
			ArrayList<ArrayList<Double>> costs)
	{
		int N = adj.size(), cnt = 0;
		boolean[] done = new boolean[N];
		double ret = 0;
		PriorityQueue<Item> pq = new PriorityQueue<>();
		pq.add(new Item(0, 0));
		while (!pq.isEmpty())
		{
			Item it = pq.poll();
			if (done[it.v]) continue;
			done[it.v] = true;
			cnt++;
			ret += it.cost;
			for (int i = 0; i < adj.get(it.v).size(); ++i) {
				int x = adj.get(it.v).get(i);
				double newVal = costs.get(it.v).get(i);
				pq.add(new Item(newVal, x));
			}
		}
		return cnt == N ? ret : Double.POSITIVE_INFINITY;
	}
}
