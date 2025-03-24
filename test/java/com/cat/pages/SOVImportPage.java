package com.cat.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cat.testCases.BaseClass;
import com.cat.utilities.handleKeyboardEvent;

public class SOVImportPage extends BaseClass {

	public SOVImportPage() {
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//h3[normalize-space()='Merge SOV Data']")
	WebElement mergeSOVTitle;

	@FindBy(xpath = "//h3[normalize-space()='SOV Preview Data']")
	WebElement sovPreviewTitle;

	@FindBy(xpath = "//a[@id='baseIcon-tab31']")
	WebElement sovImportBtn;

	@FindBy(xpath = "//td[@aria-label='Select row'] //div[@class='dx-checkbox-container']/span")
	List<WebElement> selectSheetList;

	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[10] //a[@title='Delete']")
	List<WebElement> deleteSheetBtnList;

	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[4]")
	List<WebElement> sheetNameList;

	@FindBy(xpath = "//table[@class='dx-datagrid-table dx-datagrid-table-fixed dx-select-checkboxes-hidden']/tbody/tr/td[5]")
	List<WebElement> headerRangeList;

	@FindBy(xpath = "//table[@class='dx-datagrid-table dx-datagrid-table-fixed dx-select-checkboxes-hidden']/tbody/tr/td[6]")
	List<WebElement> columnRangeList;

	@FindBy(xpath = "//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines dx-selection']/td[8] //span")
	WebElement transposeCheckbox;
//	List<WebElement> transposeCheckbox;

	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[9] //span")
	List<WebElement> keyMergeCheckbox;

	@FindBy(css = "div[aria-label='Select file'] span[class='dx-button-text']")
	WebElement selectImportFileBtn;

	@FindBy(xpath = "//input[@role='textbox']")
	WebElement textbox;

	@FindBy(xpath = "//button[normalize-space()='Next']")
	WebElement nextBtn;

	@FindBy(xpath = "//i[@class='ft-arrow-left']")
	WebElement previousBtn;

	@FindBy(xpath = "//div[@title='Preview Data']")
	WebElement previewDataBtn;

	@FindBy(xpath = "//div[@aria-label='Yes']//div[@class='dx-button-content']")
	WebElement deleteYesBtn;
	
	@FindBy(xpath = "//div[@aria-label='No']//div[@class='dx-button-content']")
	WebElement deleteNoBtn;

	@FindBy(xpath = "//div[@title='Select Bulk Action']//div[@class='dx-dropdowneditor-icon']")
	WebElement bulkActionDD;

	@FindBy(xpath = "//button[normalize-space()='Yes, Delete Files']")
	WebElement bulkDeleteYesBtn;
	
	@FindBy(xpath = "(//button[@type='button'][normalize-space()='No'])[1]")
	WebElement bulkDeleteNoBtn;

	@FindBy(xpath = "(//button[@class='btn btn-info'])[2]")
	WebElement yesOverrideFilesBtn;
	
	//FOR QC VERIFICATION
	@FindBy(id = "baseIcon-tab33")
	WebElement mappingSummaryTab;
	
	@FindBy(id = "baseIcon-tab34")
	WebElement rmTemplateTab;
	
	public void clickOnMappingSummaryTab() {
		elementAct.clickElementByJS(mappingSummaryTab, "Mapping Summary Tab");
	}
	
	public void clickOnRMTemplateTab() {
		elementAct.clickElementByJS(rmTemplateTab, "RM TemplateTab Tab");
	}

	public void clickOnSovImort() {
		wait.waitForElementToBeClickable(sovImportBtn);
		elementAct.clickElementByJS(sovImportBtn, "SOV Import");
	}

	public boolean verifySovImportPage() {
		WebElement element = driver.findElement(By.xpath("//h3[normalize-space()='SOV Import']"));
		wait.waitForElement(element);
		String actualText = element.getText();
		String expectedText = "SOV Import";
		return elementAct.verifyText(actualText, expectedText);
	}

	public void clickOnNextButton() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.scrollIntoView(nextBtn);
		elementAct.clickElementByJS(nextBtn, "Next Btn");
	}

	public void clickOnPreviousBtn() {
		elementAct.clickElementByJS(previousBtn, "Back");
	}

	public void clickOnPreviewData() {
		elementAct.scrollIntoView(previewDataBtn);
//		elementAct.clickElement(previewDataBtn, "Preview Data");
		elementAct.clickElementByJS(previewDataBtn, "Preview Data");
	}

	public void clickOnBulkDelete() {
		elementAct.selectDDOptions(bulkActionDD, "bulk Action Dropdown", "Delete");
	}

	public int getNumberOfSheetsInGrid()  {
//		wait.waitForElementToBeClickable(selectSheetList.get(0));
		elementAct.scrollIntoView(selectSheetList.get(0));
		return selectSheetList.size();
		
	}

	public void selectSovFile(String fileName) throws Exception {
		elementAct.clickElement(selectImportFileBtn, "upload sov file");
		Thread.sleep(1000);
		handleKeyboardEvent.uploadFile(fileName);
		if (yesOverrideFilesBtn.isDisplayed())
			elementAct.clickElementByJS(yesOverrideFilesBtn, "Yes, Override Files Btn");

	}

	// Selecting the first sheet and moving to mergeSOV page(sheet1)
	public void processSOV(String sheetName) {
		String text;
		for (int i = 0; i < sheetNameList.size(); i++) {
			text = sheetNameList.get(i).getText();
			if (text.equalsIgnoreCase(sheetName)) {
				wait.waitForElementToBeClickable(selectSheetList.get(i));
				elementAct.scrollIntoView(selectSheetList.get(i));
				elementAct.clickElementByJS(selectSheetList.get(i), "selecting sheet");
				break;
			}
		}

	}

