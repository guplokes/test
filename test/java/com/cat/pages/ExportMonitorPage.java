package com.cat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class ExportMonitorPage extends BaseClass {

	
	public ExportMonitorPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean verifyExportMonitorPage() {
		
		WebElement element = driver.findElement(By.xpath("//h3[normalize-space()='LMS Export Monitor']"));
		wait.waitForElement(element);
		String actualText = element.getText();

		String expectedText = "LMS Export Monitor";
		return elementAct.verifyText(actualText, expectedText);
	}
	
	@FindBy(xpath = "//div[@class='right_port']/ul[2]/li[3]")
	WebElement status;
	
	@FindBy(xpath = "//div[@class='dx-button-content']//i[@class='la la-refresh']")
	WebElement refreshBtn;
	
	public String getStatusOfAccount() {
		return status.getText();
	}
	
	public void clickOnRefreshButton() {
		elementAct.clickElementByJS(refreshBtn, "Refresh");
	}
}
