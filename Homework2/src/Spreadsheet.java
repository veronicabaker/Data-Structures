/**
 * A 2-dimensional spreadsheet of doubles that always has at least 
 * 1 row and 1 column.  Every row must have the same number of 
 * columns and every column must have the same number of rows.  
 * All row and column indices are 0-based.
 *
 */
public interface Spreadsheet
{
	/**
	 * Returns the number of rows in the spreadsheet.
	 * @return the number of rows in the spreadsheet
	 */
	int getNumRows();

	/**
	 * Returns the number of columns in the spreadsheet.
	 * @return the number of columns in the spreadsheet
	 */
	int getNumCols();

	/**
	 * If r is equal to getNumRows(), then adds a new row at the end of the 
	 * spreadsheet. Otherwise, it inserts a row before the rth row shifting 
	 * the rows r and below down. The newly added row should be entirely zero 
	 * and have exactly getNumCols() columns. 
	 * @param r the position to add the row at
	 */
	void addRow(int r);
	
	/**
	 * If c is equal to getNumCols(), then adds a new column at the end of the 
	 * spreadsheet. Otherwise, it inserts a column before the cth column 
	 * shifting the columns c and after to the right.
	 * The newly added column should be entirely zero and have 
	 * exactly getNumRows() rows. 
	 * @param c the position to add the column at
	 */
	void addCol(int c);
	
	/**
	 * Deletes the rth row and shifts all rows afterward up.
	 * Throws an IllegalArgumentException if there is only 1 row.
	 * @param r the row to delete
	 */
	void deleteRow(int r);

	/**
	 * Deletes the cth column and shifts all columns afterward left.
	 * Throws an IllegalArgumentException if there is only 1 column.
	 * @param c the column to delete
	 */
	void deleteCol(int c);
	
	/**
	 * Returns the value in row r column c.
	 * @param r the row
	 * @param c the column
	 * @return returns the value in row r column c
	 */
	double get(int r, int c);

	/**
	 * Sets the value in row r column c to v.
	 * @param r the row
	 * @param c the column
	 * @param v the value
	 * @return sets the value in row r column c to v
	 */
	void set(int r, int c, double v);
	
	/**
	 * Sorts the rows of the spreadsheet by the values in column c 
	 * in non-descending order. The sort should be stable: 
	 * if two rows have the same value in column c, then they 
	 * should be in the same relative ordering after the sort as before.  
	 * We recommend that you use Collections.sort as it is both fast and stable.
	 * @param c to column determining how the rows should be sorted
	 */
	void sortRowsByCol(int c);
	
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
	Spreadsheet getCounts();
}
