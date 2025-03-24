package com.cat.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class RuleEditorPage extends BaseClass {

	public RuleEditorPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='breadcrumb-data-3']/h3")
	WebElement pageTitle;

	@FindBy(xpath = "//dx-select-box[contains(@class,\"dx-editor-underlined dx-texteditor-empty dx-widget customselectbox\")]//input[@role='combobox']")
	WebElement ifSelectField;

	@FindBy(xpath = "//span[@class='ng-star-inserted']//select[@class='form-control ng-untouched ng-pristine ng-valid']")
	WebElement ifSelectOperator;

	@FindBy(xpath = "//input[@placeholder='Enter value']")
	WebElement ifValueTextbox;
	
	@FindBy(xpath = "//input[@placeholder='Enter value']")
	List<WebElement> valueTextboxList;	
	
	//for selecting AND & OR oprator
	
	@FindBy(xpath = "//div[@class='editrow ng-star-inserted']/span[10]/select")
	WebElement opratorForFirstRow;	

	@FindBy(xpath = "//div[@class='editrow ng-star-inserted']/span[11]/select")
	WebElement opratorAfterFirstRow;	
	

	@FindBy(xpath = "//dx-select-box[contains(@class,\"dx-editor-outlined dx-texteditor-empty dx-widget customselectbox\")]//input[@role='combobox']")
	WebElement thenSelectField;

	@FindBy(xpath = "//input[@class='form-control ng-star-inserted']")
	WebElement thenValueTextbox;
	
	@FindBy(css = "dx-select-box[placeholder='Select Value'] input[role='combobox']")
	List<WebElement> thenValueDDList;
	
	@FindBy(xpath = "//button[@class='addaction']")
	List<WebElement> addActionBtnList;

	@FindBy(xpath = "//i[@class='la la-save']")
	WebElement saveBtn;

	@FindBy(xpath = "//i[@class='la la-edit']")
	WebElement editBtn;

	@FindBy(xpath = "//i[@class='la la-code']")
	WebElement compileRuleSetBtn;

	@FindBy(xpath = "//input[@role='textbox'] ")
	WebElement ruleNameTextbox;

	@FindBy(xpath = "//dx-select-box[@placeholder='Rule Type']//input[@role='combobox']")
	WebElement typeOfRuleDD;

	@FindBy(xpath = "(//textarea[@role='textbox'])[1]")
	WebElement messageTextbox;

	@FindBy(xpath = "//dx-button[@class='submit btn btn-default dx-button dx-button-button dx-button-mode-contained dx-widget']")
	WebElement updateButton;

	public boolean verifyRuleEditorPage() {
		String expectedTitle = "Rule Editor";
		wait.waitForTextInElement(this.pageTitle, expectedTitle);
//		wait.waitForElement(this.pageTitle, expectedTitle);	
//		wait.waitForElementToBeClickable(this.pageTitle);
		String pageTitle = this.pageTitle.getText();
		System.out.println(pageTitle);

		return elementAct.verifyMessage(this.pageTitle, expectedTitle, "Rule Editor Page verfication");
	}

	public void clickOnSaveButton() {
		elementAct.clickElement(saveBtn, "Save Rule");
	}
	
	//for selecting AND & OR oprator
	
	public void selectingOpratorForFirstRow(String oprator) {
		elementAct.selectOperatorValue(opratorForFirstRow,  oprator);
	}
	
	public void selectingOpratorAfterFirstRow(String oprator) {
		elementAct.selectOperatorValue(opratorAfterFirstRow,  oprator);
	}
	
	public void addActionForIfThen() {
//		System.out.println(addActionBtnList.size());
//		addActionBtnList.get(0).click();		
		elementAct.clickElement(addActionBtnList.get(0), "Add new action");
	}
	
	public void addActionForElseIfThen() {
//		System.out.println(addActionBtnList.size());
//		addActionBtnList.get(0).click();
		
		elementAct.clickElement(addActionBtnList.get(addActionBtnList.size() - 1), "Add new action");
	}
	
	public void selectFieldForCondition(WebElement dd, String value) {
		elementAct.enterData(dd, "Field", value);
		try {
			Thread.sleep(1000);
			List<WebElement> options = driver.findElements(By.xpath("//div[@class='dx-scrollview-content']//div[@class='dx-item-content dx-list-item-content']"));
			for(int i=0; i<options.size(); i++) {
				if(options.get(i).getText().equalsIgnoreCase(value))
				{
					elementAct.clickElementByJS(options.get(i), value);
					break;
				}
//				System.out.println(option.get(i).getText());
			}
//			System.out.println(option.get(0).getText());
//			elementAct.clickElement(option.get(0), "Option");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
	}

	//Entering value for if condition only
	public void addConditionForRule(String ifField, String operator, String valueType, String ifValue ) {
//		elementAct.selectDDOptions(ifSelectField, "Select field for if cond. ", ifField);
		wait.waitForElementToBeClickable(ifSelectField);
		elementAct.scrollIntoView(ifSelectField);
		selectFieldForCondition(ifSelectField, ifField);
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		elementAct.selectOperatorValue(ifSelectOperator, operator);
//		elementAct.selectOperatorValue1(ifSelectOperator, operator);
//		ifSelectOperator.click();
//		WebElement el = driver.findElement(By.xpath("//option[text()='" + operator + "']"));
//		el.click();
		elementAct.selectOperatorValue(ifSelectOperator, valueType); //selecting type of value: value, reference, query
//		System.out.println(ifSelectOperator.isDisplayed());
		ifSelectOperator.click();
		
		elementAct.enterData(valueTextboxList.get(valueTextboxList.size() - 1), "Value", ifValue );
		
//		System.out.println(ifSelectOperator.isDisplayed());
		
//		elementAct.selectEntityValue(thenSelectField, thenField); // (selectDDOptions) will also work
//		elementAct.enterData(thenValueTextbox, "Value", thenValue);
//		clickOnSaveButton();

//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	
	//When value has textbox not dropdown
	public void addThenConditionForRule2(String thenField, String valueType, String value){
//		elementAct.selectEntityValue(thenSelectField, thenField); // (selectDDOptions) will also work
		this.selectFieldForCondition(thenSelectField, thenField);
		elementAct.selectOperatorValue(ifSelectOperator, valueType);
		elementAct.enterData(thenValueTextbox, "Value", value);
	}
	
	public void addThenConditionForRule(String thenField, String valueType, String value) {
//		elementAct.selectDDOptions(thenSelectField, "Select field for then cond. ", thenField);
		elementAct.scrollIntoView(thenSelectField);
		this.selectFieldForCondition(thenSelectField, thenField);
		elementAct.selectOperatorValue(ifSelectOperator, valueType);
		ifSelectOperator.click();
//		System.out.println(valueTextboxList.size());
		if(thenValueDDList.size() > 0)
			this.selectFieldForCondition(thenValueDDList.get(thenValueDDList.size() - 1), value);

//		System.out.println(ifSelectOperator.isDisplayed());
//		System.out.println(thenValueTextbox.isDisplayed());
		
	}
	
	public void enterIfConditionForRule(String ifField, String operator, String valueType, String ifValue ) {
		elementAct.selectDDOptions(ifSelectField, "Select field for if cond. ", ifField);

//		elementAct.selectOperatorValue(ifSelectOperator, operator);
//		ifSelectOperator.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		elementAct.selectOperatorValue(ifSelectOperator, operator);
		elementAct.selectOperatorValue(ifSelectOperator, valueType); //selecting type of value: value, reference, query
		
		elementAct.enterData(valueTextboxList.get(valueTextboxList.size() - 1), "Value", ifValue );
		
	}
	

//	public void addConditionForRule(String ifField, String operator, String ifValue, String thenField,
//			String thenValue) {
//		elementAct.selectDDOptions(ifSelectField, "Select field for if cond. ", ifField);
//		elementAct.selectOperatorValue(ifSelectOperator, operator);
//		elementAct.enterData(ifValueTextbox, "Value", ifValue);
//		elementAct.selectEntityValue(thenSelectField, thenField); // (selectDDOptions) will also work
//		elementAct.enterData(thenValueTextbox, "Value", thenValue);
//		clickOnSaveButton();
//
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	public void editRule(String ruleName, String message) {
		elementAct.clickElementByJS(editBtn, "Edit BUtton");
		elementAct.enterDataInTextbox(ruleNameTextbox, "Rule Name Textbox", ruleName);
		elementAct.enterDataInTextbox(messageTextbox, "message textbox", message);

	}
}
