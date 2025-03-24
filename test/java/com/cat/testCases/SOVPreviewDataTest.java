package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.MergeSOVPage;
import com.cat.pages.SOVImportPage;
import com.cat.pages.SOVPreviewDataPage;
import com.cat.pages.WorkTrayPage;
import com.cat.utilities.ReadExcel;
import com.cat.utilities.Utils;

public class SOVPreviewDataTest extends BaseClass {

	LoginPage loginPage;
	Utils utils;
	HomePage homePage;
	WorkTrayPage workTrayPage;
	SOVImportPage sovImportPage;
	MergeSOVPage mergeSovPage;
	SOVPreviewDataPage previewDataPage;
	ReadExcel readExcel = new ReadExcel("ScrubbingTestData");
//	String accountName = "Test_AccountN43";
	String accountName;
	String sheetName = "Sheet1";


	@Test(priority = -1, description = "Verify the SOV Preview Data Page")
	public void goToSOVPreviewDataPage() throws InterruptedException {
		loginPage = new LoginPage();
		utils = new Utils();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		workTrayPage = homePage.goToWorkTray();
		Thread.sleep(3000);
		accountName = readConfig.getDataFromConfig("Scrubbing_AccountName");

		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		sovImportPage = workTrayPage.startCleansing();
		Assert.assertTrue(utils.isSuccess());
		sovImportPage.clickOnSovImort();
		
		Thread.sleep(1000);
		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnPreviewData();
		mergeSovPage = new MergeSOVPage();
		previewDataPage = new SOVPreviewDataPage();
		boolean flag =  previewDataPage.verifySOVPreviewDataPage();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 1, description = "Verify split Column Button by providing existing column name on SOV Preview",
			dependsOnMethods = {"goToSOVPreviewDataPage"})
	public void verifySplitColumnWithExistingColumnNameInSovPreview() {
		String columnName = readExcel.getStringData("SOV Preview Data", 2, 8);
		String existingColumnName = readExcel.getStringData("SOV Preview Data", 2, 9);		
		String seperator = readExcel.getStringData("SOV Preview Data", 2, 11);
		mergeSovPage.splitColumn(columnName, existingColumnName, seperator);
		boolean flag = mergeSovPage.isColumnSplitted(columnName, existingColumnName);
		Assert.assertFalse(flag);
	}
	
	@Test(priority = 2, description = "Verify split Column Button by providing new column name on SOV Preview",
			dependsOnMethods = {"goToSOVPreviewDataPage"})
	public void validateSplitColumnInSovPreview() {

		String columnName =  readExcel.getStringData("SOV Preview Data", 2, 8);
		String newColumnName =  readExcel.getStringData("SOV Preview Data", 2, 10);
		String seperator = readExcel.getStringData("SOV Preview Data", 2, 11);
		mergeSovPage.splitColumn(columnName, newColumnName, seperator);
		boolean flag = mergeSovPage.isColumnSplitted(columnName, newColumnName);
		Assert.assertTrue(flag);
	}
	@Test(priority = 3, description = "Verify  Rename Column Button by providing existing column name on SOV Preview", 
			dependsOnMethods = {"goToSOVPreviewDataPage"})
	public void verifyRenameColumnNameWithExistingColumnInSOVPreview() {

		String columnName = readExcel.getStringData("SOV Preview Data", 2, 0);
		String existingColumnName = readExcel.getStringData("SOV Preview Data", 2, 1);
		mergeSovPage.renameColumnName(columnName, existingColumnName);
		mergeSovPage.clickOnRenameColumnBtn();
		boolean flag = utils.isError();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 4, description = "Verify  Rename Column Button by providing new column name on SOV Preview", 
			dependsOnMethods = {"goToSOVPreviewDataPage"})
	public void validateRenameColumnNameInSOVPreview() {

		String columnName = readExcel.getStringData("SOV Preview Data", 2, 0);
		String renamedColumnName = readExcel.getStringData("SOV Preview Data", 2, 2);
		mergeSovPage.renameColumnName(columnName, renamedColumnName);
		boolean flag = utils.isSuccess();
		Assert.assertTrue(flag);
		flag = mergeSovPage.isColumnRenamed(renamedColumnName);
		Assert.assertTrue(flag);
	}
	
