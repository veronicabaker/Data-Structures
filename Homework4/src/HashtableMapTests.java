import static org.junit.Assert.*;

import org.junit.Test;

public class HashtableMapTests
{
	private HashtableMap<String,Integer> testMap = new HashtableMap<>();
	
	@Test
	public void testConstructor()
	{
		assertEquals(10,testMap.buckets());
		assertEquals(0,testMap.size());
	}
	
	@Test
	public void testPut()
	{
		testMap.put("Wow", 10);
		assertTrue(testMap.containsKey("Wow"));
		assertEquals(10,testMap.get("Wow").intValue());
		assertEquals(1,testMap.size());
		HashtableMap.Entry<String, Integer> e = testMap.getEntry("Wow");
		assertEquals("Wow",e.getKey());
		assertEquals(10,e.getValue().intValue());
	}
	
	@Test
	public void testDoublePut()
	{
		Integer i1 = testMap.put("Wow", 10);
		assertEquals(null,i1);
		Integer i2 = testMap.put("Wow", 9);
		assertTrue(testMap.containsKey("Wow"));
		assertEquals(10,i2.intValue());
		assertEquals(9,testMap.get("Wow").intValue());
		assertEquals(1,testMap.size());
		HashtableMap.Entry<String, Integer> e = testMap.getEntry("Wow");
		assertEquals("Wow",e.getKey());
		assertEquals(9,e.getValue().intValue());
	}

	@Test
	public void testPutNull()
	{
		testMap.put("Hmm", null);
		assertEquals(1, testMap.size());
		assertEquals(null, testMap.get("Hmm"));
		HashtableMap.Entry<String, Integer> e = testMap.getEntry("Hmm");
		assertEquals("Hmm",e.getKey());
		assertEquals(null,e.getValue());
		assertTrue(testMap.containsValue(null));

	}
	
	@Test
	public void testRemove()
	{
		assertEquals(null,testMap.remove("WOW"));
		testMap.put("HMM", 14);
		Integer i = testMap.remove("HMM");
		assertEquals(14,i.intValue());
		assertEquals(0,testMap.size());
	}

	@Test
	public void testCollision()
	{
		String[] arr = {"QAOFSEBDCT", "BGHSRBVVZF", "IGEBYTMNZO", "KYFRRYVARR"};
		for (int i = 0; i < arr.length; ++i) testMap.put(arr[i], i);
		assertEquals(4,testMap.size());
		for (int i = 0; i < arr.length; ++i)
		{
			assertTrue(testMap.containsKey(arr[i]));
			assertTrue(testMap.containsValue(i));
			HashtableMap.Entry<String, Integer> e = testMap.getEntry(arr[i]);
			assertEquals(arr[i],e.getKey());
			assertEquals(i,e.getValue().intValue());
			assertEquals(i,testMap.get(arr[i]).intValue());
		}
		Integer i = testMap.remove(arr[0]);
		assertEquals(0,i.intValue());
		assertEquals(3,testMap.size());
	}
	
	@Test
	public void testMultiPutRemove()
	{
		for (int i = 0; i < 1000; ++i) testMap.put(i+"", i);
		assertEquals(1000,testMap.size());
		assertEquals(2560,testMap.buckets());
		for (int i = 0; i < 1000; ++i) assertEquals(i,testMap.get(i+"").intValue());
		for (int i = 0; i < 1000; ++i)
		{
			assertEquals(i,testMap.remove(i+"").intValue());
			assertEquals(1000-i-1,testMap.size());
		}		
	}

	@Test
	public void testMultiPutThresh()
	{
		testMap = new HashtableMap<>(2,.5);
		int b = 2;
		for (int i = 0; i < 1000; ++i) 
		{
			testMap.put(i+"", i);
			assertEquals(i+1,testMap.size());
			if (2*testMap.size()>b) b*=2;
			else assertEquals(b,testMap.buckets());
		}
		for (int i = 0; i < 1000; ++i) assertEquals(i,testMap.get(i+"").intValue());
		for (int i = 0; i < 1000; ++i)
		{
			assertEquals(i,testMap.remove(i+"").intValue());
			assertEquals(1000-i-1,testMap.size());
		}		
	}
	
	@Test
	public void testContainsValue()
	{
		for (int i = 0; i < 1000; ++i) testMap.put(i+"", i%10);
		for (int i = 0; i < 20; ++i) assertEquals(i<10,testMap.containsValue(i));
		assertFalse(testMap.containsValue(null));
	}

	@Test(expected=NullPointerException.class)
	public void testPutException()
	{
		testMap.put(null, 10);
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveException()
	{
		testMap.remove(null);
	}

	@Test(expected=NullPointerException.class)
	public void testGetEntryException()
	{
		testMap.getEntry(null);
	}

	@Test(expected=NullPointerException.class)
	public void testGetException()
	{
		testMap.get(null);
	}

	@Test(expected=NullPointerException.class)
	public void testContainsKeyException()
	{
		testMap.containsKey(null);
	}
	
}
