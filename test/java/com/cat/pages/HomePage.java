package com.cat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class HomePage extends BaseClass {

	String pageTitle = "Xceedance | Dashboard";

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "company-name-1")
	WebElement compName;
		
	@FindBy(xpath = "//i[@class='la la-home']")
	WebElement homePage;	

	@FindBy(css = "[href*='#/work-tray']")
	WebElement WorkTray;

	@FindBy(css = "[href*='#/lms/search-account']")
	WebElement LMSLink;
	
	@FindBy(xpath = "//a[@fragment='user-list']//i[@class='la la-users']")
	WebElement manageUser;
		
	@FindBy(xpath = "//i[@class='la la-sliders']")
	WebElement ruleEngine;
	
	@FindBy(xpath = "//i[@class='la la-credit-card']")
	WebElement workTray;

	public void verifyHomePage() {
		wait.waitForElement(compName);
		elementAct.verifyWebPage(pageTitle, "Home Page");
	}
	
	public void goToHomePage() {
		elementAct.clickElement(homePage, "Home Page Button");
	}

	public LmsSearchPage goToLMS() {
		//elementAct.clickElement(LMSLink, "LMS Link");
		elementAct.clickElementByJS(LMSLink, pageTitle);
		return new LmsSearchPage();
	}
	
	public ManageUserPage goToManageUser() {
		elementAct.clickElementByJS(manageUser, "Manage User");
		return new ManageUserPage();
	}
	
	public WorkTrayPage goToWorkTray() {
		wait.waitForElementToBeClickable(workTray);
		elementAct.clickElementByJS( workTray, "Work Tray");
		return new WorkTrayPage();
	}
	public RuleEnginePage goToRuleEngine() {
		elementAct.clickElementByJS(ruleEngine, "Rule Engine");
		return new RuleEnginePage();
	}
}
