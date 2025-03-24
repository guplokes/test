package com.cat.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.cat.testCases.BaseClass;

public class BulkUpdatePage extends BaseClass {
	String title = "Xceedance | Update Information";
	
	public BulkUpdatePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-edit']")
	WebElement bulkUpdateIcon;
	
	@FindBy(xpath = "//dx-button[@title='Filter']//i[@class='dx-icon dx-icon-filter']")
	WebElement filterIcon;
		
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-trash']")
	WebElement deleteIcon;
	
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-remove']")
	WebElement clearFilter;
	
	@FindBy(xpath = "//div[@data-dx_placeholder='Select Entity']")
	WebElement entityDD;
	
	@FindBy(xpath = "//input[@placeholder='Enter value']")
	WebElement valueTextbox;	
	
	//@FindBy(xpath = "//span[@class='ng-star-inserted']")
	@FindBy(xpath = "//select[@class='form-control ng-untouched ng-pristine ng-valid']")
	WebElement operatorDD;
	
	@FindBy(xpath = "//span[normalize-space()='Filter']")
	WebElement applyFilterBtn;
	
	@FindBy(xpath = "//span[contains(text(),'Update')]")
	WebElement updateBtn;
	
	//delete popup
	@FindBy(xpath = "//button[normalize-space()='No']")
	WebElement deleteBtnNo;
	
	@FindBy(xpath = "//td[@aria-label='Select all']//span[@class='dx-checkbox-icon']")
	WebElement selectAllLocations;
	
	
	@FindBy(xpath = "//div[contains(text(),'Success')]")
	WebElement successMsg;
	
	public boolean verifyTitle() {
		return elementAct.verifyText(driver.getTitle(), title);
	}
	
	
	public boolean isSuccess() {

		wait.waitForElement(successMsg);
		return successMsg.isDisplayed();

	}
	
	//update this from elementAct Class
	public void selectEntityValue(String entity) {
		String xpath = "//div[@class='dx-item-content dx-list-item-content'][normalize-space()='" + entity + "']" ;
		WebElement ele = driver.findElement(By.xpath(xpath));
		ele.click();
	}
	//update this from elementAct Class
	private void selectOperatorValue(String operator) {
		Select options = new Select(operatorDD);
		options.selectByVisibleText(operator);
		
	}
	
	public void filterData(String entity, String operator, String value) {
		//elementAct.clickElement(clearFilter, "clear");
		elementAct.clickElementByJS(filterIcon, "Filterr Icon");
		elementAct.clickElementByJS(entityDD, "entity dropdown");
		selectEntityValue(entity); //update this from elementAct Class
		
		//elementAct.selectDropdown(entityDD, "selecting entity", entity);
		//elementAct.selectDDOptions(entityDD, "selecting entity", entity);
		//elementAct.selectDDOptions(operatorDD, "operator", operator);
		//elementAct.clickElementByJS(operatorDD, "Select operator");
		
		selectOperatorValue(operator);			 //update this from elementAct Class

		elementAct.enterDataByJS(valueTextbox, "Value", value);
		elementAct.clickElement(applyFilterBtn, "applying filter");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateData(String entity, String value) {
		elementAct.clickElementByJS(bulkUpdateIcon, "Update locations");
		elementAct.clickElementByJS(entityDD, "entity dropdown");
		selectEntityValue(entity); //update this from elementAct Class
		elementAct.enterDataByJS(valueTextbox, "Value", value);
		elementAct.clickElement(updateBtn, "Update locations");
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void deleteData() {
		elementAct.clickElement(selectAllLocations, "Selecting all");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.clickElement(deleteIcon, "Delete Btn");
		elementAct.clickElement(deleteBtnNo, "dismiss delete popup");
		Actions act = new Actions(driver);
		act.doubleClick(selectAllLocations).build().perform();
		
	}
	
	
	
}
