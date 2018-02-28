package test;

import com.github.javafaker.Faker;
import data.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class Exercise extends BaseTest {
    private UserData userData = new UserData();

    @Test
    public void allTheThings() {
        driver.get("http://address-book-example.herokuapp.com");

        // Sign Up

        driver.findElement(By.cssSelector("a[data-test=sign-in]")).click();

        WebDriverWait wait = new WebDriverWait(driver, 2);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("a[data-test=sign-up]"))).click();

        Faker faker = new Faker();

        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("user_email"))).sendKeys(email);

        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

        String signedUpUser = driver.findElement(By.cssSelector("span[data-test=current-user]")).getText();

        assertEquals(email, signedUpUser);

        // Log Out

        driver.findElement(By.cssSelector("a[data-test=sign-out]")).click();

        assertTrue(driver.findElements(By.cssSelector("span[data-test=current-user]")).isEmpty());

        // Log In

        driver.findElement(By.id("session_email")).sendKeys(email);
        driver.findElement(By.id("session_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

        String loggedInUser = driver.findElement(By.cssSelector("span[data-test=current-user]")).getText();

        assertEquals(email, loggedInUser);

        // Create Address

        driver.findElement(By.cssSelector("a[data-test='addresses']")).click();

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("a[data-test='create']"))).click();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String streetAddress = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String zipCode = faker.address().zipCode();

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("address_first_name"))).sendKeys(firstName);

        driver.findElement(By.id("address_last_name")).sendKeys(lastName);
        driver.findElement(By.id("address_street_address")).sendKeys(streetAddress);
        driver.findElement(By.id("address_city")).sendKeys(city);
        driver.findElement(By.id("address_state")).sendKeys(state);
        driver.findElement(By.id("address_zip_code")).sendKeys(zipCode);

        driver.findElement(By.cssSelector("input[data-test='submit']")).click();

        String createMessage = driver.findElement(By.cssSelector("div[data-test='notice']")).getText();

        assertEquals("Address was successfully created.", createMessage);

        assertEquals(firstName, driver.findElement(By.cssSelector("span[data-test='first_name']")).getText());
        assertEquals(lastName, driver.findElement(By.cssSelector("span[data-test='last_name']")).getText());
        assertEquals(streetAddress, driver.findElement(By.cssSelector("span[data-test='street_address']")).getText());
        assertEquals(city, driver.findElement(By.cssSelector("span[data-test='city']")).getText());
//        assertEquals(state, driver.findElement(By.cssSelector("span[data-test='state']")).getText());
        assertEquals(zipCode, driver.findElement(By.cssSelector("span[data-test='zip_code']")).getText());

        // Edit Address

        driver.findElement(By.cssSelector("a[data-test='list']")).click();

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("tbody td:nth-child(6) a"))).click();

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("address_first_name"))).clear();

        String editedFirst = faker.name().firstName();
        String editedLast = faker.name().lastName();

        driver.findElement(By.id("address_first_name")).sendKeys(editedFirst);

        driver.findElement(By.id("address_last_name")).clear();
        driver.findElement(By.id("address_last_name")).sendKeys(editedLast);

        driver.findElement(By.cssSelector("input[data-test='submit']")).click();

        String editMessage = driver.findElement(By.cssSelector("div[data-test='notice']")).getText();

        assertEquals("Address was successfully updated.", editMessage);

        assertEquals(editedFirst, driver.findElement(By.cssSelector("span[data-test='first_name']")).getText());
        assertEquals(editedLast, driver.findElement(By.cssSelector("span[data-test='last_name']")).getText());
        assertEquals(streetAddress, driver.findElement(By.cssSelector("span[data-test='street_address']")).getText());
        assertEquals(city, driver.findElement(By.cssSelector("span[data-test='city']")).getText());
//        assertEquals(state, driver.findElement(By.cssSelector("span[data-test='state']")).getText());
        assertEquals(zipCode, driver.findElement(By.cssSelector("span[data-test='zip_code']")).getText());

        // Delete Address

        driver.findElement(By.cssSelector("a[data-test='list']")).click();

        String foundFirst = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.tagName("td"))).getText();

        assertEquals(editedFirst, foundFirst);
        assertEquals(editedLast, driver.findElements(By.tagName("td")).get(1).getText());

        driver.findElement(By.cssSelector("td:nth-child(7) a")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        String deleteMessage = driver.findElement(By.cssSelector("div[data-test='notice']")).getText();

        assertEquals("Address was successfully destroyed.", deleteMessage);

        assertTrue(driver.findElements(By.cssSelector("tbody tr")).isEmpty());
    }

}