package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver browser;

    private By signInLink = By.cssSelector("a[data-test=sign-in]");
    private By user = By.cssSelector("span[data-test=current-user]");

    public static HomePage visit(WebDriver browser) {
        HomePage page = new HomePage(browser);
        browser.get("http://address-book-example.herokuapp.com");
        return page;
    }

    private HomePage(WebDriver browser) {
        this.browser = browser;
    }

    public void navigateToSignIn() {
        browser.findElement(signInLink).click();
    }

    public Boolean isSignedIn(String email) {
        WebDriverWait wait = new WebDriverWait(browser, 10);

        WebElement currentUser = wait.until(
                ExpectedConditions.presenceOfElementLocated(user));

        return currentUser.getText().equals(email);
    }
}
