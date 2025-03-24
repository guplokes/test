package com.cat.utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcelSheet {
	public	String[][] data;
	public String[][] readXLSX(String excelPath, String sheetName) throws Exception  
	{
		FileInputStream fs = new FileInputStream(excelPath);
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
	
		int totalNoofRow =sheet.getLastRowNum();
		int totalnoOfCol = sheet.getRow(1).getPhysicalNumberOfCells();
	
		System.out.println("Total No of Row=" +totalNoofRow);
	
		System.out.println("Total No of Column="+totalnoOfCol);
	
		System.out.println("");
	
		data=new String [totalNoofRow][totalnoOfCol];
		System.out.println("Test Data for "+sheetName);
		for(int row=1; row<= totalNoofRow; row++) 
		{
			for(int col=0;col<totalnoOfCol; col++)
			{
	  
				DataFormatter formatter = new DataFormatter();
				data[row-1][col] = formatter.formatCellValue(sheet.getRow(row).getCell(col));
				System.out.print(formatter.formatCellValue(sheet.getRow(row).getCell(col))+" ");
			}
			System.out.println("");
		}
		workbook.close();
		return data;
    	}
}
