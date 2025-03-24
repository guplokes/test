package com.cat.testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.MappingSummaryPage;
import com.cat.pages.MergeSOVPage;
import com.cat.pages.ProrationPage;
import com.cat.pages.RMTemplatePage;
import com.cat.pages.SOVImportPage;
import com.cat.pages.WorkTrayPage;
import com.cat.utilities.ReadExcel;
import com.cat.utilities.Utils;

public class ProrationTest extends BaseClass {
	LoginPage loginPage;
	HomePage homePage;
	WorkTrayPage workTrayPage;
	SOVImportPage sovImportPage;
	MergeSOVPage mergeSovPage;
	MappingSummaryPage mappingSummaryPage;
	RMTemplatePage rmTemplatePage;
	ReadExcel readExcel = new ReadExcel("ScrubbingTestData");
//	String accountName = "Test_AccountN43";
	String accountName;
//	String sheetName = "Sheet1";
	Utils utils;
	ProrationPage proration;

	public void prorationSetup(String sheetName) throws InterruptedException {
		boolean result;
		loginPage = new LoginPage();
		utils = new Utils();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		workTrayPage = homePage.goToWorkTray();
		workTrayPage.verifyWorkTrayPage();
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		sovImportPage = workTrayPage.startCleansing();
		result = utils.isSuccess();
		Assert.assertTrue(result);
		proration = new ProrationPage();

		sovImportPage.clickOnSovImort();
		Thread.sleep(1000);

		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnNextButton();
		mergeSovPage = new MergeSOVPage();
		result = mergeSovPage.verifyMergeSovPageWithWorktryRedirection(accountName); //hnadling redirection
		Thread.sleep(2500);
//		mergeSovPage.deleteHiddenRowAndColumn();
		mappingSummaryPage = mergeSovPage.clickOnNextBtn();
		Thread.sleep(500);
		result = mappingSummaryPage.verifyMappingSummaryPage();
		Assert.assertTrue(result);
//		mappingSummaryPage.isMLPredictingOedColumns();
		Thread.sleep(7000);
		if (!mappingSummaryPage.isMLPredictingOedColumns()) {
			mappingSummaryPage.updateUnmappedColumns();
			result = utils.isSuccess();
			Assert.assertTrue(result);
		}
		else {		
		mappingSummaryPage.applySovColumnSearch("StateCode");
		result = mappingSummaryPage.updateMapping("StateCode", "AreaCode");
		Assert.assertTrue(result);
		mappingSummaryPage.removeSovColumnSearch();
		mappingSummaryPage.clickOnSaveBtn();
		result = utils.isSuccess();
		Assert.assertTrue(result);
		}

//		Thread.sleep(3000);
		rmTemplatePage = mappingSummaryPage.clickOnNextBtn();
		Assert.assertTrue(utils.isSuccess());
//		result = rmTemplatePage.verifyRMTemplatePage();
		result = rmTemplatePage.verifyRMTemplatePageWithWorktrayRedirection(sheetName);
		Assert.assertTrue(result);

	}

