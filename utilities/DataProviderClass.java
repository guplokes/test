package com.cat.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.cat.testCases.BaseClass;

/*This class has a generic DataProvider method.
*It depends on the name of Test method.
*Name of sheet in Excel Should be exact same as the name of Test method.
*Otherwise, DataProvider Annotation won't work and eventually TestCase
*will fail.*/

public class DataProviderClass extends BaseClass {
	//ReadExcel objExcl = new ReadExcel();

@DataProvider (name = "data-provider")
public Object[][] readXcelData(Method m){
	System.out.println(m.getName());
	String sheetName = m.getName();
	Object[][] data = null;;
	try {
		data = objExcl.readExcelSheet(sheetName);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return data;
}
}
