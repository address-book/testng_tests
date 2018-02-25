package test;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SeleniumExamples {

    @Test
    public void openBrowser() {
        System.setProperty("wdm.targetPath", "lib/drivers/auto/");
        ChromeDriverManager.getInstance().setup();

        WebDriver driver = new ChromeDriver();
        driver.quit();
    }

}