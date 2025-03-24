package com.cat.testCases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cat.pages.BasicAcDetailsPage;
import com.cat.pages.ExportConfigurationPage;
import com.cat.pages.ExportMonitorPage;
import com.cat.pages.HomePage;
import com.cat.pages.LmsSearchPage;
import com.cat.pages.LocationCoveragesPage;
import com.cat.pages.LocationDetailsPage;
import com.cat.pages.LocationFinancialPage;
import com.cat.pages.LocationMasterPage;
import com.cat.pages.LoginPage;
import com.cat.pages.PerilLevelFinancialDetailsPage;
import com.cat.pages.PolicyBlanketDetailsPage;
import com.cat.pages.PolicyLayerPage;
import com.cat.utilities.DataProviderClass;
import com.cat.utilities.ReadExcel;
import com.cat.utilities.Utils;

public class EndToEnd_LMS_Test extends BaseClass {
	LoginPage loginPage;
	HomePage homePage;
	PolicyLayerPage policyLayerPage;
	LmsSearchPage lmsSearchPage;
	BasicAcDetailsPage basicAcDetailsPage;
	PolicyBlanketDetailsPage policyBlanketDetailsPage;
	PerilLevelFinancialDetailsPage pLFinancialDetailsPage;
	LocationMasterPage locationMasterPage;
	LocationDetailsPage locationDetailsPage;
	LocationCoveragesPage locationCoveragesPage;
	LocationFinancialPage locationFinancialPage;

	ReadExcel readLmsExcel = new ReadExcel("TestData");
	Utils utils;
	boolean result;
	String accountName;

	
	public void lmsSetUp() {
		accountName = readConfig.getDataFromConfig("AccountName");
		loginPage = new LoginPage();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());
		lmsSearchPage = homePage.goToLMS();
		result = lmsSearchPage.verifyLMSSearchPage();
		Assert.assertTrue(result);
		result = lmsSearchPage.searchAccountByName(accountName);
		Assert.assertTrue(result);
	}
	
	@Test(priority = -1, description = "Verify user is able to navigate to Basic Acoount Details Tab")
	public void verifyBasicAccountDetailsTab() {
		driver.navigate().refresh();
		utils = new Utils();
		this.lmsSetUp();
		basicAcDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = basicAcDetailsPage.verifyBasicAccountDetailsTab();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 0, description = "Verify user is able to view LMS- Policy Layer Page")
	public void goToPolicyLayerTab() {
		driver.navigate().refresh();
		this.lmsSetUp();
		basicAcDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		policyLayerPage = basicAcDetailsPage.clickPolicyLayerTab();
		policyLayerPage = basicAcDetailsPage.clickModellingInformationTab();
		result = policyLayerPage.verifyPolicyLayerTab();
		Assert.assertTrue(result);
	}
	
	@DataProvider(name = "Add Policy Layer")
	public Object[][] readPolicyLayerData() throws Exception {
		return readLmsExcel.readExcelSheet("addPolicyLayer", 1, 1, 4);
	}
	
	@Test(priority = 1, dataProvider = "Add Policy Layer", 
			description = "Verify user is able to add a Policy layer Row on Policy Layer grid" )
	public void addPolicyLayer(String layerName, String participation, String layerLimit, String layerAttachment) throws InterruptedException {
		int numberOfLayerBefore = policyLayerPage.getNumberOfSavedPolicyLayers();
		policyLayerPage.addNewLayer(layerName, participation, layerLimit, layerAttachment);
		Thread.sleep(500);
		int numberOfLayerAfter = policyLayerPage.getNumberOfSavedPolicyLayers();
		Assert.assertEquals(numberOfLayerAfter, numberOfLayerBefore + 1);
	}
	
	@Test(priority = 2, description = "Verify user is able to Clone an existing Layer on Policy layer")
	public void clonePolicyLayer()  {
		String layerName = objExcl.getStringData("addPolicyLayer", 1, 0);
		int numberOfLayerBefore = policyLayerPage.getNumberOfSavedPolicyLayers();
		policyLayerPage.applyColumnSearch(layerName);
			
		policyLayerPage.cloneALayer();
		policyLayerPage.removeColumnSearch();
		
		int numberOfLayerAfter = policyLayerPage.getNumberOfSavedPolicyLayers();
		Assert.assertEquals(numberOfLayerAfter, numberOfLayerBefore + 1);
	}
	
	
	@Test(priority = 3, description = "Verify Yes delete button on Policy Layer")
	public void verifyDeleteYesBtnPolicyLayer()  {

		String layerName = objExcl.getStringData("addPolicyLayer", 1, 0);
		int numberOfLayerBefore = policyLayerPage.getNumberOfSavedPolicyLayers();
		policyLayerPage.applyColumnSearch(layerName);		
		policyLayerPage.clickOnDeleteYesBtn();
		policyLayerPage.removeColumnSearch();	
		int numberOfLayerAfter = policyLayerPage.getNumberOfSavedPolicyLayers();
		Assert.assertEquals(numberOfLayerAfter, numberOfLayerBefore - 1);
	}
