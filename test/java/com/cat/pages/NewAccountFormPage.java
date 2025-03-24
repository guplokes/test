package com.cat.pages;
import java.awt.AWTException;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;
import com.cat.utilities.helper;

public class NewAccountFormPage extends BaseClass {

	String pageTitle = "Xceedance | Add Account";

	public NewAccountFormPage() {
		PageFactory.initElements(driver, this);
		LMSAcName="LMSAC_" + helper.getCurrentDateTime();
	}
	
	@FindBy(xpath = "//*[text()='Account Form']")
	WebElement LMSAcFormVerifiation;

	@FindBy(xpath = "//*[@id=\"acnamefilter​​\"]")
	WebElement AcNameIcon;

	@FindBy(id = "txtPartyCode")
	WebElement PartyCodeTextbox;

	@FindBy(id = "txtPartyName")
	WebElement PartyNameTextbox;

	@FindBy(xpath = "//*[contains(@aria-label,'OK')]")
	WebElement AddPartyCodeNameButton;

	@FindBy(xpath = "//*[@id=\"txtPartyDesc\"]")
	WebElement DescriptionTextbox;

	@FindBy(xpath = "//*[@id=\"toast-container\"]")
	WebElement PartyCodeCreationMsg;

	@FindBy(id = "accountNumber")
	WebElement AcNumTextbox;

	@FindBy(xpath = "//*[text()='Inception Date']//following::div[5]")
	WebElement InceptionDateCal;

	@FindBy(xpath = "//*[text()='Underwriter Name']//following::div[1]")
	WebElement UnderwriterDD;

	@FindBy(xpath = "//*[text()='Currency']//following::div[1]")
	WebElement CurrencyDD;

	@FindBy(id = "grossPremium")
	WebElement GrossPremiumTextbox;

	@FindBy(id = "tax")
	WebElement TaxTextbox;

	@FindBy(id = "brokerage")
	WebElement BrokerageTextbox;

	@FindBy(id = "netPremium")
	WebElement NetPremiumTextbox;

	@FindBy(xpath = "//*[text()='LOB']//following::div[1]")
	WebElement LOBDD;

	@FindBy(xpath = "//*[text()='PayoutBasis']//following::div[1]")
	WebElement PayoutBasisDD;

	@FindBy(id = "participation")
	WebElement ParticipationTextbox;

	@FindBy(id = "reinsuredName")
	WebElement ReinsuredNameTextbox;

	@FindBy(xpath = "//*[text()='Perils']//following::div[1]")
	WebElement PerilsDD;

	@FindBy(xpath = "//*[text()='Industry Type']//following::div[1]")
	WebElement IndustryTypeDD;

	@FindBy(id = "policyForm")
	WebElement PolicyFormTextbox;
	
	@FindBy(xpath = "//*[text()='Coverages']//following::div[1]")
	WebElement CoveragesDD;

	@FindBy(id = "AccUserDef1")
	WebElement UDF1Textbox;

	@FindBy(id = "AccUserDef2")
	WebElement UDF2Textbox;

	@FindBy(id = "AccUserDef3")
	WebElement UDF3Textbox;

	@FindBy(id = "AccUserDef4")
	WebElement UDF4Textbox;

	@FindBy(id = "AccUserDef5")
	WebElement UDF5Textbox;

	@FindBy(id = "AccUserDef6")
	WebElement UDF6Textbox;

	@FindBy(id = "AccUserDef7")
	WebElement UDF7Textbox;

	@FindBy(id = "AccUserDef8")
	WebElement UDF8Textbox;

	@FindBy(id = "AccUserDef9")
	WebElement UDF9Textbox;

	@FindBy(id = "AccUserDef10")
	WebElement UDF10Textbox;
	
	@FindBy(xpath = "//*[contains(@aria-label,'Submit')]")
	WebElement SubmitButton;
	
	String ExpPartyCodeAddionMsg = "New Party added successfully.";

	public boolean verifyNewAccountFormPage() {
		
		wait.waitForElement(LMSAcFormVerifiation);		
		//elementAct.verifyWebPage(pageTitle, "LMS New Account Page");	
		return elementAct.verifyWebPageTitle(pageTitle, "LMS New Account Page" );
	}

	public void ValidatePartyCodeAdded() throws InterruptedException {
		wait.waitForElement(PartyCodeCreationMsg);		
		elementAct.verifyMessage(PartyCodeCreationMsg, ExpPartyCodeAddionMsg, "Party Code Creation");
	}

	public void selectCurrency(String curValue) {
		eleAct.clickElementByJS(CurrencyDD);
		eleAct.actionSendKeys(CurrencyDD, curValue);
		List<WebElement> element = driver.findElements(By.xpath("//div[text()='" + curValue + "']"));
		eleAct.clickElementByJS(element.get(0));
		logger.pass("Currency is selected successfully: Passed");
	}
	
