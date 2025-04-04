package com.demoblaze.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.demoblaze.utils.LoggerHelper;
import com.demoblaze.utils.Util;

public class ProductPage {
	private WebDriver driver;
	private ExtentTest test;
	private static final Logger logger = LoggerHelper.getLogger(ProductPage.class);

	String addToCart = "//a[contains(text(),'Add to cart')]";
	String productName = "//h2[@class='name']";

	public ProductPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public String validateProduct() {
		WebElement productNameLabel = Util.getElementLocatedByXpath(driver, productName);
		// Assert.assertEquals(productNameLabel.getText(), product);
		logger.info("Product Name: " + productNameLabel.getText());
		test.info("Product Name: " + productNameLabel.getText());
		return productNameLabel.getText();
	}

	public void addToCart() {
		try {
			Util.getElementClickableByXpath(driver, addToCart).click();
			Util.clickAlertAcceptButton(driver);
			logger.info("Product is added to Cart successfully");
			test.info("Product is added to Cart successfully");
		} catch (Exception e) {
			test.fail("Add to Cart Test Failed");
			System.err.println("Add to Cart Test Failed");
			throw new AssertionError("Add to Cart Test Failed", e);
		}
	}

}
