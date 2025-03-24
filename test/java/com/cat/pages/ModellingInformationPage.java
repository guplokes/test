package com.cat.pages;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cat.testCases.BaseClass;
import com.cat.utilities.handleKeyboardEvent;

public class ModellingInformationPage extends BaseClass {

	public ModellingInformationPage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(text(),'Success')]")
	WebElement successMsg;

	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select High Hazard EQ']//div[@class='dx-dropdowneditor-icon']")
	WebElement hazardEQDD;

	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select High Hazard WS']//div[@class='dx-dropdowneditor-icon']")
	WebElement hazardWSDD;

	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select a BIPOI']//div[@class='dx-dropdowneditor-icon']")
	WebElement BIPOIDD;

	@FindBy(xpath = "//input[@name='predominantOcc']")
	WebElement predominantOccupancy;

	@FindBy(xpath = "//input[@name='predominantConst']")
	WebElement predominantConstruction;

	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select SOV BI values based on']//div[@class='dx-dropdowneditor-icon']")
	WebElement sovBIValuesDD;

	// Policy Level Financials
	@FindBy(xpath = "//input[@name='txtRetentionExcessOf']")
	List<WebElement> excessOfList;

	@FindBy(xpath = "//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[7]")
	List<WebElement> limitList;

	@FindBy(xpath = "//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[11]")
	List<WebElement> blanketDeductibleList;

	// Location/Peril Level Financial Data
