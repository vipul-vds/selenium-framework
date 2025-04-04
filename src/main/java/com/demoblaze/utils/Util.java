package com.demoblaze.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Util {
	public static WebElement getElementLocatedById(WebDriver driver, String element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			return wait.until(ExpectedConditions.presenceOfElementLocated(By.id(element)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static WebElement getElementClickableById(WebDriver driver, String element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(element)));
			return wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static WebElement getElementLocatedByXpath(WebDriver driver, String element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static WebElement getElementClickableByXpath(WebDriver driver, String element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
			return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void clickAlertAcceptButton(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.alertIsPresent()).accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
