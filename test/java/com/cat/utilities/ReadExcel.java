package com.cat.utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	XSSFWorkbook wb;
	DataFormatter formatter;
	private String[][] data;

	public ReadExcel() {
		File src = new File("./TestData/TestData.xlsx");

		try {
			FileInputStream FIS = new FileInputStream(src);
			formatter = new DataFormatter();

			wb = new XSSFWorkbook(FIS);
		}

		catch (Exception e) {
			System.out.println("Could not read the excel file." + e.getMessage());
		}
	}
	
	public ReadExcel(String fileName) {
		File src = new File("./TestData/" + fileName + ".xlsx");

		try {
			FileInputStream FIS = new FileInputStream(src);
			formatter = new DataFormatter();

			wb = new XSSFWorkbook(FIS);
		}

		catch (Exception e) {
			System.out.println("Could not read the excel file." + e.getMessage());
		}
	}

	public String getStringData(int SheetIndex, int row, int col) {
		return formatter.formatCellValue(wb.getSheetAt(SheetIndex).getRow(row).getCell(col));

		// return
		// wb.getSheetAt(SheetIndex).getRow(row).getCell(col).getStringCellValue();

		/*
		 * if(cell.getCellType()==CellType.STRING) data = cell.getStringCellValue();
		 * else if(cell.getCellType()==CellType.NUMERIC) data =
		 * String.valueOf(cell.getNumericCellValue());
		 */
	}

	public String getStringData(String sheetName, int Row, int Col) {
		// return wb.getSheet(sheetName).getRow(Row).getCell(Col).getStringCellValue();
		return formatter.formatCellValue(wb.getSheet(sheetName).getRow(Row).getCell(Col));
	}

	public double getNumericData(String sheetName, int row, int Col) {
		return  wb.getSheet(sheetName).getRow(row).getCell(Col).getNumericCellValue();
	}

	public String[][] readExcelSheet(String sheetName) throws Exception {
		//FileInputStream fs = new FileInputStream(excelPath);
		//XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = wb.getSheet(sheetName);

		int totalNoofRow = sheet.getLastRowNum();
		int totalnoOfCol = sheet.getRow(0).getPhysicalNumberOfCells();



		data = new String[totalNoofRow][totalnoOfCol];
		for (int row = 1; row <= totalNoofRow; row++) {
			for (int col = 0; col < totalnoOfCol; col++) {

				DataFormatter formatter = new DataFormatter();
				if(sheet.getRow(row).getCell(col) == null) //to handle empty columns in data
					data[row-1][col] = "";
				else
					data[row - 1][col] = formatter.formatCellValue(sheet.getRow(row).getCell(col));
				
			}
			
		}
		return data;
	}
	
	/*@Method
	 * firstRow & lastRow starts from index 0,
	 *  numberOfColumn: total no columns and it should always contain data from index 0
	 *  firsRow: index of row from which data starts not column name
	 */
	public String[][] readExcelSheet(String sheetName, int firstRow, int lastRow, int numberOfColumn) throws Exception {

		XSSFSheet sheet = wb.getSheet(sheetName);

		int totalNoofRow = (lastRow - firstRow) + 1;


		data = new String[totalNoofRow][numberOfColumn];
		int dataRow = firstRow; //row from which data is stored in xcel
		for (int row = 0; row < totalNoofRow; row++) {
			for (int col = 0; col < numberOfColumn; col++) {
				
				DataFormatter formatter = new DataFormatter();
				if(sheet.getRow(dataRow).getCell(col) == null) //to handle empty columns in data
					data[row][col] = "";
				else
					data[row][col] = formatter.formatCellValue(sheet.getRow(dataRow).getCell(col));
			}
			dataRow++;			
		}
		return data;
	}
}
