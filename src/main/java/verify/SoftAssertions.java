package verify;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

//import atu.testng.reports.//ATUReports;
//import atu.testng.reports.logging.LogAs;
//import atu.testng.selenium.reports.CaptureScreen;
//import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import utilities.InitTests;
import static utilities.InitTests.dir_path;
import static utilities.InitTests.softAssert;


/**
 * @author YugandharReddyGorrep
 *
 */
public class SoftAssertions
{
	// publpublic static SoftAssert softAssert;ic static SoftAssert softAssert;
	public static void verifyEquals(String actual,String expected,ExtentTest test)
	{
		
		if (actual.equals(expected))
		{
			test.log(Status.PASS, "verifyEquals() "+"actual " + actual + " expected " + expected );
		/*//ATUReports.add("verifyEquals() ", "Locator--" , expected, actual, LogAs.PASSED,
				new CaptureScreen(ScreenshotOf.valueOf(actual)));*/
		}
		else
		{
			test.log(Status.FAIL, "verifyEquals() "+"actual " + actual + " but " + " expected " + expected);
		/*//ATUReports.add("verifyEquals() ", "Locator--" , expected, actual, LogAs.FAILED,
				new CaptureScreen(ScreenshotOf.valueOf(actual)));*/
		}
	}
	
	public static void verifyEquals(float act, float exp, ExtentTest test) {
		// TODO Auto-generated method stub
		if (act==exp)
		{
			System.out.println("is correct");
			test.log(Status.PASS, "verifyEquals() "+"actual " + act + " expected " + exp );
			////ATUReports.add("verifyEquals() ", "Locator--" , new Integer(exp).toString(), new Integer(act).toString(), LogAs.PASSED,
					//new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		else
		{
			System.out.println("is incorrect");
			test.log(Status.FAIL, "verifyEquals() "+"actual " +act +" but " + " expected " + exp);
			////ATUReports.add("verifyEquals() ", "Locator--" , new Integer(exp).toString(), new Integer(act).toString(), LogAs.FAILED,
					//new CaptureScreen(ScreenshotOf.valueOf(new Integer(act).toString())));
		}
	}
	
	public static void verifyNotEquals(String actual,String expected,ExtentTest test)
	{
		
		if (!actual.equals(expected))
		{
			test.log(Status.PASS, "verifyNotEquals()--"+
					"actual " + actual + " expected " + expected );
			//ATUReports.add("verifyNotEquals() ", "Locator--" , expected, actual, LogAs.PASSED,
//					new CaptureScreen(ScreenshotOf.valueOf(actual)));
		}
		else
		{
			test.log(Status.FAIL, "verifyNotEquals()--"+"actual " + actual + "but" + " expected " + expected);
			//ATUReports.add("verifyNotEquals() ", "Locator--" , expected, actual, LogAs.FAILED,
//					new CaptureScreen(ScreenshotOf.valueOf(actual)));
			
		}
	}
	
	public static void verifyDisabled(WebElement element, String value,ExtentTest test)
	{
		if(element.isEnabled())
		{
			test.log(Status.FAIL,  value + "is enabled" + " but " + " expected " + value+"should be disbaled");
			/*//ATUReports.add("verifyEquals() ", "Locator--" , , actual, LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.valueOf(actual)));*/
		}
		else
		{
			test.log(Status.PASS,  value + "is disabled" + " but " + " expected " + value+"should be enabled");
		}
	}
	
	public static void verifyEnabled(WebElement element, String value,ExtentTest test)
	{
		if(element.isEnabled())
		{
			test.log(Status.PASS,  value + "field is enabled" + " but " + " expected " + value+"should be disbaled");
			/*//ATUReports.add("verifyEquals() ", "Locator--" , , actual, LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.valueOf(actual)));*/
		}
		else
		{
			test.log(Status.FAIL,  value + "field is disabled" + " but " + " expected " + value+"should be enabled");
		}
	}
	public static void verifyEquals(int act, int exp, ExtentTest test) {
		// TODO Auto-generated method stub
		if (act==exp)
		{
			test.log(Status.PASS, "verifyEquals() "+"actual " + act + " expected " + exp );
			//ATUReports.add("verifyEquals() ", "Locator--" , new Integer(exp).toString(), new Integer(act).toString(), LogAs.PASSED,
//					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		else
		{
			test.log(Status.FAIL, "verifyEquals() "+"actual " +act +" but " + " expected " + exp);
			//ATUReports.add("verifyEquals() ", "Locator--" , new Integer(exp).toString(), new Integer(act).toString(), LogAs.FAILED,
//					new CaptureScreen(ScreenshotOf.valueOf(new Integer(act).toString())));
		}
	}

	public static void verifyContains(String act, String exp, String message, ExtentTest test) {
		// TODO Auto-generated method stub
		if (act.contains(exp))
		{
			test.log(Status.PASS, "verifyEquals() "+"actual " + act + " expected " + exp );
			//ATUReports.add("verifyEquals() ", "Locator--" , exp, act, LogAs.PASSED,
//					new CaptureScreen(ScreenshotOf.DESKTOP));
		}
		else
		{
			test.log(Status.FAIL, "verifyEquals() "+"actual " +act +" but " + " expected " + exp);
			//ATUReports.add("verifyEquals() ", "Locator--" , exp, act, LogAs.FAILED,
//					new CaptureScreen(ScreenshotOf.DESKTOP));
		}
	}
	
	public static void verifyElementTextContains(WebElement e, String expected,ExtentTest test)
	{
		String actual;
		if (e.getText().isEmpty())
			actual = e.getAttribute("value").trim();
		else
			actual = e.getText().trim();
		if (actual.toUpperCase().contains(expected.trim().toUpperCase()))
		{
			test.log(Status.PASS, "verifyElementContains()"+ e.getAttribute("outerHTML") + " contains text as " + expected);
			//ATUReports.add("verifyElementTextContains() ", "Locator--" + e.toString(), expected, actual, LogAs.PASSED,
//					new CaptureScreen(e));
		} else
		{
			test.log(Status.FAIL, "verifyElementContains() "+e.getAttribute("outerHTML") + " has actual value " + actual + " but" + " expected " + expected);
			//ATUReports.add("verifyElementTextContains() ", "Locator--" + e.toString(), expected, actual, LogAs.FAILED,
//					new CaptureScreen(e));
		}
	}
	public static void verifyElementText(WebElement e, String expected,ExtentTest test)
	{
		String actual;
		if (e.getText().isEmpty())
			actual = e.getAttribute("value");
		else
			actual = e.getText();
		if (actual.equals(expected))
		{
			test.log(Status.PASS, "verifyElementText() "+
					"Webelement " + e.getAttribute("outerHTML") + " contains text as " + expected);
			//ATUReports.add("verifyElementText() ", "Locator--" + e.toString(), expected, actual, LogAs.PASSED,
//					new CaptureScreen(e));
		} else
		{
			test.log(Status.FAIL, "verifyElementText() "+
					e.getAttribute("outerHTML") + " has actual " + actual + " but" + " expected " + expected);
			//ATUReports.add("verifyElementText() ", "Locator--" + e.toString(), expected, actual, LogAs.FAILED,
//					new CaptureScreen(e));
		}
	}
	public static void verifyElementTextIgnoreCase(WebElement e, String expected,ExtentTest test)
	{
		String actual;
		if (e.getText().isEmpty())
			actual = e.getAttribute("value");
		else
			actual = e.getText();
		if (actual.toUpperCase().equals(expected.toUpperCase()))
		{
			test.log(Status.PASS, "verifyElementTextIgnoreCase() "+
					"Webelement " + e.getAttribute("outerHTML") + " contains text as " + expected);
			//ATUReports.add("verifyElementTextIgnoreCase() ", "Locator--" + e.toString(), expected, actual, LogAs.PASSED,
//					new CaptureScreen(e));
		} else
		{
			test.log(Status.FAIL, "verifyElementTextIgnoreCase() "+
					e.getAttribute("outerHTML") + " has actual " + actual + " but" + " expected " + expected);
			//ATUReports.add("verifyElementTextIgnoreCase() ", "Locator--" + e.toString(), expected, actual, LogAs.FAILED,
//					new CaptureScreen(e));
		}
	}
	public static void verifyElementHyperLink(WebElement e,ExtentTest test)
	{
		if (e.getTagName().contains("a"))
		{
			test.log(Status.PASS, "verifyElementHyperLink()"+
					"Webelement " + e.getAttribute("outerHTML") + " contains hyperlink");
		} else
		{
			test.log(Status.FAIL, "verifyElementHyperLink() "+ "Webelement " + e.getAttribute("outerHTML")
					+ " have actual " + e.getTagName() + " but " + " expected " + "a");
		}
	}
	public static void verifyElementContains(WebElement e, String expected, String message,ExtentTest test)
	{
		String actual;
		if (e.getText().isEmpty())
			actual = e.getAttribute("value");
		else
			actual = e.getText();
		if (actual.toUpperCase().contains(expected.toUpperCase()))
		{
			test.log(Status.PASS, "verifyElementContains() "+
					"Webelement " + e.getAttribute("outerHTML") + " contains text as " + expected + message);
			//ATUReports.add("verifyElementContains() ", "Locator--" + e.toString(), expected, actual, LogAs.PASSED,
//					new CaptureScreen(e));
		} else
		{
			test.log(Status.FAIL, "verifyElementContains() "+
					e.getAttribute("outerHTML") + "has actual " + actual + " but" + " expected " + expected);
			//ATUReports.add("verifyElementContains() ", "Locator--" + e.toString(), expected, actual, LogAs.FAILED,
//					new CaptureScreen(e));
		}
	}
	public static void verifyElementText(WebElement e, String expected, String message,ExtentTest test)
	{
		String actual;
		if (e.getText().isEmpty())
			actual = e.getAttribute("value");
		else
			actual = e.getText();
		if (actual.equals(expected))
		{
			test.log(Status.PASS, "verifyElementText() "+
					"Webelement " + e.getAttribute("outerHTML") + " contains text as " + expected + message);
			//ATUReports.add("verifyElementText() ", "Locator--" + e.toString(), expected, actual, LogAs.PASSED,
//					new CaptureScreen(e));
		} else
		{
			test.log(Status.FAIL, "verifyElementText() "+ "actual " + actual + "but" + " expected " + expected);
			//ATUReports.add("verifyElementText() ", "Locator--" + e.toString(), expected, actual, LogAs.FAILED,
//					new CaptureScreen(e));
		}
	}
	public static void verifyElementTextIgnoreCase(WebElement e, String expected, String message,ExtentTest test)
	{
		String actual;
		if (e.getText().isEmpty())
			actual = e.getAttribute("value");
		else
			actual = e.getText();
		if (actual.toUpperCase().equals(expected.toUpperCase()))
		{
			test.log(Status.PASS, "verifyElementTextIgnoreCase() "+
					"Webelement " + e.getAttribute("outerHTML") + " contains text as " + expected + message);
			//ATUReports.add("verifyElementTextIgnoreCase() ", "Locator--" + e.toString(), expected, actual, LogAs.PASSED,
//					new CaptureScreen(e));
		} else
		{
			test.log(Status.FAIL, "verifyElementTextIgnoreCase() "+
					"actual " + actual + "but" + " expected " + expected);
			//ATUReports.add("verifyElementTextIgnoreCase() ", "Locator--" + e.toString(), expected, actual, LogAs.FAILED,
//					new CaptureScreen(e));
		}
	}
	public static void verifyElementLink(WebElement e, String message,ExtentTest test)
	{
		if (e.getTagName().contains("a"))
		{
			test.log(Status.PASS, "verifyElementLink()"+
					"Webelement " + e.getAttribute("outerHTML") + " contains hyperlink" + message);
		} else
		{
			test.log(Status.FAIL, "verifyElementLink() "+"actual " + e.getTagName() + "but" + " expected " + "a");
		}
	}
	public static void assertTrue(boolean condition, String message,ExtentTest test)
	{
		if (condition == true)
		{
			test.log(Status.PASS, "assertTrue()"+message);
		} else
		{
			test.log(Status.FAIL, "assertTrue()"+ condition);
		}
	}
	public static void assertFalse(boolean condition, String message,ExtentTest test)
	{
		if (condition == false)
		{
			test.log(Status.PASS, "assertFalse()"+ message);
		} else
		{
			test.log(Status.FAIL, "assertFalse()");
		}
	}
	public static void assertNull(Object obj, String message,ExtentTest test)
	{
		if (obj == null)
		{
			test.log(Status.PASS, "assertNull()" + message);
		} else
		{
			test.log(Status.FAIL, "assertNull()");
		}
	}
	public static void assertNotNull(Object obj, String message,ExtentTest test)
	{
		if (obj != null)
		{
			test.log(Status.PASS, "assertNotNull()"+obj.toString() + message);
		} else
		{
			test.log(Status.FAIL, "assertNotNull()");
		}
	}
	public static void fail(Throwable e, String pathFrScreenshot,ExtentTest test) throws IOException
	{
		
		String excFile="/src/main/WebContent/extentReports/exception.txt";
		createExceptionFile(excFile,e);
		String content = new String(
				Files.readAllBytes(Paths.get(dir_path + excFile)));
		test.log(Status.FAIL, "fail()-Exception"+content);
		if (InitTests.CaptureScreenshotOnFail.equalsIgnoreCase("true"))
		{
			test.log(Status.INFO, "fail()-Exception"+test.addScreenCaptureFromPath(pathFrScreenshot));
		}
		softAssert.fail();
		
	}
	private static void createExceptionFile(String path,Throwable e) throws IOException
	{
		File f = new File(dir_path + path);
		if (!f.exists())
			f.createNewFile();
		PrintWriter p = new PrintWriter(dir_path + path);
		e.printStackTrace(p);
		p.close();
	}
}
