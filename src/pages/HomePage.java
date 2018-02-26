package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
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
        click(signInLink);
    }

    public Boolean isSignedIn(String email) {
        return getText(user).equals(email);
    }
}
