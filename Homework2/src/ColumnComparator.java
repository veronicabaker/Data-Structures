import java.util.ArrayList;
import java.util.Comparator;

public class ColumnComparator implements Comparator<ArrayList<Double>>
{
	
	private int column;
	
	public ColumnComparator(int column)
	{
		this.column = column;
	}

	public int compare(ArrayList<Double> o1, ArrayList<Double> o2) 
	{
		if(o1.get(column) == o2.get(column))
		{
			return 0;
		}
		else if(o1.get(column) < o2.get(column))
		{
			return -1;
		}
		return 1;
	}

}
