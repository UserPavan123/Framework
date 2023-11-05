package GcForce;
import org.openqa.selenium.By;



import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;
//import java.util.Random;

import org.openqa.selenium.WebDriver;

import static driverfactory.Driver.clickElement;
import static driverfactory.Driver.setInput;
import static driverfactory.Driver.waitForElementToDisplay;
//import org.openqa.selenium.support.ui.WebDriverWait;
import static utilities.MyExtentReports.reports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import driverfactory.Driver;
import PageObj_GcForce.GCForce_CompanyPage;
import PageObj_GcForce.GCForce_Login;
import utilities.DataReaderMap;
import utilities.InitTests;
import verify.SoftAssertions;


public class Create_Company extends InitTests 

{
	static ExtentTest test=null;
//	static WebDriver chromeDriver = null;
	static WebDriver driver = null;
	static Driver driverFact=new Driver();
	
	
	/**
	 * @author Yugandhar Reddy
	 * @description:Instantiate the webdriver based on the browser type and other params
	 * WebDriver
	 * @throws Exception
	 */
	@BeforeTest
	public static void init() throws Exception
	
	{
		
		test = reports.createTest("Create New Company", "Create New object-Company");
		test.assignCategory("GCForce-Sanity Scripts");
		
		
		driver=driverFact.initWebDriver(BASEURL,"chrome","","","local",test,"");
//		driver=driverFact.initWebDriver(BASEURL,"CHROME","","","CHROME_DEV",test,"");
//		driver=driverFact.initWebDriver(BASEURL,"safari","latest","latest","saucelabs",test,"");
//		driver=driverObj.initWebDriver( BASEURL,"safari","latest","Mac 10.13","saucelabs",test,"");
		
//		WebDriverWait wait = new WebDriverWait(chromeDriver, 10);
	    GCForce_Login LognPage=new GCForce_Login(driver);
		
		try
		{
		    LognPage.login(USERNAME,PASSWORD);
		    
		    
		    
		    
//		    ATUReports.add("Enter user credentials to login", LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		    test.log(Status.PASS, "Enter user credentials of GC login");
					
		  } catch (Error e) {
				e.printStackTrace();
				SoftAssertions.fail(e, Driver.getScreenPath(driver,new Exception().getStackTrace()[0].getMethodName()),test);
				
//				ATUReports.add("testSeleniumGrid()", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				softAssert.assertAll();
	
			} catch (Exception e) {
				e.printStackTrace();
				SoftAssertions.fail(e,Driver.getScreenPath(driver,new Exception().getStackTrace()[0].getMethodName()),test);
//				ATUReports.add("testSeleniumGrid()", e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				softAssert.assertAll();
			} 
			
	}

