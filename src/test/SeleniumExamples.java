package test;

import data.UserData;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;

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
    public void badSignIn2() throws InterruptedException {
        HomePage homePage = HomePage.visit(driver);

        String email = "stpcon@example.com";
        String password = "example";

        Thread.sleep(2000);

        driver.findElement(By.id("session_email")).sendKeys(email);
        driver.findElement(By.id("session_pass")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

    }
}