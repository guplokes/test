package com.cat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class LoginPage extends BaseClass {
	
String pageTitle="Xceedance";
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user-name") 
	WebElement userNameField;
	
	@FindBy(id="user-password") 
	WebElement PasswordField;
	
	@FindBy(xpath="//button[text()=' Login']") 
	WebElement loginButton;
	
	public void verifyLoginPage()
	{
		wait.waitForElement(userNameField);		
		elementAct.verifyWebPage(pageTitle, "Login Page");
	}
	
	public HomePage LoginToApp(String userName,String Password)
	{
		
//		wait.waitForElement(userNameField);
		elementAct.enterData(userNameField, "User Name", userName);
		//elementAct.enterDataByJS(userNameField, "User Name", userName);
		elementAct.enterData(PasswordField, "Password",Password);
		elementAct.clickElement(loginButton, "Login Button");
		
		return new HomePage();
	
	}
}
