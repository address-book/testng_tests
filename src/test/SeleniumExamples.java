package test;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SeleniumExamples {

    @Test
    public void signIn() {
        System.setProperty("webdriver.chrome.driver", "lib/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://address-book-example.herokuapp.com");
        driver.findElement(By.id("sign-in")).click();

        String email = "saucecon@example.com";
        String password = "password";

        driver.findElement(By.id("session_email")).sendKeys(email);
        driver.findElement(By.id("session_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

        driver.quit();
    }

}