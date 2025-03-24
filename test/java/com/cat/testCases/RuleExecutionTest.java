package com.cat.testCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.MappingSummaryPage;
import com.cat.pages.MergeSOVPage;
import com.cat.pages.RMTemplatePage;
import com.cat.pages.RuleEditorPage;
import com.cat.pages.RuleEnginePage;
import com.cat.pages.SOVImportPage;
import com.cat.pages.SanityPage;
import com.cat.pages.WorkTrayPage;
import com.cat.utilities.ReadExcel;
import com.cat.utilities.Utils;

public class RuleExecutionTest extends BaseClass {
	LoginPage loginPage;
	Utils utils;
	HomePage homePage;	
	WorkTrayPage workTrayPage;
	SOVImportPage sovImportPage;
	MergeSOVPage mergeSovPage;
	MappingSummaryPage mappingSummaryPage;
	RMTemplatePage rmTemplatePage;
	RuleEnginePage ruleEnginePage;
	RuleEditorPage ruleEditor;
	SanityPage sanityPage;
	ReadExcel readExcel = new ReadExcel("CMRTestData");
//	String sourceContext = "OED Sanity";
	String sourceContext = readExcel.getStringData("Rule Engine", 2, 2);
	ArrayList<String> sovDataRulesList = new ArrayList<String>();
	ArrayList<String> oedSanityRulesList = new ArrayList<String>();

	@Test(priority = -1, description = "Verify user is able to view Rule Engine Page")
	public void goToRuleEnginePage() {
		loginPage = new LoginPage();
		utils = new Utils();
		ruleEditor = new RuleEditorPage();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());
		ruleEnginePage = homePage.goToRuleEngine();
		boolean result = ruleEnginePage.verifyRuleEnginePage();
		Assert.assertTrue(result);		
//		ruleEnginePage.ruleA();
		
	}
	


@DataProvider(name = "Add SOV Data RuleSet_1")
public Object[][] readSOVRule1Data() throws Exception {
	return readExcel.readExcelSheet("Rule Engine", 2, 2, 24);
}

@Test(priority = 2, dataProvider = "Add SOV Data RuleSet_1", description = "Verify user is able to create Sov Data Rule Set-I with If-then condition type")
public void createSovDataRule1(String ruleName, String ruleType, String sourceContext, String source, String sourceEntity,String target, String targetEntity, String lob, String ruleExecutionType,
		String ruleConditionType, String message, String ifField1, String ifOperator1, String ifValueType1, String ifValue1, String ifOprator1, String ifField2, String ifOperator2, String ifValueType2,
		String ifValue2, String ifOprator2, String thenField1, String thenValueType1, String thenValue1) 
{
	ruleEnginePage.clickOnClearSourceContextBtn();
	ruleEnginePage.enteringNewRuleDetails(ruleName, ruleType, sourceContext, source, sourceEntity, target, targetEntity,
			lob, ruleExecutionType, ruleConditionType,  message);
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	ruleEditor.addConditionForRule(ifField1, ifOperator1, ifValueType1, ifValue1);
	if(!ifOprator1.isEmpty())
		ruleEditor.selectingOpratorForFirstRow(ifOprator1);
	
	ruleEditor.addConditionForRule(ifField2, ifOperator2, ifValueType2, ifValue2);
	if(!ifOprator2.isEmpty())
		ruleEditor.selectingOpratorAfterFirstRow(ifOprator2);
	
	ruleEditor.addThenConditionForRule(thenField1, thenValueType1, thenValue1);
	ruleEditor.clickOnSaveButton();
	boolean result = utils.isSuccess();
	if(result == false)
		driver.navigate().back();
	Assert.assertTrue(result);
	sovDataRulesList.add(ruleName);
	ruleEnginePage.selectSourceContext(sourceContext);
	result = ruleEnginePage.isRuleSetPresent(ruleName);
	Assert.assertTrue(result);

}

@DataProvider(name = "Add SOV Data RuleSet_2")
public Object[][] readSOVRule2Data() throws Exception {
	return readExcel.readExcelSheet("Rule Engine", 7, 7, 38);
}

