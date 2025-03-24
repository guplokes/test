package com.cat.testCases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cat.pages.HomePage;
import com.cat.pages.LoginPage;
import com.cat.pages.MapModifierPage;
import com.cat.pages.MappingSummaryPage;
import com.cat.pages.MergeSOVPage;
import com.cat.pages.ProrationPage;
import com.cat.pages.RMTemplatePage;
import com.cat.pages.SOVImportPage;
import com.cat.pages.SOVPreviewDataPage;
import com.cat.pages.WorkTrayPage;
import com.cat.utilities.ReadExcel;
import com.cat.utilities.Utils;

public class EndToEnd_Scrubbing_Test extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	WorkTrayPage workTrayPage;
	SOVImportPage sovImportPage;
	MergeSOVPage mergeSovPage; // = new MergeSOVPage();
	SOVPreviewDataPage previewDataPage; // = new SOVPreviewDataPage();
	MappingSummaryPage mappingSummaryPage;
	RMTemplatePage rmTemplatePage;
	ProrationPage proration;
	Utils utils;
	boolean result;
	ReadExcel readScrubbingExcel = new ReadExcel("ScrubbingTestData");
//	ReadConfig config = new ReadConfig();
	String accountName; // = config.getDataFromConfig("AccountName");
	String sheetName = "Sheet1";
	private MapModifierPage mapModifierPage;

