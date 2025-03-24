package com.cat.pages;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class LocationCoveragesPage extends BaseClass {

	public LocationCoveragesPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Location Coverages']")
	WebElement locationCoveragesTab;
		
	@FindBy(xpath="//div[@title='Select Peril']//input[@role='combobox']")
	WebElement ddPeril;
	
	@FindBy(xpath="//i[@class='dx-icon dx-icon-add']")
	WebElement addPerilBtn;
	
	@FindBy(xpath="//i[@class='dx-icon dx-icon-trash']")
	WebElement deletePerilBtn;
	
	@FindBy(xpath="//button[normalize-space()='Yes, Delete']")
	WebElement cnfDeletePerilBtn;
	
	//@FindBy(css = "[title='Edit Location Coverage']")
	@FindBy(css = "td[class='dx-command-edit dx-command-edit-with-icons'] a[title='Edit Location Coverage']")	
	WebElement editIcon;
	
	@FindBy(xpath="//td[@class='dx-command-edit dx-command-edit-with-icons dx-cell-focus-disabled']//a[@title='Clear Location Coverage']")
	WebElement icClearLocCov;
	
	@FindBy(xpath="//div[@title='Column Search']//i[@class='dx-icon dx-icon-search']")
	WebElement columnSearchBtn;
	
	@FindBy(xpath="//td[@class='dx-command-edit dx-command-edit-with-icons']//a[@title='Save']")
	WebElement saveIcon;
	
	@FindBy(xpath = "//*[@id=\"toast-container\"]")
	WebElement lblAcCreation;

	//@FindBy(xpath="//*[text()='LocationNumber']//following::td[29]")
	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[1]")
	WebElement locationNumberTextbox;
	
	@FindBy(xpath="//*[text()='Building TIV 1']//following::td[29]")
	WebElement buildingTIV1Textbox;
	
	@FindBy(xpath="//*[text()='Building TIV 2']//following::td[29]")
	WebElement buildingTIV2Textbox;
	
	@FindBy(xpath="//*[text()='Building TIV 3']//following::td[29]")
	WebElement buildingTIV3Textbox;
	
	@FindBy(xpath="//*[text()='Content TIV 1']//following::td[29]")
	WebElement contentTIV1Textbox;
	
	@FindBy(xpath="//*[text()='Content TIV 2']//following::td[29]")
	WebElement contentTIV2Textbox;
	
	@FindBy(xpath="//*[text()='Content TIV 3']//following::td[29]")
	WebElement contentTIV3Textbox;
	
	@FindBy(xpath="//*[text()='Damagebility 1']//following::td[29]")
	WebElement damagebility1Textbox;
	
	@FindBy(xpath="//*[text()='Damagebility 2']//following::td[29]")
	WebElement damagebility2Textbox;
	
	@FindBy(xpath="//*[text()='Damagebility 3']//following::td[29]")
	WebElement damagebility3Textbox;
	
	@FindBy(xpath="//*[text()='Other TIV 1']//following::td[29]")
	WebElement otherTIV1Textbox;
	
	@FindBy(xpath="//*[text()='Other TIV 2']//following::td[29]")
	WebElement otherTIV2Textbox;
	
	@FindBy(xpath="//*[text()='Other TIV 3']//following::td[29]")
	WebElement otherTIV3Textbox;
	
	@FindBy(xpath="//*[text()='BI TIV 1']//following::td[29]")
	WebElement textboxBITIV1;
	
	@FindBy(xpath="//*[text()='BI TIV 2']//following::td[29]")
	WebElement textboxBITIV2;
	
	@FindBy(xpath="//*[text()='BI TIV 3']//following::td[29]")
	WebElement textboxBITIV3;
	
	@FindBy(xpath="//*[text()='BI POI 1']//following::td[29]")
	WebElement textboxBIPOI1;
	
	@FindBy(xpath="//*[text()='BI POI 2']//following::td[29]")
	WebElement textboxBIPOI2;
	
	@FindBy(xpath="//*[text()='BI POI 3']//following::td[29]")
	WebElement textboxBIPOI3;
	
	@FindBy(xpath="//*[text()='Waiting Period']//following::td[29]")
	WebElement waitingPeriodTextbox;
	
	@FindBy(xpath="//div[@aria-label='Warning']")
	List<WebElement> msgWarnig;
	
	@FindBy(xpath = "//dx-button[@title='Previous Tab']")
	WebElement previousTab;

	@FindBy(xpath = "//dx-button[@title='Next Tab']")
	WebElement nextTab;
	
	//******elements for Bulk Update functionality
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-edit']")
	WebElement bulkUpdateIcon;
	
	public void goToBulkUpdatePage() {
		elementAct.clickElementByJS(bulkUpdateIcon, "Bulk Update page icon");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean verifyLocationCoveragesTab() {
		wait.waitForElementsAttribute(locationCoveragesTab, "class", "nav-link p-0 active");
		String[] str = locationCoveragesTab.getAttribute("class").split(" ");
		String text = str[2];
		System.out.println(text);
		boolean flag = elementAct.verifyText("active", text);
		return flag;
	}
	
	

	
	public void selectPeril(String perilName) {
		eleAct.clickElementByJS(ddPeril);
		driver.findElement(By.xpath("//div[contains(text(),'"+perilName+"')]")).click();
		logger.info("Peril is selected successfully");
	}
	
	private void clickAddPeril(String perilName) throws InterruptedException {
		//addPerilBtn.click();
		elementAct.clickElementByJS(addPerilBtn, "adding data");
		Thread.sleep(1000);
		if(msgWarnig.size()>0)
		{
			logger.info("Peril data is alreay exist it returning the message: " + msgWarnig.get(0).getText());
		}
		Thread.sleep(5000);
		logger.info("Coverage datails is added for peril: "+perilName);
	}
	
	public int getNumberOfLocations() {
		int size = driver.findElements(By.xpath("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]"))
				.size(); //td[2] not working
		return size;
	}
	private void clickdeletePeril(String perilName) throws InterruptedException {
		deletePerilBtn.click();
		Thread.sleep(5000);
		cnfDeletePerilBtn.click();
		Thread.sleep(5000);
		logger.info("Coverage datails for peril "+perilName+" is deleted successfully.");
	}
	
	

	
	public void clickColSearchIcon() throws InterruptedException {
		columnSearchBtn.click();;
		Thread.sleep(1000);
		logger.info("Column Search icon is clicked.");
	}
	
	public void searchLocationData(String locID)  {
		elementAct.clickElementByJS(columnSearchBtn, "column Search Btn");
			elementAct.scrollIntoView(locationNumberTextbox);
		elementAct.enterData(locationNumberTextbox, "Location Number", locID);
		logger.info("Location ID : "+locID+" is entered to search.");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			elementAct.clickElementByJS(columnSearchBtn, "column Search Btn");
	}
	
	public void removeAppliedSearch()  {
		// clickColSearchIcon();
		elementAct.clickElementByJS(columnSearchBtn, "Column Search btn");
		//Thread.sleep(1000);


		elementAct.scrollIntoView(locationNumberTextbox);
		elementAct.clearElementText(locationNumberTextbox);
		locationNumberTextbox.clear();
//		elementAct.clearElementText(locationNumberTextbox);
		elementAct.clickElementByJS(columnSearchBtn, "Column Search btn");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void addCoveragesData(String perilName) throws InterruptedException
	{
		selectPeril(perilName);
		//elementAct.selectDDOptions(ddPeril, "Selecting peril", perilName);
		elementAct.selectDropdown(ddPeril, "Selecting peril", perilName);
		
		Thread.sleep(2000);
		clickAddPeril(perilName);
		Thread.sleep(2000);     
	}
	
	public void deleteCoveragesData(String perilName) throws InterruptedException
	{
		//selectPeril(perilName);
		//elementAct.selectDDOptions(ddPeril, "Selecting peril", perilName);
		elementAct.selectDropdownManual(ddPeril, "Selecting peril", perilName);
		Thread.sleep(500);
		clickdeletePeril(perilName);
		Thread.sleep(1000);
		
	}
	
	public void update(String locID, String buildTIV1,String buildTIV2,String buildTIV3,String contTIV1,
			String contTIV2,String contTIV3,String damagebility1, String damagebility2, String	damagebility3, String	otherTIV1,
			String otherTIV2, String otherTIV3,	String BITIV1, String BITIV2, String BITIV3, String	BIPOI1, String	BIPOI2,
			String BIPOI3, String waitingPeriod) throws InterruptedException, AWTException {
		searchLocationData(locID);
		elementAct.clickElement(editIcon, "edit icon");
		elementAct.enterDataInTextbox(buildingTIV1Textbox, "Building TIV1", buildTIV1);
		elementAct.enterDataInTextbox(buildingTIV2Textbox, "Building TIV2", buildTIV2);
		elementAct.enterDataInTextbox(buildingTIV3Textbox, "Building TIV3", buildTIV3);
		
		elementAct.scrollIntoView(damagebility3Textbox);
		
		elementAct.enterDataInTextbox(contentTIV1Textbox, "Content TIV1", contTIV1);
//		elementAct.clearElementText(contentTIV1Textbox);
//		elementAct.enterDataByJS(contentTIV1Textbox, "Content TIV1", contTIV1);
		elementAct.enterDataInTextbox(contentTIV2Textbox, "Content TIV2", contTIV2);
		elementAct.enterDataInTextbox(contentTIV3Textbox, "Content TIV3", contTIV3);
		elementAct.enterDataInTextbox(damagebility1Textbox, "Damagebility1", damagebility1);
		elementAct.enterDataInTextbox(damagebility2Textbox, "Damagebility2", damagebility2);
		elementAct.enterDataInTextbox(damagebility3Textbox, "Damagebility3", damagebility3);
		
		elementAct.scrollIntoView(otherTIV3Textbox);
		
		elementAct.enterDataInTextbox(otherTIV1Textbox, "Other TIV1", otherTIV1);
		elementAct.enterDataInTextbox(otherTIV2Textbox, "Other TIV2", otherTIV2);
		elementAct.enterDataInTextbox(otherTIV3Textbox, "Other TIV3", otherTIV3);
		
		elementAct.scrollIntoView(textboxBITIV3);
		
		elementAct.enterDataInTextbox(textboxBITIV1,"BITIV1" , BITIV1);
		elementAct.enterDataInTextbox(textboxBITIV2, "BITIV2", BITIV2);
		elementAct.enterDataInTextbox(textboxBITIV3, "BITIV3", BITIV3);
		
		elementAct.scrollIntoView(textboxBIPOI3);
		
		elementAct.enterDataInTextbox(textboxBIPOI1, "BIPOI1", BIPOI1);
		elementAct.enterDataInTextbox(textboxBIPOI2, "BIPOI1", BIPOI1);
		elementAct.enterDataInTextbox(textboxBIPOI3, "BIPOI1", BIPOI1);
		
		elementAct.scrollIntoView(waitingPeriodTextbox);
		elementAct.enterDataInTextbox(waitingPeriodTextbox, "Waiting period", waitingPeriod);
		
				
		elementAct.clickElementByJS(saveIcon, "saving Data");
		removeAppliedSearch();
		
		
		
	}
	
	public void clickPreviousTab() {

		elementAct.clickElementByJS(previousTab, "Previous Tab Buton of Policy Layer");
	}

	public void clickNextTab() {
			elementAct.clickElementByJS(nextTab, "Next Tab Buton of Policy Layer");

	}
}
