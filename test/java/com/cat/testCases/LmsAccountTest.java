package com.cat.testCases;


import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cat.pages.BasicAcDetailsPage;
import com.cat.pages.HomePage;
import com.cat.pages.LmsSearchPage;
import com.cat.pages.LoginPage;
import com.cat.pages.NewAccountFormPage;
import com.cat.utilities.DataProviderClass;

public class LmsAccountTest extends BaseClass {
	LoginPage objLogin;
	HomePage objHome;
	LmsSearchPage lmsSearchPage;
	NewAccountFormPage objNAFP;
	BasicAcDetailsPage basicAcDetailsPage;
	String accountName;    // = "ashish_case1_0104";
	
	@Test(priority = -1)
	public void goToLMS() {

		objLogin = new LoginPage();
		objHome = objLogin.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		lmsSearchPage = objHome.goToLMS();
		lmsSearchPage.verifyLMSSearchPage();
	}
	
	@Test
	public void searchAccount() throws InterruptedException  {
		accountName = readConfig.getDataFromConfig("LMS_AccountName");//xpath in last is depending on Account Name on account Name

		boolean result1 = lmsSearchPage.searchAccountByName(accountName);
		Assert.assertTrue(result1);
//		int numberOfAccounts = lmsSearchPage.getNumberOfRows();
//		Thread.sleep(3000);
//		boolean result;
//		if(numberOfAccounts  > 0)
//			result = true;
//		else
//			result = false;
//		Assert.assertEquals(numberOfAccounts, 1);	

//		Assert.assertTrue(result);
	}
	
	@Test
	public void verifyLmsFilterButton() {
		int actualSize = 9;
		lmsSearchPage.clickFilterButton();
		int expectedSize = driver.findElements(By.xpath("//span[@class='dx-header-filter dx-header-filter-empty']")).size();
		lmsSearchPage.clickFilterButton(); //removing the filter icon from columns
		Assert.assertEquals(actualSize, expectedSize);
	}
	
//	@Test
	public void verifyColumnSearchButton() {
		int actualSize = 9;
		lmsSearchPage.clickColumnSearchButton();
		int expectedSize = driver.findElements(By.xpath("//div[@class='dx-menu-horizontal']")).size();
		lmsSearchPage.clickColumnSearchButton(); // //removing the column search icon from columns
		Assert.assertEquals(actualSize, expectedSize);
	}
	
	@Test
	public void verifyNewAccountButton() {
		objNAFP = lmsSearchPage.clickAddNewAcButton();
		boolean flag = objNAFP.verifyNewAccountFormPage();
		driver.navigate().back();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void verifyUpdateAccountButton() {
		boolean result1 = lmsSearchPage.searchAccountByName(accountName);
		Assert.assertTrue(result1);
		basicAcDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		boolean result2 = basicAcDetailsPage.verifyBasicAccountDetailsTab();
		driver.navigate().back();
		Assert.assertTrue(result2);
	}
	
	
	@Test(dataProvider = "data-provider", dataProviderClass = DataProviderClass.class, priority = 4)
	public void verifyAdvanceFilter(String ddOption, String partyCodeValue, String prefixValue, String suffixValue,
			String extensionValue, String expectedAccountName) {

//		String actualAccountName = "";

		lmsSearchPage.advanceFilter(ddOption, partyCodeValue, prefixValue, suffixValue, extensionValue);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<WebElement> list = driver
				.findElements(By.xpath("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]"));
		if(list.size() > 0)
			Assert.assertTrue(true);
		
		else
			Assert.assertTrue(false);
		
		
//		for (WebElement e : list) {
//			// System.out.println(e.getText());
//			actualAccountName = e.getText();
//		}
//		Assert.assertTrue(expectedAccountName.equalsIgnoreCase(actualAccountName));
	}
	
	/*
	 * Test:verifyResetAdvanceFilter() It is based on assumption, at least there are
	 * 10 Accounts present on LMSSearch Page.
	 */
	
	@Test(priority = 5)
	public void verifyResetAdvanceFilter() {
		int expectedSize = 10;
		lmsSearchPage.resetAdvanceFilter();
		//list of all the elements present in column Account Number
		List<WebElement> list = driver.findElements(By.xpath("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[2]"));
		int actualSize = list.size();
		Assert.assertEquals(actualSize, expectedSize);
		
	}
	
	/* This test to validate the functionality of Basic Account Details Page */
//	@Test(dataProvider = "data-provider", dataProviderClass = DataProviderClass.class)
	public void basicAccountDetails(String currency, String grossPremium, String tax, String brokerage, String netPremium,
			String payoutBasis, String participation, String reinsuredName, String perils, String industryType, String policyForm,
			String userDef1, String userDef2, String userDef3, String userDef4) 
			throws ParseException, InterruptedException {
		basicAcDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		basicAcDetailsPage.update( currency,  grossPremium,  tax,  brokerage,  netPremium,
				 payoutBasis,  participation,  reinsuredName,  perils,  industryType,  policyForm,
				 userDef1,  userDef2,  userDef3,  userDef4);
		
	}


}
