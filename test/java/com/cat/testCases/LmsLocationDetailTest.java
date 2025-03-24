package com.cat.testCases;

import java.awt.AWTException;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.cat.pages.BasicAcDetailsPage;
import com.cat.pages.HomePage;
import com.cat.pages.LmsSearchPage;
import com.cat.pages.LocationCoveragesPage;
import com.cat.pages.LocationDetailsPage;
import com.cat.pages.LocationMasterPage;
import com.cat.pages.LoginPage;
import com.cat.utilities.DataProviderClass;
import com.cat.utilities.Utils;

public class LmsLocationDetailTest extends BaseClass {
	LoginPage loginPage;
	HomePage homePage;
	LmsSearchPage lmsSearchPage;
	BasicAcDetailsPage basicDetailsPage;
	LocationDetailsPage locationDetailsPage;
//	String lmsAccountName = "ashish_case1_0104";
	String lmsAccountName;
	Utils utils;

	
	//Go to Location Details Tab
	@Test(priority = -1, description = "Verify user is able to view LMS- Location Details Page")
	public void goToLocationDetailsTab() {
		loginPage = new LoginPage();
		utils = new Utils();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());
		lmsSearchPage = homePage.goToLMS();
		lmsAccountName = readConfig.getDataFromConfig("LMS_AccountName");
		lmsSearchPage.searchAccountByName(lmsAccountName);
		basicDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		locationDetailsPage = basicDetailsPage.clickLocationDetailsTab();
		boolean flag = locationDetailsPage.verifyLocationDetailsTab();
		Assert.assertTrue(flag);
	}

	
	/*
	 * This test method validates both search and edit location details data
	 * functionality
	 * It reads sata from excel
	 */
	@Test(priority = 0, description = "Verify user is able to search location on Location Details")
	public void verifyColumnSearch() throws InterruptedException, AWTException{
		String locNumber = "6";
		locationDetailsPage.applySearch(locNumber);
		int numberOfRows = locationDetailsPage.getNumberOfRowsInGrid();
		locationDetailsPage.removeAppliedSearch();
		Assert.assertEquals(numberOfRows, 1);
	}
	
	@Test(priority = 1, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class,
			description = "Verify user is able to edit location on Location Details")
	public void modifyLocationDetails(String locationID, String campusName,String buildingID, String buildingName,
			String buildingType,  String floorArea, String numberOfBuilding, String numberOfStories, String yearBuilt, String yearUpgraded,
			String occupancyCode, String constructionCode, String	roofCover, String sprinklerType
			) throws InterruptedException, AWTException {	
		
		
		locationDetailsPage.updateLocDetails( locationID,  campusName, buildingID,  buildingName,
				 buildingType,   floorArea,  numberOfBuilding,  numberOfStories,  yearBuilt,  yearUpgraded,
				 occupancyCode,  constructionCode,	
					roofCover,  sprinklerType);
	}
	@Test(priority = 2, description = "Verify the functionality of column chooser on Location Details")
	public void  verifyColumnChooserOnLocationDetails() {
		utils = new Utils();
		String[] columnNames = {"LocationNumber", "CampusName","YearBuilt", "SprinklerType"};
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length);
		
	}
	
	@Test(priority = 3, description = "Verify the functionality of Previous icon on Location Details")
	public void verifyPreviousTabLocationDetails() {
		locationDetailsPage.clickPreviousTab();
		boolean flag = new LocationMasterPage().verifyLocationMasterTab();
		locationDetailsPage.clickNextTab();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 4, description = "Verify the functionality of Next icon on Location Details")
	public void verifyNextTabLocationDetails() {
		locationDetailsPage.clickNextTab();
		boolean flag = new LocationCoveragesPage().verifyLocationCoveragesTab();
		locationDetailsPage.clickPreviousTab();
		Assert.assertTrue(flag);
	}
		

}
