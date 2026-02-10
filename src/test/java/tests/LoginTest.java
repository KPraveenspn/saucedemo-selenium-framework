package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

/**
 * LoginTest - Contains test cases for login functionality
 */
public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify login with valid credentials")
    public void testValidLogin() {
        // Create LoginPage object
        LoginPage loginPage = new LoginPage(driver);

        // Perform login
        loginPage.login("standard_user", "secret_sauce");

        // Assertion - verify user is on products page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), 
            "Login failed - User not redirected to products page");

        System.out.println("✅ Valid login test passed!");
    }

    @Test(priority = 2, description = "Verify login with invalid credentials")
    public void testInvalidLogin() {
        // Create LoginPage object
        LoginPage loginPage = new LoginPage(driver);

        // Perform login with wrong credentials
        loginPage.login("invalid_user", "wrong_password");

        // Assertions - verify error message appears
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message not displayed for invalid login");

        String errorText = loginPage.getErrorMessage();
        Assert.assertTrue(errorText.contains("Username and password do not match"), 
            "Error message text is incorrect");

        System.out.println("✅ Invalid login test passed!");
    }

    @Test(priority = 3, description = "Verify login with empty credentials")
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        // Click login without entering credentials
        loginPage.clickLoginButton();

        // Verify error message
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message not displayed for empty credentials");

        String errorText = loginPage.getErrorMessage();
        Assert.assertTrue(errorText.contains("Username is required"), 
            "Error message text is incorrect");

        System.out.println("✅ Empty credentials test passed!");
    }
}