	@Test(priority = 1, description = "Verify Area Basis proration")
	public void verifyAreaBasisProration() throws InterruptedException {
		accountName = readConfig.getDataFromConfig("Scrubbing_AccountName");
		prorationSetup("Sheet7");
		proration.selectProrationTab();
		proration.selectAreaBasisProration();
//		Thread.sleep(1000);
		Assert.assertTrue(utils.isSuccess());
		String[] buildingTIVData = readExcel.getStringData("Proration", 10, 0).split(",");
		String[] BITIVData = readExcel.getStringData("Proration", 10, 1).split(",");
		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 1);
		boolean result2 = proration.verifyColumnData("BITIV", BITIVData, 1);
		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
	}

	@Test(priority = 2, description = "Verify included area basis proration on self basis")
	public void verifyIncludedAreaSelfBasisProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
		proration.selectIncludeAreaSelfBasisProration();
		Assert.assertTrue(utils.isSuccess());
		
		String[] buildingTIVData = readExcel.getStringData("Proration", 2, 0).split(",");
		String[] contentsTIVData = readExcel.getStringData("Proration", 2, 1).split(",");
		String[] BITIVData = readExcel.getStringData("Proration", 2, 2).split(",");
		String[] otherTIVData = readExcel.getStringData("Proration", 2, 3).split(",");

		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 4);
		boolean result2 = proration.verifyColumnData("Contents TIV", contentsTIVData, 4);
		boolean result3 = proration.verifyColumnData("BITIV", BITIVData, 4);
		boolean result4 = proration.verifyColumnData("Other TIV", otherTIVData, 4);

		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
		Assert.assertTrue(result3);
		Assert.assertTrue(result4);
	}

	@Test(priority = 3, description = "Verify included area basis proration on dependent basis")
	public void verifyIncludedAreaDependentBasisProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());

		proration.selectIncludeAreaDependentBasisProration();
		Assert.assertTrue(utils.isSuccess());

		String[] buildingTIVData = readExcel.getStringData("Proration", 2, 6).split(",");
		String[] contentsTIVData = readExcel.getStringData("Proration", 2, 7).split(",");
		String[] BITIVData = readExcel.getStringData("Proration", 2, 8).split(",");
		String[] otherTIVData = readExcel.getStringData("Proration", 2, 9).split(",");

		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 4);
		boolean result2 = proration.verifyColumnData("Contents TIV", contentsTIVData, 4);
		boolean result3 = proration.verifyColumnData("BITIV", BITIVData, 4);
		boolean result4 = proration.verifyColumnData("Other TIV", otherTIVData, 4);

		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
		Assert.assertTrue(result3);
		Assert.assertTrue(result4);

	}

