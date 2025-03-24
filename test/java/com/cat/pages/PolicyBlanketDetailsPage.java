package com.cat.pages;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cat.testCases.BaseClass;
import com.cat.utilities.handleKeyboardEvent;

public class PolicyBlanketDetailsPage extends BaseClass {

	String pageTitle = "Xceedance | Add Account";

	public PolicyBlanketDetailsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[text()=' Policy Blanket Details']")
	WebElement PolicyBlanketTab;

	@FindBy(css = "[title='Add Blanket']")
	WebElement addNewBlanketButton;

	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[2]")
	WebElement perilNameColumnSearchDD;

	@FindBy(xpath = "//div[@title='Select Peril']//div[@class='dx-dropdowneditor-icon']")
	WebElement perilDD;

	@FindBy(css = "[title='Column Search']")
	WebElement columnSearchButton;

	@FindBy(xpath = "//*[text()='Deductible Code']//following::td[13] //div[@class='dx-dropdowneditor-icon']")
	WebElement deductibleCodeDD;

	@FindBy(xpath = "//*[text()='Deductible Type']//following::td[13] //div[@class='dx-dropdowneditor-icon']")
	WebElement deductibleTypeDD;

	@FindBy(xpath = "//*[text()='Deductible Type']//following::td[13] //input[@role='combobox']")
	WebElement deductibleTypeTetxbox;

	@FindBy(xpath = "//*[text()='Deductible Basis']//following::td[13]  //div[@class='dx-dropdowneditor-icon']")
	WebElement deductibleBasisDD;

	@FindBy(xpath = "//*[text()='Blanket Deductible']//following::td[13]")
	WebElement blanketDeductibleTextbox;

	@FindBy(xpath = "//*[text()='Maximum Deductible']//following::td[13]")
	WebElement maximumDeductibleTextbox;

	@FindBy(xpath = "//*[text()='Minimum Deductible']//following::td[13]")
	WebElement minimumDeductibleTextbox;

	@FindBy(xpath = "//*[text()='Limit Code']//following::td[13] //div[@class='dx-dropdowneditor-icon']")
	WebElement limitCodeDD;

	@FindBy(xpath = "//*[text()='Limit Type']//following::td[13] //div[@class='dx-dropdowneditor-icon']")
	WebElement limitTypeDD;

	@FindBy(xpath = "//*[text()='Limit Basis']//following::td[13] //div[@class='dx-dropdowneditor-icon']")
	WebElement limitBasisDD;

	@FindBy(xpath = "//*[text()='Limit Amount']//following::td[13]")
	WebElement limitAmountTextbox;

	@FindBy(xpath = "//*[text()='Peril Name']//following::td[13]")
	WebElement PerilNameSearchDD;

	@FindBy(xpath = "//td[@class='dx-command-edit dx-command-edit-with-icons']//a[@title='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//td[@class='dx-command-edit dx-command-edit-with-icons']//a[@title='Cancel']")
	WebElement cancelButton;

	@FindBy(css = "td[class='dx-command-edit dx-command-edit-with-icons'] a[title='Edit']")
	WebElement editButton;

	@FindBy(css = "td[class='dx-command-edit dx-command-edit-with-icons'] a[title='Delete']")
	WebElement deleteButton;

	// Delelte Popup Button
	@FindBy(xpath = "//*[text()='Yes']")
	WebElement deleteYesBtn;

	@FindBy(xpath = "//*[text()='No']")
	WebElement deleteNoBtn;

	@FindBy(xpath = "//dx-button[@title='Previous Tab']")
	WebElement previousTab;

	@FindBy(xpath = "//dx-button[@title='Next Tab']")
	WebElement nextTab;

	/*
	 * This method is used for verifying Policy Details Page
	 */
	public boolean verifyPolicyBlanketDetailsTab() {

		wait.waitForElementsAttribute(PolicyBlanketTab, "class", "nav-link p-0 active");
		String[] str = PolicyBlanketTab.getAttribute("class").split(" ");
		String text = str[2];
		System.out.println(text);
		boolean flag = elementAct.verifyText("active", text);
		return flag;
	}

