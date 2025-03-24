package com.cat.pages;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;
import com.cat.utilities.handleKeyboardEvent;

public class PerilLevelFinancialDetailsPage extends BaseClass {

	String pageTitle = "Xceedance | Add Account";

	public PerilLevelFinancialDetailsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Peril Level Financial Details']")
	WebElement perilLevelFinancialDetailsTab;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-add']")
	WebElement addPerilFinancialBtn;

	@FindBy(css = "td[class='dx-command-edit dx-command-edit-with-icons'] a[title='Delete']")
	WebElement deleteButton;

	// Delelte Popup Button
	@FindBy(xpath = "//*[text()='Yes']")
	WebElement deleteYesBtn;

	@FindBy(xpath = "//*[text()='No']")
	WebElement deleteNoBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-remove']")
	WebElement undoBtn;

	@FindBy(xpath = "//dx-button[@title='Previous Tab']")
	WebElement previousTab;

	@FindBy(xpath = "//dx-button[@title='Next Tab']")
	WebElement nextTab;

	// @FindBy(xpath = "(//div[@class='dx-dropdowneditor-icon'])[15]")
	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[2]")
	WebElement perilColumnSearchDD;

//	@FindBy(xpath = "//div[@title='Select Peril']//input[@role='combobox']")
	@FindBy(xpath = "//div[@title='Select Peril']//div[@class='dx-dropdowneditor-icon']")
	WebElement perilDD;

	@FindBy(xpath = "//div[@title='Select Location Group']//input[@role='combobox']")
	WebElement locGrpDD;

	@FindBy(xpath = "//div[@title='Column Search']//i[@class='dx-icon dx-icon-search']")
	WebElement colSearchButton;

	@FindBy(xpath = "//div[@class='dx-texteditor-input-container dx-tag-container dx-native-click']")
	WebElement layerDD;

	@FindBy(xpath = "//*[text()='Limit Code']//following::td[17] //div[@class='dx-dropdowneditor-icon']")
	WebElement limitCodeDD;

	@FindBy(xpath = "//*[text()='Limit Type']//following::td[17] //div[@class='dx-dropdowneditor-icon']")
	WebElement limitTypeDD;

	@FindBy(xpath = "//*[text()='Limit Basis']//following::td[17] //div[@class='dx-dropdowneditor-icon']")
	WebElement limitBasisDD;

	@FindBy(xpath = "//*[text()='Sublimit']//following::td[17]")
	WebElement sublimitTextbox;

	@FindBy(xpath = "//*[text()='Sublimit Part']//following::td[17]")
	WebElement sublimitPartTextbox;

	@FindBy(xpath = "//*[text()='Sublimit Attachment']//following::td[17]")
	WebElement sublimitAttachmentTextbox;

	@FindBy(xpath = "//*[text()='Deductible']//following::td[17]")
	WebElement deductibleTextbox;

	@FindBy(xpath = "//*[text()='Deductible Code']//following::td[17] //div[@class='dx-dropdowneditor-icon']")
	WebElement deductibleCodeDD;

	@FindBy(xpath = "//*[text()='Deductible Type']//following::td[17] //div[@class='dx-dropdowneditor-icon']")
	// @FindBy(xpath = "(//input[@role='combobox'])[20]]")
	WebElement deductibleTypeDD;

	@FindBy(xpath = "//*[text()='Deductible Basis']//following::td[17] //div[@class='dx-dropdowneditor-icon']")
	WebElement dedBasisDD;

	@FindBy(xpath = "//*[text()='Min Deductible']//following::td[17]")
	WebElement minDeductibleTextbox;

	@FindBy(xpath = "//*[text()='Max Deductible']//following::td[17]")
	WebElement maxDeductibleTextbox;

	@FindBy(how = How.XPATH, using = "//td[@class='dx-command-edit dx-command-edit-with-icons']//a[@title='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//span[normalize-space()='Add Location Group']")
	private WebElement addLocationGrouppBtn;

	@FindBy(xpath = "//span[@class='switch switch-small checked']")
	private WebElement MDMLocGrpRadioButton;

	@FindBy(xpath = "//dx-text-box[@placeholder='Enter Location Group Name']//input[@role='textbox']")
	private WebElement locationGroupName; // textBox

	@FindBy(xpath = "//dx-select-box[@placeholder='Field Name']//input[@role='combobox']")
	private WebElement locGrpFieldNameDD; // Location Group Field Name

	@FindBy(xpath = "//dx-select-box[@placeholder='Operator']//input[@role='combobox']")
	private WebElement locGrpOperatorDD;

	@FindBy(xpath = "//table[@id='gridContainerrule']/tbody[1]/tr[1]/td[3]/dx-text-box[1]/div[1]/div[1]/input[1]")
	private WebElement locGrpValue;

