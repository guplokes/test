package com.cat.testCases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cat.pages.BasicAcDetailsPage;
import com.cat.pages.HomePage;
import com.cat.pages.LmsSearchPage;
import com.cat.pages.LocationCoveragesPage;
import com.cat.pages.LocationFinancialPage;
import com.cat.pages.LoginPage;
import com.cat.utilities.Utils;

public class LmsLocationFinancialTest extends BaseClass {
	static LocationFinancialPage objLF;
	LoginPage loginPage;
	HomePage homePage;
	LmsSearchPage lmsSearchPage;
	BasicAcDetailsPage basicDetailsPage;
	//LocationDetailsPage locationDetailsPage;
	//LocationCoveragesPage locationCoveragesPage;
	LocationFinancialPage locationFinancialPage;
	private Utils utils;

	
	//Go to Location Financial Tab
	@Test(priority = -1, description = "Verify user is able to view LMS- Location FinancialPage")
	public void goToLocationFinancialTab() { 
		loginPage = new LoginPage();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());
		lmsSearchPage = homePage.goToLMS();
		basicDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		locationFinancialPage = basicDetailsPage.clickLocationFinancialTab();
		boolean flag = locationFinancialPage.verifyLocationFinancialTab();
		Assert.assertTrue(flag);
	}
	
	@DataProvider(name = "LocationFinancial")
	public static Object[][] readLocationCoverageData() throws Exception 
	{
		Thread.sleep(5000);
		objLF =new LocationFinancialPage();
		objLF.clickTabLocMaster();
		objLF.clickTabLocFinancial();
		
		if (extentRptflag) {
			logger = report.createTest("Modify Location Financial in the account.");
		}
		
		return objExcl.readExcelSheet("Location Financial");
	}
	
	
	@Test(priority = 19, dataProvider = "LocationFinancial")
	public void modifyLocationfinancial(String peril, String covType, String locNum, String lmtType, String lmt, 
			String dedType, String ded, String minDed, String maxDed) throws InterruptedException, AWTException {
		
		objLF.updateLocFinDetails( peril,  covType,  locNum,  lmtType,  lmt, dedType,  ded,  minDed,  maxDed);
	}
	
	@Test(priority = 2, description = "Verify the functionality of column chooser on Location Financial")
	public void  verifyColumnChooserOnLocationFinancial() {
		utils = new Utils();
		String[] columnNames = {"Limit", "Peril","Deductible"};
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length);
		
	}
	
	@Test(priority = 2, description = "Verify the functionality of Previous icon on Location Financial")
	public void verifyPreviousTabLocationFinancial() {
		locationFinancialPage.clickPreviousTab();
		boolean flag = new LocationCoveragesPage().verifyLocationCoveragesTab();
		locationFinancialPage.clickNextTab();
		Assert.assertTrue(flag);
	}
	
	/*
	 * @Test(priority = 3) public void verifyNextTabLocationCoverages() {
	 * locationFinancialPage.clickNextTab(); boolean flag = new
	 * LocationFinancialPage().verifyLocationFinancialTab();
	 * locationFinancialPage.clickPreviousTab(); Assert.assertTrue(flag); }
	 */
}
