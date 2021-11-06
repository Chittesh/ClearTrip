package org.clearTrip.com.TestBooking;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClearTripHomePage extends BasePage {

	@FindBy(xpath = "//a[@data-test-attrib='cleartrip-logo']")
	private WebElement elmClearTripLogo;
	@FindBy(xpath = "//h4[contains(text(),'From')]/..//input[@placeholder='Any worldwide city or airport']")
	private WebElement webElmFromInput;
	@FindBy(xpath = "//h4[contains(text(),'To')]/..//input[@placeholder='Any worldwide city or airport']")
	private WebElement webElmToInput;
	@FindBy(xpath = "//h4[contains(text(),'Depart on')]/ancestor::div[contains(@class,'flex-middle')]/following-sibling::*//button")
	private WebElement webElmDepart;
	@FindBy(xpath = "(//h4[contains(text(),'Return on')]/ancestor::div[contains(@class,'flex-middle')]/following-sibling::*//button)[last()]")
	private WebElement webElmReturn;
	@FindBy(xpath = "//p[contains(text(),'One way')]/ancestor::label/..//label")
	private List<WebElement> lstTravelModes;

	/**
	 * @Description : Constructor of HomePage
	 * @param driver
	 */
	public ClearTripHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * @Description : Method to verify Clear Trip Logo is present
	 * @return Boolean
	 */
	public boolean verifyClearTripLogoIsPresent() {
		return verifyElementIsPresent(getLocatorInfo(elmClearTripLogo));
	}

	/**
	 * @Description : Method to verify From Input is present
	 * @return Boolean
	 */
	public boolean verifyFromInputIsPresent() {
		return verifyElementIsPresent(getLocatorInfo(webElmFromInput));
	}

	/**
	 * @Description : Method to get the default Departure date
	 * @return Boolean
	 */
	public String getDefaultDepartureDate() {
		return getAttributeValue(webElmDepart);
	}

	/**
	 * @Description : Method to get the default Retrun date
	 * @return Boolean
	 */
	public String getDefaultReturnDate() {
		return getAttributeValue(webElmReturn);
	}

}