//	@Test(priority = 4, description = "Verify functionality of Column Chooser on Policy Layer")
	public void verifyColumnChooser() {
		String[] columnNames = {"Participation", "LayerLimit"};
		boolean result  = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length);
	}
	
	@Test(priority = 5, description = "Verify the functionality of Previous icon on Policy Layer")
	public void verifyPreviousTabPolicyLayer() {
		policyLayerPage.clickPreviousTab();
		boolean flag = basicAcDetailsPage.verifyBasicAccountDetailsTab();
		policyLayerPage.clickNextTab();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 6, description = "Verify the functionality of Next icon on Policy Layer")
	public void verifyNextTabPolicyLayer() {
		policyLayerPage.clickNextTab();
		boolean flag = new PolicyBlanketDetailsPage().verifyPolicyBlanketDetailsTab();
		policyLayerPage.clickPreviousTab();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 7, description = "Verify user is able to view LMS- Policy Blanket Details Page")
	public void goToPolicyBlanketDetailsTab() {
		driver.navigate().refresh();
		this.lmsSetUp();
		basicAcDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		result = basicAcDetailsPage.verifyBasicAccountDetailsTab();
		Assert.assertTrue(result);
		policyBlanketDetailsPage = basicAcDetailsPage.clickPolicyBlanketDetailsTab();
		boolean flag = policyBlanketDetailsPage.verifyPolicyBlanketDetailsTab();
		Assert.assertTrue(flag);
	}
	
	@DataProvider(name = "Add Policy Blanket Details Layer")
	public Object[][] readPolicyBlanketDetailsData() throws Exception {
		return readLmsExcel.readExcelSheet("addPolicyBlanketDetails", 1, 1, 11);
	}
	
	@Test(priority = 8, dataProvider = "Add Policy Blanket Details Layer", 
			description = "Verify user is able to add policy Blanket data on Policy Blanket Details")
	public void addPolicyBlanketDetails(String Peril,String dedCode, String dedType, String dedBasis, String blanketDed, 
			String maxDed, String minDed, String limitCode, String limitType, String limitBasis, String limitAmt) {			

		int numberOfLayerBefore = policyBlanketDetailsPage.getNumberOfSavedPolicyBlanketLayers();
		policyBlanketDetailsPage.addPolicyBlanketData(Peril, dedCode, dedType, dedBasis, blanketDed, maxDed, minDed,
				limitCode, limitType, limitBasis, limitAmt);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numberOfLayerAfter = policyBlanketDetailsPage.getNumberOfSavedPolicyBlanketLayers();
		Assert.assertEquals(numberOfLayerAfter, numberOfLayerBefore + 1);		
	}
	
	
	@Test(priority = 9, description = "Verify Yes delete button on Policy Blanket")
	public void verifyYesDeleteBtnPolicyBlanket()  {

	    String perilName =	objExcl.getStringData("addPolicyBlanketDetails", 1, 0);
	    int numberofLayerBefore = policyBlanketDetailsPage.getNumberOfPolicyBlanketLayers();
	    policyBlanketDetailsPage.applyColumnSearch(perilName);
		policyBlanketDetailsPage.clickOnDeleteBtn();
		policyBlanketDetailsPage.clickOnDeleteYesBtn();
		policyBlanketDetailsPage.removeColumnSearch();
		int numberofLayerAfter = policyBlanketDetailsPage.getNumberOfPolicyBlanketLayers();
		Assert.assertEquals(numberofLayerAfter, numberofLayerBefore - 1);
	}
	
