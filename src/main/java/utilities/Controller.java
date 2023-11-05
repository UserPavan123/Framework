package utilities;

import java.io.*;
import java.util.*;

import org.apache.poi.xssf.usermodel.*;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Controller {
   public static  List<XmlClass> myClasses = new ArrayList<XmlClass>();

	//change run method to main(String a[]) if you want to run from here.
	public static void main(String a[]) {
		ArrayList<String>testcaseList= new ArrayList<String>();
		XSSFWorkbook Workbook_obj = null;
		XSSFSheet smokeSheet=null;


		
			try{
				ClassLoader loader = new Controller().getClass().getClassLoader();
				 InputStream input = loader.getResourceAsStream("testdata/TestCases.xlsx");

				System.out.println(input.toString());
			
			System.out.println("Excel file read successfully...");
			Workbook_obj = new XSSFWorkbook(input);
			smokeSheet = Workbook_obj.getSheet("Smoke");
			XSSFSheet regSheet = Workbook_obj.getSheet("Regression");
			XSSFSheet restAPI = Workbook_obj.getSheet("RestAPI");
			XSSFSheet sauceLabs = Workbook_obj.getSheet("SauceLabs");
			XSSFSheet selGrid = Workbook_obj.getSheet("SeleniumGrid");

			Workbook_obj.close();
			//System.out.println("Excel sheet read successfully..."+sheet_obj);
			
		

		     addTestsFromSheet(smokeSheet);
		     addTestsFromSheet(regSheet);
		     addTestsFromSheet(restAPI);
		     addTestsFromSheet(sauceLabs);

		     addTestsFromSheet(selGrid);

			TestNG myTestNG = new TestNG();   
		     
		     //Create an instance of XML Suite and assign a name for it. 
		      XmlSuite mySuite = new XmlSuite(); 
		      mySuite.setName("MySuite"); 
		      //mySuite.setParallel(XmlSuite.ParallelMode.METHODS);   
		      mySuite.addListener("listeners.InitReports");
		      mySuite.addListener("listeners.RetryListener");

		      mySuite.addListener("listeners.DataTransformer");

//		      mySuite.addListener("atu.testng.reports.listeners.ATUReportsListener");
//		      mySuite.addListener("atu.testng.reports.listeners.ConfigurationListener");
//		      mySuite.addListener("atu.testng.reports.listeners.MethodListener");


		     //Create an instance of XmlTest and assign a name for it.  
		     XmlTest myTest = new XmlTest(mySuite); 
		     myTest.setName("MyTest");   
		  
		     //Add any parameters that you want to set to the Test. 
		     //myTest.setParameters(testngParams); 
		   
		      

		     //Assign that to the XmlTest Object created earlier. 
		     myTest.setXmlClasses(myClasses);   

		     //Create a list of XmlTests and add the Xmltest you created earlier to it.
		     List<XmlTest> myTests = new ArrayList<XmlTest>(); 
		     myTests.add(myTest);   

		     //add the list of tests to your Suite. 
		     mySuite.setTests(myTests);   

		     //Add the suite to the list of suites. 
		     List<XmlSuite> mySuites = new ArrayList<XmlSuite>(); 
		     mySuites.add(mySuite);   
		     
		     //Set the list of Suites to the testNG object you created earlier. 
		     myTestNG.setXmlSuites(mySuites);
		     File file = new File("TestNG.xml");
		        System.out.println("file"+file);

		        FileWriter writer = new FileWriter(file);
		        writer.write(mySuite.toXml());
		        writer.close(); 
		    System.out.println( mySuite.toXml());
		     myTestNG.run();
			}
		 catch (Exception e) {
			 e.printStackTrace();
			System.out.println("Exception "+e.getMessage());
		}
	
		
	}

	private static void addTestsFromSheet(XSSFSheet sheet) {
		// TODO Auto-generated method stub
		int Row_count = sheet.getLastRowNum();
		
		System.out.print("Row count: "+Row_count+"\n");
		for (int i = 1;i<=Row_count;i++)
		{
			System.out.println("I value :"+i);
			XSSFRow row_obj = sheet.getRow(i);
			XSSFCell cell_obj = row_obj.getCell(2);
			String Exec_indicator = cell_obj.getStringCellValue();
			System.out.print("Exec indicator: "+Exec_indicator+"\n");
			String Exec_ind = Exec_indicator.trim();
			System.out.println("Trim String: "+Exec_ind+"\n");
			if (Exec_ind.equalsIgnoreCase("Y"))
			{
				XSSFCell cellobj1 = row_obj.getCell(1);
				String testcase = cellobj1.getStringCellValue().trim();
				System.out.println("testcase : "+testcase);
				 //Create a list which can contain the classes that you want to run.
				if(sheet.getSheetName().contains("Smoke"))
			     myClasses.add(new XmlClass("ui.smoke.testcases."+testcase)); 
				else if(sheet.getSheetName().contains("Regression"))
				     myClasses.add(new XmlClass("ui.regression.testcases."+testcase)); 
				else
				     myClasses.add(new XmlClass("services.rest.testcases."+testcase)); 


			}
		}
	}
}
