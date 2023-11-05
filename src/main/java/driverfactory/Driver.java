package driverfactory;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
/**
 * @author Yugandhar Reddy 
 *         Utility Class for WebDriver where all common libraries can be defined which can be used
 *         across the test cases.
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


import static utilities.InitTests.BROWSER_TYPE;
import static utilities.InitTests.dir_path;
import static utilities.MyExtentReports.setPlatformDetails;

import org.apache.commons.io.FileUtils;
/*import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;*/
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverLogLevel;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

//import atu.testng.reports.ATUReports;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import listeners.EventListner;
import utilities.InitTests;
import static utilities.InitTests.waitTimeout;
import static utilities.InitTests.node_URL;
public class Driver {
	private static final Logger logger = Logger.getLogger("selenium");
	public static String driverpath;
	public static WebDriverWait wait;
	public EventListner event;
	public static SoftAssert softAssert;
	public static int defaultTimeOut = 90;
	public static boolean AtuInitflag=false;
	/**
	 * @author Yugandhar Reddy
	 * 
	 * @description:Instantiate the webdriver based on the browser type and other params
	 * @param url
	 * @param browserType IE/chrome/FF/safari/edge
	 * @param browserVersion 
	 * @param platform windows/mac/linux
	 * @param executionEnv local/saucelabs/grid
	 * @param test

	 * @return WebDriver
	 * @throws Exception
	 */
	public  WebDriver initWebDriver(String url,String browserType,String browserVersion,String platform,String executionEnv,ExtentTest test,String nodeUrl) throws Exception {
		  WebDriver webdriver = null;
		  EventFiringWebDriver driver = null;
		  
		  if(platform.equals(""))
			platform=InitTests.PLATFORM;
		if(executionEnv.equals(""))
			executionEnv=InitTests.EXECUTION_ENV;
		if(browserVersion.equals(""))
			browserVersion=InitTests.BROWSER_VERSION;
		if(url.equals(""))
			url=InitTests.BASEURL;
		if(browserType.equals(""))
			browserType=InitTests.BROWSER_TYPE;
		System.out.println("Inside initWebDriver : Browser type is :" + browserType);
		browserType=browserType.toUpperCase();
		switch (browserType) {
		case "IE":
			try {
				if (InitTests.OS_VERSION.contains("64")) {
					driverpath =dir_path + "/src/main/resources/drivers/IEDriverServer.exe";
 
							
				} else {
					driverpath =dir_path + "/src/main/resources/drivers/IEDriverServer32.exe";

				}
			} catch (Exception e) {
				if (driverpath == null) {
					driverpath = "c:\\iedriver\\IEDriverServer.exe";
					File file = new File(driverpath);
					if (!file.exists())
						throw new FileNotFoundException(
								"IE Driver executable not found in resources and " + driverpath);
				}
			}
			System.out.println("\n\t" + "Platform->" + platform);
			System.out.println("\n\t" + "BROWSER_TYPE ->" + browserType);
			System.out.println("\n\t" + "BROWSER_VERSION ->" + browserVersion);
			System.out.println("\n\t" + "BASEURL ->" + url);
			System.out.println("driverpath =" + driverpath);
			//setExecPermsWin(driverpath);
			System.setProperty("webdriver.ie.driver", driverpath);
			InternetExplorerDriverService service = new InternetExplorerDriverService.Builder().usingAnyFreePort()
					.withLogFile(new File(dir_path+"/IE_Driver.log")).withLogLevel(InternetExplorerDriverLogLevel.TRACE).build();
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability("EnableNativeEvents", false);
			ieCapabilities.setCapability("ignoreZoomSetting", true);
			ieCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);

			 ieCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,"about:blank");
			ieCapabilities.setCapability(CapabilityType.BROWSER_NAME, true);

		if(executionEnv.contains("local"))
		{
			webdriver = new InternetExplorerDriver(service, ieCapabilities);
		}
		else if(executionEnv.contains("saucelabs"))
		{
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			cap.setBrowserName("internet explorer");
			cap.setCapability("platform", platform);
			cap.setCapability("name", browserType);
			cap.setCapability("screenResolution", "1920x1080");
			cap.setCapability("version", browserVersion);
			cap.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
			cap.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
			webdriver = new RemoteWebDriver(new URL(InitTests.SAUCE_URL), cap);
			System.out.println("in sauce"+webdriver);
		}
		else if(executionEnv.contains("grid"))
		{
			DesiredCapabilities gridCaps=DesiredCapabilities.internetExplorer();
			gridCaps.setCapability("ignoreZoomSetting", true);
			gridCaps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			gridCaps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			gridCaps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			gridCaps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			gridCaps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);

