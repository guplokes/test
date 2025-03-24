package com.cat.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cat.testCases.BaseClass;

public class MergeSOVPage extends BaseClass {

	public MergeSOVPage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-clearformat']")
	WebElement deleteHiddenRowsAndColumnBtn;

	@FindBy(xpath = "(//button[@class='btn btn-info'])[1]")
	WebElement deleteHiddenRowsAndColumnPopupYesBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-rename']")
	WebElement renameColumnBtn;

	@FindBy(xpath = "//dx-select-box[@placeholder='Select column to rename']//div[@class='dx-dropdowneditor-icon']")
	WebElement columnToRenameDD;

	@FindBy(xpath = "//dx-text-box[@placeholder='Enter new column name here.']//input[@role='textbox']")
	WebElement newColumnNameTextbox;

	@FindBy(xpath = "//span[text()='Rename']")
	WebElement renameBtn;

	@FindBy(xpath = "//div[@class='dx-column-indicators']/following-sibling::div")
	List<WebElement> columnNameList;

	@FindBy(xpath = "//td[@aria-label='Select row'] //div[@class='dx-checkbox-container']/span")
	List<WebElement> selectLocationList;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-trash']")
	WebElement deleteLocationBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-fieldchooser']")
	WebElement splitColumnBtn;

	@FindBy(xpath = "//dx-select-box[@valueexpr='index']//div[@class='dx-dropdowneditor-icon']")
	WebElement selectColumnDD;
	@FindBy(xpath = "//dx-text-box[@placeholder='Enter separator here.']//input[@role='textbox']")
	WebElement seperatorTextbox;

	@FindBy(xpath = "//i[@class='la la-arrow-circle-up']")
	WebElement splitBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-clear']")
	WebElement deleteColumnBtn;

	@FindBy(xpath = "//span[normalize-space()='Delete']")
	WebElement applyDeleteColumn;

	@FindBy(xpath = "//div[@aria-label='Cancel']//div[@class='dx-button-content']")
	WebElement cancelDeleteBtn;

	@FindBy(xpath = "//div[@aria-label='Cancel']//div[@class='dx-button-content']")
	WebElement cancelBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-columnchooser']")
	WebElement columnChooserBtn;

	@FindBy(css = "div[aria-label='Apply'] span[class='dx-button-text']")
	WebElement applyColumnChooserBtn;

	@FindBy(xpath = "//input[@aria-label='Search']")
	WebElement searchBox;

	@FindBy(xpath = "//div[@title='Search']//i[@class='dx-icon dx-icon-search']")
	WebElement searchBtn;

	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[4]")
	WebElement locIdSearchbox;

	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[8]")
	List<WebElement> cityTextboxList;

	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[10]")
	List<WebElement> zipTextboxList;

	@FindBy(xpath = "//input[@role='textbox']")
	WebElement textBox;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-save']")
	WebElement saveButton;

	@FindBy(xpath = "//div[@class='tab-content']//li[1]/span")
	WebElement locationRowCount;
	
	@FindBy(xpath = "//div[@class='tab-content']//li[2]/span")
	WebElement columnCount;

	@FindBy(xpath = "//i[@class='ft-arrow-right']")
	WebElement nextBtn;

	@FindBy(xpath = "//i[@class='ft-arrow-left']")
	WebElement previousBtn;

	public boolean verifyMergeSOVPage() {
		WebElement element = driver.findElement(By.xpath("//h3[normalize-space()='Merge SOV Data']"));
		wait.waitForElement(element);
		String actualText = element.getText();

		String expectedText = "Merge SOV Data";
		return elementAct.verifyText(actualText, expectedText);
	}
	
	//Handling redirection to worktray
	public boolean verifyMergeSovPageWithWorktryRedirection(String accountName) {
		boolean result;
		try {
		result = verifyMergeSOVPage();
		}
		//Handling Worktray redirection
		catch(Exception NoSuchElementException) {
			WorkTrayPage workTrayPage = new WorkTrayPage();
			Assert.assertTrue(workTrayPage.verifyWorkTrayPage());
			workTrayPage.applyColumnSearchOnAccountName(accountName);
			workTrayPage.clickOnAccount();
			workTrayPage.startCleansing();
//			result = utils.isSuccess();
//			Assert.assertTrue(result);
			result = verifyMergeSOVPage();			
		}
		return result;
	}

	// returns number of Location row at that instant on the grid
	public int getNumberOfLocationInGrid() {
		return selectLocationList.size();
	}

	// returns number of Location row imported on mergeSovPage
	public int getNumberOfImportedLocations() {
		String numberOfRow = locationRowCount.getText();
		return Integer.parseInt(numberOfRow);

	}

	public int getNumberOfImportedColumn() {
		String numberOfColumn = columnCount.getText();
		return Integer.parseInt(numberOfColumn);
	}

	public void clickOnDeleteBtn() {
		elementAct.clickElementByJS(deleteLocationBtn, "Delete selected location");
	}

	public void clickOnPreviousBtn() {
		elementAct.clickElementByJS(previousBtn, "Previous Button");
	}

