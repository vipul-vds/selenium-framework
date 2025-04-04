package com.demoblaze.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.demoblaze.utils.LoggerHelper;
import com.demoblaze.utils.Util;

public class HomePage {
	private WebDriver driver;
	private static final Logger logger = LoggerHelper.getLogger(HomePage.class);
	private ExtentTest test;
	String productLinkStart = "//a[contains(text(),'"; 
	String productLinkEnd = "')]";
	String home = "//a[contains(text(),'Home')]";
	
	// String productLink = "//a[contains(text(),'Samsung galaxy s6')]";

	public HomePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public void selectProduct(String productName) {
		try {
			Util.getElementClickableByXpath(driver, home).click();
			WebElement product = Util.getElementClickableByXpath(driver, productLinkStart + productName + productLinkEnd);
			
			String prdName = product.getText();
			
			product.click();
			
			test.info("Product is clicked successfully " + prdName);
			logger.info("Product is selected successfully " + prdName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
