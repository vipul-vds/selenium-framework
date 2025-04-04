package com.demoblaze.tests;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demoblaze.base.BaseTest;
import com.demoblaze.pages.HomePage;
import com.demoblaze.utils.LoggerHelper;

public class HomeTest extends BaseTest {
	private HomePage homePage;
	private static final Logger logger = LoggerHelper.getLogger(HomeTest.class);
	
	@BeforeClass
	public void initPages() {
		homePage = new HomePage(driver, test);
	}
	
	@BeforeMethod
	public void initPages1() {
		homePage = new HomePage(driver, test);
	}
	
	@Test(priority=0)
	public void selectProduct() {
		String product = "Samsung galaxy s6";
		homePage.selectProduct(product);
		test.pass("Select Product test passed");
	}
}
