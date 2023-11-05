package listeners;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import utilities.MyExtentReports;
/**
 * @author YugandharReddyGorrep
 *
 */
public class InitReports extends TestListenerAdapter
{
	/**
	 * @description: Calling initialization of extent reports.
	 * 
	 * @throws Exception
	 */
	public InitReports() throws IOException
	{
		MyExtentReports extentReports=new MyExtentReports(); // Initialization of -Extent reports and ATU Reports
		extentReports.initExtentReports();
		
	}
	protected static Logger logger = Logger.getLogger(InitReports.class);
	static File directory;
	@Override
	public void onTestStart(ITestResult testResult)
	{
		// TODO Auto-generated method stub
		logger.info("Test '" + testResult.getName() + "' STARTED\n");
	}
	@Override
	public void onFinish(ITestContext context)
	{
		logger.info("All Tests were Finished.........\n\n");
	}
	@Override
	public void onTestSuccess(ITestResult testResult)
	{
		// TODO Auto-generated method stub
		logger.info("Test '" + testResult.getName() + "' PASSED");
		logger.info("test ID:" + testResult.getMethod().getDescription() + "\n\n");
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void onTestFailure(ITestResult testResult)
	{
		logger.info("Test '" + testResult.getName() + "' FAILED");
		logger.info("test ID:" + testResult.getMethod().getDescription() + "\n\n");
		
	}
	@Override
	public void onTestSkipped(ITestResult arg0)
	{
		// TODO Auto-generated method stub
		// Utils.getDriver().manage().deleteAllCookies() ;
	}
	/*
	 * Deleting the Folder and Files inside the Folder
	 */
	public static boolean deleteDir(File dir)
	{
		if (dir.exists())
		{
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++)
			{
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success)
				{
					return false;
				}
			}
		}
		return dir.delete();
	}
	
}
