package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.cat.pages.BasicAcDetailsPage;
import com.cat.pages.HomePage;
import com.cat.pages.LmsSearchPage;
import com.cat.pages.LoginPage;
import com.cat.pages.PerilLevelFinancialDetailsPage;
import com.cat.pages.PolicyBlanketDetailsPage;
import com.cat.pages.PolicyLayerPage;
import com.cat.utilities.DataProviderClass;
import com.cat.utilities.Utils;

public class LmsPolicyBlanketTest extends BaseClass {
	LoginPage loginPage;
	HomePage homePage;
	LmsSearchPage lmsSearchPage;
	BasicAcDetailsPage basicDetailsPage;
	PolicyBlanketDetailsPage policyBlanketDetailsPage;
	Utils utils;
	String lmsAccountName;   // = "ashish_case1_0104";
	
	//Go to Policy Blanket Details Tab
	@Test(priority = -1, description = "Verify user is able to view LMS- Policy Blanket Details Page")
	public void goToPolicyBlanketDetailsTab() {
		loginPage = new LoginPage();
		utils = new Utils();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());
		lmsSearchPage = homePage.goToLMS();
		lmsAccountName = readConfig.getDataFromConfig("LMS_AccountName");
		lmsSearchPage.searchAccountByName(lmsAccountName);
		basicDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		policyBlanketDetailsPage = basicDetailsPage.clickPolicyBlanketDetailsTab();
		boolean flag = policyBlanketDetailsPage.verifyPolicyBlanketDetailsTab();
		Assert.assertTrue(flag);
	}
	@Test(priority = 0, description = "Verify functionality of column search on Policy Blanket")
	public void verifyColumnSearch() {
		String perilName = objExcl.getStringData("addPolicyBlanketDetails", 2, 0);
		policyBlanketDetailsPage.applyColumnSearch(perilName);
		int numberOfLayers = policyBlanketDetailsPage.getNumberOfPolicyBlanketLayers();
		policyBlanketDetailsPage.removeColumnSearch();		
		Assert.assertEquals(numberOfLayers, 1);
		
	}
	@Test(priority = 0, description = "Verify Add Policy Blanket Button without selecting Peril on Policy Blanket")
	public void verifyAddPolicyBlanketWithInvalidValues() {
		policyBlanketDetailsPage.clickOnAddBlanketBtn();
		boolean result = utils.isWarning();
		Assert.assertTrue(result);
	}
	@Test(priority = 1, description = "Verify blanket & maximum deductible textbox with invalid values on Policy Blanket")
	public void validateTextboxWithInvalidValues() {
		String perilName = objExcl.getStringData("addPolicyBlanketDetails", 2, 0);
		String blanketDeductible = "=" + objExcl.getStringData("addPolicyBlanketDetails", 2, 4);
		String maximumDeductible = "=" + objExcl.getStringData("addPolicyBlanketDetails", 2, 5);
		policyBlanketDetailsPage.selectPerilForBlanket(perilName);
		policyBlanketDetailsPage.clickOnAddBlanketBtn();
		policyBlanketDetailsPage.enterBlanketDeductible(blanketDeductible);
		boolean result1 = utils.isError();
		policyBlanketDetailsPage.enterMaximumDeductible(maximumDeductible);
		boolean result2 = utils.isError();
		policyBlanketDetailsPage.clickOnCancelBtn();		
		Assert.assertTrue(result1);
		Assert.assertTrue(result2);		
	}
	@Test(priority = 2, description = "Verify functionality of cancel Button on Policy Blanket")
	public void verifyCancelButton() {
		String perilName = objExcl.getStringData("addPolicyBlanketDetails", 2, 0);
		policyBlanketDetailsPage.selectPerilForBlanket(perilName);
		policyBlanketDetailsPage.clickOnAddBlanketBtn();
		int numberOfLayersBefore = policyBlanketDetailsPage.getNumberOfPolicyBlanketLayers();
		policyBlanketDetailsPage.clickOnCancelBtn();
		int numberOfLayersAfter = policyBlanketDetailsPage.getNumberOfPolicyBlanketLayers();
		Assert.assertEquals(numberOfLayersAfter, numberOfLayersBefore-1);	
		
	}
	@Test(priority = 3, description = "Verify user is not able to add blanket with peril codes already present on Policy Blanket")
	public void verifyAddBlanketWithExistingPerilsCodes() {
		String perilName = objExcl.getStringData("addPolicyBlanketDetails", 2, 0);
		policyBlanketDetailsPage.selectPerilForBlanket(perilName);
		policyBlanketDetailsPage.clickOnAddBlanketBtn();
		policyBlanketDetailsPage.clickOnSaveBtn();
		boolean result = utils.isWarning();
		int numberOfSavedLayers = policyBlanketDetailsPage.getNumberOfSavedPolicyBlanketLayers();
		int numberOfLayers = policyBlanketDetailsPage.getNumberOfPolicyBlanketLayers();
		policyBlanketDetailsPage.clickOnCancelBtn();
		Assert.assertEquals(numberOfSavedLayers, numberOfLayers - 1);
		Assert.assertTrue(result);		
	}
	
	@Test(priority = 4, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class,
			description = "Add policy Blanket data on Policy Blanket Details")
	public void addPolicyBlanketDetails(String Peril,String dedCode, String dedType, String dedBasis, String blanketDed, 
			String maxDed, String minDed, String limitCode, String limitType, String limitBasis, String limitAmt) {			

		int numberOfLayerBefore = policyBlanketDetailsPage.getNumberOfSavedPolicyBlanketLayers();
		policyBlanketDetailsPage.addPolicyBlanketData(Peril, dedCode, dedType, dedBasis, blanketDed, maxDed, minDed,
				limitCode, limitType, limitBasis, limitAmt);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numberOfLayerAfter = policyBlanketDetailsPage.getNumberOfSavedPolicyBlanketLayers();
		Assert.assertEquals(numberOfLayerAfter, numberOfLayerBefore + 1);		
	}
	
	@Test(priority = 5, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class,
			description = "Modify data of Policy Blanket Details ")
	public void modifyPolicyBlanketDetails(String perilName,String dedCode, String dedType, String dedBasis, String blanketDed, 
			String maxDed, String minDed, String limitCode, String limitType, String limitBasis, String limitAmt) {
		policyBlanketDetailsPage.applyColumnSearch(perilName);
		
		policyBlanketDetailsPage.modifyPolicyBlanketData(perilName, dedCode, dedType, dedBasis, blanketDed, maxDed,
				minDed, limitCode, limitType, limitBasis, limitAmt);
		policyBlanketDetailsPage.removeColumnSearch();
			
		}
	@Test(priority = 6, description = "Verify No delete button on Policy Blanket")
	public void verifyNoDeleteBtnPolicyBlanket() {
		int numberofLayerBefore = policyBlanketDetailsPage.getNumberOfPolicyBlanketLayers();
		policyBlanketDetailsPage.clickOnDeleteBtn();
		policyBlanketDetailsPage.clickOnDeleteNoBtn();
		int numberofLayerAfter = policyBlanketDetailsPage.getNumberOfPolicyBlanketLayers();
		Assert.assertEquals(numberofLayerBefore, numberofLayerAfter);
		
	}
	
	@Test(priority = 7, description = "Verify Yes delete button on Policy Blanket")
	public void verifyYesDeleteBtnPolicyBlanket()  {

	    String perilName =	objExcl.getStringData("addPolicyBlanketDetails", 2, 0);
	    int numberofLayerBefore = policyBlanketDetailsPage.getNumberOfPolicyBlanketLayers();
	    policyBlanketDetailsPage.applyColumnSearch(perilName);
		policyBlanketDetailsPage.clickOnDeleteBtn();
		policyBlanketDetailsPage.clickOnDeleteYesBtn();
		policyBlanketDetailsPage.removeColumnSearch();
		int numberofLayerAfter = policyBlanketDetailsPage.getNumberOfPolicyBlanketLayers();
		Assert.assertEquals(numberofLayerAfter, numberofLayerBefore - 1);
	}
	@Test(priority =8, description = "Verify functionality of Column Chooser on Policy Blanket")
	public void verifyColumnChooserOnPolicyBlanket() {
		String[] columnNames = {"Deductible Type", "Minimum Deductible", "Limit Code", "Limit Amount"};
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length);
	}
	
	@Test(priority = 9, description = "Verify the functionality of Previous icon on Policy Blanket")
	public void verifyPreviousTabPolicyBlanket() {
		policyBlanketDetailsPage.clickPreviousTab();
		boolean flag = new PolicyLayerPage().verifyPolicyLayerTab();
		policyBlanketDetailsPage.clickNextTab();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 10, description = "Verify the functionality of Next icon on Policy Blanket")
	public void verifyNextTabPolicyBlanket() {
		policyBlanketDetailsPage.clickNextTab();
		boolean flag = new PerilLevelFinancialDetailsPage().verifyPerilLevelFinancialDetailsTab();
		policyBlanketDetailsPage.clickPreviousTab();
		Assert.assertTrue(flag);
	}
}