//	@Test(priority =10, description = "Verify functionality of Column Chooser on Policy Blanket")
	public void verifyColumnChooserOnPolicyBlanket() {
		String[] columnNames = {"Deductible Type", "Minimum Deductible", "Limit Code", "Limit Amount"};
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length);
	}
	
	@Test(priority = 10, description = "Verify the functionality of Next icon on Policy Blanket")
	public void verifyNextTabPolicyBlanket() {
		policyBlanketDetailsPage.clickNextTab();
		boolean flag = new PerilLevelFinancialDetailsPage().verifyPerilLevelFinancialDetailsTab();
		policyBlanketDetailsPage.clickPreviousTab();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 10, description = "Verify the functionality of Previous icon on Policy Blanket")
	public void verifyPreviousTabPolicyBlanket() {
		policyBlanketDetailsPage.clickPreviousTab();
		boolean flag = new PolicyLayerPage().verifyPolicyLayerTab();
		policyBlanketDetailsPage.clickNextTab();
		Assert.assertTrue(flag);
	}	
	// Go to Peril Level Financial Details Tab
	
	@Test(priority = 11, description = "Verify user is able to view LMS- Peril Level Financial Details Page")
	public void goToPerilLevelFinancialDetailsTab() {
		driver.navigate().refresh();
		this.lmsSetUp();
		basicAcDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		result = basicAcDetailsPage.verifyBasicAccountDetailsTab();
		Assert.assertTrue(result);
		pLFinancialDetailsPage = basicAcDetailsPage.clickPerilLevelFinancialDetailsTab();
		boolean flag = pLFinancialDetailsPage.verifyPerilLevelFinancialDetailsTab();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 12, dataProvider = "data-provider", dataProviderClass = DataProviderClass.class,
			description = "Verify user is able to add location group on peril level financial")
	public void addLocationGroup(String locGroupName, String fieldName, String operator, String value,
			String conditionOperator) throws InterruptedException, AWTException {

		pLFinancialDetailsPage.addLocationGroup(locGroupName, fieldName, operator, value, conditionOperator);
		boolean flag = utils.isSuccess();
		if (!flag) {
			pLFinancialDetailsPage.clickCloseBtn();
			Assert.assertFalse(flag);
		}
		else			
			Assert.assertTrue(flag);
	}
	
	@Test(priority = 13, description = "Verify Check Group Locations Button on Peril Level Financial")
	public void verifyCheckGroupLocationsBtn() {
		String locationGroup = objExcl.getStringData("addPerilLevelFinancialDetails", 1, 1);
		String expectedText = "Location Group Locations";
		pLFinancialDetailsPage.selectLocationGroup(locationGroup);
		pLFinancialDetailsPage.clickOnCheckGroupLocationsBtn();
		boolean result = pLFinancialDetailsPage.verifyPopupTitle(expectedText);
		Assert.assertTrue(result);
	}
	
	@Test(priority = 13, description = "Verify View Group Criteria Button on Peril Level Financial")
	public void verifyViewGroupCriteriaBtn() {
		String locationGroup = objExcl.getStringData("addPerilLevelFinancialDetails", 1, 1);
		String expectedText = "Location Group Criteria";
		pLFinancialDetailsPage.selectLocationGroup(locationGroup);
		pLFinancialDetailsPage.clickOnViewGroupCriteriaBtn();
		boolean result = pLFinancialDetailsPage.verifyPopupTitle(expectedText);
		Assert.assertTrue(result);
	}

	@DataProvider(name = "Add Peril Level Financial Details Data")
	public Object[][] readPerilLevelFinancialDetailsData() throws Exception {
		return readLmsExcel.readExcelSheet("addPerilLevelFinancialDetails", 2, 2, 15);
	}
	
	@Test(priority = 14, dataProvider = "Add Peril Level Financial Details Data",
			description = "Verify user is able to add Peril Level Financial Details")
	public void addPerilLevelFinancialDetails(String perilName, String locGrpName, String layerName, String limitCode,
			String limitBasis, String limitType, String sublimit, String sublimitPart, String sublimitAttachment,
			String deductible, String deductibleCode, String deductibleType, String deductibleBasis,
			String minDeductible, String maxDeductible) {

		int numberOfLayersBefore = pLFinancialDetailsPage.getNumberOfSavedPerilLevelFinancialLayers();
		pLFinancialDetailsPage.addPerilLevelFinancialData(perilName, locGrpName, layerName, limitCode, limitBasis,
				limitType, sublimit, sublimitPart, sublimitAttachment, deductible, deductibleCode, deductibleType,
				deductibleBasis, minDeductible, maxDeductible);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numberOfLayersAfter = pLFinancialDetailsPage.getNumberOfSavedPerilLevelFinancialLayers();
		Assert.assertEquals(numberOfLayersAfter, numberOfLayersBefore + 1);

	}
	
	@Test(priority = 15, description = "Verify Yes delete button on Peril Level Fianancial")
	public void deletePerilLevelFinancialDetails() throws InterruptedException {
		String perilName = objExcl.getStringData("addPerilLevelFinancialDetails", 2, 0);
		int numberOfLayersBefore = pLFinancialDetailsPage.getNumberOfSavedPerilLevelFinancialLayers();

		pLFinancialDetailsPage.applyColumnSearch(perilName);
		pLFinancialDetailsPage.clickOnDeleteBtn();
		boolean result = pLFinancialDetailsPage.clickOnDeleteYesBtn();
		pLFinancialDetailsPage.removeColumnSearch();
		Thread.sleep(1000);
		Assert.assertTrue(result);
		int numberOfLayersAfter = pLFinancialDetailsPage.getNumberOfSavedPerilLevelFinancialLayers();
		Assert.assertEquals(numberOfLayersAfter, numberOfLayersBefore - 1);

	}
	
	@Test(priority = 15, description = "Verify functionality of Column Chooser on Peril Level Financial")
	public void verifyColumnChooserOnPerilLevelFinancial() {
		String[] columnNames = { "Deductible Type", "Location Group", "Limit Code", "Sublimit Part", "Min Deductible" };
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		pLFinancialDetailsPage.clickUndoBtn();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length);

	}

	@Test(priority = 15, description = "Verify the functionality of Next icon on Peril Level Fianancial")
	public void verifyNextTabPerilLevel() {
		pLFinancialDetailsPage.clickNextTab();
		boolean flag = new LocationMasterPage().verifyLocationMasterTab();
		 pLFinancialDetailsPage.clickPreviousTab();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 15, description = "Verify the functionality of Previous icon on Peril Level Fianancial")
	public void verifyPreviousTabPerilLevel() {
		pLFinancialDetailsPage.clickPreviousTab();
		boolean flag = new PolicyBlanketDetailsPage().verifyPolicyBlanketDetailsTab();
		pLFinancialDetailsPage.clickNextTab();
		Assert.assertTrue(flag);
	}

	
