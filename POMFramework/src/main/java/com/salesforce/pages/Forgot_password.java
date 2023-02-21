package com.salesforce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Forgot_password {

	public Forgot_password(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(id="header")WebElement forgotText;
	
	
	public boolean validateForgotPasswordPage() {
		
		return  forgotText.isDisplayed();
		
	}
	

}
