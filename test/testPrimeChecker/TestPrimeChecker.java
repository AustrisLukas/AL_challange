package testPrimeChecker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import prime_checker.PrimeCheckerApp;

class TestPrimeChecker {
	
	String input_valid_low;
	String input_valid_high;
	
	String input_not_valid;
	String input_not_valid2;
	
	PrimeCheckerApp testObj;
	
	

	@BeforeEach
	void setUp() throws Exception {
		
		input_valid_low = "12";
		input_valid_high = "1234578";
		
		//Character
		input_not_valid = "123b45";
		//Special character
		input_not_valid2 = "123%45";
		
		testObj = new PrimeCheckerApp();
	}

	@Test
	void testProcessNewInput() {
		fail("Not yet implemented");
	}

	@Test
	void testIsValidInput_valid() {
		//Check for valid input 
		assertTrue(testObj.isValidInput(input_valid_low));
		assertTrue(testObj.isValidInput(input_valid_high));
		
	}
	@Test
	void testIsValidInput_invalid() {
		//Check for invalid input
		assertFalse(testObj.isValidInput(input_not_valid));
		assertFalse(testObj.isValidInput(input_not_valid2));
		
		//Test for null input
		assertThrows(NullPointerException.class, ()->{
			testObj.isValidInput(null);
	});
		
	}

	@Test
	void testGetCombinations() {
		fail("Not yet implemented");
	}

	@Test
	void testRemove_nonPrime() {
		fail("Not yet implemented");
	}

	@Test
	void testIsPrime() {
		fail("Not yet implemented");
	}

	@Test
	void testCheckCache() {
		fail("Not yet implemented");
	}

	@Test
	void testReadCache() {
		fail("Not yet implemented");
	}

}
