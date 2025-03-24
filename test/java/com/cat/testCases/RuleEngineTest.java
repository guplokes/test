package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.RuleEditorPage;
import com.cat.pages.RuleEnginePage;
import com.cat.utilities.ReadExcel;
import com.cat.utilities.Utils;

public class RuleEngineTest extends BaseClass {
	LoginPage loginPage;
	Utils utils;
	HomePage homePage;
	RuleEnginePage ruleEnginePage;
	RuleEditorPage ruleEditor;
	ReadExcel readExcel = new ReadExcel("CMRTestData");
//	String sourceContext = "OED Sanity";
	String sourceContext = readExcel.getStringData("Rule Engine", 11, 2);

	@Test(priority = -1, description = "Verify user is able to view Rule Engine Page")
	public void goToRuleEnginePage() {
		loginPage = new LoginPage();
		utils = new Utils();
		ruleEditor = new RuleEditorPage();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());
		ruleEnginePage = homePage.goToRuleEngine();
		boolean result = ruleEnginePage.verifyRuleEnginePage();
		Assert.assertTrue(result);
	}

	@Test(priority = 0, description = "Verify user is able to see rule sets on Rule Engine")
	public void verifyRuleSet() {
		boolean result = ruleEnginePage.isRuleSetDisplayed();
		Assert.assertTrue(result);
	}

	@Test(priority = 1, description = "Verify source context dropdown on Rule Engine")
	public void verifySourceContext() throws InterruptedException {
		String sourceContext = "SOV Data";
		ruleEnginePage.selectSourceContext(sourceContext);
		Thread.sleep(1000);
		boolean result = ruleEnginePage.verifySourceContextColumn(sourceContext);
		Assert.assertTrue(result);
	}

	@Test(priority = 1, dependsOnMethods = "verifySourceContext", description = "Verify user is able to clear source context on Rule Engine")
	public void verifyCrossBtnSourceContext() throws InterruptedException {
		String sourceContext = "SOV Data";
		ruleEnginePage.clickOnClearSourceContextBtn();
		Thread.sleep(1000);
		boolean result = ruleEnginePage.verifySourceContextColumn(sourceContext);
		Assert.assertFalse(result);
	}

	@Test(priority = 2, description = "Verify user is able to view the Rule Editor Page")
	public void verifyRuleEditorPage() {
		ruleEnginePage.clickOnEditRuleBtn();
		ruleEditor = new RuleEditorPage();
		boolean result = ruleEditor.verifyRuleEditorPage();
		Assert.assertTrue(result);
		driver.navigate().back();
	}

	@Test(priority = 4, description = "Verify user is able to select a Rule on Rule Engine")
	public void verifySelectingRule() {
		String sourceContext = "OED Sanity";
		String ruleSetNo = "2";
		ruleEnginePage.selectSourceContext(sourceContext);
		boolean result = ruleEnginePage.selectingARule(ruleSetNo);
//	boolean result = ruleEnginePage.isRuleSelected(ruleSetNo);
		ruleEnginePage.clearSwapRuleNoTextBox();
		Assert.assertTrue(result);

	}

//@Test(priority = 5, description = "Verify Move Up Button without selecting a Rule on Rule Engine")
	public void verifyMoveUpBtnWithoutSelectingRule() {
		ruleEnginePage.clickOnMoveUpBtn();
		boolean result = utils.isWarning();
		Assert.assertTrue(result);
	}

