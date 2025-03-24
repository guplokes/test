package com.cat.testCases;

import java.awt.AWTException;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.cat.pages.BasicAcDetailsPage;
import com.cat.pages.HomePage;
import com.cat.pages.LmsSearchPage;
import com.cat.pages.LocationDetailsPage;
import com.cat.pages.LocationMasterPage;
import com.cat.pages.LoginPage;
import com.cat.pages.PerilLevelFinancialDetailsPage;
import com.cat.utilities.DataProviderClass;
import com.cat.utilities.ReadExcel;
import com.cat.utilities.Utils;

public class LmsLocationMasterTest extends BaseClass {
	
	
	
	LoginPage loginPage;
	HomePage homePage;
	LmsSearchPage lmsSearchPage;
	BasicAcDetailsPage basicDetailsPage;
	LocationMasterPage locationMasterPage;
	Utils utils;
	ReadExcel readExcel = new ReadExcel("TestData");
	String lmsAccountName;   //= "ashish_case1_0104";

	
	//Go to Location Master Tab
	@Test(priority = -1, description = "Verify user is able to view LMS- Location Master Page")
	public void goToLocationMasterTab() {
		loginPage = new LoginPage();
		utils = new Utils();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());
		lmsSearchPage = homePage.goToLMS();
		lmsAccountName = readConfig.getDataFromConfig("LMS_AccountName");
		lmsSearchPage.searchAccountByName(lmsAccountName);
		basicDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		locationMasterPage = basicDetailsPage.clickLocationMasterTab();
		boolean flag = locationMasterPage.verifyLocationMasterTab();
		Assert.assertTrue(flag);
	}

	
	@Test(priority = 1, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class
			, description = "Verify user is able to add location on Location Master")
	public void addLocationMasterData(String locName, String inceptionDate, String expiryDate, String streetAddress, String	city,
			String area, String areaCode, String	state,	String stateCode, String postalCode, String county, String countryCode,
			String 	currencyCode,  String	latitude,	String longitude, String countyCode, String cityCode,	String cresta,
			String	subCresta, String muncipality, String muncipalityCode, String	postalName, String district, 
			String districtCode, String geocodeLevel, String geocodeConfidence, String geocoder, String geogName1, String geogScheme1
			, String userDef1, String userDef2, String userDef3, String userDef4, String userDef5, String userDef6 )
					throws InterruptedException, AWTException {
		
		locationMasterPage.addLocationData(locName, inceptionDate, expiryDate, streetAddress, city,
				area, areaCode, state, stateCode, postalCode, county, countryCode, currencyCode, latitude, longitude,
				countyCode, cityCode, cresta, subCresta, muncipality, muncipalityCode, postalName, district, districtCode,
				geocodeLevel, geocodeConfidence, geocoder, geogName1, geogScheme1, userDef1, userDef2, userDef3, userDef4, 
				userDef5, userDef6);
	
		boolean flag = utils.isSuccess();
		Assert.assertTrue(flag);	

	}
	
	

	@Test(priority = 2, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class
			, description = "Verify user is able to modify/search location on Location Master")
	public void modifyLocationMasterData(String locName, String inceptionDate, String expiryDate, String streetAddress,
			String city, String area, String areaCode, String state, String stateCode, String postalCode, String county,
			String countryCode, String currencyCode, String latitude, String longitude, String countyCode,
			String cityCode, String cresta, String subCresta, String muncipality, String muncipalityCode,
			String postalName, String district, String districtCode, String geocodeLevel, String geocodeConfidence,
			String geocoder, String geogName1, String geogScheme1, String userDef1, String userDef2, String userDef3,
			String userDef4, String userDef5, String userDef6) {
		locationMasterPage.clickDisplay20Items();

		locationMasterPage.locationNameSearch(locName);

		locationMasterPage.modifyLocationData(inceptionDate, expiryDate, streetAddress, city, area, areaCode,
				state, stateCode, postalCode, county, countryCode, currencyCode, latitude, longitude, countyCode,
				cityCode, cresta, subCresta, muncipality, muncipalityCode);

		locationMasterPage.removeAppliedSearch();

	}
	
	@Test(priority = 3, description = "Verify user is able to delete location on Location Master")
	public void deleteLocationMasterData() throws InterruptedException, AWTException
	{
		
		//locationMasterPage.clickDisplay20Items();	
		String locationName = readExcel.getStringData("addLocationMasterData", 2, 0);
		locationMasterPage.deleteLocationData(locationName);		
		boolean flag = utils.isSuccess();
		Assert.assertTrue(flag);
		locationName = readExcel.getStringData("addLocationMasterData", 1, 0);
		locationMasterPage.deleteLocationData(locationName);		
		flag = utils.isSuccess();
		Assert.assertTrue(flag);
		
}
	
	
	@Test(priority = 4, description = "Verify functionality of Column Chooser on Location Master")
	public void verifyColumnChooserOnLocationMaster() {
		String[] columnNames = {"InceptionDate", "LocationName", "City", "StreetAddress", "PostalName"};
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
//		pLFinancialDetailsPage.clickUndoBtn();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length);
		
	}
	
	@Test(priority = 5, description = "Verify the functionality of Previous icon on Location Master")
	public void verifyPreviousTabLocationMaster() {
		locationMasterPage.clickPreviousTab();
		boolean flag = new PerilLevelFinancialDetailsPage().verifyPerilLevelFinancialDetailsTab();
		locationMasterPage.clickNextTab();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 6, description = "Verify the functionality of Previous icon on Location Master")
	public void verifyNextTabLocationMaster() {
		locationMasterPage.clickNextTab();
		boolean flag = new LocationDetailsPage().verifyLocationDetailsTab();
		locationMasterPage.clickPreviousTab();
		Assert.assertTrue(flag);
	}
		
}
