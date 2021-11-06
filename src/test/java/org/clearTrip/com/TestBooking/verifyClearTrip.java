package org.clearTrip.com.TestBooking;



import org.testng.annotations.Test;

public class verifyClearTrip extends TestEnvironment {

	@Test()
	public void verifyClearTripPageIsLoaded() throws InterruptedException {

		ClearTripHomePage objIxigoHomePage = new ClearTripHomePage(driver);
		objIxigoHomePage.launchPage();

	}

}
