package com.cat.testCases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cat.pages.BasicAcDetailsPage;
import com.cat.pages.HomePage;
import com.cat.pages.LmsSearchPage;
import com.cat.pages.LocationCoveragesPage;
import com.cat.pages.LocationDetailsPage;
import com.cat.pages.LocationFinancialPage;
import com.cat.pages.LoginPage;
import com.cat.utilities.DataProviderClass;
import com.cat.utilities.Utils;


public class LmsLocationCoveragesTest extends BaseClass {
	static LocationCoveragesPage objLC;
	LoginPage loginPage;
	HomePage homePage;
	LmsSearchPage lmsSearchPage;
	BasicAcDetailsPage basicDetailsPage;
	LocationCoveragesPage locationCoveragesPage;
	Utils utils;
	String lmsAccountName;    // = "ashish_case1_0104";

	// Go to Location Coverages Tab
	@Test(priority = -1, description = "Verify user is able to view LMS- Location Coverages Page")
	public void goToLocationCoveragesTab() {
		loginPage = new LoginPage();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());
		lmsSearchPage = homePage.goToLMS();
		lmsAccountName = readConfig.getDataFromConfig("LMS_AccountName");
		lmsSearchPage.searchAccountByName(lmsAccountName);
		basicDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		locationCoveragesPage = basicDetailsPage.clickLocationCoveragesTab();
		boolean flag = locationCoveragesPage.verifyLocationCoveragesTab();
		Assert.assertTrue(flag);
	}
@Test(priority = 0, description = "Verify column search functionality on Location Coverages")
	public void verifyColumnSearch() {
	String perilName = "All Earthquake";
	String locID = "6";
	locationCoveragesPage.selectPeril(perilName);
	locationCoveragesPage.searchLocationData(locID);
	int numberOfLocations = locationCoveragesPage.getNumberOfLocations();
	locationCoveragesPage.removeAppliedSearch();
	Assert.assertEquals(numberOfLocations, 1);
	}

	
	@Test(priority = 1, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class,
			description = "Verify user is able to modify on Location Coverages")
	public void modifyLocationCoverages(String locID, String buildTIV1, String buildTIV2, String buildTIV3,
			String contTIV1, String contTIV2, String contTIV3, String damagebility1, String damagebility2,
			String damagebility3, String otherTIV1, String otherTIV2, String otherTIV3, String BITIV1, String BITIV2,
			String BITIV3, String BIPOI1, String BIPOI2, String BIPOI3, String waitingPeriod)
			throws InterruptedException, AWTException {

		locationCoveragesPage.update(locID, buildTIV1, buildTIV2, buildTIV3, contTIV1, contTIV2, contTIV3,
				damagebility1, damagebility2, damagebility3, otherTIV1, otherTIV2, otherTIV3, BITIV1, BITIV2, BITIV3,
				BIPOI1, BIPOI2, BIPOI3, waitingPeriod);

//		objLC.updateLocCoverages( locID, buildTIV1, buildTIV2, buildTIV3, contTIV1, contTIV2, contTIV3, damagebility1, 
//				damagebility2, 	damagebility3, 	otherTIV1, otherTIV2, otherTIV3, BITIV1, BITIV2, BITIV3, BIPOI1, BIPOI2,
//				 BIPOI3,  waitingPeriod);
		// objLC.addCovDatailofPeril();
		// objLC.deleteCovDatailofPeril();
	}

	/*
	 * This method adds location data for selected peril peril is defined in test
	 * method not taken from excel
	 */
	
	@Test(priority = 3, description = "verify user is able to add location coverages", dependsOnMethods = "deleteLocationCoverages")
	public void addLocationCoverages() throws InterruptedException {
		String perilName = "All Earthquake";
		locationCoveragesPage.addCoveragesData(perilName);
		int numberOfLocations = locationCoveragesPage.getNumberOfLocations();

		if (numberOfLocations > 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	/*
	 * This method deletes location data for selected peril peril is defined in test
	 * method not taken from excel
	 */
	@Test(priority = 2, description = "verify user is able to delete location coverages")
	public void deleteLocationCoverages() throws InterruptedException {
		String perilName = "All Earthquake";
		locationCoveragesPage.deleteCoveragesData(perilName);
		Thread.sleep(500);
		
		int numberOfLocations = locationCoveragesPage.getNumberOfLocations();
		Assert.assertEquals(numberOfLocations, 0);
	}

	@Test(priority = 4, description = "Verify the functionality of column chooser on Location Coverages")
	public void  verifyColumnChooserOnLocationCoverages() {
		utils = new Utils();
		String[] columnNames = {"LocationNumber", "Address","Currency"};
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length);
		
	}

	@Test(priority = 5, description = "Verify the functionality of Previous icon on Location Coverages")
	public void verifyPreviousTabLocationCoverages() {
		locationCoveragesPage.clickPreviousTab();
		boolean flag = new LocationDetailsPage().verifyLocationDetailsTab();
		locationCoveragesPage.clickNextTab();
		Assert.assertTrue(flag);
	}

	@Test(priority = 6, description = "Verify the functionality of Next icon on Location Coverages")
	public void verifyNextTabLocationCoverages() {
		locationCoveragesPage.clickNextTab();
		boolean flag = new LocationFinancialPage().verifyLocationFinancialTab();
		locationCoveragesPage.clickPreviousTab();
		Assert.assertTrue(flag);
	}
}
