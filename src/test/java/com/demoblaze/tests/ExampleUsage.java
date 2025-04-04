package com.demoblaze.tests;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.demoblaze.utils.ConfigReader;
import com.demoblaze.utils.Util;

public class ExampleUsage {
	
	public static void main(String[] args) throws InterruptedException {
		/*
		 * System.out.println("Browser: " + ConfigReader.getProperty("browser"));
		 * System.out.println("Base URL: " + ConfigReader.getProperty("baseURL"));
		 * System.out.println("Implicit Wait: " +
		 * ConfigReader.getIntProperty("implicitWait") + " seconds");
		 */
		WebDriver driver = null;

		try {
			driver = new FirefoxDriver();
			driver.get("https://www.rbi.org.in/");
			driver.manage().window().maximize();

			Thread.sleep(2000);
			WebElement engBtn = driver.findElement(By.xpath("//Button[text()='English']"));
			engBtn.click();

			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement bankHolidaysLink = driver.findElement(By.linkText("Bank Holidays"));
			js.executeScript("arguments[0].click();", bankHolidaysLink);
			System.out.println("Successfully clicked on Bank Holidays Link");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			
			//Thread.sleep(5000);
			//WebElement regionalOffice = driver.findElement(By.id("drRegionalOffice"));
			//WebElement regionalOffice = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("drRegionalOffice")));
			Select selectRegionalOffice = new Select(Util.getElementLocatedById(driver,"drRegionalOffice"));
			selectRegionalOffice.selectByVisibleText("Mumbai");

			//Thread.sleep(2000);
			//WebElement month = driver.findElement(By.id("drMonth"));
			//WebElement month = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("drMonth")));
			Select selectMonth = new Select(Util.getElementLocatedById(driver,"drMonth"));
			selectMonth.selectByVisibleText("All Months");

			//Thread.sleep(2000);
			//WebElement goButton = driver.findElement(By.id("btnGo"));
			//WebElement goButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnGo")));
			//goButton.click();
			Util.getElementLocatedById(driver,"btnGo").click();
			System.out.println("Successfully clicked on Go button");

			String filePath = "holidays.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            
            //WebElement holidayTable = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='tablebg']")));
            //List<WebElement> rows = holidayTable.findElements(By.xpath("//tr"));
            WebElement holidayTable = Util.getElementLocatedByXpath(driver, "//table[@class='tablebg']");
            List<WebElement> rows = holidayTable.findElements(By.xpath("//tr"));
            
            String months = null;
			for (WebElement row : rows) {
				//System.out.println(row.getText());
				List<WebElement> columns = row.findElements(By.tagName("td"));
				
				if (columns.size() == 1 && !columns.get(0).getText().isEmpty())
					months=columns.get(0).getText();
                
                if (columns.size() == 3) {
                    String day = columns.get(0).getText();
                    String occasion = columns.get(1).getText();

                    // Format the data as "Month - Day - Occasion"
                    String formattedHoliday = months + " | " + day + " | " + occasion;

                    // Write the formatted string to the file
                    writer.write(formattedHoliday);
                    writer.newLine();  // Move to the next line
                }
			}
			writer.close();
			System.out.println("Holidays have been written to " + filePath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}