 package com.cat.pages;

import java.text.ParseException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.cat.testCases.BaseClass;

public class BasicAcDetailsPage extends BaseClass {
	String pageName = "Basic Account Details"; //pageTitle is different here
	public  BasicAcDetailsPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[normalize-space()='Basic Account Details']")
	WebElement title;
	
	@FindBy(xpath = "//a[normalize-space()='Policy Layer']")
	WebElement policyLayerTab;
	
	@FindBy(xpath = "//a[normalize-space()='Modelling Information']")
	WebElement modellingInformationTab;
	
	@FindBy(xpath = "//a[normalize-space()='Policy Blanket Details']")
	WebElement policyBlanketDetailsTab;
	
	@FindBy(xpath = "//a[normalize-space()='Peril Level Financial Details']")
	WebElement perilLevelFinancialDetailsTab;
	
	@FindBy(xpath = "//h5[normalize-space()='Location']")
	WebElement locationTab;
	
	@FindBy(xpath = "//a[normalize-space()='Location Details']")
	WebElement locationDetailsTab;
	
	@FindBy(xpath = "//a[normalize-space()='Location Coverages']")
	WebElement locationCoveragesTab;
	
	@FindBy(xpath="//a[normalize-space()='Location Financial']")
	WebElement locationFinancialTab;
	
	//update Basic Account details
	
	@FindBy(xpath = "//*[text()='Inception Date']//following::div[5]")
	WebElement inceptionDateCal;
	
	@FindBy(xpath = "//*[text()='Currency']//following::div[1]")
	WebElement currencyDD;
	
	@FindBy(xpath = "//input[@name='grossPremium']")
	WebElement grossPremiumTextbox;
	
	@FindBy(xpath = "//input[@name='tax']")
	WebElement taxTextbox;
	
	@FindBy(xpath = "//input[@name='brokerage']")
	WebElement brokerageTextbox;
	
	@FindBy(xpath = "//input[@name='netPremium']")
	WebElement netPremiumTextBox;
	
	@FindBy(xpath = "//*[text()='PayoutBasis']//following::div[1]")
	WebElement payoutBasisDD;
	
	@FindBy(xpath = "//dx-number-box[@id='participation']//input[@role='spinbutton']")
	WebElement participationTextbox; //percentage
	
	@FindBy(xpath = "//input[@name='reinsuredName']")
	WebElement reinsuredNameTextbox;
	
	@FindBy(xpath = "//*[text()='Perils']//following::div[1]")
	WebElement perilsDD;
	
	@FindBy(xpath = "//*[text()='Industry Type']//following::div[1]")
	WebElement industryTypeDD;
	
	@FindBy(xpath = "//input[@name='policyForm']")
	WebElement policyFormTextbox;
	
	@FindBy(xpath = "//input[@name='AccUserDef1']")
	WebElement userDefined1;
	
	@FindBy(xpath = "//input[@name='AccUserDef2']")
	WebElement userDefined2;
	
	@FindBy(xpath = "//input[@name='AccUserDef4']")
	WebElement userDefined4;
	
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-revert']")
	WebElement undoBtn;
	
	EventFiringWebDriver event = new EventFiringWebDriver(driver);
	

	
	public boolean verifyBasicAccountDetailsTab() {
		String[] str = title.getAttribute("class").split(" ");
		String text = str[2];
		boolean flag = elementAct.verifyText("active", text);
		return flag;
	}
	
	public PolicyLayerPage clickPolicyLayerTab() {
		elementAct.clickElementByJS(policyLayerTab, "Policy Layer Tab");
		return new PolicyLayerPage();
	}
	
	public PolicyLayerPage clickModellingInformationTab() {
		elementAct.clickElementByJS(modellingInformationTab, "Modelling Information Tab");
		return new PolicyLayerPage();
	}
	
	public PolicyBlanketDetailsPage clickPolicyBlanketDetailsTab() {
		elementAct.clickElementByJS(policyBlanketDetailsTab, "Policy Blanket Details");
		return new PolicyBlanketDetailsPage();
	}
	
