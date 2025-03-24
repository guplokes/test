package com.cat.pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class LocationMasterPage extends BaseClass{
	
	String pageTitle = "Xceedance | Add Account";

	public LocationMasterPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h5[normalize-space()='Location']")
	WebElement locationTab;
	
	@FindBy(xpath = "//a[normalize-space()='Location Master']")
	WebElement locationMasterTab;
	
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-add']")
	WebElement addLocationBtn;
	
	@FindBy(xpath = "//div[@title='Column Search']//div[@class='dx-button-content']")
	WebElement colSearchButton;
	
//	@FindBy(xpath = "//div[@aria-label='Display 20 items on page']")
	@FindBy(xpath = "//div[@class='dx-show-invalid-badge dx-selectbox dx-textbox dx-texteditor dx-dropdowneditor-button-visible dx-editor-outlined dx-widget dx-dropdowneditor dx-dropdowneditor-field-clickable']//div[@class='dx-dropdowneditor-icon']")
	WebElement display20Items;
	
	@FindBy(xpath = "//div[contains(text(),'Success')]")
	WebElement successMsg;
	
	@FindBy(xpath = "//div[@title='Expand Screen']//i[@class='dx-icon dx-icon-expand']")
	WebElement expandScreenButton;
	
	//previously td[47], after deployment td[48]
	@FindBy(xpath = "//*[text()='LocationName']//following::td[48]")
	WebElement locNameTextbox;
	
	@FindBy(xpath = "//*[text()='InceptionDate']//following::td[48]")
	WebElement inceptionDateCAL;
	
	@FindBy(xpath = "//*[text()='ExpiryDate']//following::td[48]")
	WebElement expiryDateCAL;
	
	@FindBy(xpath = "//*[text()='StreetAddress']//following::td[48]")
	WebElement streetAddressTextbox;
	
	@FindBy(xpath = "//*[text()='PostalName']//following::td[48]")
	WebElement postalNameTextbox;
	
	@FindBy(xpath = "//*[text()='PostalCode']//following::td[48]")
	WebElement postalCodeTextbox;
	
	@FindBy(xpath = "//*[text()='Muncipality']//following::td[48]")
	WebElement muncipalityTextbox;
	
	@FindBy(xpath = "//*[text()='MuncipalityCode']//following::td[48]")
	WebElement muncipalityCodeTextbox;
	
	@FindBy(xpath = "//*[text()='City']//following::td[48]")
	WebElement cityTextbox;
	
	@FindBy(xpath = "//*[text()='CityCode']//following::td[48]")
	WebElement cityCodeTextbox;
	
	@FindBy(xpath = "//*[text()='District']//following::td[48]")
	WebElement districtTextbox;
	
	@FindBy(xpath = "//*[text()='DistrictCode']//following::td[48]")
	WebElement districtCodeTextbox;
	
	@FindBy(xpath = "//*[text()='County']//following::td[48]")
	WebElement countyTextbox;
	
	@FindBy(xpath = "//*[text()='CountyCode']//following::td[48]")
	WebElement countyCodeTextbox;
	
	@FindBy(xpath = "//*[text()='State']//following::td[48]")
	WebElement stateTextbox;
	
	@FindBy(xpath = "//*[text()='StateCode']//following::td[48]")
	WebElement stateCodeTextbox;
	
	@FindBy(xpath = "//*[text()='Area']//following::td[48]")
	WebElement areaTextbox;
	
	@FindBy(xpath = "//*[text()='AreaCode']//following::td[48]")
	WebElement areaCodeTextbox;
	
	@FindBy(xpath = "//*[text()='CountryCode']//following::td[48]")
	WebElement countryCodeTextbox;
	
	@FindBy(xpath = "//*[text()='Latitude']//following::td[48]")
	WebElement latitudeTextbox;
	
	@FindBy(xpath = "//*[text()='Longitude']//following::td[48]")
	WebElement longitudeTextbox;
	
	@FindBy(xpath = "//*[text()='Cresta']//following::td[48]")
	WebElement crestaTextbox;
	
	@FindBy(xpath = "//*[text()='SubCresta']//following::td[48]")
	WebElement subCrestaTextbox;
	
	@FindBy(xpath = "//*[text()='Geocode_Level']//following::td[48]")
	WebElement geocodeLvlTextbox;
	
	@FindBy(xpath = "//*[text()='Geocoder']//following::td[48]")
	WebElement geocoderTextbox;
	
	@FindBy(xpath = "//*[text()='GeocodeConfidence']//following::td[48]")
	WebElement geocodeConfTextbox;
	
	@FindBy(xpath = "//*[text()='GeogScheme1']//following::td[48]")
	WebElement geogSch1Textbox;
	
	@FindBy(xpath = "//*[text()='GeogName1']//following::td[48]")
	WebElement geogName1Textbox;
	
	@FindBy(xpath = "//*[text()='CurrencyCode']//following::td[48]")
	WebElement currencyCodeTextbox;
	
	@FindBy(xpath = "//*[text()='LocUserDef1']//following::td[48]")
	WebElement locUDF1Textbox;
	
	@FindBy(xpath = "//*[text()='LocUserDef2']//following::td[48]")
	WebElement locUDF2Textbox;
	
	@FindBy(xpath = "//*[text()='LocUserDef3']//following::td[48]")
	WebElement locUDF3Textbox;
	
	@FindBy(xpath = "//*[text()='LocUserDef4']//following::td[48]")
	WebElement locUDF4Textbox;
	
	@FindBy(xpath = "//*[text()='LocUserDef5']//following::td[48]")
	WebElement locUDF5Textbox;
	
	@FindBy(xpath = "//*[text()='LocUserDef6']//following::td[48]")
	WebElement locUDF6Textbox;
	
	
	@FindBy(xpath = "//td[@class='dx-command-edit dx-command-edit-with-icons']//a[@title='Save']")
	WebElement saveIcon;
	
	//for all delete icons showing on the screen
	@FindBy(css = "td[class='dx-command-edit dx-command-edit-with-icons'] a[title='Delete']")
	WebElement deleteButton;
	
	@FindBy(css = "td[class='dx-command-edit dx-command-edit-with-icons'] a[title='Edit']")
	WebElement editBtn;
	
	@FindBy(xpath = "//span[normalize-space()='Yes']")
	WebElement cNFYesToDelete;
	
	@FindBy(xpath = "//*[text()='LocationNumber']//following::td[48]")
	WebElement LocNumberTextbox;
	
	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[2]")
	WebElement locationNameSearchBox;
	
	@FindBy(xpath = "//dx-button[@title='Previous Tab']")
	WebElement previousTab;

	@FindBy(xpath = "//dx-button[@title='Next Tab']")
	WebElement nextTab;
	
	
	public boolean verifyLocationMasterTab() {
		wait.waitForElementsAttribute(locationMasterTab, "class", "nav-link p-0 active");
		String[] str = locationMasterTab.getAttribute("class").split(" ");
		String text = str[2];
		boolean flag = elementAct.verifyText("active", text);
		return flag;
	}

	/*
	 * this method will return number of locations only on current location master
	 * page(like 5,10 or 20 as selected)
	 */
	public int getNumberOfLocations() {
		int size = driver.findElements(By.xpath("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]"))
				.size(); //td[2] not working
		return size;
	}
	
	public void clickDisplay20Items() {
		elementAct.scrollIntoView(display20Items);
			elementAct.clickElementByJS(display20Items, "display 20 items on page");
			driver.findElement(By.xpath("//div[text()='20']")).click();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	public void clickLocMasterTab() throws InterruptedException {
		elementAct.clickElement(locationTab, "Location Tab");
	}
	
	/*
	 * private void selectInceptionDate(String inceptionDateValue) throws
	 * AWTException, InterruptedException {
	 * eleAct.moveToAnyElement(inceptionDateCAL);
	 * eleAct.actionSendKeys(inceptionDateCAL, inceptionDateValue);
	 * logger.info("Inception Date :  , "+ inceptionDateValue
	 * +" is selected successfully: Passed"); }
	 * 
	 * private void selectExpiryDate(String expiryDateValue) throws AWTException,
	 * InterruptedException { eleAct.moveToAnyElement(expiryDateCAL);
	 * eleAct.actionSendKeys(expiryDateCAL, expiryDateValue);
	 * logger.info("Expiry Date :  , "+ expiryDateValue
	 * +" is selected successfully: Passed"); }
	 */

	/*
	 * This method searches the data by using location name column Search Mainly
	 * used for validation purpose
	 */
	public void locationNameSearch(String locationName) {
		
		elementAct.clickElementByJS(colSearchButton, "Col Search Button");
		elementAct.scrollIntoView(locationNameSearchBox);
		elementAct.enterData(locationNameSearchBox, "location to delete", locationName);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.scrollIntoView(colSearchButton);
		elementAct.clickElementByJS(colSearchButton, "Col Search Button");
		
	}
	public void removeAppliedSearch() {
		elementAct.clickElementByJS(colSearchButton, "Col Search Button");
		elementAct.scrollIntoView(locationNameSearchBox);
		//elementAct.clearElementText(locationNameSearchBox);
		locationNameSearchBox.clear();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			elementAct.scrollIntoView(colSearchButton);
			elementAct.clickElementByJS(colSearchButton, "Col Search Button");
	}
	public void deleteLocationData(String locationName) throws InterruptedException, AWTException
	{
		

		locationNameSearch(locationName);
//		Thread.sleep(1000);
		elementAct.clickElementByJS(deleteButton, "Delete Location Button");
		elementAct.clickElement(cNFYesToDelete, "CNF Yes To Delete");
		Thread.sleep(500);
		removeAppliedSearch();

		
	}
	
//	public boolean isSuccess() {
//
//		wait.waitForElement(successMsg);
//		return successMsg.isDisplayed();
//
//	}
	
	
	public void addLocationData(String locName, String inceptionDate, String expiryDate, String streetAddress, String	city,
			String area, String areaCode, String	state,	String stateCode, String postalCode, String county, String countryCode,
			String 	currencyCode,  String	latitude,	String longitude, String countyCode, String cityCode,	String cresta,
			String	subCresta, String muncipality, String muncipalityCode, String	postalName, String district, 
			String districtCode, String geocodeLevel, String geocodeConfidence, String geocoder, String geogName1, String geogScheme1
			, String userDef1, String userDef2, String userDef3, String userDef4, String userDef5, String userDef6) throws AWTException, InterruptedException {
		
		elementAct.clickElement(addLocationBtn, "Add Location Button");
		elementAct.enterDataByJS(locNameTextbox, "Location Name", locName);
		//elementAct.enterData(locationNameSearchBox, "Location Name", locName);
		
//		selectInceptionDate(inceptionDate);
//		selectExpiryDate(expiryDate);
		
		elementAct.enterDataByJS(inceptionDateCAL, "Inception Date", inceptionDate);
		elementAct.enterDataByJS(expiryDateCAL, "Expiry Date", expiryDate);
		elementAct.scrollIntoView(cityTextbox);
		
		elementAct.enterDataByJS(streetAddressTextbox, "Street Address", streetAddress);
		elementAct.enterDataByJS(cityTextbox, "City",city);
		elementAct.enterDataAfterScroll(areaTextbox, "Area", area);
		elementAct.enterDataAfterScroll(areaCodeTextbox, "Area Code", areaCode);
		elementAct.enterDataAfterScroll(stateTextbox, "State", state);
		elementAct.enterDataAfterScroll(stateCodeTextbox, "State Code", stateCode);
		elementAct.enterDataAfterScroll(postalCodeTextbox, "Postal Code", postalCode);
		elementAct.enterDataAfterScroll(countyTextbox, "County", county);
		elementAct.enterDataAfterScroll(countryCodeTextbox, "Country Code", countryCode);
		elementAct.enterDataAfterScroll(currencyCodeTextbox, "Currency Code", currencyCode);
		elementAct.enterDataAfterScroll(latitudeTextbox, "Latitude",latitude);
		elementAct.enterDataAfterScroll(longitudeTextbox, "Longitude", longitude);
		elementAct.enterDataAfterScroll(countyCodeTextbox, "County Code", countyCode);
		elementAct.enterDataAfterScroll(cityCodeTextbox, "City Code", cityCode);
		elementAct.enterDataAfterScroll(crestaTextbox, "Cresta", cresta);
		elementAct.enterDataAfterScroll(subCrestaTextbox, "Sub Cresta", subCresta);
		elementAct.enterDataAfterScroll(muncipalityTextbox, "Muncipality", muncipality);
		elementAct.enterDataAfterScroll(muncipalityCodeTextbox, "Muncipality Code", muncipalityCode);
		elementAct.enterDataAfterScroll(postalNameTextbox, "Postal Name", postalName);
		elementAct.enterDataAfterScroll(districtTextbox, "District", district);
		elementAct.enterDataAfterScroll(districtCodeTextbox, "District Code", districtCode);
		elementAct.enterDataAfterScroll(geocodeLvlTextbox, "Geocode Level", geocodeLevel);
		elementAct.enterDataAfterScroll(geocodeConfTextbox, "Geocode Confidence", geocodeConfidence);
		elementAct.enterDataAfterScroll(geocoderTextbox, "Geocoder", geocoder);
		elementAct.enterDataAfterScroll(geogName1Textbox, "Geog Name 1", geogName1);
		elementAct.enterDataAfterScroll(geogSch1Textbox, "Geog Scheme 1", geogScheme1);
		elementAct.enterDataAfterScroll(locUDF1Textbox, "UserDef1", userDef1);
		elementAct.enterDataAfterScroll(locUDF2Textbox, "UserDef2", userDef2);
		elementAct.enterDataAfterScroll(locUDF3Textbox, "UserDef3", userDef3);
		elementAct.enterDataAfterScroll(locUDF4Textbox, "UserDef4", userDef4);
		elementAct.enterDataAfterScroll(locUDF5Textbox, "UserDef5", userDef5);
		elementAct.enterDataAfterScroll(locUDF6Textbox, "UserDef6", userDef6);
		elementAct.clickElement(saveIcon, "Save Button");
		//Thread.sleep(4500);		
	}
	
	public void modifyLocationData(String inceptionDate, String expiryDate, String streetAddress, String	city,
			String area, String areaCode, String	state,	String stateCode, String postalCode, String county, String countryCode,
			String 	currencyCode,  String	latitude,	String longitude, String countyCode, String cityCode,	String cresta,
			String	subCresta, String muncipality, String muncipalityCode)  {

		//elementAct.clickElement(colSearchButton, "column search Btn");		
		
		elementAct.clickElement(editBtn, "Edit Location Data");
		
		elementAct.clearElementText(inceptionDateCAL);
		elementAct.enterDataByJS(inceptionDateCAL, "Inception Date", inceptionDate);
		elementAct.clearElementText(expiryDateCAL);
		elementAct.enterDataByJS(expiryDateCAL, "Expiry Date", expiryDate);
		
		elementAct.enterDataInTextbox(streetAddressTextbox, "Street Address", streetAddress);
		elementAct.enterDataInTextbox(cityTextbox, "City",city);
		elementAct.scrollIntoView(stateCodeTextbox);
		elementAct.enterDataInTextbox(areaTextbox, "Area", area);
		elementAct.enterDataInTextbox(areaCodeTextbox, "Area Code", areaCode);
		elementAct.enterDataInTextbox(stateTextbox, "State", state);
		elementAct.enterDataInTextbox(stateCodeTextbox, "State Code", stateCode);
		elementAct.scrollIntoView(longitudeTextbox);		
		elementAct.enterDataInTextbox(postalCodeTextbox, "Postal Code", postalCode);
		elementAct.enterDataInTextbox(countyTextbox, "County", county);
		elementAct.enterDataInTextbox(countryCodeTextbox, "Country Code", countryCode);
		elementAct.enterDataInTextbox(currencyCodeTextbox, "Currency Code", currencyCode);
		elementAct.enterDataInTextbox(latitudeTextbox, "Latitude",latitude);
		elementAct.enterDataInTextbox(longitudeTextbox, "Longitude", longitude);
		elementAct.scrollIntoView(muncipalityCodeTextbox);
		elementAct.enterDataInTextbox(countyCodeTextbox, "County Code", countyCode);
		elementAct.enterDataInTextbox(cityCodeTextbox, "City Code", cityCode);
		elementAct.enterDataInTextbox(crestaTextbox, "Cresta", cresta);
		elementAct.enterDataInTextbox(subCrestaTextbox, "Sub Cresta", subCresta);
		elementAct.enterDataInTextbox(muncipalityTextbox, "Muncipality", muncipality);
		elementAct.enterDataInTextbox(muncipalityCodeTextbox, "Muncipality Code", muncipalityCode);		
		
		elementAct.clickElement(saveIcon, "Save Button");
//		removeAppliedSearch();
	}
	
	public void clickPreviousTab() {

		elementAct.clickElementByJS(previousTab, "Previous Tab Buton of Policy Layer");

	}

	public void clickNextTab() {
		elementAct.clickElementByJS(nextTab, "Next Tab Buton of Policy Layer");

	}
	
}