//This test to verify Location Self Basis Proration based on Area code
	@Test(priority = 4, description = "Verify location basis proration on self basis")
	public void verifyLocationSelfBasisProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
		proration.selectLocationSelfBasisProration();
		Assert.assertTrue(utils.isSuccess());
		
		String[] buildingTIVData = readExcel.getStringData("Proration", 14, 0).split(",");

		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 7);
		Assert.assertTrue(result1);

	}

	@Test(priority = 5, description = "Verify location basis proration on dependent basis")
	public void verifyLocationDependentBasisProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
		proration.selectLocationDependentBasisProration();
		Assert.assertTrue(utils.isSuccess());

		String[] buildingTIVData = readExcel.getStringData("Proration", 14, 6).split(",");
		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 7);
		Assert.assertTrue(result1);
		proration.undoAllCoverages();
		utils.isSuccess();
	}

	@Test(priority = 6, description = "Verify included and location basis proration on self basis")
	public void verifyIncludeAndLocationSelfBasisProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
		proration.performIncludeAndLocationBasisProration("SELF");
		;
		Assert.assertTrue(utils.isSuccess());
		
		String[] buildingTIVData = readExcel.getStringData("Proration", 6, 0).split(",");
		String[] contentsTIVData = readExcel.getStringData("Proration", 6, 1).split(",");
		String[] BITIVData = readExcel.getStringData("Proration", 6, 2).split(",");
		String[] otherTIVData = readExcel.getStringData("Proration", 6, 3).split(",");

		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 4);
		boolean result2 = proration.verifyColumnData("Contents TIV", contentsTIVData, 4);
		boolean result3 = proration.verifyColumnData("BITIV", BITIVData, 4);
		boolean result4 = proration.verifyColumnData("Other TIV", otherTIVData, 4);

		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
		Assert.assertTrue(result3);
		Assert.assertTrue(result4);
	}

	@Test(priority = 7, description = "Verify included and location basis proration on  dependent basis")
	public void verifyIncludeAndLocationDependentBasisProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
		proration.performIncludeAndLocationBasisProration("DEPENDENT");
		Assert.assertTrue(utils.isSuccess());

		
		String[] buildingTIVData = readExcel.getStringData("Proration", 6, 6).split(",");
		String[] contentsTIVData = readExcel.getStringData("Proration", 6, 7).split(",");
		String[] BITIVData = readExcel.getStringData("Proration", 6, 8).split(",");
		String[] otherTIVData = readExcel.getStringData("Proration", 6, 9).split(",");

		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 4);
		boolean result2 = proration.verifyColumnData("Contents TIV", contentsTIVData, 4);
		boolean result3 = proration.verifyColumnData("BITIV", BITIVData, 4);
		boolean result4 = proration.verifyColumnData("Other TIV", otherTIVData, 4);

		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
		Assert.assertTrue(result3);
		Assert.assertTrue(result4);
	}

	@Test(priority = 8, description = "Verify Pipeline proration")
	public void verifyPipelineProration() throws InterruptedException {
		driver.navigate().refresh();
		prorationSetup("Sheet8");
		proration.performPipelineProration();
//		proration.selectLocationForPipeline(); //DE-selecting location
		Assert.assertTrue(utils.isSuccess());

		String[] locNameData = readExcel.getStringData("Proration", 18, 0).split(",");
		String[] buildingTIVData = readExcel.getStringData("Proration", 18, 1).split(",");
		boolean result1 = proration.verifyColumnData("Loc Name", locNameData, 7);
		boolean result2 = proration.verifyColumnData("Building TIV", buildingTIVData, 7);
		Assert.assertTrue(result1);
		Assert.assertTrue(result2);

	}

	@DataProvider(name = "Blanket Proration Self")
	public Object[][] readBlanketProrationSelflData() throws Exception {
		return readExcel.readExcelSheet("Blanket Proration", 2, 5, 4);
	}

	@Test(priority = 9, dataProvider = "Blanket Proration Self", description = "Verify Blanket Proration on self basis")
	public void verifyBlanketProrationOnSelfBasis(String blanketValue, String blanketCategory, String columnName,
			String columnData) throws InterruptedException {
		logger.info("Category: " + blanketCategory + "TIV: TIV1");
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
		String[] tivValues = columnData.split(",");
		proration.performBlanketProration("SELF", blanketValue, blanketCategory);
		Assert.assertTrue(utils.isSuccess());
		boolean result = proration.verifyColumnData(columnName, tivValues, 3);
		Assert.assertTrue(result);

	}

	@DataProvider(name = "Blanket Proration Dependent")
	public Object[][] readBlanketProrationDependentData() throws Exception {
		return readExcel.readExcelSheet("Blanket Proration", 9, 11, 4);
	}

	@Test(priority = 10, dataProvider = "Blanket Proration Dependent", description = "Verify Blanket Proration on dependent basis")
	public void verifyBlanketProrationOnDependentBasis(String blanketValue, String blanketCategory, String columnName,
			String columnData) throws InterruptedException {
		logger.info("Category: " + blanketCategory + "TIV: TIV1");
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
		String[] tivValues = columnData.split(",");
		proration.performBlanketProration("DEPENDENT", blanketValue, blanketCategory);
		Assert.assertTrue(utils.isSuccess());
		boolean result = proration.verifyColumnData(columnName, tivValues, 3);
		Assert.assertTrue(result);
	}
	
	@Test(priority = 11, description = "Verify IFM Proration")
	public void verifyIFMProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());

		String[] buildingTIVData = readExcel.getStringData("Proration", 10, 6).split(",");
		String[] contentsTIVData = readExcel.getStringData("Proration", 10, 7).split(",");
		proration.performIFMProration();
		Assert.assertTrue(utils.isSuccess());
		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 3);
		boolean result2 = proration.verifyColumnData("Contents TIV", contentsTIVData, 3);
		Assert.assertTrue(result1);
		Assert.assertTrue(result2);

	}

	@Test(priority = 12, description = "Verify Construction & Occupancy Proration")
	public void verifyConstructionAndOccupancyProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
//		String[] locaName = { "GREENS AT DORAL" };
		String[] locaName = readExcel.getStringData("Proration", 18, 6).split(",");
		String[] coveragesData = { "0" };
		proration.performConstructionAndOccupancyProration();
		int index = proration.getNumberOfRows();
		Assert.assertTrue(utils.isSuccess());
		boolean result1 = proration.verifyColumnData("Building TIV", coveragesData, index);
		boolean result2 = proration.verifyColumnData("Contents TIV", coveragesData, index);
		boolean result3 = proration.verifyColumnData("Loc Name", locaName, index);
		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
		Assert.assertTrue(result3);

	}

}