//	//Go to Location Master Tab
	@Test(priority = 16, description = "Verify user is able to view LMS- Location Master Page")
	public void goToLocationMasterTab() {
		driver.navigate().refresh();
		this.lmsSetUp();
		basicAcDetailsPage = lmsSearchPage.clickUpdateAccountButton();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		result = basicAcDetailsPage.verifyBasicAccountDetailsTab();
		Assert.assertTrue(result);
		locationMasterPage = basicAcDetailsPage.clickLocationMasterTab();
		boolean flag = locationMasterPage.verifyLocationMasterTab();
		Assert.assertTrue(flag);
	}


	@DataProvider(name = "Add Location Master Data")
	public Object[][] readLocationMasterData() throws Exception {
		return readLmsExcel.readExcelSheet("addLocationMasterData", 1, 1, 35);
	}
	
	@Test(priority = 17, dataProvider = "Add Location Master Data",
			description = "Verify user is able to add location on Location Master")
	public void addLocationMasterData(String locName, String inceptionDate, String expiryDate, String streetAddress, String	city,
			String area, String areaCode, String	state,	String stateCode, String postalCode, String county, String countryCode,
			String 	currencyCode,  String	latitude,	String longitude, String countyCode, String cityCode,	String cresta,
			String	subCresta, String muncipality, String muncipalityCode, String	postalName, String district, 
			String districtCode, String geocodeLevel, String geocodeConfidence, String geocoder, String geogName1, String geogScheme1
			, String userDef1, String userDef2, String userDef3, String userDef4, String userDef5, String userDef6 )
					throws InterruptedException, AWTException {
		
		locationMasterPage.addLocationData(locName, inceptionDate, expiryDate, streetAddress, city,
				area, areaCode, state, stateCode, postalCode, county, countryCode, currencyCode, latitude, longitude,
				countyCode, cityCode, cresta, subCresta, muncipality, muncipalityCode, postalName, district, districtCode,
				geocodeLevel, geocodeConfidence, geocoder, geogName1, geogScheme1, userDef1, userDef2, userDef3, userDef4, 
				userDef5, userDef6);
	
		boolean flag = utils.isSuccess();
		Assert.assertTrue(flag);	

	}
	
	
	@Test(priority = 18, description = "Verify user is able to delete location on Location Master")
	public void deleteLocationMasterData() throws InterruptedException, AWTException
	{
		Thread.sleep(1000);
		locationMasterPage.clickDisplay20Items();	
		String locationName = readLmsExcel.getStringData("addLocationMasterData", 1, 0);
		locationMasterPage.deleteLocationData(locationName);		
		boolean flag = utils.isSuccess();
		Assert.assertTrue(flag);
		
}
	
	
	@Test(priority = 19, description = "Verify functionality of Column Chooser on Location Master")
	public void verifyColumnChooserOnLocationMaster() {
		String[] columnNames = {"InceptionDate", "LocationName", "City", "StreetAddress", "PostalName"};
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
//		pLFinancialDetailsPage.clickUndoBtn();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length);
		
	}
	
	@Test(priority = 20, description = "Verify the functionality of Previous icon on Location Master")
	public void verifyPreviousTabLocationMaster() {
		locationMasterPage.clickPreviousTab();
		boolean flag = new PerilLevelFinancialDetailsPage().verifyPerilLevelFinancialDetailsTab();
		locationMasterPage.clickNextTab();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 21, description = "Verify the functionality of Next icon on Location Master")
	public void verifyNextTabLocationMaster() {
		locationMasterPage.clickNextTab();
		boolean flag = new LocationDetailsPage().verifyLocationDetailsTab();
		locationMasterPage.clickPreviousTab();
		Assert.assertTrue(flag);
	}
	//Go to Location Details Tab
		@Test(priority = 22, description = "Verify user is able to view LMS- Location Details Page")
		public void goToLocationDetailsTab() {
			driver.navigate().refresh();
			this.lmsSetUp();
			basicAcDetailsPage = lmsSearchPage.clickUpdateAccountButton();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			result = basicAcDetailsPage.verifyBasicAccountDetailsTab();
			Assert.assertTrue(result);
			locationDetailsPage = basicAcDetailsPage.clickLocationDetailsTab();
			result = locationDetailsPage.verifyLocationDetailsTab();
			Assert.assertTrue(result);
		}
		
		@DataProvider(name = "Modify Location Details Data")
		public Object[][] readLocationDetailsData() throws Exception {
			return readLmsExcel.readExcelSheet("modifyLocationDetails", 1, 1, 14);
		}

