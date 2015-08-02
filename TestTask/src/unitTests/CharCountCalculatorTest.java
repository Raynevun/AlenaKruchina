package unitTests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import org.jdom2.JDOMException;
import org.junit.Assert;
import org.junit.Test;
import junit.framework.TestCase;
import programm.CharCountCalculator;;

public class CharCountCalculatorTest extends TestCase {

	@Test
	public void testGetMatchingEachCharToItsCountWithFile() throws MalformedURLException, IOException, JDOMException {
		CharCountCalculator calc = new CharCountCalculator();
		String baseDir = System.getProperty("user.dir");
		
		HashMap<Character, Integer> expected = new HashMap<>();
		expected.put('A', 12);
		expected.put('E', 10);
		expected.put('0', 10);
		expected.put('9', 7);
		expected.put('F', 9);
		expected.put('C', 9);
		expected.put('3', 7);
		expected.put('8', 8);
		expected.put('5', 14);
		expected.put('4', 8);
		expected.put('2', 7);
		expected.put('B', 10);
		expected.put('6', 5);
		expected.put('D', 3);
		expected.put('7', 3);
		expected.put('1', 6);
		
		HashMap<Character, Integer> actual = calc.getMatchingEachCharToItsCount("file:///"+baseDir+"/src/unitTests/mocks/last.xml", "outputValue");		
		Assert.assertTrue(actual.equals(expected));
	}	
	
	@Test
	public void testGetCountForChars() {
		CharCountCalculator calc = new CharCountCalculator();
		HashMap<Character, Integer> expected = new HashMap<>();
		expected.put('A', 2);
		expected.put('3', 1);
		expected.put('2', 1);
		expected.put('5', 1);
		
		String testString = "AA325";
		
		HashMap<Character, Integer> actual = calc.getCountForChars(testString.toCharArray());
		Assert.assertTrue(actual.equals(expected));
	}	
	
	@Test
	public void testGetMatchingEachCharToItsCountWithHashMaps() {
		CharCountCalculator calc = new CharCountCalculator();
		HashMap<Character, Integer> expected = new HashMap<>();
		expected.put('A', 2);
		expected.put('3', 1);
		expected.put('2', 1);
		expected.put('5', 1);
		expected.put('8', 3);
		expected.put('F', 1);

		HashMap<Character, Integer> first = new HashMap<>();
		first.put('A', 1);
		first.put('3', 1);
		first.put('2', 1);
		first.put('5', 1);
		first.put('8', 1);
		
		HashMap<Character, Integer> second = new HashMap<>();
		second.put('A', 1);
		second.put('8', 2);
		second.put('F', 1);

		HashMap<Character, Integer> actual = calc.getMatchingEachCharToItsCount(first, second);
		Assert.assertTrue(actual.equals(expected));
	}
}