@Test(priority = 3, dataProvider = "Add SOV Data RuleSet_2", description = "Verify user is able to create Sov Data Rule Set-II with If-then Else-If condition type")
public void createSovDataRule2(String ruleName, String ruleType, String sourceContext, String source, String sourceEntity,String target, String targetEntity, String lob, String ruleExecutionType,
		String ruleConditionType, String message, String ifField1, String ifOperator1, String ifValueType1, String ifValue1, String ifOprator1, String ifField2, String ifOperator2, String ifValueType2,
		String ifValue2, String ifOprator2, String thenField1, String thenValueType1, String thenValue1, String thenField2, String thenValueType2, String thenValue2, String thenField3, String thenValueType3,
		String thenValue3, String elseIfField1, String elseIfOperator1, String elseIfValueType1, String elseIfValue1, String elseIfOprator1, String thenValue4, String thenValue5, String thenValue6) throws InterruptedException 
{
	ruleEnginePage.clickOnClearSourceContextBtn();
	ruleEnginePage.enteringNewRuleDetails(ruleName, ruleType, sourceContext, source, sourceEntity, target, targetEntity,
			lob, ruleExecutionType, ruleConditionType,  message);
	
	Thread.sleep(1000);
	
	ruleEditor.addConditionForRule(ifField1, ifOperator1, ifValueType1, ifValue1);
	if(!ifOprator1.isEmpty())
		ruleEditor.selectingOpratorForFirstRow(ifOprator1);
	
	ruleEditor.addConditionForRule(ifField2, ifOperator2, ifValueType2, ifValue2);
	if(!ifOprator2.isEmpty())
		ruleEditor.selectingOpratorAfterFirstRow(ifOprator2);
	
	ruleEditor.addThenConditionForRule(thenField1, thenValueType1, thenValue1);
	ruleEditor.addActionForIfThen();
	
	ruleEditor.addThenConditionForRule(thenField2, thenValueType2, thenValue2);
	ruleEditor.addActionForIfThen();
	
	ruleEditor.addThenConditionForRule(thenField3, thenValueType3, thenValue3);
	
	ruleEditor.addConditionForRule(elseIfField1, elseIfOperator1, elseIfValueType1, elseIfValue1);
	if(!elseIfOprator1.isEmpty())
		ruleEditor.selectingOpratorForFirstRow(elseIfOprator1);
	
	ruleEditor.addThenConditionForRule(thenField1, thenValueType1, thenValue4);
	ruleEditor.addActionForElseIfThen();
	
	ruleEditor.addThenConditionForRule(thenField2, thenValueType2, thenValue5);
	ruleEditor.addActionForElseIfThen();
	
	ruleEditor.addThenConditionForRule(thenField3, thenValueType3, thenValue6);
	
	ruleEditor.clickOnSaveButton();
	boolean result = utils.isSuccess();
	if(result == false)
		driver.navigate().back();
	Assert.assertTrue(result);
	sovDataRulesList.add(ruleName);
	ruleEnginePage.selectSourceContext(sourceContext);
	result = ruleEnginePage.isRuleSetPresent(ruleName);
	Assert.assertTrue(result);

}

@DataProvider(name = "Read OED Sanity Rule_1 data")
public Object[][] readOEDSanityRule1Data() throws Exception {
	return readExcel.readExcelSheet("Rule Engine", 11, 11, 21);
}

