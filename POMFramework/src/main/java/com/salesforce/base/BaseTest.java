package com.salesforce.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.salesforce.config.SfConstants;
import com.salesforce.util.ExtentReportsDemo;
import com.salesforce.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public static WebDriver driver;
	public static Properties prop;
	protected Logger logger=null;
	 protected static ExtentReportsDemo extentObject = ExtentReportsDemo.getInstance();
	
	
	@BeforeTest
	public void setUpBeforeTest() {
		logger = LogManager.getLogger(BaseTest.class.getName());
		extentObject = new ExtentReportsDemo();
		extentObject .startExtentReport();
		//extentObject.logTestInfo("Testcase started beforeTest ");
		
	}
	
	
	@BeforeMethod
	public void setUp(Method method) {
		
		extentObject .startSingleTestReport("inside before method");
		logger.info("Testscript name = "+method.getName());
		GetDriverInstance();
		extentObject.logTestInfo("logged in success ");
		
		
	}
	
	public BaseTest()  {
		
		  prop = new Properties();
		try {
			FileInputStream fs = new FileInputStream(SfConstants .SF_DATA_PROPERTIES);
			prop.load(fs);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
public static WebDriver GetDriverInstance() {
	String browserName =prop.getProperty("browser");
		switch(browserName) {
		case "firefox": WebDriverManager.firefoxdriver().setup();
		 driver=new FirefoxDriver();
		driver.manage().window().maximize();
		break;
		
		case "chrome": WebDriverManager.chromedriver().setup();
		               driver=new ChromeDriver();
		               driver.manage().window().maximize();
	                   break;
	}	
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		return driver;
}
	
public String getScreenshottBase64(WebDriver driver) {
	String date =new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
	String curdir = SfConstants.USER_DIR;
	TakesScreenshot screenshot = (TakesScreenshot)driver;
	String img = screenshot.getScreenshotAs(OutputType.BASE64);
	return img;
	
	
}


@AfterMethod
public void teardown() {
	driver.quit();
}
	
@AfterTest
public void teardownAfterTest() {
	extentObject.endReport();
	extentObject.logTestInfo("Testcase completed AfterTest ");
}
	

}
