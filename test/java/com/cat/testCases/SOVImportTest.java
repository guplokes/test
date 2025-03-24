package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.MergeSOVPage;
import com.cat.pages.SOVImportPage;
import com.cat.pages.WorkTrayPage;
import com.cat.utilities.ReadExcel;
import com.cat.utilities.Utils;

public class SOVImportTest extends BaseClass {

	LoginPage loginPage;
	Utils utils;
	HomePage homePage;
	WorkTrayPage workTrayPage;
	SOVImportPage sovImportPage;
	ReadExcel readExcel = new ReadExcel("ScrubbingTestData");
	String accountName;

	@Test(priority = -1, description = "Verify SOV Import Page")
	public void goToSOVImportPage() throws InterruptedException {
		loginPage = new LoginPage();
		utils = new Utils();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

//		workTrayPage = homePage.goToWorkTray();
//		Thread.sleep(3000);
		accountName = readConfig.getDataFromConfig("Scrubbing_AccountName");

		String userName = readConfig.getDataFromConfig("FirstName");
		boolean result;
		workTrayPage = homePage.goToWorkTray();
		workTrayPage.verifyWorkTrayPage();
		workTrayPage.applyColumnSearchOnUserAndStatus("Cleansing", userName);
		Thread.sleep(1500);
		workTrayPage.clickOnAccount();
		workTrayPage.clickOnHoldAccount();
		result = utils.isSuccess();
		workTrayPage.removeColumnSearchOnUserAndStatus();
		Assert.assertTrue(result);

	
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		sovImportPage = workTrayPage.startCleansing();
		Assert.assertTrue(utils.isSuccess());
		sovImportPage.clickOnSovImort();

		Thread.sleep(2000);
		boolean flag = sovImportPage.verifySovImportPage();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 0, description = "Verify No Delete Button on SOV Import")
	public void verifyNoDeleteButtonInSovImport() {
		int numberOfSheetsBefore = sovImportPage.getNumberOfSheetsInGrid();
		boolean result1 = sovImportPage.clickOnDeleteNoBtn();
		Assert.assertTrue(result1);
		int numberOfSheetsAfter = sovImportPage.getNumberOfSheetsInGrid();
		Assert.assertEquals(numberOfSheetsAfter, numberOfSheetsBefore );

	}

	@Test(priority = 0, description = "Verify Yes Delete Button on SOV Import")
	public void verifyYesDeleteButtonInSovImport() {
		int numberOfSheetsBefore = sovImportPage.getNumberOfSheetsInGrid();
		boolean result1 = sovImportPage.clickOnDeleteYesBtn();
		Assert.assertTrue(result1);
		boolean result2 = utils.isSuccess();
		Assert.assertTrue(result2);
		int numberOfSheetsAfter = sovImportPage.getNumberOfSheetsInGrid();
		Assert.assertEquals(numberOfSheetsAfter, numberOfSheetsBefore - 1);

	}	


	@Test(priority = 1, description = "Verify bulk delete without selcting any sheet on SOV Import")
	public void bulkDeleteSheetWithoutSelectingSheet() {
		sovImportPage.clickOnBulkDelete();
		boolean result = utils.isWarning();
		Assert.assertTrue(result);
	}

	@Test(priority = 2, description = "Verify No button in bulk delete on SOV Import")
	public void verifybulkDeleteNoBtutton() {
		int numberOfSheetsBefore = sovImportPage.getNumberOfSheetsInGrid();
		boolean result1 = sovImportPage.clickOnBulkDeleteNoButton();
		Assert.assertTrue(result1);
		int numberOfSheetsAfter = sovImportPage.getNumberOfSheetsInGrid();
		Assert.assertEquals(numberOfSheetsAfter, numberOfSheetsBefore);

	}	
	@Test(priority = 3, description = "Verify Yes button in bulk delete on SOV Import")
	public void verifyBulkDeleteYesBtutton() throws InterruptedException {
		String[] sheetNames = readExcel.getStringData("SOV Import", 2, 0).split(",");
		boolean result1 = sovImportPage.clickOnBulkDeleteYesButton(sheetNames);
		Assert.assertTrue(result1);
		boolean result2 = utils.isSuccess();
		Assert.assertTrue(result2);

	}
	

	@Test(priority = 5, description = "Verify that user is able to upload .xlsx file on SOV Import")
	public void importSOVFile() throws Exception {
		String fileName = readExcel.getStringData("SOV Import", 2, 2);
		sovImportPage.selectSovFile(fileName);
		boolean result = utils.isSuccess();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 5, description = "Verify that user is able to Re-upload existing file on SOV Import")
	public void verifyReUploadSovFile() throws Exception {
		String fileName = readExcel.getStringData("SOV Import", 2, 2);
		sovImportPage.selectSovFile(fileName);
		boolean result = utils.isSuccess();
		Assert.assertTrue(result);
	}

	@Test(priority = 6, description = "Verify functionality of Transpose on SOV Import")
	public void processSovUsingTranspose() {
		String sheetName = readExcel.getStringData("SOV Import", 2, 4);
		boolean flag = sovImportPage.transpose(sheetName);

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(flag);
	}

	@Test(priority = 7, description = "Verify functionality of Key Merge on SOV Import")
	public void processSovUsingKeyMerge() {
		String[] sheetNames = readExcel.getStringData("SOV Import", 2, 6).split(",");
		boolean flag = sovImportPage.keyMerge(sheetNames);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertTrue(flag);
	}

	@Test(priority = 8, description = "Verify Next button without selecting any sheet on SOV Import")
	public void verifyNextButtonWithoutSelectingSheetInSovImport() {
		sovImportPage.clickOnNextButton();
		boolean result = utils.isWarning();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 9, description = "Verify Next button on SOV Import")
	public void verifyNextButtonInSovImport() {
		String sheetName = readExcel.getStringData("SOV Import", 2, 4);
		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnNextButton();
		boolean result = new MergeSOVPage().verifyMergeSOVPage();
		sovImportPage.clickOnPreviousBtn();
		Assert.assertTrue(result);
	}


	@Test(priority = 10, description = "Verify functionality of column and header range on SOV Import")
	public void processSovUsingColumnAndHeaderRange() {
		String headerRange = readExcel.getStringData("SOV Import", 6, 0);
		String columnRange = readExcel.getStringData("SOV Import", 6, 1);
		String sheetName = readExcel.getStringData("SOV Import", 6, 2);
		boolean flag = sovImportPage.headerAndColumnRange(sheetName, headerRange, columnRange);

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(flag);
	}
}
