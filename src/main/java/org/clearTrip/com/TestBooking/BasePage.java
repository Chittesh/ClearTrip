package org.clearTrip.com.TestBooking;

import java.time.Duration;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.logs.Log;

/**
 * @author chicharles
 *
 */
public class BasePage {
	WebDriver driver;
	static ResourceBundle urls = ResourceBundle.getBundle("urls");

	/**
	 * @Desciption : Base Page Constructor
	 * @param driver
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitForPageToLoad();
	}

	/**
	 * @Desciption : Method to wait for page to get loaded
	 */
	public void waitForPageToLoad() {
		this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}

	/**
	 * @Desciption : Method to Launch url
	 */
	public void launchPage() {
		System.out.println("Launching URL");
		driver.manage().deleteAllCookies();
		driver.navigate().to(urls.getString("baseUrl"));
	}

	/**
	 * @Desciption : Method to verify element is loaded
	 */
	public boolean verifyElementIsPresent(String xpathOfElement) {
		Log.info("Checking Xpath : "+xpathOfElement);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement elm;
		try {
			elm = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfElement)));
			return elm.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @Desciption : Method to get Xpath value form Page factory annotaion
	 */
	public String getLocatorInfo(WebElement element) {
		int startIndex = element.toString().indexOf("xpath:");
		return element.toString().substring(startIndex, element.toString().length() - 1).replaceAll("xpath:", "")
				.trim();
	}

	/**
	 * @Desciption : Method to get text value
	 */
	public String getTextValue(WebElement element) {
		verifyElementIsPresent(getLocatorInfo(element));
		WebElement text = driver.findElement(By.xpath(getLocatorInfo(element) + "//div"));
		return text.getText();
	}
}
