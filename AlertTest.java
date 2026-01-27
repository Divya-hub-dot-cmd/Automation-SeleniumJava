package automate.testing;
import java.time.Duration;
import org.openqa.selenium.Alert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;







public class AlertTest {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		// Navigate to Url
		driver.get("https://codepen.io/unknownUser7/pen/ZEdGLbq");
	

		

		WebElement fr=driver.findElement(By.id("result"));
		driver.switchTo().frame(fr);

		// Enter username and password
		driver.findElement(By.id("username")).sendKeys("user");
		driver.findElement(By.id("password")).sendKeys("pass");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	
		// Handle the alert and retrieve the text
		
		org.openqa.selenium.Alert alerts=driver.switchTo().alert();
	    String a=alerts.getText();
		System.out.println(a);
		
		alerts.accept();
		String message="Login successful!";
		//verify welcome text
		if(a.contains(message)) {
			System.out.println("you have logged in");
			
		}
		else {
			System.out.println("you have not logged in");
		}
		//handle logout alert
		Thread.sleep(500);

		try {
		    Alert unexpected = driver.switchTo().alert();
		    unexpected.accept();
		} catch (Exception e) {
		    // no alert present – safe to continue
		}

		
		driver.findElement(By.xpath("//button[text()='Logout']")).click();
		org.openqa.selenium.Alert alert1=driver.switchTo().alert();
		System.out.println(alert1.getText());
		alert1.accept();
		driver.close();
		
		
		
	}
}