			 gridCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,"about:blank");
			if(platform.toLowerCase().contains("windows"))
			gridCaps.setPlatform(Platform.WINDOWS);
			if(nodeUrl.equals(""))
			webdriver=new RemoteWebDriver(new URL(node_URL), gridCaps);
			else
				webdriver=new RemoteWebDriver(new URL(nodeUrl), gridCaps);

			
		}
			if(AtuInitflag==false)
			{
//			ATUReports.setWebDriver(webdriver);
			AtuInitflag=true;

			}
			wait = new WebDriverWait(webdriver,waitTimeout);
			if (webdriver == null) {
				System.out.println("Failed to initialize IE webdriver in Utils.initWebDriver() ");
				throw new Exception("Failed to initialize IE webdriver in Utils.initWebDriver() ");
			}
			Capabilities cap = ((RemoteWebDriver) webdriver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();
			System.out.println(browserName);
			Platform os = cap.getPlatform();
			System.out.println(os);
			setPlatformDetails(browserType,platform,browserVersion,url);
			webdriver.manage().window().maximize();
			driver = new EventFiringWebDriver(webdriver);
			event = new EventListner(test);
			driver.register(event);
			driver.manage().timeouts().setScriptTimeout(defaultTimeOut, TimeUnit.SECONDS);
			waitForPageLoad(driver);
			driver.get(url);
			return driver;
	
		case "CHROME":
			System.out.println("cur dir"+System.getProperty("user.dir"));
			//DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		//	capabilities.setCapability(CapabilityType.BROWSER_NAME,browserType);

			if (platform.toLowerCase().contains("linux")) {
				if (InitTests.OS_VERSION.contains("32")) {
					driverpath = Driver.class.getClassLoader().getResource("drivers/chromedriver_linux32").getPath();
					System.out.println("Linux 32 bit chrome driver:" + driverpath);
				} else {
					driverpath = Driver.class.getClassLoader().getResource("drivers/chromedriver_linux64").getPath();
					System.out.println("Linux 64 bit chrome driver:" + driverpath);
				}
				
				
			} else if (platform.toLowerCase().contains("windows")) {
				driverpath = dir_path + "/src/main/resources/drivers/chromedriver.exe";
				System.out.println("Windows chrome driver:" + driverpath);
			} else if (platform.toLowerCase().contains("mac")) {
				driverpath = dir_path + "/src/main/resources/drivers/chromedriver.exe";

				//setExecPermsPosix(driverpath);
				System.out.println(driverpath);

			}
			try {
				System.out.println("\n\t" + "Platform->" + platform);
				System.out.println("\n\t" + "BROWSER_TYPE ->" + browserType);
				System.out.println("\n\t" + "BROWSER_VERSION ->" + browserVersion);
				System.out.println("\n\t" + "BASEURL ->" + url);
				System.out.println("\n\t" + "driverpath for windows->" + driverpath);
				System.setProperty("webdriver.chrome.driver", driverpath);
				if(executionEnv.contains("local"))
				{
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					options.setExperimentalOption("useAutomationExtension", false);
					webdriver = new ChromeDriver(options);
				}
				else if(executionEnv.contains("saucelabs"))
				{
					DesiredCapabilities caps = DesiredCapabilities.chrome();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					options.setExperimentalOption("useAutomationExtension", false);
					caps.setCapability(ChromeOptions.CAPABILITY, options);
					
					boolean b;
					int v;
					
					if (b=isNumeric(browserVersion))
					{v=Integer.parseInt(browserVersion);	
						caps.setCapability("version", v);}
					else
					{caps.setCapability("version", "beta");}
					caps.setCapability("platform", platform);
					caps.setCapability("name", browserType);
					caps.setBrowserName("chrome");
					caps.setCapability("screenResolution", "1920x1080");
					
					caps.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
					caps.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
					webdriver = new RemoteWebDriver(new URL(InitTests.SAUCE_URL), caps);
					System.out.println("in sauce"+webdriver);
				}
				else if(executionEnv.contains("CHROME_DEV"))
						{
							System.out.println("inside local chrome dev");
							
							Map<String, Object> mobileEmulation = new HashMap<>();
							mobileEmulation.put("deviceName", "iPhone 5");
//							mobileEmulation.put("deviceName", "iPad");
							ChromeOptions chromeOptions = new ChromeOptions();
							chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
							chromeOptions.addArguments("--disable-notifications");
							
							DesiredCapabilities capabilities = DesiredCapabilities.chrome();
							capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
							capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized", "--ignore-ssl-errors","--start-maximized","--Show-device-frame"));
							System.setProperty("webdriver.chrome.driver", driverpath);
							webdriver= new ChromeDriver(chromeOptions);
//							webdriver.get(url);
							
//							webdriver = new RemoteWebDriver(new URL(InitTests.SAUCE_URL), caps);
						}
				else if(executionEnv.contains("grid"))
				{
					DesiredCapabilities gridCaps=DesiredCapabilities.chrome();
					if(platform.toLowerCase().contains("windows"))
					gridCaps.setPlatform(Platform.WINDOWS);
					if(nodeUrl.equals(""))
						webdriver=new RemoteWebDriver(new URL(node_URL), gridCaps);
						else
							webdriver=new RemoteWebDriver(new URL(nodeUrl), gridCaps);					
				}
				
//				ATUReports.setWebDriver(webdriver);
				AtuInitflag=true;

		
				/*
				 * ChromeOptions options = new ChromeOptions();
				 * options.addArguments("disable-extensions");
				 * options.addArguments("test-type"); options.addArguments("start-maximized");
				 * options.addArguments("--js-flags=--expose-gc");
				 * options.addArguments("--enable-precise-memory-info");
				 * options.addArguments("--disable-popup-blocking");
				 * options.addArguments("--disable-default-apps");
				 * options.addArguments("test-type=browser");
				 * options.addArguments("disable-infobars"); webdriver = new
				 * ChromeDriver(options); if (webdriver == null) { System.out.
				 * println("Failed to initialize CHROME webdriver in Utils.initWebDriver() ");
				 * throw new
				 * Exception("Failed to initialize CHROME webdriver in Utils.initWebDriver() ");
				 * } cap = ((RemoteWebDriver) webdriver).getCapabilities(); browserName =
				 * cap.getBrowserName().toLowerCase(); System.out.println(browserName); os =
				 * cap.getPlatform(); System.out.println(os); version =
				 * cap.getVersion().toString(); System.out.println("Browser Name: " +
				 * cap.getBrowserName().toLowerCase() + " OS name: " +
				 * cap.getPlatform().toString() + " Browser version: " +
				 * cap.getVersion().toString()); //setPlatformDetails(browserName, os, version,
				 * InitTests.BASEURL);
				 * 
				 * 
				 */
				setPlatformDetails(browserType,platform,browserVersion,url);
				System.out.println("before max");
				webdriver.manage().window().maximize();

				wait = new WebDriverWait(webdriver,waitTimeout);
				driver = new EventFiringWebDriver(webdriver);

				EventListner event = new EventListner(test);
				driver.register(event);
				waitForPageLoad(driver);
				driver.manage().timeouts().setScriptTimeout(defaultTimeOut, TimeUnit.SECONDS);
				driver.get(url);
				System.out.println("after get");
				System.setProperty("webdriver.chrome.logfile",dir_path+"/"+"Chromedriver.log");
				//System.setProperty("webdriver.chrome.verboseLogging", "true");
			} catch (Exception e) {
				System.out.println("got exceptiona after init chrome ");
				e.printStackTrace();
			}
			return driver;


		case "FF":
			System.out.println("\n\t" + "Platform->" + platform);
			System.out.println("\n\t" + "BROWSER_TYPE ->" + browserType);
			System.out.println("\n\t" + "BROWSER_VERSION ->" + browserVersion);
			System.out.println("\n\t" + "BASEURL ->" + url);
			if (platform.toLowerCase().contains("linux")) {
			} else if (platform.toLowerCase().contains("windows")) {
			} else if (platform.toLowerCase().contains("mac")) {
			}
			logger.info("----- Firefox webdirver -----");
			System.out.println("Checking firefox ");
			if(executionEnv.contains("local"))
			{
				webdriver = new FirefoxDriver();
			}
			else if(executionEnv.contains("saucelabs"))
			{
				DesiredCapabilities caps = DesiredCapabilities.firefox();
				
				
				caps.setCapability("platform", platform);
				caps.setCapability("name", browserType);
				caps.setCapability("browserName", "firefox");
				caps.setCapability("screenResolution", "1920x1080");
				caps.setCapability("version", browserVersion);
				caps.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
				caps.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
				webdriver = new RemoteWebDriver(new URL(InitTests.SAUCE_URL), caps);
				System.out.println("in sauce"+webdriver);
			}
			else if(executionEnv.contains("grid"))
			{
				DesiredCapabilities gridCaps=DesiredCapabilities.chrome();
				gridCaps.setBrowserName(browserType);
				if(platform.toLowerCase().contains("windows"))
				gridCaps.setPlatform(Platform.WINDOWS);
				webdriver=new RemoteWebDriver(new URL(node_URL), gridCaps);
				
			}
			if(AtuInitflag==false)
			{
//			ATUReports.setWebDriver(webdriver);
			AtuInitflag=true;

			}
		
			setPlatformDetails(browserType,platform,browserVersion,url);
			wait = new WebDriverWait(webdriver,waitTimeout);

			if (webdriver == null) {
				System.out.println("Failed to initialize FF webdriver in Utils.initWebDriver() ");
				throw new Exception("Failed to initialize FF webdriver in Utils.initWebDriver() ");
			}
			@SuppressWarnings("unused") Capabilities ffCapabilities = ((RemoteWebDriver) webdriver).getCapabilities();
			// setPlatformDetails(ffCapabilities.getBrowserName(),
			// ffCapabilities.getPlatform(),
			// ffCapabilities.getVersion().toString(), url);
			webdriver.manage().window().maximize();
			driver = new EventFiringWebDriver(webdriver);
			event = new EventListner(test);
			driver.register(event);
			driver.get(url);
			waitForPageLoad(driver);
			return driver;
		case "SAFARI":
			System.out.println("\n\t" + "Platform->" + platform);
			System.out.println("\n\t" + "BROWSER_TYPE ->" + browserType);
			System.out.println("\n\t" + "BROWSER_VERSION ->" + browserVersion);
			System.out.println("\n\t" + "BASEURL ->" + url);
			DesiredCapabilities caps = DesiredCapabilities.safari();
			caps.setBrowserName("safari");
			caps.setCapability("platform", platform);
			caps.setCapability("name", browserType);
			caps.setCapability("screenResolution", "1920x1440");
			caps.setCapability("version", browserVersion);
			caps.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
			caps.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
			webdriver = new RemoteWebDriver(new URL(InitTests.SAUCE_URL), caps);
			if(AtuInitflag==false)
			{
//			ATUReports.setWebDriver(webdriver);
			AtuInitflag=true;

			}
			browserName = caps.getBrowserName().toLowerCase();
			System.out.println(browserName);
			setPlatformDetails(browserName,platform, caps.getVersion(),url);
			webdriver.manage().window().maximize();
			driver = new EventFiringWebDriver(webdriver);
			wait = new WebDriverWait(webdriver,waitTimeout);
			event = new EventListner(test);
			driver.register(event);
			driver.manage().timeouts().setScriptTimeout(defaultTimeOut, TimeUnit.SECONDS);
			driver.get(url);
			waitForPageLoad(driver);
			return driver;
		case "EDGE":
			System.out.println("\n\t" + "Platform->" + platform);
			System.out.println("\n\t" + "BROWSER_TYPE ->" + browserType);
			System.out.println("\n\t" + "BROWSER_VERSION ->" + browserVersion);
			System.out.println("\n\t" + "BASEURL ->" + url);
			 caps = DesiredCapabilities.edge();
			caps.setBrowserName("MicrosoftEdge");
			caps.setCapability("platform",platform);
			caps.setCapability("name", browserType);
			caps.setCapability("screenResolution", "1920x1080");
			caps.setCapability("version", browserVersion);
			caps.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
			caps.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
			webdriver = new RemoteWebDriver(new URL(InitTests.SAUCE_URL), caps);
			if(AtuInitflag==false)
			{
//			ATUReports.setWebDriver(webdriver);
			AtuInitflag=true;

			}
			browserName = caps.getBrowserName().toLowerCase();
			setPlatformDetails(browserName,platform, caps.getVersion(), url);
			System.out.println(browserName);
			/*os = caps.getPlatform();
			System.out.println(os);
			version = caps.getVersion().toString();
			logger.info("Browser Name: " + caps.getBrowserName().toLowerCase() + " OS name: "
					+ caps.getPlatform().toString() + " Browser version: " + caps.getVersion().toString());
			setPlatformDetails(browserName, os, version, InitTests.BASEURL);*/
			webdriver.manage().window().maximize();
			driver = new EventFiringWebDriver(webdriver);
			wait = new WebDriverWait(webdriver,waitTimeout);
			event = new EventListner(test);
			driver.register(event);
			driver.manage().timeouts().setScriptTimeout(defaultTimeOut, TimeUnit.SECONDS);
			driver.get(url);
			waitForPageLoad(driver);
			return driver;
		default:
			//return driver;

			//System.out.println("Not valid Browser");
			throw new RuntimeException("Browser type unsupported");
		}
	}

	/*public  WebDriver getDriver() {
		return driver;
	}
*/
	
	
	@SuppressWarnings("rawtypes")
	public EventFiringWebDriver initWebDriver_iMobile(String executionEnv,ExtentTest test) throws Exception {
	    IOSDriver iOS_driver= null;
	    EventFiringWebDriver IOSdriver_Event = null;
		  
		if(executionEnv.contains("saucelabs-IOS-SIMULATOR-NATIVE"))
		{
			System.out.println("in sauce"+iOS_driver);
			DesiredCapabilities caps = DesiredCapabilities.iphone();
			caps.setCapability("deviceName","iPhone 8 Simulator");
			caps.setCapability("deviceType","Phone");
			caps.setCapability("deviceOrientation", "portrait");
			caps.setCapability("browserName", "");
			caps.setCapability("platformVersion","11.0");
			caps.setCapability("platformName","iOS");
			caps.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
			caps.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
			caps.setCapability("app", "http://saucelabs.com/example_files/ContactManager.apk");
			caps.setCapability("appiumVersion", "1.8.1");
			caps.setCapability("name", "IPhone_Simulator_Test_NativeApp");
			iOS_driver= new IOSDriver(new URL(InitTests.SAUCE_URL),caps);
			
			
			if(AtuInitflag==false)
			{
//			ATUReports.setWebDriver(iOS_driver);
			AtuInitflag=true;
			}
			iOS_driver.manage().window().maximize();
			IOSdriver_Event = new EventFiringWebDriver(iOS_driver);
			wait = new WebDriverWait(iOS_driver,waitTimeout);
			event = new EventListner(test);
			IOSdriver_Event.register(event);
			IOSdriver_Event.manage().timeouts().setScriptTimeout(defaultTimeOut, TimeUnit.SECONDS);
			waitForPageLoad(IOSdriver_Event);
			return IOSdriver_Event;
		}
		else if(executionEnv.contains("saucelabs-IOS-SIMULATOR-WEB"))
		{
			System.out.println("in sauce"+iOS_driver);
			DesiredCapabilities caps = DesiredCapabilities.iphone();
			caps.setCapability("appiumVersion", "1.8.0");
			caps.setCapability("deviceName","iPhone 6 Simulator");
			caps.setCapability("deviceType","Phone");
			caps.setCapability("deviceOrientation", "portrait");
			caps.setCapability("browserName", "Safari");
			caps.setCapability("platformVersion","11.0");
			caps.setCapability("platformName","iOS");
			caps.setCapability("username", InitTests.SAUCE_USER);
			caps.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
			caps.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
			caps.setCapability("name", "IPhone_Simulator_Test_WebApp");
			caps.setCapability("app","");
			iOS_driver= new IOSDriver(new URL(InitTests.SAUCE_URL),caps);
			
			if(AtuInitflag==false)
			{
//			ATUReports.setWebDriver(iOS_driver);
			AtuInitflag=true;
			}
//			iOS_driver.manage().window().maximize();
			IOSdriver_Event = new EventFiringWebDriver(iOS_driver);
			wait = new WebDriverWait(iOS_driver,waitTimeout);
			event = new EventListner(test);
			IOSdriver_Event.register(event);
			IOSdriver_Event.manage().timeouts().setScriptTimeout(defaultTimeOut, TimeUnit.SECONDS);
			IOSdriver_Event.get(InitTests.BASEURL);
			waitForPageLoad(IOSdriver_Event);
			return IOSdriver_Event;
		}
		else if(executionEnv.contains("saucelabs-IOS_REAL_DEVICE-NATIVE"))
		{
			// to be provided with proper details
			DesiredCapabilities icaps = DesiredCapabilities.iphone();
			icaps.setCapability("platformName", "iOS");
			icaps.setCapability("platformVersion", "8.0.0");
			icaps.setCapability("platformVersion","12.1.4");
			icaps.setCapability("deviceType", "Phone");
			icaps.setCapability("deviceOrientation", "portrait");
			icaps.setCapability("phoneOnly", "true");
			icaps.setCapability("privateDevicesOnly", "false");
			icaps.setCapability("Screen size", "5.5");
			icaps.setCapability("browserName", "");
			// cap.setCapability("appiumVersion", "1.10.1");
			icaps.setCapability("deviceName","iPhone_7_Plus_real");
			icaps.setCapability("username", InitTests.SAUCE_USER);
			icaps.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
			icaps.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
			icaps.setCapability("testobject_app_id", "1");
			icaps.setCapability("testobject_api_key",InitTests.TESTOBJ_APIKEY_IOS);
			icaps.setCapability("accessKey",InitTests.SAUCE_ACCESSKEY);
			icaps.setCapability("name", "IphoneRealDevice_Test_NativeAppTesting");
			icaps.setCapability("testobject_suite_name", "MobileNativeAppTesting_Suite");
			icaps.setCapability("testobject_test_name ", "OnePlus_3T_RealDevice_test_NativeAppTesting");
			iOS_driver = new IOSDriver(new URL("https://us1.appium.testobject.com/wd/hub"), icaps);
//			
			IOSdriver_Event = new EventFiringWebDriver(iOS_driver);
			wait = new WebDriverWait(iOS_driver,waitTimeout);
			event = new EventListner(test);
			IOSdriver_Event.register(event);
			IOSdriver_Event.manage().timeouts().setScriptTimeout(defaultTimeOut, TimeUnit.SECONDS);
			waitForPageLoad(IOSdriver_Event);
			return IOSdriver_Event;
		}
		else if(executionEnv.contains("saucelabs-IOS_REAL_DEVICE-WEB"))
		{
			// to be provided with proper details
			DesiredCapabilities icaps = DesiredCapabilities.iphone();
			icaps.setCapability("platformName", "iOS");
			icaps.setCapability("platformVersion", "8.0.0");
			icaps.setCapability("platformVersion","12.1.4");
			icaps.setCapability("deviceType", "Phone");
			icaps.setCapability("deviceOrientation", "portrait");
			icaps.setCapability("phoneOnly", "true");
			icaps.setCapability("privateDevicesOnly", "false");
			icaps.setCapability("Screen size", "5.5");
			icaps.setCapability("browserName", "Chrome");
			// cap.setCapability("appiumVersion", "1.10.1");
			icaps.setCapability("deviceName","iPhone_7_Plus_real");
			icaps.setCapability("username", InitTests.SAUCE_USER);
			icaps.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
			icaps.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
			icaps.setCapability("testobject_app_id", "1");
			icaps.setCapability("testobject_api_key",InitTests.TESTOBJ_APIKEY_IOS);
			icaps.setCapability("accessKey",InitTests.SAUCE_ACCESSKEY);
			icaps.setCapability("name", "IphoneRealDevice_Test_WebAppTesting");
			icaps.setCapability("testobject_suite_name", "MobileNativeAppTesting_Suite");
			icaps.setCapability("testobject_test_name ", "OnePlus_3T_RealDevice_test_NativeAppTesting");
			icaps.setCapability("app", "");
			iOS_driver = new IOSDriver(new URL("https://us1.appium.testobject.com/wd/hub"), icaps);
			
			
			
//			
			IOSdriver_Event = new EventFiringWebDriver(iOS_driver);
			wait = new WebDriverWait(iOS_driver,waitTimeout);
			event = new EventListner(test);
			IOSdriver_Event.register(event);
			IOSdriver_Event.manage().timeouts().setScriptTimeout(defaultTimeOut, TimeUnit.SECONDS);
			IOSdriver_Event.get(InitTests.BASEURL);
			waitForPageLoad(IOSdriver_Event);
			return IOSdriver_Event;
		}
		return IOSdriver_Event;
		
	}

	
	@SuppressWarnings("rawtypes")
	public EventFiringWebDriver initWebDriver_Android_Mobile(String executionEnv,ExtentTest test) throws Exception {
	    AndroidDriver Android_Driver= null;
	    EventFiringWebDriver Android_driver_Event = null;
	    
			if(executionEnv.contains("saucelabs-ANDROID_REAL_DEVICE-NATIVE"))
			
			{
				DesiredCapabilities Android_caps = DesiredCapabilities.android();
				Android_caps.setCapability("deviceName","OnePlus_3_real_us");
				Android_caps.setCapability("username", InitTests.SAUCE_USER);
				Android_caps.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
				Android_caps.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
				Android_caps.setCapability("name", "AndroidEmulator_Test_WebApp");
				
				 // Set my TestObject API Key
				Android_caps.setCapability("testobject_api_key",InitTests.TESTOBJ_APIKEY_ANDROID);
				Android_caps.setCapability("accessKey", InitTests.SAUCE_ACCESSKEY);
//				Android_caps.setCapability("appiumVersion", "1.8.1");
//				cap.setCapability("appiumVersion", "1.10.1");
				Android_caps.setCapability("deviceType","Phone");
				Android_caps.setCapability("testobject_app_id", "1");
				Android_caps.setCapability("deviceOrientation", "portrait");
				Android_caps.setCapability("browserName", "Chrome");
				Android_caps.setCapability("Screen size", "5.5");
				Android_caps.setCapability("phoneOnly", "true");
				Android_caps.setCapability("platformName","Android");
				Android_caps.setCapability("platformVersion","8.0.0");
				Android_caps.setCapability("privateDevicesOnly", "true");
				Android_caps.setCapability("testobject_suite_name", "MobileNativeAppTesting_Suite");
				Android_caps.setCapability("testobject_test_name ", "OnePlus_3T_RealDevice_test_NativeAppTesting");
				Android_caps.setCapability("name", "RealDeviceTesting-OnePlus3_WebAppTesting");
				Android_Driver = new AndroidDriver(new URL("https://us1.appium.testobject.com/wd/hub"),Android_caps);
				Android_driver_Event = new EventFiringWebDriver(Android_Driver);
				wait = new WebDriverWait(Android_Driver,waitTimeout);
				event = new EventListner(test);
				Android_driver_Event.register(event);
				Android_driver_Event.manage().timeouts().setScriptTimeout(defaultTimeOut, TimeUnit.SECONDS);
				waitForPageLoad(Android_driver_Event);
				return Android_driver_Event;
			}
			else if(executionEnv.contains("saucelabs-ANDROID_REAL_DEVICE-WEB"))
			{
				DesiredCapabilities Android_caps = DesiredCapabilities.android();
				Android_caps.setCapability("deviceName","OnePlus_3_real_us");
				Android_caps.setCapability("username", InitTests.SAUCE_USER);
				Android_caps.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
				Android_caps.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
				Android_caps.setCapability("name", "AndroidEmulator_Test_WebApp");
				
				 // Set my TestObject API Key
				Android_caps.setCapability("testobject_api_key",InitTests.TESTOBJ_APIKEY_ANDROID);
				Android_caps.setCapability("accessKey", InitTests.SAUCE_ACCESSKEY);
//				Android_caps.setCapability("appiumVersion", "1.8.1");
				Android_caps.setCapability("deviceType","Phone");
				Android_caps.setCapability("deviceOrientation", "portrait");
				Android_caps.setCapability("browserName", "Chrome");
				Android_caps.setCapability("Screen size", "5.5");
				Android_caps.setCapability("phoneOnly", "true");
				Android_caps.setCapability("platformName","Android");
				Android_caps.setCapability("platformVersion","8.0.0");
				Android_caps.setCapability("privateDevicesOnly", "true");
				Android_caps.setCapability("app", "");
				Android_caps.setCapability("name", "RealDeviceTesting-OnePlus3_WebAppTesting");
				Android_Driver = new AndroidDriver(new URL("https://us1.appium.testobject.com/wd/hub"),Android_caps);
				Android_driver_Event = new EventFiringWebDriver(Android_Driver);
				wait = new WebDriverWait(Android_Driver,waitTimeout);
				event = new EventListner(test);
				Android_driver_Event.register(event);
				Android_driver_Event.manage().timeouts().setScriptTimeout(defaultTimeOut, TimeUnit.SECONDS);
				waitForPageLoad(Android_driver_Event);
				return Android_driver_Event;
			}
			else if(executionEnv.contains("saucelabs-ANDROID-EMULATOR-WEB"))
			{
				DesiredCapabilities caps = DesiredCapabilities.android();
				caps.setCapability("appiumVersion", "1.8.1");
				caps.setCapability("deviceName","Android Emulator");
				caps.setCapability("deviceType","Phone");
				caps.setCapability("deviceOrientation", "portrait");
				caps.setCapability("browserName", "Chrome");
				caps.setCapability("platformVersion","8.0");
				caps.setCapability("platformName","Android");
				caps.setCapability("username", "gvkannan");
				caps.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
				caps.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
				caps.setCapability("name", "AndroidEmulator_Test_WebApp");
				Android_Driver= new AndroidDriver(new URL(InitTests.SAUCE_URL), caps);
				
				Android_driver_Event = new EventFiringWebDriver(Android_Driver);
				wait = new WebDriverWait(Android_Driver,waitTimeout);
				event = new EventListner(test);
				Android_driver_Event.register(event);
				Android_driver_Event.manage().timeouts().setScriptTimeout(defaultTimeOut, TimeUnit.SECONDS);
				waitForPageLoad(Android_driver_Event);
				return Android_driver_Event;
			}
			else if(executionEnv.contains("saucelabs-ANDROID-EMULATOR-NATIVE"))
			{
			
				DesiredCapabilities cap = DesiredCapabilities.android();
				cap.setCapability("appiumVersion", "1.8.1");
				cap.setCapability("deviceName","Android Emulator");
				cap.setCapability("deviceType","Phone");
				cap.setCapability("deviceOrientation", "portrait");
				cap.setCapability("browserName", "");
				cap.setCapability("platformVersion","8.0");
				cap.setCapability("platformName","Android");
				cap.setCapability("parentTunnel", InitTests.PARENT_TUNNEL);
				cap.setCapability("tunnel-identifier", InitTests.TUNNEL_IDETIFIER);
				cap.setCapability("app", "http://saucelabs.com/example_files/ContactManager.apk");
				cap.setCapability("name", "AndroidEmulator_Test_NativeApp");
				
				Android_Driver= new AndroidDriver(new URL(InitTests.SAUCE_URL), cap);
				Android_driver_Event = new EventFiringWebDriver(Android_Driver);
				wait = new WebDriverWait(Android_Driver,waitTimeout);
				event = new EventListner(test);
				Android_driver_Event.register(event);
				Android_driver_Event.manage().timeouts().setScriptTimeout(defaultTimeOut, TimeUnit.SECONDS);
				waitForPageLoad(Android_driver_Event);
				return Android_driver_Event;
				
			}
			
			return Android_driver_Event;
			
		
	}
	
	
	/**
	 * Delete cookies 
	 * 
	 */
	public  void deleteCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}

	
	/**
	 * Kill  Driver.exe
	 */
	public static void killBrowserExe(String browser) {
		String browserExe="";
		if(browser.equalsIgnoreCase("chrome"))
	 browserExe="chromedriver.exe";
		if(browser.equalsIgnoreCase("IE"))
			 browserExe="IEDriverServer.exe";
		if(browser.equalsIgnoreCase("FF"))
			 browserExe="firefox.exe";
		try {
			System.out.println("in kill" +browserExe);

			Runtime rt = Runtime.getRuntime();
				rt.exec("taskkill /f /t /im "+browserExe);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * Close All Browsers
	 *//*
	public  void closeBrowser(String brType) {
		logger.info("----- closeBrowser() : Closing the currently opened browser and killing driver " + brType);
		System.out.println("----- closeBrowser() : Closing the currently opened browser and killing driver " + brType);
		if (brType.toLowerCase().contains("ie")) {
			if (getDriver() != null) {
				getDriver().manage().deleteAllCookies();
				getDriver().quit();
			}
			
			deleteCookies();
			//delay(2000);
		} else if (brType.toLowerCase().contains("chrome")) {
			if (getDriver() != null) {
				getDriver().quit();
			}
			//killChromeDriver();
			delay(2000);
		} else if (brType.toLowerCase().contains("ff")) {
			// code for FF
			System.out.println("close broser: kill ff browser");
			if (getDriver() != null) {
				getDriver().quit();
			}
			//killFireFoxDriver();
			//delay(2000);
		}
	}*/

	public static void delay(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public  static void implicitWait(WebDriver driver,long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	//All web element methods
	
	

	/**
	 * @description: WebDriver Wait for element to display by locator
	 * @param WebElement
	 */
	public static void waitForElementToEnable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * @description: WebDriver Wait for element to display by locator
	 * @param WebElement
	 */
	public static void waitForElementToDisplay(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * @description: WebDriver Wait for element to display by locator
	 * @param by
	 * @param secs
	 */
	/**
	 * @description: WebDriver Wait for element to display by locator
	 * @param by
	 */
	public static void waitForElementToDisplay(By by) {
		try {
			System.out.println("in wait for");

			System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(by)));
		} catch (Exception e) {
			System.out.println("in wait for catch");
			e.printStackTrace();
		}
	}
	
	
	public static void waitForElementToDisplayAndEnable(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * @description: WebDriver Wait for element to display by locator
	 * @param by
	 * @param secs
	 */
	public  void waitForElementToDisplay(WebDriver driver,By by, int secs) {
		wait = new WebDriverWait(driver, secs);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * @description : Waits for the page to load for about 30 seconds.
	 */
	public static void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, defaultTimeOut);
		try {
			wait.until(expectation);
		} catch (Throwable error) {
			logger.error(" Page load is timing out :" + error.getMessage());
			Assert.assertFalse(false, "Timeout waiting for Page Load Request to complete.");
		}
	}


	public  void switchToFrame(WebDriver driver,WebElement frameLocator) {
		driver.switchTo().frame(frameLocator);
		
		System.out.println("navigated inside frame " + driver.getTitle());
	}

	public  void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
		
		System.out.println("navigated to default content " + driver.getTitle());
	}

	public  void navigateBack(WebDriver driver) {
		driver.navigate().back();
	}
	public  boolean isElementExisting(WebDriver driver,WebElement we, int time) {
		try {
			implicitWait(driver,time);
			// Utils.getDriver().findElement(By.xpath(we.getText()));
			if (we.isDisplayed())
				return true;
			else
				return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	/**
	 * @throws InterruptedException
	 * @description:Sets the input text after the element is displayed
	 */
	public static void setInput(WebElement element, String value)
	{
		if (BROWSER_TYPE.equalsIgnoreCase("IE"))
		{
			System.out.println("came in ie block input");
			waitForElementToDisplay(element);
			waitForElementToEnable(element);
			element.click(); // This below line
			//new Actions(driver).moveToElement(element).perform();
			element.clear();
			if (value != null)
			{
				element.sendKeys(value);
			}
		} else
		{
			/*try
			{
				Thread.sleep(1000);
			} catch (Exception e)
			{
				e.printStackTrace();
			}*/
			element.clear();
		//clickElementUsingJavaScript(element);
			//element.click();
			if (value != null)
			{
				element.sendKeys(value);
			}
		}
	}
	/**
	 * @description:Clicks on a web element after element is displayed
	 */
	public  static void clickElement(WebElement element)
	{
		waitForElementToDisplay(element);
		waitForElementToEnable(element);
		element.click();
	}
	/**
	 * @description:Double clicks on an element
	 */
	public  void doubleClickElement(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick(element).build().perform();
	}
	/**
	 * @description:Clicks on a web element using Java Script Executor after
	 *                     element is displayed
	 */
	public  static void clickElementUsingJavaScript(WebDriver driver,WebElement element)
	{
		@SuppressWarnings("unused")
		String attribute = element.getAttribute("outerHTML");
		waitForElementToEnable(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		//test.log(LogStatus.INFO, "clickElementUsingJavaScript() ", "clicked on " + attribute);
	}
	/**
	 * @description: Poll until text display
	 * @param element
	 */
	public  void pollUntilTextDisplay(WebElement element)
	{
		for (int elementDispalyCount = 0; elementDispalyCount < 100; elementDispalyCount++)
		{
			try
			{
				if (element.getText() != null)
				{
					break;
				}
			} catch (Exception e)
			{
				refreshpage();
				try
				{
					Thread.sleep(1000);
				} catch (InterruptedException interuptException)
				{
				}
			}
		}
	}
	
	public static void Sel_dropdown_values(WebDriver driver, String value) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String xpath = "//mat-option[@role='option']/span[normalize-space(text())='"+value+"']";
		wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
		//waitForElementToDisplay(drp_values); 
		WebElement drp_values = driver.findElement(By.xpath(xpath));
		drp_values.click();
		
	}
	
	
	
	/**
	 * @description: refresh the checkout page with robo script
	 */
	public static void refreshpage()
	{
		try
		{
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_F5);
			robot.keyRelease(KeyEvent.VK_F5);
		} catch (AWTException e)
		{
			logger.info("AWTException:Page will not be refereshed");
		}
		// Need time to reload the page
		try
		{
			Thread.sleep(3000);
		} catch (InterruptedException e)
		{
			logger.info("InterruptedException:Wait will not applied");
		}
	}
	/**
	 * @description: refresh the page with Webdriver
	 */
	public  void refreshPageWithWebdriver(WebDriver driver)
	{
		driver.navigate().refresh();
	}
	
	/**
	 * @description:Hovers over parent element and clicks on child element
	 */
	public  void hoverAndClickOnElement(WebDriver driver,WebElement parentElement, WebElement childElement)
	{
		Actions action = new Actions(driver);
		action.moveToElement(parentElement).build().perform();
		delay(2000);
		childElement.click();
	}
	
	public static  void ScrollToElement(WebDriver driver,WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);;
	}
	
	
	public static void waitForElementToClickable(WebElement element) {
		//wait.until(ExpectedConditions.elementToBeClickable(by));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	
	/**
	 * @description:Hovers over parent element
	 *//*
	public static void hoverOverElement(WebElement element)
	{
		Actions action = new Actions(driver);
		action = action.moveToElement(element);
		action.build().perform();
		delay(2000);
	}
	
	
	*//**
	 * @description:Fetches link of a web element using Java Script Executor
	 *                      after element is displayed
	 *//*
	public static String getHrefFromWebElementUsingJavaScript(WebElement element)
	{
		waitForElementToDisplay(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return executor.executeScript("return arguments[0].getAttribute(\"href\")", element).toString();
	}
	
	*//**
	 * @description:Fetches link of a web element using Java Script Executor
	 *                      after element is displayed
	 *//*
	public static String getTextFromWebElementUsingJavaScript(WebElement element)
	{
		waitForElementToDisplay(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return executor.executeScript("return arguments[0].text", element).toString();
	}
	*//**
	 * @description : Verifies if the checkbox is already selected.
	 * @param driver
	 * @param checkboxpath
	 * @return
	 *//*
	public static boolean verifyCheckBox(WebElement checkBox)
	{
		boolean isChecked = false;
		try
		{
			isChecked = checkBox.isSelected();
		} catch (Exception e)
		{
			logger.info(e.getMessage());
		}
		return isChecked;
	}
	*//**
	 * @description:Fetches link of a web element using Java Script Executor
	 *                      after element is displayed
	 *//*
	public static String getIdFromWebElementUsingJavaScript(WebElement element)
	{
		waitForElementToDisplay(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return executor.executeScript("return arguments[0].id", element).toString();
	}
	*//**
	 * @author khaderkhan
	 * @description: Equivalent to sendkeys in special cases
	 *//*
	public static void setValueUsingJavaScript(WebElement element, String value)
	{
		waitForElementToDisplay(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(
				"document.getElementById('" + element.getAttribute("id") + "').innerHTML = '" + value + "'", element);
	}
	
	public static WebElement getElementWithLinkText(String locatorText)
	{
		By loc = By.partialLinkText(locatorText);
		waitForElementToDisplay(loc);
		return driver.findElement(loc);
	}
	public static WebElement getElementWithPartialLinkText(String locatorText)
	{
		By loc = By.partialLinkText(locatorText);
		waitForElementToDisplay(loc);
		return driver.findElement(loc);
	}
	public static WebElement getElementWithTitle(String tagName, String tagHasTitle)
	{
		return driver.findElement(By.cssSelector(tagName + "[title='" + tagHasTitle + "']"));
	}
	public static void selEleByVisbleText(WebElement selectDropDown, String visbleText)
	{
		Select sel = new Select(selectDropDown);
		sel.selectByVisibleText(visbleText);
		
	}
	*//**
	 * @param table
	 *//*
	public static List<String> getTableData(WebElement tableLoc)
	{
		List<String> myTabdata = new ArrayList<String>();
		waitForElementToDisplay(tableLoc);
		List<WebElement> trs = tableLoc.findElements(By.cssSelector("tr:not([class*='ng-hide'])"));
		for (WebElement tr : trs)
		{
			List<WebElement> tds = tr.findElements(By.tagName("td"));
			for (WebElement td : tds)
			{
				myTabdata.add(td.getText());
			}
		}
		return myTabdata;
	}
	*//**
	 * @param table,row
	 *            and col index
	 *//*
	public static WebElement getTableColumn(WebElement rowLoc, int colIndex)
	{
		waitForElementToDisplay(rowLoc);
		List<WebElement> tds = rowLoc.findElements(By.tagName("td"));
		return tds.get(colIndex);
	}
	*//**
	 * @param table
	 *//*
	public static int getNoOfRowsInTable(WebElement tableLoc)
	{
		waitForElementToDisplay(tableLoc);
		List<WebElement> trs = tableLoc.findElements(By.cssSelector("tbody>tr:not([class*='ng-hide'])"));
		System.out.println("rows" + trs.size());
		return trs.size();
	}
	*//**
	 * @param table
	 *//*
	public static int getNoOfColInRow(WebElement rowLoc)
	{
		waitForElementToDisplay(rowLoc);
		List<WebElement> tds = rowLoc.findElements(By.tagName("td"));
		return tds.size();
	}
	*//**
	 * @param table
	 *//*
	public static int getSizeOfTable(WebElement tableLoc)
	{
		int c = 0;
		waitForElementToDisplay(tableLoc);
		List<WebElement> trs = tableLoc.findElements(By.cssSelector("tbody>tr:not([class*='ng-hide'])"));
		for (WebElement tr : trs)
		{
			List<WebElement> tds = tr.findElements(By.tagName("td"));
			for (WebElement td : tds)
			{
				c++;
			}
		}
		return c;
	}
	*//**
	 * @param table,row
	 *            and col index
	 *//*
	public static WebElement getTableColumnByIndex(WebElement tableLoc, int rowIndex, int colIndex)
	{
		waitForElementToDisplay(tableLoc);
		List<WebElement> trs = tableLoc.findElements(By.cssSelector("tbody>tr:not([class*='ng-hide']"));
		WebElement row = trs.get(rowIndex);
		List<WebElement> tds = row.findElements(By.tagName("td"));
		return tds.get(colIndex);
	}
	*//**
	 * Scrolls to web element specified
	 *
	 * @param driver
	 * @param element
	 * @throws InterruptedException
	 *//*
	public static void scrollToElement(final WebElement element) throws InterruptedException
	{
		Thread.sleep(4000); // we cannot use explicit wait here
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	*//**
	 * @param table
	 *//*
	public static int getWebElementCount(List<WebElement> element)
	{
		
		return element.size();
	}
	public static List<String> getAllValuesFromDrop(Select drop)
	{
		List<String> values = new ArrayList<String>();
		for (WebElement el : drop.getOptions())
		{
			values.add(el.getText());
		}
		return values;
	}
	

	*//**
	 * @description : Gets the value of "font-family" attribute of a web element
	 *              .
	 * @param webElement
	 * @return : font styles of the web element.
	 *//*
	public static String getFontStyle(WebElement webElement)
	{
		return webElement.getCssValue("font-family");
	}
	*//**
	 * @description : Gets the page source of the current page.
	 * @return : page source as string.
	 *//*
	public static String getPageSource()
	{
		return driver.getPageSource();
	}
	*//**
	 * @description : Gets the value of "font-size" attribute of a web element .
	 * @param webElement
	 * @return : font size of the web element.
	 *//*
	public static String getFontSize(WebElement webElement)
	{
		return webElement.getCssValue("font-size");
	}
	*//**
	 * @description : Gets the value of "font-weight" attribute of a web element
	 *              .
	 * @param webElement
	 * @return : String,font weight of the web element.
	 *//*
	public static String getFontWeight(WebElement webElement)
	{
		return webElement.getCssValue("font-weight");
	}
	*//**
	 * @param Path
	 *            - File to make it executable
	 * @throws IOException
	 *//*
	public static void setExecPermsWin(String Path) throws Exception {
		File file = new File(Path);
		System.out.println("IE Driver path: " + Path);
		System.out.println("Before set - Is Execute Permission set : " + file.canExecute());
		if (file.exists()) {
			System.out.println("Is Execute Permission set : " + file.canExecute());
			file.setExecutable(true, false);
			file.setReadable(true, false);
			file.setWritable(true, false);
		}
		System.out.println("After - Is Execute allow : " + file.canExecute());
		System.out.println("Afetr - Is Write allow : " + file.canWrite());
		System.out.println("After - Is Read allow : " + file.canRead());
	}

	*//**
	 * @param Path
	 *            - File to make it executable
	 * @throws IOException
	 *//*
	public static void setExecPermsPosix(String Path) throws IOException {
		File file = new File(Path);
		
		 * if(file.exists()){
		 * System.out.println("Before setting perms - Execute allowed : " +
		 * file.canExecute()); file.setExecutable(true, false); file.setReadable(true,
		 * false); file.setWritable(true, false); }
		 
		
		 * System.out.println("Is Execute allow : " + file.canExecute());
		 * System.out.println("Is Write allow : " + file.canWrite());
		 * System.out.println("Is Read allow : " + file.canRead());
		 
		// using PosixFilePermission to set file permissions 777
		Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
		// add owners permission
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE);
		perms.add(PosixFilePermission.OWNER_EXECUTE);
		// add group permissions
		perms.add(PosixFilePermission.GROUP_READ);
		perms.add(PosixFilePermission.GROUP_WRITE);
		perms.add(PosixFilePermission.GROUP_EXECUTE);
		// add others permissions
		perms.add(PosixFilePermission.OTHERS_READ);
		perms.add(PosixFilePermission.OTHERS_WRITE);
		perms.add(PosixFilePermission.OTHERS_EXECUTE);
		Files.setPosixFilePermissions(Paths.get(Path), perms);
	}

	*/
	
	public static  String getScreenPath(WebDriver driver,String testScriptName) throws IOException
	{
		String timeStamp = getCurrTimeStamp();
		File ff = new File(dir_path + "/Screens");
		if (!ff.exists())
		{
			ff.mkdir();
		}
		String dir = null;
		if (InitTests.CaptureScreenshotOnFail.equalsIgnoreCase("true"))
		{
			 dir= dir_path+"/Screens/" + testScriptName + "_" + timeStamp + ".png";
			System.out.println("about to screnn"+dir);
			// TODO Auto-generated method stub
			File f = ((TakesScreenshot) (driver)).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(f,
					new File(dir + testScriptName + "_" + timeStamp + ".png"));
			return  dir + testScriptName + "_" + timeStamp + ".png";
		} else
		{
			return  dir + testScriptName + "_" + timeStamp + ".png";
		}
	}
	/**
	 * @description:Returns the current time stamp
	 * 
	 * 
	 * @return String
	 */
	public static String getCurrTimeStamp()
	{
		LocalDate.now();
		Locale locale = Locale.getDefault();
		TimeZone tz = TimeZone.getDefault();
		Calendar cal = Calendar.getInstance(tz, locale);
		Date d = new Date(System.currentTimeMillis());
		cal.setTime(d);
		int m = cal.get(Calendar.MONTH) + 1;
		int h = cal.get(Calendar.HOUR);
		int mm = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);
		String timeStamp = cal.get(Calendar.DAY_OF_MONTH) + "_" + m + "_" + cal.get(Calendar.YEAR) + "_" + h + "hh_"
				+ mm + "mm_" + s + "ss";
		return timeStamp;
	}

	public static void ScrolltoElementusingJavaScript(WebDriver chromeDriver, WebElement btn_Bind) {
		// TODO Auto-generated method stub
		
	}
	
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	
	/**
	 * Method to send email with attachment
	 * 
	 * @param filename
	 *            filename is HTML report created
	 * @throws MessagingException
	 */
	/*
	 * @SuppressWarnings("unused") public static void sendEmail(String filename)
	 * throws MessagingException { String host = "localhost"; String from =
	 * ".com"; // String to = ""; String to = ".com";
	 * // Get system properties Properties props = System.getProperties(); // Setup
	 * mail server // props.put("smtp.xxxx.com", host);
	 * //MSPMSGCCR000.corp.fairisaac.com // props.put(host, "smtp.xxxx.com");
	 * props.put("mail.smtp.host", "smtp.xxxx.com"); props.put("mail.smtp.port",
	 * "25"); // Get session Session session = Session.getDefaultInstance(props); //
	 * Define message MimeMessage message = new MimeMessage(session);
	 * message.setFrom(new InternetAddress(from));
	 * message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	 * message.setSubject("OM45 Automation Report"); // Create the message part
	 * BodyPart messageBodyPart = new MimeBodyPart(); // Fill the message
	 * messageBodyPart.
	 * setText("Hi, This is automated email, please do not reply. kindly check the attached report"
	 * ); Multipart multipart = new MimeMultipart();
	 * multipart.addBodyPart(messageBodyPart); // Part two is attachment //
	 * messageBodyPart = new MimeBodyPart(); // DataSource source = new
	 * FileDataSource(filename); // messageBodyPart.setDataHandler(new
	 * DataHandler(source)); // messageBodyPart.setFileName(filename); //
	 * multipart.addBodyPart(messageBodyPart); // Put parts in message
	 * message.setContent(multipart); // Send message Transport.send(message); }
	 */
}
