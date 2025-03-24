package com.cat.pages;

import java.awt.AWTException;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;
import com.cat.utilities.handleKeyboardEvent;
import com.cat.utilities.helper;

public class CmrNewRequestPage extends BaseClass {

	public CmrNewRequestPage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	String title = "Xceedance | New Request";

	@FindBy(xpath = "//a[@aria-controls='tabIcon32' ]")
	WebElement modellingInformationTab;

	@FindBy(xpath = "//input[@name='accountName']")
	WebElement accountNameTextbox;

	// Account no
	@FindBy(xpath = "//input[@name='accountNo']")
	WebElement submissionNoTextbox;

	@FindBy(xpath = "//dx-drop-down-box[@id='NoOfLocation']//div[@class='dx-dropdowneditor-icon']")
	WebElement noOfLocationsDD;

	@FindBy(xpath = "//input[@name='reinsuredName']")
	WebElement reinsuredNameTextbox;

	@FindBy(xpath = "//dx-drop-down-box[@id='IncludedPerils']//input[@role='combobox']")
	WebElement perilsDD;

	@FindBy(xpath = "//dx-number-box[@id='tiv']//input[@role='spinbutton']")
	WebElement tivTextbox;

	@FindBy(xpath = "//dx-date-box[@id='effectiveDate']//div[@class='dx-dropdowneditor-icon']")
	WebElement inceptionDate;

	@FindBy(xpath = "//dx-drop-down-box[@id='Underwriter']//div[@class='dx-dropdowneditor-icon']")
	WebElement underwriterDD;
	
	@FindBy(xpath = "(//input[@aria-label='Search'])[2]")
	WebElement underwriterDDSearchbox;
	
	@FindBy(xpath = "(//input[@aria-label='Search'])[3]")
	WebElement currencyDDSearchbox;

	@FindBy(xpath = "//*[text()='Currency']//following::div[1]")
	WebElement currencyDD;

	@FindBy(xpath = "//dx-drop-down-box[@id='UWBranch']//div[@class='dx-dropdowneditor-icon']")
	WebElement underwriterBranchDD;

	@FindBy(xpath = "//dx-date-box[@id='dueDate']//div[@class='dx-dropdowneditor-icon']")
	WebElement dueDate;

	@FindBy(xpath = "//dx-drop-down-box[@id='RequestType']//div[@class='dx-dropdowneditor-icon']")
	WebElement requestTypeDD;

	@FindBy(xpath = "//dx-drop-down-box[@id='Lob']//div[@class='dx-dropdowneditor-icon']")
	WebElement lineOfBuisnessDD;

	@FindBy(xpath = "//dx-drop-down-box[@id='Priority']//div[@class='dx-dropdowneditor-icon']")
	WebElement priorityDD;

	@FindBy(xpath = "//dx-drop-down-box[@id='Platform']//div[@class='dx-dropdowneditor-icon']")
	WebElement platformDD;

	@FindBy(xpath = "//dx-drop-down-box[@id='accountType']//div[@class='dx-dropdowneditor-icon']")
	WebElement accountTypeDD;

	@FindBy(xpath = "//input[@name='SOVFilePath']")
	WebElement sovFilepathTextbox;

	@FindBy(xpath = "//span[@class='switch disabled switch-small']")
	WebElement noTuchSwitch;

	@FindBy(xpath = "//input[@name='AccUserDef1']")
	WebElement userDefined1;

	@FindBy(xpath = "//input[@name='AccUserDef2']")
	WebElement userDefined2;

	@FindBy(xpath = "//input[@name='AccUserDef3']")
	WebElement userDefined3;

	@FindBy(xpath = "//input[@name='AccUserDef4']")
	WebElement userDefined4;

	@FindBy(xpath = "//input[@name='AccUserDef5']")
	WebElement userDefined5;

	@FindBy(xpath = "//input[@aria-label='Search']")
	WebElement perilSearch;
	
	@FindBy(css = "div[aria-label='Select file'] span[class='dx-button-text']")
	WebElement uploadFileBtn;

	public boolean verifyNewRequestPageTitle() {
		wait.waitForTitle(title);
		return elementAct.verifyWebPageTitle(title, "New Request");
	}
	
	public String randGeneratedStr(int l)
	 {
	 // a list of characters to choose from in form of a string
	 String alphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
	 // creating a StringBuffer size of AlphaNumericStr
	 StringBuilder s = new StringBuilder(l);
	 int i;

	 for ( i=0; i<l; i++) {
	   //generating a random number using math.random()
	   int ch = (int)(alphaNumericStr.length() * Math.random());
	   //adding Random character one by one at the end of s
	   s.append(alphaNumericStr.charAt(ch));
	  }
	    return s.toString();
	 }


	public ModellingInformationPage clickOnModellingInformation() {
		elementAct.clickElementByJS(modellingInformationTab, "Modelling Information");
		return new ModellingInformationPage();
	}

