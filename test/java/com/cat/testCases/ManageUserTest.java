package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.ManageUserPage;
import com.cat.utilities.DataProviderClass;
import com.cat.utilities.Utils;


public class ManageUserTest extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	ManageUserPage manageUserPage;

	
	@Test(priority = -1, description = "Verify User is able view Manage User Page")
	public void goToManageUserPage() {
		loginPage = new LoginPage();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		
		manageUserPage = homePage.goToManageUser();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block(Remove sleep later, no need of it)
			e.printStackTrace();
		}
		boolean flag = manageUserPage.verifyManageUserPageTitle();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 1, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class, description = "Verify functionality of add new user on Manage User" )
	public void addNewUser(String userName, String roles, String firstName, String lastName, String aliasName,
			String email, String timeZone) {
		manageUserPage.addUser(userName, roles, firstName, lastName, aliasName, email, timeZone);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block(Remove sleep later, no need of it)
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2, description = "Verify Search Button on Manage User")
	public void searchUser() {
		
		String userName = readConfig.getUserName();
		manageUserPage.applyColumnSearch(userName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int numberOfUsers = manageUserPage.getNumberOfUsers();
		manageUserPage.removeColumnSearch();
		
		Assert.assertEquals(1, numberOfUsers);			
	}
	
	@Test(priority = 3, description = "Veriffy modify user functionality on Manage User")
	public void modifyUser() {
			
		String roles =	objExcl.getStringData("addNewUser", 1, 1);
		String firstName = objExcl.getStringData("addNewUser", 1, 2);
		String lastName = objExcl.getStringData("addNewUser", 1, 3);
		String timeZone = objExcl.getStringData("addNewUser", 1, 6);
		
		//String userName = readConfig.getUserName();
		String userName =	objExcl.getStringData("addNewUser", 1, 0);
		manageUserPage.applyColumnSearch(userName);
		
		manageUserPage.modifyUser(roles, firstName, lastName, timeZone);
//		boolean flag = manageUserPage.isSuccess();
//		Assert.assertTrue(flag);
		
	}
	
	/*
	 * delete is Soft delete here where user doesn't get deleted, only active status
	 * of user get changed to de-activated
	 */
	@Test(priority = 4, description = "Verify delete User on Manage User")
	public void deleteUser() {
		
		String userName =	objExcl.getStringData("addNewUser", 1, 0);
		manageUserPage.applyColumnSearch(userName);
		manageUserPage.deleteUser(userName);
		boolean flag1 = manageUserPage.isSuccess();
		Assert.assertTrue(flag1);
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag2 = manageUserPage.getActiveStatus();
		Assert.assertFalse(flag2);
		manageUserPage.removeColumnSearch();		
		
	}
	
	@Test(priority = 5, description = "Verify user is able to change the status on Manage User")
	public void changeUserStatus() {
		
		String userName = objExcl.getStringData("addNewUser", 1, 0);
		manageUserPage.applyColumnSearch(userName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manageUserPage.switchUserStatus();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag;
		flag = manageUserPage.getActiveStatus();
		Assert.assertTrue(flag);
//		manageUserPage.switchUserStatus();
//		flag = manageUserPage.getActiveStatus();
//		Assert.assertFalse(flag);		
	}
	
	@Test(priority = 6, description = "Verify user is able to assign privileges on Manage User")
	public void assignUserPrivilege() {
		String userName = objExcl.getStringData("addNewUser", 1, 0);
		manageUserPage.applyColumnSearch(userName);
		manageUserPage.privilegeAssignment();
		boolean flag = new Utils().isSuccess();
		Assert.assertTrue(flag);
		driver.navigate().back();
		
		
	}

}
