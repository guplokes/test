package com.cat.testCases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cat.pages.BasicAcDetailsPage;
import com.cat.pages.HomePage;
import com.cat.pages.LmsSearchPage;
import com.cat.pages.LocationMasterPage;
import com.cat.pages.LoginPage;
import com.cat.pages.PerilLevelFinancialDetailsPage;
import com.cat.pages.PolicyBlanketDetailsPage;
import com.cat.utilities.DataProviderClass;
import com.cat.utilities.Utils;


public class LmsPerilLevelFinancialTest extends BaseClass {

	PerilLevelFinancialDetailsPage pLFinancialDetailsPage;
	LoginPage loginPage;
	HomePage homePage;
	LmsSearchPage lmsSearchPage;
	BasicAcDetailsPage basicDetailsPage;
	Utils utils;
	String lmsAccountName;   // = "ashish_case1_0104";

	// Go to Peril Level Financial Details Tab
	@Test(priority = -1, description = "Verify user is able to view LMS- Peril Level Financial Details Page")
	public void goToPerilLevelFinancialDetailsTab() {
		loginPage = new LoginPage();
		utils = new Utils();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());
		lmsSearchPage = homePage.goToLMS();
		lmsAccountName = readConfig.getDataFromConfig("LMS_AccountName");
		lmsSearchPage.searchAccountByName(lmsAccountName);
		basicDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pLFinancialDetailsPage = basicDetailsPage.clickPerilLevelFinancialDetailsTab();
		boolean flag = pLFinancialDetailsPage.verifyPerilLevelFinancialDetailsTab();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 1, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class,
			description = "Verify user is able to add location group on peril level financial")
	public void addLocationGroup(String locGroupName, String fieldName, String operator, String value,
			String conditionOperator) throws InterruptedException, AWTException {

		pLFinancialDetailsPage.addLocationGroup(locGroupName, fieldName, operator, value, conditionOperator);
		boolean flag = utils.isError();
		if (flag) {
			pLFinancialDetailsPage.clickCloseBtn();
		Assert.assertTrue(flag);
		}
		else
			Assert.assertFalse(flag);
	}

	@Test(priority = 2, description = "Verify  View Group Criteria Button with invalid values on Peril Level Financial")
	public void verifyViewGroupCriteriaBtnWithoutSelectingLocGrp() {
		pLFinancialDetailsPage.clickOnViewGroupCriteriaBtn();
		boolean result = utils.isWarning();
		Assert.assertTrue(result);
	}

	@Test(priority = 2, description = "Verify  Check Group Locations Button with invalid values on Peril Level Financial")
	public void verifyCheckGroupLocationWithoutSelectingLocGrp() {
		pLFinancialDetailsPage.clickOnCheckGroupLocationsBtn();
		boolean result = utils.isWarning();
		Assert.assertTrue(result);
	}

	@Test(priority = 3, description = "Verify  View Group Criteria Button on Peril Level Financial")
	public void verifyViewGroupCriteriaBtn() {
		String locationGroup = objExcl.getStringData("addPerilLevelFinancialDetails", 1, 1);
		String expectedText = "Location Group Criteria";
		pLFinancialDetailsPage.selectLocationGroup(locationGroup);
		pLFinancialDetailsPage.clickOnViewGroupCriteriaBtn();
		boolean result = pLFinancialDetailsPage.verifyPopupTitle(expectedText);
		Assert.assertTrue(result);
	}

	@Test(priority = 3, description = "Verify  Check Group Locations Button Peril Level Financial")
	public void verifyCheckGroupLocationsBtn() {
		String locationGroup = objExcl.getStringData("addPerilLevelFinancialDetails", 1, 1);
		String expectedText = "Location Group Locations";
		pLFinancialDetailsPage.selectLocationGroup(locationGroup);
		pLFinancialDetailsPage.clickOnCheckGroupLocationsBtn();
		boolean result = pLFinancialDetailsPage.verifyPopupTitle(expectedText);
		Assert.assertTrue(result);
	}

	/*
	 * This test
	 */


	@Test(priority = 4, description = "Verify functionality of column search on Peril Level Financial")
	public void verifyColumnSearch() throws InterruptedException {
		String perilName = objExcl.getStringData("addPerilLevelFinancialDetails", 1, 0);
		pLFinancialDetailsPage.applyColumnSearch(perilName);
		Thread.sleep(2000);
		int numberOfLayers = pLFinancialDetailsPage.getNumberOfSavedPerilLevelFinancialLayers();
		pLFinancialDetailsPage.removeColumnSearch();
		Assert.assertEquals(numberOfLayers, 1);
	}

	@Test(priority = 5, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class, description = "Modify data of Peril Level Financial Details Layer")
	public void editPerilLevelFinancialDetails(String perilName, String layerName, String limitCode, String limitBasis,
			String limitType, String sublimit, String sublimitPart, String sublimitAttachment, String deductible,
			String deductibleCode, String deductibleType, String deductibleBasis, String minDeductible,
			String maxDeductible) {
		pLFinancialDetailsPage.applyColumnSearch(perilName);
		pLFinancialDetailsPage.editPerilLevelFinancialData(layerName, limitCode, limitBasis, limitType, sublimit,
				sublimitPart, sublimitAttachment, deductible, deductibleCode, deductibleType, deductibleBasis,
				minDeductible, maxDeductible);
		pLFinancialDetailsPage.removeColumnSearch();
	}

	/*
	 * This test reads data from excel and adds a new layer to Peril Level Financial
	 * Details Page
	 */
	@Test(priority = 6, description = "Verify Add Peril Level Financial details without selecting Peril on Peril Level Financial")
	public void addPerilLevelFinancialDetailsWithInvalidValues() {
		pLFinancialDetailsPage.clickOnAddPerilFinancialDetailsBtn();
		boolean result = utils.isWarning();
		Assert.assertTrue(result);
	}

	@Test(priority = 7, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class, description = "Add Peril Level Financial Details")
	public void addPerilLevelFinancialDetails(String perilName, String locGrpName, String layerName, String limitCode,
			String limitBasis, String limitType, String sublimit, String sublimitPart, String sublimitAttachment,
			String deductible, String deductibleCode, String deductibleType, String deductibleBasis,
			String minDeductible, String maxDeductible) {

		int numberOfLayersBefore = pLFinancialDetailsPage.getNumberOfSavedPerilLevelFinancialLayers();
		pLFinancialDetailsPage.addPerilLevelFinancialData(perilName, locGrpName, layerName, limitCode, limitBasis,
				limitType, sublimit, sublimitPart, sublimitAttachment, deductible, deductibleCode, deductibleType,
				deductibleBasis, minDeductible, maxDeductible);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numberOfLayersAfter = pLFinancialDetailsPage.getNumberOfSavedPerilLevelFinancialLayers();
		Assert.assertEquals(numberOfLayersAfter, numberOfLayersBefore + 1);

	}

	@Test(priority = 8, description = "Verify sublimit & deductible textbox with invalid values on Peril Level Financial")
	public void validateTextboxWithInvalidValues() {
		boolean result = false;
		String perilName = objExcl.getStringData("addPerilLevelFinancialDetails", 1, 0);
		String locationGroup = objExcl.getStringData("addPerilLevelFinancialDetails", 1, 1);
		String sublimit = "=" + objExcl.getStringData("addPerilLevelFinancialDetails", 1, 6);
		String deductible = "+" + objExcl.getStringData("addPerilLevelFinancialDetails", 1, 9);
		String minDeductible = "=" + objExcl.getStringData("addPerilLevelFinancialDetails", 1, 13);
		pLFinancialDetailsPage.selectPerilAndLocGroup(perilName, locationGroup);
		pLFinancialDetailsPage.clickOnAddPerilFinancialDetailsBtn();
		pLFinancialDetailsPage.enterSublimit(sublimit);
		result = utils.isError();
		Assert.assertTrue(result);
		pLFinancialDetailsPage.enterDeductible(deductible);
		result = utils.isError();
		Assert.assertTrue(result);
		pLFinancialDetailsPage.enterMinDeductible(minDeductible);
		result = utils.isError();
		Assert.assertTrue(result);
		pLFinancialDetailsPage.clickOnCancelBtn();

	}

	@Test(priority = 9, description = "Verify functionality of cancel Button on Peril Level Financial")
	public void verifyCancelButton() {
		String perilName = objExcl.getStringData("addPerilLevelFinancialDetails", 1, 0);
		String locationGroup = objExcl.getStringData("addPerilLevelFinancialDetails", 1, 1);
		pLFinancialDetailsPage.selectPerilAndLocGroup(perilName, locationGroup);
		pLFinancialDetailsPage.clickOnAddPerilFinancialDetailsBtn();
		int numberOfLayersBefore = pLFinancialDetailsPage.getNumberPerilLevelFinancialLayers();
		pLFinancialDetailsPage.clickOnCancelBtn();
		int numberOfLayersAfter = pLFinancialDetailsPage.getNumberPerilLevelFinancialLayers();
		Assert.assertEquals(numberOfLayersAfter, numberOfLayersBefore - 1);

	}

	/*
	 * this test validate the Filter functionality
	 */
	@Test(priority = 10, description = "Verify Filter Data on Peril Level Fianancial")
	public void filterPerilFinancial() throws InterruptedException, AWTException {

		String entity = objExcl.getStringData("addPerilLevelFinancialDetails", 0, 13); // Minimum Deductable
		entity = entity.replaceAll("\\s", ""); // removing spaces
		String operator = "Is Equals To";
		String entityValue = objExcl.getStringData("addPerilLevelFinancialDetails", 2, 13);
		boolean result = pLFinancialDetailsPage.applyFilter(entity, operator, entityValue);
//		int numberOfLayers = pLFinancialDetailsPage.getNumberOfSavedPerilLevelFinancialLayers();
		pLFinancialDetailsPage.clickUndoBtn();
		Thread.sleep(1000);
		Assert.assertTrue(result);
		// Assert.assertEquals(numberOfLayers, 0); //first u have to save data

	}

	/*
	 * This test verifies the delete and column search functionality of Peril Level
	 * Financal Details Page
	 */
	@Test(priority = 11, description = "Verify Yes delete button on Peril Level Fianancial")
	public void deletePerilLevelFinancialDetails() throws InterruptedException {
		String perilName = objExcl.getStringData("addPerilLevelFinancialDetails", 2, 0);
		int numberOfLayersBefore = pLFinancialDetailsPage.getNumberOfSavedPerilLevelFinancialLayers();

		pLFinancialDetailsPage.applyColumnSearch(perilName);
		pLFinancialDetailsPage.clickOnDeleteBtn();
		boolean result = pLFinancialDetailsPage.clickOnDeleteYesBtn();
		pLFinancialDetailsPage.removeColumnSearch();
		Thread.sleep(1000);
		Assert.assertTrue(result);
		int numberOfLayersAfter = pLFinancialDetailsPage.getNumberOfSavedPerilLevelFinancialLayers();
		Assert.assertEquals(numberOfLayersAfter, numberOfLayersBefore - 1);

	}

	@Test(priority = 12, description = "Verify No delete button on Peril Level Fianancial")
	public void verifyDeleteNoBtn() throws InterruptedException {
		int numberOfLayersBefore = pLFinancialDetailsPage.getNumberOfSavedPerilLevelFinancialLayers();
		pLFinancialDetailsPage.clickOnDeleteBtn();
		boolean result = pLFinancialDetailsPage.clickOnDeleteNoBtn();
		Thread.sleep(500);
		Assert.assertTrue(result);
		int numberOfLayersAfter = pLFinancialDetailsPage.getNumberOfSavedPerilLevelFinancialLayers();
		Assert.assertEquals(numberOfLayersAfter, numberOfLayersBefore);
	}

