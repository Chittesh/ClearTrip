package org.clearTrip.com.TestBooking;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class TestEnvironment {
	static WebDriver driver;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "runLocation", "browserName" })
	public synchronized void driverSetup(String runLocation, String browserName, ITestContext ctx)
			throws MalformedURLException {

		if (runLocation.contains("local") && browserName.contains("chrome")) {
			ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("test-type");
			options.addArguments("--disable-notifications");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
			driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
	}

	@AfterSuite(alwaysRun = true)
	public void driverClose() {
		driver.quit();
	}

}
