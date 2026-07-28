package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_RF_002 {
	
	public static void main(String[] args) {
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//span[text()='Hello, sign in']")).click();
		driver.findElement(By.linkText("Conditions of Use")).click();
		
	}

}
