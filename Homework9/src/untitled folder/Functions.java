import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
 
public class Functions
{
	/**
	 * You will be given a rectangular maze.  Each character of maze will be one of the
	 * following:
	 * 1) '.' : empty space
	 * 2) 'S' : your starting location (exactly 1 of these will occur)
	 * 3) 'X' : unpassable obstruction
	 * 4) 'E' : the exit (exactly 1 of these will occur)
	 * Each turn you can move to a horizontally or vertically adjacent 
	 * unobstructed square, and you must stay within the rows and columns of
	 * the maze.  Your goal is to move from S to E in the shortest number of
	 * turns possible.  Return the minimum number of turns required, or -1 if 
	 * it is impossible.  
	 * 
	 * See the tests for examples.  [Hint: BFS; Similar code to knight moves]
	 * 
	 * @param maze the characters of the maze as described above
	 * @return the smallest number of steps required to reach the exit, 
	 * or -1 if it is impossible
	 */
	public static int closestExit(String[] maze)
	{
		return 0;
	}

	/**
	 * Each element of flights will have the form:
	 * "LOCA to LOCB for COST"
	 * Here LOCA and LOCB are Strings (with no spaces) representing locations 
	 * and COST is a double representing the cost of the 1-way flight from
	 * LOCA to LOCB.  Compute the cost of the cheapest trip from start to end.
	 * You are guaranteed that start != end and that end is reachable from start.
	 * You are also guaranteed there are at most 1000 locations.
	 * 
	 * [Hint: Use a HashMap to turn location names into ints and build adjList.]
	 * 
	 * @param flights 1-way flights as described above
	 * @param start the starting location
	 * @param end the target location
	 * @return the cost of the cheapest trip from start to end using the 
	 * 1-way flights in flights
	 */
	public static double cheapestTrip(String[] flights, String start, String end)
	{
		HashMap<String, Integer> map = new HashMap<>();
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(flights.length + 1);
		ArrayList<ArrayList<Double>> costs = new ArrayList<>(flights.length + 1);
		for(int i = 0; i <= flights.length; i++)
		{
			adjList.add(new ArrayList<Integer>());
			costs.add(new ArrayList<Double>());
		}
		for(int i = 0; i < flights.length; i++)
		{
			String[] info = flights[i].split(" ");
			adjList.get(getIndex(map, info[0])).add(getIndex(map, info[2]));
			costs.get(getIndex(map, info[0])).add(Double.parseDouble(info[4]));
		}
		double[] ret = dijkstra(adjList, costs, getIndex(map, start));
		return ret[getIndex(map, end)];
	}
	
	public static double[] dijkstra(ArrayList<ArrayList<Integer>> adj, 
			ArrayList<ArrayList<Double>> costs, int v)
	{
		int n = adj.size();
		double[] ret = new double[n + 1];
		Arrays.fill(ret, Double.POSITIVE_INFINITY);
		PriorityQueue<Item> pq = new PriorityQueue<>();
		pq.add(new Item(0, v));
		while(!pq.isEmpty())
		{
			Item it = pq.poll();
			if(!Double.isInfinite(ret[it.v]))
			{
				continue;
			}
			ret[it.v] = it.cost;
			ArrayList<Integer> nbrs = adj.get(it.v);
			double cost = it.cost;
			for(int i = 0; i < nbrs.size(); ++i)
			{
				int w = nbrs.get(i);
				double edgeCost = costs.get(it.v).get(i);
				pq.add(new Item(cost + edgeCost, w));
			}
		}
		return ret;
	}
	