//	@BeforeTest
	public void scrubbingToolSetup() {
		accountName = readConfig.getDataFromConfig("AccountName");
		loginPage = new LoginPage();

		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		workTrayPage = homePage.goToWorkTray();
		workTrayPage.verifyWorkTrayPage();
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		sovImportPage = workTrayPage.startCleansing();
		result = utils.isSuccess();
		Assert.assertTrue(result);

//		sovImportPage.clickOnSovImort();
	}

	public void rmTemplateSetup(String sheetName) throws InterruptedException {

		proration = new ProrationPage();
		this.scrubbingToolSetup();
		sovImportPage.clickOnSovImort();
		Thread.sleep(1000);
		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnNextButton();
//		result = mergeSovPage.verifyMergeSOVPage();
		result = mergeSovPage.verifyMergeSovPageWithWorktryRedirection(accountName);
		Assert.assertTrue(result);
		mergeSovPage.deleteHiddenRowAndColumn();
		Assert.assertTrue(utils.isSuccess());
		mappingSummaryPage = mergeSovPage.clickOnNextBtn();
		Thread.sleep(1000);
		result = mappingSummaryPage.verifyMappingSummaryPage();
		Assert.assertTrue(result);
		if (!mappingSummaryPage.isMLPredictingOedColumns())
			mappingSummaryPage.updateUnmappedColumns();

		mappingSummaryPage.applySovColumnSearch("StateCode");
		result = mappingSummaryPage.updateMapping("StateCode", "AreaCode");
		Assert.assertTrue(result);
		mappingSummaryPage.removeSovColumnSearch();
		mappingSummaryPage.clickOnSaveBtn();
		result = utils.isSuccess();
		Assert.assertTrue(result);
		Thread.sleep(3000);
		rmTemplatePage = mappingSummaryPage.clickOnNextBtn();
//		result = rmTemplatePage.verifyRMTemplatePage();
		result = rmTemplatePage.verifyRMTemplatePageWithWorktrayRedirection(accountName);
		Assert.assertTrue(result);

	}

	@Test(priority = -1, description = "Verify user is able to navigate to Sov Import Page")
	public void goToSovImport() {
		utils = new Utils();
		this.scrubbingToolSetup();
		sovImportPage.clickOnSovImort();
		result = sovImportPage.verifySovImportPage();
		Assert.assertTrue(result);

	}

	@Test(priority = 1, description = "Verify Yes Delete Button on SOV Import")
	public void verifyYesDeleteButtonInSovImport() {
		int numberOfSheetsBefore = sovImportPage.getNumberOfSheetsInGrid();
		boolean result1 = sovImportPage.clickOnDeleteYesBtn();
		Assert.assertTrue(result1);
		boolean result2 = utils.isSuccess();
		Assert.assertTrue(result2);
		int numberOfSheetsAfter = sovImportPage.getNumberOfSheetsInGrid();
		Assert.assertEquals(numberOfSheetsAfter, numberOfSheetsBefore - 1);

	}

	@Test(priority = 2, description = "Verify Yes button in bulk delete on SOV Import")
	public void verifyBulkDeleteYesBtutton() throws InterruptedException {
		String[] sheetNames = readScrubbingExcel.getStringData("SOV Import", 2, 0).split(",");
		boolean result1 = sovImportPage.clickOnBulkDeleteYesButton(sheetNames);
		Assert.assertTrue(result1);
		boolean result2 = utils.isSuccess();
		Assert.assertTrue(result2);

	}

	@Test(priority = 3, description = "Verify that user is able to upload .xlsx file on SOV Import")
	public void importSOVFile() throws Exception {
		String fileName = readScrubbingExcel.getStringData("SOV Import", 2, 2);
		sovImportPage.selectSovFile(fileName);
		boolean result = utils.isSuccess();
		Assert.assertTrue(result);
	}

	@Test(priority = 4, description = "Verify functionality of Transpose on SOV Import")
	public void processSovUsingTranspose() {
		String sheetName = readScrubbingExcel.getStringData("SOV Import", 2, 4);
		boolean flag = sovImportPage.transpose(sheetName);

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(flag);
	}

	@Test(priority = 5, description = "Verify functionality of Key Merge on SOV Import")
	public void processSovUsingKeyMerge() {
		String[] sheetNames = readScrubbingExcel.getStringData("SOV Import", 2, 6).split(",");
		boolean flag = sovImportPage.keyMerge(sheetNames);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertTrue(flag);
	}

	@Test(priority = 5, description = "Verify Next button on SOV Import")
	public void verifyNextButtonInSovImport() {
		String sheetName = readScrubbingExcel.getStringData("SOV Import", 2, 4);
		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnNextButton();
		boolean result = new MergeSOVPage().verifyMergeSOVPage();
		sovImportPage.clickOnPreviousBtn();
		Assert.assertTrue(result);
	}

	@Test(priority = 6, description = "Verify user is able to navigate to SOV Preview Data Page")
	public void goToSOVPreviewDataPage() throws InterruptedException {
		mergeSovPage = new MergeSOVPage();
		previewDataPage = new SOVPreviewDataPage();
		driver.navigate().refresh();
		this.scrubbingToolSetup();
		sovImportPage.clickOnSovImort();
		Thread.sleep(1000);
		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnPreviewData();
		try {
		result = previewDataPage.verifySOVPreviewDataPage();
		Assert.assertTrue(result);
		}
		//Handling Worktray redirection
		catch(Exception NoSuchElementException) {
			Assert.assertTrue(workTrayPage.verifyWorkTrayPage());
			workTrayPage.applyColumnSearchOnAccountName(accountName);
			workTrayPage.clickOnAccount();
			sovImportPage = workTrayPage.startCleansing();
			result = utils.isSuccess();
			Assert.assertTrue(result);
			result = previewDataPage.verifySOVPreviewDataPage();
			Assert.assertTrue(result);
		}
		
//		Assert.assertTrue(result);
	}

	@Test(priority = 7, description = "Verify split Column Button by providing new column name on SOV Preview")
	public void validateSplitColumnInSovPreview() {

		String columnName = readScrubbingExcel.getStringData("SOV Preview Data", 2, 8);
		String newColumnName = readScrubbingExcel.getStringData("SOV Preview Data", 2, 10);
		String seperator = readScrubbingExcel.getStringData("SOV Preview Data", 2, 11);
		mergeSovPage.splitColumn(columnName, newColumnName, seperator);
		boolean flag = mergeSovPage.isColumnSplitted(columnName, newColumnName);
		Assert.assertTrue(flag);
	}

	@Test(priority = 7, description = "Verify  Rename Column Button by providing new column name on SOV Preview")
	public void validateRenameColumnNameInSOVPreview() throws InterruptedException {

		String columnName = readScrubbingExcel.getStringData("SOV Preview Data", 2, 0);
		String renamedColumnName = readScrubbingExcel.getStringData("SOV Preview Data", 2, 2);
		mergeSovPage.renameColumnName(columnName, renamedColumnName);
		Thread.sleep(1000);
		boolean flag = mergeSovPage.isColumnRenamed(renamedColumnName);
		Assert.assertTrue(flag);
	}

	@Test(priority = 8, description = "Verify functionality of delete columns button on SOV Preview")
	public void validateDeleteColumnInSOVPreview() {
		String values = readScrubbingExcel.getStringData("SOV Preview Data", 2, 4);
		String[] columnName = values.split(",");
		int numberOfColumnsBefore = mergeSovPage.getNumberOfImportedColumn();
		mergeSovPage.deleteColumn(columnName);

		boolean flag = utils.isSuccess();
		Assert.assertTrue(flag);
		int numberOfColumnsAfter = mergeSovPage.getNumberOfImportedColumn();
		Assert.assertEquals(numberOfColumnsAfter, numberOfColumnsBefore - columnName.length);
	}

	@Test(priority = 8, description = "Verify delete locations on SOV Preview")
	public void validateDeleteLocationRowInSOVPreview() throws InterruptedException {
		int numberOfLocationToDelete = 3;
		Thread.sleep(1000);
		int numberOfLocationRowBefore = mergeSovPage.getNumberOfImportedLocations();
		mergeSovPage.deleteLocation(numberOfLocationToDelete);
		Thread.sleep(1500);
		int numberOfLocationRowAfter = mergeSovPage.getNumberOfImportedLocations();
		Assert.assertEquals(numberOfLocationRowAfter, (numberOfLocationRowBefore - numberOfLocationToDelete));
	}

	@Test(priority = 9, description = "Verify functionality of Column Chooser Button on SOV Preview")
	public void columnChooserInSOVPreview() {
		String columnNames[] = readScrubbingExcel.getStringData("SOV Preview Data", 6, 6).split(",");
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length + 1);
	}

	@Test(priority = 9, description = "Verify functionality of Next Button on SOV Preview")
	public void verifyNextButtonInSOVPreview() {
		previewDataPage.clickOnNextBtn();
		boolean flag = mergeSovPage.verifyMergeSOVPage();
		mergeSovPage.clickOnPreviousBtn();
		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnPreviewData();
		Assert.assertTrue(flag);
	}

	//Handling redirection to worktray
	public boolean verifyMergeSovPageWithWorktryRedirection() {
		try {
		result = mergeSovPage.verifyMergeSOVPage();
		}
		//Handling Worktray redirection
		catch(Exception NoSuchElementException) {
			Assert.assertTrue(workTrayPage.verifyWorkTrayPage());
			workTrayPage.applyColumnSearchOnAccountName(accountName);
			workTrayPage.clickOnAccount();
			sovImportPage = workTrayPage.startCleansing();
			result = utils.isSuccess();
			Assert.assertTrue(result);
			result = mergeSovPage.verifyMergeSOVPage();			
		}
		return result;
	}
	@Test(priority = 10, description = "Verify user is able to navigate to Merge SOV Page")
	public void goToMergeSOVPage() {
		driver.navigate().refresh();
		this.scrubbingToolSetup();
		sovImportPage.clickOnSovImort(); // Thread.sleep(1000);
		sovImportPage.processSOV(sheetName); 
		// sovImportPage.clickOnPreviewData();
		// result = previewDataPage.verifySOVPreviewDataPage(); 
		//Assert.assertTrue(result);
		sovImportPage.clickOnNextButton();
//		result = mergeSovPage.verifyMergeSOVPage();
		result = mergeSovPage.verifyMergeSovPageWithWorktryRedirection(accountName);
		Assert.assertTrue(result);
	}

	@Test(priority = 11, description = "Verify remove hidden row and column button functionality on Merge SOV")
	public void removeHiddenRowAndColumnDataInMergeSOV() {
		mergeSovPage.deleteHiddenRowAndColumn();
		boolean flag = utils.isSuccess();
		Assert.assertTrue(flag);
	}

	@Test(priority = 12, description = "Verify  Rename Column Button by providing new column name on Merge SOV")
	public void verifyRenameColumnNameInMergeSOV() {
		String columnName = readScrubbingExcel.getStringData("MergeSOV", 2, 0);
		String renamedColumnName = readScrubbingExcel.getStringData("MergeSOV", 2, 2);
		mergeSovPage.renameColumnName(columnName, renamedColumnName);
		boolean result = utils.isSuccess();
		Assert.assertTrue(result);
		result = mergeSovPage.isColumnRenamed(renamedColumnName);
		Assert.assertTrue(result);
	}

	@Test(priority = 12, description = "Verify split Column Button by providing new column name on Merge SOV")
	public void verifySplitColumnInMergeSOV() {
		String columnName = readScrubbingExcel.getStringData("MergeSOV", 2, 8);
		String newColumnName = readScrubbingExcel.getStringData("MergeSOV", 2, 10);
		String seperator = readScrubbingExcel.getStringData("MergeSOV", 2, 11);
		mergeSovPage.splitColumn(columnName, newColumnName, seperator);
		boolean flag = mergeSovPage.isColumnSplitted(columnName, newColumnName);
		Assert.assertTrue(flag);
	}

	@Test(priority = 13, description = "Verify functionality of delete columns button on Merge SOV")
	public void verifyDeleteColumnInMergeSOV() {
		String data = readScrubbingExcel.getStringData("MergeSOV", 2, 4);
		String[] columnName = data.split(",");
		System.out.println(columnName);
		int numberOfColumnsBefore = mergeSovPage.getNumberOfImportedColumn();
		mergeSovPage.deleteColumn(columnName);
		boolean flag = utils.isSuccess();
		Assert.assertTrue(flag);
		int numberOfColumnsAfter = mergeSovPage.getNumberOfImportedColumn();
		Assert.assertEquals(numberOfColumnsAfter, numberOfColumnsBefore - columnName.length);
	}

	@Test(priority = 14, description = "Verify functionality of Column Chooser Button on Merge SOV")
	public void verifyColumnChooserButton() {
		String columnNames[] = readScrubbingExcel.getStringData("MergeSOV", 6, 4).split(",");
		boolean result = utils.columnChooser(columnNames);
		Assert.assertTrue(result);
		int actualNumberOfColumns = utils.getNumberOfColumnsInGrid();
		Assert.assertEquals(actualNumberOfColumns, columnNames.length + 3);
	}

	@Test(priority = 14, description = "Verify functionality of Next Button on Merge SOV")
	public void verifyNextButton() throws InterruptedException {
		MappingSummaryPage mappingPage = mergeSovPage.clickOnNextBtn();
		boolean result = mappingPage.verifyMappingSummaryPage();
		mergeSovPage.clickOnPreviousBtn();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 14, description =  "Verify functionality of Previous Button on Merge SOV")
	public void verifyPreviousButtonInMergeSOV() {
		mergeSovPage.clickOnPreviousBtn();
		boolean flag = sovImportPage.verifySovImportPage();
		Assert.assertTrue(flag);		
	}

	@Test(priority = 15, description = "Verify user is able to navigate to Mapping Summary Page")
	public void goToMappingSummaryPage() throws InterruptedException {
		driver.navigate().refresh();
		this.scrubbingToolSetup();
		sovImportPage.clickOnSovImort();
//		Thread.sleep(1000);
		sovImportPage.processSOV(sheetName);
		sovImportPage.clickOnNextButton();
//		Thread.sleep(1500);
//		result = mergeSovPage.verifyMergeSOVPage();
		result = mergeSovPage.verifyMergeSovPageWithWorktryRedirection(accountName);
		Assert.assertTrue(result);
		
		mappingSummaryPage = mergeSovPage.clickOnNextBtn();
		
		Thread.sleep(4000);
		result = mappingSummaryPage.verifyMappingSummaryPage();
		Assert.assertTrue(result);
	}

	@Test(priority = 16, description = "Verify Move Up button functionality on Mapping Summary")
	public void verifyMoveUpButton() {
		boolean result = mappingSummaryPage.moveUpSovColumn();
		Assert.assertTrue(result);
	}

	@Test(priority = 16, description = "Verify Move Down button button functionality on Mapping Summary")
	public void verifyMoveDownButton() {
		boolean result = mappingSummaryPage.moveDownSovColumn();
		Assert.assertTrue(result);
	}

	@Test(priority = 16, description = "Verify mapping of mapped Sov column on Mapping Summary")
	public void verifyMappingOfMappedSovColumn() throws InterruptedException {
		String sovColumn = "County";
		String oedColumn = "CountyCode";
		mappingSummaryPage.applySovColumnSearch(sovColumn);
		boolean result1 = mappingSummaryPage.updateMapping(sovColumn, oedColumn);
		mappingSummaryPage.removeSovColumnSearch();
		Assert.assertTrue(result1);
		boolean result2 = mappingSummaryPage.isColumnMapped(sovColumn, oedColumn);
		Assert.assertTrue(result2);
	}

	@Test(priority = 17, description = "Verify Review button on Mapping Summary")
	public void verifyReviewButton() {
		boolean result = mappingSummaryPage.isReviewUnmappedColumnDisplayed();
		Assert.assertTrue(result);
	}

	@Test(priority = 17, description = "Verify Clear mapping of mapped sov column on Mapping Summary")
	public void verifyClearMappingOfMappedColumn() {
		String[] sovColumns = { "Building Foundation", "Other TIV" };
		boolean result1 = mappingSummaryPage.clearMapping(sovColumns);
		Assert.assertTrue(result1);
//		boolean result = mappingSummaryPage.isMappingCleared(sovColumns);
//		Assert.assertTrue(result);		
	}

	@Test(priority = 18, description = "Verify Tabwise TIV button on Mapping Summary")
	public void verifyTabwiseTIVButton() {
		String[] args = { "BuildingTIV", "ContentTIV", "BITIV", "OtherTIV", "TotalTIV" };
		boolean result = mappingSummaryPage.isTabwiseTIVDisplayed(args);
		Assert.assertTrue(result);
	}

	@Test(priority = 18, description = "Verify visible Tab Name column switch on Mapping Summary")
	public void verifVisibleTabNameSwitch() throws InterruptedException {
		mappingSummaryPage.clickOnVisibleTabNameSwitch();
		Thread.sleep(500);
		boolean result = mappingSummaryPage.isTabNameColumnDisplayed();
		Assert.assertTrue(result);
		mappingSummaryPage.clickOnVisibleTabNameSwitch();
		Thread.sleep(500);
		result = mappingSummaryPage.isTabNameColumnDisplayed();
		Assert.assertFalse(result);
	}

	@Test(priority = 19, description = "Verify Next button functionality on Mapping Summary")
	public void verifyNextButtonInMappingSummary() {
		rmTemplatePage = mappingSummaryPage.clickOnNextBtn();
		boolean result = rmTemplatePage.verifyRMTemplatePage();
		mappingSummaryPage.clickOnPreviousBtn();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 19, description = "Verify Previous button functionality on Mapping Summary")
	public void verifyPreviousButton() {
		mappingSummaryPage.clickOnPreviousBtn();
		boolean result = mergeSovPage.verifyMergeSOVPage();
		Assert.assertTrue(result);
	}

	@Test(priority = 20, description = "Verify user is able to navigate to  RM Template Page")
	public void goToRMTemplatePage() throws InterruptedException {
		driver.navigate().refresh();
		this.rmTemplateSetup(sheetName);
		Thread.sleep(2000);
	}

	@Test(priority = 21, description = "Verify Find & Replace button functionality with valid values on RM Template")
	public void verifyFindAndReplaceWithValidValues() throws InterruptedException {
		Thread.sleep(1000);
		String columnName = readScrubbingExcel.getStringData("RMTemplate", 6, 0);
		String findText = readScrubbingExcel.getStringData("RMTemplate", 6, 2);
		String replaceText = readScrubbingExcel.getStringData("RMTemplate", 6, 3);
		String[] values = readScrubbingExcel.getStringData("RMTemplate", 6, 1).split(",");
		rmTemplatePage.findAndReplace(columnName.replace(" ", ""), findText, replaceText);
		Thread.sleep(1000);
		boolean result = utils.isSuccess();
		if (!result)
			rmTemplatePage.clickOnCloseButton();
		Assert.assertTrue(result);
		result = rmTemplatePage.verifyColumnData(columnName, values);
		Assert.assertTrue(result);

	}

