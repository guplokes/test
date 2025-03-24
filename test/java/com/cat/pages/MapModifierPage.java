package com.cat.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class MapModifierPage extends BaseClass {

	public MapModifierPage() {
		PageFactory.initElements(driver, this);
	}
	
	
//	@FindBy(xpath = "//ul[@class='nav nav-tabs nav-left nav-border-left']/li/a[@aria-controls='invalidLiTab1']")
	@FindBy(xpath = "//ul[@class='nav nav-tabs nav-left nav-border-left leftpanel-verticalscrollbar']/li/a[@aria-controls='invalidLiTab1']")
	List<WebElement> invalidModifiersList;

	
	@FindBy(xpath = "//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[4]")
	List<WebElement> repositoryList;
	
	@FindBy(xpath = "	//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]")
	List<WebElement> cellDescriptionList;
	
	@FindBy(css = "li[id='liAllSave'] button[type='button']")
	WebElement allSaveBtn;
	
	@FindBy(xpath = "//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[1]/div/div/span")
	WebElement selectingCheckbox;
	
	@FindBy(xpath = "//dx-data-grid[@id='grdInvalidData'] //td[@aria-label='Select all']/div")
	WebElement selectAllCheckbox;
	
	@FindBy(xpath = "//div[@id='invalidLiTab1']//div[@class='row natureofac']//input[@role='combobox']")
	WebElement natureOfAccountDD;
	
	@FindBy(xpath = "//button[@id='inValidNOA']")
	WebElement applyNOABtn;
	
	@FindBy(xpath = "//div[contains(@class,'dx-show-invalid-badge dx-selectbox')]//div[@class='dx-dropdowneditor-icon']")
	WebElement repositoryDD;
	
	@FindBy(xpath = "//div[@class='dx-item dx-list-item dx-list-item-selected']")
	WebElement selectedRepository;
	
	@FindBy(css = "li[id='liClose'] button[type='button']")
	WebElement closeBtn;
	
	
	public boolean verifyMapMpdifierPage() {
//		WebElement element = driver.findElement(By.xpath("//div[@id='invalidLiTab1']"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement element = driver.findElement(By.xpath("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]"));
//		wait.waitForElement(element);
		wait.waitForElementToBeClickable(element);
		return element.isDisplayed();
	}
	
	public boolean isInvalidModifierMapped() {
		String text;
		for(int i = 0; i < cellDescriptionList.size(); i++) {
			text = repositoryList.get(i).getText();
			if(text.trim().isEmpty()) 
				return false;
		}
		return true;
	}
	
	public void clickOnCloseButton() {
		elementAct.clickElementByJS(closeBtn, "Close Button");
	}
	
	public boolean updateModifierData() {
		String text;
		WebElement selectCategory = driver.findElement(By.xpath("//span[@id='selecteCategory']"));
		for(int i=0; i<invalidModifiersList.size(); i++) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			wait.waitForElementToBeClickable(invalidModifiersList.get(i));
			text = invalidModifiersList.get(i).getText();
			
			
			elementAct.clickElementByJS(invalidModifiersList.get(i), text);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			wait.waitForTextInElement(selectCategory, text);
			wait.waitForElementToBeClickable(repositoryList.get(0));;
			if(this.isInvalidModifierMapped() == false)
				this.applyNoaOnAll();
		}
		wait.waitForElementToBeClickable(allSaveBtn);
		elementAct.clickElement(allSaveBtn, "All Save");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(invalidModifiersList.size() == 0)
			return true;
		else
			return false;
	}
	
	public boolean applyNatureOfAccount(String oedCode) throws InterruptedException {
		wait.waitForElementToBeClickable(selectingCheckbox);
		elementAct.clickElementByJS(selectingCheckbox, "Selecting CellDescription");
		elementAct.selectDropdown(natureOfAccountDD, "NOA Dropdown", oedCode);
		elementAct.clickElementByJS(applyNOABtn, "Apply NOA");
		
		Thread.sleep(1500);
		
		String text = repositoryList.get(0).getText();
		if(text.equals(oedCode))
			return true;
		else
			return false;		
	}
	
	public void applyNoaOnAll() {
		elementAct.clickElementByJS(selectAllCheckbox, "Selecting All");
		elementAct.selectDropdown(natureOfAccountDD, "NOA Dropdown", "Unknown");
		elementAct.clickElementByJS(applyNOABtn, "Apply NOA");
//		wait.waitForElementToBeClickable(repositoryList.get(0));
	}
	
	public boolean isMLPredictingOedCodeReposatory() {
		wait.waitForElementToBeClickable(repositoryList.get(0));
		elementAct.clickElement(repositoryList.get(0), "Repstitory Dropdown");
		elementAct.clickElementByJS(repositoryDD, "Repository Dropdown");
		String repositoryCode = selectedRepository.getText();
		System.out.println(repositoryCode);
		elementAct.clickElementByJS(repositoryDD, "Repository Dropdown");

		String[] args = repositoryCode.split("-");
		if(args[1].contains("0.00%"))
			return false;
		else
			return true;
	}

}
