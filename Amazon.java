package automate.testing;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
public class Amazon {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.get("https://www.amazon.in/");
		WebElement searchBox=driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("bags",Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.s-main-slot.s-result-list.s-search-results.sg-row")));
		By brandSection=By.xpath("//span[text()='Brands']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(brandSection));
	    By brandRefinement=By.xpath("//div[@id='brandsRefinements']//div[contains(@class,'a-checkbox a-checkbox-fancy s-navigation-checkbox aok-float-left')]");
		List<WebElement> brands=driver.findElements(brandRefinement);
		int count=0;
		for(int i=0;i<brands.size();i++) {
			if(count==5) break;
			try {
				brands=driver.findElements(brandRefinement);
				WebElement brand=brands.get(i);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",brand);
				wait.until(ExpectedConditions.elementToBeClickable(brand)).click();
				wait.until(ExpectedConditions.stalenessOf(brand));
				count++;
				
			}
			catch(Exception e) {
				System.out.println("Could not able to click brand index:"+i);
				e.printStackTrace();
			}
			System.out.println("5 brands selected successfully");
		}
}
}
