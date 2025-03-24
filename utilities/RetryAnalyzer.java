package com.cat.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.cat.testCases.BaseClass;

public class RetryAnalyzer extends BaseClass implements IRetryAnalyzer {		
		int counter=0;
		int maxTry=3;		
		
		//@Override
	    public boolean retry(ITestResult result) {
	        if (!result.isSuccess()) {                      		//Check if test not succeed
	            if (counter < maxTry) {                            	//Check if maxtry count is reached
	            	counter++;                                     	//Increase the maxTry count by 1
	            	result.setStatus(ITestResult.FAILURE);  		//Mark test as failed
	            	logger.info("Re-trying to execute the test again....");
	                return true;                                 	//Tells TestNG to re-run the test
	            } else {
	            	result.setStatus(ITestResult.FAILURE);  		//If maxCount reached,test marked as failed
	            	extentRptflag=true;
	            }
	        } else {
	        	result.setStatus(ITestResult.SUCCESS);      		//If test passes, TestNG marks it as passed
	        }
	        return false;
	    }
}


