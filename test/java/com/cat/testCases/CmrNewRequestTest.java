package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cat.pages.CmrNewRequestPage;
import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.ModellingInformationPage;
import com.cat.pages.WorkTrayPage;
import com.cat.utilities.ReadExcel;

public class CmrNewRequestTest extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	WorkTrayPage workTrayPage;
	CmrNewRequestPage newRequestPage;
	ModellingInformationPage modellingInfoPage;
	ReadExcel readExcel = new ReadExcel("CMRTestData");

	@Test(priority = -1, description = "Verify user is able to view CMR New Request Form")
	public void goToNewRequestPage() throws InterruptedException {
		loginPage = new LoginPage();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		workTrayPage = homePage.goToWorkTray();
		Thread.sleep(1500);
		newRequestPage = workTrayPage.clickOnNewRequest();
		Thread.sleep(2500);

		boolean flag = newRequestPage.verifyNewRequestPageTitle();
		Assert.assertTrue(flag);
	}

	@DataProvider(name = "CMR New Request")
	public Object[][] readRequestInfoData() throws Exception {
//		ReadExcel readExcel = new ReadExcel("CMRTestData");
		return readExcel.readExcelSheet("New Request Form");
	}

	@Test(priority = 3, dataProvider = "CMR New Request", description = "Verify user is able to enter details on New Request Form")
	public void cmrNewRequest(String accountName, String submissionNumber, String noOfLocations, String reinsuredName,
			String perils, String tiv, String underwriterName, String currency, String underwriterBranch,
			String requestType, String lineOfBuisness, String priority, String platform, String accountType,
			String userDef1, String userDef2, String userDef3, String userDef4, String userDef5) {
		newRequestPage.fillAccountDetails(accountName, submissionNumber, noOfLocations, reinsuredName, perils, tiv,
				underwriterName, currency, underwriterBranch, requestType, lineOfBuisness, priority, platform,
				accountType, userDef1, userDef2, userDef3, userDef4, userDef5);
	}

	@DataProvider(name = "Basic Modelling Info")
	public Object[][] readBasicModellingInfoData() throws Exception {
//		ReadExcel readExcel = new ReadExcel("CMRTestData");
		return readExcel.readExcelSheet("Basic Modelling Information");
	}

	@Test(priority = 4, dataProvider = "Basic Modelling Info", description = "Verify user is able to enter Basic Modelling Info on CMR Form")
	public void basicModellingInfo(String hazardEQ, String hazardWS, String BIPOI, String predominantOcc,
			String predominantConst, String sovBIValues) {
		modellingInfoPage = newRequestPage.clickOnModellingInformation();
		modellingInfoPage.fillBasicModellingInfo(hazardEQ, hazardWS, BIPOI, predominantOcc, predominantConst,
				sovBIValues);
	}

	@DataProvider(name = "Policy Level Financials")
	public Object[][] readPolicyLevelFinancialData() throws Exception {
//		ReadExcel readExcel = new ReadExcel("CMRTestData");
		return readExcel.readExcelSheet("Policy Level Financials");
	}

	@DataProvider(name = "Sheet1")
	public Object[][] read() throws Exception {
//		ReadExcel readExcel = new ReadExcel("CMRTestData");
		return readExcel.readExcelSheet("Sheet1");
	}

	@Test(priority = 5, dataProvider = "Sheet1", description = "Verify user is able to enter Policy Level Financial on CMR Form")
	public void policyLevelFinancial(String excessOf, String limit, String blanketDeductible) {
		modellingInfoPage.filPolicyLevelFinancialData(excessOf, limit, blanketDeductible);
	}

	@DataProvider(name = "Peril Level Financials")
	public Object[][] readPerilLevelFinancialData() throws Exception {
//		ReadExcel readExcel = new ReadExcel("CMRTestData");
		return readExcel.readExcelSheet("Peril Level Financial");
	}

	@Test(priority = 6, dataProvider = "Peril Level Financials", description = "Verify user is able to enter Peril Level Financial on CMR Form")
	public void perilLevelFinancial(String peril, String locationGroup, String sublimitCode, String sublimit,
			String deductible, String deductibleCode) {
		modellingInfoPage.fillPerilLevelFinancialData(peril, locationGroup, sublimitCode, sublimit, deductible,
				deductibleCode);

	}

	@Test(priority = 7, description = "Verify user is able to save CMR Request Form as Draft")
	public void saveRequestFormAsDraft() {
		modellingInfoPage.saveAsDraft();
		boolean flag = modellingInfoPage.isSuccess();
		Assert.assertTrue(flag);
	}

	@Test(priority = 8, description = "Verify user is able to delete Pending Request")
	public void deletePendingRequest() {
		modellingInfoPage.deletePendingRequest();
	}
}