	public void clickPolicyBlanketTab() {
		elementAct.clickTab(PolicyBlanketTab, "Policy Blanket Tab");
	}

	public void clickOnCancelBtn() {
		elementAct.clickElementByJS(cancelButton, "Cancel Button");
	}

	public void clickOnSaveBtn() {
		elementAct.clickElementByJS(saveButton, "Save Button");
	}

	public void clickOnAddBlanketBtn() {
		elementAct.clickElement(addNewBlanketButton, "Add New Blanket Button");
	}

	public void selectPerilForBlanket(String perilName) {
		elementAct.selectDropdown(perilDD, "Peril", perilName);
	}

	public void enterBlanketDeductible(String blanketDeductible) {
		elementAct.scrollIntoView(blanketDeductibleTextbox);
		elementAct.enterDataInTextbox(blanketDeductibleTextbox, "Blanket Deductible", blanketDeductible);
		try {
			handleKeyboardEvent.pressTabKey();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enterMaximumDeductible(String maximumDeductible) {
		elementAct.scrollIntoView(maximumDeductibleTextbox);
		elementAct.enterDataInTextbox(maximumDeductibleTextbox, "Max Deductible", maximumDeductible);
		try {
			handleKeyboardEvent.pressTabKey();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// apply column search on peril name column
	public void applyColumnSearch(String peril) {
		elementAct.clickElement(columnSearchButton, "Column Search Button");
		elementAct.scrollIntoView(perilNameColumnSearchDD);
		elementAct.selectDropdown(perilNameColumnSearchDD, "peril column Search Btn", peril);
		elementAct.clickElement(columnSearchButton, "Column Search Button");
	}

	public void removeColumnSearch() {
		elementAct.clickElement(columnSearchButton, "Column Search Button");
		elementAct.scrollIntoView(perilNameColumnSearchDD);
		elementAct.selectDropdown(perilNameColumnSearchDD, "peril column Search Btn", "(All)");
		elementAct.clickElement(columnSearchButton, "Column Search Button");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// This method for handling some dropdown on policy blanket details page while
	// adding or modifying a layer
	public void selectPolicyBlanketTypeDD(WebElement ddElement, String ddName, String ddOption) {
		boolean flag = false;
		elementAct.clickElement(ddElement, ddName + " dropdown");
		List<WebElement> ddList = driver.findElements(By.xpath(
				"//div[contains(@class,'dx-dropdownlist-popup-wrapper dx-selectbox-popup-wrapper')]//div[contains(@class,'dx-popup-draggable dx-resizable')] //div[contains(@class,'dx-item dx-list-item')]"));
		for (int i = 0; i < ddList.size(); i++) {
			String text = ddList.get(i).getText();
			if (text.equalsIgnoreCase(ddOption))
				elementAct.clickElement(ddList.get(i), text);
			flag = true;
		}
		if (flag)
			logger.info(ddOption + " of " + ddName + "is selected succesfully");
		else
			logger.fail(ddOption + " of " + ddName + "is not selected");
	}

	/*
	 * This method is used for adding a new layer to Policy Blanket Details Data
	 */
	public void addPolicyBlanketData(String peril, String deductibleCode, String deductibleType, String deductibleBasis,
			String blanketDeductible, String maximumDeductible, String minimumDeductible, String limitCode,
			String limitType, String limitBasis, String limitAmount) {

		elementAct.selectDropdown(perilDD, "Peril", peril);
		clickOnAddBlanketBtn();
		elementAct.selectDropdown(deductibleCodeDD, "Deductible Code", deductibleCode);
		selectPolicyBlanketTypeDD(deductibleTypeDD, "Deductible Type", deductibleType);
		elementAct.scrollIntoView(maximumDeductibleTextbox);
		elementAct.selectDropdown(deductibleBasisDD, "Deductible Basis", deductibleBasis);

		elementAct.enterDataInTextbox(blanketDeductibleTextbox, "Blanket Deductible", blanketDeductible);
		elementAct.enterDataInTextbox(maximumDeductibleTextbox, "Max Deductible", maximumDeductible);
		elementAct.scrollIntoView(limitAmountTextbox);

		elementAct.enterDataInTextbox(minimumDeductibleTextbox, "Min Deductible", minimumDeductible);
		wait.waitForElementToBeClickable(limitCodeDD);
//		elementAct.clickElement(limitCodeDD, "limit Code"); // issue with limit Code
//		elementAct.selectDropdown(limitCodeDD, "Limit Code", limitCode);

//		selectPolicyBlanketTypeDD(limitTypeDD, "Limit Type", limitType);
		elementAct.enterDataInTextbox(limitAmountTextbox, "Limit Amount", limitAmount);
		elementAct.moveToElement(saveButton);
		elementAct.clickElementByJS(saveButton, "Save Button");
		logger.pass("A new policy blanket details for Peril: '" + peril + "' are added successfully.");
	}

	/*
	 * This method is used for modifying Policy Blanket Details Layer Data
	 */
	public void modifyPolicyBlanketData(String peril, String deductibleCode, String deductibleType,
			String deductibleBasis, String blanketDeductible, String maximumDeductible, String minimumDeductible,
			String limitCode, String limitType, String limitBasis, String limitAmount) {

		elementAct.clickElement(editButton, "Edit layer");
		elementAct.selectDropdown(deductibleCodeDD, "Deductible Code", deductibleCode);
		selectPolicyBlanketTypeDD(deductibleTypeDD, "Deductible Type", deductibleType);
		elementAct.selectDropdown(deductibleBasisDD, "Deductible Basis", deductibleBasis);

		elementAct.enterDataInTextbox(blanketDeductibleTextbox, "Blanket Deductible", blanketDeductible);
		elementAct.enterDataInTextbox(maximumDeductibleTextbox, "Max Deductible", maximumDeductible);
		elementAct.scrollIntoView(limitAmountTextbox);
		elementAct.enterDataInTextbox(minimumDeductibleTextbox, "Min Deductible", minimumDeductible);

		wait.waitForElementToBeClickable(limitCodeDD);
		elementAct.clickElement(limitCodeDD, "limit Code"); // issue with limit Code
		elementAct.selectDropdown(limitCodeDD, "Limit Code", limitCode);

		selectPolicyBlanketTypeDD(limitTypeDD, "Limit Type", limitType);

		elementAct.enterDataInTextbox(limitAmountTextbox, "Limit Amount", limitAmount);
		elementAct.clickElement(saveButton, "Save Button");
	}

	//returns total no. of layers visible on grid including the one being edited
	public int getNumberOfPolicyBlanketLayers() {
		int size = driver
				.findElements(
						By.xpath("//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[3]"))
				.size(); // td[2] not working
		return size;
	}
	
	
	//returns number of only Saved layers on Policy Blanket Details
	public int getNumberOfSavedPolicyBlanketLayers() {
		int size = driver.findElements(By.xpath("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]"))
				.size();
		return size;
	}

	public void clickOnDeleteBtn() {
		elementAct.clickElementByJS(deleteButton, "delete Btn");
	}

	public void clickOnDeleteYesBtn() {
		elementAct.scrollIntoView(deleteYesBtn);
		elementAct.clickElementByJS(deleteYesBtn, "Delete popup Yes");
	}

	public void clickOnDeleteNoBtn() {
		elementAct.clickElementByJS(deleteNoBtn, "Delete popup No");
	}

	public void clickPreviousTab() {
		elementAct.clickElement(previousTab, "Previous Tab Buton of Policy Layer");
	}

	public void clickNextTab() {
		elementAct.clickElement(nextTab, "Next Tab Buton of Policy Layer");

	}
}