	@Test(priority = 1, enabled = true,dataProvider="getDataFromExcel",dataProviderClass=DataReaderMap.class)
	public void Company(Map<String, String> row) throws Exception
	{
		try
		{
			
			String StrCompanyName=row.get("CompanyName");
			String StrCompanyLevel=row.get("CompanyLevel");
			String StrClientType=row.get("ClientType");
			String StrSegment=row.get("Segment");
			String StrAnnualRevenue=row.get("AnnualRevenue");
			String StrTotalRevenue=row.get("TotalPotentialRevenue");
			String StrStreet=row.get("HeadQuarterStreet");
			String StrHQ_Country=row.get("HeadQuarterCountry");
			String StrHQ_City=row.get("HeadQuarterCity");
			String StrHQ_ZIP=row.get("HeadCountryZIP");
			
			Thread.sleep(20000);
			GCForce_CompanyPage Company_Pg=new GCForce_CompanyPage(driver);	  	  
			Thread.sleep(3000);
			
			waitForElementToDisplay(Company_Pg.DashBoard);
			
	        waitForElementToDisplay(Company_Pg.CompanyOnly);
	        clickElement(Company_Pg.CompanyOnly);
	        
	        Thread.sleep(1000);
	        
	        waitForElementToDisplay(Company_Pg.ContactNew);
	        clickElement(Company_Pg.ContactNew);
	        
//	        ATUReports.add("Click on the new company button from the menu", LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	        test.log(Status.PASS, "Click on the new company button from the menu");
	        
	       
	        
	        Thread.sleep(100);
	    	
	    	waitForElementToDisplay(Company_Pg.CreateCompany_CompName);
	    	clickElement(Company_Pg.CreateCompany_CompName);
	    	setInput(Company_Pg.CreateCompany_CompName,StrCompanyName);
	    	
	    	
	    	waitForElementToDisplay(Company_Pg.CreateCompany_CompName);
	    	clickElement(Company_Pg.CreateCompany_CompName);
	    	setInput(Company_Pg.CreateCompany_CompName,StrCompanyName);
	    	
	    	waitForElementToDisplay(Company_Pg.CreateCompany_CompLevel);
	    	clickElement(Company_Pg.CreateCompany_CompLevel);

	    	waitForElementToDisplay(By.xpath("//a[text()='"+StrCompanyLevel+"']"));
	        clickElement(driver.findElement(By.xpath("//a[text()='"+StrCompanyLevel+"']")));
	    	
	    	waitForElementToDisplay(Company_Pg.CreateCompany_ClientLevel);
	    	clickElement(Company_Pg.CreateCompany_ClientLevel);
	    	
	    	waitForElementToDisplay(By.xpath("//a[text()='"+StrClientType+"']"));
	        clickElement(driver.findElement(By.xpath("//a[text()='"+StrClientType+"']")));
			
	    	waitForElementToDisplay(Company_Pg.CreateCompany_Segment);
	    	clickElement(Company_Pg.CreateCompany_Segment);
			
	    	Thread.sleep(100);
	    	
	    	waitForElementToDisplay(By.xpath("//a[text()='"+StrSegment+"']"));
	        clickElement(driver.findElement(By.xpath("//a[text()='"+StrSegment+"']")));
	    	
	    	waitForElementToDisplay(Company_Pg.CreateCompany_AnnualRevenue);
	    	setInput(Company_Pg.CreateCompany_AnnualRevenue,StrAnnualRevenue);
	    	
	    	waitForElementToDisplay(Company_Pg.CreateCompany_TotalPotential_Revenue);
	    	setInput(Company_Pg.CreateCompany_TotalPotential_Revenue,StrTotalRevenue);
	    	
	    	waitForElementToDisplay(Company_Pg.CreateCompany_HeadQuarter_Street);
	    	setInput(Company_Pg.CreateCompany_HeadQuarter_Street,StrStreet);
	    	
	    	waitForElementToDisplay(Company_Pg.CreateCompany_HeadQuarter_City);
	    	setInput(Company_Pg.CreateCompany_HeadQuarter_City,StrHQ_City);
	    	
	    	waitForElementToDisplay(Company_Pg.CreateCompany_HeadQuarter_PO_Code);
	    	setInput(Company_Pg.CreateCompany_HeadQuarter_PO_Code,StrHQ_ZIP);
	    	
	    	waitForElementToDisplay(Company_Pg.CreateCompany_HQ_Country);
	    	clickElement(Company_Pg.CreateCompany_HQ_Country);
	    	Thread.sleep(100);
	    	    	
	    	waitForElementToDisplay(By.xpath("//a[text()='"+StrHQ_Country+"']"));
	        clickElement(driver.findElement(By.xpath("//a[text()='"+StrHQ_Country+"']")));
	        	    	
	        waitForElementToDisplay(Company_Pg.SaveContactBtn);
	        clickElement(Company_Pg.SaveContactBtn);
	        
	        
//	        ATUReports.add("Enter all the details of Company object", LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	        test.log(Status.PASS, "Enter all the details of company object");

	        Thread.sleep(5000);
	        
//			ATUReports.add("Company "+StrCompanyName+ " is created sucessfully", LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			test.log(Status.PASS, "Company "+StrCompanyName+ " is created sucessfully");
					
	  }catch (Error e) {
		e.printStackTrace();
		SoftAssertions.fail(e, Driver.getScreenPath(driver,new Exception().getStackTrace()[0].getMethodName()),test);
		
		
//		ATUReports.add("testSeleniumGrid()", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		test.log(Status.FAIL, "Company workflow failed");
		test.log(Status.FAIL, "details", MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
		softAssert.assertAll();

		

	} catch (Exception e) {
		e.printStackTrace();
		SoftAssertions.fail(e,Driver.getScreenPath(driver,new Exception().getStackTrace()[0].getMethodName()),test);
		test.log(Status.FAIL, "Company creation workflow failed");
		test.log(Status.FAIL, "details", MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
		
//		ATUReports.add("testSeleniumGrid()", e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		softAssert.assertAll();
	}

		finally
		{
			reports.flush();
			
		}
	
	}
	
	@org.testng.annotations.AfterTest
	public void tearDown() throws IOException 
	{
		driver.quit();
	}



}
