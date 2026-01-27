package practice;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FaceBookAutomation {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/login.php");

        List<WebElement> languages =
                driver.findElements(By.xpath("//ul[contains(@class,'uiList')]//a"));

        for (int i = 0; i < languages.size(); i++) {
            WebElement lang = languages.get(i);
            String languageName = lang.getText();
            String languageUrl = lang.getAttribute("href");

            if (!languageName.isEmpty()) {
                System.out.println((i + 1) + ". " + languageName + " --> " + languageUrl);
            }
        }

        driver.quit();
    }
}
