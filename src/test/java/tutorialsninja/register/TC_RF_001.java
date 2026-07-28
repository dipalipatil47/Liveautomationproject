package tutorialsninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_001 {

	@Test
	public void verifyRegisterWithManadatoryField() {

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("input-firstname")).sendKeys("dipali");
		driver.findElement(By.id("input-lastname")).sendKeys("patil");
		driver.findElement(By.id("input-email")).sendKeys(generateEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("456567654");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		String expectedHeading = "Your Account Has Been Created!";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success'] //h1")).getText(),
				expectedHeading);

		String ActualproperDetails1 = "Congratulations! Your new account has been successfully created!";
		String ActualproperDetails2 = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String ActualproperDetails3 = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String ActualproperDetails4 = "contact us";
    
		String expectedProperDetails= driver.findElement(By.id("content")).getText();
		
		Assert.assertTrue(expectedProperDetails.contains(ActualproperDetails1));
		Assert.assertTrue(expectedProperDetails.contains(ActualproperDetails2));
		Assert.assertTrue(expectedProperDetails.contains(ActualproperDetails3));	
		Assert.assertTrue(expectedProperDetails.contains(ActualproperDetails4));
		
		driver.findElement(By.xpath("//a[text()='Continue']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		driver.quit();
	}

	public String generateEmail() {
		return new Date().toString().replaceAll(" ", "").replaceAll("\\:", "") + "@gmail.com";

	}

}
