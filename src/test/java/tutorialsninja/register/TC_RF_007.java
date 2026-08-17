package tutorialsninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_007 {

	@Test
	public void verifyRegistringAccountByRightSide() {

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo");

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Register Account']")).isDisplayed());

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Login']")).click();
		driver.findElement(By.xpath("//div[@class='well']//a[text()='Continue']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Register Account']")).isDisplayed());

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Login']")).click();

		driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Register Account']")).isDisplayed());
	}

	public String generateNewEmail() {
		return new Date().toString().replaceAll(" ", "").replaceAll("\\:", "") + "@gmail.com";

	}
}
