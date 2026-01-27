package automate.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

public class DropDown {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.telerik.com/contact");
		driver.manage().window().maximize();
		// Select By Value
		WebElement drop1=driver.findElement(By.id("Dropdown-1"));
		Select select1=new Select(drop1);
		select1.selectByValue("Invoicing/Billing");
		// Select By Text
		WebElement drop2=driver.findElement(By.id("Dropdown-2"));
		Select select2=new Select(drop2);
		select2.selectByVisibleText("Testing Framework");
		driver.findElement(By.id("Textbox-1")).sendKeys("Dhivya");
		driver.findElement(By.id("Textbox-2")).sendKeys("Velumani");
		Thread.sleep(2000);
		// Select By Index
		WebElement drop3=driver.findElement(By.id("Country-1"));
		Select select3=new Select(drop3);
		select3.selectByIndex(1);
		// Close the driver
		driver.close();
		

	}

}
