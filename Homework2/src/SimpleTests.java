
public class SimpleTests
{
	public static void assertEquals(double val, double exp)
	{
		String s = String.format("Got %s, Expected %s",
				String.valueOf(val), String.valueOf(exp));
		if (val != exp) throw new AssertionError(s);
	}
	public static void assertEquals(int val, int exp)
	{
		String s = String.format("Got %s, Expected %s",
				String.valueOf(val), String.valueOf(exp));
		if (val != exp) throw new AssertionError(s);
	}
	public static void testConstruct()
	{
		Spreadsheet s = new SpreadsheetImpl();
		assertEquals(s.getNumCols(),1);
		assertEquals(s.getNumRows(),1);
		assertEquals(s.get(0, 0), 0.0);
	}
	public static void testAddRowCol()
	{
		Spreadsheet s = new SpreadsheetImpl();
		s.addRow(1);
		s.addRow(2);
		s.addCol(1);
		assertEquals(s.getNumRows(),3);
		assertEquals(s.getNumCols(),2);
	}
	public static void testSet()
	{
		Spreadsheet s = new SpreadsheetImpl();
		s.addRow(0);
		s.addCol(0);
		s.set(0, 0, 1);
		s.set(0, 1, 2);
		s.set(1, 0, 3);
		s.set(1, 1, 4);
		assertEquals(s.get(0, 0),1);
		assertEquals(s.get(0, 1),2);
		assertEquals(s.get(1, 0),3);
		assertEquals(s.get(1, 1),4);
	}
	public static void testInsertRow()
	{
		Spreadsheet s = new SpreadsheetImpl();
		s.set(0,0,1);
		s.addRow(0);
		assertEquals(s.get(0, 0),0);
		s.addRow(1);
		assertEquals(s.get(1, 0),0);
		assertEquals(s.get(2, 0),1);
	}
	
	public static void testInsertCol()
	{
		Spreadsheet s = new SpreadsheetImpl();
		s.set(0,0,1);
		s.addCol(0);
		assertEquals(s.get(0, 0),0);
		s.addCol(1);
		assertEquals(s.get(0, 1),0);
		assertEquals(s.get(0, 2),1);
	}
	public static void testDeleteRowExc()	
	{
		Spreadsheet s = new SpreadsheetImpl();
		s.addRow(0);
		s.deleteRow(0);
		try {
			s.deleteRow(0);
		} catch (IllegalArgumentException e) 
		{
			return;
		}
		throw new AssertionError("Expected IllegalArgumentException");
	}
	public static void testDeleteColExc()	
	{
		Spreadsheet s = new SpreadsheetImpl();
		s.addCol(0);
		s.deleteCol(0);
		try {
			s.deleteCol(0);
		} catch (IllegalArgumentException e) 
		{
			return;
		}
		throw new AssertionError("Expected IllegalArgumentException");
	}
	static Spreadsheet setup(double[][] arr)
	{
		Spreadsheet si = new SpreadsheetImpl();
		while (si.getNumRows() < arr.length) si.addRow(0);
		while (si.getNumCols() < arr[0].length) si.addCol(0);
		for (int r = 0; r < arr.length; ++r)
			for (int c = 0; c < arr[0].length; ++c)
				si.set(r, c, arr[r][c]);
		return si;
	}
	static String str(double[][] arr)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for (int i = 0; i < arr.length; ++i)
		{
			for (int j = 0; j < arr[0].length; ++j)
			{
				if (j > 0) sb.append(",");
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		sb.append(']');
		return sb.toString();
	}
	static String str(Spreadsheet s)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for (int i = 0; i < s.getNumRows(); ++i)
		{
			for (int j = 0; j < s.getNumCols(); ++j)
			{
				if (j > 0) sb.append(",");
				sb.append(s.get(i, j));
			}
			sb.append('\n');
		}
		sb.append(']');
		return sb.toString();
	}
	static void testArray(Spreadsheet s, double[][] arr)
	{
		for (int i = 0; i < arr.length; ++i)
			for (int j = 0; j < arr[0].length; ++j)
				if (s.get(i, j) != arr[i][j])
				{
					String st = String.format("\nGot\n%s\nExpected\n%s\n",str(s), str(arr));
					throw new AssertionError(st);
				}
	}
	public static void testSort()
	{
		double[][] data = {{9,9,9},{9,8,7},{1,2,3},{2,1,0},{9,6,4}};
		double[][] data0 = {{1,2,3},{2,1,0},{9,9,9},{9,8,7},{9,6,4}};
		double[][] data1 = {{2,1,0},{1,2,3},{9,6,4},{9,8,7},{9,9,9}};
		double[][] data2 = {{2,1,0},{1,2,3},{9,6,4},{9,8,7},{9,9,9}};
		Spreadsheet s0 = setup(data);
		Spreadsheet s1 = setup(data);
		Spreadsheet s2 = setup(data);
		s0.sortRowsByCol(0);
		s1.sortRowsByCol(1);
		s2.sortRowsByCol(2);
		testArray(s0,data0);
		testArray(s1,data1);
		testArray(s2,data2);
	}
	public static void testCounts()
	{
		double[][] data = {{9,9,9},{9,8,7},{1,2,3},{2,1,0},{9,6,4}};
		double[][] cnts = {{0,1},{1,2},{2,2},{3,1},{4,1},{6,1},{7,1},{8,1},{9,5}};
		Spreadsheet s = setup(data);
		Spreadsheet c = s.getCounts();
		testArray(c,cnts);
	}
	public static void main(String[] args)
	{
		testConstruct();
		testAddRowCol();
		testSet();
		testInsertCol();
		testInsertRow();
		testDeleteColExc();
		testDeleteRowExc();
		testSort();
		System.out.println("All Simple Unit Tests Succeeded");
		testCounts();
		System.out.println("Counts Test Succeeded");
	}
}
