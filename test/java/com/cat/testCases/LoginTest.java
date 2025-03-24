package com.cat.testCases;

import org.testng.annotations.Test;

import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;

public class LoginTest extends BaseClass {
	LoginPage objLogin;
	HomePage objHome;

	@Test(priority = 1, description = "Login with valid username and password.")
	public void LoginToLMS() throws InterruptedException {
//		if (extentRptflag) {
//			logger=report.createTest("Login to LMS", "Desc: Login with valid username and Password");
//		}
		
		  objLogin = new LoginPage(); 
		  objLogin.verifyLoginPage();
//		  objHome = objLogin.LoginToApp(objExcl.getStringData("login", 1, 0),
//		  objExcl.getStringData("login", 1, 1));
		  objHome = objLogin.LoginToApp(readConfig.getUserName(), readConfig.getPassword());
		  
		  //objHome = new HomePage(); 
		  objHome.verifyHomePage();
		 
	}
}
