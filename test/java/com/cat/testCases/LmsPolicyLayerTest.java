package com.cat.testCases;

import java.awt.AWTException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cat.pages.BasicAcDetailsPage;
import com.cat.pages.HomePage;
import com.cat.pages.LmsSearchPage;
import com.cat.pages.LoginPage;
import com.cat.pages.PolicyBlanketDetailsPage;
import com.cat.pages.PolicyLayerPage;
import com.cat.utilities.DataProviderClass;
import com.cat.utilities.Utils;

public class LmsPolicyLayerTest extends BaseClass {
	LoginPage loginPage;
	HomePage homePage;
	PolicyLayerPage policyLayerPage;
	LmsSearchPage lmsSearchPage;
	BasicAcDetailsPage basicAcDetailsPage;
	Utils utils;
	String lmsAccountName;   // = "ashish_case1_0104";
	// ReadExcel objExcl;

	/*
	 * @DataProvider(name = "PolicyLayerData") public Object[][] readPLData() throws
	 * Exception { if (extentRptflag) { logger =
	 * report.createTest("Add Policy Layer Data into an account"); } objPL= new
	 * PolicyLayerPage();
	 * 
	 * //Go to Policy Layer Tab objPL.clickPolicyLayerTab(); return
	 * objExcl.readExcelSheet("Policy Layar Data"); }
	 */

