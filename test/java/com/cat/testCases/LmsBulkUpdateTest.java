package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cat.pages.BasicAcDetailsPage;
import com.cat.pages.BulkUpdatePage;
import com.cat.pages.HomePage;
import com.cat.pages.LmsSearchPage;
import com.cat.pages.LocationCoveragesPage;
import com.cat.pages.LoginPage;

public class LmsBulkUpdateTest extends BaseClass {
	LoginPage loginPage;
	HomePage homePage;
	LmsSearchPage lmsSearchPage;
	BasicAcDetailsPage basicDetailsPage;
	LocationCoveragesPage locationCoveragesPage;
	BulkUpdatePage bulkUpdatePage;

	// Go to Lms Location Bulk Update Page
	@Test(priority = -1)
	public void goToBulkUpdatePage() {
		loginPage = new LoginPage();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());
		lmsSearchPage = homePage.goToLMS();
		basicDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		locationCoveragesPage = basicDetailsPage.clickLocationCoveragesTab();
		locationCoveragesPage.goToBulkUpdatePage();
		bulkUpdatePage = new BulkUpdatePage();
		boolean flag  = bulkUpdatePage.verifyTitle();
		Assert.assertTrue(flag);
		
	}
	
	//
	@Test(priority = 2)
	public void bulkUpdateFilter() {
		String entity = "PostalName";
		String operator = "Is Equals To";
		//String operator = "1";
		String value = "A";
		bulkUpdatePage.filterData(entity, operator, value);
	}
	
	@Test(priority = 1)
	public void bulkUpdateLocations() {
		String entity = "PostalName";
		String value = "A";
		bulkUpdatePage.updateData(entity, value);
		boolean flag = bulkUpdatePage.isSuccess();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 3)
	public void bulkUpdateDelete() {
		bulkUpdatePage.deleteData();
	}

}
