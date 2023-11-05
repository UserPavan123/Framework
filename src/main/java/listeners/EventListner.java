package listeners;

import org.openqa.selenium.By;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

//import atu.testng.reports.ATUReports;
//import atu.testng.reports.logging.LogAs;
//import atu.testng.selenium.reports.CaptureScreen;

/**
 * @author YugandharReddyGorrep
 *
 */
public class EventListner implements WebDriverEventListener
{
	ExtentTest test;
	
	public EventListner(ExtentTest test)
	{
		this.test=test;
	}
	@Override
	public void beforeNavigateTo(String url, WebDriver driver)
	{
	}
	/**
	 * @description:Logs each navigation of AUT into extent report.
	 * 
	 * @param url
	 * @param WebDriver
	 * 
	 */
	@Override
	public void afterNavigateTo(String url, WebDriver arg)
	{
		//test.log(LogStatus.INFO, "afterNavigateTo()", "navigated to " + url + " page title : " + arg.getTitle());
		
		System.out.println("navigated to " + url + " page title : " + arg.getTitle());
	}
	@Override
	public void beforeNavigateBack(WebDriver driver)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void afterNavigateBack(WebDriver driver)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void beforeNavigateForward(WebDriver driver)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void afterNavigateForward(WebDriver driver)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void beforeNavigateRefresh(WebDriver driver)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void afterNavigateRefresh(WebDriver driver)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver)
	{
		// test.log(LogStatus.INFO, "beforeFindBy() ", by.toString());
	}
	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver)
	{
		// test.log(LogStatus.INFO, "element found with ", by.toString());
	}
	@Override
	public void beforeClickOn(WebElement element, WebDriver driver)
	{
		// test.log(LogStatus.INFO, "beforeClickOn()",
		// "before click on locator - [" + element.toString().split("-> ",
		// 2)[1]);
	}
	/**
	 * @description:Logs each click of AUT into extent report.
	 * 
	 * @param WebElement
	 * @param WebDriver
	 * 
	 */
	@Override
	public void afterClickOn(WebElement element, WebDriver driver)
	{
		try
		{
			String html=element.getAttribute("outerHTML");
			//commented by pavan s on 4th Oct 
			//test.log(Status.INFO, "afterClickOn() " +html);

			//ATUReports.add("afterClickOn() ", "Locator--" + html, LogAs.INFO,new CaptureScreen(element));
			System.out.println("afterClickOn " + element);
			
		} catch (StaleElementReferenceException e)
		{

			//ATUReports.add("afterClickOn() ", "Locator--" + element.toString(), LogAs.INFO,new CaptureScreen(element));
//			test.log(Status.INFO, "afterClickOn() "+element.toString());
			System.out.println("ignoring staleElement Exception");
			System.out.println("afterClickOn " + element);
		}
	}
	@Override
	public void beforeScript(String script, WebDriver driver)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void afterScript(String script, WebDriver driver)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void onException(Throwable throwable, WebDriver driver)
	{
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * beforeChangeValueOf(org.openqa.selenium.WebElement,
	 * org.openqa.selenium.WebDriver)
	 */
	public void beforeChangeValueOf(WebElement element, WebDriver driver)
	{
		// TODO Auto-generated method stub
	}
	/**
	 * @description:Logs each change value of AUT into extent report.
	 * 
	 * @param WebElement
	 * @param WebDriver
	 * 
	 */
	public void afterChangeValueOf(WebElement element, WebDriver driver)
	{
		try
		{
			//commented by pavan s on 4th Oct
//		 test.log(Status.INFO, "afterChangeValueOf()"+ element.toString().split("-> ", 2)[1] + " value - " + element.getAttribute("value"));
		System.out.println("after send keys locator - [" + element.toString().split("-> ", 2)[1] + " value - "
				+ element.getAttribute("value"));
		//ATUReports.add("afterChangeValueOf() ", "Locator--" + element.toString(),element.getAttribute("value"), LogAs.INFO,new CaptureScreen(element));
		}
		catch(StaleElementReferenceException e)
		{
			test.log(Status.INFO, "afterChangeValueOf()"+ element.toString().split("-> ", 2)[1] );
			System.out.println("after send keys locator - [" + element.toString().split("-> ", 2)[1] );
		}
	}
	
	
}