	public MappingSummaryPage clickOnNextBtn() {
		elementAct.clickElementByJS(nextBtn, "Next Button");
		return new MappingSummaryPage();
	}

	public void clickOnSaveBtn() {
		elementAct.clickElementByJS(saveButton, "Save Button");
	}

	public void deleteHiddenRowAndColumn() {
		wait.waitForElementToBeClickable(deleteHiddenRowsAndColumnBtn);
		elementAct.clickElementByJS(deleteHiddenRowsAndColumnBtn, "Delete Hidden Rows And Column Button");
		elementAct.clickElementByJS(deleteHiddenRowsAndColumnPopupYesBtn, "Delete Popup Yes Btn");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickOnRenameColumnBtn() {
		wait.waitForElementToBeClickable(renameColumnBtn);
		elementAct.clickElementByJS(renameColumnBtn, "Rename Column");
	}
	public void renameColumnName(String columnName, String renamedColumnName) {
		clickOnRenameColumnBtn();
		elementAct.selectDropdown(columnToRenameDD, "column to rename dropdown", columnName);
		elementAct.enterData(newColumnNameTextbox, "New Column Name", renamedColumnName);
		elementAct.clickElementByJS(renameBtn, "Rename Button");

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isColumnRenamed(String renamedColumnName) {
		int counter = 0;
		String text;
		for (int i = 0; i < columnNameList.size(); i++) {
			elementAct.scrollIntoView(columnNameList.get(i));
			text = columnNameList.get(i).getText();

			if (text.equalsIgnoreCase(renamedColumnName)) {
				counter = 1;
				break;
			}
		}
		elementAct.scrollIntoView(columnNameList.get(0));
		if (counter == 1)
			return true;
		else
			return false;
	}

	public void deleteLocation(int numberOfLocationToDelete) {

		for (int i = 0; i < numberOfLocationToDelete; i++) {
			elementAct.clickElementByJS(selectLocationList.get(i), "Selecting location row ");
		}
		clickOnDeleteBtn();
	}

	public void splitColumn(String columnName, String newColumnName, String seperator) {
		elementAct.clickElementByJS(splitColumnBtn, "Split Column Button");
//		elementAct.selectDDOptions(selectColumnDD, "Select Column Dropdown", columnName);
		elementAct.selectDropdown(selectColumnDD, "Select Column Dropdown", columnName);
		elementAct.enterDataInTextbox(newColumnNameTextbox, "New Column Name", newColumnName);
		elementAct.enterDataInTextbox(seperatorTextbox, "Seperator", seperator);
		elementAct.clickElementByJS(splitBtn, "Split Button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.clickElementByJS(splitColumnBtn, "Split Column Button");

	}

	public boolean isColumnSplitted(String columnName, String splittedColumnName) {
		int columnIndex = 0;
		String text;
		for (int i = 0; i < columnNameList.size(); i++) {
			elementAct.scrollIntoView(columnNameList.get(i));
			text = columnNameList.get(i).getText();
			if (text.equalsIgnoreCase(columnName)) {
				columnIndex = i;
				break;
			}
		}
		text = columnNameList.get(columnIndex + 1).getText();
		System.out.println(text);
		if (text.equalsIgnoreCase(splittedColumnName))
			return true;
		else
			return false;
	}

	public void deleteColumn(String[] columnName) {
		elementAct.selectMultiCheckboxDDWithSearchbox(deleteColumnBtn, applyDeleteColumn, cancelDeleteBtn, searchBox,
				columnName, "Delete Column");
//		WebElement checkbox;
//		elementAct.clickElementByJS(deleteColumnBtn, "Delete Column Button");
//		for(int i=0; i<columnName.length; i++) {
//			elementAct.enterDataInTextbox(searchBox, "Search Column", columnName[i]);
//			checkbox = driver.findElement(By.xpath("//div[@class='dx-list-select-checkbox dx-show-invalid-badge dx-checkbox dx-widget']//span[@class='dx-checkbox-icon']"));
//			elementAct.clickElementByJS(checkbox, "Select column");
//		}
//		elementAct.clickElementByJS(applyDeleteColumn, "Apply Delete Column");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.clearElementText(locIdSearchbox);
		elementAct.clickElementByJS(searchBtn, "Search Button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// This method modifies the city and zip of Location
	public void modifyLocationData(String city, String zip) {
		elementAct.clearElementText(cityTextboxList.get(0));
		elementAct.enterDataInTextbox(textBox, "City", city);
		elementAct.clearElementText(zipTextboxList.get(0));
		elementAct.enterDataInTextbox(textBox, "Zip", zip);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isLocationDataModified(String locId, String city, String zip) {
		boolean flag = true;
		applySearchOnLocation(locId);
		if (!cityTextboxList.get(0).getText().equalsIgnoreCase(city))
			flag = false;
		if (!zipTextboxList.get(0).getText().equalsIgnoreCase(zip))
			flag = false;

		removeSearchOnLocation();
		return flag;
	}

	public boolean columnChooser(String[] columnnames) {
		return elementAct.selectMultiCheckboxDDWithSearchbox(columnChooserBtn, applyColumnChooserBtn, cancelBtn,
				searchBox, columnnames, "Column Chooser");
	}

}
