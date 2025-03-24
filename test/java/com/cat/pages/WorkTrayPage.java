package com.cat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class WorkTrayPage extends BaseClass {

	public WorkTrayPage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	String title = "Xceedance | Work Tray";
	String processDetailsPageTitle = "Xceedance | Account Details";
	String additionalEvaluationPageTitle = "Xceedance | New Request";
	@FindBy(xpath = "//div[@aria-label='Success']")
	WebElement successMessage;

	@FindBy(xpath = "//dx-button[@text='Save']")
	WebElement saveBtn;

	@FindBy(xpath = "//dx-button[@text='Reset']	")
	WebElement resetBtn;

	@FindBy(xpath = "//i[@class='ft-plus-circle']")
	WebElement newRequest;
	
	@FindBy(xpath = "//i[@class='la la-black-tie']")
	WebElement assignWork;

	@FindBy(xpath = "//div[@title='Column Search']//div[@class='dx-button-content']")
	WebElement columnSearchBtn;

	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[2]")
	WebElement accountNameColumnSearch;
	
//	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[9]")
//	@FindBy(xpath = "//tbody/tr[@role='row']/td[9]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]")
	@FindBy(xpath = "//tbody/tr[@role='row']/td[10]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]")
	WebElement currentUserColumnSearch;
	
//	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[8]")
//	@FindBy(xpath = "//tbody/tr[@role='row']/td[8]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]")
	@FindBy(xpath = "//tbody/tr[@role='row']/td[9]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]")
	WebElement processingStatusColumnSearch;

	// will only work when there is only one row after Search
	@FindBy(xpath = "(//i[@class='la la-trash-o'])[2]")
	WebElement deleteBtn;

	@FindBy(xpath = "//button[@class='btn btn-primary']")
	WebElement cancelDeleteBtn;

	@FindBy(xpath = "//div[@id='delete-alert']//p[1]")
	WebElement deletePopupText;

	@FindBy(xpath = "(//a[@title='View Record Time Details']//i)[2]")
	WebElement recordTimeDetailsBtn;

	@FindBy(xpath = "//i[@class='la la-long-arrow-left']")
	WebElement backBtn;

	@FindBy(xpath = "(//a[@title='View Process Details']//i)[2]")
	WebElement processDetailsBtn;

	@FindBy(xpath = "(//a[@title='Add Additional Evaluation']//i)[2]")
	WebElement additionalEvalutionBtn;

	@FindBy(xpath = "(//a[@title='Update Proposed Date']//i)[2]")
	WebElement updateProposedDateBtn;

	@FindBy(xpath = "//dx-date-box[@id='proposedDueDate']//div[@class='dx-dropdowneditor-icon']")
	WebElement proposedDate;

	@FindBy(xpath = "//div[contains(text(),'Update')]")
	WebElement updateBtn;
	
	@FindBy(xpath = "(//a[@title='Query Log']//i)[2]")
	WebElement queryLogBtn;

	@FindBy(xpath = "(//div[@class='dx-dropdowneditor-icon'])[2]")
	WebElement addNewQueryBtn;

	@FindBy(xpath = "//dx-button[@text='Back']")
	WebElement queryBackBtn;

	// Advanced Search WebElements
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-fieldchooser']")
	WebElement advancedSearchBtn;

	@FindBy(xpath = "//dx-text-box[@placeholder='Account Name']//input[@role='textbox']")
	WebElement accountNameTextbox;

	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Lob']//div[@class='dx-dropdowneditor-icon']")
	WebElement lobDD;

	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Request Type']//div[@class='dx-dropdowneditor-icon']")
	WebElement requestTypeDD;

	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Accounts/Team']//div[@class='dx-dropdowneditor-icon']")
	WebElement accounts_TeamDD;

	@FindBy(xpath = "//dx-select-box[@placeholder='Account Type']//div[@class='dx-dropdowneditor-icon']")
	WebElement accountTypeDD;

	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Account Status']//div[@class='dx-dropdowneditor-icon']")
	WebElement accountsStatusDD;

	@FindBy(xpath = "//div[@class='app-content content']//button[1]")
	WebElement applyAdvancedSearch;

	@FindBy(xpath = "//div[@class='app-content content']//button[2]")
	WebElement clearAdvancedSearch;
	// complexity matrix
	@FindBy(xpath = "//i[@class='la la-connectdevelop']")
	WebElement complexityMatrixBtn;

	@FindBy(xpath = "//dx-number-box[@id='LocCount']//input[@role='spinbutton']")
	WebElement noOfLocationsTextbox;

	@FindBy(xpath = "//dx-number-box[@id='PerilRegion']//input[@role='spinbutton']")
	WebElement perilRegionTextbox;
	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select Cleansing Complexity']//div[@class='dx-dropdowneditor-icon']")
	WebElement cleansingComplexityDD;
	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select Financial Terms']//div[@class='dx-dropdowneditor-icon']")
	WebElement financialTermsDD;

	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select Complexity']//div[@class='dx-dropdowneditor-icon']")
	WebElement complexityDD;

	@FindBy(xpath = "//dx-number-box[@id='TimeInMinId']//input[@role='spinbutton']")
	WebElement timeInMinsTextbox;

	@FindBy(xpath = "//textarea[@name='txtComment']")
	WebElement commentsTextbox;

	@FindBy(xpath = "//i[@class='la la-empire']")
	WebElement riskInspectionBtn;
	@FindBy(xpath = "//i[@class='la la-file-pdf-o']")
	WebElement pdfExtractionBtn;
	@FindBy(xpath = "//i[@class='la la-file-text']")
	WebElement processDocumentationBtn;
	@FindBy(xpath = "//i[@class='la la-pencil-square']")
	WebElement queryResolutionBtn;
	@FindBy(xpath = "//i[@class='la la-map']")
	WebElement geospatialBtn;
	@FindBy(xpath = "//i[@class='la la-wrench']")
	WebElement toolDevelopmentBtn;

	// Delegate Activity WebElements

	@FindBy(xpath = "//i[@class='la la-exchange']")
	WebElement delegateActivityBtn;
	@FindBy(xpath = "//dx-select-box[@displayexpr='TaskName']//div[@class='dx-dropdowneditor-icon']")
	WebElement parentTaskDD;

	@FindBy(xpath = "//input[@name='task']")
	WebElement taskTextbox;

	@FindBy(xpath = "(//div[@class='dx-dropdowneditor-icon'])[3]")
	WebElement analystDD;

	@FindBy(xpath = "//i[@class='la la-bullseye']") // for scrubbing
	WebElement cleansingBtn;

	@FindBy(xpath = "//i[@class='la la-cogs']")
	WebElement cleansingInProgressBtn;
	
	@FindBy(xpath = "//i[@class='la la-hourglass-start']")
	WebElement cleansingQCBtn;
	
	@FindBy(xpath = "//i[@class='la la-hand-stop-o']")
	WebElement onHoldBtn;
	
	//Submit Account to LMS
	@FindBy(xpath = "(//i[@class='la la-codepen'])[2]")
	WebElement reviewAndSubmitBtn;
	
	@FindBy(xpath = "//dx-button[@type='danger']/div/span")
	WebElement submitToLms;


	public boolean verifyWorkTrayPage() {
		WebElement element = driver.findElement(By.xpath("//h3[normalize-space()='Job Display']"));
		wait.waitForElement(element);
		String actualText = element.getText();
		
		String expectedText = "Job Display";
		return elementAct.verifyText(actualText, expectedText);
		
//		wait.waitForTitle(title);
//		return elementAct.verifyWebPageTitle(title, "WorkTray");
	}

	public SOVImportPage startCleansing() {
		wait.waitForElement(cleansingBtn);
		elementAct.clickElementByJS(cleansingBtn, "Cleansing");
		elementAct.clickElementByJS(cleansingInProgressBtn, "Cleansing in Progress");
		return new SOVImportPage();
	}
	
	public void startQCVerification() throws InterruptedException {
		wait.waitForElement(cleansingBtn);
		Thread.sleep(4000);
		elementAct.clickElementByJS(cleansingBtn, "Cleansing");
		elementAct.clickElementByJS(cleansingQCBtn, "QC Verification");
	}

	public CmrNewRequestPage clickOnNewRequest() {
		elementAct.clickElementByJS(newRequest, "New Request Form");
		return new CmrNewRequestPage();
	}
	
	public AssignWorkPage clickOnAssignWork() {
		elementAct.clickElementByJS(assignWork, "Assign Work Icon");
		return new AssignWorkPage();
	}

	public boolean isSuccess() {
		wait.waitForElement(successMessage);
//		return successMessage.isDisplayed();
		boolean flag = successMessage.isDisplayed();
		driver.findElement(By.cssSelector("div#toast-container>div>button>span")).click();
		return flag;
	}

	public int getNumberOfRows() {
		return driver.findElements(By.xpath("//td[@aria-label='Select row']/div")).size();
	}

	// this method is for selecting/deselecting an account
	public void clickOnAccount() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement ele = driver
				.findElement(By.xpath("//td[@aria-label='Select row']//span[@class='dx-checkbox-icon']"));
		wait.waitForElementToBeClickable(ele);
		elementAct.clickElementByJS(ele, "account checkbox");
	}
	
	public void clickOnHoldAccount() {
		elementAct.clickTab(onHoldBtn, "Account on Hold");
	}
	
	public void clickOnReviewAndSubmitBtn() {
		elementAct.clickElementByJS(reviewAndSubmitBtn, "Review And Submit ");
	}

	// for advanced Search
	private void selectAccountType(String accountType) {
		elementAct.clickElement(accountTypeDD, "account Type dropdown");
		String xpath = "//div[@class='dx-item-content dx-list-item-content'][normalize-space()='" + accountType + "']";
		WebElement ele = driver.findElement(By.xpath(xpath));
		wait.waitForElement(ele);
		elementAct.clickElement(ele, "Select Account");
	}
	
	//This method is used for applying column search on current user and processing status
	public void applyColumnSearchOnUserAndStatus(String processingStatus, String currentUser)
	{
		wait.waitForElement(columnSearchBtn);
		elementAct.clickElementByJS(columnSearchBtn, "column search Btn");
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.scrollIntoView(currentUserColumnSearch);
		elementAct.enterData(currentUserColumnSearch, "Current User Column Search", currentUser);
		elementAct.enterData(processingStatusColumnSearch, "Processing Status Column Search", "Cleansing");
		elementAct.clickElementByJS(columnSearchBtn, "column search Btn");
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeColumnSearchOnUserAndStatus() {
		elementAct.clickElementByJS(columnSearchBtn, "column search Btn");
		elementAct.clearElementText(currentUserColumnSearch);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.scrollIntoView(processingStatusColumnSearch);
		processingStatusColumnSearch.clear();
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		elementAct.clearElementText(processingStatusColumnSearch);
		elementAct.clickElementByJS(columnSearchBtn, "column search Btn");
	}
	
	/* this method to apply column search based on account number column */
	public void applyColumnSearchOnAccountName(String accountName) {
		wait.waitForElement(columnSearchBtn);
		elementAct.clickElementByJS(columnSearchBtn, "column search Btn");
		try {
			Thread.sleep(6000);
			elementAct.enterDataInTextbox(accountNameColumnSearch, "Account Name Column Search", accountName);
			elementAct.clickElementByJS(columnSearchBtn, "column search Btn");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void removeColumnSearchOnAccountName() {
		elementAct.clickElementByJS(columnSearchBtn, "column search Btn");
		elementAct.clearElementText(accountNameColumnSearch);
		elementAct.clickElementByJS(columnSearchBtn, "column search Btn");
	}

	public String deleteAccount() {
		elementAct.clickElement(deleteBtn, "delete Button");
		String text = deletePopupText.getText();
		elementAct.clickElementByJS(cancelDeleteBtn, "Cancel Btn");
		System.out.println(text);
		return text;
	}

	public int applyAdvancedSearch(String accountName, String lob, String requestType, String accounts_Team,
			String accountType, String accountStatus) {
		elementAct.clickElement(advancedSearchBtn, "advanced Search Button");
		elementAct.enterData(accountNameTextbox, "accountName", accountName);
		elementAct.selectDDOptions(lobDD, "lob", lob);
		elementAct.selectDropdownManual(requestTypeDD, "request Type", requestType);
		elementAct.selectDropdownManual(accounts_TeamDD, "By Accounts/Team", accounts_Team);
		// elementAct.selectDropdown(accountTypeDD, "Accounts Type", accountType);
		selectAccountType(accountType);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		elementAct.selectDDOptions(accountsStatusDD, "Accounts Status", accountStatus);
		elementAct.clickElement(applyAdvancedSearch, "Apply advanced search");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int numberOfAccounts = getNumberOfRows();
		elementAct.clickElement(clearAdvancedSearch, "Clear Advanced Search");
		elementAct.clickElement(advancedSearchBtn, "advanced Search Button");

		return numberOfAccounts;
	}

	public void enterComplexityData(String noOfLocations, String perilRegion, String cleansingComplexity,
			String financialTerms, String complexity) {
		elementAct.clickElement(complexityMatrixBtn, "Fill Complexity");
		elementAct.enterData(noOfLocationsTextbox, "Locaion Count", noOfLocations);
		elementAct.enterData(perilRegionTextbox, "Peril Region", perilRegion);
		elementAct.selectDDOptions(cleansingComplexityDD, "Cleansing Complexity dropdown", cleansingComplexity);
		elementAct.selectDDOptions(financialTermsDD, "financial terms dropdown", financialTerms);
		elementAct.selectDDOptions(complexityDD, "complexity dropdown", complexity);
		elementAct.clickElementByJS(saveBtn, "save");

	}

	public void enterTaskDetails(String timeInMins, String comments, String taskName) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (taskName.equalsIgnoreCase("risk inspection"))
			elementAct.clickElementByJS(riskInspectionBtn, "Risk Inspection");
		else if (taskName.equalsIgnoreCase("PDF Extraction"))
			elementAct.clickElementByJS(pdfExtractionBtn, "Pdf Extraction");
		else if (taskName.equalsIgnoreCase("Process Documentation"))
			elementAct.clickElementByJS(processDocumentationBtn, "Process Documentation");
		else if (taskName.equalsIgnoreCase("Query Resolution"))
			elementAct.clickElementByJS(queryResolutionBtn, "Query Resolution");
		else if (taskName.equalsIgnoreCase("Geospatial"))
			elementAct.clickElementByJS(geospatialBtn, "Geospatial");
		else if (taskName.equalsIgnoreCase("Tool Development"))
			elementAct.clickElementByJS(toolDevelopmentBtn, "Tool Development");

		elementAct.enterData(timeInMinsTextbox, "Time", timeInMins);
		elementAct.enterData(commentsTextbox, "Comments", comments);
		elementAct.clickElement(saveBtn, "Save");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enterDelegateActivityData(String parentTask, String task, String analyst) {
		elementAct.clickElement(delegateActivityBtn, "Delegate Activity");
		elementAct.selectDDOptions(parentTaskDD, "Parent Task", parentTask);
		elementAct.enterData(taskTextbox, "Task", task);
		elementAct.selectDDOptions(analystDD, "Analyst", analyst);
		elementAct.clickElement(saveBtn, "Save");
	}

	public boolean validateViewRecordTimeDetails() throws InterruptedException {
		String expectedText = "Record Time";
		elementAct.clickElementByJS(recordTimeDetailsBtn, "Record Time Details");
		Thread.sleep(1500);
		WebElement recoedPage = driver.findElement(By.xpath("//h3[normalize-space()='Record Time']"));
		wait.waitForElement(recoedPage);
		System.out.println(driver.getTitle());
		boolean flag = elementAct.verifyText(recoedPage.getText(), expectedText);
		elementAct.clickElementByJS(backBtn, "Back Btn");
//		Thread.sleep(2000);
		wait.waitForTitle(title);
		return flag;
	}

	public boolean validateViewProcessDetails() throws InterruptedException {
		wait.waitForElement(processDetailsBtn);
		elementAct.clickElementByJS(processDetailsBtn, "Record Time Details");
		Thread.sleep(1500);
		boolean flag = elementAct.verifyWebPageTitle(processDetailsPageTitle, "Process Details");
		elementAct.clickElementByJS(backBtn, "Back Btn");
//		Thread.sleep(1000);
		wait.waitForTitle(title);
		return flag;
	}

	public boolean validateAdditionalEvaluation() throws InterruptedException {
		wait.waitForElement(additionalEvalutionBtn);
		elementAct.clickElementByJS(additionalEvalutionBtn, "Add Additional Evaluation");
		Thread.sleep(1000);
		boolean flag = elementAct.verifyWebPageTitle(additionalEvaluationPageTitle, "Add Additional Evaluation");
		driver.navigate().back();
//		Thread.sleep(3000);
		wait.waitForTitle(title);
		return flag;
	}

	public void modifyProposedDate() {
		wait.waitForElement(updateProposedDateBtn);
		elementAct.clickElementByJS(updateProposedDateBtn, "Update Proposed Date");
		elementAct.selectDate(proposedDate, "Update Proposed Date");
		elementAct.clickElementByJS(updateBtn, "Update Date");
	}

	public boolean validateQueryLog(String query) throws InterruptedException {
		String expectedText = "New Query";
		elementAct.clickElementByJS(queryLogBtn, "Query Log");
		elementAct.selectDDOptions(addNewQueryBtn, "Add New Query Dropdown", query);
		Thread.sleep(1500);
		WebElement ele = driver.findElement(By.xpath("//h3[normalize-space()='New Query']"));
		wait.waitForElement(ele);
		String actualText = ele.getText();
		System.out.println(actualText);
		boolean flag = elementAct.verifyText(actualText, expectedText);
		elementAct.clickElementByJS(queryBackBtn, "Back Btn");
		return flag;
	}
	
	public boolean verifyMRFReviewForm() {
		WebElement element = driver.findElement(By.xpath("//h3[normalize-space()='MRF Review Form']"));
		wait.waitForElement(element);
		String actualText = element.getText();

		String expectedText = "MRF Review Form";
		return elementAct.verifyText(actualText, expectedText);
	}
	
	public void submitAccountToLms() {
		elementAct.scrollIntoView(submitToLms);
		elementAct.clickElementByJS(submitToLms, "Submit To LMS");
	}
}
