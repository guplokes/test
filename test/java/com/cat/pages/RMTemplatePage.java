package com.cat.pages;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cat.testCases.BaseClass;
import com.cat.utilities.Utils;
import com.cat.utilities.handleKeyboardEvent;

public class RMTemplatePage extends BaseClass {

	public RMTemplatePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//i[@class='la la-bar-chart']")
	WebElement annualizationBtn;
	
	@FindBy(xpath = "//i[@class='la la-location-arrow']")
	WebElement submitToQABtn;
		
	@FindBy(xpath = "//i[@class='la la-map-pin']")
	WebElement mapModifierBtn;

	@FindBy(xpath = "//td[@aria-label='Select row'] //div[@class='dx-checkbox-container']/span")
	List<WebElement> selectLocationList;

	@FindBy(xpath = "//input[@role='textbox']")
	WebElement textbox;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-trash']")
	WebElement deleteLocationBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-hidepanel']")
	WebElement applyMultiplierBtn;

//	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select Sheet Name']//div[@class='dx-dropdowneditor-icon']")
	@FindBy(xpath = "////dx-drop-down-box[@placeholder='Select Sheet Name']//input[@role='combobox']")
	WebElement selectSheetNameDD;

	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select Value Column Names']//div[@class='dx-dropdowneditor-icon']")
	WebElement selectColumnNameDD;

	@FindBy(xpath = "//dx-number-box[@id='txtBuildingValue']//input[@role='spinbutton']")
	WebElement factorValueTextbox;

	@FindBy(xpath = "//dx-button[@id='button']//div[@class='dx-button-content']")
	WebElement applyBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-rename']")
	WebElement renameColumnBtn;

	@FindBy(xpath = "//dx-select-box[@placeholder='Select column to rename']//input[@role='combobox']")
	WebElement fromColumnDD;

	@FindBy(xpath = "//dx-select-box[@placeholder='Select column to rename with']//input[@role='combobox']")
	WebElement renameToColumnDD;

	@FindBy(xpath = "//dx-button[@aria-label='Rename']")
	WebElement renameBtn;

	@FindBy(xpath = "(//i[@class='dx-icon dx-icon-close'])[2]")
	WebElement closeButton;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-revert']")
	WebElement undoCoveragesBtn;

	@FindBy(xpath = "//dx-drop-down-box[@id='columnId']//div[@class='dx-dropdowneditor-icon']")
	WebElement selectColumnDD;

	@FindBy(xpath = "//dx-button[@id='btnUndo']//div[@class='dx-button-content']")
	WebElement okUndoCoveragesBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-clear']")
	WebElement deleteColumnsBtn;

	@FindBy(xpath = "//input[@aria-label='Search']")
	WebElement columnSearchbox;

	@FindBy(xpath = "//div[@aria-label='Delete']//div[@class='dx-button-content']")
	WebElement applyDeleteBtn;

	@FindBy(xpath = "//div[@aria-label='Cancel']//div[@class='dx-button-content']")
	WebElement cancelBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-columnchooser']")
	WebElement columnChooserBtn;

	@FindBy(css = "div[aria-label='Apply'] span[class='dx-button-text']")
	WebElement applyColumnChooserBtn;

	@FindBy(css = ".dx-icon.dx-icon-fieldchooser")
	WebElement filterLocationBtn;

	@FindBy(xpath = "//dx-select-box[@placeholder='Select Entity']//input[@role='combobox']")
	WebElement entityDD;

	@FindBy(xpath = "//select[@class='form-control ng-untouched ng-pristine ng-valid']")
	WebElement selectOperatorDD;

	@FindBy(xpath = "//input[@placeholder='Enter value']")
	WebElement valueTextbox;

	@FindBy(xpath = "//dx-button[@aria-label='Filter']//div[@class='dx-button-content']")
	WebElement applyFilterBtn;
	
	@FindBy(xpath = "//dx-button[@aria-label='Reset']//div[@class='dx-button-content']")
	WebElement resetBtn;

	@FindBy(xpath = "//div[@class='ruleeditorarea']")
	WebElement ruleEditorArea;

	@FindBy(css = "div[title='Update'] i[class='dx-icon dx-icon-edit']")
	WebElement updateLocationBtn;

