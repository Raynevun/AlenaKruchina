package programm;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class CharCountCalculator 
{
	public HashMap<Character, Integer> getMatchingEachCharToItsCount(String url, String nodeName) throws MalformedURLException, IOException, JDOMException 
	{
		InputStream inputStream = new URL(url).openConnection().getInputStream();
		Document document =  new SAXBuilder().build(inputStream);
		char[] chars = document.getRootElement().getChild(nodeName).getValue().toCharArray();
				
		return getCountForChars(chars);
	}

	public HashMap<Character, Integer> getCountForChars(char[] chars) 
	{
		HashMap<Character, Integer> charsCountMatch = new HashMap<Character, Integer>();
		
		for (char ch : chars)
		{
			if (charsCountMatch.containsKey(ch)) 
			{
				Integer count = charsCountMatch.get(ch);
				count++;
				charsCountMatch.put(ch, count); 
			}
			else
				charsCountMatch.put(ch, 1);			
		}
		
		return charsCountMatch;
	}	
	
	public HashMap<Character, Integer> getMatchingEachCharToItsCount(HashMap<Character, Integer> first, HashMap<Character, Integer> second)
	{
		HashMap<Character, Integer> summarizedResult = new HashMap<Character, Integer>();
		
		for (Map.Entry<Character, Integer> entry : first.entrySet()) 
		{
		    Character character = entry.getKey();
		    Integer count = entry.getValue();
		    summarizedResult.put(character, count);
		}
		
		for (Map.Entry<Character, Integer> entry : second.entrySet()) 
		{
		    Character character = entry.getKey();
		    Integer count = entry.getValue();
		    
		    if (summarizedResult.containsKey(character)) 
				count += summarizedResult.get(character);
			
		    summarizedResult.put(character, count);		
		}
		
		return summarizedResult;
	}
}
