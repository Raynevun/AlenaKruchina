package unitTests;

import org.junit.Assert;
import org.junit.Test;
import junit.framework.TestCase;
import programm.DateConvertor;

public class DateConvertorTest extends TestCase {

	@Test
	public void testMinutes() {
		DateConvertor convertor = new DateConvertor();
		long currentTime = 1438530245;
		long expectedTime = currentTime - 2 * 60;
		String param = "2 minutes";
		
		Assert.assertEquals(expectedTime, convertor.convertStringToUtcTimestamp(param, currentTime));
	}
	
	@Test
	public void testHours() {
		DateConvertor convertor = new DateConvertor();
		long currentTime = 1438530245;
		long expectedTime = currentTime - 2 * 60 * 60;
		String param = "2 hours";
		
		Assert.assertEquals(expectedTime, convertor.convertStringToUtcTimestamp(param, currentTime));
	}
	
	@Test
	public void testDays() {
		DateConvertor convertor = new DateConvertor();
		long currentTime = 1438530245;
		long expectedTime = currentTime - 2 * 60 * 60 * 24;
		String param = "2 days";
		
		Assert.assertEquals(expectedTime, convertor.convertStringToUtcTimestamp(param, currentTime));
	}

	@Test
	public void testMonths() {
		DateConvertor convertor = new DateConvertor();
		long currentTime = 1438530245;
		long expectedTime = currentTime-2 * 60 * 60 * 24 * 30;
		String param = "2 months";
		
		Assert.assertEquals(expectedTime, convertor.convertStringToUtcTimestamp(param, currentTime));
	}

	@Test
	public void testAllParams() {
		DateConvertor convertor = new DateConvertor();
		long currentTime = 1438530245;
		long expectedTime = currentTime - 1 * 60 * 60 * 24 * 30 - 2 * 60 * 60 * 24 - 3 * 60 * 60 - 4 * 60;
		String param = "1 month 2 days 3 hours 4 minutes";
		
		Assert.assertEquals(expectedTime, convertor.convertStringToUtcTimestamp(param, currentTime));
	}
}
