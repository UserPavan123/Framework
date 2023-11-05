package PageObj_GcForce;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




public class GCForce_CompanyPage 
{
	@FindBy(xpath="//span[text()='Guy Carpenter Lightning']")
	public WebElement DashBoard;
	
	@FindBy(xpath="//legend[text()='Select a record type']/parent::div/parent::fieldset/parent::div/parent::div/parent::div/parent::div/following-sibling::div//button/span[text()='Next']/parent::button")
	public WebElement ProvisionNewCompany_NextBtn;

	
	@FindBy(xpath="//h2[contains(text(),'New Company')]")
	public WebElement NewCompany_Window;
	
	
	@FindBy(xpath="//span[text()='Companies']/parent::a/parent::one-app-nav-bar-item-root")
	public WebElement CompanyOnly;
	
	@FindBy(xpath="//div[text()='New']/parent::a/parent::li")
	public WebElement ContactNew;
	
	@FindBy(xpath="//span[text()='Company Name']/parent::label/following-sibling::input")
	public WebElement CreateCompany_CompName;
	
	@FindBy(xpath="//span[text()='Company Level']/parent::span/following-sibling::div//a")
	public WebElement CreateCompany_CompLevel;
	
	@FindBy(xpath="//span[text()='Client Type']/parent::span/following-sibling::div//a")
	public WebElement CreateCompany_ClientLevel;
	
	@FindBy(xpath="//span[text()='Segment']/parent::span/following-sibling::div//a")
	public WebElement CreateCompany_Segment;
	
	@FindBy(xpath="//span[text()='Annual Revenue']/parent::label/following-sibling::input")
	public WebElement CreateCompany_AnnualRevenue;
	
	@FindBy(xpath="//span[text()='Total Potential Revenue (USD)']/parent::label/following-sibling::input")
	public WebElement CreateCompany_TotalPotential_Revenue;
	
	@FindBy(xpath="(//span[text()='Headquarter Street'])[2]/parent::label/following-sibling::textarea[normalize-space(@class)='textarea']")
	public WebElement CreateCompany_HeadQuarter_Street;
	
	@FindBy(xpath="(//span[text()='Headquarter City'])[2]/parent::label/following-sibling::input")
	public WebElement CreateCompany_HeadQuarter_City;
	
	@FindBy(xpath="//span[contains(text(),'Headquarter Zip/Postal Code')]/parent::label/following-sibling::input")
	public WebElement CreateCompany_HeadQuarter_PO_Code;
	
	@FindBy(xpath="//span[text()='Headquarter Country']/parent::span/following-sibling::div//a")
	public WebElement CreateCompany_HQ_Country;
	
	@FindBy(xpath="//input[@title='Search Companies']")
	public WebElement CompanyName;
	
	@FindBy(xpath="//span[text()='Save & New']/parent::button/following-sibling::button")
	public WebElement SaveContactBtn;
	
	@FindBy(xpath="//span[text()='Save']/parent::button/following-sibling::button")
	public WebElement OnlySaveContactBtn;
	
	
	
	
	
	public GCForce_CompanyPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
}