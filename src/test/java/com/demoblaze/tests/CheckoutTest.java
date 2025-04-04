package com.demoblaze.tests;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demoblaze.base.BaseTest;
import com.demoblaze.pages.CartPage;
import com.demoblaze.pages.CheckoutPage;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.ProductPage;
import com.demoblaze.utils.LoggerHelper;

import junit.framework.Assert;

public class CheckoutTest extends BaseTest{
	private HomePage homePage;
	private ProductPage productPage;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;
	private static final Logger logger = LoggerHelper.getLogger(CheckoutTest.class);

	@BeforeClass
	public void initPages() {
		homePage = new HomePage(driver, test);
		productPage = new ProductPage(driver, test);
		cartPage = new CartPage(driver, test);
		checkoutPage = new CheckoutPage(driver, test);
	}
	
	@BeforeMethod
	public void initPages1() {
		homePage = new HomePage(driver, test);
		productPage = new ProductPage(driver, test);
		cartPage = new CartPage(driver, test);
		checkoutPage = new CheckoutPage(driver, test);
	}

	@Test(priority = 0)
	public void completePayment() {
		try {
			String product = "Samsung galaxy s6";
			homePage.selectProduct(product);
			Assert.assertEquals(product, productPage.validateProduct());
			productPage.addToCart();
			cartPage.goToCart();
			cartPage.placeOrder();
			checkoutPage.fillDetailsAndPurchase("John", "USA", "Minneapolis", "4563 4567 8976 4321", "12", "2027");
			Assert.assertTrue("Purchase confirmation not displayed", checkoutPage.verifyPurchase());
			test.pass("Purchase successful");
			logger.info("Purchase successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
