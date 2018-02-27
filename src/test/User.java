package test;

import data.UserData;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class User extends BaseTest {
    private UserData userData = new UserData();

    @Test
    public void signUp() {
        driver.get("http://address-book-example.herokuapp.com/sign_up");

        HashMap newUser = (HashMap) userData.newUser();
        String emailAddress = (String) newUser.get("emailAddress");
        String password = (String) newUser.get("password");

        driver.findElement(By.id("user_email")).sendKeys(emailAddress);
        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

        assertEquals("Address Book", driver.getTitle());
    }

    @Test
    public void signUpFailBlankPassword() {
        driver.get("http://address-book-example.herokuapp.com/sign_up");

        Map<String, String> requiredData = new HashMap<String, String>();
        requiredData.put("password", "");
        HashMap newUser = (HashMap) userData.newUser(requiredData);
        String emailAddress = (String) newUser.get("emailAddress");
        String password = (String) newUser.get("password");

        driver.findElement(By.id("user_email")).sendKeys(emailAddress);
        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

        assertEquals("Address Book - Sign Up", driver.getTitle());
    }

}