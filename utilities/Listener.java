package com.cat.utilities;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.cat.testCases.BaseClass;

public class Listener extends BaseClass implements ITestListener {
	ExtentSparkReporter extent;
	String className = "";
	ExtentTest parent;
	String testDescription;
	String testMethodName;
	String parentNode;
	
	public void onTestStart(ITestResult result) {
		testDescription = result.getMethod().getDescription();   //to get description of method
		testMethodName = result.getName();

		//getting running class Name which is present in testng.xml and splitting it
			String Str = result.getMethod().getRealClass().getName(); 
		   String[] arrOfStr = Str.split("\\.");
		   parentNode = arrOfStr[arrOfStr.length - 1];


		if(!parentNode.equals(className))
		{
			className = parentNode;
//			parent = report.createTest("Automation Report: " + parentNode);
			parent = report.createTest(parentNode);
			if(testDescription.length()!=0) {

				logger = parent.createNode(testDescription);
				logger.info("'" + testMethodName + "'" +  " test case started");
			}
			else {
				logger = parent.createNode(testMethodName);
				logger.info("'" + testMethodName + "'" +  " test case started");
			}
		}
		else
		{
			if(testDescription.length()!=0) {
				logger = parent.createNode(testDescription);
				logger.info(result.getName() +  " test case started");
			}
			else
				logger = parent.createNode(testMethodName);
						
		}
			}

	public void onTestSuccess(ITestResult result) {
		logger.assignCategory(parentNode);
		if(testDescription.length()!=0) {
		System.out.println("The name of the testcase passed is :" + "'" + testDescription + "'");
		}
		else
			System.out.println("The name of the testcase passed is :" + "'" + testMethodName + "'");
	    
	    logger.log(Status.PASS,MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		logger.assignCategory(parentNode);
		
		if (testDescription.length() != 0)
			System.out.println("The name of the testcase failed is :" + "'" + testDescription + "'");
		else
			System.out.println("The name of the testcase failed is :" + "'" + testMethodName + "'");
	
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL,
							MarkupHelper.createLabel(result.getName() + "- Test case failed", ExtentColor.RED));
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + "- Test case failed", ExtentColor.RED));
			String screenshotPath = helper.CaptureScreenShot(driver, result.getMethod().getMethodName());
			logger.fail("Screenshot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());		
			
			
		}	
	
	
	
	}

	public void onTestSkipped(ITestResult result) {
		logger.assignCategory(parentNode);
		if (testDescription.length() != 0)
			System.out.println("The name of the testcase skipped is :" + "'" + testDescription + "'");

		else
			System.out.println("The name of the testcase skipped is :" + "'" + testMethodName + "'");
		
		logger.log(Status.SKIP, "Skipped Test case is: " + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		Reporter.log("Setting up reports and test getting started.", true);		
		
		extent = new ExtentSparkReporter(
				new File(System.getProperty("user.dir") + "/Reports/RDP_" + helper.getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		
//		extent.config().setTimelineEnabled(false);
		extent.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST}).apply();
		report.attachReporter(extent);
		
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Type of Testing", "Automation");
		report.setSystemInfo("Host IP", inetAddress.toString());
		report.setSystemInfo("User Name", System.getProperty("user.name"));
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("Server", readConfig.getURL());
		try {
			extent.loadXMLConfig(new File(System.getProperty("user.dir") + "/XMLFiles/extent-config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("Extent Report has been configured.",true);
		
//		ExtentTest parent = report.createTest(getClass().getName());
//        parentTest.set(parent);
		
		
	}

	public void onFinish(ITestContext context) {
		report.flush();
	}

}
