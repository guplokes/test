package com.cat.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class RuleEnginePage extends BaseClass {

	public RuleEnginePage() {
		PageFactory.initElements(driver, this);
	}

	String title = "Xceedance | Rule List";

	@FindBy(xpath = "//div[@title='Column Search']//i[@class='dx-icon dx-icon-search']")
	WebElement columnSearchBtn;

	@FindBy(xpath = "//tr[contains(@class,'dx-datagrid-filter-row')]/td[3] //input[@aria-label='Filter cell']")
	WebElement ruleSetSearchTextbox;

	@FindBy(xpath = "//dx-select-box[@placeholder='Source Context']//input[@role='combobox']")
	WebElement sourceContextDD;

	@FindBy(xpath = "//span[@class='dx-icon dx-icon-clear']")
	WebElement clearSourceContextBtn;

	@FindBy(xpath = "//dx-button[@title='Move Up']//div[@class='dx-button-content']")
	WebElement moveUpBtn;

	@FindBy(xpath = "//i[@class='la la-arrow-down']")
	WebElement moveDownBtn;

	@FindBy(xpath = "//input[@role='spinbutton']")
	WebElement swapRuleNoTextbox;

	@FindBy(xpath = "//i[@class='la la-exchange']")
	WebElement swapRulesBtn;

	@FindBy(xpath = "//div[@class='dx-button-content']//i[@class='la la-close']")
	WebElement clearSwapRuleNoBtn;

	@FindBy(xpath = "//i[@class='dx-icon dx-icon-add']")
	WebElement addRuleBtn;
	
	@FindBy(xpath = "//div[@class='dx-overlay-content dx-popup-normal dx-resizable']//div[@class='dx-popup-content']")
	WebElement createRuleDialog;
	
	@FindBy(xpath = "//div[@class='dx-overlay-content dx-popup-normal dx-resizable']//div[contains(@class,'dx-popup-title')]/div/div[1]")
	WebElement createRuleDialogTitle;
	
	@FindBy(xpath = "(//dx-switch[@role = 'button'])[1]")
	WebElement masterTemplateSwitch;
	
	@FindBy(xpath = "(//dx-switch[@role = 'button'])[2]")
	WebElement processDependentSwitch;
	
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-close']")
	WebElement closeRuleBtn;

	@FindBy(xpath = "//i[@class='la la-edit'] ")
	WebElement editRuleBtn;

	@FindBy(xpath = "//i[@class='la la-copy']")
	WebElement copyRuleBtn;

	@FindBy(xpath = "//i[@class='la la-trash-o']")
	WebElement deleteRuleBtn;

	@FindBy(css = "button[class='btn btn-info']")
	WebElement deleteYesBtn;

	@FindBy(css = "button[class='btn btn-primary']")
	WebElement deleteCancelBtn;

	@FindBy(css = "div[class='del-1-alert-data-3'] h3")
	WebElement deletePopupText;

	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[2]")
	List<WebElement> ruleSetNoList;

	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[3]")
	List<WebElement> ruleSetList;

	@FindBy(xpath = "//div[contains(@class,'dx-datagrid-rowsview dx-datagrid-nowrap')] //tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]")
	List<WebElement> ruleRowList; // xpath for whole row of List

	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[5]")
	List<WebElement> sourceContextList;

	// Xpaths for add rule
	@FindBy(xpath = "//div[@data-dx_placeholder='Rule Name']/preceding-sibling::input[@type='text']")
	WebElement ruleNameTextbox;

	@FindBy(xpath = "//div[@data-dx_placeholder='Rule Type']/preceding-sibling::input[@type='text']")
	WebElement ruleTypeDD;

//	@FindBy(xpath = "//div[@class='form-group']//dx-select-box[@placeholder='Source Context']//div[@class='dx-dropdowneditor-icon']")
	@FindBy(xpath = "//div[@class='form-group']//dx-select-box[@placeholder='Source Context']//input[@role='combobox']")
	WebElement selectContextDD;

	@FindBy(xpath = "//div[@data-dx_placeholder='Source Entity']/preceding-sibling::input[@type='text']")
	WebElement sourceDD;

	// review xpath
	@FindBy(xpath = "//div[contains(@class,'dx-texteditor-input-container dx-tag-container')]")
	WebElement sourceEntityDD;

	@FindBy(xpath = "//div[contains(@class,'dx-popup-cancel-visible dx-popup-done-visible')] //div[@aria-label='OK']/div/span")
	WebElement okButton;	
	
	//When source Context is model Transformation
	@FindBy(xpath = "//dx-select-box[@placeholder='Target Entity']//div[@class='dx-dropdowneditor-icon']")
	WebElement targetDD;
	
	@FindBy(xpath = "//dx-tag-box[@placeholder='Target Entity']//div[@class='dx-texteditor-input-container dx-tag-container dx-native-click']")
	WebElement targetEntityDD;
	
	@FindBy(xpath = "//dx-select-box[@placeholder='Select LOB']//div[@class='dx-dropdowneditor-icon']")
	WebElement selectLOBDD;
	
	@FindBy(xpath = "//dx-select-box[@placeholder='Execution Type']//div[@class='dx-dropdowneditor-icon']")
	WebElement ruleExecutionTypeDD;
	
	@FindBy(xpath = "//dx-select-box[@placeholder='Rule Configuration Type']//div[@class='dx-dropdowneditor-icon']")
	WebElement ruleConditionTypeDD;
	
	@FindBy(xpath = "//label[text()='Message']/following-sibling::dx-text-area[@placeholder='Value']/div")
	WebElement messageTextbox;

	@FindBy(css = "dx-button[class='submit btn btn-default dx-button dx-button-button dx-button-mode-contained dx-widget'] div[class='dx-button-content']")
	WebElement createRuleBtn; // same locator for copying rule


	public boolean verifyRuleEnginePage() {
		return elementAct.verifyWebPageTitle(title, "Rule Engine");
	}

	public int getNumberOfRulesInGrid() {
		int noOfRows = driver.findElements(By.xpath(
				"//table[@class='dx-datagrid-table dx-datagrid-table-fixed']//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]//td[2]"))
				.size();
		return noOfRows;
	}

	public void displayNRulesOnGrid(int N) {
		WebElement ele = driver.findElement(By.xpath("//div[@aria-label='Display " + N + " items on page']"));
		elementAct.clickElementByJS(ele, "Displaying " + N + " Rules on grid");
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isRuleSetDisplayed() {
		if (ruleSetList.size() > 0)
			return true;
		else
			return false;
	}

	public void selectSourceContext(String sourceContext) {
		elementAct.selectDropdown(sourceContextDD, "Source Context Dropdown", sourceContext);
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.waitForElementToBeClickable(deleteRuleBtn);
	}
	
	public void clearSwapRuleNoTextBox() {
		elementAct.clickElementByJS(clearSwapRuleNoBtn, "Clear Swap Rule No Button");
	}

	public void clickOnSwapRulesBtn() {
		elementAct.clickElement(swapRulesBtn, "Swap Rules Button");
	}

	public void clickOnClearSourceContextBtn() {

		if (clearSourceContextBtn.isDisplayed()) {
			elementAct.clickElement(clearSourceContextBtn, "Clear Source Context");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void clickOnMoveUpBtn() {
		elementAct.clickElement(moveUpBtn, "Move Up Btn");
	}

	public void clickOnMoveDownBtn() {
		elementAct.clickElement(moveDownBtn, "Move Down Btn");
	}
	
	public void clickOnAddRuleBtn() {
		elementAct.clickElementByJS(addRuleBtn, "Add Rule Button");
	}
	
	public void clickOnCloseRuleBtn() {
		if(closeRuleBtn.isDisplayed())
		elementAct.clickElementByJS(closeRuleBtn, "Close Create Rule Popup");
	}

	public void clicOnDeleteBtn() {
		elementAct.scrollIntoView(deleteRuleBtn);
		elementAct.clickElementByJS(deleteRuleBtn, "Delete Button");
	}

	public boolean deletePopupText() {
//		wait.waitForElement(deletePopupText, "Delete Popup");
		if (deletePopupText.isDisplayed())
			return elementAct.verifyMessage(deletePopupText, "Delete Rule", "Delete popup text");
		else
			return false;
	}

	public void clickOnCancelDeleteBtn() {
		elementAct.clickElementByJS(deleteCancelBtn, "Cancel Delete");
	}

	public void clickOnYesDeleteBtn() {
		elementAct.clickElementByJS(deleteYesBtn, "Cancel Delete");
	}

	public void applyColumnSearch(String ruleSet) {
		elementAct.clickElementByJS(columnSearchBtn, "Column Search Btn");
		elementAct.enterData(ruleSetSearchTextbox, "Rule Set Search Textbox", ruleSet);
		try {
			Thread.sleep((500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		elementAct.clickElementByJS(columnSearchBtn, "Column Search Btn");
	}

	public String getRuleSetText(String ruleSetNo) {
		String text;
		wait.waitForElementToBeClickable(ruleSetNoList.get(0));
		for (int i = 0; i < ruleSetNoList.size(); i++) {
			text = ruleSetNoList.get(i).getText();

			if (text.equals(ruleSetNo)) {
				return ruleSetList.get(i).getText();
			}
		}
		return null;
	}

	public boolean selectingFirstRule() {
		elementAct.clickElementByJS(ruleSetList.get(0), "Selecting Rule");
		try {
			wait.waitForElementsAttribute(ruleRowList.get(0), "aria-selected", "true");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info("First rule not get selected");
			return false;
		}
	}

	public boolean selectingLastRule() {
		displayNRulesOnGrid(100);
		try {
			Thread.sleep(800);
			int index = ruleSetList.size() - 1;
			elementAct.scrollIntoView(ruleSetList.get(index));
			elementAct.clickElementByJS(ruleSetList.get(index), "Selecting Rule");
			wait.waitForElementsAttribute(ruleRowList.get(index), "aria-selected", "true");
			elementAct.scrollIntoView(ruleSetList.get(index));
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info("Last rule not get selected");
			return false;
		}

	}

	public void removeColumnSearch() {
		elementAct.clickElementByJS(columnSearchBtn, "Column Search Btn");
		elementAct.clearElementText(ruleSetSearchTextbox);
		try {
			Thread.sleep((800));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		elementAct.clickElementByJS(columnSearchBtn, "Column Search Btn");
	}

	public boolean verifySourceContextColumn(String sourceContext) {
		String text;
		for (int i = 0; i < sourceContextList.size(); i++) {
			text = sourceContextList.get(i).getText();
			System.out.println(text);
			if (text.equalsIgnoreCase(sourceContext))
				;
			else
				return false;
		}
		return true;
	}
	
	public boolean verifyCreateRuleDialog() {
		String title = "Create Rule";
		String text;
		if (createRuleDialog.isDisplayed()) {
			text = createRuleDialogTitle.getText();
			return elementAct.verifyText(text, title);
		}

		return false;
	}
	
	public boolean setMasterTemplateSwitch() {
		elementAct.clickElementByJS(masterTemplateSwitch, "Switch");
		wait.waitForElementsAttribute(masterTemplateSwitch, "aria-label", "Yes");
		String text = masterTemplateSwitch.getAttribute("aria-label");
		return elementAct.verifyText(text, "Yes");
	}
	
	public boolean setProcessDependentSwitch() {
		elementAct.clickElementByJS(processDependentSwitch, "process Dependent Switch");
		wait.waitForElementsAttribute(processDependentSwitch, "aria-label", "Yes");
		String text = processDependentSwitch.getAttribute("aria-label");
		return elementAct.verifyText(text, "Yes");
	}

	//This method can be used for selecting source entity as well as Target Entity dropdown on create Rule
	public void selectSourceEntityOnCraeteRule(String sourceEntity) {
		elementAct.clickElement(sourceEntityDD, "Source Entity Dropdown");
		WebElement element = driver.findElement(By.xpath("//div[text()='" + sourceEntity + "']"));
		elementAct.clickElement(element, "Entity");
		elementAct.clickElement(okButton, "Ok Btn");
	}
	
	public void selectTargetEntityOnCraeteRule(String sourceEntity) {
		elementAct.clickElement(targetEntityDD, "Target Entity Dropdown");
		WebElement element = driver.findElement(By.xpath("(//div[text()='" + sourceEntity + "'])[2]"));
		elementAct.clickElement(element, "Entity");
		elementAct.clickElement(okButton, "Ok Btn");
	}

	public void selectTargetOnCreateRule(String target) {
		elementAct.clickElement(targetDD, "Source Dropdown");
		WebElement element = driver.findElement(By.xpath("(//div[text()='" + target + "'])[2]"));
		elementAct.clickElement(element, target);
	}
	
	public void selectSourceOnCreateRule(String source) {
		elementAct.clickElement(sourceDD, "Source Dropdown");
		WebElement element = driver.findElement(By.xpath("//div[text()='" + source + "']"));
		elementAct.clickElement(element, source);
	}
	
	public void selectSourceContextOnCreateRule(String sourceContext) {
		elementAct.enterData(selectContextDD, "Source Context", sourceContext);
		try {
			Thread.sleep(400);
			List<WebElement> sourceContextList = driver.findElements(By.xpath(
					"//div[@class='dx-overlay-content dx-popup-normal dx-popup-draggable dx-resizable']//div[@class='dx-item-content dx-list-item-content']"));
			for (int i = 0; i < sourceContextList.size(); i++) {
				elementAct.scrollIntoView(sourceContextList.get(i));
				if (sourceContextList.get(i).getText().equalsIgnoreCase(sourceContext)) {
					Thread.sleep(200);
					wait.waitForElementToBeClickable(sourceContextList.get(i));
					elementAct.clickElementByJS(sourceContextList.get(i), "Sorce Context");
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void createRule(String ruleName, String ruleType, String sourceContext, String source, String sourceEntity,
			String message) {
//		elementAct.clickElementByJS(addRuleBtn, "Add Rule Button");
		clickOnAddRuleBtn();
		elementAct.enterData(ruleNameTextbox, "Rule Name", ruleName);
		elementAct.selectDropdown(ruleTypeDD, "Rule Type Dropdown", ruleType);
//		elementAct.selectDropdown(selectContextDD, "Source Context Dropdown", sourceContext);
		elementAct.clickElementByJS(selectContextDD, "AA");
		WebElement el = driver.findElement(By.xpath("(//div[text()='" + sourceContext + "'])[2]"));
		el.click();
		// temp code
		/*
		 * // List<WebElement> sourceContextList =
		 * driver.findElements(By.xpath("//div[@class='dx-item dx-list-item']")); //
		 * for(int i=0;i<sourceContextList.size(); i++) { //
		 * elementAct.scrollIntoView(sourceContextList.get(i)); // String text =
		 * sourceContextList.get(i).getText(); // System.out.println(text); //
		 * if(text.equals(sourceContext)) //
		 * elementAct.clickElementByJS(sourceContextList.get(i), sourceContext); // }
		 */ try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		elementAct.selectDropdown(sourceDD, "Source Dropdown", source);
		elementAct.clickElement(sourceDD, "Source Dropdown");
		WebElement element = driver.findElement(By.xpath("//div[text()='" + source + "']"));
		elementAct.clickElement(element, source);
		elementAct.clickElement(sourceEntityDD, "Source Entity Dropdown");
		element = driver.findElement(By.xpath("//div[text()='" + sourceEntity + "']"));
		elementAct.clickElement(element, sourceEntity);
		elementAct.clickElement(okButton, "Ok Btn");
		elementAct.enterDataAfterScroll(messageTextbox, "Message", message);
		elementAct.clickElement(createRuleBtn, "Create Rule");

	}

	public boolean isRuleSetPresent(String ruleName) {
		for (int i = 0; i < ruleSetList.size(); i++) {
			elementAct.scrollIntoView(ruleSetList.get(i));
			String text = ruleSetList.get(i).getText();
			if (text.equalsIgnoreCase(ruleName))
				return true;
		}
		return false;
	}

	public boolean selectingARule(String ruleSetNo) {
		String text;
		wait.waitForElementToBeClickable(ruleSetNoList.get(0));
		for (int i = 0; i < ruleSetNoList.size(); i++) {
			text = ruleSetNoList.get(i).getText();

			if (text.equals(ruleSetNo)) {
				elementAct.clickElementByJS(ruleSetList.get(i), "Selecting Rule");
				try {
					wait.waitForElementsAttribute(ruleRowList.get(i), "aria-selected", "true");
					return true;
				} catch (Exception ex) {
					ex.printStackTrace();
					return false;
				}
			}
		}

		logger.info("rule set No not found");
		return false;
	}

	public void deSelectingARule() {
		elementAct.clickElement(clearSwapRuleNoBtn, "De-Selceting Rule");
	}

	public void copyRuleSet(String copiedRuleName) {
		elementAct.clickElement(copyRuleBtn, "Copy Rule");
		ruleNameTextbox.clear();
		logger.info("Rule Name Textbox is cleared");
		elementAct.actionSendKeys(ruleNameTextbox, copiedRuleName);
		elementAct.clickElement(createRuleBtn, "Copy Rule");
	}

	public void clickOnEditRuleBtn() {
		elementAct.clickElementByJS(editRuleBtn, "Edit Rule");
	}

	public void enterSwapRuleNo(String ruleSetNo) {
		elementAct.enterData(swapRuleNoTextbox, "Swap Rule Textbox", ruleSetNo);
	}
	
	//If the selected sourceContext is Model Transformation
	public void selectiDDForModelTransformation(String target, String targetEntity, String lob, String ruleExecutionType) {
		selectTargetOnCreateRule(target);
		selectTargetEntityOnCraeteRule(targetEntity);
		elementAct.selectDropdown(selectLOBDD, "LOB", lob);
		elementAct.scrollIntoView(ruleExecutionTypeDD);
		elementAct.selectDropdown(ruleExecutionTypeDD, ruleExecutionType, ruleExecutionType);
	}
	public void enteringNewRuleDetails(String ruleName, String ruleType, String sourceContext, String source, String sourceEntity,String target,
			String targetEntity, String lob, String ruleExecutionType, String ruleConditionType, String message) {
		clickOnAddRuleBtn();
		elementAct.enterData(ruleNameTextbox, "Rule Name", ruleName);
		elementAct.selectDropdown(ruleTypeDD, "Rule Type Dropdown", ruleType);
//		WebElement context = driver.findElement(By.xpath(
//				"//div[@class='form-group']//dx-select-box[@placeholder='Source Context']//input[@role='combobox']"));
//		elementAct.enterData(context, "Source Context", "SOV Data");   //SOV Data
		selectSourceContextOnCreateRule(sourceContext);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		selectSourceOnCreateRule(source) ;
		this.selectSourceEntityOnCraeteRule(sourceEntity);
		
		System.out.println(target.isEmpty());

		System.out.println(sourceContext.equals("Model Transformation"));
		
		if(sourceContext.equalsIgnoreCase("Model Transformation"))
			selectiDDForModelTransformation(target, targetEntity, lob, ruleExecutionType);
		
		
//		String ruleConditionType = "If-Then";
		elementAct.scrollIntoView(ruleConditionTypeDD);
		elementAct.selectDropdown(ruleConditionTypeDD, "Rule Condition Type DD", ruleConditionType);
//		elementAct.enterDataAfterScroll(messageTextbox, "Message", message);
		elementAct.scrollIntoView(messageTextbox);
		elementAct.actionSendKeys(messageTextbox, message);
		
		elementAct.clickElement(createRuleBtn, "Creating Rule");
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
}
