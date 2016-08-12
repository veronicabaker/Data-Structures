import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;



/**
 * Implements a hashtable using chaining.  If the load factor is ever strictly larger than 
 * the prespecified threshold, then the table automatically resizes.  
 * 
 * Unlike the Java API we do not allow null keys.  We also differ from the Java API in 
 * that we use K as the argument for methods like get, containsKey, etc.  The Java API uses
 * Object for the type in those methods for maximum flexibility but here we go for maximum
 * clarity and type safety.
 *
 * @param <K>
 * @param <V>
 */
public class HashtableMap<K, V>
{
	public static class Entry<Key,Value>
	{
		private Key key;
		private Value value;
		
		public Key getKey() 
		{ 
			return key; 
		}
		public Value getValue() 
		{ 
			return value; 
		}
		
		public Entry(Key k, Value v)
		{
			key = k;
			value = v;
		}
	}
	
	private ArrayList<LinkedList<Entry<K,V>>> table;
	private double threshold;
	private int size = 0;
	
	/**
	 * Creates a hashtable with initCap buckets and a threshold of thresh
	 * on the load factor.  If the load factor becomes larger than thresh then 
	 * the table will be resized (by doubling).
	 * 
	 * @param initCap
	 * @param thresh
	 */
	public HashtableMap(int initCap, double thresh)
	{
		threshold = thresh;
		table = new ArrayList<LinkedList<Entry<K, V>>>(initCap);
		for(int i = 0; i < initCap; i++)
		{
			table.add(null);
		}
	}

	public HashtableMap() 
	{
		this(10,.75); 
	}
	
	//Constant used by getBucket
	private static final double HASH_MULT = (Math.sqrt(5)-1)/2;
	
	//Given a key and the number of buckets, returns the bucket number using key.hashCode()
	private static int getBucket(Object key, int numBuckets) 
	{ 
		int hash = key.hashCode();
		double x = Integer.toUnsignedLong(hash)*HASH_MULT;
		return (int)(numBuckets*(x - Math.floor(x)));
	}
		
	/**
	 * If the key is contained in the map, returns the corresponding Entry.  Otherwise, returns null.
	 * 
	 * @param key the key to look for
	 * @return the corresponding Entry, or null if the key is not present in the map.
	 * @throws NullPointerException if key is null
	 */
	public Entry<K,V> getEntry(K key) throws NullPointerException
	{
		if(key == null)
		{
			throw new NullPointerException();
		}
		int i = getBucket(key, table.size());
		if(table.get(i) != null)
		{
			for(Iterator<Entry<K, V>> it = table.get(i).iterator(); it.hasNext();)
			{
				Entry<K, V> item = it.next();
				if(item.getKey().equals(key))
				{
					return item;
				}
			}
		}
		return null;
	}
	
	/**
	 * If the key is contained in the map, returns the corresponding value.  Otherwise, returns null.
	 * Note that this method could return null even if the key is contained in the map provided
	 * the corresponding value is null.
	 * 
	 * @param key the key to look for
	 * @return the corresponding value, or null if the key is not present in the map.
	 * @throws NullPointerException if key is null
	 */
	public V get(K key) throws NullPointerException
	{ 
		if(key == null)
		{
			throw new NullPointerException();
		}
		if(getEntry(key) == null)
		{
			return null;
		}
		else
		{
			return getEntry(key).getValue();
		}
	}
	
	/**
	 * Removes the key from the map if present, and returns the corresponding value.  If the
	 * key is not present, returns null.
	 * 
	 * @param key the key to remove
	 * @return removes the key if present and returns the corresponding value; returns null otherwise
	 * @throws NullPointerException if key is null
	 */
	public V remove(K key) throws NullPointerException
	{
		if(key == null)
		{
			throw new NullPointerException();
		}
		if(containsKey(key))
		{
			int i = getBucket(key, table.size());
			for(Iterator<Entry<K, V>> it = table.get(i).iterator(); it.hasNext();)
			{
				Entry<K, V> item = it.next();
				if(item.getKey().equals(key))
				{
					V v = item.getValue();
					it.remove();
					size -= 1;
					return v;
				}
			}
		}
		return null;
	}

