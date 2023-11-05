package utilities;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import utilities.InitTests;
public class RetryAnalyzer implements IRetryAnalyzer
{
	private int count = 0;
	private int maxCount; // set your count to re-run test
	 @Override
	public boolean retry(ITestResult result)
	{
		try
		{
			System.out.println("in RetryAnalyzer retry method");
			maxCount = Integer.parseInt(InitTests.getPropValue("Retry_test_count").trim());
			System.out.println("Retrying the failed test case:" + maxCount);
			if (count < maxCount)
			{
				count++;
				return true;
			}
		} catch (NumberFormatException e)
		{
			e.getStackTrace();
		}
		return false;
	}
}