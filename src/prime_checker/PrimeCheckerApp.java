package prime_checker;

import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.junit.platform.commons.logging.LoggerFactory;


public class PrimeCheckerApp {
	
	//Console text color constants
	public static final String COLOR_GREEN = "\u001B[32m";
	public static final String COLOR_RESET = "\u001B[0m";
	//Menu choice constants
	private static final int MIN_CHOICE = 1;
	private static final int QUIT_CHOICE = 3;
	
	
	
	private static final org.junit.platform.commons.logging.Logger logger = LoggerFactory.getLogger(PrimeCheckerApp.class);



	//cache to read in and store historical data
	static Map <String, List<Integer>> cache = new HashMap<String, List<Integer>>();
	
	static RecordUpdater updater = new RecordUpdater();

	
	public static void main(String[] args) {
		
		
		//read in to cache from file
		CacheUpdater cupdate = new CacheUpdater();
		cupdate.readIn(cache);
		Scanner sc = new Scanner(System.in);
		System.out.println("Prime Checker started.");
		
		//main menu loop
		int choice = 0;
		do {
			System.out.println();
			showMenu();
			choice = getChoice(sc);
			processMenuChoice(choice, sc);
			
		} while (choice != QUIT_CHOICE);

		sc.close();
	}
	

	public static boolean processNewInput(Scanner sc){

		System.out.println(COLOR_GREEN + "[INPUT] Username:" + COLOR_RESET);
		String user_name = sc.nextLine();
		System.out.println(COLOR_GREEN +"[INPUT] Value to check:" + COLOR_RESET);
		String user_input = sc.nextLine();
		
		//check if user inpit meets business rules
		if (isValidInput(user_input)) {
			List<Integer> tempCache = new ArrayList<Integer>();
			
			//check cache map for matching input
			if(checkCache(user_input, tempCache)) {
				System.out.println("Match found in cache. Value not processed.");
				System.out.printf("%-20s %s%n" ,"User Input:", user_input);
				System.out.printf("%-20s %s%n" ,"Matche Primes:", tempCache);
				
			} else {
				//If no matching input found, proceed with processing input, then writing to file
				List<Integer> prime_candidates = getCombinations(user_input);
				System.out.printf("%-20s %s%n" ,"Prime Candidates:", prime_candidates.toString());
				remove_nonPrime(prime_candidates);
				if (!prime_candidates.isEmpty()) {
				System.out.printf("%-20s %s%n" ,"Actual Prime:", prime_candidates.toString());
				
				cache.put(user_input, prime_candidates);
				updater.writeToFile(cache);
				System.out.println("New data written to file");
				return true;
			} else {
				//If no prime numbers are found, nothing is written to cache
				System.out.printf("%-20s %s%n" ,"Actual Prime:", "None found!");
			}	
			}
		} else {
			System.out.println("Not a valid input. Enter numerical values 0-9");
			
		
		}
		return false;
	}
	
	/**
	 * Checks if user input matches business rule of numerical inputs only.
	 * @param input
	 * @return
	 */
	public static boolean isValidInput (String input) throws NullPointerException {
		if (input == null) {
			throw new NullPointerException();
		} else {
			if (input.matches("^[0-9]+$")) return true;
		else return false;
		}
		
	}
	
	/**
	 * returns a naturally sorted Integer List of all possible sequential digit combinations from a String input
	 * @param user_input
	 * @return
	 */
	public static List<Integer> getCombinations (String in){
		
		//String user_in = String.valueOf(user_input);
		List<Integer> combinations = new ArrayList<Integer>();
		
		for (int i = 0; i <= in.length(); i++) {
			for (int j = i+1; j <= in.length(); j++) {
				combinations.add(Integer.valueOf(in.substring(i, j)));
			}
		}
		Collections.sort(combinations);
		return combinations;
	}
	
	/**
	 * void method - removes non-prime numbers from a passed in List<Integer>
	 * @param prime_candidates
	 */
	public static void remove_nonPrime (List<Integer> prime_candidates){
		
		Iterator<Integer> iterator = prime_candidates.iterator();	
		while(iterator.hasNext()) {
			if (!isPrime(iterator.next())) {
				iterator.remove();
			}
		}	
	}
	/**
	 * Helper method - check if a number is prime;
	 * @param prime_cadidate;
	 * @return true = prime;
	 */
	public static boolean isPrime (int prime_cadidate) {
	
		//Shortcut: numbers <=1 are never prime - return false.
		if (prime_cadidate <= 1) return false;
		//Shortcut: numbers 2 and 3 are prime - return true.
		if (prime_cadidate == 2 || prime_cadidate == 3) return true;
		//Shortcut: even numbers >2 are never prime - return false.
		if (prime_cadidate % 2 == 0) return false;
		
		
		//Check factors from 3 to <= prime_candidate. Skip even numbers each iteration.
		for (int i = 3; i*i <= prime_cadidate; i+=2) {
			if (prime_cadidate % i == 0) return false;
		}	
		return true;
	}
	/**
	 * Displays available menu options to the user
	 */
	public static void showMenu() {
		System.out.println("1. Check new Prime");
		System.out.println("2. Read catche");
		System.out.println("3. Quit");
	}
	
	/**
	 * Gets menu choice from user + basic input validation
	 * @param scanner object must be passed in.
	 * @return
	 */
	private static int getChoice(Scanner sc) {
		int choice = -1;
		//Scanner sc2 = new Scanner(System.in);
		System.out.println("Enter Menu choice");
		try {
			choice = sc.nextInt();
			sc.nextLine();
			if (choice < MIN_CHOICE || choice > QUIT_CHOICE) {
				throw new Exception();//throw exception, caught immediately and retry
			}
		} catch (Exception e) {
			System.out.println("Invalid choice, please try again");
			
			
			return getChoice(sc);//recursive call to try again and return that value
		}
		return choice;
	}
	
	
	/**
	 * Menu driver 
	 * @param choice
	 * @param sc
	 */
	private static void processMenuChoice(int choice, Scanner sc) {
		
		try {
			switch (choice) {
			case 1:
				processNewInput(sc);
				
				break;
			case 2:	
				readCache();
				break;
			case 3:
				System.out.println("Quitting");
				break;
			default:
				System.out.println("Unrecognised menu choice");
			}
		} catch (Exception e) {
			System.out.println("Exception caught in task "+choice);
			System.out.println("Exception message: "+e.getMessage());
			System.out.println("Try again");
		}

	}
	/**
	 * checks if user input is available in cache. Is there is match - passed in array will be populated and function returns true.
	 * @param input
	 * @param primes
	 * @return
	 */
	public static boolean checkCache(String input,List<Integer> primes ) {
			
		for (String s : cache.keySet()) {
			if (s.equals(input)) {
				primes.addAll(cache.get(s));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Read values currently stored in cache
	 */
	public static void readCache() {
		System.out.printf("%-20s %s%n" ,"Input Record", "Prime Numbers");
		for (String i : cache.keySet()) {
			System.out.printf("%-20s %s%n" ,i, cache.get(i));
			
		}
	}
	
	
	

	
	
	


}
