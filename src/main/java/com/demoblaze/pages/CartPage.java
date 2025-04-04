package com.demoblaze.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.demoblaze.utils.LoggerHelper;
import com.demoblaze.utils.Util;

public class CartPage {
	private WebDriver driver;
	private ExtentTest test;
	private static final Logger logger = LoggerHelper.getLogger(CartPage.class);

	String goToCart = "cartur";
	String placeOrder = "//button[text()='Place Order']";
	String closeBtn = "//button[text()='Close']";

	public CartPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public void goToCart() {
		Util.getElementClickableById(driver, goToCart).click();
		logger.info("Cart window opened");
		test.info("Cart window opened");
	}

	public void placeOrder() {
		Util.getElementClickableByXpath(driver, placeOrder).click();
		logger.info("Order placed");
		test.info("Order placed");
	}

	public void clickCloseBtn() throws InterruptedException {
		// Util.getElementClickableByXpath(driver, closeBtn).click();
		Thread.sleep(5000);
		List<WebElement> closeButton = driver.findElements(By.xpath(closeBtn));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", closeButton.get(2));
		js.executeScript("arguments[0].click()", closeButton.get(2));
		System.out.println("Clicked on Close Button");
	}
}
