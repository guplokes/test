package com.cat.pages;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class LocationFinancialPage extends BaseClass {

	public LocationFinancialPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[normalize-space()='Location Financial']")
	WebElement locationFinancialTab;
	
	@FindBy(xpath = "//h5[normalize-space()='Location']")
	WebElement tabLocation;
	
	@FindBy(xpath="//div[@title='Select Peril']//input[@role='combobox']")
	WebElement ddPeril;
	
	@FindBy(xpath="//div[@title='Select Coverage']//input[@role='combobox']")
	WebElement ddCovType;
	
	@FindBy(xpath="//div[@title='Column Search']//i[@class='dx-icon dx-icon-search']")
	WebElement btnSearch;
	
	@FindBy(css = "[title='Edit Location Financial']")
	List<WebElement> btnEdit;
	
	@FindBy(css = "[title='Save']")
	List<WebElement> btnSave;
	
	@FindBy(xpath="//*[text()='LocationNumber']//following::td[12]")
	WebElement txtLocNum;
	
	@FindBy(xpath="//*[text()='Limit Type']//following::td[12]")
	WebElement ddLmtTyp;
	
	@FindBy(xpath="//*[text()='Limit']//following::td[12]")
	WebElement txtLmt;
	
	@FindBy(xpath="//*[text()='Deductible Type']//following::td[12]")
	WebElement ddDedTyp;
	
	@FindBy(xpath="//*[text()='Deductible']//following::td[12]")
	WebElement txtDed;
	
	@FindBy(xpath="//*[text()='Min Deductible']//following::td[12]")
	WebElement txtMinDed;
	
	@FindBy(xpath="//*[text()='Max Deductible']//following::td[12]")
	WebElement txtMaxDed;
	
	@FindBy(xpath = "//dx-button[@title='Previous Tab']")
	WebElement previousTab;

	@FindBy(xpath = "//dx-button[@title='Next Tab']")
	WebElement nextTab;
	
	
	public boolean verifyLocationFinancialTab() {
		wait.waitForElementsAttribute(locationFinancialTab, "class", "nav-link p-0 active");
		String[] str = locationFinancialTab.getAttribute("class").split(" ");
		String text = str[2];
		System.out.println(text);
		boolean flag = elementAct.verifyText("active", text);
		return flag;
	}
	public void clickTabLocFinancial() throws InterruptedException {
		locationFinancialTab.click();
		Thread.sleep(5000);
		logger.info("Location financial tab is clicked.");
	}
	
	public void clickTabLocMaster() throws InterruptedException {
		Thread.sleep(5000);
		tabLocation.click();
		Thread.sleep(5000);
	}
	
	public void updateLocFinDetails(String peril, String covType, String locNum, String lmtType, String lmt, 
			String dedType, String ded, String minDed, String maxDed)  throws AWTException, InterruptedException {
		eleAct.selectDDOptions(ddPeril, "Peril", peril);
		Thread.sleep(5000);
		eleAct.selectDDOptions(ddCovType, "Coverage Type", covType);
		Thread.sleep(5000);
		eleAct.clickElement(btnSearch, "Search Icon");
		Thread.sleep(5000);
		eleAct.enterDataInTextbox(txtLocNum, "Location Number", locNum);
		Thread.sleep(5000);
		Thread.sleep(5000);
		eleAct.clickElement(btnSearch, "Search Icon");
		Thread.sleep(5000);
		Thread.sleep(5000);
		eleAct.clickElements(btnEdit, "Edit Icon");
		Thread.sleep(5000);	
		eleAct.selectGridDDOptions(ddLmtTyp, "Limit Type", lmtType);
		Thread.sleep(5000);	
		eleAct.enterDataInTextbox(txtLmt, "Limit", lmt);
		Thread.sleep(5000);
		eleAct.selectGridDDOptions(ddDedTyp, "Deductible Type", dedType);
		Thread.sleep(5000);
		eleAct.enterDataInTextbox(txtDed, "Deductible", ded);
		Thread.sleep(5000);
		eleAct.enterDataInTextbox(txtMinDed, "Min Deductible", minDed);
		Thread.sleep(5000);
		eleAct.enterDataInTextbox(txtMaxDed, "Max Deductible", maxDed);
		Thread.sleep(5000);
		eleAct.clickElements(btnSave, "Save Icon");
		Thread.sleep(5000);
		clickTabLocMaster();
		clickTabLocFinancial();
		Thread.sleep(5000);
	}
	
	public void clickPreviousTab() {

		//elementAct.clickElement(previousTab, "Previous Tab Buton of Policy Layer");
		elementAct.clickElementByJS(previousTab, "Previous Tab Buton of Policy Layer");
	}

	public void clickNextTab() {
		//elementAct.clickElement(nextTab, "Next Tab Buton of Policy Layer");
		elementAct.clickElementByJS(nextTab, "Next Tab Buton of Policy Layer");

	}
	
}
