package com.cat.pages;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cat.testCases.BaseClass;


public class MappingSummaryPage extends BaseClass {

	public MappingSummaryPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@title='Column Search']")
	WebElement columnSearchBtn;

	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[1]")
	WebElement sovColumnSearchTextbox;

	@FindBy(xpath = "//div[@class='card-body card-dashboard'] //tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[1]")
	List<WebElement> sovColumnList;

	@FindBy(xpath = "//div[@class='card-body card-dashboard'] //tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[2]")
	List<WebElement> oedColumnList;
	
	@FindBy(xpath = "//div[@class='dx-column-indicators']/following-sibling::div")
	List<WebElement> columnNameList;
	
	@FindBy(xpath = "//span[@class='dx-icon dx-icon-clear']")
	WebElement clearOedColumnTextbox;

	@FindBy(xpath = "//div[contains(@class,'dx-datagrid-nowrap dx-scrollable dx-visibility-change-handler')]//input[@role='combobox']")
	WebElement oedColumnTextbox;

	@FindBy(xpath = "//div[contains(@class,'dx-datagrid-nowrap dx-scrollable dx-visibility-change-handler')]//div[@class='dx-dropdowneditor-icon']")
	WebElement oedColumnDD;

	@FindBy(id = "lnkMapped")
	WebElement mappedColumnLink;

	@FindBy(id = "lnkUnMapped")
	WebElement unmappedColumnLink;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-close']")
	WebElement clearMappingBtn;

	@FindBy(xpath = "//input[@aria-label='Search']")
	WebElement clearMappingSearchbox;

	@FindBy(xpath = "//div[@class='dx-list-select-checkbox dx-show-invalid-badge dx-checkbox dx-widget']//span[@class='dx-checkbox-icon']")
	List<WebElement> clearMappingCheckbox;

	@FindBy(xpath = "//div[@aria-label='Apply']//div[@class='dx-button-content']")
	WebElement applyClearMapping;

	@FindBy(xpath = "	//span[normalize-space()='Cancel']")
	WebElement cancelClearMapping;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-arrowup']")
	WebElement moveUpBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-arrowdown']")
	WebElement moveDownBtn;
	
	@FindBy(xpath = "//div[@class='dx-switch-handle']")
	WebElement visibleTabNameSwitch;
	
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-plus']")
	WebElement reviewBtn;

	@FindBy(xpath = "//div[@class='titlehead']")
	WebElement reviewUnmappedColumnWindowTitle;

	@FindBy(xpath = "//div[@title='Close']")
	WebElement closeReviewUnmappedColumnWindow;

	@FindBy(xpath = "//div[@title='Tabwise TIV']//div[@class='dx-button-content']")
	WebElement tabwiseTIVBtn;

	@FindBy(xpath = "//div[contains(@class,'summary_cards')]/ul/li/span[1]")
	List<WebElement> tabwiseTIVList;

	@FindBy(xpath = "(//i[@class='dx-icon dx-icon-save'])[1]")
	WebElement saveButton;

	@FindBy(xpath = "//button[normalize-space()='Next']")
	WebElement nextBtn;

	@FindBy(xpath = "//i[@class='ft-arrow-left']")
	WebElement previousBtn;

	//For Cleansing QC
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-check']")
	WebElement approveBtn;
	
	public boolean verifyMappingSummaryPage() {
		WebElement element = driver.findElement(By.xpath("//h3[normalize-space()='Mapping Summary']"));
		wait.waitForElement(element);
		String actualText = element.getText();

		String expectedText = "Mapping Summary";
		return elementAct.verifyText(actualText, expectedText);
	}

	public int getNumberOfRowsInGrid() {
		int noOfRows = driver.findElements(By.xpath(
				"//div[@class='card-body card-dashboard'] //tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]"))
				.size();
		return noOfRows;
	}

	public void clickOnPreviousBtn() {
		elementAct.clickElementByJS(previousBtn, "Previous Button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnSaveBtn() {
		elementAct.clickElement(saveButton, "Save Button");
	}
	
	public void clickOnVisibleTabNameSwitch() {
		elementAct.clickElementByJS(visibleTabNameSwitch, "Visible Tab Name switch");
	}
	

	public RMTemplatePage clickOnNextBtn() {
		wait.waitForElementToBeClickable(nextBtn);
		elementAct.clickElementByJS(nextBtn, "Next Button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new RMTemplatePage();
	}

	public boolean isColumnSearch(String sovColumnName) {
		String text = sovColumnList.get(0).getText();
		if (text.equalsIgnoreCase(sovColumnName))
			return true;
		else
			return false;
	}

	public void applySovColumnSearch(String sovColumnName) {
		elementAct.clickElementByJS(columnSearchBtn, "Column Search Button");
		wait.waitForElementToBeClickable(sovColumnSearchTextbox);
		elementAct.actionSendKeys(sovColumnSearchTextbox, sovColumnName);
		elementAct.clickElementByJS(columnSearchBtn, "Column Search Button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void removeSovColumnSearch() {
		elementAct.clickElementByJS(columnSearchBtn, "Column Search Button");
		wait.waitForElementToBeClickable(sovColumnSearchTextbox);
		sovColumnSearchTextbox.clear();

		elementAct.clickElementByJS(columnSearchBtn, "Column Search Button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean updateMapping(String sovColumnName, String oedColumnName) {
		String text;
		int index = -1;
		for (int i = 0; i < sovColumnList.size(); i++) {
			text = sovColumnList.get(i).getText();
			if (text.contains(sovColumnName)) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			return false;
		}
		text = oedColumnList.get(index).getText();
		if(!text.equalsIgnoreCase(oedColumnName)) {
		elementAct.clickElementByJS(oedColumnList.get(index), "oed column");
		if (!clearOedColumnTextbox.isDisplayed())
			return false;
		elementAct.clickElementByJS(clearOedColumnTextbox, "clear OED cloumn");
		elementAct.enterData(oedColumnTextbox, "OED ColumnName", oedColumnName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement element = driver.findElement(By.xpath("//div[contains(text(),'" + oedColumnName + "')]"));
		elementAct.clickElementByJS(element, "Selecting OED Column");
		}
		return true;
	}

	//For Proration & RM Template: This method is for the scenario when ML is not working or issue with mapping of column
	public void updateUnmappedColumns() {
		unmappedColumnLink.click();
		String[] sovColumnData = { "StateCode", "Country", "Roof Built", "Content Value", "*# of Stories", "*Square Footage"};
		String[] oedColumnData = { "AreaCode", "CountryCode", "RoofYearBuilt", "ContentsTIV", "NumberOfStories", "FloorArea"};
		int counter = 0;
		String text;
		while (counter < sovColumnData.length) {
			for (int index = 0; index < sovColumnList.size(); index++) {
				text = sovColumnList.get(index).getText();
				if (text.contains(sovColumnData[counter])) {
//					elementAct.clickElementByJS(oedColumnList.get(index), "oed column");
					wait.waitForElement(oedColumnList.get(index));
					elementAct.moveToElement(oedColumnList.get(index));
					elementAct.doubleClickElement(oedColumnList.get(index), "oed column");
					wait.waitForElementToBeClickable(oedColumnTextbox);
					elementAct.enterData(oedColumnTextbox, oedColumnData[counter], oedColumnData[counter]);
					try {
						Thread.sleep(700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					WebElement element = driver
							.findElement(By.xpath("//div[contains(text(),'" + oedColumnData[counter] + "')]"));
					elementAct.clickElementByJS(element, "Selecting OED Column");
					break;
				}
			}
			counter++;
		}
		elementAct.clickElementByJS(saveButton, "Save");
		mappedColumnLink.click();
		
	}

	public boolean isColumnMapped(String sovColumnName, String oedColumnName) {
		applySovColumnSearch(sovColumnName);
		boolean flag = false;
		if ((oedColumnList.get(0).getText()).contains(oedColumnName))
			flag = true;
		removeSovColumnSearch();
		return flag;
	}

	public boolean isCountOfMappedAndUnmappedColumnVisible() {
		wait.waitForElement(mappedColumnLink);
		String mappedText = mappedColumnLink.getText();
		String unmappedText = unmappedColumnLink.getText();
		boolean flag = false;
		if (mappedText.matches("[0-9].*"))
			flag = true;
		if (unmappedText.matches("[0-9].*"))
			flag = true;
		else
			flag = false;
		return flag;
	}

	public boolean clearMapping(String[] sovColumns) {
		elementAct.clickElementByJS(clearMappingBtn, "Clear Mapping Button");
		List<WebElement> selectAllCheckbox = driver.findElements(By.xpath(
				"//div[contains(@class,'dx-list-select-all-checkbox dx-show-invalid-badge  dx-checkbox-checked')]//span[@class='dx-checkbox-icon']"));
//		System.out.println(selectAllCheckbox.size());
		if (selectAllCheckbox.size() != 0)
			elementAct.clickElementByJS(selectAllCheckbox.get(0), "De-selecting all");
		for (int i = 0; i < sovColumns.length; i++) {
			elementAct.enterData(clearMappingSearchbox, "Search sovColumn", sovColumns[i]);
			if (clearMappingCheckbox.size() == 0) {
				elementAct.clickElementByJS(cancelClearMapping, "Cancel clear mapping");
				return false;
			}
			elementAct.clickElementByJS(clearMappingCheckbox.get(0), "checkbox");
			elementAct.clearElementText(clearMappingSearchbox);
		}
		elementAct.clickElementByJS(applyClearMapping, "Apply Clear Mapping");
		return true;
	}

	public boolean isMappingCleared(String[] sovColumns) {
		String text;
		int count = 0;
//		display20ItemsOnGrid();
		wait.waitForElement(unmappedColumnLink);
		elementAct.clickElementByJS(unmappedColumnLink, "unmapped columns");
		List<String> list = Arrays.asList(sovColumns);
		wait.waitForElementsAttribute(unmappedColumnLink, "class", "active");
		for (int i = 0; i < sovColumnList.size(); i++) {
			elementAct.scrollIntoView(sovColumnList.get(i));
			text = sovColumnList.get(i).getText();
			if (list.contains(text))
				count = count + 1;
		}
		if (count == sovColumns.length) {
			elementAct.clickElementByJS(mappedColumnLink, "Mapped Column");
			return true;
		} else
			return false;
	}

	public boolean moveUpSovColumn() {
		int index = 3;
		String text = sovColumnList.get(index).getText();
		elementAct.clickElementByJS(sovColumnList.get(index), "click on 3rd sov column");
		elementAct.clickElementByJS(moveUpBtn, "Move Up SovColumn");
		if (text.equals(sovColumnList.get(index - 1).getText()))
			return true;
		else
			return false;
	}

	public boolean moveDownSovColumn() {
		int index = 2;
		String text = sovColumnList.get(index).getText();
		elementAct.clickElementByJS(sovColumnList.get(index), "click on 4th sov column");
		elementAct.clickElementByJS(moveDownBtn, "Move Down SovColumn");
		if (text.equals(sovColumnList.get(index + 1).getText()))
			return true;
		else
			return false;
	}

	public boolean viewUnmappedColumns() {
		elementAct.clickElementByJS(unmappedColumnLink, "Unmapped Column Link");
		String text = unmappedColumnLink.getAttribute("class");
		elementAct.clickElementByJS(mappedColumnLink, "mapped column");
		if (text.equalsIgnoreCase("active"))
			return true;
		else
			return false;
	}

	public boolean isMLPredictingOedColumns() {
		try {
		Thread.sleep(1000);
		WebElement mapped = driver.findElement(By.xpath("//a[@class='active']"));
		wait.waitForElement(mapped);
		Thread.sleep(500);
		wait.waitForElementToBeClickable(oedColumnList.get(1));
//		elementAct.doubleClickElement(oedColumnList.get(1), "oed Column");
		elementAct.clickElementByJS(oedColumnList.get(1), "Oed Column");
		elementAct.clickElement(oedColumnDD, "OED column dropdown");
		List<WebElement> oedList = driver.findElements(By.xpath("//div[contains(@class,'dx-overlay-content dx-popup-normal')] //div[contains(@class,'dx-scrollview-content')]//div[@class='dx-item-content dx-list-item-content']"));
//		String text = driver.findElements(By.xpath("//div[@class='dx-item-content dx-list-item-content']")).get(1)
//				.getText();

		elementAct.scrollIntoView(oedList.get(0));
		String text = oedList.get(0).getText();
		String[] args = text.split("-");
		String percentage = args[1].trim();
		if (percentage.equals("0.00%") )
			{
			if(percentage.equals("100.00%"))
				return true;
			else
				return false;
			}
			
		else
			return true;
	}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean isReviewUnmappedColumnDisplayed() {
		elementAct.clickElementByJS(reviewBtn, "Review Button");
		boolean flag = false;
		if (reviewUnmappedColumnWindowTitle.isDisplayed()) {
			flag = elementAct.verifyText(reviewUnmappedColumnWindowTitle.getText(), "Review Unmapped Columns");
			elementAct.clickElementByJS(closeReviewUnmappedColumnWindow, "Close Btn");
		}
		return flag;
	}

	public boolean isTabwiseTIVDisplayed(String[] tivColumns) {
		elementAct.clickElementByJS(tabwiseTIVBtn, "Tabwise TIV Button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.waitForElement(tabwiseTIVList.get(0));
		for (int i = 0; i < tivColumns.length; i++) {
			if (!tabwiseTIVList.get(i).getText().equals(tivColumns[i]))
				return false;
		}
		return true;
	}
	
	public boolean isTabNameColumnDisplayed() {
		String text;
		for(int i = 0; i < columnNameList.size(); i++) {
			text = columnNameList.get(i).getText();
			if(text.equalsIgnoreCase("Tab Name"))
				return true;						
		}
		return false;
	}

	public void approveMappingForCleansingQC() {
//		elementAct.clickElement(oedColumnList.get(2), "Select record");
		elementAct.clickElement(oedColumnList.get(3), "Select record");
		elementAct.clickElementByJS(approveBtn, "approve mapping");
	}
}
