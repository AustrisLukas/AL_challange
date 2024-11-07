package prime_checker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CacheUpdater {
	
	public static final String FILE_NAME = "cache";
	public static final String FILE_EXT = ".txt";
	
	File f1;
	String readLine;
	String[] splitLine;
	
	/**
	 * reads file and populates array. Method expects specific structure in file.
	 * nums , [num, num, num, num, etc..] /n
	 * nums , [num, num, num, num, etc..] /n
	 * @param fileIN
	 */
	public void readIn(Map<String, List<Integer>> fileIN) {
		
		readLine = null;
		
		
		try(BufferedReader breader = new BufferedReader(new FileReader(FILE_NAME + FILE_EXT))){
			
			String readLine = breader.readLine();
			
			while (readLine!=null) {
				
				//read first line in file and split Key away from Values
				splitLine = readLine.split(",", 2);
				
				//Remove square breackets surrounding values
				splitLine[1] = splitLine[1].substring(1, splitLine[1].length()-1);
				//Remove white space
				splitLine[1] = splitLine[1].replace(" ", "");
				//Read all the values and populate in Integer list 
				List<Integer> integerList = new ArrayList<Integer>();
				for (String s : splitLine[1].split(",")) {
					integerList.add(Integer.parseInt(s));
				}
				//Add Key and Values to Map
				fileIN.put(splitLine[0], integerList);

				//Read new line and repeat above while not null
				readLine = breader.readLine();
			}
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
			System.out.println("Cache file not found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
