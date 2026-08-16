package tutorialsninja.register;

import java.time.Duration;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_002 {

	@Test
	public void verifyConfirmationEmail() {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		String appPassword = "hlfh bzss fcbg blms";
		String email = "dipalipatil8390@gmail.com";

		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//span[text()='Hello, sign in']")).click();
		// driver.findElement(By.linkText("Conditions of Use")).click();
		driver.findElement(By.xpath("//input[@id='ap_email_login']")).sendKeys("dipalipatil8390@gmail.com");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		driver.findElement(By.xpath("//a[@id='auth-fpp-link-bottom']")).click();
		driver.findElement(By.xpath("//input[@id='continue']")).click();

		Properties properties = new Properties();

		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imaps.host", "imap.gmail.com");
		properties.put("mail.imaps.port", "993");
		properties.put("mail.imaps.ssl.enable", "true");

		String expectedSubject = "amazon.in: Password recovery";
		String expectedFromEmail="\"amazon.in\" <account-update@amazon.in>";
		String expectedBodyContent= "Someone is attempting to reset the password of your account";
		

		try {

			// Create Gmail session
			Session session = Session.getInstance(properties);

			// Connect to Gmail
			Store store = session.getStore("imaps");

			store.connect("imap.gmail.com", email, appPassword);

			System.out.println("Gmail connected successfully!");

			// Open Inbox
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			// Get all emails
			Message[] messages = inbox.getMessages();

			System.out.println("Total emails: " + messages.length);

			// Read latest email
			if (messages.length > 0) {

				Message message = messages[messages.length - 1];
				Assert.assertEquals(message.getSubject(),expectedSubject);

				// From
				Address[] from = message.getFrom();

				if (from != null && from.length > 0) {
					Assert.assertEquals(from[0].toString(),expectedFromEmail);
					//System.out.println("From: " + from[0]);
				}

				// Body
				String actualBodyContent = getEmailBody(message);
				Assert.assertTrue(
				        actualBodyContent.contains(expectedBodyContent),
				        "Expected body content was not found in the email"
				);
				//System.out.println("Body:");
				System.out.println(getEmailBody(message));
			}

			// Close connection
			inbox.close(false);
			store.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get email body
	private static String getEmailBody(Part part) throws Exception {

		if (part.isMimeType("text/plain")) {

			return part.getContent().toString();

		} else if (part.isMimeType("text/html")) {

			return part.getContent().toString();

		} else if (part.isMimeType("multipart/*")) {

			Multipart multipart = (Multipart) part.getContent();

			for (int i = 0; i < multipart.getCount(); i++) {

				BodyPart bodyPart = multipart.getBodyPart(i);

				String body = getEmailBody(bodyPart);

				if (body != null && !body.isEmpty()) {
					return body;
				}
			}
		}

		return "";

	}

}
