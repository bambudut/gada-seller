import config.GlobalVariable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {

    WebDriver driver;
    WebDriverWait webDriverWait;
    GlobalVariable variable = new GlobalVariable();
    String driverPath = "/driver/chrome/chromedriver_mac";

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + driverPath);
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 1);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.get(variable.baseUrl);
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, variable.defaultTimeout);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}