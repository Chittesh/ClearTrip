package utils.listeners;

import static utils.extentreports.ExtentTestManager.getTest;

import com.aventstack.extentreports.Status;
import java.util.Objects;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.extentreports.ExtentManager;
import utils.logs.Log_1;


public class TestListener  implements ITestListener {
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}



	@Override
	public void onFinish(ITestContext iTestContext) {
		Log_1.info("I am in onFinish method " + iTestContext.getName());
		// Do tier down operations for ExtentReports reporting!
		ExtentManager.extentReports.flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		Log_1.info(getTestMethodName(iTestResult) + " test is starting.");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		Log_1.info(getTestMethodName(iTestResult) + " test is succeed.");
		// ExtentReports Log_1 operation for passed tests.
		//getTest().Log_1(Status.PASS, "Test passed");
	}

	

	

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		Log_1.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
}