package test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import java.util.function.Function;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class SeleniumExamples {

    @Test
    public void signIn() {
        System.setProperty("webdriver.chrome.driver", "lib/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://address-book-example.herokuapp.com");
        driver.findElement(By.id("sign-in")).click();

        String email = "saucecon@example.com";
        String password = "password";

        Wait fluentWait = new FluentWait(driver)
                .withTimeout(5, SECONDS)
                .pollingEvery(500, MICROSECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement emailElement = (WebElement) fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver browser) {
                return browser.findElement(By.id("session_email"));
            }
        });

        emailElement.sendKeys(email);
        driver.findElement(By.id("session_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

        driver.quit();
    }

}