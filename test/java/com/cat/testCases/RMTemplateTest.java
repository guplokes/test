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
import com.cat.utilities.ReadExcel;
import com.cat.utilities.Utils;

public class RMTemplateTest extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	WorkTrayPage workTrayPage;
	SOVImportPage sovImportPage;
	MergeSOVPage mergeSovPage;
	MappingSummaryPage mappingSummaryPage;
	RMTemplatePage rmTemplatePage;
	ReadExcel readExcel = new ReadExcel("ScrubbingTestData");
//	String accountName = "Test_AccountN43";
	String accountName;
	String sheetName = "Sheet1";
	Utils utils;

	@Test(priority = -1, description = "Verify user is able to go to  RM Template Page")
	public void goToRMTemplatePage() throws InterruptedException {
		utils = new Utils();
		loginPage = new LoginPage();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		workTrayPage = homePage.goToWorkTray();
		workTrayPage.verifyWorkTrayPage();
//		Thread.sleep(3000);
		accountName = readConfig.getDataFromConfig("Scrubbing_AccountName");
		
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		
		workTrayPage.clickOnAccount();
		sovImportPage = workTrayPage.startCleansing();
		boolean result = utils.isSuccess();

		Assert.assertTrue(result);

		sovImportPage.clickOnSovImort();
		result = sovImportPage.verifySovImportPage();
		Assert.assertTrue(result);
		Thread.sleep(1000);

		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnNextButton();
		mergeSovPage = new MergeSOVPage();
		Thread.sleep(1500);
		mergeSovPage.deleteHiddenRowAndColumn();
		mappingSummaryPage = mergeSovPage.clickOnNextBtn();
		Thread.sleep(1000);
		mappingSummaryPage.verifyMappingSummaryPage();
//		mappingSummaryPage.isMLPredictingOedColumns();

		Thread.sleep(5000);
		if (!mappingSummaryPage.isMLPredictingOedColumns()) {
			mappingSummaryPage.updateUnmappedColumns();
			result = utils.isSuccess();
			Assert.assertTrue(result);
		}
		else {		
		mappingSummaryPage.applySovColumnSearch("StateCode");
		result = mappingSummaryPage.updateMapping("StateCode", "AreaCode");
		Assert.assertTrue(result);
		mappingSummaryPage.removeSovColumnSearch();
		mappingSummaryPage.clickOnSaveBtn();
		result = utils.isSuccess();
		Assert.assertTrue(result);
		}

//		Thread.sleep(3000);
		rmTemplatePage = mappingSummaryPage.clickOnNextBtn();
		Assert.assertTrue(utils.isSuccess());

		result = rmTemplatePage.verifyRMTemplatePage();
		Assert.assertTrue(result);
	}

	@Test(priority = 0, description = "Verify user is able to search any record  on RM Template",
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifySearchInRMTemplate() {
		String rowId = "7";
		rmTemplatePage.applySearchOnLocation(rowId);
		int numberOfRows = rmTemplatePage.getNumberOfRowsInGrid();
		rmTemplatePage.removeSearchOnLocation();
		Assert.assertEquals(numberOfRows, 1);
	}

	@Test(priority = 1, description = "Verify the functionality of Delete button without selecting any row on RM Template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyDeleteLocationsWithoutSelectingAnyRow() {
		rmTemplatePage.clickOnDeleteBtn();
		boolean result = utils.isWarning();
		Assert.assertTrue(result);
		int numberOfRowsIngrid = rmTemplatePage.getNumberOfRowsInGrid();
		Assert.assertEquals(numberOfRowsIngrid, 10);
	}

	@Test(priority = 2, description = "Verify cleansing of Street Address on RM Template", dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyStreetAddressCleansing() {
		String columnName = readExcel.getStringData("RMTemplate", 9, 1);
		String values = readExcel.getStringData("RMTemplate", 10, 1);
		String[] cleansedStreetAdresses = values.split(",");
		boolean result = rmTemplatePage.verifyColumnData(columnName, cleansedStreetAdresses);
		Assert.assertTrue(result);
	}

	@Test(priority = 3, description = "Verify cleansing of State code on RM Template", dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyStateCodeCleansing() {
		String columnName = readExcel.getStringData("RMTemplate", 9, 3);
		String values = readExcel.getStringData("RMTemplate", 10, 3);
		String[] cleansedStateCodes = values.split(",");
		System.out.println(cleansedStateCodes);
		boolean result = rmTemplatePage.verifyColumnData(columnName, cleansedStateCodes);
		Assert.assertTrue(result);
	}
	

	@Test(priority = 4, description = "Verify cleansing of Zip code on RM Template", dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyZipCodeCleansing() {
		String columnName = readExcel.getStringData("RMTemplate", 9, 5);
		String values = readExcel.getStringData("RMTemplate", 10, 5);
		String[] cleansedZipCodes = values.split(",");
		boolean result = rmTemplatePage.verifyColumnData(columnName, cleansedZipCodes);
		Assert.assertTrue(result);
	}

	@Test(priority = 5, description = "Verify Find & Replace button functionality with valid values on RM Template", dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyFindAndReplaceWithValidValues() throws InterruptedException {
		String columnName = readExcel.getStringData("RMTemplate", 6, 0);
		String findText = readExcel.getStringData("RMTemplate", 6, 2);
		String replaceText = readExcel.getStringData("RMTemplate", 6, 3);
		String[] values = readExcel.getStringData("RMTemplate", 6, 1).split(",");
		rmTemplatePage.findAndReplace(columnName.replace(" ", ""), findText, replaceText);
		Thread.sleep(1000);
		boolean result = utils.isSuccess();
		if(!result)
			rmTemplatePage.clickOnCloseButton();
		Assert.assertTrue(result);
		result = rmTemplatePage.verifyColumnData(columnName, values);
		Assert.assertTrue(result);

	}

	public void verifyFindAndReplaceWithInvalidValues() {

	}

	@Test(priority = 6, description = "Verify user is able to apply multiplier on coverage column  on RM Template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyApplyMultilierButton() {
		String sheetName = readExcel.getStringData("RMTemplate", 2, 0);
		String columnName = readExcel.getStringData("RMTemplate", 2, 1);
		String factorValue = readExcel.getStringData("RMTemplate", 2, 2);
		String data = readExcel.getStringData("RMTemplate", 2, 3);
		String[] columnData = data.split(",");

		rmTemplatePage.applyMultilplier(sheetName, columnName.replace(" ", ""), factorValue);
		boolean result = rmTemplatePage.verifyColumnData(columnName, columnData);
		Assert.assertTrue(result);
	}

	@Test(priority = 7, description = "Verify user is able to Undo coverages on RM Template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyUndoCoverages() {
		String columnName = readExcel.getStringData("RMTemplate", 2, 1);
		String data = readExcel.getStringData("RMTemplate", 2, 9);
		String[] columnData = data.split(",");
//		rmTemplatePage.undoCoverages(columnName.replace(" ", ""));
		rmTemplatePage.undoAllCoverages();
		boolean result = rmTemplatePage.verifyColumnData(columnName, columnData);
		Assert.assertTrue(result);
	}

	@Test(priority = 8, description = "Verify user is able to perform Annuaization on RM Template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyAnnualization() throws InterruptedException {
		String[] bipoi = readExcel.getStringData("RMTemplate", 6, 10).split(",");
		rmTemplatePage.performAnnualization(bipoi);
		Thread.sleep(1500);
		String[] buildingTIVValues = readExcel.getStringData("RMTemplate", 6, 11).split(",");
		boolean result1 = utils.isSuccess();
		Assert.assertTrue(result1);
		boolean result2 = rmTemplatePage.verifyColumnData("BITIV", buildingTIVValues);
		Assert.assertTrue(result2);
	}	
	
	@Test(priority = 9, description = "Verify functionality of Filter Locations with invalid values on RM Template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyFilterLocationsWithInvalidValues() throws InterruptedException {
		String columnName = readExcel.getStringData("RMTemplate", 15, 4);
		String operator = readExcel.getStringData("RMTemplate", 15, 5);
		String value = ""; //validating with empty value
		boolean result = rmTemplatePage.filterLocationData(columnName.replace(" ", ""), operator, value);
		Assert.assertTrue(result);
		result = utils.isWarning();
		Assert.assertTrue(result);
		rmTemplatePage.clickOnCloseButton();
		Thread.sleep(1500);
	}

	@Test(priority = 9, description = "Verify functionality of Filter Locations with valid values on RM Template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyFilterLocationsWithValidValues() throws InterruptedException  {
		String columnName = readExcel.getStringData("RMTemplate", 15, 4);
		String operator = readExcel.getStringData("RMTemplate", 15, 5);
		String value = readExcel.getStringData("RMTemplate", 15, 6);
		int expectedOutput = (int)readExcel.getNumericData("RMTemplate", 15, 7);
		boolean result = rmTemplatePage.filterLocationData(columnName.replace(" ", ""), operator, value);
		Assert.assertTrue(result);
		Thread.sleep(1000);
		int numberOfRows = rmTemplatePage.getNumberOfRowsInGrid();
		rmTemplatePage.clickOnClearAllFilters();
		Assert.assertEquals(numberOfRows, expectedOutput);
		
	}
	@Test(priority = 10, description = "Verify functionality of Update Locations with invalid values on RM template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyUpdateLocationWithInvalidValues() throws InterruptedException {
		String columnName = readExcel.getStringData("RMTemplate", 15, 1);
		String value = "";  //validating with empty value
		boolean result = rmTemplatePage.updateLocationData(columnName.replace(" ", ""), value);
		Assert.assertTrue(result);
		result = utils.isWarning();
		Assert.assertTrue(result);
		rmTemplatePage.clickOnCloseButton();	
		Thread.sleep(1000);
	}
	
	@Test(priority = 10, description = "Verify functionality of Update Locations with valid values on RM template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyUpdateLocationWithValidValues() {
		String columnName = readExcel.getStringData("RMTemplate", 15, 1);
		String value = readExcel.getStringData("RMTemplate", 15, 2);
		String[] outputData = {value, value, value, value};
		boolean result = rmTemplatePage.updateLocationData(columnName.replace(" ", ""), value);
		Assert.assertTrue(result);
		result = utils.isSuccess();
		Assert.assertTrue(result);
		result =  rmTemplatePage.verifyColumnData(columnName, outputData);
		Assert.assertTrue(result);
		
	}

	@Test(priority = 11, description = "Verify user is able to delete selected columns on RM Template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyDeleteColumns() throws InterruptedException {
		String values = readExcel.getStringData("RMTemplate", 2, 11);
		String[] columnNames = values.split(",");
		System.out.println(columnNames );
		int numberOfColumnsBefore = rmTemplatePage.getNumberOfColumnsInGrid();
		System.out.println(numberOfColumnsBefore );
		boolean result1 = rmTemplatePage.deleteColumns(columnNames);

		Thread.sleep(1500);
		Assert.assertTrue(result1);
		boolean result2 = utils.isSuccess();
		Assert.assertTrue(result2);
		int numberOfColumnsAfter = rmTemplatePage.getNumberOfColumnsInGrid();
		System.out.println(numberOfColumnsAfter );
		Assert.assertEquals(numberOfColumnsAfter, numberOfColumnsBefore - columnNames.length);
	}

	@Test(priority = 12, description = "Verify Review button on RM Template", dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyReviewButton() {
		boolean result = rmTemplatePage.reviewMappings();
		Assert.assertTrue(result);
	}

	@Test(priority = 13, description = "Verify functionality of Column Chooser Button on RM Template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyColumnChooser() {
		String[] columnNames = readExcel.getStringData("RMTemplate", 15, 11).split(",");
		rmTemplatePage.columnChooser(columnNames);
		int numberOfColumnsInGrid = rmTemplatePage.getNumberOfColumnsInGrid();
		Assert.assertEquals(numberOfColumnsInGrid, columnNames.length + 3);
	}

	@Test(priority = 14, description = "Verify user should not be able to Rename column to existing Column Name on RM Template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyRenameColumnToExistingColumnInRMTemplate() {
		String columnRenameFrom = readExcel.getStringData("RMTemplate", 10, 8);
		String columnRenameTo = readExcel.getStringData("RMTemplate", 10, 10);
		rmTemplatePage.renameColumnName(columnRenameFrom.replace(" ", ""), columnRenameTo.replace(" ", ""));
		Assert.assertTrue(utils.isError());
		rmTemplatePage.clickOnCloseButton();

//		boolean result = rmTemplatePage.isColumnRenamed(columnRenameTo);
//		Assert.assertTrue(result);
	}

	@Test(priority = 15, description = "Verify user is able to Rename a Column on RM Template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyRenameColumnInRMTemplate() {
		String columnRenameFrom = readExcel.getStringData("RMTemplate", 10, 8);
		String columnRenameTo = readExcel.getStringData("RMTemplate", 10, 9);
		rmTemplatePage.renameColumnName(columnRenameFrom.replace(" ", ""), columnRenameTo.replace(" ", ""));
		Assert.assertTrue(utils.isSuccess());
		boolean result = rmTemplatePage.isColumnRenamed(columnRenameTo);
		Assert.assertTrue(result);
	}


	@Test(priority = 16, description = "Verify the functionality of Delete button by selecting rows on RM Template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyDeleteLocations() throws InterruptedException {
		int numberOfLocationsToDelete = 3;
		rmTemplatePage.deleteLocation(numberOfLocationsToDelete);
		Thread.sleep(2000);
		int actualNumberOfRowsInGrid = rmTemplatePage.getNumberOfRowsInGrid();
//		System.out.println(actualNumberOfRowsInGrid);
		Assert.assertEquals(actualNumberOfRowsInGrid, 8);
	}

	@Test(priority = 17, description = "Verify functionality of Previous button on RM Template", 
			dependsOnMethods = {"goToRMTemplatePage"})
	public void verifyPreviousButton() {
		rmTemplatePage.clickOnPreviousBtn();
		boolean result = mappingSummaryPage.verifyMappingSummaryPage();
		Assert.assertTrue(result);
	}

}
