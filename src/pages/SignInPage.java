package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private WebDriver browser;

    public SignInPage(WebDriver browser) {
        this.browser = browser;
    }

    public void signIn(String email, String password) {
        WebDriverWait wait = new WebDriverWait(browser, 10);

        WebElement emailElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(emailField));

        emailElement.sendKeys(email);
        browser.findElement(passwordField).sendKeys(password);
        browser.findElement(commitButton).click();
    }
}


