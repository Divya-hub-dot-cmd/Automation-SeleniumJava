package automate.testing;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.asserts.*;
import java.util.List;
import org.openqa.selenium.interactions.Actions;

public class LeafGroundInputTest {
	ChromeDriver driver;
	@BeforeClass
	public void setup() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://leafground.com/input.xhtml");
		
	}
	@Test(priority=1)
	public void TC_LG_001_enterName(){
		WebElement nameBox=driver.findElement(By.xpath("//input[@placeholder='Babu Manickam']"));
		nameBox.sendKeys("TestLeaf");
		Assert.assertEquals(nameBox.getAttribute("value"), "TestLeaf");
		
	}
	@Test(priority=2)
	public void TC_LG_002_appendCountry() {
		WebElement append=driver.findElement(By.xpath("//input[contains(@value,'Chennai')]"));
		append.sendKeys("India");
		Assert.assertTrue(append.getAttribute("value").contains("India"));
	}
	@Test(priority=3)
	public void TC_LG_003_verifyDiabled() {
		WebElement disabled=driver.findElement(By.xpath("//input[@placeholder='Disabled']"));
		Assert.assertFalse(disabled.isEnabled());
	}
	@Test(priority=4)
	public void TC_LG_004_clearBox() {
		WebElement clearBox=driver.findElement(By.xpath("//input[contains(@value,'Can you clear me, please?')]"));
		clearBox.clear();
		Assert.assertEquals(clearBox.getAttribute("value"), "");
	}
	@Test(priority=5)
	public void TC_LG_005_verifyTabFunction() {
		WebElement TabFunction=driver.findElement(By.xpath("//input[@placeholder='Your email and tab']"));
		TabFunction.sendKeys("ezhilvel");
		TabFunction.sendKeys(Keys.TAB);
		WebElement nextElement=driver.switchTo().activeElement();
		Assert.assertNotEquals(TabFunction,nextElement);
	}

	
	
	@Test(priority = 6)
	public void TC_LG_006_autoCompleteSelection() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    By autoInput = By.xpath("//input[contains(@id,'auto-complete_input')]");
	    WebElement input = wait.until(
	            ExpectedConditions.elementToBeClickable(autoInput));

	    input.clear();
	    input.sendKeys("a");

	    By suggestionItems = By.xpath("//span[contains(@id,'auto-complete_panel')]//li[contains(@class,'ui-autocomplete-item')]");
	    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(suggestionItems,2));

	    List<WebElement> options = driver.findElements(suggestionItems);
	    WebElement third = options.get(2);

	    String expected = third.getText();
	    third.click();
	    By selectedToken=By.xpath("//li[contains(@class,'ui-autocomplete-token')]");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(selectedToken));
	    String actual =driver.findElement(selectedToken).getText();
	    Assert.assertEquals(actual, expected);

	
	}
	@Test(priority = 7)
	public void TC_LG_007_retrieveTypedText() {

	    WebElement retrieveBox = driver.findElement(
	            By.xpath("//input[contains(@value,'superb')]"));

	    String actualText = retrieveBox.getAttribute("value");

	    Assert.assertTrue(actualText.contains("superb"));
	}
	@Test(priority=8)
	public void TC_LG_008_textDescription() {
		WebElement textArea=driver.findElement(By.xpath("//textarea[@placeholder='About yourself']"));
		String description="I am learning Selenium";
		textArea.sendKeys(description);
		String actual=textArea.getAttribute("value");
		Assert.assertEquals(actual,description);
	}
	@Test(priority=9)
	public void TC_LG_009_italicEditor() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement ital=driver.findElement(By.xpath("//button[contains(@class,'ql-italic')]"));
		ital.click();
		WebElement editor=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'ql-editor')]")));
		String Text="ItalicText";
		editor.sendKeys(Text);
		String format=editor.getAttribute("innerHTML");
		Assert.assertTrue(format.contains("<em>"),"Text is not italic formatted");
	}
	 
	
	@Test(priority=10)
	public void TC_LG_010_labelPosition() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement label=driver.findElement(By.id("j_idt106:float-input"));
		((org.openqa.selenium.JavascriptExecutor) driver)
        .executeScript("arguments[0].scrollIntoView({block:'center'});", label);

		String beforeClass=label.getAttribute("class");
		label.click();
		wait.until(ExpectedConditions.attributeContains(label, "class", "ui-state-focus"));
		String afterClass=label.getAttribute("class");
		Assert.assertNotEquals(beforeClass, afterClass);
		Assert.assertTrue(afterClass.contains("ui-state-focus"));
	}
	@Test(priority=11)
	public void verifyDateSelection() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement datePicker=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='j_idt106:j_idt116_input']")));
		String expectedDate="21/02/2026";
		datePicker.clear();
		datePicker.sendKeys(expectedDate);
		datePicker.sendKeys(Keys.TAB);
		String actualValue=datePicker.getAttribute("value");
		Assert.assertEquals(actualValue, expectedDate);
		
	}

	@Test(priority=12)
	public void spinnerIncrement() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement spinnerIncrement=driver.findElement(By.xpath("//input[contains(@class,'ui-spinner-input')]"));
		spinnerIncrement.clear();
		spinnerIncrement.sendKeys("5");
		int before=Integer.parseInt(spinnerIncrement.getAttribute("value"));
		WebElement spinUp=driver.findElement(By.xpath("//a[contains(@class,'ui-spinner-up')]"));
		spinUp.click();
		int after=Integer.parseInt(spinnerIncrement.getAttribute("value"));
		Assert.assertEquals(after,before+1);
	}
	@Test(priority=13)
	public void verifySliderMovement() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement sliderInput=wait.until(ExpectedConditions.elementToBeClickable(By.id("j_idt106:slider")));
		sliderInput.clear();
		sliderInput.sendKeys("50");
		sliderInput.sendKeys(Keys.ENTER);
		WebElement sliderHandle=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'ui-slider-handle')]")));
		String style=sliderHandle.getAttribute("style");
		double percent =Double.parseDouble(style.replace("left:","").replace("%;","").trim());
		System.out.println("Slider Handle: "+style);
		Assert.assertTrue(percent>=49 &&percent<=51);

	}
	@Test(priority=14)
	public void TC_LG_014_verifycustomToolBarTool() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement boldButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='ql-bold']")));
		boldButton.click();
		WebElement editor=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'ql-editor')]")));
		String text="BoldText";
		editor.sendKeys("text");
		String html=editor.getAttribute("innerHTML");
		System.out.println("Editor HTML:"+html);
		Assert.assertTrue(html.contains("<strong>"),"Text is not in bold format");
	}
	@Test(priority=15)
	public void TC_LG_015_verifyKeyboardPopup() throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));

	    WebElement keyboardInput = driver.findElement(
	            By.xpath("//input[contains(@class,'ui-keyboard-input')]"));

	    keyboardInput.click();
	    By keyboardPopup=By.xpath("//div[contains(@class,'keypad-popup')]");
	    WebElement popup=wait.until(ExpectedConditions.visibilityOfElementLocated(keyboardPopup));

	    Assert.assertTrue(popup.isDisplayed(),"Keyboard did not appear");
	}
	@Test(priority=16)
	public void TC_LG_006_ageValidation() {
	    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));

	    WebElement ageValiadation=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id,'thisform:age')]")));
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].scrollIntoView({block:'center'});", ageValiadation);
	    
	    

	    ageValiadation.click();             // leave empty
	    ageValiadation.sendKeys(Keys.ENTER);   // trigger blur
	    By errorMessage=By.xpath("//div[contains(@id,'thisform:j_idt110')]");
	    WebElement messageElement=wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));

	    String message = messageElement.getText();

	    Assert.assertFalse(message.trim().isEmpty(),"Validation message not displayed");
	}
	

	
	

}
