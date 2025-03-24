package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.MappingSummaryPage;
import com.cat.pages.MergeSOVPage;
import com.cat.pages.SOVImportPage;
import com.cat.pages.WorkTrayPage;
import com.cat.utilities.ReadExcel;
import com.cat.utilities.Utils;

public class MergeSOVTest extends BaseClass {

	LoginPage loginPage;
	Utils utils;
	HomePage homePage;
	WorkTrayPage workTrayPage;
	SOVImportPage sovImportPage;
	MergeSOVPage mergeSovPage;
	ReadExcel readExcel = new ReadExcel("ScrubbingTestData");
//	String accountName = "Test_AccountN43";
	String accountName;
	String sheetName = "Sheet1";

	@Test(priority = -1, description =  "Verify the Merge SOV Page")
	public void goToMergeSOVPage() throws InterruptedException {
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
		sovImportPage.clickOnNextButton();
		mergeSovPage = new MergeSOVPage();
		boolean flag =  mergeSovPage.verifyMergeSOVPage();
		Assert.assertTrue(flag);
	}
	@Test(priority = 1, description =  "Verify remove hidden row and column button functionality on Merge SOV", 
			dependsOnMethods = {"goToMergeSOVPage"})
	public void removeHiddenRowAndColumnDataInMergeSOV() {
		mergeSovPage.deleteHiddenRowAndColumn();
		boolean flag = utils.isSuccess();
		Assert.assertTrue(flag);
	}
	@Test(priority = 2, description =  "Verify  Rename Column Button by providing existing column name on Merge SOV",
			dependsOnMethods = {"goToMergeSOVPage"})
	public void verifyRenameColumnNameWithExistingColumnInMergeSOV() {
		String columnName = readExcel.getStringData("MergeSOV", 2, 0);
		String existingColumnName = readExcel.getStringData("MergeSOV", 2, 1);
		mergeSovPage.renameColumnName(columnName, existingColumnName);
		mergeSovPage.clickOnRenameColumnBtn();
		boolean flag1 = utils.isError();
		Assert.assertTrue(flag1);
	}
	@Test(priority = 3, description =  "Verify  Rename Column Button by providing new column name on Merge SOV", 
			dependsOnMethods = {"goToMergeSOVPage"})
	public void verifyRenameColumnNameInMergeSOV() {
		String columnName = readExcel.getStringData("MergeSOV", 2, 0);
		String renamedColumnName = readExcel.getStringData("MergeSOV", 2, 2);
		mergeSovPage.renameColumnName(columnName, renamedColumnName);
		boolean result = utils.isSuccess();
		Assert.assertTrue(result);
		result = mergeSovPage.isColumnRenamed(renamedColumnName);
		Assert.assertTrue(result);
	}
	
	@Test(priority = 4, description =  "Verify delete locations without selecting any location on Merge SOV", 
			dependsOnMethods = {"goToMergeSOVPage"})
	public void verifyDeleteWithoutSelectingRowInMergeSOV() {
		mergeSovPage.clickOnDeleteBtn();
		boolean flag = utils.isWarning();
		Assert.assertTrue(flag);
	}

