package com.cat.pages;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class LocationDetailsPage extends BaseClass {

	String pageTitle = "Xceedance | Add Account";

	public LocationDetailsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Location Details']")
	WebElement locationDetailsTab;

	@FindBy(xpath = "//a[normalize-space()='Location Master']")
	WebElement LocMasterTab;

	@FindBy(css = "td[class='dx-command-edit dx-command-edit-with-icons'] a[title='Edit Location Row']")
	private WebElement editLocButton;

	@FindBy(xpath = "//div[@title='Column Search']//i[@class='dx-icon dx-icon-search']")
	WebElement colSearchButton;

	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[1]")
	WebElement locationNumberTextbox;

	@FindBy(xpath = "//*[text()='Actions']//following::td[149]")
	WebElement actionsTextbox;

	@FindBy(xpath = "//*[text()='CampusName']//following::td[149]")
	WebElement campusNameTextbox;

	@FindBy(xpath = "//*[text()='BuildingID']//following::td[149]")
	WebElement buildingIDTextbox;

	@FindBy(xpath = "//*[text()='BuildingName']//following::td[149]")
	WebElement buildingNameTextbox;

	@FindBy(xpath = "//*[text()='BuildingType']//following::td[149]")
	WebElement buildingTypeTextbox;

	@FindBy(xpath = "//*[text()='YearBuilt']//following::td[149]")
	WebElement yearBuiltTextbox;

	@FindBy(xpath = "//*[text()='NumberOfStories']//following::td[149]")
	WebElement numberOfStoryTextbox;

	@FindBy(xpath = "//*[text()='NumberOfBuildings']//following::td[149]")
	WebElement numberOfBldTextbox;

	@FindBy(xpath = "//*[text()='FloorArea']//following::td[149]")
	WebElement floorAreaTextbox;

	@FindBy(xpath = "//*[text()='YearUpgraded']//following::td[149]")
	WebElement yearUpgradedTextbox;

	@FindBy(xpath = "//*[text()='OccupancyCode']//following::td[149]")
	WebElement occupancyCodeTextbox;

	@FindBy(xpath = "//*[text()='ConstructionCode']//following::td[149]")
	WebElement constructionCodeTextbox;

	@FindBy(xpath = "//*[text()='SprinklerType']//following::td[149]")
	WebElement sprinklerTypeTextbox;

	@FindBy(xpath = "//*[text()='RoofCover']//following::td[149]")
	WebElement roofCoverTextbox;

	@FindBy(xpath = "//dx-select-box[@id='categoryId']//input[@role='combobox']")
	WebElement catModifierTypeDD;

	@FindBy(xpath = "//dx-select-box[@id='catdataid']//input[@role='combobox']")
	WebElement desModifierTypeDD;

	@FindBy(xpath = "//span[normalize-space()='Save']")
	WebElement SMSaveButton;

	@FindBy(xpath = "//td[@class='dx-command-edit dx-command-edit-with-icons']//a[@title='Save']")
	WebElement saveButton;
	
	@FindBy(xpath = "//dx-button[@title='Previous Tab']")
	WebElement previousTab;

	@FindBy(xpath = "//dx-button[@title='Next Tab']")
	WebElement nextTab;



	public boolean verifyLocationDetailsTab() {
		wait.waitForElementsAttribute(locationDetailsTab, "class", "nav-link p-0 active");
		String[] str = locationDetailsTab.getAttribute("class").split(" ");
		String text = str[2];
		boolean flag = elementAct.verifyText("active", text);
		return flag;
	}
	public int getNumberOfRowsInGrid() {
		return driver.findElements(By.xpath("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]")).size();
	}

	/*
	 * This method search Location Details data based on Location number textbox
	 * 
	 * @param required
	 */
	public void applySearch(String LocID) throws InterruptedException, AWTException {
		elementAct.clickElementByJS(colSearchButton, "Column Search btn");
		elementAct.scrollIntoView(locationNumberTextbox);
		elementAct.enterData(locationNumberTextbox, "Location number", LocID);
		Thread.sleep(2500);
		elementAct.clickElementByJS(colSearchButton, "Column Search btn");
	}

	public void removeAppliedSearch() throws InterruptedException, AWTException {
		elementAct.clickElementByJS(colSearchButton, "Column Search btn");
		Thread.sleep(500);
		elementAct.scrollIntoView(locationNumberTextbox);
		locationNumberTextbox.clear();
//		elementAct.clearElementText(locationNumberTextbox);
		elementAct.clickElementByJS(colSearchButton, "Column Search btn");
		Thread.sleep(1000);
	}

	private void enterModifierData(WebElement Element, String Key, String Value) throws InterruptedException {
		eleAct.moveToAnyElement(Element);
		Element.click();
		Thread.sleep(500);
		catModifierTypeDD.click();
		Thread.sleep(500);
		List<WebElement> modifierType = driver.findElements(By.xpath("//div[text()='" + Key + "']"));
		eleAct.clickElementByJS(modifierType.get(1));
		desModifierTypeDD.click();
		Thread.sleep(500);
		List<WebElement> modifierTypeValue = driver.findElements(By.xpath("//div[text()='" + Value + "']"));
		eleAct.clickElementByJS(modifierTypeValue.get(0));
		Thread.sleep(500);
		SMSaveButton.click();
		Thread.sleep(500);
		logger.info(Key + " : " + Value + " is entered successfully: Passed");
	}


	public void updateLocDetails(String locationID, String campusName, String buildingID, String buildingName,
			String buildingType, String floorArea, String numberOfBuilding, String numberOfStories, String yearBuilt,
			String yearUpgraded, String occupancyCode, String constructionCode, String roofCover, String sprinklerType)
			throws AWTException, InterruptedException {

		applySearch(locationID);

		elementAct.clickElementByJS(editLocButton, "Edit Location Button");
		elementAct.enterDataByJS(campusNameTextbox, "Campus Name", campusName);
		elementAct.enterDataByJS(buildingIDTextbox, "Building ID", buildingID);
		// enterBasicLocData(buildingNameTextbox,"Building Name", buildingName);
		elementAct.enterDataByJS(buildingNameTextbox, "Building Name", buildingName);

		enterModifierData(buildingTypeTextbox, "BuildingType", buildingType);
		Thread.sleep(1000);
		elementAct.scrollIntoView(numberOfStoryTextbox);

		elementAct.enterDataInTextbox(floorAreaTextbox, "Floor Area", floorArea);
		elementAct.enterDataInTextbox(numberOfBldTextbox, "Number Of Building", numberOfBuilding);
		elementAct.enterDataInTextbox(numberOfStoryTextbox, "Number Of Stories", numberOfStories);

		elementAct.scrollIntoView(yearUpgradedTextbox);
		elementAct.enterDataInTextbox(yearBuiltTextbox, "Year Built", yearBuilt);
		elementAct.enterDataInTextbox(yearUpgradedTextbox, "Year Upgraded", yearUpgraded);
		elementAct.scrollIntoView(occupancyCodeTextbox);
		enterModifierData(occupancyCodeTextbox, "OccupancyCode", occupancyCode);
//		Thread.sleep(1000);
		elementAct.scrollIntoView(constructionCodeTextbox);
		enterModifierData(constructionCodeTextbox, "ConstructionCode", constructionCode);
		// elementAct.enterDataInTextbox(constructionCodeTextbox,"ConstructionCode",
		// constructionCode);
//		Thread.sleep(1000);
		elementAct.scrollIntoView(roofCoverTextbox);
		enterModifierData(roofCoverTextbox, "RoofCover", roofCover);
		// elementAct.enterDataInTextbox(roofCoverTextbox,"RoofCover", roofCover);
//		Thread.sleep(1000);

		elementAct.scrollIntoView(sprinklerTypeTextbox);
		enterModifierData(sprinklerTypeTextbox, "SprinklerType", sprinklerType);
		// elementAct.enterDataInTextbox(sprinklerTypeTextbox,"SprinklerType",
		// sprinklerType);
//		Thread.sleep(1000);

		elementAct.clickElement(saveButton, "Save Location Button");
		removeAppliedSearch();

		Thread.sleep(1000);
		// clickLocMasterTab();
		// clickLocDetailTab();
	}

	
	public void clickPreviousTab() {
		elementAct.clickElementByJS(previousTab, "Previous Tab Buton of Policy Layer");
	}

	public void clickNextTab() {
		elementAct.clickElementByJS(nextTab, "Next Tab Buton of Policy Layer");

	}
}
