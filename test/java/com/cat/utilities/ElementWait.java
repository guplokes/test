package com.cat.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.cat.testCases.BaseClass;

public class ElementWait extends BaseClass {
	
	public ElementWait() {
		PageFactory.initElements(driver, this);
	}
	
	public void waitForElement(WebElement element, String ElementName) {
		WebDriverWait wait = new WebDriverWait(driver, readConfig.getElementWaitTime());
		wait.until(ExpectedConditions.visibilityOf(element));
		logger.info("Element "+ElementName+" is found successfully on the page.");
	}
	
	public void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	//waiting for new WEBelement after its attribute changes
	public void waitForElementsAttribute(WebElement element, String attributeName, String attributeValue) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		 try{
			 wait.until(ExpectedConditions.attributeContains(element, attributeName, attributeValue));
		 }
		 catch(Exception ex) {
//			 ex.printStackTrace();
		 }
	}
	
	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForTextInElement(WebElement element, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	public void applyImplicitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Implicit wait is getting applied.");
	}
	
	public void waitForTitle(String expectedTitle) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains(expectedTitle));
	}
}