	/*
	 * In Sheet1 total locations=12, deleted in hiddenRowsAndColumn=1 and deleted
	 * locations=3 Remaining no of locationRow=8
	 */
	@Test(priority = 4, description =  "Verify delete locations on Merge SOV", dependsOnMethods = {"goToMergeSOVPage"})
	public void verifyDeleteLocationRowInMergeSOV() throws InterruptedException {
		int numberOfLocationToDelete = 3;
		Thread.sleep(1000);
		int numberOfLocationRowBefore = mergeSovPage.getNumberOfImportedLocations();
		mergeSovPage.deleteLocation(numberOfLocationToDelete);
		Thread.sleep(1000);
		int numberOfLocationRowAfter = mergeSovPage.getNumberOfImportedLocations();
		Assert.assertEquals(numberOfLocationRowAfter, (numberOfLocationRowBefore-numberOfLocationToDelete));
	}
	//total number of columns in sheet1=44 on mergeSOV Page
	@Test(priority = 5, description =  "verify functionality of delete columns button on Merge SOV", dependsOnMethods = {"goToMergeSOVPage"})
	public void verifyDeleteColumnInMergeSOV() {
		String data = readExcel.getStringData("MergeSOV", 2, 4);
		String[] columnName = data.split(",");
		System.out.println(columnName);
		int numberOfColumnsBefore = mergeSovPage.getNumberOfImportedColumn();
		mergeSovPage.deleteColumn(columnName);
		boolean flag = utils.isSuccess();
		Assert.assertTrue(flag);
		int numberOfColumnsAfter = mergeSovPage.getNumberOfImportedColumn();
		Assert.assertEquals(numberOfColumnsAfter , numberOfColumnsBefore-columnName.length);
	}
	@Test(priority = 6, description =  "verify functionality of column search button on Merge SOV", dependsOnMethods = {"goToMergeSOVPage"})
	public void verifySearchButtonInMergeSOV() {
		String orgLocId = readExcel.getStringData("MergeSOV", 2, 6); //6
		mergeSovPage.applySearchOnLocation(orgLocId);
		int numberOfLocations = mergeSovPage.getNumberOfLocationInGrid();
		mergeSovPage.removeSearchOnLocation();
		Assert.assertEquals(numberOfLocations, 1);
	}
	@Test(priority = 7, description =  "verify modify data on Merge SOV", dependsOnMethods = {"goToMergeSOVPage"})
	public void verifyModifyDataInMergeSOV() {
		String city = readExcel.getStringData("MergeSOV", 6, 1);
		String zip = readExcel.getStringData("MergeSOV", 6, 2);
		String orgLocId = readExcel.getStringData("MergeSOV", 6, 0);
		
		mergeSovPage.applySearchOnLocation(orgLocId);
		mergeSovPage.modifyLocationData(city, zip);
		mergeSovPage.removeSearchOnLocation();
		mergeSovPage.clickOnSaveBtn();
		boolean flag1  = utils.isSuccess();
		Assert.assertTrue(flag1);
		boolean flag2 = mergeSovPage.isLocationDataModified(orgLocId, city, zip);
		Assert.assertTrue(flag2);
		
	}
	@Test(priority = 8, description =  "Verify split Column Button by providing existing column name on Merge SOV",
			dependsOnMethods = {"goToMergeSOVPage"})
	public void verifySplitColumnWithExistingColumnNameInMergeSOV() {
		String columnName = readExcel.getStringData("MergeSOV", 2, 8);
		String existingColumnName = readExcel.getStringData("MergeSOV", 2, 9);
		String seperator = readExcel.getStringData("MergeSOV", 2, 11);
		
		mergeSovPage.splitColumn(columnName, existingColumnName, seperator);
		boolean flag = mergeSovPage.isColumnSplitted(columnName, existingColumnName);
		Assert.assertFalse(flag);
	}
	@Test(priority = 9, description =  "Verify split Column Button by providing new column name on Merge SOV",
			dependsOnMethods = {"goToMergeSOVPage"})
	public void verifySplitColumnInMergeSOV() {
		String columnName = readExcel.getStringData("MergeSOV", 2, 8);
		String newColumnName = readExcel.getStringData("MergeSOV", 2, 10);
		String seperator = readExcel.getStringData("MergeSOV", 2, 11);
		mergeSovPage.splitColumn(columnName, newColumnName, seperator);
		boolean flag = mergeSovPage.isColumnSplitted(columnName, newColumnName);
		Assert.assertTrue(flag);
	}

	@Test(priority = 11, description =  "Verify functionality of Column Chooser Button on Merge SOV",
			dependsOnMethods = {"goToMergeSOVPage"})
	public void verifyColumnChooserButton() {
		String columnNames[] = readExcel.getStringData("MergeSOV", 6, 4).split(",");
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length + 3);
	}
	@Test(priority = 12, description =  "Verify functionality of Next Button on Merge SOV", dependsOnMethods = {"goToMergeSOVPage"})
	public void verifyNextButton() throws InterruptedException {
		MappingSummaryPage mappingPage = mergeSovPage.clickOnNextBtn();
//		Thread.sleep(4500);
		boolean result = mappingPage.verifyMappingSummaryPage();
		mergeSovPage.clickOnPreviousBtn();
		Assert.assertTrue(result);
	}
	@Test(priority = 13, description =  "Verify functionality of Previous Button on Merge SOV", 
			dependsOnMethods = {"goToMergeSOVPage"})
	public void verifyPreviousButtonInMergeSOV() {
		mergeSovPage.clickOnPreviousBtn();
		boolean flag = sovImportPage.verifySovImportPage();
		Assert.assertTrue(flag);		
	}
	



}
