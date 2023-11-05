package PageObj_GcForce;

import static driverfactory.Driver.clickElement;
import static driverfactory.Driver.setInput;
import static utilities.InitTests.waitTimeout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GCForce_Login 
{
	@FindBy(xpath="//img[contains(@src,'OpenTwinsApp/javax.faces.resource/images/ajaxloadingbar.gif')]")
	public WebElement PleaseWait_LoadingBar;
	
	@FindBy(xpath="//img[@alt='User' and @title='User']/parent::span/parent::div")
	public WebElement GC_ForceIcon;
	
	@FindBy(xpath="//input[@id='username']")
	public static WebElement Login_Usr;
	
	@FindBy(xpath="//input[@id='password']")
	public static WebElement Login_Pwd;
	
	@FindBy(xpath="//*[@id='Login' and @value='Log In to Sandbox']")
	public static WebElement Login_Button;
	
	
	public GCForce_Login(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
public void login(String uname, String pwd) throws InterruptedException
{
//	waitForElementToDisplay(Login_Usr);
//	setInput(Login_Usr,"");
	Login_Usr.clear();
	
	setInput(Login_Usr,uname);
	
	
	//waitForElementToDisplay(Login_Pwd);
//	setInput(Login_Pwd,"");
	Login_Pwd.clear();
	
	setInput(Login_Pwd,pwd);
	clickElement(Login_Button);
}
}