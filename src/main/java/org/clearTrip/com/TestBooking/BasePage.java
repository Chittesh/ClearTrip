package org.clearTrip.com.TestBooking;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.logs.Log;

/**
 * @author chicharles
 *
 */
public class BasePage {

	static String fromDate;
	static String toDate;

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
		Log.info("Checking Xpath : " + xpathOfElement);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
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

	/**
	 * @Description : Method to get the first selected value from select drop down
	 */
	public String getDefultValueFromSelectDropDown(WebElement elm) {
		Select objSelect = new Select(driver.findElement(By.xpath(getLocatorInfo(elm))));
		WebElement firstSelectedOption = objSelect.getFirstSelectedOption();
		return firstSelectedOption.getText();
	}

	/**
	 * @Description : Method to select from select drop down
	 */
	public void selectFromSelectDropDown(WebElement elm, String value) {
		Select objSelect = new Select(driver.findElement(By.xpath(getLocatorInfo(elm))));
		objSelect.selectByValue(value);
	}

	/**
	 * @Description : Method to set from and to date
	 */
	public void setDates(String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date());
		c1.add(Calendar.DATE, 5);
		fromDate = simpleDateFormat.format(c1.getTime());
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 40);
		String output = simpleDateFormat.format(c.getTime());
		System.out.println(output);
		toDate = output;
	}

	/**
	 * @Description : Method to select date from date picker
	 */
	public void selectDatePicker(String date) {
		String[] dateArray = date.split("/");
		String day = dateArray[0];
		String month = dateArray[1];
		String year = dateArray[2];
		String monthToSelected = String.valueOf((Integer.parseInt(month) - 1));

		String xpathOfTable = "//table[contains(@class,'ui-datepicker-calendar')]";
		verifyElementIsPresent(xpathOfTable);
		String dateToBeSelected = xpathOfTable + "//*[contains(@data-month,'" + monthToSelected
				+ "')][contains(@data-year,'" + year + "')]//a[(text()='" + day + "')]";
		verifyElementIsPresent(dateToBeSelected);
		driver.findElement(By.xpath(dateToBeSelected)).click();
	}
	
	public void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
