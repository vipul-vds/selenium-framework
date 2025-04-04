package com.demoblaze.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.demoblaze.utils.LoggerHelper;
import com.demoblaze.utils.Util;

public class LoginPage {
	private WebDriver driver;
	private ExtentTest test;
	private static final Logger logger = LoggerHelper.getLogger(LoginPage.class);
	private String loginButton = "login2";
	private String usernameField = "loginusername";
	private String passwordField = "loginpassword";
	private String submitButton = "//button[text()='Log in']";
	private String logoutButton = "logout2";
	private String closeButton = "//div[@id='logInModal']//button[text()='Close']";

	public LoginPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		System.out.println("test :::: " + test);
		this.test = test;
	}

	public void login(String username, String password) {
//		driver.findElement(loginButton).click();
//		driver.findElement(usernameField).sendKeys(username);
//		driver.findElement(passwordField).sendKeys(password);
//		driver.findElement(submitButton).click();
		WebElement loginButtons = Util.getElementClickableById(driver, loginButton);
		loginButtons.click();

		WebElement usernameFields = Util.getElementLocatedById(driver, usernameField);
		usernameFields.sendKeys(username);

		WebElement passwordFields = Util.getElementLocatedById(driver, passwordField);
		passwordFields.sendKeys(password);

		WebElement submitButtons = Util.getElementClickableByXpath(driver, submitButton);
		submitButtons.click();

		logger.info("User attempted login with username: " + username);
		test.info("User attempted login with username: " + username);
	}

	public void logout(String username) {
		try {
			Thread.sleep(5000);
			// driver.findElement(logoutButton).click();
			WebElement logoutButtons = Util.getElementClickableById(driver, logoutButton);
			logoutButtons.click();
			logger.info("User username: " + username + " logged out");
			test.info("User username: " + username + " logged out");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isLogoutDisplayed() {
		return driver.findElements(By.id("logout2")).size() > 0;
	}

	public void clickOKButton() {
		try {
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			test.info("Closed Alert Pop Up");
			Thread.sleep(5000);

			// WebElement closeButton =
			// driver.findElement(By.xpath("//div[@id='logInModal']//button[text()='Close']"));
			// closeButton.click();
			WebElement closeButtons = Util.getElementClickableByXpath(driver, closeButton);
			closeButtons.click();

			test.info("Clicked on Close Button");
			// driver.findElement(closeButton).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
