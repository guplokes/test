package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.cat.pages.AssignWorkPage;
import com.cat.pages.CmrNewRequestPage;
import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.ModellingInformationPage;
import com.cat.pages.SOVImportPage;
import com.cat.pages.WorkTrayPage;
import com.cat.utilities.ReadExcel;
import com.cat.utilities.Utils;


public class EndToEnd_CMR_Test extends BaseClass {

	
	LoginPage loginPage;
	HomePage homePage;
	WorkTrayPage workTrayPage;
	CmrNewRequestPage newRequestPage;
	ModellingInformationPage modellingInfoPage;
	AssignWorkPage assignWorkPage;
	SOVImportPage sovImportPage;
	Utils utils;
	boolean result;
	String accountName = "Auto_Test_";
	ReadExcel readCMRExcel = new ReadExcel("CMRTestData");
	
	@Test(priority = -1, description = "Verify user is able view CMR New Request form Page")
	public void verifyCMRNewRequestForm() throws InterruptedException {
		loginPage = new LoginPage();
		utils = new Utils();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		workTrayPage = homePage.goToWorkTray();

		newRequestPage = workTrayPage.clickOnNewRequest();

		boolean flag = newRequestPage.verifyNewRequestPageTitle();
		Assert.assertTrue(flag);
		accountName = accountName + newRequestPage.randGeneratedStr(5);

		readConfig.updateKeyValue("AccountName", accountName);

		flag = accountName.equals(readConfig.getDataFromConfig("AccountName"));
		Assert.assertTrue(flag);
		
	}
	
	@DataProvider(name = "CMR New Request Form")
	public Object[][] readNewRequestFormData() throws Exception {
		return readCMRExcel.readExcelSheet("New Request Form");
	}
	
	@Test(priority = 1, dataProvider = "CMR New Request Form", description = "Verify user is able to enter details on New Request Form")
	public void verifyCMRNewRequestDetails(String accountName, String submissionNumber, String noOfLocations, String reinsuredName,
			String perils, String tiv, String underwriterName, String currency, String underwriterBranch,
			String requestType, String lineOfBuisness, String priority, String platform, String accountType,
			String userDef1, String userDef2, String userDef3, String userDef4, String userDef5) {
		newRequestPage.fillAccountDetails(this.accountName, this.accountName, noOfLocations, reinsuredName, perils, tiv,
				underwriterName, currency, underwriterBranch, requestType, lineOfBuisness, priority, platform,
				accountType, userDef1, userDef2, userDef3, userDef4, userDef5);
	}
	@Test(priority = 2, description = "Verify user is able to upload SOV file on New Request Form")
	public void verifyUploadSOVFile() {
		newRequestPage.uploadSOVFile("Demo File1.xlsx");
		boolean result = utils.isSuccess();
		Assert.assertTrue(result);
	}
	
	@DataProvider(name = "Basic Modelling Info")
	public Object[][] readBasicModellingInfoData() throws Exception {
		return readCMRExcel.readExcelSheet("Basic Modelling Information");
	}
	
	@Test(priority = 3, dataProvider = "Basic Modelling Info", description = "Verify user is able to enter Basic Modelling Info on CMR Form")
	public void verifyBasicModellinInfoDetails(String hazardEQ, String hazardWS, String BIPOI, String predominantOcc,
			String predominantConst, String sovBIValues) {
		modellingInfoPage = newRequestPage.clickOnModellingInformation();
		modellingInfoPage.fillBasicModellingInfo(hazardEQ, hazardWS, BIPOI, predominantOcc, predominantConst,
				sovBIValues);
	}
	
	@DataProvider(name = "Sheet1")
	public Object[][] read() throws Exception {
//		ReadExcel readExcel = new ReadExcel("CMRTestData");
		return readCMRExcel.readExcelSheet("Sheet1");
	}

	@Test(priority = 4, dataProvider = "Sheet1",
			description = "Verify user is able to enter Policy Level Financial on CMR Form")
	public void policyLevelFinancial(String excessOf, String limit, String blanketDeductible) {
		modellingInfoPage.filPolicyLevelFinancialData(excessOf, limit, blanketDeductible);
	}
	
	@DataProvider(name = "Peril Level Financials")
	public Object[][] readPerilLevelFinancialData() throws Exception {
		return readCMRExcel.readExcelSheet("Peril Level Financial");
	}

	@Test(priority = 5, dataProvider = "Peril Level Financials",
			description = "Verify user is able to enter Peril Level Financial on CMR Form")
	public void perilLevelFinancial(String peril, String locationGroup, String sublimitCode, String sublimit,
			String deductible, String deductibleCode) {
		modellingInfoPage.fillPerilLevelFinancialData(peril, locationGroup, sublimitCode, sublimit, deductible,
				deductibleCode);

	}
	
	@Test(priority = 6, description = "Verify user is able to Submit the cmr new request form")
	public void verifySubmitBtn() {

		modellingInfoPage.clickOnSubmitBtn();
		result = utils.isSuccess();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 7, description = "Verify user is able to navigate to Assign Work Page")
	public void goToAssignWork() {
		homePage.goToWorkTray();
		result = workTrayPage.verifyWorkTrayPage();
		Assert.assertTrue(result);
		assignWorkPage = workTrayPage.clickOnAssignWork();
		result = assignWorkPage.verifyAssignWorkTitle();
		Assert.assertTrue(result);		
		
	}
	
	@Test(priority = 8, description = "Verify user is able to assign cleansing associate and cleansing QA for account")
	public void verifySelectingCleansingAssociate_QA(){
		String userName = readConfig.getDataFromConfig("FirstName");
		assignWorkPage.assignAccountToUser(accountName, userName);
		assignWorkPage.clickOnSaveButton();
		result = utils.isSuccess();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 9, description  = "Verify user is able to put an account on hold on WorkTray and start cleansing of new account")
	public void verifyPuttingAccountOnHold() throws InterruptedException {
		String userName = readConfig.getDataFromConfig("FirstName");
		homePage.goToWorkTray();
		result = workTrayPage.verifyWorkTrayPage();
		Assert.assertTrue(result);
		workTrayPage.applyColumnSearchOnUserAndStatus("Cleansing", userName);
		Thread.sleep(1500);
		int numberOfAccount = workTrayPage.getNumberOfRows(); //can be 0 or 1
		if(numberOfAccount == 0) {
			workTrayPage.removeColumnSearchOnUserAndStatus();
		}
		else if(numberOfAccount > 0) {
			workTrayPage.clickOnAccount();
			workTrayPage.clickOnHoldAccount();
			result = utils.isSuccess();
			workTrayPage.removeColumnSearchOnUserAndStatus();
			Assert.assertTrue(result);
		}

		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		Thread.sleep(1500);
		sovImportPage = workTrayPage.startCleansing();
		result = sovImportPage.verifySovImportPage();
		Assert.assertTrue(result);
		
	}
	
	
}
