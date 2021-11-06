package org.clearTrip.com.TestBooking;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	WebDriver driver;
	static ResourceBundle urls = ResourceBundle.getBundle("urls");

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitForPageToLoad();
	}

	public void waitForPageToLoad() {
		this.driver.manage().timeouts().pageLoadTimeout(360, TimeUnit.SECONDS);
	}

	public void launchPage() {
		System.out.println("Launching URL");
		driver.get(urls.getString("baseUrl"));
	}

	public boolean verifyElementIsPresent(String xpathOfElement) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement elm;
		elm = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfElement)));
		return elm.isDisplayed();

	}

	public String getLocatorInfo(WebElement element) {
		int startIndex = element.toString().indexOf("xpath:");
		return element.toString().substring(startIndex, element.toString().length() - 1).replaceAll("xpath:", "")
				.trim();
	}
	
	public String getAttributeValue(WebElement element) {
		verifyElementIsPresent(getLocatorInfo(element));
		WebElement text = driver.findElement(By.xpath(getLocatorInfo(element) +"//div"));
		return text.getAttribute("value");
	}
}
