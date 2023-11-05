package utilities;
import static utilities.DateUtils.getCurrTimeStamp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.Platform;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



/**
 * @author YugandharReddyGorrep
 *
 */
public class MyExtentReports extends InitTests
{
	/**
	 * Setting up the platform details
	 * 
	 * @param browserType
	 * @param url
	 * @param platform
	 * @param version
	 * @throws Exception
	 */
	public static String  timeStampFrJsp;
	public static String  timeStamp;
	public static ExtentReports reports;
	public static void setPlatformDetails(String browserType, String platform, String version, String url)
			throws IOException
	{
		reports.setSystemInfo("Browser", browserType);
		reports.setSystemInfo("Browser Version", version);
		 reports.setSystemInfo("platform", platform.toString());
		reports.setSystemInfo("URL", url);
	}
	/**
	 * Initialization of extent reports with time stamp
	 * 
	 */
	
	public void initExtentReports() throws IOException
	{
		try
		{
		Properties props = new Properties();
		
			//String reportLoc="/src/main/WebContent/extentReports";
			String DefPath="C:/SeleniumAutomation_Results";
			ClassLoader loader = this.getClass().getClassLoader();
			 InputStream input = loader.getResourceAsStream("config/testdata.properties");
			props.load(input);
			
			//System.setProperty("atu.reporter.config", dir_path +"/src/main/resources/config/atu.properties");
			
			String environment = props.getProperty("Environment");
			String browser = props.getProperty("browser");
			if(timeStamp==null)
				timeStamp=DateUtils.getCurrTimeStamp();
			System.out.println("extent report time stamp "+timeStamp);

			System.out.println("dir path in extent reports init "+ dir_path);
		//File f = new File(dir_path + reportLoc);
		File f = new File(DefPath);
		if (!f.exists())
		{			f.mkdir();
		}
		//f = new File(dir_path + reportLoc+"/" + timeStamp + ".html");
		f = new File(DefPath+"/" + timeStamp + ".html");
		f.createNewFile();
		//String reportFilePath = dir_path + reportLoc+"/" + timeStamp + ".html";
		String reportFilePath = DefPath+"/" + timeStamp + ".html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportFilePath);
		reports = new ExtentReports() ;
		reports.attachReporter(htmlReporter);
		// reports.addSystemInfo("Selenium Version", "2.53.1");
		//reports.addSystemInfo("Browser",browser);
		reports.setSystemInfo("Environment",environment);
		
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	}
	}