	@FindBy(xpath = "//div[@class='dx-button-content']//span[text()='Update']")
	WebElement updateBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-plus']")
	WebElement reviewBtn;

	@FindBy(xpath = "//div[contains(@class,'dx-popup-title')]")
	WebElement reviewMappingdPopup;

	@FindBy(xpath = "//a[normalize-space()='Mapped']")
	WebElement mappedLink;

	@FindBy(xpath = "//a[normalize-space()='UnMapped']")
	WebElement unMappedLink;

//	@FindBy(xpath = "(//i[@class='dx-icon dx-icon-close'])[2]")
//	WebElement closeReviewMappingPopup;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-exportselected']")
	WebElement findAndReplaceBtn;

	@FindBy(xpath = "//input[@role='textbox']")
	WebElement findValueTextbox;

	@FindBy(xpath = "//dx-select-box[@placeholder='Select Column']//input[@role='combobox']")
	WebElement selctColumnDDInFindAndReplace;

	@FindBy(xpath = "//dx-number-box[@placeholder='Enter value']//input[@role='spinbutton']")
	WebElement replaceValueTextbox;

	@FindBy(css = "dx-button[aria-label='Replace'] span[class='dx-button-text']")
	WebElement replaceBtn;

	@FindBy(xpath = "//dx-text-box[@placeholder='Enter separator here.']//input[@role='textbox']")
	WebElement seperatorTextbox;

	@FindBy(xpath = "//input[@aria-label='Search']")
	WebElement searchBox;

	@FindBy(xpath = "//span[normalize-space()='Delete']")
	WebElement applyDeleteColumn;

	@FindBy(xpath = "//div[@title='Search']//i[@class='dx-icon dx-icon-search']")
	WebElement searchBtn;

	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[1]")
	WebElement rowIdSearchbox;

	@FindBy(xpath = "//input[@role='textbox']")
	WebElement textBox;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-save']")
	WebElement saveButton;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-close']")
	WebElement clearAllFiltersBtn;

	@FindBy(xpath = "//div[@class='tab-content']//li[1]/span")
	WebElement locationRowCount;
	
	@FindBy(xpath = "//div[@class='tab-content']//li[2]/span")
	WebElement columnCount;

	@FindBy(xpath = "//i[@class='ft-arrow-right']")
	WebElement nextBtn;

	@FindBy(xpath = "//i[@class='ft-arrow-left']")
	WebElement previousBtn;
	
	@FindBy(xpath = "//div[@id='validationSummary']//div[@class='modal-content']")
	WebElement validationErrorsPopup;
	
	@FindBy(xpath = "//div[@id='validationSummary']//span[@class='la la-times']")
	WebElement validationErrorsCloseBtn;

	
	String columnDataXpath = "//table[@class='dx-datagrid-table dx-datagrid-table-fixed']//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]"; // td[5]
	// td[2]

	@FindBy(xpath = "//div[@class='dx-column-indicators']/following-sibling::div")
	List<WebElement> columnNameList;
	//WebElement for submit to QA
	
	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select no of location']//div[@class='dx-dropdowneditor-icon']")
	WebElement noOfLocationDD;
	
	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select platform']//div[@class='dx-dropdowneditor-icon']")
	WebElement platformDD;
	
	@FindBy(xpath = "(//span[@class='dx-button-text'])[2]")
	WebElement saveSubmitToQa;

	//WebElement for submitting cleansing QC
	
	@FindBy(xpath = "//i[@class='la la la-thumbs-up']")
	WebElement approveBtn;
	
	@FindBy(xpath = "//textarea[@role='textbox']")
	WebElement commentsTextbox;
	//dx-button[@type='success']/div
	@FindBy(xpath = "(//div[@class='dx-button-content']//span)[2]")
	WebElement submitCleansingQCCompleteBtn;
	
	
	public boolean verifyRMTemplatePage() {
		WebElement element = driver.findElement(By.xpath("//h3[normalize-space()='RM Template']"));
		wait.waitForElement(element);
		String actualText = element.getText();

		String expectedText = "RM Template";
		return elementAct.verifyText(actualText, expectedText);
	}
	