//@Test(priority = 5, description = "Verify Move Down Button without selecting a Rule on Rule Engine")
	public void verifyMoveDownBtnWithoutSelectingRule() {
		ruleEnginePage.clickOnMoveDownBtn();
		boolean result = utils.isWarning();
		Assert.assertTrue(result);
	}

	@Test(priority = 5, description = "Verify Swap rules Button without selecting a Rule on Rule Engine")
	public void verifySwapRulesWithoutSelectingRule() {
		ruleEnginePage.clickOnSwapRulesBtn();
		boolean result = utils.isError();
		Assert.assertTrue(result);
	}

	@Test(priority = 6, description = "Verify functionality of Move Up Button on Rule Engine")
	public void verifyMoveUpBtn() {
		ruleEnginePage.selectSourceContext(sourceContext);
		String ruleSetNo = "3";
		String ruleSetExpectedText = ruleEnginePage.getRuleSetText(ruleSetNo);
		if (ruleSetExpectedText != null)
			ruleEnginePage.getRuleSetText(ruleSetNo);
		else {
			logger.info("Rule Set Text not able to capture");
			Assert.assertFalse(true);
		}
		boolean result = ruleEnginePage.selectingARule(ruleSetNo);
		Assert.assertTrue(result);
		ruleEnginePage.clickOnMoveUpBtn();
		result = utils.isSuccess();

		ruleSetNo = Integer.toString(Integer.parseInt(ruleSetNo) - 1);
		String ruleSetActualText = ruleEnginePage.getRuleSetText(ruleSetNo);
		Assert.assertEquals(ruleSetActualText, ruleSetExpectedText);
		ruleEnginePage.clickOnClearSourceContextBtn();

	}

	@Test(priority = 6, description = "Verify Move Up Button by selecting first Rule on Rule Engine")
	public void verifyMoveUpBtnWithFirstRule() {
		ruleEnginePage.selectSourceContext(sourceContext);
		boolean result = ruleEnginePage.selectingFirstRule();
		Assert.assertTrue(result);
		ruleEnginePage.clickOnMoveUpBtn();
		result = utils.isError();
		Assert.assertTrue(result);
		ruleEnginePage.clickOnClearSourceContextBtn();
	}

	@Test(priority = 7, description = "Verify Move Down Button by selecting last Rule on Rule Engine")
	public void verifyMoveDownBtnwithLastRule() {
		ruleEnginePage.selectSourceContext(sourceContext);
		boolean result = ruleEnginePage.selectingLastRule();
		Assert.assertTrue(result);
		ruleEnginePage.clickOnMoveDownBtn();
		result = utils.isError();
		Assert.assertTrue(result);
		ruleEnginePage.clickOnClearSourceContextBtn();
		ruleEnginePage.displayNRulesOnGrid(10);
	}

	@Test(priority = 7, description = "Verify functionality of Move Down Button on Rule Engine")
	public void verifyMoveDownBtn() {
		ruleEnginePage.selectSourceContext(sourceContext);
		String ruleSetNo = "2";
		String ruleSetExpectedText = ruleEnginePage.getRuleSetText(ruleSetNo);
		if (ruleSetExpectedText != null)
			ruleEnginePage.getRuleSetText(ruleSetNo);
		else {
			logger.info("Rule Set Text not able to capture");
			Assert.assertFalse(true);
		}
		boolean result = ruleEnginePage.selectingARule(ruleSetNo);
		Assert.assertTrue(result);
		ruleEnginePage.clickOnMoveDownBtn();
		;
		result = utils.isSuccess();

		ruleSetNo = Integer.toString(Integer.parseInt(ruleSetNo) + 1);
		String ruleSetActualText = ruleEnginePage.getRuleSetText(ruleSetNo);
		Assert.assertEquals(ruleSetActualText, ruleSetExpectedText);
		ruleEnginePage.clickOnClearSourceContextBtn();
	}
 
	@Test(priority = 8, description = "Verify functionality of swap rules Button on Rule Engine")
	public void verifySwapRules() throws InterruptedException {
		ruleEnginePage.selectSourceContext(sourceContext);
		String ruleSetNo = "2";
		String swapRuleSetNo = "4";
		String ruleSetText1 = ruleEnginePage.getRuleSetText(ruleSetNo);
		String ruleSetText2 = ruleEnginePage.getRuleSetText(swapRuleSetNo);

		boolean result = ruleEnginePage.selectingARule(ruleSetNo);
		Assert.assertTrue(result);
		ruleEnginePage.enterSwapRuleNo(swapRuleSetNo);
		ruleEnginePage.clickOnSwapRulesBtn();
		result = utils.isSuccess();
		Thread.sleep(500);
		Assert.assertTrue(result);
		String actualText1 = ruleEnginePage.getRuleSetText(ruleSetNo);
		String actualText2 = ruleEnginePage.getRuleSetText(swapRuleSetNo);

		Assert.assertEquals(actualText1, ruleSetText2);
		Assert.assertEquals(actualText2, ruleSetText1);
		ruleEnginePage.clickOnClearSourceContextBtn();
	}

	@Test(priority = 9, description = "")
	public void verifySwapRulesWithInvalidValues() {
		String ruleSetNo = "4";
		String swapRuleSetNo = "0";
		ruleEnginePage.selectSourceContext(sourceContext);
		boolean result = ruleEnginePage.selectingARule(ruleSetNo);
		Assert.assertTrue(result);
		ruleEnginePage.enterSwapRuleNo(swapRuleSetNo);
		ruleEnginePage.clickOnSwapRulesBtn();
		result = utils.isError();
		Assert.assertTrue(result);
		ruleEnginePage.clickOnClearSourceContextBtn();

	}
	
	@Test(priority = 10, description = "Verify Add Rule dialog appears on Rule Engine Page")
	public void verifyAddNewRuleBtn() {
		ruleEnginePage.clickOnClearSourceContextBtn();
		ruleEnginePage.clickOnAddRuleBtn();
		boolean result = ruleEnginePage.verifyCreateRuleDialog();
		ruleEnginePage.clickOnCloseRuleBtn();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 10, description = "Verify user is able to set (Yes/No) for master template")
	public void verifyMasterTemplateSwitch() {
		ruleEnginePage.clickOnAddRuleBtn();
		boolean result = ruleEnginePage.setMasterTemplateSwitch();
		ruleEnginePage.clickOnCloseRuleBtn();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 10, description = "Verify user is able to set (Yes/No) for Process Dependent")
	public void verifyProcessDependentSwitch() {
		ruleEnginePage.clickOnAddRuleBtn();
		boolean result = ruleEnginePage.setProcessDependentSwitch();
		ruleEnginePage.clickOnCloseRuleBtn();
		Assert.assertTrue(result);
	}
	

	@DataProvider(name = "Add new Rule Set")
	public Object[][] readCreateRuleData() throws Exception {
		return readExcel.readExcelSheet("Rule Engine", 28, 28, 19);
	}

	@Test(priority = 10, dataProvider = "Add new Rule Set", description = "Verify user is able to add new Rule on Rule Engine")
	public void verifyCreateNewRuleOnRuleEngine(String ruleName, String ruleType, String sourceContext, String source, String sourceEntity,String target, String targetEntity, String lob, String ruleExecutionType,
			String ruleConditionType, String message, String ifField1, String ifOperator1, String ifValueType1, String ifValue1, String ifOprator1, 
			 String thenField1, String thenValueType1, String thenValue1) {
		
		ruleEnginePage.clickOnClearSourceContextBtn();
		ruleEnginePage.enteringNewRuleDetails(ruleName, ruleType, sourceContext, source, sourceEntity, target, targetEntity,
				lob, ruleExecutionType, ruleConditionType,  message);
		
		boolean result = ruleEditor.verifyRuleEditorPage();
		Assert.assertTrue(result);
		
		ruleEditor.addConditionForRule(ifField1, ifOperator1, ifValueType1, ifValue1);
		if(!ifOprator1.isEmpty())
			ruleEditor.selectingOpratorForFirstRow(ifOprator1);
		
		ruleEditor.addThenConditionForRule2(thenField1, thenValueType1, thenValue1);
		ruleEditor.clickOnSaveButton();
		
		result = utils.isSuccess();
		Assert.assertTrue(result);
		ruleEnginePage.selectSourceContext(sourceContext);
		ruleEnginePage.displayNRulesOnGrid(100);
		result = ruleEnginePage.isRuleSetPresent(ruleName);
		Assert.assertTrue(result);
		ruleEnginePage.displayNRulesOnGrid(10);
		ruleEnginePage.clickOnClearSourceContextBtn();

	}

