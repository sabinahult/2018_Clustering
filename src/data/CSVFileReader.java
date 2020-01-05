package data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader {
	/**
	 * The read method reads in a csv file as a two dimensional string array.
	 * This method is utilizes the string.split method for splitting each line of the data file.
	 * @param csvFile File to load
	 * @param delim Character used to separate entries
	 * @param nullValue What to insert in case of missing values
	 * @param headerRow Does data file contain a header row?
	 * @return Data file content as a 2D string array
	 * @throws IOException
	 */
	public static String[][] readDataFile(String csvFile, String delim, String nullValue, boolean headerRow) throws IOException {
		List<String[]> lines = new ArrayList<>();

		BufferedReader br = new BufferedReader(new FileReader(new File(csvFile)));
		String line;
		
		if(headerRow) {
			// read the header
			line = br.readLine();
		}
		
		while ((line = br.readLine()) != null) {
			String[] arr = line.split(delim);
			
			for(int i = 0; i < arr.length; i++) {
				if(arr[i].equals("")) {
					arr[i] = nullValue;
				}				
			}			
			lines.add(arr);
		}
		
		String[][] ret = new String[lines.size()][];
		br.close();
		return lines.toArray(ret);
	}
}
