package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.MappingSummaryPage;
import com.cat.pages.MergeSOVPage;
import com.cat.pages.RMTemplatePage;
import com.cat.pages.SOVImportPage;
import com.cat.pages.WorkTrayPage;
import com.cat.utilities.Utils;

public class MappingSummaryTest extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	WorkTrayPage workTrayPage;
	SOVImportPage sovImportPage;
	MergeSOVPage mergeSovPage;
	MappingSummaryPage mappingSummaryPage;
	RMTemplatePage rmTemplatePage;
	Utils utils;
//	ReadExcel readExcel = new ReadExcel("CMRTestData");
//	String accountName = "Test_AccountN43";
	String accountName;
	String sheetName = "Sheet1";


	@Test(priority = -1, description = "Verify user is able to view Mapping Summary Page")
	public void goToMappingSummaryPage() throws InterruptedException {
		loginPage = new LoginPage();
		utils = new Utils();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		workTrayPage = homePage.goToWorkTray();
		workTrayPage.verifyWorkTrayPage();
//		Thread.sleep(3000);
		accountName = readConfig.getDataFromConfig("Scrubbing_AccountName");

		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		sovImportPage = workTrayPage.startCleansing();
		sovImportPage.clickOnSovImort();
		
		Thread.sleep(1000);
		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnNextButton();
		mergeSovPage = new MergeSOVPage();
		Thread.sleep(1500);
		mergeSovPage.deleteHiddenRowAndColumn();
		mappingSummaryPage = mergeSovPage.clickOnNextBtn();
		Thread.sleep(7000);
		boolean flag = mappingSummaryPage.verifyMappingSummaryPage();
		Assert.assertTrue(flag);
		}
	@Test(priority = 0, description = "Verify if ML prediction is working on Mapping Summary", 
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifyMLPrediction() {
		boolean result = mappingSummaryPage.isMLPredictingOedColumns();
		Assert.assertTrue(result);
	}
	@Test(priority = 1, description = "Verify count of Mapped and Unmapped Column is visible on Mapping Summary", 
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifyCountOfMappedAndUnmappedColumnIsVisible() {
		boolean result = mappingSummaryPage.isCountOfMappedAndUnmappedColumnVisible();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 2, description = "Verify search functionality on Mapping Summary", 
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifySearchSovColumn() throws InterruptedException {
		String sovColumnName = "Street Address";
		mappingSummaryPage.applySovColumnSearch(sovColumnName);
		int numberOfRows = mappingSummaryPage.getNumberOfRowsInGrid();
		boolean flag = mappingSummaryPage.isColumnSearch(sovColumnName);
		mappingSummaryPage.removeSovColumnSearch();
		Assert.assertEquals(numberOfRows, 1);		
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 3, description = "Verify Move Up button functionality on Mapping Summary", 
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifyMoveUpButton() {
		boolean result = mappingSummaryPage.moveUpSovColumn();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 4, description = "Verify Move Down button button functionality on Mapping Summary", 
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifyMoveDownButton() {
		boolean result = mappingSummaryPage.moveDownSovColumn();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 5, description = "Verify mapping of mapped Sov column on Mapping Summary",
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifyMappingOfMappedSovColumn() throws InterruptedException {
//		String sovColumn = "Yr. Roof covering";
//		String oedColumn = "RoofMaintenance";
//		String sovColumn = "Construction Quality";
//		String oedColumn = "ConstructionQuality";
		String sovColumn = "County";
		String oedColumn = "CountyCode";
		mappingSummaryPage.applySovColumnSearch(sovColumn);
		boolean result1 = mappingSummaryPage.updateMapping(sovColumn, oedColumn);
		mappingSummaryPage.removeSovColumnSearch();
		Assert.assertTrue(result1);
		boolean result2 = mappingSummaryPage.isColumnMapped(sovColumn, oedColumn);
		Assert.assertTrue(result2);
	}

	
	@Test(priority = 6, description = "Verify unmapped link on Mapping Summary", 
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifyUnmappedColumnLink() {
		boolean result = mappingSummaryPage.viewUnmappedColumns();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 7, description = "Verify Review button on Mapping Summary",
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifyReviewButton() {
		boolean result = mappingSummaryPage.isReviewUnmappedColumnDisplayed();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 8, description = "Verify Clear mapping of mapped sov column on Mapping Summary", 
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifyClearMappingOfMappedColumn() {
		String[] sovColumns = {"Building Foundation", "Other TIV"};
		boolean result1 = mappingSummaryPage.clearMapping(sovColumns);
		Assert.assertTrue(result1);	
//		boolean result = mappingSummaryPage.isMappingCleared(sovColumns);
//		Assert.assertTrue(result);		
	}
	
	@Test(priority = 9, description = "Verify Mapping of 2 sov column with 1 oed column with same priority on Mapping Summary", 
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifyMappingOfTwoSovColumnWithOneOedColumnWithSamePriority() throws Exception {
		String sovColumn = "City";
		String oedColumn = "Loc Name";
		String[] columnData = {"GREENS AT DORAL DORAL","GREENS AT DORAL DORAL","GREENS AT DORAL DORAL"};
		mappingSummaryPage.applySovColumnSearch(sovColumn);
		mappingSummaryPage.updateMapping(sovColumn, oedColumn.replace(" ",""));

		mappingSummaryPage.removeSovColumnSearch();
		Thread.sleep(1000);
		mappingSummaryPage.clickOnSaveBtn();
		Assert.assertTrue(utils.isSuccess());		
		
		rmTemplatePage = mappingSummaryPage.clickOnNextBtn();
		Assert.assertTrue(utils.isSuccess());
		Thread.sleep(2000);
		Assert.assertTrue(rmTemplatePage.verifyRMTemplatePage());
		boolean result = rmTemplatePage.verifyColumnData(oedColumn, columnData);
		mappingSummaryPage.clickOnPreviousBtn();
		Assert.assertTrue(result);
	}

	
	@Test(priority = 10, description = "Verify Tabwise TIV button on Mapping Summary", 
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifyTabwiseTIVButton() {
		String[] args = {"BuildingTIV", "ContentTIV", "BITIV", "OtherTIV", "TotalTIV"};
		boolean result = mappingSummaryPage.isTabwiseTIVDisplayed(args);
		Assert.assertTrue(result);
	}
	

	@Test(priority = 11, description = "Verify visible Tab Name column switch on Mapping Summary", 
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifVisibleTabNameSwitch() throws InterruptedException {
		mappingSummaryPage.clickOnVisibleTabNameSwitch();
		Thread.sleep(500);
		boolean result = mappingSummaryPage.isTabNameColumnDisplayed();
		Assert.assertTrue(result);
		mappingSummaryPage.clickOnVisibleTabNameSwitch();
		Thread.sleep(500);
		result = mappingSummaryPage.isTabNameColumnDisplayed();
		Assert.assertFalse(result);		
	}
	
	@Test(priority = 12, description = "Verify Next button functionality on Mapping Summary", 
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifyNextButtonInMappingSummary() {
		rmTemplatePage = mappingSummaryPage.clickOnNextBtn();
		boolean result = rmTemplatePage.verifyRMTemplatePage();
		mappingSummaryPage.clickOnPreviousBtn();		
		Assert.assertTrue(result);
	}
	
	@Test(priority = 13, description = "Verify Previous button functionality on Mapping Summary", 
			dependsOnMethods = {"goToMappingSummaryPage"})
	public void verifyPreviousButton() {
		mappingSummaryPage.clickOnPreviousBtn();
		boolean result = mergeSovPage.verifyMergeSOVPage();
		Assert.assertTrue(result);
	}
}
