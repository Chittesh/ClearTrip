package org.clearTrip.com.TestBooking;

import static utils.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;
import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.testng.annotations.Test;

public class MockingGeolocation extends TestEnvironment {
	@Test(priority = 0, description = "verifyMockingGeolocation")
	public void verifyMockingGeolocation(Method method) throws InterruptedException {
		BasePage objBasePage = new BasePage(driver);
		startTest(method.getName(), "verifyMockingGeolocation");
		DevTools devTools = ((ChromeDriver) driver).getDevTools();
		devTools.createSession();
		devTools.send(Emulation.setGeolocationOverride(
				Optional.of(35.8235), 
				Optional.of(-78.8256), 
				Optional.of(100)));
		driver.get("https://mycurrentlocation.net/");
		objBasePage.wait(5);
	}
}
