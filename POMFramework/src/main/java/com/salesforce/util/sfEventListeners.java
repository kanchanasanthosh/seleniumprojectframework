package com.salesforce.util;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.salesforce.base.BaseTest;




public class sfEventListeners implements ITestListener{
	protected static ExtentReportsDemo extentObject =null;
	 protected WebDriver driver;

	@Override
	public void onTestStart(ITestResult result) {
		extentObject.startSingleTestReport(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentObject.logTestPassed(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentObject.logTestFailed(result.getMethod().getMethodName());
		BaseTest ob = new BaseTest();
		driver =ob.GetDriverInstance();
		String path =ob.getScreenshottBase64(driver);
		extentObject.logTestFailedwithException(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getMethod().getMethodName());
	}

	@Override
	public void onStart(ITestContext context) {
		extentObject =ExtentReportsDemo.getInstance();
    	extentObject.startExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test completed successfully");
		extentObject.endReport();
	}

}
