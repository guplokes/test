package com.cat.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class ManageTeamPage extends BaseClass {

	public ManageTeamPage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);

	}
	String title = "Xceedance | Team List";
	
	@FindBy(xpath = "//div[@aria-label='Success']")
	WebElement successMessage;
	
	@FindBy(xpath = "//div[@title='Add New Team']//div[@class='dx-button-content']")
	WebElement addNewTeam;
	
	@FindBy(xpath = "//input[@name='TeamName']")
	WebElement teamNameTextbox;	
	
	@FindBy(xpath = "(//div[@class='dx-dropdowneditor-icon'])[2]")
	WebElement addMemberDD;
	
	@FindBy(xpath = "//input[@aria-label='Search']")
	WebElement addMemberSearchbox;
	
	@FindBy(xpath = "//dx-select-box[@id='teamLead']//div[@class='dx-dropdowneditor-icon']")
	WebElement teamLeadDD;
	
	@FindBy(xpath = "//dx-number-box[@id='TeamCapacity']//input[@role='spinbutton']")
	WebElement teamCapacityTextbox;
	
	@FindBy(xpath = "//dx-number-box[@id='TeamAddCapacity']//input[@role='spinbutton']")
	WebElement additionalCapacityTextbox;
				
	@FindBy(xpath = "(//i[@class='la la-close'])[1]")
	WebElement cancelCreateTeam;
	
	@FindBy(xpath = "//i[@class='la la-life-saver']")
	WebElement assignType;
	
	@FindBy(xpath = "(//div[@class='dx-dropdowneditor-icon'])[2]")
	WebElement accountTypeDD;	
	
	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select request type']//div[@class='dx-dropdowneditor-icon']")
	WebElement requestTypeDD;
	
	@FindBy(xpath = "//div[@title='Column Search']//i[@class='dx-icon dx-icon-search']")
	WebElement columnSearchBtn;
	
	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[1]")
	WebElement teamNameColumnSearch;	
	
	@FindBy(xpath = "//i[@class='la la-edit']")
	WebElement updateTeamBtn;
	
	@FindBy(xpath = "//i[@class='la la-gear']")
	WebElement teamPriviligesBtn;
	
	@FindBy(xpath = "//ui-switch[@name='switchAllTask']//small")
	WebElement switchAllTask;
	
	@FindBy(xpath = "//div[text()[normalize-space()='Save']]")
	WebElement savePrivilegesBtn;
	
	@FindBy(xpath = "//dx-button[@id='button']//div[1]")
	WebElement cancelPrivilegesBtn;	
	
	@FindBy(xpath = "//dx-button[@id='button']//div[@class='dx-button-content']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//i[@class='la la-trash-o']")
	WebElement deleteTeam;
	
	@FindBy(xpath = "//button[normalize-space()='Ok']")
	WebElement deleteOkBtn;
	
	
	public boolean verifyManageTeamPageTitle() {	
		return elementAct.verifyWebPageTitle(title, "Manage Team");		
	}
	
	public int getNumberOfTeams() {
		return driver.findElements(By.xpath("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]")).size();
	}
	
	private void selectAddMemberDD(String members) {
		String[] membersArr = members.split("[,]", 0);
		elementAct.clickElementByJS(addMemberDD, "Perils dropdown");
		for (String myStr : membersArr) {
			 elementAct.enterDataInTextbox(addMemberSearchbox, myStr , myStr);
			List<WebElement> element = driver.findElements(By.xpath("//div[contains(text(),'" + myStr + "')]"));
			elementAct.clickElementByJS(element.get(0), myStr);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	public boolean isSuccess() {
		wait.waitForElement(successMessage);
		boolean flag = successMessage.isDisplayed();
		WebElement crossBtn = driver.findElement(By.xpath("//span[text()='×']"));
		elementAct.clickElementByJS(crossBtn, "closing success msg");
		return flag;
	}
	private void selectTeamLead(String teamLead) {
		elementAct.clickElement(teamLeadDD, "Team Lead dropdown");
		String xpath = "//div[@class='dx-item-content dx-list-item-content'][contains(normalize-space(),'" + teamLead + "')]";
		driver.findElement(By.xpath(xpath)).click();
			}

	/* this method to apply column search on Team name column */
	public void applyColumnSearch(String teamName) {
		elementAct.clickElementByJS(columnSearchBtn, "column search Btn");
		elementAct.enterData(teamNameColumnSearch, "Team Name Column Search", teamName);
		elementAct.clickElementByJS(columnSearchBtn, "column search Btn");
	}
	
	public void removeColumnSearch() {
		elementAct.clickElementByJS(columnSearchBtn, "column search Btn");
		elementAct.clearElementText(teamNameColumnSearch);
		elementAct.clickElementByJS(columnSearchBtn, "column search Btn");
	}
	
	public void enterNewTeamData(String teamName, String members, String teamLead, String capacity, String additonalCapacity) {
		elementAct.clickElementByJS(addNewTeam, "Add New Team Btn");
		elementAct.enterData(teamNameTextbox, "Team Name", teamName);
//		elementAct.multipleDropdownOptions(addMemberDD, "Add team memebers dropdown", members);
		this.selectAddMemberDD(members);
		//elementAct.selectDDOptions(teamLeadDD, "Team Lead dropdown", teamLead);
		//elementAct.selectDropdown(teamLeadDD, "Team Lead dropdown", teamLead);
//		elementAct.clickElement(teamLeadDD, "Team Lead dropdown");
		this.selectTeamLead(teamLead);
//		driver.findElement(By.xpath("//div[@class='dx-item-content dx-list-item-content'][contains(normalize-space(),'Ashish Niturkar')]")).click();
		elementAct.enterData(teamCapacityTextbox, "team capacity", capacity);
		elementAct.enterData(additionalCapacityTextbox, "additional capacity", additonalCapacity);
		//elementAct.clickElementByJS(cancelCreateTeam, "Cross Btn");
		elementAct.clickElement(saveBtn, "Save Btn");
			

	}
	
	public void updatingTeamData(String teamName, String members, String teamLead, String capacity, String additonalCapacity) {
		elementAct.clickElementByJS(updateTeamBtn, "Update Team Btn");
		elementAct.enterDataInTextbox(teamNameTextbox, "Team Name", teamName);
//		elementAct.multipleDropdownOptions(addMemberDD, "Add team memebers dropdown", members);
//		elementAct.multipleDropdownOptions(addMemberDD, "Add team memebers dropdown", members);
		this.selectTeamLead(teamLead);
		elementAct.enterDataInTextbox(teamCapacityTextbox, "team capacity", capacity);
		elementAct.enterDataInTextbox(additionalCapacityTextbox, "additional capacity", additonalCapacity);
		elementAct.clickElementByJS(saveBtn, "Save");
	}
	
	public void assignApplicationType(String accountType, String requestType) {
		elementAct.clickElementByJS(assignType, "Assign Type");
		elementAct.selectDDOptions(accountTypeDD, "Account Type", accountType);
		elementAct.selectDropdownManual(requestTypeDD, "Request Type", requestType);
		elementAct.clickElementByJS(saveBtn, "Save");
	}
	
	public void selectAllPrivileges() {
		elementAct.clickElement(teamPriviligesBtn, "Privileges");
		elementAct.clickElement(switchAllTask, "All Task switch");
		//elementAct.clickElement(switchAllTask, "All Task switch");
		elementAct.clickElementByJS(savePrivilegesBtn, "Save Privileges");
		elementAct.clickElementByJS(cancelPrivilegesBtn, "Save Privileges");
	}
	
	public void deleteTeam() {
		elementAct.clickElementByJS(deleteTeam, "Delete");
		elementAct.clickElement(deleteOkBtn, "delete Ok popup");
	}
}