//@Test(priority = 10, dependsOnMethods = "verifyAddNewRuleOnRuleEngine")
	public void verifyEditRuleOnRuleEngine() {
		String ruleName = readExcel.getStringData("Rule Engine", 2, 0);
		System.out.println(ruleName);
		String message = "Edited " + readExcel.getStringData("Rule Engine", 2, 5);
		System.out.println(message);
		ruleEnginePage.applyColumnSearch(ruleName);
		ruleName = "Edited " + ruleName;
		System.out.println(ruleName);
		ruleEnginePage.clickOnEditRuleBtn();
		ruleEditor = new RuleEditorPage();
		ruleEditor.editRule(ruleName, message);
	}

	@Test(priority = 11, dependsOnMethods = "verifyCreateNewRuleOnRuleEngine", description = "Verify the functionality of column search on Rule Engine")
	public void verifyColumnSearch() {
		String ruleName = "Automation Rule";
		ruleEnginePage.applyColumnSearch(ruleName);
//	int numberOfRulesInGrid = ruleEnginePage.getNumberOfRulesInGrid();
		boolean result = ruleEnginePage.isRuleSetPresent(ruleName);
		ruleEnginePage.removeColumnSearch();
		Assert.assertTrue(result);
//	Assert.assertEquals(numberOfRulesInGrid, 1);
	}

	@Test(priority = 12, dependsOnMethods = "verifyCreateNewRuleOnRuleEngine", description = "Verify cancel Delete button on Rule Engine")
	public void verifyDeleteCancelButtonOnRuleEngine() {
		String ruleName = "Automation Rule";
		ruleEnginePage.applyColumnSearch(ruleName);
		ruleEnginePage.clicOnDeleteBtn();
		boolean result = ruleEnginePage.deletePopupText();
		Assert.assertTrue(result);
		ruleEnginePage.clickOnCancelDeleteBtn();
		int numberOfRulesInGrid = ruleEnginePage.getNumberOfRulesInGrid();
		ruleEnginePage.removeColumnSearch();
		Assert.assertEquals(numberOfRulesInGrid, 1);
	}

	@Test(priority = 13, dependsOnMethods = "verifyCreateNewRuleOnRuleEngine", description = "Verify Yes Delete button on Rule Engine")
	public void verifyDeleteYesButtonOnRuleEngine() {
		String ruleName = "Automation Rule";
		ruleEnginePage.applyColumnSearch(ruleName);
		ruleEnginePage.clicOnDeleteBtn();
		boolean result = ruleEnginePage.deletePopupText();
		Assert.assertTrue(result);
		ruleEnginePage.clickOnYesDeleteBtn();
		result = utils.isSuccess();
		Assert.assertTrue(result);
		int numberOfRulesInGrid = ruleEnginePage.getNumberOfRulesInGrid();
		ruleEnginePage.removeColumnSearch();
		Assert.assertEquals(numberOfRulesInGrid, 0);
	}

	@Test(priority = 14, description = "Verify functionality of copy rule on Rule Engine")
	public void verifyCopyRuleOnRuleEngine() {
		String copiedRuleName = "Copied Rule Automation Test";
		ruleEnginePage.copyRuleSet(copiedRuleName);
		boolean result = utils.isSuccess();
		Assert.assertTrue(result);

		ruleEnginePage.applyColumnSearch(copiedRuleName);
		result = ruleEnginePage.isRuleSetPresent(copiedRuleName);
		int numberOfRulesInGrid = ruleEnginePage.getNumberOfRulesInGrid();
		ruleEnginePage.clicOnDeleteBtn();
		ruleEnginePage.clickOnYesDeleteBtn();
		result = utils.isSuccess();
		ruleEnginePage.removeColumnSearch();
		Assert.assertTrue(result);
		Assert.assertEquals(numberOfRulesInGrid, 1);
	}

}
