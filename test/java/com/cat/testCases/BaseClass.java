package com.cat.testCases;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.cat.utilities.ReadConfig;
import com.cat.utilities.ReadExcel;
import com.cat.utilities.ElementAction;
import com.cat.utilities.ElementActions;
import com.cat.utilities.ElementWait;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static ReadExcel objExcl;
	public static ReadConfig readConfig;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ElementAction eleAct;
	public static ElementActions elementAct;
	public static ElementWait wait;
	boolean Testskip = false;
	int counter = 1;
	int retryLimit = 1;
	public static boolean extentRptflag = true;
	public static String LMSAcName;


	@BeforeSuite
	public void setUpSuite() throws IOException {
		objExcl = new ReadExcel();	

		// ....
		readConfig = new ReadConfig();	
		

	}

	@BeforeClass
	public void launchApplication() {
		// readConfig = new ReadConfig();
		String browserName = readConfig.getBrowser();
		String url = readConfig.getURL();
		System.out.println(browserName + url);

		if (browserName.equals("Chrome")) {
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
//			driver = new ChromeDriver();
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			Reporter.log("driver is initiated....", true);
		}
		else if(browserName.equals("Edge")){
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/driver/msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else if (browserName.equals("Mozila")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/driver/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "/driver/MicrosoftWebDriver.exe");
			driver = new InternetExplorerDriver();
		} else {
			System.out.println("We do not support " + browserName + " browser");
		}
 
		
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Reporter.log("Window is maximized.", true);
		driver.manage().deleteAllCookies();
		
		// entering the url in the browser
		driver.get(url);
			
		
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		Reporter.log("URL is entered.", true);

		// temporary, have to find new way to access these classes
		eleAct = new ElementAction(driver);
		elementAct = new ElementActions(driver);
		wait = new ElementWait();

		// return driver
	}

	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	 

	@AfterSuite
	public void tearDown() {
		// browserFactory.QuitBrowser(driver);
	}
}
