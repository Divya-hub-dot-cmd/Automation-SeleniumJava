package automate.testing;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.Set;

public class WindowHandle {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		// Open Amazon 
		driver.get("https://www.amazon.in");
		// Search Mobiles
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Mobiles");
		driver.findElement(By.id("nav-search-submit-button")).click();
		// Collect and Sort
		List<WebElement>prices=driver.findElements(By.className("a-price-whole"));
		int[] pricewhole=new int[prices.size()];
		for(int i=0;i<pricewhole.length;i++) {
			String pricetext=prices.get(i).getText();
			String replace=pricetext.replaceAll(",","");
			int parse=Integer.parseInt(replace);
			pricewhole[i]=parse;
		}
			Arrays.sort(pricewhole);
			for(int price :pricewhole) {
				System.out.println(price);
				
				
			}
			Thread.sleep(2000);
		// Click to the first product
		WebElement mobile = driver.findElement(
				    By.xpath("//a[contains(@class,'a-link-normal s-line-clamp-2 puis-line-clamp-3-for-col-4-and-8 s-link-style a-text-normal')]")
				);

		mobile.click();
		// Switch to NewTab
		
		Set<String>tabs=driver.getWindowHandles();
		for(String tab:tabs) {
			driver.switchTo().window(tab);
		}
		WebElement addtocart=driver.findElement(By.id("add-to-cart-button"));
		// Actions actions=new Actions(driver);
		//actions.moveToElement(addtocart).perform();
		//System.out.println("Scrolled to Add To Cart");
		// Scroll to Addtocart using Javascript
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", addtocart);
		// Click Addtocart using Javascript
		js.executeScript("arguments[0].click();", addtocart);
		System.out.println("Scrolled to Add To Cart");
				
			
		}
		
		

	}


