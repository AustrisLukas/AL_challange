package prime_checker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RecordUpdater {
	
	public static final String FILE_NAME = "cache";
	public static final String FILE_EXT = ".txt";
	
	File f1;
	
	
	/**
	 * creates new file (if none available) and names it as per constants. Processes passed in array to read array in
	 * @param fileIN
	 */
	public void writeToFile(Map<String, List<Integer>> fileIN) {
		
		File f1 = new File(FILE_NAME + FILE_EXT);
		
		try(BufferedWriter fwriter = new BufferedWriter(new FileWriter(f1))) {
			if (!f1.exists()) f1.createNewFile();
			
			for (String i : fileIN.keySet()) {
				fwriter.write(i + "," + fileIN.get(i));
				fwriter.newLine();	
			}
		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
	}
}
