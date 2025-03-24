package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.WorkTrayPage;
import com.cat.utilities.ReadExcel;

public class WorkTrayTest extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	WorkTrayPage workTrayPage;
	ReadExcel readExcel = new ReadExcel("CMRTestData");
	String accountName; //= "Test_AccountN43";


	@Test(priority = -1)
	public void goToWorkTrayPage() {
		accountName = readConfig.getDataFromConfig("AccountName");
		loginPage = new LoginPage();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		workTrayPage = homePage.goToWorkTray();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block(Remove sleep later, no need of it)
			e.printStackTrace();
		}

		boolean flag = workTrayPage.verifyWorkTrayPage() ;
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 1)
	public void verifyColumnSearch() throws InterruptedException {
		//String submissionNumber = "test123";
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		Thread.sleep(1500);
		int numberOfRows = workTrayPage.getNumberOfRows();
		workTrayPage.removeColumnSearchOnAccountName();
		Assert.assertEquals(numberOfRows, 1);
	}
	@Test(priority=2)
	public void verifyDeleteAccount() {
		//String submissionNumber = "test123";
		String expectedText = "delete Account";
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		String actualText = workTrayPage.deleteAccount();
		boolean flag = actualText.contains(expectedText);
		Assert.assertTrue(flag);
		workTrayPage.removeColumnSearchOnAccountName();
		
	}
	@DataProvider(name = "Advanced Search")
	public Object[][] readAdvancedSearchData() throws Exception {
		
		return readExcel.readExcelSheet("WorkTray AdvSearch");
	}
	
	/*
	 * This test method validated number of Accounts after applying advanced search.
	 * Here, based on data in excel number of accounts returned should be 1.
	 */
	
	@Test(priority =3, dataProvider = "Advanced Search")
	public void verifyAdvancedSearch(String accountName, String lob,String requestType, String accounts_Team, String accountType, String accountStatus) {
		int numberOfAccounts = workTrayPage.applyAdvancedSearch(accountName, lob, requestType, accounts_Team, accountType, accountStatus);
		Assert.assertEquals(numberOfAccounts, 1);
		
	}
	@DataProvider(name = "Complexity")
	public Object[][] readFillComplexityData() throws Exception {
		
		return readExcel.readExcelSheet("Complexity Matrix");
	}
	@Test(priority = 4, dataProvider = "Complexity")
	public void fillComplexity(String noOfLocations, String perilRegion, String cleansingComplexity, String financialTerms, String Complexity) {
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		workTrayPage.enterComplexityData(noOfLocations, perilRegion, cleansingComplexity, financialTerms, Complexity);
		boolean flag = workTrayPage.isSuccess();
		workTrayPage.removeColumnSearchOnAccountName();
		Assert.assertTrue(flag);
	}
	@Test(priority = 5)
	public void riskInspection() {
		String timeInMins = "10";
		String comments = "Risk Inspection was performed";
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		workTrayPage.enterTaskDetails(timeInMins, comments, "Risk Inspection");
		boolean flag = workTrayPage.isSuccess();
		workTrayPage.clickOnAccount();
		workTrayPage.removeColumnSearchOnAccountName();
		Assert.assertTrue(flag);
	}
	@Test(priority = 6)
	public void extractionFromPDF() {
		String timeInMins = "10";
		String comments = "Extraction from pdf  was performed";
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		workTrayPage.enterTaskDetails(timeInMins, comments, "Pdf Extraction");
		boolean flag = workTrayPage.isSuccess();
		workTrayPage.clickOnAccount();
		workTrayPage.removeColumnSearchOnAccountName();
		Assert.assertTrue(flag);
	}
	@Test(priority = 7)
	public void processDocumentation() {
		String timeInMins = "10";
		String comments = "Process Documentation was performed";
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		workTrayPage.enterTaskDetails(timeInMins, comments, "Process Documentation");
		boolean flag = workTrayPage.isSuccess();
		workTrayPage.clickOnAccount();
		workTrayPage.removeColumnSearchOnAccountName();
		Assert.assertTrue(flag);
	}
	@Test(priority = 8)
	public void queryResolution() {
		String timeInMins = "10";
		String comments = "Query Resolution was performed";
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		workTrayPage.enterTaskDetails(timeInMins, comments, "Query Resolution");
		boolean flag = workTrayPage.isSuccess();
		workTrayPage.clickOnAccount();
		workTrayPage.removeColumnSearchOnAccountName();
		Assert.assertTrue(flag);
	}
	@DataProvider(name = "Delegate Activity")
	public Object[][] readDelagateActivityData() throws Exception {
		
		return readExcel.readExcelSheet("Delegate Activity");
	}
//	@Test(priority = 9, dataProvider = "Delegate Activity")
	public void delegateActivity(String parentTask, String task, String analyst) {
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		workTrayPage.enterDelegateActivityData(parentTask, task, analyst);
		boolean flag = workTrayPage.isSuccess();
		workTrayPage.clickOnAccount();
		workTrayPage.removeColumnSearchOnAccountName();
		Assert.assertTrue(flag);
	}
	@Test(priority = 10)
	public void geoSpatial() {
		String timeInMins = "10";
		String comments = "Geospatial was performed";
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		workTrayPage.enterTaskDetails(timeInMins, comments, "Geospatial");
		boolean flag = workTrayPage.isSuccess();
		workTrayPage.clickOnAccount();
		workTrayPage.removeColumnSearchOnAccountName();
		Assert.assertTrue(flag);
	}
	@Test(priority = 11)
	public void toolDevelopment() {
		String timeInMins = "10";
		String comments = "Tool Developmnet or Enhancement was performed";
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		workTrayPage.enterTaskDetails(timeInMins, comments, "Tool Development");
		boolean flag = workTrayPage.isSuccess();
		workTrayPage.clickOnAccount();
		workTrayPage.removeColumnSearchOnAccountName();
		Assert.assertTrue(flag);
	}
	@Test(priority = 12)
	public void viewRecordTimeDetails() throws InterruptedException {
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		boolean flag = workTrayPage.validateViewRecordTimeDetails();
		Assert.assertTrue(flag);
	}
	@Test(priority = 13)
	public void viewProcessDetails() throws InterruptedException {
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		boolean flag = workTrayPage.validateViewProcessDetails();
		Assert.assertTrue(flag);
	}
	@Test(priority = 14)
	public void addAdditionalEvaluation() throws InterruptedException {
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		boolean flag = workTrayPage.validateAdditionalEvaluation();
		Assert.assertTrue(flag);		
	}
	@Test(priority = 15)
	public void updateProposedDate() {
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.modifyProposedDate();
		boolean flag = workTrayPage.isSuccess();
		workTrayPage.removeColumnSearchOnAccountName();
		Assert.assertTrue(flag);			
	}
//	@Test(priority = 16)
	public void queryLog() throws InterruptedException {
		String query = "Missing Document - MRF not attached";
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		boolean flag = workTrayPage.validateQueryLog(query);
		Assert.assertTrue(flag);
		
	}
}
