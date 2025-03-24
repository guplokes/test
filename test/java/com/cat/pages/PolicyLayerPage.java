package com.cat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class PolicyLayerPage extends BaseClass {

	String pageTitle = "Xceedance | Add Account";

	public PolicyLayerPage() {
		PageFactory.initElements(driver, this);
	}
	
//	@FindBy(xpath = "//a[normalize-space()='Policy Layer']")
//	@FindBy(xpath = "//div[@class='navbuttons allowClick']")
	@FindBy(xpath = "//a[normalize-space()='Modelling Information']")
	WebElement policyLayerTab;
	
	@FindBy(css = "[title='Add New Layer']")
	WebElement addNewLayerButton;
	
	@FindBy(css = "[title='Column Search']")
	WebElement colSearchButton;
	
//	@FindBy(xpath = "//*[text()='Layer Name']//following::td[5]")
//	@FindBy(xpath = "//*[text()='Layer Name']//following::td[14]")
	@FindBy(xpath = "//tr[contains(@class,'dx-edit-row')]/td[2]")
	WebElement layerNameTextbox;

//	@FindBy(xpath = "//*[text()='Layer Name']//following::td[6]")
//	@FindBy(xpath = "//*[text()='Layer Name']//following::td[15]")
	@FindBy(xpath = "//tr[contains(@class,'dx-edit-row')]/td[3]")
	WebElement participationTextbox;
	
//	@FindBy(xpath = "//*[text()='Layer Limit']//following::td[5]")
//	@FindBy(xpath = "//*[text()='Layer Name']//following::td[16]")
	@FindBy(xpath = "//tr[contains(@class,'dx-edit-row')]/td[4]")
	WebElement layerLimitTextbox;
	
//	@FindBy(xpath = "//*[text()='Layer Attachment']//following::td[5]")
//	@FindBy(xpath = "//*[text()='Layer Name']//following::td[17]")
	@FindBy(xpath = "//tr[contains(@class,'dx-edit-row')]/td[5]")
	WebElement layerAttachmentTextbox;
	
	@FindBy(xpath="//a[@title='Save']")
	private WebElement saveButton;
	
	@FindBy(css = "[title='Clone']")
	WebElement cloneButton;
	
	@FindBy(css = "[title='Cancel']")
	WebElement cancelButton;
	
	@FindBy(css = "[title='Edit']")
	WebElement editButton;
	
	@FindBy(css = "[title='Delete']")
	WebElement deleteButton;
	
	//Delelte Popup Button
	@FindBy(xpath = "//*[text()='Yes']")
	WebElement deleteYesBtn;
	
	@FindBy(xpath = "//*[text()='No']")
	WebElement deleteNoBtn;
	
	@FindBy(xpath = "//dx-button[@title='Previous Tab']")
	WebElement previousTab;
	
	@FindBy(xpath = "//dx-button[@title='Next Tab']")
	WebElement nextTab;
	
	public void clickPolicyLayerTab() {
		elementAct.clickTab(policyLayerTab, "Policy Layer Tab");
	}
	
	public void clickPreviousTab() {
		
		elementAct.clickElementByJS(previousTab, "Previous Tab Buton of Policy Layer");
		
	}
	public void clickNextTab() {
		elementAct.clickElementByJS(nextTab, "Next Tab Buton of Policy Layer");
		
	}
	public boolean verifyPolicyLayerTab()  {
		wait.waitForElementsAttribute(policyLayerTab, "class", "nav-link p-0 active");
		String[] str = policyLayerTab.getAttribute("class").split(" ");
		String text = str[2];
		
//		String text = policyLayerTab.getText();
		System.out.println(text);
		boolean flag = elementAct.verifyText("active", text);
//		boolean flag = elementAct.verifyText("PolicyLayer", text);
		return flag;

	}
	public void clickOnAddNewLayerBtn() {
		elementAct.clickElement(addNewLayerButton, "Add New Layer Button");
	}
	
	public void clickOnSaveBtn() {
		wait.waitForElementToBeClickable(saveButton);
		elementAct.clickElement(saveButton, "Save Button");
	}
	public void clickOnCancelBtn() {
		elementAct.clickElementByJS(cancelButton, "Cancel Button");
	}
	//This method apply column search on Layer Name Column
	public void applyColumnSearch(String layerName) {
		elementAct.clickElement(colSearchButton, "Column Search Button");
		elementAct.enterDataByJS(layerNameTextbox, "Layer Name", layerName);
		elementAct.clickElement(colSearchButton, "Column Search Button");
	}
	
	public void removeColumnSearch() {
		elementAct.clickElement(colSearchButton, "Column Search Button");
		elementAct.clearElementText(layerNameTextbox);
		elementAct.clickElement(colSearchButton, "Column Search Button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			/*
			 * // TODO Auto-generated catch block e.printStackTrace();
			 */}
	}
	
	public void addNewLayer(String layerName, String Participation, String layerLimit, String layerAttachment) {
		clickOnAddNewLayerBtn();

//		layerNameTextbox.clear();

		elementAct.enterDataAfterScroll(layerNameTextbox, "Layer Name", layerName);
		elementAct.enterDataByJS(participationTextbox, "Participation", Participation);
		elementAct.enterDataByJS(layerLimitTextbox, "Layer Limit", layerLimit);
		elementAct.enterDataByJS(layerAttachmentTextbox, "Layer Attachment",layerAttachment);
		clickOnSaveBtn();
	}
	public void enterLayerLimit(String layerLimit) {
		elementAct.enterDataByJS(layerLimitTextbox, "Layer Limit", layerLimit);
	}
	public void enterLayerAttachment(String layerAttachment) {
		elementAct.enterDataByJS(layerAttachmentTextbox, "Layer Attachment",layerAttachment);
	}
	public void cloneALayer()  {
		elementAct.clickElementByJS(cloneButton, "Clone Layer button");
	}
	public void clickOnDeleteNoBtn()  {

		elementAct.clickElement(deleteButton, "Delete Layer Button");
		elementAct.clickElement(deleteNoBtn, "No Delete Button");
	}
	public void clickOnDeleteYesBtn()  {

		elementAct.clickElement(deleteButton, "Delete Layer Button");
		elementAct.clickElement(deleteYesBtn, "Yes Delete Confirmation");
	}
	
	public void modifyALayer( String LayerName, String Participation, String LayerLimit, String LayerAttachement)  {

		elementAct.clickElement(editButton, "Edit Layer Button");		
		elementAct.enterDataInTextbox(layerNameTextbox, "Layer Name", LayerName);
		elementAct.clearElementText(participationTextbox);
		elementAct.enterDataByJS(participationTextbox, "Participation", Participation);

		elementAct.enterDataInTextbox(layerLimitTextbox, "Layer Limit", LayerLimit);
		elementAct.enterDataInTextbox(layerAttachmentTextbox, "Layer Attachment",LayerAttachement);
		clickOnSaveBtn();

	}
	
	public int getNumberOfPolicyLayers() {
		int size = driver.findElements(By.xpath("//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[2]")).size();
		return size;
	}
	
	//returns number of only Saved layers on Policy layer
	public int getNumberOfSavedPolicyLayers() {
//		int size = driver.findElements(By.xpath("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]")).size();
		int size = driver.findElements(By.xpath("//dx-data-grid[@id='accountGrid']//div[@aria-label='Data grid']//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]")).size();
		
		return size;
	}
	
}
