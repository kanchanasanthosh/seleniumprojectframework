package com.salesforce.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.base.BaseTest;

public class Homepage  extends BaseTest {

	@FindBy(xpath="//img[@id='phHeaderLogoImage'] ")WebElement logodisplay;

	
	@FindBy(id="tryLexDialogX")WebElement promptwindow;
	@FindBy(id="userNav")WebElement usermenu;
	@FindBy(xpath="//a[@title='Logout']")WebElement logout;
	
	public Homepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomePage() {
		return driver.getTitle();
	}
	
	public boolean validatelogo() {
		return logodisplay.isDisplayed();
		
		}
	public void handlePrompt() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(promptwindow).build().perform();;
	    Thread.sleep(4000);
	}
	
	

	

	public void user_menu() throws InterruptedException {
		usermenu.click();
		Thread.sleep(2000);
		
	}
	public Loginpage select_Logout() throws InterruptedException {
		logout.click();
		Thread.sleep(2000);
		return new Loginpage(driver);
	}

}