//	@Test(priority = 22, description = "Verify user is able to apply multiplier on coverage column  on RM Template")
	public void verifyApplyMultilierButton() {
		String sheetName = readScrubbingExcel.getStringData("RMTemplate", 2, 0);
		String columnName = readScrubbingExcel.getStringData("RMTemplate", 2, 1);
		String factorValue = readScrubbingExcel.getStringData("RMTemplate", 2, 2);
		String data = readScrubbingExcel.getStringData("RMTemplate", 2, 3);
		String[] columnData = data.split(",");

		rmTemplatePage.applyMultilplier(sheetName, columnName.replace(" ", ""), factorValue);
		boolean result = rmTemplatePage.verifyColumnData(columnName, columnData);
		Assert.assertTrue(result);
	}

//	@Test(priority = 22, description = "Verify user is able to Undo coverages on RM Template")
	public void verifyUndoCoverages() {
		String columnName = readScrubbingExcel.getStringData("RMTemplate", 2, 1);
		String data = readScrubbingExcel.getStringData("RMTemplate", 2, 9);
		String[] columnData = data.split(",");
		rmTemplatePage.undoAllCoverages();										
		result = rmTemplatePage.verifyColumnData(columnName, columnData);
		Assert.assertTrue(result);
	}

	@Test(priority = 22, description = "Verify user is able to perform Annuaization on RM Template")
	public void verifyAnnualization() throws InterruptedException {
		String[] bipoi = readScrubbingExcel.getStringData("RMTemplate", 6, 10).split(",");
		rmTemplatePage.performAnnualization(bipoi);
		Thread.sleep(2500);
		String[] buildingTIVValues = readScrubbingExcel.getStringData("RMTemplate", 6, 11).split(",");
		boolean result1 = utils.isSuccess();
		Assert.assertTrue(result1);
		boolean result2 = rmTemplatePage.verifyColumnData("BITIV", buildingTIVValues);
		Assert.assertTrue(result2);
	}

	@Test(priority = 22, description = "Verify functionality of Filter Locations with valid values on RM Template")
	public void verifyFilterLocationsWithValidValues() throws InterruptedException {
		String columnName = readScrubbingExcel.getStringData("RMTemplate", 15, 4);
		String operator = readScrubbingExcel.getStringData("RMTemplate", 15, 5);
		String value = readScrubbingExcel.getStringData("RMTemplate", 15, 6);
		int expectedOutput = (int) readScrubbingExcel.getNumericData("RMTemplate", 15, 7);
		boolean result = rmTemplatePage.filterLocationData(columnName.replace(" ", ""), operator, value);
		Assert.assertTrue(result);
		Thread.sleep(3000);
		int numberOfRows = rmTemplatePage.getNumberOfRowsInGrid();
		rmTemplatePage.clickOnClearAllFilters();
		Assert.assertEquals(numberOfRows, expectedOutput);

	}

	@Test(priority = 23, description = "Verify functionality of Update Locations with valid values on RM template")
	public void verifyUpdateLocationWithValidValues() {
		String columnName = readScrubbingExcel.getStringData("RMTemplate", 15, 1);
		String value = readScrubbingExcel.getStringData("RMTemplate", 15, 2);
		String[] outputData = { value, value, value, value };
		boolean result = rmTemplatePage.updateLocationData(columnName.replace(" ", ""), value);
		Assert.assertTrue(result);
		result = utils.isSuccess();
		Assert.assertTrue(result);
		result = rmTemplatePage.verifyColumnData(columnName, outputData);
		Assert.assertTrue(result);
	}

	@Test(priority = 24, description = "Verify user is able to delete selected columns on RM Template")
	public void verifyDeleteColumns() throws InterruptedException {
		String values = readScrubbingExcel.getStringData("RMTemplate", 2, 11);
		String[] columnNames = values.split(",");
		System.out.println(columnNames);
		int numberOfColumnsBefore = rmTemplatePage.getNumberOfColumnsInGrid();
		System.out.println(numberOfColumnsBefore);
		boolean result1 = rmTemplatePage.deleteColumns(columnNames);

		Thread.sleep(1500);
		Assert.assertTrue(result1);
		boolean result2 = utils.isSuccess();
		Assert.assertTrue(result2);
		int numberOfColumnsAfter = rmTemplatePage.getNumberOfColumnsInGrid();
		System.out.println(numberOfColumnsAfter);
		Assert.assertEquals(numberOfColumnsAfter, numberOfColumnsBefore - columnNames.length);
	}

	@Test(priority = 24, description = "Verify user is able to Rename a Column on RM Template")
	public void verifyRenameColumnInRMTemplate() {
		String columnRenameFrom = readScrubbingExcel.getStringData("RMTemplate", 10, 8);
		String columnRenameTo = readScrubbingExcel.getStringData("RMTemplate", 10, 9);
		rmTemplatePage.renameColumnName(columnRenameFrom.replace(" ", ""), columnRenameTo.replace(" ", ""));
		Assert.assertTrue(utils.isSuccess());
		boolean result = rmTemplatePage.isColumnRenamed(columnRenameTo);
		Assert.assertTrue(result);
	}

	@Test(priority = 25, description = "Verify functionality of Column Chooser Button on RM Template")
	public void verifyColumnChooser() {
		String[] columnNames = readScrubbingExcel.getStringData("RMTemplate", 15, 11).split(",");
		;
		rmTemplatePage.columnChooser(columnNames);
		int numberOfColumnsInGrid = rmTemplatePage.getNumberOfColumnsInGrid();
		Assert.assertEquals(numberOfColumnsInGrid, columnNames.length + 3);
	}

	@Test(priority = 25, description = "Verify the functionality of Delete button by selecting rows on RM Template")
	public void verifyDeleteLocations() throws InterruptedException {
		int numberOfLocationsToDelete = 3;
		rmTemplatePage.deleteLocation(numberOfLocationsToDelete);
		Thread.sleep(2500);

		int actualNumberOfRowsInGrid = rmTemplatePage.getNumberOfRowsInGrid();

		Assert.assertEquals(actualNumberOfRowsInGrid, 8);
	}

	@Test(priority = 26, description = "Verify Area Basis proration")
	public void verifyAreaBasisProration() throws InterruptedException {
		driver.navigate().refresh();
		this.rmTemplateSetup("Sheet7");
		proration.selectProrationTab();
		proration.selectAreaBasisProration(); 
		Thread.sleep(1000);
		Assert.assertTrue(utils.isSuccess());
		String[] buildingTIVData = readScrubbingExcel.getStringData("Proration", 10, 0).split(",");
		String[] BITIVData = readScrubbingExcel.getStringData("Proration", 10, 1).split(",");
		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 1);
		boolean result2 = proration.verifyColumnData("BITIV", BITIVData, 1);
		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
	}

	@Test(priority = 27, description = "Verify included area basis proration on self basis")
	public void verifyIncludedAreaSelfBasisProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
		proration.selectIncludeAreaSelfBasisProration();
		Assert.assertTrue(utils.isSuccess());

		String[] buildingTIVData = readScrubbingExcel.getStringData("Proration", 2, 0).split(",");
		String[] contentsTIVData = readScrubbingExcel.getStringData("Proration", 2, 1).split(",");
		String[] BITIVData = readScrubbingExcel.getStringData("Proration", 2, 2).split(",");
		String[] otherTIVData = readScrubbingExcel.getStringData("Proration", 2, 3).split(",");

		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 4);
		boolean result2 = proration.verifyColumnData("Contents TIV", contentsTIVData, 4);
		boolean result3 = proration.verifyColumnData("BITIV", BITIVData, 4);
		boolean result4 = proration.verifyColumnData("Other TIV", otherTIVData, 4);

		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
		Assert.assertTrue(result3);
		Assert.assertTrue(result4);
	}

	@Test(priority = 28, description = "Verify included area basis proration on dependent basis")
	public void verifyIncludedAreaDependentBasisProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());

		proration.selectIncludeAreaDependentBasisProration();
		Assert.assertTrue(utils.isSuccess());

		String[] buildingTIVData = readScrubbingExcel.getStringData("Proration", 2, 6).split(",");
		String[] contentsTIVData = readScrubbingExcel.getStringData("Proration", 2, 7).split(",");
		String[] BITIVData = readScrubbingExcel.getStringData("Proration", 2, 8).split(",");
		String[] otherTIVData = readScrubbingExcel.getStringData("Proration", 2, 9).split(",");

		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 4);
		boolean result2 = proration.verifyColumnData("Contents TIV", contentsTIVData, 4);
		boolean result3 = proration.verifyColumnData("BITIV", BITIVData, 4);
		boolean result4 = proration.verifyColumnData("Other TIV", otherTIVData, 4);

		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
		Assert.assertTrue(result3);
		Assert.assertTrue(result4);

	}

	@Test(priority = 29, description = "Verify location basis proration on self basis")
	public void verifyLocationSelfBasisProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
		proration.selectLocationSelfBasisProration();
		Assert.assertTrue(utils.isSuccess());

		String[] buildingTIVData = readScrubbingExcel.getStringData("Proration", 14, 0).split(",");

		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 7);
		Assert.assertTrue(result1);

	}

	@Test(priority = 30, description = "Verify location basis proration on dependent basis")
	public void verifyLocationDependentBasisProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
		proration.selectLocationDependentBasisProration();
		Assert.assertTrue(utils.isSuccess());

		String[] buildingTIVData = readScrubbingExcel.getStringData("Proration", 14, 6).split(",");
		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 7);
		Assert.assertTrue(result1);
		proration.undoAllCoverages();
		utils.isSuccess();
	}

	@Test(priority = 31, description = "Verify included and location basis proration on self basis")
	public void verifyIncludeAndLocationSelfBasisProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
		proration.performIncludeAndLocationBasisProration("SELF");
		;
		Assert.assertTrue(utils.isSuccess());

		String[] buildingTIVData = readScrubbingExcel.getStringData("Proration", 6, 0).split(",");
		String[] contentsTIVData = readScrubbingExcel.getStringData("Proration", 6, 1).split(",");
		String[] BITIVData = readScrubbingExcel.getStringData("Proration", 6, 2).split(",");
		String[] otherTIVData = readScrubbingExcel.getStringData("Proration", 6, 3).split(",");

		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 4);
		boolean result2 = proration.verifyColumnData("Contents TIV", contentsTIVData, 4);
		boolean result3 = proration.verifyColumnData("BITIV", BITIVData, 4);
		boolean result4 = proration.verifyColumnData("Other TIV", otherTIVData, 4);

		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
		Assert.assertTrue(result3);
		Assert.assertTrue(result4);
	}

	@Test(priority = 32, description = "Verify included and location basis proration on  dependent basis")
	public void verifyIncludeAndLocationDependentBasisProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());
		proration.performIncludeAndLocationBasisProration("DEPENDENT");
		Assert.assertTrue(utils.isSuccess());

		String[] buildingTIVData = readScrubbingExcel.getStringData("Proration", 6, 6).split(",");
		String[] contentsTIVData = readScrubbingExcel.getStringData("Proration", 6, 7).split(",");
		String[] BITIVData = readScrubbingExcel.getStringData("Proration", 6, 8).split(",");
		String[] otherTIVData = readScrubbingExcel.getStringData("Proration", 6, 9).split(",");

		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 4);
		boolean result2 = proration.verifyColumnData("Contents TIV", contentsTIVData, 4);
		boolean result3 = proration.verifyColumnData("BITIV", BITIVData, 4);
		boolean result4 = proration.verifyColumnData("Other TIV", otherTIVData, 4);

		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
		Assert.assertTrue(result3);
		Assert.assertTrue(result4);
	}

	@Test(priority = 33, description = "Verify Pipeline proration")
	public void verifyPipelineProration() throws InterruptedException {
		driver.navigate().refresh();
		this.rmTemplateSetup("Sheet8");
		proration.performPipelineProration(); 
//		proration.selectLocationForPipeline(); // DE-selecting location
		Assert.assertTrue(utils.isSuccess());

		String[] locNameData = readScrubbingExcel.getStringData("Proration", 18, 0).split(",");
		String[] buildingTIVData = readScrubbingExcel.getStringData("Proration", 18, 1).split(",");
		boolean result1 = proration.verifyColumnData("Loc Name", locNameData, 7);
		boolean result2 = proration.verifyColumnData("Building TIV", buildingTIVData, 7);
		Assert.assertTrue(result1);
		Assert.assertTrue(result2);

	}

	@DataProvider(name = "Blanket Proration Self")
	public Object[][] readBlanketProrationSelflData() throws Exception {
		return readScrubbingExcel.readExcelSheet("Blanket Proration", 2, 2, 4);
	}

	@Test(priority = 34, dataProvider = "Blanket Proration Self", 
			description = "Verify Blanket Proration on self basis for building coverage",
			dependsOnMethods="verifyPipelineProration")
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
		return readScrubbingExcel.readExcelSheet("Blanket Proration", 9, 9, 4);
	}

