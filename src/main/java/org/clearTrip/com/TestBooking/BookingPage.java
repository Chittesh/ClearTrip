package org.clearTrip.com.TestBooking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.logs.Log;

public class BookingPage extends BasePage {

	@FindBy(xpath = "//*[@data-test-attrib='onward-view']")
	private WebElement elmOnwardView;
	@FindBy(xpath = "//*[@data-test-attrib='return-view']")
	private WebElement elmReturnView;
	@FindBy(xpath = "//*[contains(@class,'sortbar-container')]")
	private WebElement elmView;
	@FindBy(xpath = "//*[contains(@data-ct-handle,'solutionPrice')]//p[contains(text(),'₹')]")
	private List<WebElement> elmViewResults;
	@FindBy(xpath = "//*[contains(@class,'col flex flex-middle')]//button//div[contains(text(),'')]")
	private List<WebElement> elmXpathOfTopSeachButtons;
	@FindBy(xpath = "//*[contains(@class,'col flex flex-middle')]//input")
	private List<WebElement> elmXpathOfInput;

	/**
	 * @Description : Constructor of HomePage
	 * @param driver
	 */
	public BookingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return
	 * @Description : Method to verify Booking Page fields
	 * @return Boolean
	 */
	public HashMap<String, Boolean> verifyBookingPageElements() {
		HashMap<String, Boolean> hm = new HashMap<String, Boolean>();
		hm.put("Results View", verifyElementIsPresent(getLocatorInfo(elmView)));
		hm.put("Results", verifyElementIsPresent(getLocatorInfo(elmViewResults.get(0))));

		return hm;
	}

	/**
	 * @return
	 * @Description : Method to get Results count
	 * @return int
	 */
	public int getResultsCount() {
		return elmViewResults.size();
	}

	/**
	 * @return
	 * @Description : Method to get Top search results
	 * @return List<String>
	 */
	public List<String> getTopSearchDefaulFields() {
		verifyElementIsPresent(getLocatorInfo(elmXpathOfTopSeachButtons.get(0)));
		List<String> searchFileds = new ArrayList<String>();
		List<String> searchFileds_2 = new ArrayList<String>();
		searchFileds = elmXpathOfInput.stream().map(elm -> elm.getAttribute("value")).collect(Collectors.toList());
		searchFileds_2 = elmXpathOfTopSeachButtons.stream().map(elm -> elm.getText()).collect(Collectors.toList());
		searchFileds.addAll(searchFileds_2);
		Collections.sort(searchFileds);
		return searchFileds;
	}

}
