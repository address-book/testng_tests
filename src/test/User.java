package test;

import data.UserData;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

public class User extends BaseTest {

    @Test
    public void signUp() {
        driver.get("http://address-book-example.herokuapp.com/sign_up");

        HashMap newUser = (HashMap) new UserData().newUser();
        String emailAddress = (String) newUser.get("emailAddress");
        String password = (String) newUser.get("password");

        driver.findElement(By.id("user_email")).sendKeys(emailAddress);
        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

        assertEquals("Address Book", driver.getTitle());
    }

}