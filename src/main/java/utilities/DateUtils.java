/**
 * 
 */
package utilities;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
/**
 * @author YugandharReddyGorrep
 *
 */
import java.time.LocalDate;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;


import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class DateUtils
{
	public static String timeStamp ;
	public static boolean flag=false ;

	static Locale locale = Locale.getDefault();
	static TimeZone tz = TimeZone.getDefault();
	static Calendar cal = Calendar.getInstance(tz, locale);
	static Date d = new Date(System.currentTimeMillis());
	public static Date date;
	public static long diff;
	public static int differnce;
	/**
	 * @description:Returns the current time stamp
	 * 
	 * 
	 * @return String
	 */
	public static String getCurrTimeStamp()
	{
		if(flag==false)
		{
		LocalDate.now();
		Locale locale = Locale.getDefault();
		TimeZone tz = TimeZone.getDefault();
		Calendar cal = Calendar.getInstance(tz, locale);
		Date d = new Date(System.currentTimeMillis());
		cal.setTime(d);
		int m = cal.get(Calendar.MONTH) + 1;
		int h = cal.get(Calendar.HOUR);
		int mm = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);
		 timeStamp = cal.get(Calendar.DAY_OF_MONTH) + "_" + m + "_" + cal.get(Calendar.YEAR) + "_" + h + "hh_"
				+ mm + "mm_" + s + "ss";
		System.out.println("date util time stamp in if+"+timeStamp);
		flag=true;
		return timeStamp;
		}
		else
		{
			System.out.println("date util time stamp in else+"+timeStamp);

			return timeStamp;
		}
	}
	
	
	public static Date convertDateintoString(String dateinString) throws Exception 
	{
		SimpleDateFormat formatter[] = new SimpleDateFormat[]{new SimpleDateFormat("MMM dd, yyyy"), new SimpleDateFormat("MMM D, YYYY")};
		SimpleDateFormat to = new SimpleDateFormat("mm/dd/yyyy");
    	date = parse(dateinString.trim(), formatter);
        System.out.println(date);
       // dateinString = formatter.format(date);
       return date;
        	
	}
	
	public static String getCurrMonthInMM()
	{
		cal.setTime(d);
		int m = cal.get(Calendar.MONTH) + 1;
		String mm;
		if (m < 10)
			mm = "0" + m;
		else
			mm = Integer.toString(m);
		return mm;
	}
	public static String getCurrDateInDD()
	{
		int d = cal.get(Calendar.DATE);
		String mm;
		if (d < 10)
			mm = "0" + d;
		else
			mm = Integer.toString(d);
		return mm;
	}
	public static Integer getCurrYearInYYYY()
	{
		int y = cal.get(Calendar.YEAR);
		return y;
	}
	
	public static Date parse(String value, DateFormat formatters[]) {
	    Date date = null;
	    for (DateFormat formatter : formatters) {
	      try {
	        date = formatter.parse(value);
	        break;
	      } catch (ParseException e) {
	      }
	    }
	    return date;
	  }

	public static void ComparesDates(String Date1, String Date2, ExtentTest test) throws Exception
	{
		SimpleDateFormat formatter[] = new SimpleDateFormat[]{
				new SimpleDateFormat("D-MMM-YYYY"), new SimpleDateFormat("M/D/YYYY"), new SimpleDateFormat ("MMM d YYYY") };
		
		Date date1 = parse(Date1, formatter);
		Date date2 = parse(Date2, formatter);
		
		if(date1.equals(date2))
		{
			test.log(Status.PASS, Date1+"matches with the "+Date2);
		}
		else
		{
			test.log(Status.FAIL, Date1+"does not matche with the "+Date2);
		}
				
	}
	
	public static int datesubstract(String d1, String d2) throws ParseException
	{
		Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
		cal1.setTime(sdf.parse(d1));
		cal2.setTime(sdf.parse(d2));
              
        long millis1 = cal1.getTimeInMillis();
        long millis2 = cal2.getTimeInMillis();

        long diff = millis2 - millis1;
         differnce = (int) (diff / (24 * 60 * 60 * 1000));
        System.out.println(differnce); 
		return differnce;
	        
	 }
	
	
}
