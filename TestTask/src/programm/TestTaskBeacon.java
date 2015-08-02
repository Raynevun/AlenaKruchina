package programm;
import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import org.jdom2.*;

public class TestTaskBeacon 
{
	private static String from = null;
	private static String to = null;
	
	public static void main(String[] args) throws JDOMException, IOException
	{	
		getParams(args);
		HashMap<Character, Integer> result;
		
		if (from == null && to == null)
			result = processNoParams();
		else if (from != null && to != null)
			result = processWithParams(); 
		else {
			System.out.println("Error. Input string is invalid");
			return;
		}
		
		if (result == null)
			return;
		
		printResults(result);
	}
	
	private static void getParams(String[] params) 
	{
		if (params.length % 2 == 1)
			return;
		
		for (int i = 0; i < params.length; i += 2) {
			switch(params[i]) {
				case "--from" 	: from = params[i+1]; break;
				case "--to" 	: to = params[i+1]; break;
			}
		}
	}
	
	private static HashMap<Character, Integer> processNoParams() throws MalformedURLException, IOException, JDOMException {
		CharCountCalculator calculator = new CharCountCalculator();
		HashMap<Character, Integer> result = calculator.getMatchingEachCharToItsCount("https://beacon.nist.gov/rest/record/last", "outputValue");

		return result;
	}
	
	private static HashMap<Character, Integer> processWithParams() throws MalformedURLException, IOException, JDOMException {
		CharCountCalculator calculator = new CharCountCalculator();
		DateConvertor convertor = new DateConvertor();
		
		long fromUtc = convertor.convertStringToUtcTimestamp(from);
		if (fromUtc == -1) {
			System.out.println("FROM date predates the Unix epoch time");
			return null;
		}
		
		long toUtc = convertor.convertStringToUtcTimestamp(to);
		if (toUtc == -1) {
			System.out.println("TO date predates the Unix epoch time");
			return null;
		}
		
		HashMap<Character, Integer> resultFrom = calculator.getMatchingEachCharToItsCount("https://beacon.nist.gov/rest/record/" + fromUtc, "outputValue");
		HashMap<Character, Integer> resultTo = calculator.getMatchingEachCharToItsCount("https://beacon.nist.gov/rest/record/" + toUtc, "outputValue");
		
		HashMap<Character, Integer> result = calculator.getMatchingEachCharToItsCount(resultFrom, resultTo);

		return result;
	}
	
	private static void printResults(HashMap<Character, Integer> result) {
		for (Map.Entry<Character, Integer> entry : result.entrySet()) 
		{
			Character character = entry.getKey();
			Integer count = entry.getValue();
			System.out.println(character + ", " + count);
		}
	}	
}