	public boolean headerAndColumnRange(String sheetName, String headerRange, String columnRange) {
		String text;
		for (int i = 0; i < sheetNameList.size(); i++) {
			text = sheetNameList.get(i).getText();
			if (text.equalsIgnoreCase(sheetName)) {
				elementAct.doubleClickElement(headerRangeList.get(i), "headerRange");
				elementAct.enterDataByJS(textbox, "header Range", headerRange);
				elementAct.doubleClickElement(columnRangeList.get(i), "column Range");
				elementAct.enterDataByJS(textbox, "column Range", columnRange);
				elementAct.clickElement(selectSheetList.get(i), "select sheet");
//				elementAct.clickElementByJS(nextBtn, "Next Btn");
				elementAct.moveToElement(previewDataBtn);
				elementAct.clickElementByJS(previewDataBtn, "Preview Data");
				break;
			}
		}

//		wait.waitForElement(sovPreviewTitle);
//		String actualText = sovPreviewTitle.getText();
//		System.out.println(actualText);
//		boolean result = elementAct.verifyText(actualText, "SOV Preview Data");
////		"Merge SOV Data"
//		clickOnPreviousBtn();
		boolean result = new SOVPreviewDataPage().verifySOVPreviewDataPage();
		clickOnPreviousBtn();
		return result;
	}

	public boolean transpose(String sheetName) {
		String text;
		for (int i = 0; i < sheetNameList.size(); i++) {
			text = sheetNameList.get(i).getText();
			if (text.equalsIgnoreCase(sheetName)) {
				// elementAct.clickElementByJS(transposeCheckboxList.get(i), "transpose");
				elementAct.scrollIntoView(selectSheetList.get(i));
//				elementAct.clickElement(selectSheetList.get(i), "select sheet");
				elementAct.clickElementByJS(selectSheetList.get(i), "select sheet");
				elementAct.scrollIntoView(transposeCheckbox);
				elementAct.clickElementByJS(transposeCheckbox, "transpose");
				elementAct.clickElementByJS(previewDataBtn, "Preview Data");
				break;
			}
		}
		boolean result = new SOVPreviewDataPage().verifySOVPreviewDataPage();
		clickOnPreviousBtn();
		return result;
	}

	
	public boolean clickOnDeleteYesBtn() {
		elementAct.scrollIntoView(deleteSheetBtnList.get(4));
		elementAct.clickElementByJS(deleteSheetBtnList.get(4), "delete sheet");
		wait.waitForElementToBeClickable(deleteYesBtn);
		if(deleteYesBtn.isDisplayed())
		{
			elementAct.clickElementByJS(deleteYesBtn, "Yes delete");
			return true;
		}
		return false;
	}
	
	public boolean clickOnDeleteNoBtn() {
		elementAct.clickElementByJS(deleteSheetBtnList.get(4), "delete sheet");
		wait.waitForElementToBeClickable(deleteNoBtn);
		if(deleteNoBtn.isDisplayed())
		{
			elementAct.clickElementByJS(deleteNoBtn, "Yes delete");
			return true;
		}
		return false;
	}

	public boolean clickOnBulkDeleteYesButton(String[] sheetNames) {
		List<String> sheets = Arrays.asList(sheetNames);
		String text;
		elementAct.scrollIntoView(selectSheetList.get(0));
		for (int i = 0; i < sheetNameList.size(); i++) {
			text = sheetNameList.get(i).getText();
			if (sheets.contains(text))
				elementAct.clickElementByJS(selectSheetList.get(i), text);
		}

		clickOnBulkDelete();
		wait.waitForElementToBeClickable(bulkDeleteYesBtn);
		if (bulkDeleteYesBtn.isDisplayed()) {
			elementAct.clickElementByJS(bulkDeleteYesBtn, "Yes, bulk delete");
			return true;
		} else
			return false;
	}
	
	public boolean clickOnBulkDeleteNoButton() {
		elementAct.clickElementByJS(selectSheetList.get(4), "select sheet");
		clickOnBulkDelete();
		wait.waitForElementToBeClickable(bulkDeleteNoBtn);
		if(bulkDeleteNoBtn.isDisplayed())
		{
			elementAct.clickElementByJS(bulkDeleteNoBtn, "NO, Bulk Delete");
			elementAct.clickElementByJS(selectSheetList.get(4), "De-select sheet");
			return true;
		}
		return false;
	}

	public boolean keyMerge(String[] sheetNames) {
		List<String> sheets = Arrays.asList(sheetNames);
		String text;
		for (int i = 0; i < sheetNameList.size(); i++) {
			text = sheetNameList.get(i).getText();
			if (sheets.contains(text)) {
				elementAct.scrollIntoView(selectSheetList.get(i));
				elementAct.clickElementByJS(selectSheetList.get(i), "select sheet");
				elementAct.scrollIntoView(keyMergeCheckbox.get(i));
				elementAct.clickElementByJS(keyMergeCheckbox.get(i), "key merge checkbox");
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		elementAct.clickElementByJS(previewDataBtn, "Preview Data");
		boolean result = new SOVPreviewDataPage().verifySOVPreviewDataPage();

//			wait.waitForElement(sovPreviewTitle);
//			String actualText = sovPreviewTitle.getText();
//			System.out.println(actualText);
//			boolean result = elementAct.verifyText(actualText, "SOV Preview Data");
		clickOnPreviousBtn();
		return result;

	}

}
