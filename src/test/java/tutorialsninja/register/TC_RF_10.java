package tutorialsninja.register;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class TC_RF_10 {

	@Test
	public void verifyRegistringAccountUsingInvalidEmail() throws InterruptedException, IOException {

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("input-firstname")).sendKeys("dipali");
		driver.findElement(By.id("input-lastname")).sendKeys("patil");
		driver.findElement(By.id("input-email")).sendKeys("dipali");
		driver.findElement(By.id("input-telephone")).sendKeys("4456567654");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("1234567");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();

		Thread.sleep(3000);

		// TakesScreenshot
		File src1 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src1, new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png"));

		BufferedImage actual = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png"));
		BufferedImage expected = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Expected.png"));

		ImageDiffer imgDiffer = new ImageDiffer();
		ImageDiff imgDifference=imgDiffer.makeDiff(expected, actual);
		
		Boolean b=imgDifference.hasDiff();
		System.out.println(b);
		
		Assert.assertFalse(imgDifference.hasDiff());
		
		driver.quit();

	}
}
