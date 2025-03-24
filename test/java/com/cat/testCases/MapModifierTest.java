package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.MapModifierPage;
import com.cat.pages.MappingSummaryPage;
import com.cat.pages.MergeSOVPage;
import com.cat.pages.RMTemplatePage;
import com.cat.pages.SOVImportPage;
import com.cat.pages.WorkTrayPage;
import com.cat.utilities.ReadExcel;
import com.cat.utilities.Utils;

public class MapModifierTest extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	WorkTrayPage workTrayPage;
	SOVImportPage sovImportPage;
	MergeSOVPage mergeSovPage;
	MappingSummaryPage mappingSummaryPage;
	RMTemplatePage rmTemplatePage;
	MapModifierPage mapModifierPage;
	ReadExcel readExcel = new ReadExcel("ScrubbingTestData");
	String accountName;// = "Test_AccountN43";
	String sheetName = "Sheet1";
	Utils utils;

	@Test(description = "verify if user is able to see map modifier page", priority = -1)
	public void goToMapModifierPage() throws InterruptedException {
		utils = new Utils();
		loginPage = new LoginPage();
		accountName = readConfig.getDataFromConfig("AccountName");
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		workTrayPage = homePage.goToWorkTray();
		workTrayPage.verifyWorkTrayPage();

		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		sovImportPage = workTrayPage.startCleansing();
		Assert.assertTrue(utils.isSuccess());

		sovImportPage.clickOnSovImort();
		Thread.sleep(1000);

		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnNextButton();
		mergeSovPage = new MergeSOVPage();
		Thread.sleep(1500);
		mergeSovPage.deleteHiddenRowAndColumn();
		mappingSummaryPage = mergeSovPage.clickOnNextBtn();
		mappingSummaryPage.verifyMappingSummaryPage();
		Thread.sleep(2000);
		rmTemplatePage = mappingSummaryPage.clickOnNextBtn();
		Assert.assertTrue(utils.isSuccess());
		rmTemplatePage.verifyRMTemplatePage();
		mapModifierPage = rmTemplatePage.clickOnMapModifierBtn();
		Thread.sleep(2000);
//		System.out.println(driver.getTitle());
		boolean result = mapModifierPage.verifyMapMpdifierPage();
		Assert.assertTrue(result);

	}

//	@Test(priority = 1)
	public void verifyOedCodeRepositoryDropdown() throws InterruptedException {
		Thread.sleep(2000);
		boolean result = mapModifierPage.isMLPredictingOedCodeReposatory();
		Assert.assertTrue(result);
	}

//	@Test(priority = 2)
	public void verifyNatureOfAccount() throws InterruptedException {
		String oedCode = "1053-Residential, Temporary lodging";
		boolean result = mapModifierPage.applyNatureOfAccount(oedCode);
		Assert.assertTrue(result);
	}

	@Test(priority = 5)
	public void updateModifiersOnInvalidTab() {
		boolean result = mapModifierPage.updateModifierData();
		Assert.assertTrue(result);
	}

	@Test(priority = 6)
	public void verifyCloseButton() throws InterruptedException {
		System.out.println(rmTemplatePage.verifyRMTemplatePage());
		mapModifierPage.clickOnCloseButton();
		Thread.sleep(3000);
		boolean result = rmTemplatePage.verifyRMTemplatePage();
		System.out.println(result);
		System.out.println(mapModifierPage.verifyMapMpdifierPage());
	}
}
