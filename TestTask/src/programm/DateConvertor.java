package programm;
import java.util.Calendar;

public class DateConvertor 
{
	public long getCurrentTimeInSeconds()
	{
		return Calendar.getInstance().getTimeInMillis() / 1000;
	}
	
	public long convertStringToUtcTimestamp(String dateDescription)
	{	
		return convertStringToUtcTimestamp(dateDescription, getCurrentTimeInSeconds());
	}
	
	public long convertStringToUtcTimestamp(String dateDescription, long timeInSeconds)
	{		
		String[] split = dateDescription.split(" ");
		for (int i = 0; i < split.length; i += 2)
		{
			if (split[i+1].contains("month"))
			{
				timeInSeconds -= Long.parseLong(split[i]) * 60 * 60 * 24 * 30;
			}
			if (split[i+1].contains("day"))
			{
				timeInSeconds -= Long.parseLong(split[i]) * 60 * 60 * 24;
			}
			if (split[i+1].contains("hour"))
			{
				timeInSeconds -= Long.parseLong(split[i]) * 60 * 60;
			}
			if (split[i+1].contains("minute"))
			{
				timeInSeconds -= Long.parseLong(split[i]) * 60;
			}
		}
		
		if(timeInSeconds < 1378395540)
			return -1;
		return timeInSeconds;
	}
}