//		@Test(priority = 23, dataProvider = "Modify Location Details Data",
//				description = "Verify user is able to edit location on Location Details")
		public void modifyLocationDetails(String locationID, String campusName,String buildingID, String buildingName,
				String buildingType,  String floorArea, String numberOfBuilding, String numberOfStories, String yearBuilt, String yearUpgraded,
				String occupancyCode, String constructionCode, String	roofCover, String sprinklerType
				) throws InterruptedException, AWTException {	
			
			
			locationDetailsPage.updateLocDetails( locationID,  campusName, buildingID,  buildingName,
					 buildingType,   floorArea,  numberOfBuilding,  numberOfStories,  yearBuilt,  yearUpgraded,
					 occupancyCode,  constructionCode,	
						roofCover,  sprinklerType);
			boolean flag = utils.isSuccess();
			Assert.assertTrue(flag);
		}
		
		@Test(priority = 24, description = "Verify the functionality of column chooser on Location Details")
		public void  verifyColumnChooserOnLocationDetails() {
			utils = new Utils();
			String[] columnNames = {"LocationNumber", "CampusName","YearBuilt", "SprinklerType"};
			boolean result = utils.columnChooser(columnNames);
			Assert.assertTrue(result);
			int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
			Assert.assertEquals(actualNumberOfColumns, columnNames.length);
			
		}
		
		@Test(priority = 24, description = "Verify the functionality of Next icon on Location Details")
		public void verifyNextTabLocationDetails() {
			locationDetailsPage.clickNextTab();
			boolean flag = new LocationCoveragesPage().verifyLocationCoveragesTab();
			locationDetailsPage.clickPreviousTab();
			Assert.assertTrue(flag);
		}
			
		
		@Test(priority = 24, description = "Verify the functionality of Previous icon on Location Details")
		public void verifyPreviousTabLocationDetails() {
			locationDetailsPage.clickPreviousTab();
			boolean flag = new LocationMasterPage().verifyLocationMasterTab();
			locationDetailsPage.clickNextTab();
			Assert.assertTrue(flag);
		}
		
		// Go to Location Coverages Tab
		
		@Test(priority = 25, description = "Verify user is able to view LMS- Location Coverages Page")
		public void goToLocationCoveragesTab() {
			driver.navigate().refresh();
			this.lmsSetUp();
			basicAcDetailsPage = lmsSearchPage.clickUpdateAccountButton();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			result = basicAcDetailsPage.verifyBasicAccountDetailsTab();
			Assert.assertTrue(result);
			locationCoveragesPage = basicAcDetailsPage.clickLocationCoveragesTab();
			boolean flag = locationCoveragesPage.verifyLocationCoveragesTab();
			Assert.assertTrue(flag);
		}
		
		@DataProvider(name = "Modify Location Coverages Data")
		public Object[][] readLocationCoveragesData() throws Exception {
			return readLmsExcel.readExcelSheet("modifyLocationCoverages", 2, 2, 20);
		}
	
