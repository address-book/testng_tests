package test;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class BaseTest {
    WebDriver driver;

    private String useSauce = System.getenv("USE_SAUCE");

    private static DesiredCapabilities createCapabilities(String value) throws FileNotFoundException, YamlException {
        YamlReader reader = new YamlReader(new FileReader("src/config/data.yml"));
        HashMap map = (HashMap) reader.read();
        HashMap config = (HashMap) map.get(value);

        String browser = config == null ? "chrome" : (String) config.get("browser");
        String version = config == null ? "63.0" : (String) config.get("version");
        String platform = config == null ? "macOS 10.12" : (String) config.get("platform");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("version", version);

        return capabilities;
    }

    @BeforeMethod
    public void setup(Method method) throws MalformedURLException, FileNotFoundException, YamlException {
        if (useSauce == null) {
            System.setProperty("webdriver.chrome.driver", "lib/drivers/chromedriver");
            driver = new ChromeDriver();
        } else {
            String USER = System.getenv("SAUCE_USERNAME");
            String KEY = System.getenv("SAUCE_ACCESS_KEY");
            String URL = "https://" + USER + ":" + KEY + "@ondemand.saucelabs.com/wd/hub";
            String buildEnv = System.getenv("BUILD_TAG");

            String configEnv = System.getenv("PLATFORM_CONFIG");
            DesiredCapabilities caps = createCapabilities(configEnv);

            caps.setCapability("name", method.getName());
            if (buildEnv != null) {
                caps.setCapability("build", buildEnv);
            }

            driver = new RemoteWebDriver(new URL(URL), caps);
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String status = result.isSuccess() ? "passed" : "failed";

        if (useSauce == null) {
            System.out.println(status);
        } else {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("sauce:job-result=" + status);
        }

        driver.quit();
    }

}