	public void selectCoverages(String Coverages)  {
		elementAct.clickElementByJS(CoveragesDD, "Coverage Type");
		if(!Coverages.contains("Building")) {
			driver.findElement(By.xpath("//*[text()='Building']")).click();
		}
		
		if(!Coverages.contains("Contents")) {
			driver.findElement(By.xpath("//*[text()='Contents']")).click();
		}
		
		if(!Coverages.contains("Other")) {
			List<WebElement> element = driver.findElements(By.xpath("//*[text()='Other']"));
			eleAct.clickElementByJS(element.get(1));
		}
		
		if(!Coverages.contains("Business Interruption")) {
			driver.findElement(By.xpath("//*[text()='Business Interruption']")).click();
		}
		
		if(!Coverages.contains("Property Damage")) {
			driver.findElement(By.xpath("//*[text()='Property Damage']")).click();
		}
		
		if(!Coverages.contains("All")) {
			driver.findElement(By.xpath("//*[text()='All']")).click();
		}
		
		logger.info("Appropriate coverages are selected successfully.");
	}

	public void FillNewAcForm(String UWName, String Currency, String GrossPremium, String Tax, String Brokerage,
			String NetPremium, String LOB, String PayoutBasis, String Participation, String ReinsuredName, String perilList,
			String IndustryType, String PolicyForm, String coverageList, String UDF1, String UDF2, String UDF3, String UDF4, String UDF5,
			String UDF6, String UDF7, String UDF8, String UDF9, String UDF10) throws ParseException, InterruptedException, AWTException {
		wait.waitForElement(AcNameIcon);
	/*	elementAct.clickElement(AcNameIcon, "Account Name Icon");
		
		wait.waitForElement(PartyCodeTextbox);
		elementAct.enterDataByJS(PartyCodeTextbox, "Party Code", "PC_" + helper.getCurrentDateTime());
		elementAct.enterDataByJS(PartyNameTextbox, "Party Name", LMSAcName);
		elementAct.enterDataByJS(DescriptionTextbox, "Description", "desc");
		elementAct.clickElement(AddPartyCodeNameButton, "Ok Button for Party Code"); */
		
	//	ValidatePartyCodeAdded();
		
		elementAct.enterDataByJS(AcNumTextbox, "Account Number","LMSAcNum_" + helper.getCurrentDateTime());
		elementAct.selectDate(InceptionDateCal, "Inception Date");
		elementAct.selectDDOptions(UnderwriterDD, "Underwriter Name", UWName);
		elementAct.selectDDOptions(CurrencyDD, "Currency", Currency);
		
		elementAct.enterDataByJS(GrossPremiumTextbox, "Gross Premium",GrossPremium);
		elementAct.enterDataByJS(TaxTextbox, "Tax",Tax);
		elementAct.enterDataByJS(BrokerageTextbox, "Brokerage",Brokerage);
		elementAct.enterDataByJS(NetPremiumTextbox, "Net Premium",NetPremium);
		elementAct.selectDDOptions(LOBDD, "LOB", LOB);
		elementAct.selectDDOptions(PayoutBasisDD, "Payout Basis", PayoutBasis);
		
		elementAct.enterDataByJS(ParticipationTextbox, "Participation",Participation);
		elementAct.enterDataByJS(ReinsuredNameTextbox, "Reinsured Name",ReinsuredName);
		
		elementAct.selectMultiSelectDDOptions(PerilsDD, "Perils", perilList);
		elementAct.selectDDOptions(IndustryTypeDD, "Industry Type", IndustryType);
		elementAct.enterDataByJS(PolicyFormTextbox, "Policy Form",PolicyForm);
		
		selectCoverages(coverageList);
		/*
		// Enter User Defined value
		elementAct.enterDataByJS(UDF1Textbox, "UDF 1",UDF1);
		elementAct.enterDataByJS(UDF2Textbox, "UDF 2",UDF2);
		elementAct.enterDataByJS(UDF3Textbox, "UDF 3",UDF3);
		elementAct.enterDataByJS(UDF4Textbox, "UDF 4",UDF4);
		elementAct.enterDataByJS(UDF5Textbox, "UDF 5",UDF5);
		elementAct.enterDataByJS(UDF6Textbox, "UDF 6",UDF6);
		elementAct.enterDataByJS(UDF7Textbox, "UDF 7",UDF7);
		elementAct.enterDataByJS(UDF8Textbox, "UDF 8",UDF8);
		elementAct.enterDataByJS(UDF9Textbox, "UDF 9",UDF9);
		elementAct.enterDataByJS(UDF10Textbox, "UDF 10",UDF10);
		
		*/
		//elementAct.clickElement(SubmitButton, "Submit Button");
	}
}