	@Test(priority = -1, description = "Verify user is able to view LMS- Policy Layer Page")
	public void goToPolicyLayerTab() {
		loginPage = new LoginPage();
		utils = new Utils();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());
		lmsSearchPage = homePage.goToLMS();
		lmsAccountName = readConfig.getDataFromConfig("LMS_AccountName");
		lmsSearchPage.searchAccountByName(lmsAccountName);
		basicAcDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		policyLayerPage = basicAcDetailsPage.clickPolicyLayerTab();
		boolean flag = policyLayerPage.verifyPolicyLayerTab();
		Assert.assertTrue(flag);
	}

	@Test(priority = 1, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class,
			description = "Verify user is able to add a Policy layer Row on Policy Layer grid" )
	public void addPolicyLayer(String layerName, String participation, String layerLimit, String layerAttachment) throws InterruptedException {
		//int numberOfLayerBefore = driver.findElements(By.xpath("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[2]"))
		int numberOfLayerBefore = policyLayerPage.getNumberOfSavedPolicyLayers();
		policyLayerPage.addNewLayer(layerName, participation, layerLimit, layerAttachment);
		Thread.sleep(500);
		int numberOfLayerAfter = policyLayerPage.getNumberOfSavedPolicyLayers();
		Assert.assertEquals(numberOfLayerAfter, numberOfLayerBefore + 1);
	}
	@Test(priority = 2, description = "Verify the LayerLimit and LayerAttachment textbox with invalid values on Policy Layer")
	public void validateTextboxwithInvaildValues() {
		String layerLimit = "=" + objExcl.getStringData("addPolicyLayer", 1, 2) ;
		String layerAttachment = "=" + objExcl.getStringData("addPolicyLayer", 1, 3);
		policyLayerPage.clickOnAddNewLayerBtn();
		policyLayerPage.enterLayerLimit(layerLimit);
		policyLayerPage.clickOnSaveBtn();
		boolean result1  = utils.isError();
		Assert.assertTrue(result1);
		policyLayerPage.enterLayerAttachment(layerAttachment);
		policyLayerPage.clickOnSaveBtn();
		boolean result2  = utils.isError();
		Assert.assertTrue(result2);		
		System.out.println(policyLayerPage.getNumberOfPolicyLayers());
	}
	@Test(priority = 2, description = "Verify functionality of cancel Button on Policy Layer")
	public void verifyCancelButton() {
		int numberOfLayersBefore = policyLayerPage.getNumberOfPolicyLayers();
		policyLayerPage.clickOnCancelBtn();
		int numberOfLayersAfter = policyLayerPage.getNumberOfPolicyLayers();
		Assert.assertEquals(numberOfLayersAfter, numberOfLayersBefore - 1);
	}
	@Test(priority = 3,dependsOnMethods = "addPolicyLayer", description = "Verify functionality of column search on Policy Layer")
	public void verifyColumnSearch() throws InterruptedException {
		String layerName = objExcl.getStringData("addPolicyLayer", 1, 0);
		policyLayerPage.applyColumnSearch(layerName);
		Thread.sleep(500);
		int numberOfLayerAfter = policyLayerPage.getNumberOfPolicyLayers();
		policyLayerPage.removeColumnSearch();
		Assert.assertEquals(numberOfLayerAfter,  1);		
	}
	

	/*
	 * @DataProvider(name = "ModifyPolicyLayerData") public Object[][]
	 * readPLModificationData() throws Exception { return
	 * objExcl.readExcelSheet("Modify Policy Layer Data"); }
	 */

	@Test(priority = 4, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class,
			description = "Verify user is able to Modify an existing Layer on Policy layer")
	public void modifyPolicyLayer(String layerToModify, String layerName, String participation, String layerLimit,
			String layerAttachment) throws InterruptedException, AWTException {

		policyLayerPage.applyColumnSearch(layerToModify);
		policyLayerPage.modifyALayer(layerName, participation, layerLimit, layerAttachment);
		policyLayerPage.removeColumnSearch();
		policyLayerPage.applyColumnSearch(layerName);
		Thread.sleep(500);
		int numberOfLayers = policyLayerPage.getNumberOfPolicyLayers();
		policyLayerPage.removeColumnSearch();
		Assert.assertEquals(numberOfLayers, 1);
	}

	@Test(priority = 6, description = "Verify user is able to Clone an existing Layer on Policy layer")
	public void clonePolicyLayer()  {
		String layerName = objExcl.getStringData("addPolicyLayer", 1, 0);
		int numberOfLayerBefore = policyLayerPage.getNumberOfPolicyLayers();
		policyLayerPage.applyColumnSearch(layerName);
			
		policyLayerPage.cloneALayer();
		policyLayerPage.removeColumnSearch();
		
		int numberOfLayerAfter = policyLayerPage.getNumberOfPolicyLayers();
		Assert.assertEquals(numberOfLayerAfter, numberOfLayerBefore + 1);
	}
	@Test(priority = 7, description = "Verify No delete button on Policy Layer")
	public void verifyNoDeleteBtnPolicyLayer() {
		int numberofLayerBefore = policyLayerPage.getNumberOfPolicyLayers();
		policyLayerPage.clickOnDeleteNoBtn();
		int numberofLayerAfter = policyLayerPage.getNumberOfPolicyLayers();
		Assert.assertEquals(numberofLayerBefore, numberofLayerAfter);
		
	}

	@Test(priority = 7, description = "Verify Yes delete button on Policy Layer\"")
	public void verifyDeleteYesBtnPolicyLayer()  {

		String layerName = objExcl.getStringData("addPolicyLayer", 3, 0);
		int numberOfLayerBefore = policyLayerPage.getNumberOfPolicyLayers();
		policyLayerPage.applyColumnSearch(layerName);		
		policyLayerPage.clickOnDeleteYesBtn();
		policyLayerPage.removeColumnSearch();	
		int numberOfLayerAfter = policyLayerPage.getNumberOfPolicyLayers();
		Assert.assertEquals(numberOfLayerAfter, numberOfLayerBefore - 1);
	}
	@Test(priority = 8)
	public void verifyColumnChooser() {
		String[] columnNames = {"Participation", "LayerLimit"};
		boolean result  = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length);
	}
	
	@Test(priority = 8, description = "Verify the functionality of Previous icon on Policy Layer")
	public void verifyPreviousTabPolicyLayer() {
		policyLayerPage.clickPreviousTab();
		boolean flag = basicAcDetailsPage.verifyBasicAccountDetailsTab();
		policyLayerPage.clickNextTab();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 9, description = "Verify the functionality of Next icon on Policy Layer")
	public void verifyNextTabPolicyLayer() {
		policyLayerPage.clickNextTab();
		boolean flag = new PolicyBlanketDetailsPage().verifyPolicyBlanketDetailsTab();
		policyLayerPage.clickPreviousTab();
		Assert.assertTrue(flag);
	}
}
