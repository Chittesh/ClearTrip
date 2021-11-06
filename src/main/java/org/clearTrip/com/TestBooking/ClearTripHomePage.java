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

	@FindBy(xpath = "//*[contains(text(),'Travellers | Class')]/following-sibling::input")
	private WebElement webElmTarvellers;
	@FindBy(xpath = "//button[contains(text(),'Search')]")
	private WebElement webElmSearchButton;
	@FindBy(xpath = "//*[contains(@class,'rd-date')]//table")
	private WebElement webElmDateTableFrom;
	@FindBy(xpath = "(//*[contains(@class,'rd-date')]//table)[last()]")
	private WebElement webElmDateTableTo;
	@FindBy(xpath = "//*[contains(text(),'Adult')]/ancestor::*[contains(@class,'number-counter')]//*[@data-val='2']")
	private WebElement webElmDateNoOfTravellers;
	/*
	 * @FindBy(xpath = "//button[@class='ixi-icon-arrow rd-next']") private
	 * WebElement webElmDateNextIcon;
	 */

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

	public String getDefaultTravellers() {
		verifyElementIsPresent(getLocatorInfo(webElmTarvellers));
		return webElmTarvellers.getAttribute("value");
	}

	public void enterTravelDetails(String from, String to, String depDate, String retDate) throws InterruptedException {
		System.out.println("Entering details to Search flights");

		System.out.println("Entering From Place as : " + from);
		webElmFromInput.clear();
		Thread.sleep(2000);
		webElmFromInput.sendKeys(from);
		Thread.sleep(4000);
		webElmFromInput.clear();
		Thread.sleep(2000);
		webElmFromInput.sendKeys(from);
		Thread.sleep(4000);
		webElmFromInput.sendKeys(Keys.ENTER);

		System.out.println("Entering To Place as : " + to);
		webElmToInput.clear();
		Thread.sleep(2000);
		webElmToInput.sendKeys(to);
		Thread.sleep(4000);
		webElmToInput.clear();
		Thread.sleep(2000);
		webElmToInput.sendKeys(to);
		Thread.sleep(4000);
		webElmToInput.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		System.out.println("Selecting Depart Date as :" + depDate);
		webElmDepart.click();
		Thread.sleep(2000);
		// webElmDateNextIcon.click();
		Thread.sleep(2000);
		verifyElementIsPresent(getLocatorInfo(webElmDateTableFrom));
		WebElement fromDate = driver
				.findElement(By.xpath("//*[contains(@class,'rd-date')]//table//*[@data-date='" + depDate + "']"));
		fromDate.click();
		Thread.sleep(2000);
		System.out.println("Selecting Return Date as : " + retDate);
		webElmReturn.click();
		Thread.sleep(2000);
		verifyElementIsPresent(getLocatorInfo(webElmDateTableTo));
		WebElement toDate = driver.findElement(
				By.xpath("(//*[contains(@class,'rd-date')]//table//*[@data-date='" + retDate + "'])[last()]"));
		toDate.click();
		Thread.sleep(2000);

		System.out.println("Selecting No of Travellers as 2");
		webElmTarvellers.click();
		Thread.sleep(2000);
		verifyElementIsPresent(getLocatorInfo(webElmDateNoOfTravellers));
		webElmDateNoOfTravellers.click();
		Thread.sleep(2000);

		System.out.println("Clicking Search Button");
		webElmSearchButton.click();

	}

}
