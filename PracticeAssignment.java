package automate.testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PracticeAssignment {

    // Test Case 1: Positive Login
    @Test(priority = 1)
    public void positiveLoginTest() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("logged-in-successfully")
        );

        Assert.assertTrue(
                driver.findElement(By.linkText("Log out")).isDisplayed()
        );

        driver.quit();
    }

    // Test Case 2: Negative Username
    @Test(priority = 2, dependsOnMethods = "positiveLoginTest")
    public void negativeUsernameTest() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.id("username")).sendKeys("incorrectUser");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();

        Assert.assertEquals(
                driver.findElement(By.id("error")).getText(),
                "Your username is invalid!"
        );

        driver.quit();
    }

    // Test Case 3: Negative Password
    @Test(priority = 3, dependsOnMethods = "negativeUsernameTest")
    public void negativePasswordTest() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("incorrectPassword");
        driver.findElement(By.id("submit")).click();

        Assert.assertEquals(
                driver.findElement(By.id("error")).getText(),
                "Your password is invalid!"
        );

        driver.quit();
    }
}


