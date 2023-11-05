package utilities;

import static utilities.InitTests.dir_path;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataReaderMap {
	 static Object[][] obj;

	@DataProvider(name = "getDataFromExcel")
	public static Object[][] getDataFromExcel(Method testMethod) throws IOException {
		String testClassName = getTestClassName(testMethod);
		Package PackagePath=getTestPackageName(testMethod);
		String packageName = PackagePath.getName();
		 
		
//		System.out.println("Calling class package path "+packageName);
		
		if (testClassName.contains(".")) {
			testClassName = testClassName.substring(testClassName.lastIndexOf(".") + 1);
		}
		Properties props = new Properties();

		
		ClassLoader loader = new DataReaderMap().getClass().getClassLoader();
		 InputStream input = loader.getResourceAsStream("config/testdata.properties");
		props.load(input);
		
		//String dir_path = props.getProperty("userdir");
		
		//dir_path = props.getProperty("userdir");
		
		//P:\git\Selenium_Automation\src\main\resources\Import_Document
		
		
		
		String excelPath=dir_path+"/src/main/resources/testdata/"+packageName+"/"+testClassName+".xlsx";
		
		System.out.println("excelPath "+excelPath);
		//excelPath = "C:\\Users\\yugandhar-gorrepati\\SeleniumAutomationUiFramework\\resources\\testdata\\GoogleSearch2.xlsx";
	
		System.out.println("in read excel sheet name --" + testMethod);
		FileInputStream file;
		try {
			file = new FileInputStream(new File(excelPath));
		
		// Get the workbook instance for XLS file
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(testMethod.getName());
		workbook.close();
		//System.out.println("data from excel path " + excelPath);
		int lastRowNum = sheet.getLastRowNum() ;
	    int lastCellNum = sheet.getRow(0).getLastCellNum();
	     obj = new Object[lastRowNum][1];

	    for (int i = 0; i < lastRowNum; i++) {
	      Map<Object, Object> datamap = new LinkedHashMap<>();
	      for (int j = 0; j < lastCellNum; j++) {
	    	  Cell cell=sheet.getRow(i+1).getCell(j);
	    	  
	    	  if(cell==null)
	    	System.out.println("empty cell");
	    	  else
	        datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
	      }
	      obj[i][0] = datamap;

	    }

		
		return obj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return obj;
		}

	}

	private static String getTestClassName(Method testMethod) {
		return testMethod.getDeclaringClass().getSimpleName();
	}
	
	private static Package getTestPackageName(Method testMethod) {
		return testMethod.getDeclaringClass().getPackage();
	}
	
	
	
}
