package base;

import java.io.FileInputStream;
import java.util.Properties;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import util.ErrorUtil;
import util.Xls_Reader;

public class TestBase {

	public static Logger log=null;
	public static Properties prop=null;
	public static Xls_Reader suiteXls=null;
	public static Xls_Reader suiteShopXls=null;
	public static Xls_Reader suiteCartXls=null;
	public static Xls_Reader suiteProductDisplayXls=null;
	public static boolean isInitialized=false;
	public static WebDriver driver=null;
	
	public static void initialize() throws Exception{
		
		if(!isInitialized){
		//initialize log4j logging
		log=Logger.getLogger("devpinoyLogger");
		
		//initialize properties file
		prop=new Properties();
		FileInputStream ip1=new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\config.properties");
		prop.load(ip1);
		log.debug(prop.get("propFile"));
		
		
		//initialize xls files
		suiteXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\Suite.xlsx");
		suiteShopXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\Shop Suite.xlsx");
		suiteCartXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\Cart Suite.xlsx");
		suiteProductDisplayXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\Product Display Suite.xlsx");
	isInitialized=true;
		}
	}
	
	public static void openBrowser(){
		if(prop.getProperty("browserName").equalsIgnoreCase("mozilla")){
			ProfilesIni pr=new ProfilesIni();
			FirefoxProfile p=pr.getProfile("default");
			driver=new FirefoxDriver(p);
		}
		else if(prop.getProperty("browserName").equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver", "C:\\Users\\Rupjit\\Desktop\\Desktop\\LEARN\\selenium\\selenium-java-2.35.0\\IEDriverServer_x64_2.35.1\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		else if(prop.getProperty("browserName").equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rupjit\\Desktop\\Desktop\\LEARN\\selenium\\selenium-java-2.35.0\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
		}
	}
	
	public static void closeBrowser(){
		driver.quit();
	}
	
	public static boolean verifyTitle(String ExpectedValue){
		try{
		Assert.assertEquals(ExpectedValue, driver.getTitle());
		}catch(Throwable t){
			ErrorUtil.addVerificationFailure(t);
			log.debug("Title do not match");
			return false;
		}
		return true;
	}
}