//		@Test(priority = 26, dataProvider = "Modify Location Coverages Data",
//				description = "Verify user is able to modify on Location Coverages")
		public void modifyLocationCoverages(String locID, String buildTIV1, String buildTIV2, String buildTIV3,
				String contTIV1, String contTIV2, String contTIV3, String damagebility1, String damagebility2,
				String damagebility3, String otherTIV1, String otherTIV2, String otherTIV3, String BITIV1, String BITIV2,
				String BITIV3, String BIPOI1, String BIPOI2, String BIPOI3, String waitingPeriod)
				throws InterruptedException, AWTException {

			locationCoveragesPage.update(locID, buildTIV1, buildTIV2, buildTIV3, contTIV1, contTIV2, contTIV3,
					damagebility1, damagebility2, damagebility3, otherTIV1, otherTIV2, otherTIV3, BITIV1, BITIV2, BITIV3,
					BIPOI1, BIPOI2, BIPOI3, waitingPeriod);

//			objLC.updateLocCoverages( locID, buildTIV1, buildTIV2, buildTIV3, contTIV1, contTIV2, contTIV3, damagebility1, 
//					damagebility2, 	damagebility3, 	otherTIV1, otherTIV2, otherTIV3, BITIV1, BITIV2, BITIV3, BIPOI1, BIPOI2,
//					 BIPOI3,  waitingPeriod);
			// objLC.addCovDatailofPeril();
			// objLC.deleteCovDatailofPeril();
		}
		
		/*
		 * This method deletes location data for selected peril peril is defined in test
		 * method not taken from excel
		 */
		@Test(priority = 27, description = "verify user is able to delete location coverages")
		public void deleteLocationCoverages() throws InterruptedException {
			String perilName = "All Earthquake";
			locationCoveragesPage.deleteCoveragesData(perilName);
			Thread.sleep(500);
			
			int numberOfLocations = locationCoveragesPage.getNumberOfLocations();
			Assert.assertEquals(numberOfLocations, 0);
		}

		/*
		 * This method adds location data for selected peril peril is defined in test
		 * method not taken from excel
		 */
		
		@Test(priority = 28, description = "Verify user is able to add location coverages", dependsOnMethods = "deleteLocationCoverages")
		public void addLocationCoverages() throws InterruptedException {
			String perilName = "All Earthquake";
			locationCoveragesPage.addCoveragesData(perilName);
			int numberOfLocations = locationCoveragesPage.getNumberOfLocations();

			if (numberOfLocations > 0) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}

		}



		@Test(priority = 29, description = "Verify the functionality of column chooser on Location Coverages")
		public void  verifyColumnChooserOnLocationCoverages() {
		
			String[] columnNames = {"LocationNumber", "Address","Currency"};
			boolean result = utils.columnChooser(columnNames);
			Assert.assertTrue(result);
			int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
			Assert.assertEquals(actualNumberOfColumns, columnNames.length);
			
		}
		
		@Test(priority = 29, description = "Verify the functionality of Next icon on Location Coverages")
		public void verifyNextTabLocationCoverages() {
			locationCoveragesPage.clickNextTab();
			boolean flag = new LocationFinancialPage().verifyLocationFinancialTab();
			locationCoveragesPage.clickPreviousTab();
			Assert.assertTrue(flag);
		}

		@Test(priority = 29, description = "Verify the functionality of Previous icon on Location Coverages")
		public void verifyPreviousTabLocationCoverages() {
			locationCoveragesPage.clickPreviousTab();
			boolean flag = new LocationDetailsPage().verifyLocationDetailsTab();
			locationCoveragesPage.clickNextTab();
			Assert.assertTrue(flag);
		}
		
		//Go to Location Financial Tab
		@Test(priority = 30, description = "Verify user is able to view LMS- Location FinancialPage")
		public void goToLocationFinancialTab() { 
			driver.navigate().refresh();
			this.lmsSetUp();
			basicAcDetailsPage = lmsSearchPage.clickUpdateAccountButton();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			result = basicAcDetailsPage.verifyBasicAccountDetailsTab();
			Assert.assertTrue(result);
			locationFinancialPage = basicAcDetailsPage.clickLocationFinancialTab();
			boolean flag = locationFinancialPage.verifyLocationFinancialTab();
			Assert.assertTrue(flag);
		}
		
		@Test(priority = 31, description = "Verify the functionality of column chooser on Location Financial")
		public void  verifyColumnChooserOnLocationFinancial() throws InterruptedException {
			utils = new Utils();
			String[] columnNames = {"Limit", "Peril","Deductible"};
			Thread.sleep(1000);
			boolean result = utils.columnChooser(columnNames);
			Assert.assertTrue(result);
			int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
			Assert.assertEquals(actualNumberOfColumns, columnNames.length);
			
		}
		
		@Test(priority = 31, description = "Verify the functionality of Previous icon on Location Financial")
		public void verifyPreviousTabLocationFinancial() {
			locationFinancialPage.clickPreviousTab();
			boolean flag = new LocationCoveragesPage().verifyLocationCoveragesTab();
			locationFinancialPage.clickNextTab();
			Assert.assertTrue(flag);
		}
		
		@Test(priority = 32, description = "User is able to export account from LMS")
		public void verifyLmsExport() {
			driver.navigate().refresh();
			this.lmsSetUp();
			lmsSearchPage.clickLmsExportButton();
			ExportConfigurationPage exportPage = new ExportConfigurationPage();
			exportPage.verifyExportConfigurationPage();
			exportPage.selectModel("AIR");
			exportPage.clickOnSubmitBtn();
			result = lmsSearchPage.verifyLMSSearchPage();
			Assert.assertTrue(result);			
		}
		
		@Test(priority = 33,
				description = "Verify user is able to view exported account status on Export Monitor Page")
		public void verifyAccountOnExportMonitor() {
			lmsSearchPage.clickExportMonitorIcon();
			ExportMonitorPage exportMonitor = new ExportMonitorPage();
			result = exportMonitor.verifyExportMonitorPage();
			String actualStatus = exportMonitor.getStatusOfAccount();
			Assert.assertEquals(actualStatus, "IN PROGRESS");
		}
		@Test(priority = 34, description = "Verify exported account status changed to Complete after refresh")
		public void verifyExportStatusComplete() throws InterruptedException {
			int count = 0;
			String status = "";
			ExportMonitorPage exportMonitor = new ExportMonitorPage();
			exportMonitor.clickOnRefreshButton();
			Thread.sleep(4000);
			while(count < 10) {
				exportMonitor.clickOnRefreshButton();
				Thread.sleep(4000);
				status = exportMonitor.getStatusOfAccount();
				if(status.equals("COMPLETED"))
					break;
				Thread.sleep(10000);
				count++;
			}
			Assert.assertEquals(status, "COMPLETED");
			
		}
		

	
}