	/**
	 * Returns whether or not the key is present in the map.
	 * 
	 * @param key to look up
	 * @return true if the key is present, false otherwise
	 * @throws NullPointerException if key is null
	 */
	public boolean containsKey(K key) throws NullPointerException
	{ 
		return getEntry(key) != null;
	}
	
	/**
	 * Returns whether or not the value is present in the map.  Note the value may be null.  
	 *  
	 * @param value the value to look for
	 * @return true if the given value is found in the map, false otherwise
	 */
	public boolean containsValue(V value) 
	{ 
		for(int i = 0; i < table.size(); i++)
		{
			if(table.get(i) != null)
			{
				for(Iterator<Entry<K, V>> it = table.get(i).iterator(); it.hasNext();)
				{
					Entry<K, V> item = it.next();
					if(value == null)
					{
						if(item.getValue() == null)
						{
							return true;
						}
					}
					else if(item.getValue().equals(value))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * If the key is not contained in the map, it adds the key to the map and associates the given value to it.
	 * If the key is already contained in the map, it updates the corresponding value.
	 * Returns null if the key is not already contained in the map.  Otherwise, returns the old value.
	 * After a key is added, this could trigger a table resizing if the load factor threshold is violated.
	 * 
	 * @param key the key to assign a value to
	 * @param value the value for the corresponding key
	 * @return the old value associated with the key, or null if the key isn't present in the map
	 * @throws NullPointerException if key is null
	 */
	public V put(K key, V value) throws NullPointerException
	{ 
		if(key == null)
		{
			throw new NullPointerException();
		}
		if(!containsKey(key))
		{
			int bucket = getBucket(key, buckets());
			if(table.get(bucket) != null)
			{
				table.get(bucket).add(new Entry<K, V>(key, value));
			}
			else
			{
				LinkedList<Entry<K, V>> newList = new LinkedList<Entry<K, V>>();
				newList.add(new Entry<K, V>(key, value));
				table.set(bucket, newList);
			}
			size += 1;
			resize();
			return null;
		}
		else
		{
			V v = getEntry(key).getValue();
			int i = getBucket(key, table.size());
			for(Iterator<Entry<K, V>> it = table.get(i).iterator(); it.hasNext();)
			{
				Entry<K, V> item = it.next();
				if(item.getKey().equals(key))
				{
					item.value = value;
					return v;
				}
			}
		}
		return null;
	}
	
	public void resize()
	{
		if(size*1.0 / buckets() > threshold)
		{
			ArrayList<LinkedList<Entry<K,V>>> newTable = new ArrayList<LinkedList<Entry<K, V>>>(table.size() * 2);
			for(int i = 0; i < table.size() * 2; i++)
			{
				newTable.add(i, null);
			}
			for(int i = 0; i < table.size(); i++)
			{
				if(table.get(i) != null)
				{
					for(Iterator<Entry<K, V>> it = table.get(i).iterator(); it.hasNext();)
					{
						Entry<K, V> item = it.next();
						int bucket = getBucket(item.key, table.size() * 2);
						if(newTable.get(bucket) != null)
						{
							newTable.get(bucket).add(new Entry<K, V>(item.key, item.value));
						}
						else
						{
							LinkedList<Entry<K, V>> newList = new LinkedList<Entry<K, V>>();
							newList.add(new Entry<K, V>(item.key, item.value));
							newTable.set(bucket, newList);
						}
					}
				}
			}
			this.table = newTable;
		}
	}
	
	/**
	 * Returns the number of keys in the map.
	 * @return the number of keys in the map
	 */
	public int size() 
	{ 
		return size;
	}
	
	/**
	 * Returns the number of buckets in the bucket table.
	 * @return the number of buckets in the bucket table.
	 */
	public int buckets()
	{
		return table.size();
	}
}