	private void selectingPerils(String perils) {
		String[] perilArr = perils.split("[,]", 0);
		elementAct.clickElementByJS(perilsDD, "Perils dropdown");
		for (String myStr : perilArr) {
			// elementAct.enterDataInTextbox(perilSearch, myStr , myStr);
			List<WebElement> element = driver.findElements(By.xpath("//span[normalize-space()='" + myStr + "']"));
			elementAct.clickElementByJS(element.get(0), myStr);
			
			// only for RSG or VRU below two lines
//			if(myStr.equals("All Earthquake"))
//				elementAct.clickElementByJS(element.get(0), myStr);
				
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	public void selectDropdownWithSearchbox(WebElement ddElement, WebElement searchbox, String elementName, String ddName) {
		elementAct.clickElementByJS(ddElement, ddName);
		elementAct.enterDataInTextbox(searchbox, "Searchbox", elementName);
		WebElement ele = driver.findElement(By.xpath("//div[contains(text(),'" + elementName + "')]"));
		elementAct.clickElementByJS(ele, elementName);
		elementAct.clickElementByJS(ddElement, ddName);
	}
	
//	public void selectInceptionDate() {
//		String date;
//		try {
//			date = helper.getDateAfterAddingDays();
//			elementAct.clickElementByJS(dueDate, "Primary Modelling Due Date");
////			String dateparam = "(//*[contains(@aria-label,'" + date + "')])" + "[3]";
//			String dateparam = "//div[@class='dx-overlay-content dx-popup-normal dx-resizable dx-dropdowneditor-overlay-flipped dx-popup-inherit-height'] //*[contains(@aria-label,'"
//					+ date + "')]";
//			driver.findElement(By.xpath(dateparam)).click();
//			logger.pass("Due Date selected successfully");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}

	private void selectPrimaryModellingDate() {
		String date;
		try {
			date = helper.getDateAfterAddingDays();
			elementAct.clickElementByJS(dueDate, "Primary Modelling Due Date");
//			String dateparam = "(//*[contains(@aria-label,'" + date + "')])" + "[3]";
			String dateparam = "//div[@class='dx-overlay-content dx-popup-normal dx-resizable dx-dropdowneditor-overlay-flipped dx-popup-inherit-height'] //*[contains(@aria-label,'"
					+ date + "')]";
			driver.findElement(By.xpath(dateparam)).click();
			logger.pass("Due Date selected successfully");
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	public void uploadSOVFile(String fileName) {
		elementAct.scrollIntoView(uploadFileBtn);
		elementAct.clickElementByJS(uploadFileBtn, "Upload SOV File");
		try {
			Thread.sleep(1000);
			handleKeyboardEvent.uploadFile(fileName);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void fillAccountDetails(String accountName, String submissionNumber, String noOfLocations,
			String reinsuredName, String perils, String tiv, String underwriterName, String currency,
			String underwriterBranch, String requestType, String lineOfBuisness, String priority, String platform,
			String accountType, String userDef1, String userDef2, String userDef3, String userDef4, String userDef5) {
		elementAct.enterData(accountNameTextbox, "Account name", accountName);
		elementAct.enterData(submissionNoTextbox, "Sumission no", submissionNumber);
		elementAct.selectDDOptions(noOfLocationsDD, "Number of locations DD", noOfLocations);
		// elementAct.selectDropdown(noOfLocationsDD, "Number of locations DD",
		// noOfLocations);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementAct.enterData(reinsuredNameTextbox, "Reinsured Name", reinsuredName);
		selectingPerils(perils);
		elementAct.enterData(tivTextbox, "TIV", tiv);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementAct.selectDate(inceptionDate, "Inception Date");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//Commenting for VRU demo - underwriter & currency
		selectDropdownWithSearchbox(underwriterDD, underwriterDDSearchbox, underwriterName, "Underwriter Name dropdown");
		selectDropdownWithSearchbox(currencyDD, currencyDDSearchbox, currency, "currency dropdown");

//		elementAct.selectDDOptions(underwriterBranchDD, "Underwriter Branch dropdown", underwriterBranch);
		selectPrimaryModellingDate();
		elementAct.selectDDOptions(requestTypeDD, "request type dropdown", requestType);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementAct.selectDDOptions(lineOfBuisnessDD, "LineofBuisness Dropdown", lineOfBuisness);
		elementAct.selectDDOptions(priorityDD, "priority dropdown", priority);
		elementAct.selectDDOptions(platformDD, "platform dropdown", platform);
		elementAct.selectDDOptions(accountTypeDD, "accountType dropdown", accountType);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementAct.enterData(userDefined1, "userDef1", userDef1);
		elementAct.enterData(userDefined2, "userDef2", userDef2);
		elementAct.enterData(userDefined3, "userDef3", userDef3);
		elementAct.enterData(userDefined4, "userDef4", userDef4);
		elementAct.enterData(userDefined5, "userDef5", userDef5);
	}

}
