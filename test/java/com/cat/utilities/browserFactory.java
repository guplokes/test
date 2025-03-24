package com.cat.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

public class browserFactory {
	
	public static WebDriver startApplication(WebDriver driver,String BrowserName,String AppURL) 
	{
		if(BrowserName.equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver.exe");
	        driver = new ChromeDriver();
	        Reporter.log("driver is initiated....",true);
		}
		else if(BrowserName.equals("Mozila"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/driver/geckodriver.exe");
	        driver = new FirefoxDriver();
		}
		else if(BrowserName.equals("IE"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/driver/MicrosoftWebDriver.exe");
	        driver = new InternetExplorerDriver();
		}
		else 
		{
			System.out.println("We do not support "+BrowserName+" browser");
		}
		
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Reporter.log("Window is maximized.",true);
		driver.get(AppURL);
		Reporter.log("URL is entered.",true);
		return driver;
	}
	
	public static void QuitBrowser(WebDriver driver)
	{
		driver.quit();
	}
}
