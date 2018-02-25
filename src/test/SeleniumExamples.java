package test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SeleniumExamples {

    @Test
    public void signIn() {
        System.setProperty("webdriver.chrome.driver", "lib/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://address-book-example.herokuapp.com");
        driver.findElement(By.id("sign-in")).click();

        String email = "saucecon@example.com";
        String password = "password";

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement emailElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("session_email")));

        emailElement.sendKeys(email);
        driver.findElement(By.id("session_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

        assertEquals("Address Book", driver.getTitle());

        driver.quit();
    }

}