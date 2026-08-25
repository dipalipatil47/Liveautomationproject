package tutorialsninja.register;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_10 {

	@Test
	public void verifyRegisteringAccountUsingInvalidEmail() throws InterruptedException, IOException {

		WebDriver driver = new ChromeDriver();

		try {

			// =====================================================
			// Browser Setup
			// =====================================================

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();

			driver.get("https://tutorialsninja.com/demo");

			// =====================================================
			// Navigate to Register Account
			// =====================================================

			driver.findElement(By.xpath("//span[text()='My Account']")).click();

			driver.findElement(By.linkText("Register")).click();

			// =====================================================
			// Enter Registration Details
			// =====================================================

			driver.findElement(By.id("input-firstname")).sendKeys("Dipali");

			driver.findElement(By.id("input-lastname")).sendKeys("Patil");

			driver.findElement(By.id("input-email")).sendKeys("dipali@gmail");

			driver.findElement(By.id("input-telephone")).sendKeys("09146220946");

			driver.findElement(By.id("input-password")).sendKeys("1234");

			driver.findElement(By.id("input-confirm")).sendKeys("1234");

			// Newsletter - No
			driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).click();

			// Agree to Privacy Policy
			driver.findElement(By.name("agree")).click();

			// =====================================================
			// Click Continue
			// =====================================================

			driver.findElement(By.xpath("//input[@value='Continue']")).click();

			Thread.sleep(2000);

			// =====================================================
			// Verify Invalid Email Error Message
			// =====================================================

			String expectedWarningMessage = "E-Mail Address does not appear to be valid!";

			String actualWarningMessage = driver
					.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();

			System.out.println("Expected Warning Message : " + expectedWarningMessage);

			System.out.println("Actual Warning Message   : " + actualWarningMessage);

			Assert.assertEquals(actualWarningMessage, expectedWarningMessage,
					"Invalid email validation message is incorrect.");

			// =====================================================
			// Take Screenshot
			// =====================================================

			captureScreenshot(driver, "TC_RF_10_InvalidEmail.png");

			// =====================================================
			// Test Case Passed
			// =====================================================

			System.out.println("TC_RF_10 - Invalid Email Test Passed");

		} finally {

			// =====================================================
			// Close Browser
			// =====================================================

			driver.quit();
		}
	}

	// =============================================================
	// Screenshot Method
	// =============================================================

	public void captureScreenshot(WebDriver driver, String fileName) throws IOException {

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File destination = new File(
				System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + fileName);

		// Create Screenshots folder if it does not exist
		destination.getParentFile().mkdirs();

		FileHandler.copy(source, destination);

		System.out.println("Screenshot saved: " + destination.getAbsolutePath());
	}
}