//	@Test(priority = 35, dataProvider = "Blanket Proration Dependent",
//			description = "Verify Blanket Proration on dependent basis for building coverage", dependsOnMethods="verifyPipelineProration")
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

	@Test(priority = 36, description = "Verify IFM Proration", dependsOnMethods="verifyPipelineProration")
	public void verifyIFMProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess());

		String[] buildingTIVData = readScrubbingExcel.getStringData("Proration", 10, 6).split(",");
		String[] contentsTIVData = readScrubbingExcel.getStringData("Proration", 10, 7).split(",");
		proration.performIFMProration();
		Assert.assertTrue(utils.isSuccess());
		boolean result1 = proration.verifyColumnData("Building TIV", buildingTIVData, 3);
		boolean result2 = proration.verifyColumnData("Contents TIV", contentsTIVData, 3);
		Assert.assertTrue(result1);
		Assert.assertTrue(result2);

	}

	@Test(priority = 37, description = "Verify Construction & Occupancy Proration", dependsOnMethods="verifyPipelineProration")
	public void verifyConstructionAndOccupancyProration() {
		proration.undoAllCoverages();
		Assert.assertTrue(utils.isSuccess()); // String[] locaName = { "GREENS AT DORAL" };

		String[] locaName = readScrubbingExcel.getStringData("Proration", 18, 6).split(",");
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

//	@Test(priority = 38, description = "Verify user is able to view Validation Error popup while submitting to QA")
	public void verifyValidationErrors() throws InterruptedException {
		driver.navigate().refresh();
		this.rmTemplateSetup("Sheet1");
		rmTemplatePage.clickOnSubmitToQA();
		result = rmTemplatePage.isValidationErrorsDisplayed();
		if (result)
			rmTemplatePage.closeValidationErrorPopup();
		Assert.assertTrue(result);
	}

	@Test(priority = 39, description = "verify user is able to navigate to map modifier page")
	public void goToMapModifierPage() throws InterruptedException {
		driver.navigate().refresh();
		this.rmTemplateSetup("Sheet1");
//		this.scrubbingToolSetup();

		result = rmTemplatePage.verifyRMTemplatePage();
		Assert.assertTrue(result);

		mapModifierPage = rmTemplatePage.clickOnMapModifierBtn();
		Thread.sleep(2000);
		boolean result = mapModifierPage.verifyMapMpdifierPage();
		Assert.assertTrue(result);

	}

	@Test(priority = 40, description = "Verify user is able to click on all the invalid modifier tab one by one ")
	public void updateModifiersOnInvalidTab() {
		boolean result = mapModifierPage.updateModifierData();
		Assert.assertTrue(result);
		mapModifierPage.clickOnCloseButton();
	}

	@Test(priority = 41, description = "Verify user is able to submit account for cleansing QC")
	public void submitAccountToCleansingQC() {
		rmTemplatePage.clickOnSubmitToQA();
		rmTemplatePage.submitCleansingQC();
		result = utils.isSuccess();
		Assert.assertTrue(result);
		result = workTrayPage.verifyWorkTrayPage();
		Assert.assertTrue(result);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test(priority = 42, description = "Verify user is able to start QC Verification and able to view Merge Sov Page")
	public void startCleansingQC() throws InterruptedException {
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		Thread.sleep(6000);
		workTrayPage.startQCVerification();
		result = mergeSovPage.verifyMergeSOVPage();
		Assert.assertTrue(result);
		sovImportPage.clickOnMappingSummaryTab();
		result = mappingSummaryPage.verifyMappingSummaryPage();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 43, description = "Verify user is able approve Mapping on Mapping Summary Page during QC Verification")
	public void approveMappingOnMappingSummary() {
		result = mappingSummaryPage.verifyMappingSummaryPage();
		Assert.assertTrue(result);
		mappingSummaryPage.approveMappingForCleansingQC();
		result  = utils.isSuccess();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 43, description = "Verify user is able to navigate to RM Template Page during QC Verification")
	public void verifyRmTemplatePageOnQCVerification() {
		sovImportPage.clickOnRMTemplateTab();
		result = rmTemplatePage.verifyRMTemplatePage();
		Assert.assertTrue(result);		
	}
	
	@Test(priority = 44, description = "Verify user is able to approve account and submit cleansing QC Complete")
	public void completeCleansingQC() throws InterruptedException {
		driver.navigate().refresh();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		workTrayPage = homePage.goToWorkTray();
		workTrayPage.verifyWorkTrayPage();
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		workTrayPage.clickOnAccount();
		Thread.sleep(6000);
		workTrayPage.startQCVerification();
		rmTemplatePage.submitCleansingQCComplete("Automation Test");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = workTrayPage.verifyWorkTrayPage();
		Assert.assertTrue(result);
	}
	
	@Test(priority = 45, description = "Verify user is able to submit account to LMS")
	public  void reviewAndAuditOnWorkTray() throws InterruptedException {
		driver.navigate().refresh();
		accountName = readConfig.getDataFromConfig("AccountName");
		loginPage = new LoginPage();
		homePage = loginPage.LoginToApp(readConfig.getUserName(), readConfig.getPassword());

		workTrayPage = homePage.goToWorkTray();
		workTrayPage.verifyWorkTrayPage();
		workTrayPage.applyColumnSearchOnAccountName(accountName);
		Thread.sleep(1000);
		workTrayPage.clickOnReviewAndSubmitBtn();
		result = workTrayPage.verifyMRFReviewForm();
		Assert.assertTrue(result);
		Thread.sleep(1000);
		workTrayPage.submitAccountToLms();
		Thread.sleep(1500);
		result  = utils.isSuccess();
		Assert.assertTrue(result);
	}
}
