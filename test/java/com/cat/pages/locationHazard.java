package com.cat.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class locationHazard extends BaseClass {
	
	public locationHazard() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@id='tabs']")
	WebElement tabMore;
	
	@FindBy(xpath="//a[normalize-space()='Location Hazard']")
	WebElement tabLocHazard;
	
	@FindBy(xpath = "//h5[normalize-space()='Location']")
	WebElement tabLocation;
	
	@FindBy(xpath="//div[@title='Column Search']//i[@class='dx-icon dx-icon-search']")
	WebElement btnColSearch;
	
	@FindBy(css = "[title='Edit Location Hazard Row']")
	List<WebElement> btnEdit;
	
	@FindBy(css = "[title='Save']")
	List<WebElement> btnSave;
	
	@FindBy(xpath="//*[text()='LocationNumber']//following::td[57]")
	WebElement txtLocNum;
	
	@FindBy(xpath="//*[text()='Distance-TreeCover']//following::td[57]")
	WebElement txtDisTreeCover;
	
	@FindBy(xpath="//*[text()='LocalWildfireRisk']//following::td[57]")
	WebElement txtlocalWildFireRisk;
	
	@FindBy(xpath="//*[text()='Content-CumbustibilityRank']//following::td[57]")
	WebElement txtContCumRank;
	
	@FindBy(xpath="//*[text()='Content-SuspenctibilityRank']//following::td[57]")
	WebElement txtContSusRank;
	
	@FindBy(xpath="//*[text()='ProtectionCredits-Sprinkler']//following::td[57]")
	WebElement txtProCrSp;
	
	@FindBy(xpath="//*[text()='InlandFloodZone']//following::td[57]")
	WebElement txtInlandFZ;
	
	@FindBy(xpath="//*[text()='TropicalCyclone-StormSurgeZone']//following::td[57]")
	WebElement txtTroCySSZ;
	
	@FindBy(xpath="//*[text()='TropicalCyclone-WindZone']//following::td[57]")
	WebElement txtTroCyWZ;
	
	@FindBy(xpath="//*[text()='EQ-ShakeZone']//following::td[57]")
	WebElement txtEQSZ;
	
	@FindBy(xpath="//*[text()='WildfireHazardZone']//following::td[57]")
	WebElement txtWildFireHZ;
	
	public void clickTabLocHazard() throws InterruptedException {
		tabMore.click();
		Thread.sleep(1000);
		tabLocHazard.click();
		Thread.sleep(1000);
		logger.info("Location Hazard tab is clicked.");
	}
	
	public void clickTabLocMaster() throws InterruptedException {
		Thread.sleep(5000);
		tabLocation.click();
		Thread.sleep(5000);
	}
	
	public void updateLocHazardData() {
		
	}

}