	// Verifying RM template page with handling of worktray redirection 	
	public boolean verifyRMTemplatePageWithWorktrayRedirection(String accountName) {
		boolean result;
		try {
		result = verifyRMTemplatePage();
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
			result = verifyRMTemplatePage();			
		}
		return result;
	}

	public int getNumberOfRowsInGrid() {
		int noOfRows = driver.findElements(By.xpath(
				"//table[@class='dx-datagrid-table dx-datagrid-table-fixed']//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]//td[2]"))
				.size();
		return noOfRows;
	}

	public void clickOnDeleteBtn() {
		elementAct.clickElementByJS(deleteLocationBtn, "Delete selected location");
	}

	public void clickOnClearAllFilters() {
		wait.waitForElementToBeClickable(clearAllFiltersBtn);
		elementAct.clickElementByJS(clearAllFiltersBtn, "lear All Filters Btn");

	}

	public MapModifierPage clickOnMapModifierBtn() {
		wait.waitForElementToBeClickable(mapModifierBtn);
		elementAct.clickElementByJS(mapModifierBtn, "Map Modifier");
		return new MapModifierPage();
	}

	public int getNumberOfColumnsInGrid() {
		return columnNameList.size();
	}

	public List<WebElement> columnDataList(int columnIndex) {
		return driver.findElements(By.xpath(columnDataXpath + "/td[" + columnIndex + "]"));
	}

