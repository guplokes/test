package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.ManageTeamPage;
import com.cat.pages.ManageUserPage;
import com.cat.utilities.ReadExcel;

public class ManageTeamTest extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	ManageUserPage manageUserPage;
	ManageTeamPage manageTeamPage;
	ReadExcel readExcel = new ReadExcel("CMRTestData");

	
	@Test(priority = -1, description = "Verify user is able to view Manage Team Page")
	public void goToManageTeamPage() {
		loginPage = new LoginPage();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());		
		manageUserPage = homePage.goToManageUser();	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block(Remove sleep later, no need of it)
			e.printStackTrace();
		}
		manageTeamPage = manageUserPage.goToManageTeam();
		boolean flag = manageTeamPage.verifyManageTeamPageTitle();
		Assert.assertTrue(flag);
	}
	
	@DataProvider(name = "Create Team")
	public Object[][] readNewTeamlData() throws Exception {
		
		return readExcel.readExcelSheet("Create Team");
	}
	
	@Test(priority = 1, dataProvider = "Create Team", description = "Verify create team functionality on Manage Team")
	public void createTeam(String teamName, String members, String teamLead, String capacity, String additonalCapacity) {
		manageTeamPage.enterNewTeamData(teamName, members, teamLead, capacity, additonalCapacity);
		boolean flag = manageTeamPage.isSuccess();
		manageTeamPage.removeColumnSearch();
		Assert.assertTrue(flag);
		
	}
	
	@Test(priority = 2, description = "Verify search team functionality on Manage Team")
	public void searchTeam() throws InterruptedException {
		String teamName = readExcel.getStringData("Create Team", 1, 0);
		manageTeamPage.applyColumnSearch(teamName);
		Thread.sleep(500);
		int numberOfTeams = manageTeamPage.getNumberOfTeams();
		manageTeamPage.removeColumnSearch();
		Thread.sleep(500);
		Assert.assertEquals(numberOfTeams, 1);	
	}

	
	@Test(priority = 3, description = "Verify user is able to assign type on Manage Team")
	public void verifyAssignType() {
		String accountType = "Primary Modelling";
		String requestType = "Quote/Re-Run";
		String teamName = readExcel.getStringData("Create Team", 1, 0);
		manageTeamPage.applyColumnSearch(teamName);
		manageTeamPage.assignApplicationType(accountType, requestType);
		boolean flag = manageTeamPage.isSuccess();
		manageTeamPage.removeColumnSearch();
		Assert.assertTrue(flag);
	}
	@Test(priority = 4, description = "Verify user is able to assign team privileges on Manage Team")
	public void assignTeamPrivileges() {
		String teamName = readExcel.getStringData("Create Team", 1, 0);
		manageTeamPage.applyColumnSearch(teamName);
		manageTeamPage.selectAllPrivileges();
		boolean flag1 = manageTeamPage.isSuccess();
		Assert.assertTrue(flag1);
		boolean flag2 = manageTeamPage.verifyManageTeamPageTitle();
		Assert.assertTrue(flag2);		
	}
	
	@DataProvider(name = "Edit Team")
	public Object[][] readUpdateTeamData() throws Exception {
		
		return readExcel.readExcelSheet("Edit Team");
	}
	
	@Test(priority = 5, dataProvider = "Edit Team", description = "Verify update team functionality on Manage Team")
	public void updateTeam(String updatedTeamName, String members, String teamLead, String capacity, String additonalCapacity) {
		String teamName = readExcel.getStringData("Create Team", 1, 0);
		manageTeamPage.applyColumnSearch(teamName);
		manageTeamPage.updatingTeamData(updatedTeamName, members, teamLead, capacity, additonalCapacity);
		boolean flag = manageTeamPage.isSuccess();
		manageTeamPage.removeColumnSearch();
		Assert.assertTrue(flag);
		
	}
	@Test(priority = 6, description = "Verify delete team functionality on Manage Team")
	public void verifyDeleteTeam() {
		String teamName = readExcel.getStringData("Edit Team", 1, 0);
		manageTeamPage.applyColumnSearch(teamName);
		manageTeamPage.deleteTeam();
		//int numberOfTeams = manageTeamPage.getNumberOfTeams();
		boolean flag = manageTeamPage.isSuccess();
		manageTeamPage.removeColumnSearch();
		Assert.assertTrue(flag);
		//Assert.assertEquals(numberOfTeams, 0);
		manageTeamPage.removeColumnSearch();
		
		
	}
}
