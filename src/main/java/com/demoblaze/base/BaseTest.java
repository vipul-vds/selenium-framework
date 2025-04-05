package com.demoblaze.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestMethodFinder;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.demoblaze.utils.LoggerHelper;

public class BaseTest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	private static final Logger logger = LoggerHelper.getLogger(BaseTest.class);
	public static ExtentTest test;
	protected static ExtentReports extentReports;
	protected static ExtentSparkReporter extentSparkReporter;
	public static String screenshotsSubFolderName;
	public static File destFile;
	public static String reportSubFolderName;
	public static File reportdestFile;

	@BeforeSuite
	public void setup() {
		setupBrowser();
		setupExtentReport();
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			logger.info("Browser closed");
			test.info("Browser closed");
		}
		generateReport();
	}

	@BeforeMethod
//	public void setupExtentReportTest(ITestResult context) {
	public void setupExtentReportTest(ITestContext context) {
		System.out.println("context.getName() : " + context.getName());
		test = extentReports.createTest(context.getName());
//		System.out.println("context.getName() : " + context.getMethod().getMethodName());
//		test = extentReports.createTest(context.getMethod().getMethodName());
		System.out.println("extent Test object is created");
	}

	@AfterMethod
	public void screenshotCapture(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String fileName = captureScreenshot(
					result.getTestContext().getName() + "_" + result.getMethod().getMethodName() + ".jpg");
			test.addScreenCaptureFromPath(fileName);
			test.fail(result.getThrowable());
			test.fail(MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
			test.info("Test Failed");
		} else {
			test.info("Test Passed");
		}
	}

	public static void setupBrowser() {
		try {
			driver = new FirefoxDriver();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.manage().window().maximize();
			driver.get("https://www.demoblaze.com/");
			logger.info("Browser launched and navigated to DemoBlaze");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setupExtentReport() {
		if (reportSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
			reportSubFolderName = myDateObj.format(myFormatObj);
		}

		reportdestFile = new File("./reports/" + reportSubFolderName + "/testReport.html");

		extentSparkReporter = new ExtentSparkReporter(reportdestFile);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		//test = extentReports.createTest("Test");
	}
	
	public static void generateReport() {
		extentReports.flush();
		logger.info("Extent Report Generated");
	}

	public static String captureScreenshot(String fileName) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (screenshotsSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
			screenshotsSubFolderName = myDateObj.format(myFormatObj);
		}
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		destFile = new File("./screenshots/" + screenshotsSubFolderName + "/" + fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();
	}
}
