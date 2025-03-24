package com.cat.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class helper {
	public static String CaptureScreenShot(WebDriver driver, String testName)
	{
		
		File src=  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
	    File f1 = new File(System.getProperty("user.dir")+"/Screenshots/"+testName);  
	    boolean fileExist= !(f1.exists());
	    if(fileExist) {
	    	f1.mkdir();  
	    }
		
		String ScreenshotPath= System.getProperty("user.dir")+"/Screenshots/"+testName+"/"+testName+"_"+getCurrentDateTime()+".png";
		
		try {
			FileHandler.copy(src, new File (ScreenshotPath));
		} catch (Exception e) {
			System.out.println("Unable to capture screenshots.."+e.getMessage());
		}
		
		return ScreenshotPath;
	}
	
	public static String getCurrentDateTime() {
		DateFormat customFormat=new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss");		
		Date currentDate=new Date();		
		return customFormat.format(currentDate);


	}
	
	public static  String getCurrentDate()  throws ParseException
	{
		SimpleDateFormat month_date = new SimpleDateFormat("MMMMMMMM d, yyyy", Locale.ENGLISH);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		Calendar c = Calendar.getInstance();
		String dateAfterModification = dateFormat.format(c.getTime());
		Date date = dateFormat.parse(dateAfterModification);
		String month_name = month_date.format(date);
		return month_name;
	}
	/**
	 * to add  4 Days from the current date
	 * @return
	 * @throws ParseException 
	 */
	public static String getDateAfterAddingDays() throws ParseException {
		SimpleDateFormat month_date = new SimpleDateFormat("MMMMMMMM d, yyyy", Locale.ENGLISH);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		Calendar c = Calendar.getInstance();
;
		c.add(Calendar.DAY_OF_WEEK, 4);
	//	c.add(Calendar.DAY_OF_MONTH, 4);

		if((c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)||(c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY))
		{
			c.add(Calendar.DAY_OF_WEEK, 2);	
		}
		String dateAfterModification = dateFormat.format(c.getTime());

		Date date = dateFormat.parse(dateAfterModification);
		String month_name = month_date.format(date);
//		String dateparam="[aria-label*='"+date+"']";
        return month_name; 
	}

}
