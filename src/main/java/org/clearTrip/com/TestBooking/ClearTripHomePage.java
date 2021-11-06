package org.clearTrip.com.TestBooking;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ClearTripHomePage extends BasePage {

	@FindBy(xpath = "//a[@class='ctBrand']")
	private WebElement elmClearTripLogo;
	@FindBy(xpath = "//label[contains(text(),'From')]/..//input[@placeholder='Any worldwide city or airport']")
	private WebElement webElmFromInput;
	@FindBy(xpath = "//label[contains(text(),'To')]/..//input[@placeholder='Any worldwide city or airport']")
	private WebElement webElmToInput;
	@FindBy(xpath = "//input[contains(@name,'depart')]")
	private WebElement webElmDepart;
	@FindBy(xpath = "//input[contains(@name,'return')]")
	private WebElement webElmReturn;
	@FindBy(xpath = "//*[contains(text(),'One way')]/ancestor::ul//li")
	private List<WebElement> lstTravelModes;
	@FindBy(xpath = "//*[contains(text(),'One way')]/ancestor::li//input")
	private WebElement elmOneWayInput;
	@FindBy(xpath = "//*[contains(text(),'Round Trip')]/ancestor::li//input")
	private WebElement elmRoundTripInput;
	@FindBy(xpath = "//*[contains(text(),'Multi City')]/ancestor::li//input")
	private WebElement elmMultiCityInput;
	@FindBy(xpath = "//*[@id='adults_selector']")
	private WebElement elmAdultSelector;
	@FindBy(xpath = "//*[@id='children_selector']")
	private WebElement elmchildrenSelector;
	@FindBy(xpath = "//*[@id='infant_selector']")
	private WebElement elmInfantSelector;
	@FindBy(xpath = "//*[@id='submit_search_form']")
	private WebElement elmSearchFlight;

	@FindBy(xpath = "//*[@id='adults_selector']/..//span")
	private WebElement elmAdultSelectorDefault;
	@FindBy(xpath = "//*[@id='children_selector']/..//span")
	private WebElement elmchildrenSelectorDefault;
	@FindBy(xpath = "//*[@id='infant_selector']/..//span")
	private WebElement elmInfantSelectorDefault;

	/**
	 * @Description : Constructor of HomePage
	 * @param driver
	 */
	public ClearTripHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return
	 * @Description : Method to verify Home Page fileds
	 * @return Boolean
	 */
	public HashMap<String, Boolean> verifyHomePageElements() {
		HashMap<String, Boolean> hm = new HashMap<String, Boolean>();
		hm.put("Clear Trip Logo", verifyElementIsPresent(getLocatorInfo(elmClearTripLogo)));
		hm.put("One Way Radio button", verifyElementIsPresent(getLocatorInfo(elmOneWayInput)));
		hm.put("Two Way Radio button", verifyElementIsPresent(getLocatorInfo(elmRoundTripInput)));
		hm.put("Multi City Radio button", verifyElementIsPresent(getLocatorInfo(elmMultiCityInput)));
		hm.put("From Input Filed", verifyElementIsPresent(getLocatorInfo(webElmFromInput)));
		hm.put("To Input Filed", verifyElementIsPresent(getLocatorInfo(webElmToInput)));
		hm.put("Departure Date Filed", verifyElementIsPresent(getLocatorInfo(webElmDepart)));
		hm.put("Adult Drop Down", verifyElementIsPresent(getLocatorInfo(elmAdultSelector)));
		hm.put("Children Drop Down", verifyElementIsPresent(getLocatorInfo(elmchildrenSelector)));
		hm.put("Infant Drop Down", verifyElementIsPresent(getLocatorInfo(elmInfantSelector)));
		hm.put("Search Button", verifyElementIsPresent(getLocatorInfo(elmSearchFlight)));
		return hm;
	}

	/**
	 * @Description : Method to get count of travel mode radio buttons
	 * @return int
	 */
	public int getDefaultTravelModesCount() {
		return lstTravelModes.size();
	}

	/**
	 * @Description : Method to select travel radio button
	 */
	public void selectMode(String tripMode) {
		String xpath = "//p[contains(text(),'" + tripMode + "')]/ancestor::label/..//input";
		verifyElementIsPresent(xpath);
		WebElement radio = driver.findElement(By.xpath(xpath));
		radio.click();
	}

	public boolean verifyOneWayRadioButtonIsChecked() {
		return elmOneWayInput.isSelected();
	}

	public HashMap<String, String> getDefaultPeopleCountAndAgeLimit() {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Adult Drop Down", getDefultValueFromSelectDropDown(elmAdultSelector));
		hm.put("Children Drop Down", getDefultValueFromSelectDropDown(elmchildrenSelector));
		hm.put("Infant Drop Down", getDefultValueFromSelectDropDown(elmInfantSelector));
		hm.put("Adult Age", elmAdultSelectorDefault.getText());
		hm.put("Children Age", elmchildrenSelectorDefault.getText());
		hm.put("Infant Age", elmInfantSelectorDefault.getText());
		return hm;
	}

	public String getDefultValueFromSelectDropDown(WebElement elm) {
		Select se = new Select(driver.findElement(By.xpath(getLocatorInfo(elm))));
		WebElement firstSelectedOption = se.getFirstSelectedOption();
		return firstSelectedOption.getText();
	}

}
