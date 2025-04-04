package com.demoblaze.tests;

import com.demoblaze.base.BaseTest;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.LoginPage;
import com.demoblaze.utils.LoggerHelper;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
	private LoginPage loginPage;
	private static final Logger logger = LoggerHelper.getLogger(LoginTest.class);
	
	@BeforeClass
	public void initPages() {
		loginPage = new LoginPage(driver, test);
	}
	
	@BeforeMethod
	public void initPages1() {
		loginPage = new LoginPage(driver, test);
	}
	
	@Test(priority=0)
	public void validLoginTest() {
		loginPage.login("validUser", "validPassword");
		test.pass("Login Test");
		Assert.assertEquals("1", "2");
		Assert.assertTrue(loginPage.isLogoutDisplayed(), "Login failed");
		test.pass("Login test passed");
		
		loginPage.logout("validUser");
	}
	
	//@Test(priority=1)
	public void validLoginTest7() {
		loginPage.login("validUser", "validPassword");
		test.pass("Login Test");
		Assert.assertTrue(loginPage.isLogoutDisplayed(), "Login failed");
		test.pass("Login test passed");
		
		loginPage.logout("validUser");
	}

	//@Test(priority=2)
	public void invalidLoginTest() {
		loginPage.login("invalidUser", "invalidPassword");
		loginPage.clickOKButton();
		Assert.assertFalse(loginPage.isLogoutDisplayed(), "Login should fail");
		test.fail("Invalid login test failed");
	}
}
