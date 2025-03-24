package com.cat.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class SanityPage extends BaseClass {
	public SanityPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//i[@class='la la-ge']")
	WebElement sanityPage;
	
	@FindBy(xpath = "//dx-button[@type='success']//div[@class='dx-button-content']")
	WebElement runBtn;
	
	@FindBy(xpath = "//ui-switch[@name='defPort']/span")
	List<WebElement> switchBtnList;
	
	@FindBy(xpath = "//div[@data-placement='top']/h6")
	List<WebElement> ruleNameList;
	
	@FindBy(xpath = "//a[@id='baseIcon-tab34']")
	WebElement rmTemplateIcon;	
	
	@FindBy(xpath = "//i[@class='la la-exclamation-triangle']")
	WebElement sanityErrorsBtn;

	@FindBy(xpath = "//div[@id='idsanity']//div[@class='modal-content']")
	WebElement sanityRuleErrorsPopup;
	
	@FindBy(xpath = "//div[@id='idsanity']//span[@class='la la-times']")
	WebElement closeSanityErrorsPopup;
	
	@FindBy(xpath = "//div[@id='idsanity'] //tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[1]")
	List<WebElement> sanityErrorsRuleNameList;
	
	@FindBy(xpath = "//div[@id='idsanity'] //tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]")
	List<WebElement> sanityErrorsLocIdsList;
	
	
	
	public void goToSanityPage() {
		elementAct.clickElementByJS(sanityPage, "Sanity Rules ");
	}
	

	public void clickOnRmTemplate() {
		elementAct.clickElementByJS(rmTemplateIcon, "RM Template ");
	}
	
	public void clickOnSanityErrors() {
		elementAct.clickElementByJS(sanityErrorsBtn, "Sanity Errors ");
	}
	
	public boolean isSanityRulesErrorDisplayed() {
		wait.waitForElement(sanityRuleErrorsPopup);
		return sanityRuleErrorsPopup.isDisplayed();
	}
	
	public void closeSanityErrorsPopup() {
		elementAct.clickElementByJS(closeSanityErrorsPopup, "Close Btn ");
	}
	
		
	
	public void selectSanityRule(String ruleName) {
		String text;
		for(int i = 0; i < ruleNameList.size(); i++ ) {
			elementAct.scrollIntoView(ruleNameList.get(i));
			text = ruleNameList.get(i).getText();
			if(text.equalsIgnoreCase(ruleName)) {
				elementAct.clickElementByJS(switchBtnList.get(i), "Selecting Sanity Rule" );
				break;
			}
				
		}
	}
	
	public void executeOedSanityRules() {
		elementAct.scrollIntoView(runBtn);
		elementAct.clickElementByJS(runBtn, "Run Button");
	}
	
	public boolean verifyLocationIds(ArrayList<String> oedSanityRules, String[] locIds) {
		String text;
		for(int i = 0; i < sanityErrorsRuleNameList.size(); i++)
		{
			text = sanityErrorsRuleNameList.get(i).getText();
			if(oedSanityRules.contains(text)) {
				text = sanityErrorsLocIdsList.get(i).getText();
				if(!locIds[i].equalsIgnoreCase(text))
					return false;
			}
		}
		
		return true;
	}
}
