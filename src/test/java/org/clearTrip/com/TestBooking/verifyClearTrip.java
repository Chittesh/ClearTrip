package org.clearTrip.com.TestBooking;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.logs.Log;
import static utils.extentreports.ExtentTestManager.startTest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class verifyClearTrip extends TestEnvironment {
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, MMM d");
	static final String expectedDepartureDate = dtf.format(LocalDateTime.now());
	
	
	

	@BeforeTest()
	public void startApplication() {
		ClearTripHomePage objClearTripHomePage = new ClearTripHomePage(driver);
		Log.info("Tests is starting!");
		objClearTripHomePage.launchPage();
	}

	@Test(priority = 0, description = "Verify Clear trip HomePage")
	public void verifyClearTripPageIsLoaded(Method method) {
		ClearTripHomePage objClearTripHomePage = new ClearTripHomePage(driver);
		// ExtentReports Description
		startTest(method.getName(), "Verify Clear trip HomePage");

		Log.info("Verifying Clear trip Home Page Logo is present");
		Assert.assertTrue(objClearTripHomePage.verifyClearTripLogoIsPresent(), "Verify Clear trip Home Page Logo");

		Log.info("Verifying From Location input is present");
		Assert.assertTrue(objClearTripHomePage.verifyFromInputIsPresent(), "Verify From Location");
		
		Log.info("Verifying Default Departure Date");
		String actualDepartedDate  = objClearTripHomePage.getDefaultDepartureDate();
		Assert.assertEquals(actualDepartedDate, expectedDepartureDate,
				"Verify Departure Date Acutual : " + actualDepartedDate + " Expected is : "
						+ expectedDepartureDate);

	}

}
