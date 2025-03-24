package com.cat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class ExportConfigurationPage extends BaseClass {

	
	public ExportConfigurationPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//dx-select-box[@placeholder='Select Model']//div[@class='dx-dropdowneditor-icon']")
	WebElement selectModelDD;
	
	@FindBy(xpath = "//i[@class='la la-check']")
	WebElement submitBtn;
	
	public boolean verifyExportConfigurationPage() {
		
		WebElement element = driver.findElement(By.xpath("//h3[normalize-space()='Export Configuration']"));
		wait.waitForElement(element);
		String actualText = element.getText();

		String expectedText = "Export Configuration";
		return elementAct.verifyText(actualText, expectedText);
	}
	
	public void selectModel(String model) {
		elementAct.selectDropdown(selectModelDD, "Model Dropdown", model);
	}
	
	public void clickOnSubmitBtn() {
		elementAct.clickElementByJS(submitBtn, "Submit");
	}
}