//	//@Test(priority = 14)
//	public void updatePerilFinancial() throws InterruptedException, AWTException {
//		if (extentRptflag) {
//			logger = report.createTest("Update Peril Financial Data in a report");
//		}
//		pLFinancialDetailsPage.bulkUpdatePerilFin();	
//	}

	@Test(priority = 13, description = "Verify functionality of Column Chooser on Peril Level Financial")
	public void verifyColumnChooserOnPerilLevelFinancial() {
		String[] columnNames = { "Deductible Type", "Location Group", "Limit Code", "Sublimit Part", "Min Deductible" };
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		pLFinancialDetailsPage.clickUndoBtn();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length);

	}

	@Test(priority = 14, description = "Verify the functionality of Previous icon on Peril Level Fianancial")
	public void verifyPreviousTabPerilLevel() {
		pLFinancialDetailsPage.clickPreviousTab();
		boolean flag = new PolicyBlanketDetailsPage().verifyPolicyBlanketDetailsTab();
		pLFinancialDetailsPage.clickNextTab();
		Assert.assertTrue(flag);
	}

	@Test(priority = 15, description = "Verify the functionality of Next icon on Peril Level Fianancial")
	public void verifyNextTabPerilLevel() {
		pLFinancialDetailsPage.clickNextTab();
		boolean flag = new LocationMasterPage().verifyLocationMasterTab();
		// pLFinancialDetailsPage.clickPreviousTab();
		Assert.assertTrue(flag);
	}
}
