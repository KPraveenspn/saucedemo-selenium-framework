package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

/**
 * BaseTest - Parent class for all test classes
 * Handles WebDriver initialization and cleanup
 */
public class BaseTest {

    // WebDriver instance - available to all child test classes
    protected WebDriver driver;

    /**
     * Setup method - runs BEFORE each test method
     * Initializes WebDriver based on browser from config.properties
     */
    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getBrowser();

        // Initialize WebDriver based on browser type
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                
             // Chrome options to disable password manager
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.addArguments("--disable-save-password-bubble");
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                
                // Disable password manager and autofill
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("autofill.profile_enabled", false);
                chromeOptions.setExperimentalOption("prefs", prefs);

                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        // Configure WebDriver
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));

        // Navigate to application URL
        driver.get(ConfigReader.getAppURL());
    }
    

    /**
     * Teardown method - runs AFTER each test method
     * Closes the browser and quits WebDriver
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
