package com.demoblaze.tests;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demoblaze.base.BaseTest;
import com.demoblaze.pages.CartPage;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.ProductPage;
import com.demoblaze.utils.LoggerHelper;

import junit.framework.Assert;

public class CartTest extends BaseTest {
	private HomePage homePage;
	private ProductPage productPage;
	private CartPage cartPage;
	private static final Logger logger = LoggerHelper.getLogger(CartTest.class);

	@BeforeClass
	public void initPages() {
		homePage = new HomePage(driver, test);
		productPage = new ProductPage(driver, test);
		cartPage = new CartPage(driver, test);
	}
	
	@BeforeMethod
	public void initPages1() {
		homePage = new HomePage(driver, test);
		productPage = new ProductPage(driver, test);
		cartPage = new CartPage(driver, test);
	}

	@Test(priority = 0)
	public void proceedToCheckout() {
		try {
			String product = "Samsung galaxy s6";
			homePage.selectProduct(product);
			Assert.assertEquals(product, productPage.validateProduct());
			productPage.addToCart();
			cartPage.goToCart();
			cartPage.placeOrder();
			cartPage.clickCloseBtn();
			test.pass("Order Place test passed");
			logger.info("Order Place test passed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
