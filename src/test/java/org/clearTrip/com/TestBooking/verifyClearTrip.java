package org.clearTrip.com.TestBooking;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.logs.Log;
import static utils.extentreports.ExtentTestManager.startTest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map.Entry;

public class verifyClearTrip extends TestEnvironment {

	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, MMM d");
	static final String expectedDepartureDate = dtf.format(LocalDateTime.now());

	@BeforeTest()
	public void startApplication() {
		BasePage objBasePage = new BasePage(driver);
		Log.info("Tests is starting!");
		objBasePage.launchPage();
	}

	@Test(priority = 0, description = "Verify Clear Trip Home Page Defaults Values and fileds")
	public void verifyClearTripHomePageDefaultsValues(Method method) {
		ClearTripHomePage objClearTripHomePage = new ClearTripHomePage(driver);
		// ExtentReports Description
		startTest(method.getName(), "Verify Clear Trip Home Page Defaults Values and fileds");

		Log.info("Verifying Clear trip Home Page Fileds");

		HashMap<String, Boolean> hmDefaultFileds = new HashMap<String, Boolean>();
		hmDefaultFileds = objClearTripHomePage.verifyHomePageElements();

		for (Entry<String, Boolean> entry : hmDefaultFileds.entrySet()) {
			String key = entry.getKey();
			Boolean value = entry.getValue();
			Assert.assertTrue(value, "Verify " + key);
		}

		Log.info("Verifying Count of Travel Modes");
		int actualCountOfModes = objClearTripHomePage.getDefaultTravelModesCount();
		Assert.assertEquals(actualCountOfModes, StaticConstantClass.expectedTravelModeCount,
				"Verify count of travel modes Actual : " + actualCountOfModes + " Expected is : "
						+ StaticConstantClass.expectedTravelModeCount);

		Log.info("Verifying One Way Radio Button is selected");
		Assert.assertTrue(objClearTripHomePage.verifyOneWayRadioButtonIsChecked(), "Verify One Way Radio Button");

		Log.info("Verifying Passager default values");
		HashMap<String, String> hmDefaultPassangerValues = new HashMap<String, String>();
		hmDefaultPassangerValues = objClearTripHomePage.getDefaultPeopleCountAndAgeLimit();
		Assert.assertEquals(hmDefaultPassangerValues.get("Adult Drop Down"),
				StaticConstantClass.expectedAdultDropDownValue,
				"Verify Adult Drop Down Actual : " + hmDefaultPassangerValues.get("Adult Drop Down") + " Expected is : "
						+ StaticConstantClass.expectedAdultDropDownValue);
		Assert.assertEquals(hmDefaultPassangerValues.get("Children Drop Down"),
				StaticConstantClass.expectedChildDropDownValue,
				"Verify Children Drop Down Actual : " + hmDefaultPassangerValues.get("Children Drop Down")
						+ " Expected is : " + StaticConstantClass.expectedChildDropDownValue);
		Assert.assertEquals(hmDefaultPassangerValues.get("Infant Drop Down"),
				StaticConstantClass.expectedInfantDropDownValue,
				"Verify Infant Drop Down Actual : " + hmDefaultPassangerValues.get("Infant Drop Down")
						+ " Expected is : " + StaticConstantClass.expectedInfantDropDownValue);
		Assert.assertEquals(hmDefaultPassangerValues.get("Adult Age"), StaticConstantClass.expectedAdultDefaultValue,
				"Verify Adult Default Actual : " + hmDefaultPassangerValues.get("Adult Age") + " Expected is : "
						+ StaticConstantClass.expectedAdultDefaultValue);
		Assert.assertEquals(hmDefaultPassangerValues.get("Children Age"),
				StaticConstantClass.expectedChildtDefaultValue,
				"Verify Children Default Actual : " + hmDefaultPassangerValues.get("Children Age") + " Expected is : "
						+ StaticConstantClass.expectedChildtDefaultValue);
		Assert.assertEquals(hmDefaultPassangerValues.get("Infant Age"), StaticConstantClass.expectedInfantDefaultValue,
				"Verify Infant Default Actual : " + hmDefaultPassangerValues.get("Infant Age") + " Expected is : "
						+ StaticConstantClass.expectedInfantDefaultValue);
	}

}
