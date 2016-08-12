import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Random;

import org.junit.Test;

public class HashtableMapRandomTests
{
	private HashMap<String,Integer> refMap = new HashMap<>(10,.75f);
	private HashtableMap<String,Integer> testMap = new HashtableMap<>();

	private static final String[] words = {
			"BVENDIIVLS","YLYQTRBHBI","SLOZICMHNK","XTYTVJWJQX","UHCSNJFRJD","ETBCRYFQFI","QHAUXZNABU","KNBPFPNMMF",
			"DPGRRLYTFM","YWEWAZSYPN","FAAOXDXJAG","PEBNOLWZIU","NKDUHWSZGM","TNUXKRIOJJ","YRMEXHBLJI","PEFGOWMQKX",
			"ZPQEUFSXKZ","QBVRRMBIWW","KIOSGWHGVC","KVGCKGKTSE","KTUETZUWTQ","QNPBTPTEGH","QWQDOXXNRW","DASAIBWZYO",
			"FMOUFYNFXA","DJTDZIXWJG","LNKVWRQYXI",
	};

	private Random ran = new Random(1);
	private String gen(int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; ++i) sb.append((char)('A'+ran.nextInt(26)));
		return sb.toString();
	}
	
	private static final int VALUE_MAX = 20;
	
	private void checkConsistency(String[] pool)
	{
		assertEquals(refMap.size(),testMap.size());
		for (int i = 0; i < pool.length; ++i)
		{
			assertEquals(refMap.get(pool[i]),testMap.get(pool[i]));
			assertEquals(refMap.containsKey(pool[i]),testMap.containsKey(pool[i]));
			HashtableMap.Entry<String,Integer> e = testMap.getEntry(pool[i]);
			if (!refMap.containsKey(pool[i])) assertEquals(null,e);
			else
			{
				assertEquals(pool[i],e.getKey());
				assertEquals(refMap.get(pool[i]),e.getValue());
			}
		}
		for (int i = 0; i < VALUE_MAX+5; ++i)
		{
			assertEquals(refMap.containsValue(i),testMap.containsValue(i));
		}
	}
	
	private void testRandomOperations(boolean useWords)
	{
		String[] arr = new String[1000];
		for (int i = 0; i < 1000; ++i) arr[i] = gen(10);
		String[] pool = useWords ? words : arr;
		for (int i = 0; i < 10000; ++i)
		{
			int op = ran.nextInt(2);
			if (op == 0) //put word
			{
				int n = ran.nextInt(pool.length);
				int v = ran.nextInt(VALUE_MAX);
				Integer r = refMap.put(pool[n], v);
				assertEquals(r, testMap.put(pool[n], v));						
			}
			else if (op == 1) //remove
			{
				int n = ran.nextInt(pool.length);
				Integer r = refMap.remove(pool[n]);
				assertEquals(r, testMap.remove(pool[n]));
			}
			checkConsistency(pool);
		}
	}
	
	@Test
	public void testRandomCollision() { testRandomOperations(true); }
	@Test
	public void testRandom() { testRandomOperations(false); } 
}
