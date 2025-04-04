package com.demoblaze.tests;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demoblaze.base.BaseTest;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.ProductPage;
import com.demoblaze.utils.LoggerHelper;

import junit.framework.Assert;

public class ProductTest extends BaseTest {
	private HomePage homePage;
	private ProductPage productPage;
	private static final Logger logger = LoggerHelper.getLogger(ProductTest.class);

	@BeforeClass
	public void initPages() {
		homePage = new HomePage(driver, test);
		productPage = new ProductPage(driver, test);
	}
	
	@BeforeMethod
	public void initPages1() {
		homePage = new HomePage(driver, test);
		productPage = new ProductPage(driver, test);
	}

	@Test(priority = 0)
	public void addToCartProduct() {
		try {
			String product = "Samsung galaxy s6";
			homePage.selectProduct(product);
			Assert.assertEquals(product, productPage.validateProduct());
			productPage.addToCart();
			test.pass("Select Product test passed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