	//total number of columns in sheet1=44 on SOV Preview Data Page
	@Test(priority = 5, description = "verify functionality of delete columns button on SOV Preview", 
			dependsOnMethods = {"goToSOVPreviewDataPage"})
	public void validateDeleteColumnInSOVPreview() {
		String values = readExcel.getStringData("SOV Preview Data", 2, 4);
		String[] columnName = values.split(",");
		int numberOfColumnsBefore = mergeSovPage.getNumberOfImportedColumn();
		mergeSovPage.deleteColumn(columnName);
		
		boolean flag = utils.isSuccess();
		Assert.assertTrue(flag);
		int numberOfColumnsAfter = mergeSovPage.getNumberOfImportedColumn();
		Assert.assertEquals(numberOfColumnsAfter, numberOfColumnsBefore-columnName.length);
	}
	
	@Test(priority = 6, description = "Verify delete locations without selecting any location on SOV Preview", 
			dependsOnMethods = {"goToSOVPreviewDataPage"})
	public void verifyDeleteWithoutSelectingRowSOVPreview() {
		mergeSovPage.clickOnDeleteBtn();
		boolean flag = utils.isWarning();
		Assert.assertTrue(flag);
	}
	
	/*
	 * In Sheet1 total locations=12, deleted locations=3 
	 * Remaining no of locationRow=8
	 */
	@Test(priority = 7, description = "Verify delete locations on SOV Preview", 
			dependsOnMethods = {"goToSOVPreviewDataPage"})
	public void validateDeleteLocationRowInSOVPreview() throws InterruptedException {
		int numberOfLocationToDelete = 3;
		Thread.sleep(1000);
		int numberOfLocationRowBefore = mergeSovPage.getNumberOfImportedLocations();
		mergeSovPage.deleteLocation(numberOfLocationToDelete);
		Thread.sleep(1000);
		int numberOfLocationRowAfter = mergeSovPage.getNumberOfImportedLocations();
		Assert.assertEquals(numberOfLocationRowAfter, (numberOfLocationRowBefore-numberOfLocationToDelete));
	}
	
	@Test(priority = 8, description = "verify functionality of column search button on SOV Preview", 
			dependsOnMethods = {"goToSOVPreviewDataPage"})
	public void verifySearchButtonInSOVPreview() {
		String orgLocId =  readExcel.getStringData("SOV Preview Data", 2, 6);
		previewDataPage.applySearchOnLocation(orgLocId);
		int numberOfLocations = mergeSovPage.getNumberOfLocationInGrid();
		previewDataPage.removeSearchOnLocation();
		Assert.assertEquals(numberOfLocations, 1);
	}
	@Test(priority = 9, description = "verify modify data on SOV Preview", dependsOnMethods = {"goToSOVPreviewDataPage"})
	public void verifyModifyDataInSOVPreview() {
		String city = readExcel.getStringData("SOV Preview Data", 6, 1);
		String stateCode = readExcel.getStringData("SOV Preview Data", 6, 3);
		String zip = readExcel.getStringData("SOV Preview Data", 6, 2);
		String orgLocId = readExcel.getStringData("SOV Preview Data", 6, 0);
		previewDataPage.applySearchOnLocation(orgLocId);
		previewDataPage.modifyLocationData(city, stateCode, zip);
		previewDataPage.removeSearchOnLocation();
		previewDataPage.clickOnSaveBtn();		
		boolean flag1  = utils.isSuccess();
		boolean flag2 = previewDataPage.isLocationDataModified(orgLocId, city, stateCode, zip);
		Assert.assertTrue(flag1);
		Assert.assertTrue(flag2);
		
	}

	@Test(priority = 10, description = "Verify functionality of Column Chooser Button on SOV Preview", 
			dependsOnMethods = {"goToSOVPreviewDataPage"})
	public void columnChooserInSOVPreview() {
		String columnNames[] = readExcel.getStringData("SOV Preview Data", 6, 6).split(",");
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length + 1);
	}
	@Test(priority = 11, description = "Verify functionality of Next Button on SOV Preview", 
			dependsOnMethods = {"goToSOVPreviewDataPage"})
	public void verifyNextButtonInSOVPreview() {
		previewDataPage.clickOnNextBtn();
		boolean flag = mergeSovPage.verifyMergeSOVPage();
		mergeSovPage.clickOnPreviousBtn();
		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnPreviewData();
		Assert.assertTrue(flag);
	}
	@Test(priority = 12, description = "Verify functionality of Previous Button on SOV Preview", 
			dependsOnMethods = {"goToSOVPreviewDataPage"})
	public void verifyPreviousButtonInSOVPreview() {
		previewDataPage.clickOnPreviousBtn();
		sovImportPage.verifySovImportPage();
		
	}
}
