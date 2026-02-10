package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;

/**
 * LoginPage - Page Object Model for SauceDemo Login Page
 * Contains locators and methods for login functionality
 */
public class LoginPage {

    // WebDriver instance
    private WebDriver driver;

    // ===== Locators (Using By class) =====
    // Why By locators? - Can be reused, easier to maintain
    
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    // ===== Constructor =====
    // Receives WebDriver from test class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // ===== Page Actions (Methods) =====

    /**
     * Enter username in username field
     * @param username - username to enter
     */
    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    /**
     * Enter password in password field
     * @param password - password to enter
     */
    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    /**
     * Click on login button
     */
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    /**
     * Complete login action (combines all steps)
     * This is a higher-level method for convenience
     * @param username - username to login
     * @param password - password to login
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Get error message text when login fails
     * @return error message text
     */
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    /**
     * Check if error message is displayed
     * @return true if error message is visible, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get current page title
     * @return page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}