	public static int getIndex(HashMap<String, Integer> map, String name)
	{
		if(map.containsKey(name))
		{
			return map.get(name);
		}
		int s = map.size();
		map.put(name, s);
		return s;
	}
	
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
		public int compareTo(Item o)
		{
			return Double.compare(cost, o.cost);
		}
	}

	/**
	 * Each element of links will have the form:
	 * "NODEA NODEB COST"
	 * where NODEA and NODEB are integers between 0 and N-1, inclusive,
	 * and COST is a double representing the cost of building a 2-way (undirected) link
	 * between NODEA and NODEB.  We want to ensure that all nodes are in the same
	 * connected component, but at the cheapest price possible.  Return that cheapest cost.
	 * You are guaranteed that all N nodes can be connected.
	 *
	 * @param N number of nodes in the network
	 * @param links see above for formatting
	 * @return the cost of the minimum cost network that connects all of the nodes.
	 */
	
	//build and adjancency list of costs
	public static double minCostNetwork(int N, String[] links)
	{
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>(N);
		ArrayList<ArrayList<Double>> costs = new ArrayList<>(N);
		for(int i = 0; i <= links.length; i++)
		{
			adj.add(new ArrayList<Integer>());
			costs.add(new ArrayList<Double>());
		}
		String[] info = new String[3];
		for(int i = 0; i < links.length; i++)
		{
			info = links[i].split(" ");
			if(!adj.get(Integer.parseInt(info[1])).contains(Integer.parseInt(info[0])))
			{
				adj.get(Integer.parseInt(info[0])).add(Integer.parseInt(info[1]));
				costs.get(Integer.parseInt(info[0])).add(Double.parseDouble(info[2]));
			}
		}
		double[] ret = dijkstra(adj, costs, Integer.parseInt(info[0]));
		double cost = 0;
		System.out.println(costs.size());
		for(int i = 0; i < costs.size(); i++)
		{
			if(!costs.get(i).isEmpty())
			{
				Collections.sort(costs.get(i));
				cost += costs.get(i).get(0);
			}
		}
		return cost;
	}
			
	/**
	 * You are given an matrix describing an undirected connected graph 
	 * with at least 2 vertices.  The lengths of each edge are stored 
	 * in dists. dists[i][j] will be Infinity if there is no edge, and
	 * the edge's length if there is an edge between i and j.  
	 * The diameter of a graph is the max distance between 
	 * any pair of vertices.  Here the length of a path is the sum of the
	 * lengths of its edges, and the distance between two vertices is the length
	 * of the shortest path between them.
	 *
	 * You are guaranteed that dists[i][j] == dist[j][i] and dists[i][i] = Infinity.
	 * 
	 * @param dists matrix of lengths of the edges
	 * @return the diameter of the graph
	 */
	public static double diameter(double[][] dists)
	{
		int size = dists[0].length;
		double diameter = 0;
		
		double[][]  graph = dists.clone();
		
		floydWarshall(graph);
		
		for(int i = 0; i < size; i++)
		{
			for(int j = i + 1; j < size; j++)
			{
				if(graph[i][j] != Double.POSITIVE_INFINITY)
				{
					if(diameter < graph[i][j])
					{
						diameter = graph[i][j];
					}
				}
			}
		}
		return diameter;
	}
	
	public static void floydWarshall(double[][] graph)
	{
		int size = graph[0].length;
		
		
		for(int k = 0; k < size; k++)
		{
			for(int i = 0; i < size; i++)
			{
				for(int j = 0; j < size; j++)
				{
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
	}
	
	
	/**
	 * (Extra Credit)
	 * You have 2 buckets with capacities (in liters) given in the array caps.  
	 * There is also a special container with a very large capacity.  
	 * 
	 * Each turn you can perform one of the following steps:
	 * 1) Use the faucet to add water to a bucket until it is full.
	 * 2) Spill out a bucket into the drain until it is empty.
	 * 3) Pour the contents of one bucket into the other until the receiving
	 * bucket is full, or the pouring bucket is empty (whichever occurs first).
	 * 4) Pour the entire contents of a bucket into the special container.
	 * 
	 * Return the smallest number of turns needed to make the special container
	 * have exactly target liters of water.  If this is impossible, return -1.  
	 * Note that you can never remove water from the special container.  Each
	 * capacity will be between 0 and 100 inclusive.  A capacity of 0 means the 
	 * corresponding bucket is unusable.  
	 * 
	 * @param caps an array of 2 capacities as described above
	 * @param target the required number of liters of water needed in the special container
	 * @return the smallest number of steps as described above, or -1 if it is impossible
	 */
	public static int minSteps(int[] caps, int target)
	{
		return 0;
	}
}
