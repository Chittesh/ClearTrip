package org.clearTrip.com.TestBooking;

import org.testng.annotations.Test;
import utils.logs.Log;
import static utils.extentreports.ExtentTestManager.startTest;
import java.lang.reflect.Method;

public class verifyClearTrip extends TestEnvironment {

	@Test(priority = 0, description = "Verify Clear trip HomePage")
	public void verifyClearTripPageIsLoaded(Method method) {
		// ExtentReports Description
		startTest(method.getName(), "Verify Clear trip HomePage");

		Log.info("Tests is starting!");
		ClearTripHomePage objClearTripHomePage = new ClearTripHomePage(driver);
		objClearTripHomePage.launchPage();
	}

}
