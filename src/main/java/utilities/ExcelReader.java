package utilities;

import java.io.*;
import java.util.*;

import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

	public static ArrayList<String>ReadDriverSuiteExcel(String sheetName) {
		ArrayList<String>testcaseList= new ArrayList<String>();
		XSSFWorkbook Workbook_obj = null;
		XSSFSheet sheet_obj=null;
	

		
			try{
				ClassLoader loader = new ExcelReader().getClass().getClassLoader();
				 InputStream input = loader.getResourceAsStream("testdata/TestCases.xlsx");

				System.out.println(input.toString());
			
			System.out.println("Excel file read successfully...");
			Workbook_obj = new XSSFWorkbook(input);
			System.out.println("Before reading Excel sheet...."+sheetName);
			sheet_obj = Workbook_obj.getSheet(sheetName);
			Workbook_obj.close();
			System.out.println("Excel sheet read successfully..."+sheet_obj);
			
			int Row_count = sheet_obj.getLastRowNum();
			
			System.out.print("Row count: "+Row_count+"\n");
			for (int i = 1;i<=Row_count;i++)
			{
				System.out.println("I value :"+i);
				XSSFRow row_obj = sheet_obj.getRow(i);
				XSSFCell cell_obj = row_obj.getCell(2);
				String Exec_indicator = cell_obj.getStringCellValue();
				System.out.print("Exec indicator: "+Exec_indicator+"\n");
				String Exec_ind = Exec_indicator.trim();
				System.out.println("Trim String: "+Exec_ind+"\n");
				if (Exec_ind.equalsIgnoreCase("Y"))
				{
					XSSFCell cellobj1 = row_obj.getCell(1);
					String testcase = cellobj1.getStringCellValue();
					System.out.println("testcase : "+testcase);
					testcaseList.add(testcase);
				}
			}
			}
		 catch (Exception e) {
			 e.printStackTrace();
			System.out.println("Exception "+e.getMessage());
		}
	
		
		return testcaseList;
	}
}
