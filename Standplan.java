package automate.testing;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Standplan {
	public static void main(String[] args) throws InterruptedException {
		
	
	ChromeDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.mapz.com/map");
	WebElement ele=driver.findElement(By.xpath("//a[contains(@class,'show-modal-dialog') and contains(text(),'Draw')]"));
	ele.click();
	WebElement email=driver.findElement(By.xpath("//input[@id='email']"));
	
	((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();",email);
	String message=email.getAttribute("validationMessage");
	System.out.println("The Validation message is: " + message);
	
	}

}
