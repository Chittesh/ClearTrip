package org.clearTrip.com.TestBooking;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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

import org.openqa.selenium.devtools.DevTools;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class TestEnvironment {
	protected static WebDriver driver;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "runLocation", "browserName" })
	public synchronized void driverSetup(String runLocation, String browserName, ITestContext ctx)
			throws MalformedURLException {

		if (runLocation.contains("local") && browserName.contains("chrome")) {
			ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--incognito");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("test-type");
			options.addArguments("--disable-notifications");
			options.setAcceptInsecureCerts(true);
			// options.addArguments("--headless");

			// driver.manage().window().maximize();
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		} else if (runLocation.contains("docker")) {
			// To Get Name From testng xml file
			String testName = ctx.getCurrentXmlTest().getName();

			DesiredCapabilities capabilities = null;
			if (browserName.contains("chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.setAcceptInsecureCerts(true);
				ChromeDriver driver = new ChromeDriver(options);
			} else if (browserName.contains("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				options.setAcceptInsecureCerts(true);
				FirefoxDriver driver = new FirefoxDriver(options);
			}
			String host = "localhost";
			if (System.getProperty("HUB_HOST") != null) {
				host = System.getProperty("HUB_HOST");
			}
			URL objURL = new URL("http://" + host + ":4444/wd/hub");
			capabilities.setCapability("name", testName);
			driver = new RemoteWebDriver(objURL, capabilities);

		} else if (runLocation.contains("deviceMode")) {
			ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
			driver = new ChromeDriver();

			// Selenium 4 introduces the new ChromiumDriver class, which includes two
			// methods to access Chrome DevTools: getDevTools() and executeCdpCommand().

			// The getDevTools() method returns the new DevTools object which allows you to
			// send() the built-in Selenium commands for CDP. These commands are wrapper
			// methods that make it cleaner and easier to invoke CDP functions.
			DevTools devTools = ((ChromeDriver) driver).getDevTools();
			devTools.createSession();
			Map deviceMetrics = new HashMap() {
				{
					put("width", 600);
					put("height", 1000);
					put("mobile", true);
					put("deviceScaleFactor", 50);
				}
			};
			// The CDP command to modify the device’s metrics is
			// Emulation.setDeviceMetricsOverride, and this command requires input of width,
			// height, mobile, and deviceScaleFactor. These four keys are mandatory for this
			// scenario

			// The executeCdpCommand() method also allows you to execute CDP methods but in
			// a more raw sense. It does not use the wrapper APIs but instead allows you to
			// directly pass in a Chrome DevTools command and the parameters for that
			// command
			((ChromeDriver) driver).executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

		}
	}

	@AfterSuite(alwaysRun = true)
	public void driverClose() {
		driver.quit();
	}

}
