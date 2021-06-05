package acedemy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest {

	@Test
	public void loginTest() {

		System.out.println("Strating testing");

		System.setProperty("webdriver.chrome.driver",
				"D:\\java-eclipse\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);

		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");

		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		loginButton.click();

		// verification

		// new url
		String actual = driver.getCurrentUrl();
		String expected = "http://the-internet.herokuapp.com/secure";

		Assert.assertEquals(actual, expected, "Url is not correct");

		// logout button
		WebElement logout = driver.findElement(By.cssSelector("a[class='button secondary radius']"));
		Assert.assertTrue(logout.isDisplayed(), "logout button is not displayed");

		// loged in message
		WebElement loggedin = driver.findElement(By.xpath("//div[@class='flash success']"));
		String actualMessage = loggedin.getText();
		String expectedMessage = "You logged into a secure area!";

		// Assert.assertEquals(actualMessage,expectedMessage,"not matching");
		Assert.assertTrue(actualMessage.contains(expectedMessage), "login message is not displayed\nActual Message: "
				+ actualMessage + "\nExpected Message: " + expectedMessage);
		driver.quit();

	}
}