@Test(priority = 4, dataProvider = "Read OED Sanity Rule_1 data", description = "Verify user is able to create Oed Sanity Rule Set-I ")
public void createOEDSanityRule1(String ruleName, String ruleType, String sourceContext, String source, String sourceEntity,String target, String targetEntity, String lob, String ruleExecutionType,
		String ruleConditionType, String message, String ifField1, String ifOperator1, String ifValueType1, String ifValue1, String ifOprator1, String ifField2, String ifOperator2, String ifValueType2,
		String ifValue2, String ifOprator2) throws InterruptedException
{
	ruleEnginePage.clickOnClearSourceContextBtn();
	ruleEnginePage.enteringNewRuleDetails(ruleName, ruleType, sourceContext, source, sourceEntity, target, targetEntity,
			lob, ruleExecutionType, ruleConditionType,  message);
	
	Thread.sleep(1000);
	
	ruleEditor.addConditionForRule(ifField1, ifOperator1, ifValueType1, ifValue1);
	if(!ifOprator1.isEmpty())
		ruleEditor.selectingOpratorForFirstRow(ifOprator1);
	
	ruleEditor.addConditionForRule(ifField2, ifOperator2, ifValueType2, ifValue2);
	if(!ifOprator2.isEmpty())
		ruleEditor.selectingOpratorAfterFirstRow(ifOprator2);
	
	ruleEditor.clickOnSaveButton();
	boolean result = utils.isSuccess();
	if(result == false)
		driver.navigate().back();
	Assert.assertTrue(result);
	oedSanityRulesList.add(ruleName);
//	ruleEnginePage.selectSourceContext(sourceContext);
//	ruleEnginePage.displayNRulesOnGrid(100);
//	result = ruleEnginePage.isRuleSetPresent(ruleName);
//	Assert.assertTrue(result);

	

}

@DataProvider(name = "Read OED Sanity Rule_2 data")
public Object[][] readOEDSanityRule2Data() throws Exception {
	return readExcel.readExcelSheet("Rule Engine", 15, 15, 26);
}

@Test(priority = 5, dataProvider = "Read OED Sanity Rule_2 data", description = "Verify user is able to create Oed Sanity Rule Set-II")
public void createOEDSanityRule2(String ruleName, String ruleType, String sourceContext, String source, String sourceEntity,String target, String targetEntity, String lob, String ruleExecutionType,
		String ruleConditionType, String message, String ifField1, String ifOperator1, String ifValueType1, String ifValue1, String ifOprator1, String ifField2, String ifOperator2, String ifValueType2,
		String ifValue2, String ifOprator2, String ifField3, String ifOperator3, String ifValueType3, String ifValue3, String ifOprator3) throws InterruptedException
{
	ruleEnginePage.clickOnClearSourceContextBtn();
	ruleEnginePage.enteringNewRuleDetails(ruleName, ruleType, sourceContext, source, sourceEntity, target, targetEntity,
			lob, ruleExecutionType, ruleConditionType,  message);
	
	Thread.sleep(1500);
	
	ruleEditor.addConditionForRule(ifField1, ifOperator1, ifValueType1, ifValue1);
	if(!ifOprator1.isEmpty())
		ruleEditor.selectingOpratorForFirstRow(ifOprator1);
	
	ruleEditor.addConditionForRule(ifField2, ifOperator2, ifValueType2, ifValue2);
	if(!ifOprator2.isEmpty())
		ruleEditor.selectingOpratorAfterFirstRow(ifOprator2);
	
	ruleEditor.addConditionForRule(ifField3, ifOperator3, ifValueType3, ifValue3);
	if(!ifOprator3.isEmpty())
		ruleEditor.selectingOpratorAfterFirstRow(ifOprator3);
	
	ruleEditor.clickOnSaveButton();
	boolean result = utils.isSuccess();
	if(result == false)
		driver.navigate().back();
	Assert.assertTrue(result);
	oedSanityRulesList.add(ruleName);
//	ruleEnginePage.selectSourceContext(sourceContext);
//	ruleEnginePage.displayNRulesOnGrid(100);
//	result = ruleEnginePage.isRuleSetPresent(ruleName);
//	Assert.assertTrue(result);

	
}

@Test(priority = 6, dataProvider = "Read OED Sanity Rule_2 data")
@DataProvider(name = "Read OED Sanity Rule_3 data")
public Object[][] readOEDSanityRule3Data() throws Exception {
	return readExcel.readExcelSheet("Rule Engine", 19, 19, 29);
}

