package com.cat.utilities;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class Utils extends BaseClass {

	public Utils() {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//div[@aria-label='Warning']")
	WebElement warningMessage;
	
	@FindBy(xpath = "//div[@aria-label='Success']")
	WebElement successMessage;
	
	@FindBy(xpath = "//div[@aria-label='Error']")
	WebElement errorMessage;
	
	@FindBy(css = "div#toast-container>div>button>span")
	WebElement crossBtn;
	
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-columnchooser']")
	WebElement columnChooserBtn;

	@FindBy(css = "div[aria-label='Apply'] span[class='dx-button-text']")
	WebElement applyColumnChooserBtn;
	
	@FindBy(xpath = "//div[@aria-label='Cancel']//div[@class='dx-button-content']")
	WebElement cancelBtn;
	
	@FindBy(xpath = "//input[@aria-label='Search']")
	WebElement searchbox;
	
	@FindBy(xpath = "//div[@class='dx-column-indicators']/following-sibling::div")
	List<WebElement> columnNameList;
	
	@FindBy(xpath = "//div[@class='dx-list-select-all']/div[1]")
	WebElement selectAllCheckbox;
	
	public  boolean isWarning() {
		boolean flag;
		try {
			wait.waitForElement(warningMessage);
			flag = warningMessage.isDisplayed();
			
		 	}
		catch(NoSuchElementException e) {
			logger.info("Warning message not displayed");
			return false;
		}		
		catch(TimeoutException e) {
			logger.info("Warning message not displayed");
			return false;
		}
		 elementAct.clickElementByJS(crossBtn, "closing warning message");		 
		 return flag;
	}	
	public boolean isSuccess() {
		boolean flag;
		try {
			wait.waitForElement(successMessage);
			flag = successMessage.isDisplayed();
		 	}
		catch(NoSuchElementException e) {
			logger.info("Success message not displayed");
			return false;
		}		
		catch(TimeoutException e) {
			logger.info("Success message not displayed");
			return false;
		}
	elementAct.clickElementByJS(crossBtn, "close Sucess msg");
		 return flag;
	}

	public boolean isError() {
		boolean flag;
		try {
			wait.waitForElement(errorMessage);
			flag = errorMessage.isDisplayed();			
			System.out.println(flag);
		 	}
		catch(NoSuchElementException e) {
			logger.info("Error message not displayed");
			return false;
		}
		catch(TimeoutException e) {
			logger.info("Error message not displayed");
			return false;
		}
		elementAct.clickElementByJS(crossBtn, "close Error msg");
		return flag;
	}
	
	public int getNumberOfColumnsInGrid() {
		return columnNameList.size();
	}
	public boolean columnChooser(String[] values) {
		List<WebElement> checkbox;
		boolean flag = false;
		elementAct.clickElementByJS(columnChooserBtn, "Column Chooser");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		wait.waitForElementToBeClickable(selectAllCheckbox);
		try {
		if(selectAllCheckbox.getAttribute("aria-checked").equals("true"))
			elementAct.clickElementByJS(driver.findElement(By.xpath("//div[text()='Select All']")), "Unselecting All");
////			driver.findElement(By.xpath("//div[@class='dx-list-select-all']/div/div")).click();
//		logger.info("Select All Checkbox clicked");
//			driver.findElement(By.xpath("//div[@class='dx-list-select-all-label']")).click();
		}
		catch(Exception e) {
			logger.info("Not able to click on Select All checkbox");
			return false;
		}
//			elementAct.clickElement(selectAllCheckbox, "Unselecting All");
		for(int i=0; i<values.length; i++) {
			elementAct.enterDataInTextbox(searchbox, "Search Column", values[i]);
			checkbox = driver.findElements(By.xpath("//div[@class='dx-list-select-checkbox dx-show-invalid-badge dx-checkbox dx-widget']//span[@class='dx-checkbox-icon']"));
			if(checkbox.size() == 0) {
				elementAct.clickElementByJS(cancelBtn, "Cancel Btn");
				logger.info("Column Chooser"+" is cancelled.");
				return flag;
			}
			elementAct.clickElementByJS(checkbox.get(0), "Select column");
		}
		elementAct.clickElementByJS(applyColumnChooserBtn, "Apply Column Chooser");
		flag = true;
		logger.info("Column Chooser is applied successfully.");
		return flag;
				
	}

}
