package com.demoblaze.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.demoblaze.base.BaseTest;
import com.demoblaze.utils.LoggerHelper;
import com.demoblaze.utils.Util;

public class CheckoutPage {
	private WebDriver driver;
	private ExtentTest test;
	private static final Logger logger = LoggerHelper.getLogger(CheckoutPage.class);

	String name = "name";
	String country = "country";
	String city = "city";
	String creditCard = "card";
	String month = "month";
	String year = "year";
	String purchase = "//button[text()='Purchase']";
	String ok = "//button[text()='OK']";

	public CheckoutPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public void fillDetailsAndPurchase(String nameStr, String countryStr, String cityStr, String cardStr,
			String monthStr, String yearStr) {
		Util.getElementLocatedById(driver, name).sendKeys(nameStr);
		Util.getElementLocatedById(driver, country).sendKeys(countryStr);
		Util.getElementLocatedById(driver, city).sendKeys(cityStr);
		Util.getElementLocatedById(driver, creditCard).sendKeys(cardStr);
		Util.getElementLocatedById(driver, month).sendKeys(monthStr);
		Util.getElementLocatedById(driver, year).sendKeys(yearStr);
		Util.getElementClickableByXpath(driver, purchase).click();
		test.info("Order is Purchased");
		logger.info("Order is Purchased");
	}

	public boolean verifyPurchase() {
		WebElement confirmationText = driver.findElement(By.xpath("//h2[text()='Thank you for your purchase!']"));
		try {
			Thread.sleep(5000);
			boolean isDisplayed = confirmationText.isDisplayed();
			if (isDisplayed) {
				Util.getElementClickableByXpath(driver, ok).click();
				test.info("Purchase Successful");
				logger.info("Purchase Successful");
			} else {
				test.fail("Purchase Fail");
				logger.error("Purchase Fail");
			}
			return isDisplayed;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
}
