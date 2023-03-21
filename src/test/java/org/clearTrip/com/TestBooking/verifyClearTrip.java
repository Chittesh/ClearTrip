package org.clearTrip.com.TestBooking;




import utils.logs.Log_1;

import static utils.extentreports.ExtentTestManager.startTest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class verifyClearTrip extends TestEnvironment {

	@BeforeTest()
	public void startApplication() {
		BasePage objBasePage = new BasePage(driver);
		Log_1.info("Tests is starting!");
		objBasePage.launchPage();
	}

	@Test(priority = 0, description = "Verify Clear Trip Home Page Defaults Values and fileds")
	public void verifyTripHomePageDefaultsValues(Method method) {
		TripHomePage objTripHomePage = new TripHomePage(driver);
		// ExtentReports Description
		startTest(method.getName(), "Verify Clear Trip Home Page Defaults Values and fileds");

		Log_1.info("Verifying Clear trip Home Page Fileds");

		HashMap<String, Boolean> hmDefaultFileds = new HashMap<String, Boolean>();
		hmDefaultFileds = objTripHomePage.verifyHomePageElements();

		for (Entry<String, Boolean> entry : hmDefaultFileds.entrySet()) {
			String key = entry.getKey();
			Boolean value = entry.getValue();
			Assert.assertTrue(value, "Verify " + key);
		}

		Log_1.info("Verifying Count of Travel Modes");
		int actualCountOfModes = objTripHomePage.getDefaultTravelModesCount();
		Assert.assertEquals(actualCountOfModes, Constants.expectedTravelModeCount,
				"Verify count of travel modes Actual : " + actualCountOfModes + " Expected is : "
						+ Constants.expectedTravelModeCount);

		Log_1.info("Verifying One Way Radio Button is selected");
		Assert.assertTrue(objTripHomePage.verifyOneWayRadioButtonIsChecked(), "Verify One Way Radio Button");

		Log_1.info("Verifying Passager default values");
		HashMap<String, String> hmDefaultPassangerValues = new HashMap<String, String>();
		hmDefaultPassangerValues = objTripHomePage.getDefaultPeopleCountAndAgeLimit();
		Assert.assertEquals(hmDefaultPassangerValues.get("Adult Drop Down"),
				Constants.expectedAdultDropDownValue,
				"Verify Adult Drop Down Actual : " + hmDefaultPassangerValues.get("Adult Drop Down") + " Expected is : "
						+ Constants.expectedAdultDropDownValue);
		Assert.assertEquals(hmDefaultPassangerValues.get("Children Drop Down"),
				Constants.expectedChildDropDownValue,
				"Verify Children Drop Down Actual : " + hmDefaultPassangerValues.get("Children Drop Down")
						+ " Expected is : " + Constants.expectedChildDropDownValue);
		Assert.assertEquals(hmDefaultPassangerValues.get("Infant Drop Down"),
				Constants.expectedInfantDropDownValue,
				"Verify Infant Drop Down Actual : " + hmDefaultPassangerValues.get("Infant Drop Down")
						+ " Expected is : " + Constants.expectedInfantDropDownValue);
		Assert.assertEquals(hmDefaultPassangerValues.get("Adult Age"), Constants.expectedAdultDefaultValue,
				"Verify Adult Default Actual : " + hmDefaultPassangerValues.get("Adult Age") + " Expected is : "
						+ Constants.expectedAdultDefaultValue);
		Assert.assertEquals(hmDefaultPassangerValues.get("Children Age"),
				Constants.expectedChildtDefaultValue,
				"Verify Children Default Actual : " + hmDefaultPassangerValues.get("Children Age") + " Expected is : "
						+ Constants.expectedChildtDefaultValue);
		Assert.assertEquals(hmDefaultPassangerValues.get("Infant Age"), Constants.expectedInfantDefaultValue,
				"Verify Infant Default Actual : " + hmDefaultPassangerValues.get("Infant Age") + " Expected is : "
						+ Constants.expectedInfantDefaultValue);
	}

	@Test(priority = 1, description = "verify Booking Tickets")
	public void verifyBookingTickets(Method method) {
		BasePage objBasePage = new BasePage(driver);
		TripHomePage objTripHomePage = new TripHomePage(driver);
		HomeBookingPage objBookingPage = new HomeBookingPage(driver);
		// ExtentReports Description
		startTest(method.getName(), "verify Booking Tickets");

		Log_1.info("verify Booking Tickets");
		objTripHomePage.selectMode(Constants.oneWayTrip);

		// Log_1.info("Verifying Return date field is present");
		// Assert.assertTrue(objTripHomePage.verifyReturnFiled(), "Verify Return
		// date field");

		Log_1.info("Selecting Round trip fileds");
		objBasePage.setDates(Constants.dateFiledFormat);
		objTripHomePage.selectingFiledsForOneWay(Constants.fromLocation, Constants.toLocation,
				objBasePage.fromDate, Constants.adultCount, Constants.childCount,
				Constants.infantCount);

		Log_1.info("Hitting search Button");
		objTripHomePage.clickOnSearch();

		Log_1.info("Verifying booking page Elements");
		HashMap<String, Boolean> hmBookingPageElements = new HashMap<String, Boolean>();
		hmBookingPageElements = objBookingPage.verifyBookingPageElements();
		for (Entry<String, Boolean> entry : hmBookingPageElements.entrySet()) {
			String key = entry.getKey();
			Boolean value = entry.getValue();
			Assert.assertTrue(value, "Verify " + key);
		}

		Log_1.info("Verifying booking page Top search fields");
		List<String> expectedSearchFileds = new ArrayList<String>();
		objBasePage.setDates(Constants.expdateFiledFormat);
		String expectedPassngerCount = String.valueOf(Integer.parseInt(Constants.adultCount)
				+ Integer.parseInt(Constants.childCount) + Integer.parseInt(Constants.infantCount));
		expectedSearchFileds.add(objBasePage.fromDate);
		expectedSearchFileds.add("Return");
		expectedSearchFileds.add(expectedPassngerCount + " Travellers");
		expectedSearchFileds.add(Constants.expctedfromLocation);
		expectedSearchFileds.add(Constants.expectedtoLocation);
		expectedSearchFileds.add(Constants.oneWayTrip);
		Collections.sort(expectedSearchFileds);
		List<String> actualSearchFileds = new ArrayList<String>();
		actualSearchFileds = objBookingPage.getTopSearchDefaulFields();
		Assert.assertEquals(actualSearchFileds, expectedSearchFileds, "Veirfy top search fields");

		Log_1.info("Verifying Results count");
		Assert.assertTrue(objBookingPage.getResultsCount() > 0, "Verify Results are greater than zero");
	}

}
