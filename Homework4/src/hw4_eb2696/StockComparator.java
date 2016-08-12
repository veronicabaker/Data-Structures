
import java.util.Comparator;
import java.util.Map.Entry;

public class StockComparator implements Comparator<Entry<String, Integer>>
{
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)
	{
		if(Math.abs(o1.getValue()) < Math.abs(o2.getValue()))
		{
			return -1;
		}
		else if(Math.abs(o1.getValue()) > Math.abs(o2.getValue()))
		{
			return 1;
		}
		else
		{
			return o1.getKey().compareTo(o2.getKey()) * -1;
		}
	}
}

