package com.cat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cat.testCases.BaseClass;

public class ManageUserPage extends BaseClass{

	public ManageUserPage() {
				
			PageFactory.initElements(driver, this);
		
	}
	
	String title = "Xceedance | Manage User";
	
	@FindBy(xpath = "//i[@class='la la-user-plus']")
	WebElement manageTeam;
	
	@FindBy(xpath = "//i[@class='dx-icon dx-icon-add']")
	WebElement addUserBtn;
	
	@FindBy(xpath = "//div[@title='Column Search']//i[@class='dx-icon dx-icon-search']")
	WebElement columnSearchBtn;
	
	@FindBy(xpath = "//div[@title='Select Bulk Action']//div[@class='dx-dropdowneditor-icon']")
	WebElement bulkActionDD;
	
	@FindBy(xpath = "(//i[@class='la la-pencil'])[1]")
	WebElement editUserBtn;
	
	@FindBy(xpath = "(//i[@class='la la-trash-o'])[1]")
	WebElement deleteUserBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Yes, Delete User']")
	WebElement deleteButtonCNF;
	
	@FindBy(xpath = "//i[@class='la la-gear']")
	WebElement userPrivilegeBtn;
	
	@FindBy(xpath = "//input[@name='loginName']")
	WebElement userNameTextbox;
	
	@FindBy(xpath = "(//div[@class='dx-dropdowneditor-icon'])[2]")
	WebElement roleDD;
	
	@FindBy(xpath = "//input[@name='FirstName']")
	WebElement firstNameTextbox;
	
	@FindBy(xpath = "//input[@name='LastName']")
	WebElement lastNameTextbox;
	
	@FindBy(xpath = "//input[@name='AliasName']")
	WebElement aliasNameTextbox;	
	
	@FindBy(xpath = "//input[@name='Email']")
	WebElement emailTextbox;
	
	@FindBy(xpath = "//dx-drop-down-box[@placeholder='Select TimeZone']//input[@role='combobox']")
	WebElement timeZoneDD;
	
	@FindBy(xpath = "//input[@aria-label='Search']")
	WebElement timeZoneSearch;
	
	@FindBy(xpath = "(//i[@class='la la-user'])[1]")
	WebElement saveUserBtn;
	
	@FindBy(xpath = "//i[@class='ft-chevron-left']")
	WebElement cancelUserBtn;
	
	@FindBy(xpath = "(//input[@aria-label='Filter cell'])[1]")
	WebElement loginNameColumnSeachTextbox;
	
	@FindBy(xpath = "//div[@aria-label='Success']")
	WebElement successMessage;
	
	@FindBy(xpath = "(//span[contains(@class,'switch switch-small')])[1]")
	WebElement activeStatusSwitch;
	
	@FindBy(xpath = "//i[@class='la la-check']")
	WebElement saveUserPrivilege;
	
	@FindBy(xpath = "//p[normalize-space()='Select All Task']//span[@class='switch switch-small']")
	WebElement selectAllTaskSwitch;
	

	public boolean verifyManageUserPageTitle() {
		//String actualTitle = driver.getTitle();
		return elementAct.verifyWebPageTitle(title, "Manage User");
	}
	
	public ManageTeamPage goToManageTeam() {
		elementAct.clickElementByJS(manageTeam, "Manage Team");
		return new ManageTeamPage();
	}
	
	public boolean isSuccess() {
		wait.waitForElement(successMessage);
		return successMessage.isDisplayed();
	}
	
	private void selectTimeone(String timeZone) {
		
		elementAct.clickElementByJS(timeZoneDD, "Time Zone Dropdown");
		elementAct.enterData(timeZoneSearch, "Search timezone", timeZone);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = "//div[contains(text(),'" + timeZone + "')]";
		driver.findElement(By.xpath(path)).click();
		elementAct.clickElementByJS(timeZoneDD, "Time Zone Dropdown");
		
	}
	
	public void addUser(String userName, String roles, String firstName, String lastName, String aliasName,
			String email, String timeZone) {
		
		elementAct.clickElementByJS(addUserBtn, "Add User Button ");
		elementAct.enterData(userNameTextbox,"User Name", userName);
		elementAct.scrollIntoView(roleDD);
		elementAct.multipleDropdownOptions(roleDD, "Roles", roles);		
		elementAct.enterData(firstNameTextbox, "First Name", firstName);
		elementAct.enterData(lastNameTextbox, "Last Name", lastName);
		elementAct.enterData(aliasNameTextbox, "Alias Name", aliasName);
		elementAct.enterData(emailTextbox, "Email", email);
		
		selectTimeone(timeZone);
		
		elementAct.clickElementByJS(cancelUserBtn, "Cancel btn");			
	}
	
	public int getNumberOfUsers() {
		return driver.findElements(By.xpath("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines']/td[3]")).size();
	}
	
	public boolean getActiveStatus() {
		String status = activeStatusSwitch.getAttribute("class");
		if(status.contains("checked"))
			return true;
		else
			return false;
	}
	
	public void switchUserStatus() {
		elementAct.clickElementByJS(activeStatusSwitch, "Switch active status");
	}
	
	public void applyColumnSearch(String loginName) {
		elementAct.clickElementByJS(columnSearchBtn, "Column Search Btn");
		elementAct.enterData(loginNameColumnSeachTextbox, "Login Search", loginName);
		elementAct.clickElementByJS(columnSearchBtn, "Column Search Btn");
	}
	
	public void removeColumnSearch() {
		elementAct.clickElementByJS(columnSearchBtn, "Column Search Btn");
		loginNameColumnSeachTextbox.clear();
		elementAct.clickElementByJS(columnSearchBtn, "Column Search Btn");
	}
	
	public void modifyUser(String roles, String firstName, String lastName, String timeZone) {
		
		elementAct.clickElementByJS(editUserBtn, "Edit User");
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.scrollIntoView(roleDD);
		
		elementAct.multipleDropdownOptions(roleDD, "Roles", roles); //de-selecting previous roles
		elementAct.multipleDropdownOptions(roleDD, "Roles", roles);
		elementAct.enterDataInTextbox(firstNameTextbox,  "First Name", firstName);
		elementAct.enterDataInTextbox(lastNameTextbox,  "Last Name", lastName);
		selectTimeone(timeZone);
		
		elementAct.clickElementByJS(cancelUserBtn, "Cancel btn");
		//elementAct.clickElementByJS(saveUserBtn, "save Btn");
		
	}
	
	public void deleteUser(String userName) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementAct.clickElementByJS(deleteUserBtn, "Delete User");
		elementAct.clickElementByJS(deleteButtonCNF, "Delete Popup Yes");				 
	}
	
	public void privilegeAssignment() {
		elementAct.clickElementByJS(userPrivilegeBtn, "User privilege");
		elementAct.clickElementByJS(selectAllTaskSwitch, "Select All Task");
		elementAct.clickElementByJS(saveUserPrivilege, "Save settings");		
	}
	


}
