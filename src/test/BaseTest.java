package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    WebDriver driver;

    private String useSauce = System.getenv("USE_SAUCE");

    @BeforeMethod
    public void setup(Method method) throws MalformedURLException {
        if (useSauce == null) {
            System.setProperty("webdriver.chrome.driver", "lib/drivers/chromedriver");
            driver = new ChromeDriver();
        } else {
            String USER = System.getenv("SAUCE_USERNAME");
            String KEY = System.getenv("SAUCE_ACCESS_KEY");
            String URL = "https://" + USER + ":" + KEY + "@ondemand.saucelabs.com/wd/hub";
            String browserEnv = System.getenv("BROWSER");
            String platformEnv = System.getenv("PLATFORM");
            String versionEnv = System.getenv("VERSION");
            String buildEnv = System.getenv("BUILD_TAG");

            String browser = browserEnv == null ? "chrome" : browserEnv;
            String version = versionEnv == null ? "63.0" : versionEnv;
            String platform = platformEnv == null ? "macOS 10.12" : platformEnv;

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", browser);
            caps.setCapability("platform", platform);
            caps.setCapability("version", version);

            caps.setCapability("name", method.getName());
            if (buildEnv != null) {
                caps.setCapability("build", buildEnv);
            }

            driver = new RemoteWebDriver(new URL(URL), caps);
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}


