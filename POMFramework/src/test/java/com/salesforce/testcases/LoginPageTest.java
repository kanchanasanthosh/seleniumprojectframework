package com.salesforce.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.Forgot_password;
import com.salesforce.pages.Homepage;
import com.salesforce.pages.Loginpage;
import com.salesforce.pages.SFWebPage;

public class LoginPageTest extends BaseTest {
	SFWebPage sfwebpage;
	Loginpage logpage;
	Homepage homepage;
	Forgot_password fpwd;
	
	public LoginPageTest() {
		super();
	}

	@Test
	public void LoginTest() throws InterruptedException {
		logger.info("inside the testcase method");
		extentObject.logTestInfo("inside the ex method");
		 sfwebpage = new SFWebPage(driver);
		 Thread.sleep(2000);
		logpage = new Loginpage(driver);
		Thread.sleep(4000);
		logpage.login(prop.getProperty("userid"),prop.getProperty("password"));
		Thread.sleep(4000);
		homepage= new Homepage(driver);
		String actual =homepage.validateHomePage();
		Assert.assertEquals(actual, "Salesforce - Essentials Edition");
		logger.info("Title validated"+actual);
		boolean value =homepage.validatelogo();
		Assert.assertTrue(value);
		logger.info("logo validated"+value);
		extentObject.logTestInfo("logo validated"+value);
	}

	@Test
	public void errorLoginTest() throws InterruptedException {
		logger.info("inside the testcase method");
		 sfwebpage = new SFWebPage(driver);
		 Thread.sleep(2000);
		logpage = new Loginpage(driver);
		Thread.sleep(4000);
		logpage.error_login(prop.getProperty("invalid.userid"),prop.getProperty("invalid.password"));
		Thread.sleep(1000);
		String actual =logpage.get_error_message();
		Thread.sleep(1000);
		String expected = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		Assert.assertEquals(actual, expected);
		logger.info("error message validated"+actual);
		logger.info(" errorlogin testcase passed");
	
	}
	@Test
	public void empty_pwdTest() throws InterruptedException {
		logger.info("inside the testcase method");
		 sfwebpage = new SFWebPage(driver);
		 Thread.sleep(2000);
		logpage = new Loginpage(driver);
		Thread.sleep(4000);
		logpage.error_login(prop.getProperty("userid"),prop.getProperty("password.empty"));
		Thread.sleep(1000);
		String actual =logpage.get_error_message();
		Thread.sleep(1000);
		String expected = "Please enter your password.";
		Assert.assertEquals(actual, expected);
		logger.info("error message validated"+actual);
		logger.info(" empty_pwdTest testcase passed");
	
	}
	@Test
	public void forgot_Password() throws InterruptedException {
		logger.info("inside the testcase method");
		 sfwebpage = new SFWebPage(driver);
		 Thread.sleep(2000);
		logpage = new Loginpage(driver);
		Thread.sleep(4000);
		logpage.forgotpassword();
		Thread.sleep(1000);
		fpwd=new Forgot_password(driver);
		fpwd.validateForgotPasswordPage();
		logger.info("forgot your password page displayed");
		
	}
	
	@Test
	public void rememberMe() throws InterruptedException {
		logger.info("inside the testcase method");
		sfwebpage = new SFWebPage(driver);
		 Thread.sleep(2000);
		logpage = new Loginpage(driver);
		Thread.sleep(4000);
		logpage.remember_Me(prop.getProperty("userid"),prop.getProperty("password"));
		System.out.println("entered username and password");
		Thread.sleep(1000);
		logger.info("checked rememberme");
		homepage= new Homepage(driver);
		Thread.sleep(4000);
		homepage.user_menu();
		homepage.select_Logout();
		logpage = new Loginpage(driver);
		String actual = logpage.validate_username_field();
		String expected = prop.getProperty("userid");
		Assert.assertEquals(actual, expected);
		logger.info("username field validated");
		
	}
	
	
	
}
