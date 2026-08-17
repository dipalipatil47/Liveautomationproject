package tutorialsninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_005 {

	@Test
	public void verifyRegistringAccountWithoutFillFields() {

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("input-firstname")).sendKeys("dipali");
		driver.findElement(By.id("input-lastname")).sendKeys("patil");
		driver.findElement(By.id("input-email")).sendKeys(generateNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("456567654");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");

		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();

		driver.findElement(By.linkText("Continue")).click();
		driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Newsletter']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).isSelected());
	}

	public String generateNewEmail() {
		return new Date().toString().replaceAll(" ", "").replaceAll("\\:", "") + "@gmail.com";

	}
}
