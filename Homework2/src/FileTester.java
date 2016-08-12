import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileTester
{
	static void outputSpreadsheet(Spreadsheet s)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(s.getNumRows()).append(' ').append(s.getNumCols()).append('\n');
		sb.append("[\n");
		for (int r = 0; r < s.getNumRows(); ++r)
		{
			for (int c = 0; c < s.getNumCols(); ++c)
			{
				sb.append(s.get(r, c)).append(' ');
			}
			sb.append('\n');
		}
		sb.append("]\n");
		System.out.print(sb);
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Spreadsheet s = new SpreadsheetImpl();
		String line;
		while ((line = in.readLine()) != null)
		{
			String[] toks = line.split("\\s+");
			if (line.startsWith("AR")) 
			{
				int r = Integer.parseInt(toks[1]);
				s.addRow(r);
			}
			else if (line.startsWith("AC"))
			{
				int r = Integer.parseInt(toks[1]);
				s.addCol(r);				
			}
			else if (line.startsWith("SE"))
			{
				int r = Integer.parseInt(toks[1]);
				int c = Integer.parseInt(toks[2]);
				double v = Double.parseDouble(toks[3]);
				s.set(r, c, v);
			}
			else if (line.startsWith("DR"))
			{
				int r = Integer.parseInt(toks[1]);
				s.deleteRow(r);
			}
			else if (line.startsWith("DC"))
			{
				int c = Integer.parseInt(toks[1]);
				s.deleteCol(c);
			}
			else if (line.startsWith("SO"))
			{
				int c = Integer.parseInt(toks[1]);
				s.sortRowsByCol(c);
			}
			else if (line.startsWith("CO"))
			{
				Spreadsheet t = s.getCounts();
				outputSpreadsheet(t);
			}
			outputSpreadsheet(s);
		}
	}
}
