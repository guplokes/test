package com.cat.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class SOVPreviewDataPage extends BaseClass {

	public SOVPreviewDataPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@title='Search']//i[@class='dx-icon dx-icon-search']")
	WebElement searchBtn;

	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[2]")
	WebElement locIdSearchbox;
	
	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[8]")
	List<WebElement> cityTextboxList;
	
	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[9]")
	List<WebElement> stateCodeTextboxList;
	
	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[10]")
	List<WebElement> zipTextboxList;
	
	@FindBy(xpath = "//input[@role='textbox']")
	WebElement textBox;
	
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-save']")
	WebElement saveButton;
	
	@FindBy(xpath = "//i[@class='ft-arrow-right']")
	WebElement nextBtn;
	
	@FindBy(xpath = "//i[@class='ft-arrow-left']")
	WebElement previousBtn;
	
	public void clickOnPreviousBtn() {
		elementAct.clickElementByJS(previousBtn, "Previous Button");
	}
	public void clickOnNextBtn() {
		elementAct.clickElementByJS(nextBtn, "Next Button");
	}
	public void clickOnSaveBtn() {
		elementAct.clickElementByJS(saveButton, "Save Button");
	}
	
	public boolean verifySOVPreviewDataPage() {
		WebElement element = driver.findElement(By.xpath("//h3[normalize-space()='SOV Preview Data']"));
		wait.waitForElement(element);
		String actualText = element.getText();
		String expectedText = "SOV Preview Data";
		return elementAct.verifyText(actualText, expectedText);
	}

	public void applySearchOnLocation(String locID) {
		elementAct.clickElementByJS(searchBtn, "Search Button");
		elementAct.enterData(locIdSearchbox, "Org Loc ID", locID);
		elementAct.clickElementByJS(searchBtn, "Search Button");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeSearchOnLocation() {
		elementAct.clickElement(searchBtn, "Search Button");
		elementAct.clearElementText(locIdSearchbox);
		elementAct.clickElementByJS(searchBtn, "Search Button");	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//This method modifies the city and zip of Location	
	public void modifyLocationData(String city, String stateCode, String zip) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.clearElementText(cityTextboxList.get(0));
		elementAct.enterDataInTextbox(textBox, "City", city);
		elementAct.clearElementText(stateCodeTextboxList.get(0));
		elementAct.enterDataInTextbox(textBox, "StateCode", stateCode);
		elementAct.clearElementText(zipTextboxList.get(0));
		elementAct.enterDataInTextbox(textBox, "Zip", zip);

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isLocationDataModified(String locId, String city, String stateCode, String zip) {
		boolean flag = true;
		applySearchOnLocation(locId);
		if(!cityTextboxList.get(0).getText().equalsIgnoreCase(city))
			flag = false;
		if(!stateCodeTextboxList.get(0).getText().equalsIgnoreCase(stateCode))
			flag = false;		
		if(!zipTextboxList.get(0).getText().equalsIgnoreCase(zip))
			flag = false;
		
		removeSearchOnLocation();		
		return flag;
	}
}