package test;

import data.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SeleniumExamples extends BaseTest {

    @Test(dataProvider = "validUser", dataProviderClass = UserData.class, retryAnalyzer = test.RetryAnalyzer.class)
    public void signIn(String email, String password) throws InterruptedException {
        HomePage homePage = HomePage.visit(driver);
        homePage.navigateToSignIn();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.signIn(email, password);

        assertTrue(homePage.isSignedIn(email));
    }

    @Test(dataProvider = "validUser", dataProviderClass = UserData.class, retryAnalyzer = test.RetryAnalyzer.class)
    public void openBrowser1() throws InterruptedException {
        HomePage homePage = HomePage.visit(driver);

        String email = "stpcon@example.com";
        String password = "example";

        Thread.sleep(2000);

        driver.findElement(By.id("session_email")).sendKeys(email);
        driver.findElement(By.id("session_pass")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

    }

    @Test(dataProvider = "validUser", dataProviderClass = UserData.class, retryAnalyzer = test.RetryAnalyzer.class)
    public void openBrowser2() throws InterruptedException, MalformedURLException {
        public static final String USERNAME = System.getenv("SAUCE_USERNAME");
        public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
        public static final String URL = "https://" + USERNAME + ":"
                + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
        String email = "stpcon@example.com";
        String password = "example";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Win 10");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "latest");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        driver.get("http://address-book-example.herokuapp.com");

        driver.findElement(By.id("sign-in")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement emailInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("session_email")));
        emailInput.sendKeys(email);
        driver.findElement(By.id("session_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

        assertEquals("Address Book - Sign In", driver.getTitle());
    }
}