@Test(priority = 5, dataProvider = "Read OED Sanity Rule_3 data", description = "Verify user is able to create Oed Sanity Rule Set-III")
public void createOEDSanityRule3(String ruleName, String ruleType, String sourceContext, String source, String sourceEntity,String target, String targetEntity, String lob, String ruleExecutionType,
		String ruleConditionType, String message, String ifField1, String ifOperator1, String ifValueType1, String ifValue1, String ifOprator1, String ifField2, String ifOperator2, String ifValueType2,
		String ifValue2, String ifOprator2, String ifField3, String ifOperator3, String ifValueType3, String ifValue3, String ifOprator3, String thenField, String thenValueType, String thenValue)
{
//	ruleEnginePage.clickOnClearSourceContextBtn();
	ruleEnginePage.enteringNewRuleDetails(ruleName, ruleType, sourceContext, source, sourceEntity, target, targetEntity,
			lob, ruleExecutionType, ruleConditionType,  message);
	try {
		Thread.sleep(1500);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	ruleEditor.addConditionForRule(ifField1, ifOperator1, ifValueType1, ifValue1);
	if(!ifOprator1.isEmpty())
		ruleEditor.selectingOpratorForFirstRow(ifOprator1);
	
	ruleEditor.addConditionForRule(ifField2, ifOperator2, ifValueType2, ifValue2);
	if(!ifOprator2.isEmpty())
		ruleEditor.selectingOpratorAfterFirstRow(ifOprator2);
	
	ruleEditor.addConditionForRule(ifField3, ifOperator3, ifValueType3, ifValue3);
	if(!ifOprator3.isEmpty())
		ruleEditor.selectingOpratorAfterFirstRow(ifOprator3);
	
	ruleEditor.addThenConditionForRule(thenField, thenValueType, thenValue);
	
	ruleEditor.clickOnSaveButton();
	boolean result = utils.isSuccess();
	if(result == false)
		driver.navigate().back();
	Assert.assertTrue(result);
	
	oedSanityRulesList.add(ruleName);
	
//	ruleEnginePage.selectSourceContext(sourceContext);
//	ruleEnginePage.displayNRulesOnGrid(100);
//	result = ruleEnginePage.isRuleSetPresent(ruleName);
//	Assert.assertTrue(result);	

	
}

@Test(priority = 7, description = "Verify the results of Rule Execution of Sov Data on Rm Template")	
public void verifySovDataRuleExecution() throws InterruptedException {
		String userName = readConfig.getDataFromConfig("FirstName");
		String sheetName = "RuleEngine_SovData";
		String accountName = readConfig.getDataFromConfig("Scrubbing_AccountName");
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
		sovImportPage.verifySovImportPage();
		
		Thread.sleep(1000);
		

		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnNextButton();
		mergeSovPage = new MergeSOVPage();

		mergeSovPage.verifyMergeSOVPage();
//		mergeSovPage.deleteHiddenRowAndColumn();
		mappingSummaryPage = mergeSovPage.clickOnNextBtn();

		mappingSummaryPage.verifyMappingSummaryPage();
		Thread.sleep(6000);


		
		if (!mappingSummaryPage.isMLPredictingOedColumns()) {
			mappingSummaryPage.updateUnmappedColumns();
			result = utils.isSuccess();
			Assert.assertTrue(result);
		}
		else {		
			mappingSummaryPage.applySovColumnSearch("State Code");
			result = mappingSummaryPage.updateMapping("State Code", "AreaCode");
			Assert.assertTrue(result);
			mappingSummaryPage.removeSovColumnSearch();
			mappingSummaryPage.clickOnSaveBtn();
			result = utils.isSuccess();
			Assert.assertTrue(result);
			}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sovColumnName = "Opening Protection";
		String oedColumnName = "BuildingExteriorOpening";
		
		mappingSummaryPage.applySovColumnSearch(sovColumnName);
		result = mappingSummaryPage.updateMapping(sovColumnName, oedColumnName);
		Assert.assertTrue(result);
		mappingSummaryPage.clickOnSaveBtn();
		Assert.assertTrue(utils.isSuccess());
		mappingSummaryPage.removeSovColumnSearch();
		
//		sovColumnName = "State Code";
//		oedColumnName = "AreaCode";
//		
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		mappingSummaryPage.applySovColumnSearch(sovColumnName);
//		result = mappingSummaryPage.updateMapping(sovColumnName, oedColumnName);
//		Assert.assertTrue(result);
//		mappingSummaryPage.clickOnSaveBtn();
//		Assert.assertTrue(utils.isSuccess());
//		mappingSummaryPage.removeSovColumnSearch();
		
		
		rmTemplatePage = mappingSummaryPage.clickOnNextBtn();
		Assert.assertTrue(utils.isSuccess());

		result = rmTemplatePage.verifyRMTemplatePage();
		Assert.assertTrue(result);
		
		//Verifying the column data after executing SovData rules on RM Template
		
		String columnName1 = readExcel.getStringData("Rule Execution", 2, 0);
		String[] columnData1 = readExcel.getStringData("Rule Execution", 2, 1).split(",");
		
		String columnName2 = readExcel.getStringData("Rule Execution", 2, 3);
		String[] columnData2 = readExcel.getStringData("Rule Execution", 2, 4).split(",");
		
		String columnName3 = readExcel.getStringData("Rule Execution", 2, 5);
		String[] columnData3 = readExcel.getStringData("Rule Execution", 2, 6).split(",");
		
		String columnName4 = readExcel.getStringData("Rule Execution", 2, 7);
		String[] columnData4 = readExcel.getStringData("Rule Execution", 2, 8).split(",");
		
		result  = rmTemplatePage.verifyColumnData(columnName1, columnData1);
		Assert.assertTrue(result);
		result = rmTemplatePage.verifyColumnData(columnName2, columnData2);
		Assert.assertTrue(result);
		result = rmTemplatePage.verifyColumnData(columnName3, columnData3);
		Assert.assertTrue(result);
		result = rmTemplatePage.verifyColumnData(columnName4, columnData4);
		Assert.assertTrue(result);
}



@Test(priority = 9, description = "Perform execution of oed Sanity Rules and verify the results")
public void verifyOedSanityRulesExecution() {
	boolean result = rmTemplatePage.verifyRMTemplatePage();
	Assert.assertTrue(result);
	sanityPage = new SanityPage();
	sanityPage.goToSanityPage();
	for(int i = 0; i < oedSanityRulesList.size(); i++) {
		sanityPage.selectSanityRule(oedSanityRulesList.get(i));
	}
	
	sanityPage.executeOedSanityRules();
	result = utils.isSuccess();
	Assert.assertTrue(result);
	sanityPage.clickOnRmTemplate();
	
	result = rmTemplatePage.verifyRMTemplatePage();
	Assert.assertTrue(result);
	
	sanityPage.clickOnSanityErrors();
//	Thread.sleep(500);
	result = sanityPage.isSanityRulesErrorDisplayed();
	try {
		Thread.sleep(1500);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	Assert.assertTrue(result);
	sanityPage.closeSanityErrorsPopup();
	
	
	try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Test(priority = 10, description = "Verify user is able to delete all the rules created on Rule Engine")
public void deleteCreatedRulesOnRuleEngine() {
	homePage.goToHomePage();
	homePage.goToRuleEngine();
	for(int i = 0; i < sovDataRulesList.size(); i++)
	{
		try {

		ruleEnginePage.applyColumnSearch(sovDataRulesList.get(i));
		ruleEnginePage.clicOnDeleteBtn();
		ruleEnginePage.clickOnYesDeleteBtn();
		Assert.assertTrue(utils.isSuccess());
		ruleEnginePage.removeColumnSearch();
		
//		result = utils.isSuccess();
		
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	for(int i = 0; i < oedSanityRulesList.size(); i++)
	{
		try {
			System.out.println(oedSanityRulesList.get(i));
		ruleEnginePage.applyColumnSearch(oedSanityRulesList.get(i));
		ruleEnginePage.clicOnDeleteBtn();
		ruleEnginePage.clickOnYesDeleteBtn();
		Assert.assertTrue(utils.isSuccess());
		ruleEnginePage.removeColumnSearch();
		
//		result = utils.isSuccess();
		
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
}