	public PerilLevelFinancialDetailsPage clickPerilLevelFinancialDetailsTab() {
		elementAct.clickElementByJS(perilLevelFinancialDetailsTab, "Peril Level Financial Details");
		return new PerilLevelFinancialDetailsPage();
	}
	
	//for location Master Tab
	public LocationMasterPage clickLocationMasterTab() {
		elementAct.clickElementByJS(locationTab, "Location Master Tab");
		return new LocationMasterPage();
	}
	
	public LocationDetailsPage clickLocationDetailsTab() {
		elementAct.clickElementByJS(locationTab, "Location Master Tab");
		elementAct.clickElementByJS(locationDetailsTab, "location details tab");
		return new LocationDetailsPage();
	}
	
	public LocationCoveragesPage clickLocationCoveragesTab() {
		elementAct.clickElementByJS(locationTab, "Location Master Tab");
		elementAct.clickElementByJS(locationCoveragesTab ,"location coverages tab");
		return new LocationCoveragesPage();
	}
	
	public LocationFinancialPage clickLocationFinancialTab() {
		elementAct.clickElementByJS(locationTab, "Location Master Tab");
		elementAct.clickElementByJS(locationFinancialTab ,"location financial tab");
		return new LocationFinancialPage();
	}
	private void scrollForm(String pixels) {
		String query = "document.querySelector('div[id=\"AccountForm\"] div[class=\"job-form-other-2 vertical_scroll_newacc cusscroll1\"]').scrollTop="
				+ pixels ;
		
		event.executeScript(query);
	}
	public void update(String currency, String grossPremium, String tax, String brokerage, String netPremium,
			String payoutBasis, String participation, String reinsuredName, String perils, String industryType, String policyForm,
			String userDef1, String userDef2, String userDef3, String userDef4) throws ParseException, InterruptedException {
//		Actions act = new Actions(driver);
		Thread.sleep(2000);
		elementAct.selectDate(inceptionDateCal, "Inception Date ");
		elementAct.selectDDOptions(currencyDD, "Currency Dropdown", currency);
		Thread.sleep(2000);
		//elementAct.scrollIntoView(grossPremiumTextbox);
		scrollForm("0");
		
		//elementAct.enterDataByJS(grossPremiumTextbox, "GrossPremium", grossPremium);
		elementAct.enterDataInTextbox(grossPremiumTextbox, "GrossPremium", grossPremium);
		Thread.sleep(2000);
		elementAct.enterDataInTextbox(taxTextbox, "Tax", tax);
		elementAct.enterDataInTextbox(brokerageTextbox, "Brokerage", brokerage);
		elementAct.enterDataInTextbox(netPremiumTextBox, "NetPremium", netPremium);
		elementAct.selectDDOptions(payoutBasisDD, "PayoutBasis Dropdown", payoutBasis);
		//act.moveToElement(participationTextbox).build().perform();
		Thread.sleep(6000);
		
		scrollForm("100");
		
		Thread.sleep(4000);
		
		//elementAct.scrollIntoView(participationTextbox);		
		elementAct.enterDataInTextbox(participationTextbox, "Participation", participation);		
		Thread.sleep(4000);		
		elementAct.enterDataInTextbox(reinsuredNameTextbox, "ReinsuredName", reinsuredName);
		elementAct.selectDDOptions(industryTypeDD, "industryType Dropdown", industryType);
		Thread.sleep(4000);
		scrollForm("120");
		Thread.sleep(4000);
				
		elementAct.enterDataByJS(policyFormTextbox, "PolicyForm", policyForm);
		elementAct.enterDataByJS(userDefined1, "UserDef1", userDef1);
		elementAct.enterDataByJS(userDefined2, "UserDef2", userDef1);
		elementAct.enterDataByJS(userDefined4, "UserDef4", userDef4);
		Thread.sleep(8000);
		//elementAct.clickElement(undoBtn, "Undo");
		
		
		
		Thread.sleep(8000);
	}
}
