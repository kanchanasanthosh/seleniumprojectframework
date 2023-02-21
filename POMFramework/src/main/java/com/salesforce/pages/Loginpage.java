package com.salesforce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.base.BaseTest;

public class Loginpage  extends BaseTest {
	
	@FindBy(id="username")WebElement username;
	
	@FindBy(id="password")WebElement password;
	
	@FindBy(id="Login")WebElement loginbtn;
	
	@FindBy(id="error")WebElement errortext;
	@FindBy(id="forgot_password_link")WebElement forgotpwd;
	@FindBy(id="rememberUn")WebElement remember;
	
	public Loginpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public Homepage login(String un,String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginbtn.click();
		return new Homepage(driver);
	}
	
	public void error_login(String invalidun , String invalidpwd) {
		
		username.sendKeys(invalidun);
		password.sendKeys(invalidpwd);
		loginbtn.click();
	}
	
	public void error_empty_pwd(String un,String epwd ) {
		username.sendKeys(un);
		password.sendKeys(epwd);
		loginbtn.click();
		
	}
	
	
	public String get_error_message() {
		String  errormsg = errortext.getText();
		return errormsg;
		
	}
	
	
	
	
	public Forgot_password forgotpassword() {
		forgotpwd.click();
		return new Forgot_password(driver);
	}
	
	public Homepage remember_Me(String un,String pwd ) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		remember.click();
		loginbtn.click();
		return new Homepage(driver);
		
		
	}
	
	public String validate_username_field() {
		String usernamefield = username.getText();
		return usernamefield;
		
		
		
	}
	

}
