package com.cat.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class AssignWorkPage extends BaseClass {

	public AssignWorkPage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	String pageTitle = "Xceedance | Assign work";
	
	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[2]")
	List<WebElement> accountNameList;
	
	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[7]")
	List<WebElement> cleansingAssociateList;
	
	@FindBy(xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[8]")
	List<WebElement> cleansingQAList;
	
	@FindBy(xpath = "//div[contains(@class,'dx-datagrid-headers dx-datagrid-nowrap')] //tr[contains(@class,'dx-header-row')]//td")
	List<WebElement> columnHeaderList;
	
	@FindBy(xpath = "//div[contains(@class,'dx-item dx-list-item')]//div[1]")
	List<WebElement> ddList;
	
	@FindBy(xpath = "//dx-select-box[contains(@class,'dx-show-invalid-badge dx-selectbox dx-textbox dx-texteditor dx-show-clear-button')]//input[@role='combobox']")
	WebElement invalidTextbox;	
	
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-save']")
	WebElement saveBtn;

	private List<WebElement> processModeList;
	
	public boolean verifyAssignWorkTitle()
	{
		wait.waitForTitle(pageTitle);
		return elementAct.verifyWebPageTitle(pageTitle, "WorkTray");
	}
	
	public void clickOnSaveButton() {
		elementAct.clickElementByJS(saveBtn, "Save ");
	}
	
	public String getColumnListXpath(String columnName) {
		String text;
		String xpath = "//tr[contains(@class,'dx-row dx-data-row dx-row-lines dx-column-lines')]/td[";
		System.out.println(columnHeaderList.size());
		for(int i = 0; i < columnHeaderList.size(); i++) {
			elementAct.scrollIntoView(columnHeaderList.get(i));
			text = columnHeaderList.get(i).getText();
			if(text.equalsIgnoreCase(columnName)) {
				int index = i+1;
				xpath = xpath + index + "]";
				System.out.println(xpath);
				break;
			}
		}
		return xpath;
	}
	
	public void selectUserDD(String userName) {
		String text;
		elementAct.clickElement(invalidTextbox, "invalid textbox");
		for(int j = 0; j < ddList.size(); j++) {
			elementAct.scrollIntoView(ddList.get(j));
			text = ddList.get(j).getText();
			if(text.contains(userName)) {
				elementAct.clickElementByJS(ddList.get(j), userName);
				break;
			}
		}
	}
	
	public void assignAccountToUser(String accountName, String userName) {
		String text;
		for(int i = 0; i < accountNameList.size(); i++ )
		{
			text = accountNameList.get(i).getText();
			if(text.equals(accountName)) {
				elementAct.clickElementByJS(accountNameList.get(i), "Selecting Account");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//for process mode
//				processModeList = driver.findElements(By.xpath(this.getColumnListXpath("Process Mode")));
//				elementAct.scrollIntoView(processModeList.get(i));
//				elementAct.clickElementByJS(processModeList.get(i), "Process Mode");
//				this.selectUserDD("Auto");
				

//				elementAct.clickElementByJS(driver.findElement(By.xpath("//div[contains(text(),'Medium')]")), "Medium Complexity");
				
				
				cleansingAssociateList =driver.findElements(By.xpath(this.getColumnListXpath("Cleansing Associate")));
				elementAct.scrollIntoView(cleansingAssociateList.get(i));
				elementAct.clickElementByJS(cleansingAssociateList.get(i), "Cleansing Associate");
				this.selectUserDD(userName);

				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				cleansingQAList = driver.findElements(By.xpath(this.getColumnListXpath("Cleansing QA")));
				
				elementAct.scrollIntoView(cleansingQAList.get(i));
				elementAct.clickElementByJS(cleansingQAList.get(i), "Cleansing QA");
				this.selectUserDD(userName);
				
				
				//For Complexity
				
				processModeList = driver.findElements(By.xpath(this.getColumnListXpath("Complexity")));
				elementAct.scrollIntoView(processModeList.get(i));
				elementAct.clickElementByJS(processModeList.get(i), "Complexity");
				this.selectUserDD("Medium");

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		}
	}
}

