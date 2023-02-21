package com.salesforce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.base.BaseTest;

public class SFWebPage extends BaseTest{
	
	//page factory- object repository
	
	@FindBy(xpath="//span[contains(text(),'Login')]")WebElement login;
	
	@FindBy(xpath="//h4[contains(text(),'Salesforce')]")WebElement salesforce;
	
	public SFWebPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public void clicksfbtn() {
		login.click();
	}
	
	public Loginpage navigateSfLoginPage() {
		salesforce.click();
		return new Loginpage(driver);
	}
	
	

}
