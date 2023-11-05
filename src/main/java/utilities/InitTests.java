package utilities;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.poi.util.SystemOutLogger;
import org.testng.asserts.SoftAssert;



/**
 * @author YugandharReddy
 */
public class InitTests {
	public static SoftAssert softAssert=new SoftAssert();

	public static Properties props = new Properties();
	
	/**
	 * @description:Initialization OS,version,browser and url details.
	 * 
	 * @throws Exception
	 */
	public static String OS_VERSION = "";
	public static String ENDPOINT_LOGIN = "";
	public static String CaptureScreenshotOnFail = "";
	public static String OS_NAME = "";
	public static String BROWSER_TYPE = "";
	public static String BASEURL = "";
	public static String FH_baseurl="";
	public static String USERNAME = "";
	public static String PASSWORD = "";
	public static String dir_path;
	public static String REST_URL_PURCHASE_JOBS = "";
	public static String PROJ_CONFIG_Path=""; 
	public static String TestCases = "";
	public static String SAUCE_USER = "";
	public static String TESTOBJ_APIKEY_ANDROID = "";
	public static String TESTOBJ_APIKEY_IOS = "";
	public static String BROWSER_VERSION = "";
	public static String PLATFORM = "";
	public static String SAUCE_USERNAME = "";
	public static String SAUCE_ACCESSKEY = "";
	public static String SAUCE_URL= "";
	public static String PARENT_TUNNEL = "";
	public static String TUNNEL_IDETIFIER = "";
	public static String RESOLUTION = "";
	public static String EXECUTION_ENV = "";
	public static String node_URL;
	public static String GCForce_ADMIN_USERNAME; 
	public static String GCForce_ADMIN_PASSWORD;
	public static String GCLogic_ADMIN_USERNAME;
	public static String GCLogic_ADMIN_PASSWORD;
	public static String COMPANY_SEARCH_TERM;
	public static String PRODUCT_SHORT_NAME;
	public static String PRODUCT_ICON_COLOR;
	public static String GCLogic_Username;
	public static String GCLogic_Pwd;
	public static String ADMIN_USERNAME;
	public static String GCLogic_BASEURL;
	public static String Clnt_USERNAME = "";
	public static String Clnt_PASSWORD = "";
	public static String Brok_USERNAME = "";
	public static String Brok_PASSWORD = "";
	
	
	public static String CLM_baseurl="";
	public static String CLM_Market_USERNAME="";
	public static String CLM_Market_PASSWORD="";
	public static String CLM_Broker_USERNAME="";
	public static String CLM_Broker_PASSWORD="";
	public static String CLM_clm_USERNAME="";
	public static String CLM_clm_PASSWORD="";
	
	public static String FH_Client_USERNAME="";
	public static String FH_Client_PASSWORD="";
	
	public static String FH_Broker_USERNAME="";
	public static String FH_Broker_PASSWORD="";
	
	public static List<String> ZipCodeForFremont;
	public static String DefaultRetention = "";
	public static float defaultFremontNPM;
	
	
	public static int waitTimeout ;

	
	
	
	public InitTests() {
		try {
			System.out.println("Getting config files..");

			System.out.println("Config read successfull");
			ClassLoader loader = this.getClass().getClassLoader();
			
			String CallingClass = getClass().getName();
			System.out.println("Calling CLass "+CallingClass);
			
			string rollno=randomc(21343);
			
			'rollno (3454)
			InputStream input = loader.getResourceAsStream("config/testdata.properties");
			props.load(input);
			
			
			String temp= System.getProperty("user.dir");
			
			dir_path=temp.replaceAll("\\\\", "/");
//			props.setProperty("userdir", dir_path);
			
			
//			dir_path = props.getProperty("userdir");
			System.out.println("dir path"+dir_path);
			
			PROJ_CONFIG_Path="//resources//config//Project_Config.properties";
			REST_URL_PURCHASE_JOBS=props.getProperty("removePurchaseJobsRestUrl");
			waitTimeout=Integer.parseInt(props.getProperty("explicitWaitInSec"));
			
			
			BASEURL = props.getProperty("baseurl")
			GCLogic_BASEURL = props.getProperty("GF_baseurl");
			USERNAME = props.getProperty("username");
			PASSWORD = props.getProperty("password");
			GCForce_ADMIN_USERNAME = props.getProperty("username_Admin");
			GCForce_ADMIN_PASSWORD = props.getProperty("PASSWORD_ADMIN");
			
			GCLogic_Username = props.getProperty("GL_username");
			GCLogic_Pwd = props.getProperty("GL_password");
			ADMIN_USERNAME = props.getProperty("username_Admin");
			PASSWORD = props.getProperty("password");
			
			FH_baseurl=props.getProperty("FH_baseurl");
			FH_Client_USERNAME=props.getProperty("FH_Clnt_username");
			FH_Client_PASSWORD=props.getProperty("FH_Clnt_password");
			FH_Broker_USERNAME=props.getProperty("FH_Brok_username");
			FH_Broker_PASSWORD=props.getProperty("FH_Brok_password");
			
			CLM_baseurl=props.getProperty("CLM_baseurl");
			CLM_Market_USERNAME=props.getProperty("clm_market_username");
			CLM_Market_PASSWORD=props.getProperty("clm_market_password");
			CLM_Broker_USERNAME=props.getProperty("CLM_Brok_username");
			CLM_Broker_PASSWORD=props.getProperty("CLM_Brok_password");
			CLM_clm_USERNAME=props.getProperty("CLM_clm_USERNAME");
			CLM_clm_PASSWORD=props.getProperty("CLM_clm_PASSWORD");
			
			
			
			BROWSER_TYPE = props.getProperty("browser");
			OS_VERSION = props.getProperty("os_version");
			OS_NAME = props.getProperty("os_name");
			CaptureScreenshotOnFail=props.getProperty("CaptureScreenshotOnFail");
			SAUCE_URL=props.getProperty("sauce_url");
			PARENT_TUNNEL = props.getProperty("parent_tunnel");
			TUNNEL_IDETIFIER = props.getProperty("tunnel_identifier");
			PLATFORM = props.getProperty("platform");
			RESOLUTION=props.getProperty("resolution");
			BROWSER_VERSION = props.getProperty("browser_version");
			EXECUTION_ENV = props.getProperty("execution_env");
			node_URL=props.getProperty("node_url");
			
			
			//FacHybrid specific
			String[] ZIPFremont = new String []{"30303","30305","30308","30309","30313","30326","33123","33125","33127","33129","33130","33131","33132","33135","33136","33137","33139","33140","33142","33146","77002","77003","77004","77006","77007","77009","77011","77019","77020","77023","77026","77030","92101","92102","92103","92140","02109","02110","02111","02113","02114","01116","02118","02127","02128","02129","02199","02210","19103","19104","19106","19107","19121","19122","19123","19125","19130","19133","19134","19135","19145","19146","19147","19148","89101","89102","89103","89104","89106","89107","89109","89118","89119","89120","89121","89146","94102","94103","94104","94105","94107","94108","94109","94110","94111","94114","94115","94117","94118","94123","94129","94133","94158","60601","60602","60603","60604","60605","60606","60607","60608","60609","60610","60611","60661","19102","90012","90013","90014","90015","90017","90021","90026","90031","90033","90039","90057","90065","90071"};
			ZipCodeForFremont = Arrays.asList(ZIPFremont);
			DefaultRetention = "6,000,000";
			defaultFremontNPM = (float) 250.0;
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		catch (Error ex) {
			ex.printStackTrace();
		}
	}

	public static String getPropValue(String key) {
		return props.getProperty(key);
	}
}
