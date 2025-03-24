package com.cat.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class ProrationPage extends BaseClass {

	public ProrationPage() {
		PageFactory.initElements(driver, this);
	}

	String prorationType, prorationBasisType;

	String columnDataXpath = "//table[@class='dx-datagrid-table dx-datagrid-table-fixed']//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]";

	@FindBy(xpath = "//div[@class='dx-column-indicators']/following-sibling::div")
	List<WebElement> columnNameList;

	@FindBy(xpath = "//td[@aria-label='Select row'] //div[@class='dx-checkbox-container']/span")
	List<WebElement> selectRowList;

//	@FindBy(xpath = "//i[@class='dx-icon dx-icon-revert']")
	@FindBy(xpath = "//div[@title='Undo Coverages']//i[@class='dx-icon dx-icon-revert']")
	WebElement undoCoveragesBtn;

	@FindBy(xpath = "//dx-drop-down-box[@id='columnId']//div[@class='dx-dropdowneditor-icon']")
	WebElement selectColumnDD;

	@FindBy(xpath = "//dx-button[@id='btnUndo']//div[@class='dx-button-content']")
	WebElement okUndoCoveragesBtn;

	@FindBy(xpath = "//i[@class='la la-angle-right']")
	WebElement rightBtn;

	@FindBy(xpath = "//i[@class='la la-angle-left']")
	WebElement leftBtn;

	@FindBy(xpath = "//i[@class='la la-check']")
	WebElement applyProrationTypeBtn;

	@FindBy(xpath = "//span[@class='txt_wrap']")
	WebElement wrapText;

	@FindBy(xpath = "//dx-select-box[@placeholder='Select Proration Type']//input[@role='combobox']")
	WebElement prorationTypeDD;

	@FindBy(xpath = "//dx-select-box[@placeholder='Select Basis Type']//div[@class='dx-dropdowneditor-icon']")
	WebElement prorationBasisTypeDD;

	@FindBy(xpath = "//input[@name='BlanketValue']")
	WebElement blanketValueTextbox;

	@FindBy(xpath = "//dx-select-box[@placeholder='Select Category']//div[@class='dx-dropdowneditor-icon']")
	WebElement blanketCategoryDD;

	@FindBy(xpath = "//dx-select-box[@placeholder='Select TIV']//div[@class='dx-dropdowneditor-icon']")
	WebElement blanketTIVDD;

	@FindBy(css = "dx-button[id='button'] span[class='dx-button-text']")
	WebElement runBlanketProrationBtn;

	@FindBy(css = "//dx-number-box[@placeholder='Enter number of locations to duplicate']//input[@role='spinbutton']")
	WebElement spinUpBtn;

	@FindBy(css = "//span[normalize-space()='Apply']")
	WebElement applyLocationsToDuplicate;

	public void applyProrationType() {
		
		elementAct.clickElementByJS(applyProrationTypeBtn, "Apply Proration Type");
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getNumberOfRows(){
		return selectRowList.size();
	}

	public void undoAllCoverages() {
//		Thread.sleep(500);
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

	public void selectProrationTab() {
		String text = wrapText.getText();

		while (!text.equals("ProrationType")) {

			elementAct.clickElementByJS(rightBtn, "Right Button");
			text = wrapText.getText();
		}
	}

	public int getColumnIndex(String columnName) {
		int index = -1;
		String text;
		
//		wait.waitForElementToBeClickable(columnNameList.get(0));
		elementAct.scrollIntoView(columnNameList.get(0));
		for (int i = 0; i < columnNameList.size(); i++) {
			elementAct.scrollIntoView(columnNameList.get(i));
			text = columnNameList.get(i).getText();
			if (text.equalsIgnoreCase(columnName)) {
				index = i;
				break;
			}
		}
		elementAct.scrollIntoView(columnNameList.get(0));
		return index;
	}

	//here rowNum is from which row u want to verify data
	public boolean verifyColumnData(String columnName, String[] data, int rowNum) {
		String text;
		boolean flag = false;

		int index = getColumnIndex(columnName);
		if (index == -1)
			return flag;
		index = index + 2;
		elementAct.scrollIntoView(columnNameList.get(index));
//		System.out.println("INDEX:" + index);
		List<WebElement> columnDataList = driver.findElements(By.xpath(columnDataXpath + "/td[" + index + "]"));
		for (int k = 0; k < data.length; k++) {
			flag = false;
			elementAct.scrollIntoView(columnDataList.get(k + (rowNum - 1)));
			text = columnDataList.get(k + (rowNum - 1)).getText();
			text = text.replace(",", "");
//			System.out.println("APP: " + text);
//			System.out.println("Sheet: " + data[k]);
			if (text.equalsIgnoreCase(data[k]))
				flag = true;
			else if (text.contains(data[k]))
				flag = true;
			if (flag == false) {
//				elementAct.scrollIntoView(columnNameList.get(0));
				return flag;
			}
				

		}
//		elementAct.scrollIntoView(columnNameList.get(0));
		return flag;
	}

	public void selectLocationForPipeline() {
		String text;
		int columnIndex = getColumnIndex("Loc Name");
		elementAct.scrollIntoView(columnNameList.get(columnIndex));
		columnIndex = columnIndex + 2;
		List<WebElement> columnDataList = driver.findElements(By.xpath(columnDataXpath + "/td[" + columnIndex + "]"));
		for (int rowNum = 0; rowNum < columnDataList.size(); rowNum++) {
			text = columnDataList.get(rowNum).getText();
			if (text.equals("Pipeline")) {
				elementAct.scrollIntoView(selectRowList.get(rowNum));
				elementAct.clickElementByJS(selectRowList.get(rowNum), "Selecting Location");
				break;
			}
		}
		elementAct.scrollIntoView(columnNameList.get(0));
	}
//Select 4 location for blanket from 3 to 6 rowNum
	public void selectLocationForBlanketProration() {

		for (int rowNum = 2; rowNum < 6; rowNum++)
		{
			elementAct.clickElementByJS(selectRowList.get(rowNum), "Selecting Location");

		}
	}

	public void selectAreaBasisProration() {
		prorationType = "AreaBasisProration";
		elementAct.selectDropdown(prorationTypeDD, prorationType, prorationType);
		applyProrationType();

	}

	public void selectIncludeAreaSelfBasisProration() {
		selectProrationTab();
		prorationType = "IncludedAreaBasisProration";
		prorationBasisType = "SELF";

		elementAct.selectDropdown(prorationTypeDD, prorationType, prorationType);
		elementAct.selectDropdown(prorationBasisTypeDD, prorationBasisType, prorationBasisType);
		applyProrationType();
	}

	public void selectIncludeAreaDependentBasisProration() {
		selectProrationTab();
		prorationType = "IncludedAreaBasisProration";
		prorationBasisType = "DEPENDENT";

		elementAct.selectDropdown(prorationTypeDD, prorationType, prorationType);
		elementAct.selectDropdown(prorationBasisTypeDD, prorationBasisType, prorationBasisType);
		applyProrationType();
	}

	public void selectLocationSelfBasisProration() {
		selectProrationTab();
		prorationType = "LocationBasis";
		prorationBasisType = "SELF";

		elementAct.selectDropdown(prorationTypeDD, prorationType, prorationType);
		elementAct.selectDropdown(prorationBasisTypeDD, prorationBasisType, prorationBasisType);
		applyProrationType();
	}

	public void selectLocationDependentBasisProration() {
		selectProrationTab();
		prorationType = "LocationBasis";
		prorationBasisType = "DEPENDENT";

		elementAct.selectDropdown(prorationTypeDD, prorationType, prorationType);
		elementAct.selectDropdown(prorationBasisTypeDD, prorationBasisType, prorationBasisType);
		applyProrationType();
	}

	public void performPipelineProration() {
		selectProrationTab();
		prorationType = "PipeLineProration";
		elementAct.selectDropdown(prorationTypeDD, prorationType, prorationType);
		selectLocationForPipeline();
		applyProrationType();
		selectLocationForPipeline();
	}

	public void performIncludeAndLocationBasisProration(String basisType) {
		selectProrationTab();
		prorationType = "Include&LocationBasis";
		prorationBasisType = basisType;
		elementAct.selectDropdown(prorationTypeDD, prorationType, prorationType);
		elementAct.selectDropdown(prorationBasisTypeDD, prorationBasisType, prorationBasisType);
		applyProrationType();
	}

	public void performBlanketProration(String basisType, String prorateValue, String blanketCategory) {
		selectProrationTab();
		prorationType = "BlanketProration";
		prorationBasisType = basisType;
		selectLocationForBlanketProration();

		elementAct.selectDropdown(prorationTypeDD, prorationType, prorationType);
		elementAct.selectDropdown(prorationBasisTypeDD, prorationBasisType, prorationBasisType);
		applyProrationType();

		elementAct.actionSendKeys(blanketValueTextbox, prorateValue);

		elementAct.clickElementByJS(blanketCategoryDD, "Category Dropdown");
		WebElement ddElement = driver.findElement(By.xpath(
				"//div[@class='dx-item-content dx-list-item-content'][normalize-space()='" + blanketCategory + "']"));
		ddElement.click();
//		elementAct.selectDropdown(blanketTIVDD, "TIV Dropdown", "TIV1");
		
		//Handling TIV dropdown
		elementAct.clickElementByJS(blanketTIVDD, "TIV Dropdown Dropdown");
		WebElement ddElementTIV = driver.findElement(By.xpath(
				"//div[@class='dx-item-content dx-list-item-content'][normalize-space()='" + "TIV1" + "']"));
		ddElementTIV.click();

		elementAct.clickElementByJS(runBlanketProrationBtn, "Run Blanket Proration Button");

	}

	public void performConstructionAndOccupancyProration() {
		elementAct.clickElementByJS(leftBtn, "leftBtn");
		selectProrationTab();
		elementAct.clickElementByJS(selectRowList.get(0), "Selecting Row");
		prorationType = "Construction & Occupancy";
		elementAct.selectDDOptions(prorationTypeDD, prorationType, prorationType);
		applyProrationType();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//dx-button[@text='Apply']/div/span")).click();
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		elementAct.clickElementByJS(selectRowList.get(0), "Selecting Row");
	}
	 public void performIFMProration() {
			selectProrationTab();
			prorationType = "IFMProration";
			selectLocationForBlanketProration();
			elementAct.selectDropdown(prorationTypeDD, prorationType, prorationType);
			applyProrationType();
			selectLocationForBlanketProration();
			
	 }

}
