package test;

import data.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SeleniumExamples extends BaseTest {

    @Test(dataProvider = "validUser", dataProviderClass = UserData.class, retryAnalyzer = test.RetryAnalyzer.class)
    public void signIn(String email, String password) {
        HomePage homePage = HomePage.visit(driver);
        homePage.navigateToSignIn();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.signIn(email, password);

        assertTrue(homePage.isSignedIn());
    }

}