	@FindBy(xpath = "//dx-select-box[@placeholder='Value']//div[@class='dx-dropdowneditor-icon']")
	private WebElement locGrpValueDD; // if value is a dropdown

	@FindBy(xpath = "//dx-select-box[@placeholder='Conditional Operator']//input[@role='combobox']")
	private WebElement locGrpConditionOperatorDD;

	@FindBy(xpath = "//dx-button[@aria-label='Save']//div[@class='dx-button-content']")
	private WebElement locGrpSaveBtn;

	@FindBy(xpath = "//div[@title='Filter Peril Financial']//div[@class='dx-button-content']")
	private WebElement filterIcon;

	@FindBy(xpath = "//span[contains(text(),'Reset')]")
	private List<WebElement> resetFilterButton;

	@FindBy(xpath = "//dx-select-box[@placeholder='Select Entity']//input[@role='combobox']")
	private WebElement entityFilterDD;

	@FindBy(xpath = "(//input[@role='combobox'])[17]")
	WebElement entityValueDD;

	@FindBy(xpath = "(//input[@placeholder='Enter value'])[1]")
	WebElement entityValueTextbox;

	@FindBy(xpath = "//select[@class='form-control ng-untouched ng-pristine ng-valid']")
	private WebElement operatorFilterDD;

	@FindBy(xpath = "//div[contains(@data-dx_placeholder,'Select Entity')]")
	private List<WebElement> EnityFilterUpdateDD;

	@FindBy(xpath = "//span[normalize-space()='Filter']")
	private WebElement filterButton;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-edit']")
	private WebElement updateIcon;

	@FindBy(css = "td[class='dx-command-edit dx-command-edit-with-icons'] a[title='Edit']")
	private WebElement editButton;

	@FindBy(xpath = "//dx-select-box[@placeholder='Select Entity']//input[@role='combobox']")
	private WebElement entityUpdateDD;

	@FindBy(xpath = "//td[@class='dx-command-edit dx-command-edit-with-icons']//a[@title='Cancel']")
	WebElement cancelButton;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-close']")
	WebElement crossButton;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-codeblock']")
	WebElement viewGroupCriteriaBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-info']")
	WebElement checkGroupLocationsBtn;

	@FindBy(xpath = "//div[contains(@class,'dx-popup-title')]/div/div[1]")
	WebElement popUpTitle;

	public boolean verifyPerilLevelFinancialDetailsTab() {
		wait.waitForElementsAttribute(perilLevelFinancialDetailsTab, "class", "nav-link p-0 active");
		String[] str = perilLevelFinancialDetailsTab.getAttribute("class").split(" ");
		String text = str[2];
		System.out.println(text);
		boolean flag = elementAct.verifyText("active", text);
		return flag;
	}

	// returns number of only Saved layers on Peril Level Financial Details
	public int getNumberOfSavedPerilLevelFinancialLayers() {
		int size = driver.findElements(By.xpath("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]"))
				.size(); // td[2] not working
		return size;
	}

	// returns total no. of layers visible on grid including the one being edited
	public int getNumberPerilLevelFinancialLayers() {
		int size = driver
				.findElements(
						By.xpath("//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[3]"))
				.size(); // td[2] not working
		return size;
	}

	public void clickOnCancelBtn() {
		elementAct.clickElementByJS(cancelButton, "Cancel Button");
	}

	public void clickOnAddPerilFinancialDetailsBtn() {
		elementAct.clickElement(addPerilFinancialBtn, "Add New Peril Financial Button");
	}

	public void clickOnViewGroupCriteriaBtn() {
		elementAct.clickElementByJS(viewGroupCriteriaBtn, "View Group Criteria Button");
	}

	public void clickOnCheckGroupLocationsBtn() {
		elementAct.clickElementByJS(checkGroupLocationsBtn, "Check Group Locations");
	}
	
