import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SpreadsheetImpl implements Spreadsheet
{
	//member variable
	private ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
	
	//constructor
	public SpreadsheetImpl()
	{
		//creates a 0.0 entry
		ArrayList<Double> defaultEntry = new ArrayList<Double>();
		defaultEntry.add(0.0);
		data.add(defaultEntry);
	}
	/**
	 * Returns the number of rows in the spreadsheet.
	 * @return the number of rows in the spreadsheet
	 */
	public int getNumRows()
	{
		return data.size();
	}

	/**
	 * Returns the number of columns in the spreadsheet.
	 * @return the number of columns in the spreadsheet
	 */
	public int getNumCols()
	{
		return data.get(0).size();
	}

	/**
	 * If r is equal to getNumRows(), then adds a new row at the end of the 
	 * spreadsheet. Otherwise, it inserts a row before the rth row shifting 
	 * the rows r and below down. The newly added row should be entirely zero 
	 * and have exactly getNumCols() columns. 
	 * @param r the position to add the row at
	 */
	public void addRow(int r)
	{
		//create a new row
		ArrayList<Double> additionalRow = new ArrayList<Double>();
		//populate the new row with 0.0 entries
		for(int i = 0; i < this.getNumCols(); i++)
		{
			additionalRow.add(0.0);
		}
		//add a row at the end if r = the num of rows
		if(r == this.getNumRows())
		{
			data.add(additionalRow);
		}
		//otherwise specify the placement of the new row
		else
		{
			if(r == 0)
			{
				data.add(0, additionalRow);
			}
			else
			{
				data.add(r, additionalRow);	
			}
		}
	}
	
	/**
	 * If c is equal to getNumCols(), then adds a new column at the end of the 
	 * spreadsheet. Otherwise, it inserts a column before the cth column 
	 * shifting the columns c and after to the right.
	 * The newly added column should be entirely zero and have 
	 * exactly getNumRows() rows. 
	 * @param c the position to add the column at
	 */
	public void addCol(int c)
	{
		//if c = num of columns
		//add a new column by adding to the end of each row
		if(c == this.getNumCols())
		{
			for(int i = 0; i < data.size(); i++)
			{
				data.get(i).add(0.0);
			}
		}
		//otherwise specify the placement of the column
		else
		{
			for(int i = 0; i < data.size(); i++)
			{
				if(c == 0)
				{
					data.get(i).add(0, 0.0);;
				}
				else
				{
					data.get(i).add(c, 0.0);
				}
			}
		}
	}
	
	/**
	 * Deletes the rth row and shifts all rows afterward up.
	 * Throws an IllegalArgumentException if there is only 1 row.
	 * @param r the row to delete
	 */
	public void deleteRow(int r)
	{
		if(this.getNumRows() == 1)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			data.remove(r);
		}
	}

	/**
	 * Deletes the cth column and shifts all columns afterward left.
	 * Throws an IllegalArgumentException if there is only 1 column.
	 * @param c the column to delete
	 */
	public void deleteCol(int c)
	{
		if(this.getNumCols() == 1)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			for(int i = 0; i < data.size(); i++)
			{
				data.get(i).remove(c);
			}
		}
	}
	
	/**
	 * Returns the value in row r column c.
	 * @param r the row
	 * @param c the column
	 * @return returns the value in row r column c
	 */
	public double get(int r, int c)
	{
		return data.get(r).get(c);
	}

	/**
	 * Sets the value in row r column c to v.
	 * @param r the row
	 * @param c the column
	 * @param v the value
	 * @return sets the value in row r column c to v
	 */
	public void set(int r, int c, double v)
	{
		data.get(r).set(c, v);
	}
	
	/**
	 * Sorts the rows of the spreadsheet by the values in column c 
	 * in non-descending order. The sort should be stable: 
	 * if two rows have the same value in column c, then they 
	 * should be in the same relative ordering after the sort as before.  
	 * We recommend that you use Collections.sort as it is both fast and stable.
	 * @param c to column determining how the rows should be sorted
	 */
	public void sortRowsByCol(int c)
	{
		Collections.sort(data, new ColumnComparator(c));
	}
	
	/**
	 * Extra Credit:
	 * Returns a new spreadsheet with exactly 2 columns.  The first column 
	 * contains all of the distinct values of this spreadsheet in ascending 
	 * order.  The second column contains how many times the value in the first
	 * column occurs in this spreadsheet.  The number of rows in the returned 
	 * spreadsheet will be the number of distinct values in this spreadsheet.
	 * 
	 * @return a 2 column spreadsheet containing the frequencies of occurrence 
	 * of each value in this spreadsheet as described above
	 */
	public Spreadsheet getCounts()
	{
		//create a new spreadsheet object
		Spreadsheet counts = new SpreadsheetImpl();
		//make it 2 columned
		counts.addCol(0);
		//make a count variable to keep track of the num of entries in spreadsheet
		int count = 0;
		//loop through each entry of the origional spreadsheet
		//using nested for loops
		for(int i = 0; i < data.size(); i++)
		{
			for(int j = 0; j < data.get(i).size(); j++)
			{
				//initiate a boolean to tell us if
				//the entry is in our count spreadsheet already
				boolean found = false;
				//loop through the count spreadsheet
				for(int k = 0; k <= count; k++)
				{
					//if the number is in counts
					//increment its count
					if(counts.get(k, 0) == data.get(i).get(j))
					{
						Double v = counts.get(k, 1) + 1.0;
						counts.set(k, 1, v);
						found = true;
					}
				}
				//otherwise add an entry
				if(found == false)
				{
					counts.addRow(counts.getNumRows());
					counts.set(counts.getNumRows() - 1, 0, data.get(i).get(j));
					counts.set(counts.getNumRows() - 1, 1, 1);
					count++;
				}
			}
		}
		//sort the spreadsheet
		counts.sortRowsByCol(0);
		//if there is an entry with 0.0 as count
		//delete it
		for(int i = 0; i < counts.getNumRows(); i++)
		{
			if(counts.get(i, 1) == 0.0)
			{
				counts.deleteRow(i);
			}
		}
		return counts;
	}
}
