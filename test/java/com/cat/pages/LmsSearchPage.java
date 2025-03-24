package com.cat.pages;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class LmsSearchPage extends BaseClass {

	public LmsSearchPage() {
		PageFactory.initElements(driver, this);
	}

	String pageTitle = "Xceedance | Search Account";

	@FindBy(xpath = "//*[text()='Accounts']")
	private WebElement LMSPageVerifiation;

	@FindBy(xpath = "//*[@id=\"toast-container\"]")
	private WebElement AcCreationLabel;

	@FindBy(css = "[title='New Account'] i")
	private WebElement addNewAcButton;

	@FindBy(xpath = "//div[@title='Column Search']//i")
	private WebElement columnSearchButton;

	@FindBy(xpath = "(//i[@class='la la-pencil'])[1]")
	private WebElement updateAcButton;
	 
	@FindBy(xpath = "//input[@aria-autocomplete='inline']")
//	@FindBy(xpath = "//dx-autocomplete[@valueexpr='AccountName']/div")
	private WebElement searchAcByNameTextBox;
	
	@FindBy(xpath = "//dx-button[@aria-label='search']/div")
	private WebElement searchAcByNameBtn;
	
	@FindBy(xpath = "//div[@title='Column Filter']//i")
	private WebElement filterButton;
	
	//Advanced Search Buttons beloW:
	
	@FindBy(xpath = "//div[@title='Advanced Search']")
	private WebElement advancedSearchButton;
	
	@FindBy(xpath = "//div[@class='form-group mt-1']/dx-select-box")
	private WebElement dropdown;
	
	@FindBy(xpath = "//input[@name='partyCode']")
	private WebElement partyCode;
	
	@FindBy(xpath = "(//input[@name='prefix'])[1]")
	private WebElement prefix;
	
	@FindBy(xpath = "//input[@name='suffix']")
	private WebElement suffix;
	
	@FindBy(xpath = "(//input[@name='prefix'])[2]")
	private WebElement extension;
	
	@FindBy(xpath = "//button[normalize-space()='Submit']")
	private WebElement submit;
	
	@FindBy(xpath = "//button[normalize-space()='Reset']")
	private WebElement reset;
	
	@FindBy(xpath = "//i[@class='la la-close']")
	private WebElement closeAdvancedFilter;
	
	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[1]")
	WebElement accountNumberSearchbox;
	
	@FindBy(xpath = "(//i[@class='la la-file-excel-o'])[1]")
	WebElement lmsExportBtn;
	
	@FindBy(xpath = "//i[@class='la la-desktop']")
	WebElement exportMonitorBtn;
	
	@FindBy(xpath = "//table[@class='dx-datagrid-table dx-datagrid-table-fixed']//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]//td[3]")
	List<WebElement> accountNameList;
	
	String AcCreationMsg = "Data Saved Successfully.";
	
	public boolean verifyLMSSearchPage()
	{
		wait.waitForElement(LMSPageVerifiation);		
		return elementAct.verifyWebPageTitle(pageTitle, "LMS Search Page");
	}
	
	public void openNewAccountForm() {
		wait.waitForElement(addNewAcButton);
		elementAct.clickElement(addNewAcButton, "New Account Button");
	}
	
	public int getNumberOfRows() {
		return accountNameList.size();
	}
	
	
//	  public void searchAnAccount(String LMSAcNAme) {
//	  //LMSAcName="Next Level Church - SOV_2";
//	  wait.waitForElement(searchAcByNameTextBox);
//	  //elementAct.enterDataByJS(searchAcByNameTextBox, "Account Name Search",
//	  LMSAcName); //eleAct.enterDetails(searchAcByNameTextBox, LMSAcNAme);
//	  eleAct.actionSendKey(searchAcByNameTextBox, LMSAcNAme);
//	  searchAcByNameTextBox.click(); Actions act = new Actions(driver);
//	  act.moveToElement(searchAcByNameTextBox).keyDown(Keys.ENTER).build().perform(
//	  ); wait.waitForElement(searchAcByNameBtn);
//	  elementAct.clickElement(searchAcByNameBtn, "Search Icon");
//	  //elementAct.searchElement(LMSAcName, "Account"); }
	  
	  public boolean searchAccountByName(String accountName) {
		  wait.waitForElementToBeClickable(searchAcByNameTextBox);
		  elementAct.enterData(searchAcByNameTextBox, "Search Account By Name", accountName);
		  elementAct.clickElementByJS(searchAcByNameBtn, "Search Button");
		  try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  String text;
		  for(int i = 0; i < accountNameList.size(); i++) {
			  text = accountNameList.get(i).getText();
			  if(!text.equals(accountName))
				  return false;
		  }
//		  return accountNameList.get(0).getText().equals(accountName);
		  return true;
	  }
	 
	
	public WebElement getSearchAccountTextbox() {
		return searchAcByNameTextBox;
	}
	
	public WebElement getSearchAccountButton() {
		return searchAcByNameBtn;
	}
	
	public void clickFilterButton() {
		elementAct.clickElement(filterButton,"Filter Button");
	}
	
	public void clickColumnSearchButton() {
		elementAct.clickElement(columnSearchButton,"columnSearch Button");
	}
	
	public void clickLmsExportButton() {
		elementAct.clickElementByJS(lmsExportBtn, "Lms Export");;
	}
	
	public void clickExportMonitorIcon() {
		elementAct.clickElementByJS(exportMonitorBtn, "Export Monitor Icon ");
	}
	
	public void searchByAccountNumber(String accountNumber) {
		clickColumnSearchButton();
		elementAct.enterData(accountNumberSearchbox, "Account to update", accountNumber);
	}
	
	public NewAccountFormPage clickAddNewAcButton() {
		elementAct.clickElementByJS(addNewAcButton, "New Account Button from Lms");
		return new NewAccountFormPage();
		
	}
	
	public BasicAcDetailsPage clickUpdateAccountButton() {

		elementAct.clickElementByJS(updateAcButton, "Update Account on Lms");
		return new BasicAcDetailsPage();
	}
	
	public void advanceFilter(String ddOption,String partyCodeValue, String prefixValue, String suffixValue, String extensionValue) {
		elementAct.clickElementByJS(advancedSearchButton, "Advanced Search Button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.selectDDOptions(dropdown, ddOption, ddOption);
		elementAct.enterDataByJS(partyCode, "partyCode", partyCodeValue);
		elementAct.enterData(prefix, "prefix", prefixValue);
		elementAct.enterData(suffix, "suffix", suffixValue);
		elementAct.enterData(extension, "extension", extensionValue);
		elementAct.clickElementByJS(submit, "Submit button");
		elementAct.clickElementByJS(closeAdvancedFilter, "Close Advanced Filter Menu");
		
	}
	
	public void resetAdvanceFilter() {
		elementAct.clickElementByJS(advancedSearchButton, "Advanced Search Button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.clickElementByJS(reset, "Reset Advanced Search Filter");
		elementAct.clickElementByJS(closeAdvancedFilter, "Close Advanced Filter Menu");		
	}
	public void openAcUpdateFrom() {
		wait.waitForElement(updateAcButton);
		elementAct.clickElement(updateAcButton, "Update Account Button");
	}

	public void verifyAccountCreation() throws InterruptedException {
		wait.waitForElement(AcCreationLabel);
		elementAct.verifyMessage(AcCreationLabel, AcCreationMsg, "Account Creation");
	}	
}