	public boolean verifyPopupTitle(String expectedTitle) {
		try {
			String actualPopupTitle = "";
			Thread.sleep(1000);			
			wait.waitForElementToBeClickable(popUpTitle);
			if (popUpTitle.isDisplayed())
				actualPopupTitle = popUpTitle.getText();
			clickCloseBtn();
			return elementAct.verifyText(actualPopupTitle, expectedTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			clickCloseBtn();
			return false;
		}		
	}
	public void selectLocationGroup(String locGroupName) {
		elementAct.selectDropdown(locGrpDD, "Location Group", locGroupName);
	}

	public void selectPerilAndLocGroup(String perilName, String locGroupName) {
		elementAct.selectDropdown(perilDD, "Peril", perilName);
		elementAct.selectDropdown(locGrpDD, "Location Group", locGroupName);
	}

	public void enterSublimit(String sublimit) {
		elementAct.scrollIntoView(sublimitTextbox);
		elementAct.enterDataInTextbox(sublimitTextbox, "Sub-limit", sublimit);
		try {
			handleKeyboardEvent.pressTabKey();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enterDeductible(String deductible) {
		elementAct.scrollIntoView(deductibleTextbox);
		elementAct.enterDataInTextbox(deductibleTextbox, "Deductible", deductible);
		try {
			handleKeyboardEvent.pressTabKey();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enterMinDeductible(String minDeductible) {
		elementAct.scrollIntoView(minDeductibleTextbox);
		elementAct.enterDataInTextbox(minDeductibleTextbox, "Min Deductible", minDeductible);
		try {
			handleKeyboardEvent.pressTabKey();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// to handle Dropdowns in Peril Level Financial Details Layers

	public void applyColumnSearch(String perilName) {
		elementAct.clickElementByJS(colSearchButton, "column Search Button");
		elementAct.scrollIntoView(perilColumnSearchDD);
		wait.waitForElementToBeClickable(perilColumnSearchDD);
		elementAct.selectDropdown(perilColumnSearchDD, "peril column search", perilName);
		elementAct.clickElementByJS(colSearchButton, "column Search Button");
	}

	public void removeColumnSearch() {
		elementAct.clickElementByJS(colSearchButton, "column Search Button");
		elementAct.scrollIntoView(perilColumnSearchDD);
		elementAct.selectDropdown(perilColumnSearchDD, "peril column search", "(All)");
//		elementAct.selectDropdownManual(perilColumnSearchDD, "peril column search", "(All)");
		elementAct.clickElementByJS(colSearchButton, "column Search Button");
	}

	/*
	 * This method adds a new layer to peril level financial details page
	 */
	public void addPerilLevelFinancialData(String perilName, String logGrpName, String layerName, String limitCode,
			String limitBasis, String limitType, String sublimit, String sublimitPart, String sublimitAttachment,
			String deductible, String deductibleCode, String deductibleType, String deductibleBasis,
			String minDeductible, String maxDeductible) {
		selectPerilAndLocGroup(perilName, logGrpName);
		clickOnAddPerilFinancialDetailsBtn();
		elementAct.selectDropdown(layerDD, "Layer", layerName);
		elementAct.moveToElement(limitCodeDD);
		elementAct.selectDropdown(limitCodeDD, "Limit Code", limitCode);
		elementAct.scrollIntoView(limitBasisDD);
		elementAct.selectDropdown(limitBasisDD, "Limit Basis", limitBasis);
		elementAct.scrollIntoView(sublimitAttachmentTextbox);
		elementAct.selectDropdown(limitTypeDD, "Limit Type", limitType);
		elementAct.enterDataInTextbox(sublimitTextbox, "Sub-limit", sublimit);
		elementAct.enterDataInTextbox(sublimitPartTextbox, "Sub-limit Part", sublimitPart);
		elementAct.enterDataInTextbox(sublimitAttachmentTextbox, "Sub-limit Attachement", sublimitAttachment);
		elementAct.scrollIntoView(maxDeductibleTextbox);

		elementAct.clickElementByJS(deductibleCodeDD, "Deductible code"); // issue with dedctible code DD
		elementAct.selectDropdown(deductibleCodeDD, "Deductible Code", deductibleCode);

		elementAct.selectDropdown(deductibleTypeDD, "Ded Type", deductibleType);
		elementAct.selectDropdown(dedBasisDD, "Ded Basis", deductibleBasis);
		elementAct.enterDataInTextbox(deductibleTextbox, "Deductible", deductible);
		elementAct.enterDataInTextbox(minDeductibleTextbox, "Min Deductible", minDeductible);
		elementAct.enterDataInTextbox(maxDeductibleTextbox, "Max Deductible", maxDeductible);
		elementAct.clickElement(saveButton, "Save Button");
	}

	/*
	 * This method edits the data of a layer to peril level financial details page
	 */

	public void editPerilLevelFinancialData(String layerName, String limitCode, String limitBasis, String limitType,
			String sublimit, String sublimitPart, String sublimitAttachment, String deductible, String deductibleCode,
			String deductibleType, String deductibleBasis, String minDeductible, String maxDeductible) {

		elementAct.clickElementByJS(editButton, "edit layer");
		// elementAct.selectDropdown(layerDD, "Layer", layerName); //issue in layer dropdown	
		
		elementAct.selectDropdown(limitCodeDD, "Limit Code", limitCode);
		elementAct.scrollIntoView(limitBasisDD);
		elementAct.selectDropdown(limitBasisDD, "Limit Basis", limitBasis);
		elementAct.scrollIntoView(sublimitAttachmentTextbox);
		elementAct.selectDropdown(limitTypeDD, "Limit Type", limitType);
		elementAct.enterDataInTextbox(sublimitTextbox, "Sub-limit", sublimit);

		elementAct.enterDataInTextbox(sublimitPartTextbox, "Sub-limit Part", sublimitPart);

		elementAct.enterDataInTextbox(sublimitAttachmentTextbox, "Sub-limit Attachement", sublimitAttachment);
		elementAct.scrollIntoView(maxDeductibleTextbox);

		elementAct.clickElement(deductibleCodeDD, "Deductible code"); // issue with dedctible code DD
		elementAct.selectDropdown(deductibleCodeDD, "Ded Code", deductibleCode);

		elementAct.selectDropdown(deductibleTypeDD, "Ded Type", deductibleType);
		elementAct.selectDropdown(dedBasisDD, "Ded Basis", deductibleBasis);

		elementAct.enterDataInTextbox(deductibleTextbox, "Deductible", deductible);
		elementAct.enterDataInTextbox(minDeductibleTextbox, "Min Deductible", minDeductible); // issue in minimum deductible																								 

		elementAct.enterDataInTextbox(maxDeductibleTextbox, "Max Deductible", maxDeductible);
		elementAct.clickElement(saveButton, "Save Button");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addLocationGroup(String locGroupName, String fieldName, String operator, String value,
			String conditionOperator) throws InterruptedException, AWTException {
		elementAct.clickElementByJS(addLocationGrouppBtn, "Add Location Group Button");
		elementAct.disableRadioButton(MDMLocGrpRadioButton, "MDM LOC Group Radio Button");
		elementAct.enterDataByJS(locationGroupName, "Location Group", locGroupName);
		elementAct.selectDDOptions(locGrpFieldNameDD, "Field Name", fieldName);
		elementAct.selectDDOptions(locGrpOperatorDD, "Operator", operator);

		elementAct.enterDataInTextbox(locGrpValue, "Value", value);
		elementAct.clearElementText(locGrpConditionOperatorDD);
		elementAct.selectDDOptions(locGrpConditionOperatorDD, "Conditional Operator", conditionOperator);
			elementAct.clickElementByJS(locGrpSaveBtn, "Save Location Group Button");
	}

	public void clickOnDeleteBtn() {
		elementAct.clickElementByJS(deleteButton, "delete Btn");
	}

	public boolean clickOnDeleteYesBtn() {
		wait.waitForElementToBeClickable(deleteYesBtn);
		if(deleteYesBtn.isDisplayed()) {
		elementAct.clickElementByJS(deleteYesBtn, "Delete popup Yes");
		return true;
		}
		return false;
	}

	public boolean clickOnDeleteNoBtn() {
		wait.waitForElementToBeClickable(deleteNoBtn);
		if(deleteNoBtn.isDisplayed()) {
			elementAct.clickElementByJS(deleteNoBtn, "Delete popup No");
			return true;
		}
		return false;
	}

	public boolean applyFilter(String entity, String operator, String entityValue) throws InterruptedException {
	
		wait.waitForElementToBeClickable(filterIcon);
		elementAct.moveToElement(filterIcon);
		try {
		elementAct.clickElement(filterIcon, "Filter Button");
		elementAct.selectEntityValue(entityFilterDD, entity);
		elementAct.selectOperatorValue(operatorFilterDD, operator);
		elementAct.enterData(entityValueTextbox, "Value", entityValue);
//		ruleEditorArea.click();
		elementAct.clickElementByJS(filterIcon, "Apply Filter");
		}
		catch (TimeoutException e) {
			clickCloseBtn();
			return false;
		}
		catch (Exception e) {
			clickCloseBtn();
			return false;
		}
		return true;
	}
	
	public void clickUndoBtn() {
		elementAct.clickElement(undoBtn, "Undo");
	}

	public void clickCloseBtn() {
		elementAct.clickElementByJS(crossButton, "Cross button");
	}

	public void bulkUpdatePerilFin() throws InterruptedException {
		elementAct.clickElement(updateIcon, "Update Icon");
		elementAct.clickElement(resetFilterButton.get(0), "Reset Button");
		elementAct.selectDDOptions(EnityFilterUpdateDD.get(0), "Entity", "LimitType");
		elementAct.selectDDOptions(EnityFilterUpdateDD.get(1), "Entity", "Amount");
		elementAct.clickElement(editButton, "Update Button");
	}

	public void clickPreviousTab() {

		elementAct.clickElement(previousTab, "Previous Tab Buton of Policy Layer");

	}

	public void clickNextTab() {
		elementAct.clickElement(nextTab, "Next Tab Buton of Policy Layer");

	}
}