	public void multiplierDropdown(WebElement ddElement, String ddName, String ddOption) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.scrollIntoView(ddElement);
//		elementAct.clickElementByJS(ddElement, ddName);
		elementAct.clickElement(ddElement, ddName);
		WebElement option = driver.findElement(By.xpath("//span[normalize-space()='" + ddOption + "']"));
		elementAct.scrollIntoView(option);
		elementAct.clickElementByJS(option, ddOption);
		elementAct.clickElementByJS(ddElement, ddName);

	}

	// returns -1 if column not found
	public int getColumnIndex(String columnName) {
		String text;
		int columnIndex = -1;
		elementAct.scrollIntoView(columnNameList.get(0));
		for (int index = 0; index < columnNameList.size(); index++) {
			elementAct.scrollIntoView(columnNameList.get(index));
			text = columnNameList.get(index).getText();
			if (text.equalsIgnoreCase(columnName)) {
				columnIndex = index;
				break;
			}
		}
		return columnIndex;
	}

	public boolean verifyColumnData(String columnName, String[] data) {
		String text;
//		int index = 0;
		boolean flag = false;

		int columnIndex = getColumnIndex(columnName);
		if(columnIndex == -1)
			return false;
		columnIndex = columnIndex + 2;
		List<WebElement> columnDataList = columnDataList(columnIndex);

		for (int k = 0; k < data.length; k++) {
			flag = false;
			text = columnDataList.get(k).getText();
			text = text.replace(",", "");
//			System.out.println(text);
//			System.out.println(data[k]);
			if (text.equalsIgnoreCase(data[k]))
				flag = true;
			else if (text.contains(data[k]))
				flag = true;
			if (flag == false)
				break;

		}
//		elementAct.scrollIntoView(columnNameList.get(0));
		return flag;
	}

	public void clickOnPreviousBtn() {
		elementAct.clickElementByJS(previousBtn, "Previous Button");
	}

	public void clickOnSaveBtn() {
		elementAct.moveToElement(saveButton);
		elementAct.clickElementByJS(saveButton, "Save Button");
	}

	public void deleteLocation(int numberOfLocationToDelete) {

		for (int i = 1; i <= numberOfLocationToDelete; i++) {
			elementAct.clickElementByJS(selectLocationList.get(i), "Selecting location row ");
		}
		clickOnDeleteBtn();
	}

	public void renameColumnName(String columnName, String renamedColumnName) {
		try {
			elementAct.clickElementByJS(renameColumnBtn, "Rename Column");
			Thread.sleep(500);
			elementAct.selectDDOptions(fromColumnDD, "From Column dropdown", columnName);
			elementAct.selectDDOptions(renameToColumnDD, "Rename To Column Dropdown", renamedColumnName);

			elementAct.clickElementByJS(renameBtn, "Rename Button");
			Thread.sleep(1000);
		} catch (TimeoutException e) {
			clickOnCloseButton();
		} catch (InterruptedException e) {
			e.printStackTrace();
			clickOnCloseButton();
		}

	}

	public boolean isColumnRenamed(String renamedColumnName) {
		int columnIndex = getColumnIndex(renamedColumnName);
		elementAct.scrollIntoView(columnNameList.get(0));
		if (columnIndex == -1)
			return false;
		else
			return true;
	}

	public void clickOnCloseButton() {

		elementAct.scrollIntoView(closeButton);
		elementAct.clickElementByJS(closeButton, "Close");
	}

	public void applySearchOnLocation(String rowID) {
		elementAct.clickElementByJS(searchBtn, "Search Button");
		elementAct.enterData(rowIdSearchbox, "Org Loc ID", rowID);
		elementAct.clickElementByJS(searchBtn, "Search Button");
		try {
			Thread.sleep(1000);
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
		elementAct.clearElementText(rowIdSearchbox);
		elementAct.clickElementByJS(searchBtn, "Search Button");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void applyMultilplier(String sheetName, String columnName, String factorValue) {
		elementAct.clickElementByJS(applyMultiplierBtn, "Apply Multiplier Button");
		multiplierDropdown(selectSheetNameDD, "SheetName Dropdown", sheetName);
		multiplierDropdown(selectColumnNameDD, "ColumnName Dropdown", columnName);
		elementAct.enterData(factorValueTextbox, "Factor Value", factorValue);

		elementAct.moveToElement(applyBtn);
		elementAct.clickElement(applyBtn, "Apply Multiplier");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void undoCoverages(String columnName) {
//		elementAct.clickElement(undoCoveragesBtn, "Undo Coverages Button");
//		elementAct.selectDropdownManual(selectColumnDD, "Select Column", columnName);
//		elementAct.clickElement(okUndoCoveragesBtn, "Ok Button");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void undoAllCoverages() {
		wait.waitForElementToBeClickable(undoCoveragesBtn);
		elementAct.scrollIntoView(undoCoveragesBtn);
		elementAct.clickElementByJS(undoCoveragesBtn, "Undo Coverages Button");
		elementAct.clickElementByJS(selectColumnDD, "Undo Coverages Dropdown");
		WebElement selectAllCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Select All')]"));
		wait.waitForElementToBeClickable(selectAllCheckbox);
		elementAct.clickElementByJS(selectAllCheckbox, "Select All");
		elementAct.clickElementByJS(selectColumnDD, "Undo Coverages Dropdown");
		elementAct.clickElement(okUndoCoveragesBtn, "Ok Button");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean deleteColumns(String[] columnNames) {
		boolean flag = elementAct.selectMultiCheckboxDDWithSearchbox(deleteColumnsBtn, applyDeleteBtn, cancelBtn,
				columnSearchbox, columnNames, "Delete Columns");
		return flag;
	}

	public boolean filterLocationData(String entity, String operator, String value) {
		wait.waitForElementToBeClickable(filterLocationBtn);
		elementAct.moveToElement(filterLocationBtn);
		try {
			elementAct.clickElement(filterLocationBtn, "Filter Location Button");
			elementAct.clickElementByJS(resetBtn, "Reset");

			elementAct.selectEntityValue(entityDD, entity);
			Thread.sleep(500);
			elementAct.selectOperatorValue(selectOperatorDD, operator);
			elementAct.enterData(valueTextbox, "Value", value);

			ruleEditorArea.click();
			elementAct.clickElementByJS(applyFilterBtn, "Apply Filter");
		} catch (TimeoutException e) {
			clickOnCloseButton();
			return false;
		} catch (Exception e) {
			clickOnCloseButton();
			return false;
		}
		return true;

	}

	public boolean updateLocationData(String entity, String value) {
		wait.waitForElementToBeClickable(updateLocationBtn);
		elementAct.moveToElement(updateLocationBtn);
		elementAct.clickElement(updateLocationBtn, "Update Location Button");
		wait.waitForElementToBeClickable(resetBtn);
		elementAct.clickElementByJS(resetBtn, "Reset");
		try {
			elementAct.selectEntityValue(entityDD, entity);
			elementAct.enterData(valueTextbox, "Value", value);
			ruleEditorArea.click(); // to remove focus from Value TextBox
			elementAct.clickElementByJS(updateBtn, "Update Button");
		} catch (TimeoutException e) {
			clickOnCloseButton();
			return false;
		} catch (Exception e) {
			clickOnCloseButton();
			return false;
		}
		return true;
	}

	public void performAnnualization(String[] bipoiValues) {
		String text, xpath;
		int index = -1;
		for (int columnIndex = 0; columnIndex < columnNameList.size(); columnIndex++) {
			elementAct.scrollIntoView(columnNameList.get(columnIndex));
			text = columnNameList.get(columnIndex).getText();
			if (text.equalsIgnoreCase("BIPOI")) {
				index = columnIndex;
				break;
			}
		}
		index = index + 2;
		for (int rowIndex = 1; rowIndex <= bipoiValues.length; rowIndex++) {
			xpath = "(//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')])[" + (rowIndex + 1)
					+ "]/td[" + index + "]";
			WebElement element = driver.findElement(By.xpath(xpath));
			elementAct.clickElement(element, "BIPOI textbox");
			elementAct.enterData(textBox, "textbox", bipoiValues[rowIndex - 1]);
		}

		try {
			handleKeyboardEvent.pressTabKey();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickOnSaveBtn();
		if (new Utils().isSuccess())
			elementAct.clickElementByJS(annualizationBtn, "Perform Annualization");
	}

	public boolean reviewMappings() {
		elementAct.clickElementByJS(reviewBtn, "Review Button");
		boolean flag = true;
		if (!elementAct.verifyMessage(reviewMappingdPopup, "Review Mappings", "Review Button Popup"))
			flag = false;
		if (!elementAct.verifyText(mappedLink.getAttribute("class"), "nav-link active"))
			flag = false;
		elementAct.clickElement(unMappedLink, "Un-Mapped Link");
		if (!elementAct.verifyText(unMappedLink.getAttribute("class"), "nav-link active"))
			flag = false;
		elementAct.clickElementByJS(closeButton, "Close Review Mapping");
		return flag;
	}

	public void columnChooser(String[] columnnames) {
		elementAct.selectMultiCheckboxDDWithSearchbox(columnChooserBtn, applyColumnChooserBtn, cancelBtn,
				columnSearchbox, columnnames, "Column Chooser dropdown");
	}

	public void findAndReplace(String columnName, String findText, String replaceText) {
		elementAct.clickElementByJS(findAndReplaceBtn, "Find & Replace");

		elementAct.selectDDOptions(selctColumnDDInFindAndReplace, "Column Name", columnName);
		elementAct.enterData(findValueTextbox, "find Textbox", findText);
		elementAct.enterData(replaceValueTextbox, "Replace Textbox", replaceText);
		elementAct.moveToElement(replaceBtn);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.clickElement(replaceBtn, "Apply Find & Repalce");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clickOnSubmitToQA() {
		elementAct.clickElementByJS(submitToQABtn, "Submit to QA");
	}
	public boolean isValidationErrorsDisplayed() {
		return validationErrorsPopup.isDisplayed();
	}
	
	public void closeValidationErrorPopup() {
		elementAct.clickElementByJS(validationErrorsCloseBtn, "Close Validation Errors");
	}
	
	public void submitCleansingQC() {
//		elementAct.clickElementByJS(resetBtn, "Reset Button");
//		elementAct.selectDropdownManual(noOfLocationDD, "number of Locations", "1-100");
//		elementAct.selectDropdownManual(platformDD, "Platform", "AIR");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.scrollIntoView(saveSubmitToQa);
//		elementAct.clickElementByJS(saveSubmitToQa, "Saving for QC");
		elementAct.clickElement(saveSubmitToQa, "Saving for QC");
	}

	public void submitCleansingQCComplete(String comments) {
		elementAct.scrollIntoView(approveBtn);
		elementAct.clickElementByJS(approveBtn, "Approve Cleansing QC Button");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		wait.waitForElementToBeClickable(commentsTextbox);
		elementAct.scrollIntoView(commentsTextbox);
		elementAct.enterData(commentsTextbox, "comments", comments);
//		elementAct.enterDataInTextbox(commentsTextbox, "comments", comments);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.scrollIntoView(submitCleansingQCCompleteBtn);
		elementAct.clickElement(submitCleansingQCCompleteBtn, "Submit Cleansing Qc");
	}
}