//	@FindBy(xpath = "(//input[@role='combobox'])[27]")
	@FindBy(xpath = "//dx-drop-down-box[@id='finperilId']//input[@role='combobox']")
	WebElement perilsDD;

	@FindBy(xpath = "//div[@class='dx-treeview-search dx-show-invalid-badge dx-textbox dx-texteditor dx-editor-outlined dx-searchbox dx-show-clear-button dx-widget dx-texteditor-empty']//input[@aria-label='Search']")
	WebElement perilSearchBox;

	@FindBy(xpath = "//button[@id='btnperillocId']")
	WebElement addPerilBtn;

	@FindBy(xpath = "//dx-select-box[@id='locGrpName']//input[@role='combobox']")
	WebElement locationGroupName;

	@FindBy(xpath = "//dx-select-box[@id='limitcodeId']//div[@class='dx-dropdowneditor-icon']")
	WebElement sublimitCodeDD;

	@FindBy(xpath = "//dx-text-box[@id='txtsublimitId']//input[@name='sublimit']")
	WebElement sublimitTextbox;

	@FindBy(xpath = "//dx-text-box[@id='txtduductibleId']//input[@name='sublimit']")
	WebElement deductibleTextbox;

	@FindBy(xpath = "//dx-select-box[@id='sbxdeductcodeId']//div[@class='dx-dropdowneditor-icon']")
	WebElement deductibleCodeDD;

	@FindBy(xpath = "//dx-button[@id='btnaddfinperilId']//div[@class='dx-button-content']")
	WebElement okBtn;

	@FindBy(xpath = "//button[normalize-space()='Save Draft']")
	WebElement saveDraftBtn;
	
	@FindBy(xpath = "//dx-button[@id='button']//div[@class='dx-button-content']")
	WebElement submitBtn;	

	@FindBy(xpath = "//img[@class='draft-icon']")
	WebElement pendingRequestBtn;

	@FindBy(xpath = "//i[@class='la la-pencil']")
	WebElement editPendingRequestBtn;

	@FindBy(xpath = "//div[@aria-label='Yes']//div[@class='dx-button-content']")
	WebElement deleteYesBtn;

	@FindBy(xpath = "//tr[@class='dx-row dx-data-row dx-column-lines']/td[1]")
	List<WebElement> pendingJobNames;

	public boolean isSuccess() {
		wait.waitForElement(successMsg);
		return successMsg.isDisplayed();
	}

	public void selectHazardWSDD(WebElement ddElement, String ddName, String ddOption) {
		ddElement.click();
		String xpath = "(//div[contains(text(),'" + ddOption + "')])[2]";
		WebElement ele = driver.findElement(By.xpath(xpath));
		elementAct.clickElementByJS(ele, "dd option");
		ddElement.click();
	}

	private void selectingPeril(String peril) {

		WebDriverWait wait = (new WebDriverWait(driver, 20));
		wait.until(ExpectedConditions.elementToBeClickable(perilsDD));
		 elementAct.scrollIntoView(perilsDD);
		elementAct.clickElementByJS(perilsDD, "Perils dropdown");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		WebElement perilCheckbox = driver.findElement(By.xpath("(//span[normalize-space()='" + peril + "'])[2]"));
		elementAct.clickElementByJS(perilCheckbox, "Peril: " + peril);
		elementAct.clickElementByJS(perilsDD, "Perils dropdown");
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * value of x: for limit-> x=12 for Blanket Deductible ->x=13
	 */
	public void enterPolicyValues(List<WebElement> textboxList, String values, String x) {
		String[] tempArr = values.split("[*]", 0);
		WebElement invalidTextbox = driver.findElement(By.xpath("(//input[@role='textbox'])[" + x + "]"));
		// elementAct.scrollIntoView(textboxList.get(0));
		for (int i = 0; i < textboxList.size(); i++) {
//			System.out.println(tempArr[i]);
			elementAct.scrollIntoView(textboxList.get(i));
			new Actions(driver).moveToElement(textboxList.get(i)).build().perform();
			elementAct.clickElement(textboxList.get(i), "textbox");		
			elementAct.clearElementText(textboxList.get(i));
			try {
				Thread.sleep(500);
				handleKeyboardEvent.removeText();
				logger.info( "Textbox's text is cleared");
				Thread.sleep(500);
			} catch (AWTException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			invalidTextbox.sendKeys(tempArr[i]);
			
		}
	}

	public void selectCodeDD(WebElement ddElement, String ddOption, String ddName) {
		elementAct.clickElement(ddElement, ddName + " dropdown");
		List<WebElement> ddList = driver.findElements(By.xpath(
				"//div[contains(@class,'dx-dropdownlist-popup-wrapper dx-selectbox-popup-wrapper')]//div[contains(@class,'dx-popup-draggable dx-resizable')] //div[@class='dx-item dx-list-item']"));
		boolean flag = false;
		for (int i = 0; i < ddList.size(); i++) {
			String text = ddList.get(i).getText();
			if (text.equalsIgnoreCase(ddOption))
				elementAct.clickElement(ddList.get(i), text);
			flag = true;
		}
		
		if(flag)
		logger.info(ddOption + " of " + ddName + "is selected succesfully");
		else
			logger.fail(ddOption + " of " + ddName + "is not selected");

	}

	private void deleteJob(String jobName, String index) {
		WebElement deleteJobBtn = driver.findElement(By.xpath("(//i[@class='la la-remove'])[" + index + "]"));
		elementAct.clickElement(deleteJobBtn, "delete job");
		elementAct.clickElementByJS(deleteYesBtn, "yes");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fillBasicModellingInfo(String hazardEQ, String hazardWS, String BIPOI, String predominantOcc,
			String predominantConst, String sovBIValues) {
		elementAct.selectDDOptions(hazardEQDD, "hazardEQ", hazardEQ);
		selectHazardWSDD(hazardWSDD, "hazardWS", hazardWS);
		elementAct.selectDDOptions(BIPOIDD, "BIPOI", BIPOI);
		elementAct.enterData(predominantOccupancy, "predominant Occupancy", predominantOcc);
		elementAct.enterData(predominantConstruction, "predominant Construction", predominantConst);

		elementAct.selectDDOptions(sovBIValuesDD, "SOV BI values based on", sovBIValues);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void filPolicyLevelFinancialData(String excessOf, String limit, String blanketDeductible) {
		elementAct.scrollIntoView(excessOfList.get(0));
		elementAct.actionSendKeys(excessOfList.get(0), excessOf);
		enterPolicyValues(limitList, limit, "12");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		enterPolicyValues(blanketDeductibleList, blanketDeductible, "13");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.clickElementByJS(perilsDD, "Perils dropdown");// this step tom make the next script working
	}

	public void fillPerilLevelFinancialData(String peril, String locationGroup, String sublimitCode, String sublimit,
			String deductible, String deductibleCode) {
		selectingPeril(peril);
		elementAct.clickElementByJS(addPerilBtn, "AddPeril Button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementAct.enterDataByJS(locationGroupName, locationGroup, locationGroup);
		elementAct.clickElement(locationGroupName, "locationGroupName");

		selectCodeDD(sublimitCodeDD, sublimitCode, "sublimitCode");
		elementAct.enterDataInTextbox(sublimitTextbox, "sublimit", sublimit);

		elementAct.enterDataInTextbox(deductibleTextbox, "deductible", deductible);
		selectCodeDD(deductibleCodeDD, deductibleCode, "deductibleCode");
		elementAct.clickElement(okBtn, "OK Button");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveAsDraft() {
		elementAct.clickElement(saveDraftBtn, "Save Draft");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickOnSubmitBtn() {
		elementAct.scrollIntoView(submitBtn);
		elementAct.clickElementByJS(submitBtn, "Submitting CMR Form");
	}

	// this method deleted the pending request previously saved by user i.e, admin
	public void deletePendingRequest() {
		elementAct.clickElement(pendingRequestBtn, "Pending Request Tab");
		for (int i = 0; i < pendingJobNames.size(); i++) {
			String jobName = pendingJobNames.get(i).getText();
			if (jobName.contains(readConfig.getUserName())) {
				deleteJob(jobName, String.valueOf(i + 1));
				break;
			} else
				logger.pass("no pending request from this account to delete");

		}
	}
}
