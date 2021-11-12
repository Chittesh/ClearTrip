package org.clearTrip.com.TestBooking;

import static utils.extentreports.ExtentTestManager.startTest;
import java.lang.reflect.Method;
import java.util.Optional;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.*;
import org.openqa.selenium.devtools.v85.network.model.ConnectionType;
import org.testng.annotations.Test;

public class checkNetowkSpeed extends TestEnvironment {

	@Test(priority = 0, description = "checkNetowkSpeed")
	public void verifyCELLULAR2GNetowkSpeed(Method method) {
		startTest(method.getName(), "checkNetowkSpeed");
		
		//The CDP command to fake a network connection is Network.emulateNetworkConditions.
		DevTools devTools = ((ChromeDriver) driver).getDevTools();
		devTools.createSession();
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		//https://chromedevtools.github.io/devtools-protocol/tot/Network/#method-emulateNetworkConditions
		devTools.send(Network.emulateNetworkConditions(
				false,
				20,
				20,
				50,
				Optional.of(ConnectionType.CELLULAR2G)));
		driver.get("https://www.google.com");
	}
}
