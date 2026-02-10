package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;

/**
 * ProductsTest - Test cases for Products Page functionality
 */
public class ProductsTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void loginToApp() {
        // Login before each test (required to access products page)
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        productsPage = new ProductsPage(driver);
    }

    @Test(priority = 1, description = "Verify products page is displayed after login")
    public void testProductsPageDisplayed() {
        // Verify page title
        String pageTitle = productsPage.getPageTitle();
        Assert.assertEquals(pageTitle, "Products", 
            "Products page title is incorrect");

        // Verify URL contains inventory
        String currentUrl = productsPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), 
            "URL does not contain inventory.html");

        // Verify products container is visible
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), 
            "Products page is not displayed");

        System.out.println("✅ Products page verification passed!");
    }

    @Test(priority = 2, description = "Verify product count on page")
    public void testProductCount() {
        int productCount = productsPage.getProductCount();
        
        // SauceDemo has 6 products
        Assert.assertEquals(productCount, 6, 
            "Product count is incorrect");

        System.out.println("✅ Product count: " + productCount);
    }

    @Test(priority = 3, description = "Verify product names are displayed")
    public void testProductNamesDisplayed() {
         List<String> productNames = productsPage.getAllProductNames();

        // Verify at least one product exists
        Assert.assertFalse(productNames.isEmpty(), 
            "No products found on page");

        // Verify specific product exists
        Assert.assertTrue(productNames.contains("Sauce Labs Backpack"), 
            "Backpack product not found");

        System.out.println("✅ Product names: " + productNames);
    }

    @Test(priority = 4, description = "Verify cart badge appears after adding product")
    public void testAddProductToCart() {
        // Initially cart badge should not be visible
        Assert.assertFalse(productsPage.isCartBadgeDisplayed(), 
            "Cart badge should not be visible initially");

        // Add one product
        productsPage.addBackpackToCart();

        // Verify cart badge appears with count "1"
        Assert.assertTrue(productsPage.isCartBadgeDisplayed(), 
            "Cart badge not displayed after adding product");

        String cartCount = productsPage.getCartItemCount();
        Assert.assertEquals(cartCount, "1", 
            "Cart count is incorrect");

        System.out.println("✅ Product added to cart successfully! Count: " + cartCount);
    }

    @Test(priority = 5, description = "Verify adding multiple products updates cart count")
    public void testAddMultipleProducts() {
        // Add 3 products
        productsPage.addBackpackToCart();
        productsPage.addBikeLightToCart();
        productsPage.addBoltTshirtToCart();

        // Verify cart count is 3
        String cartCount = productsPage.getCartItemCount();
        Assert.assertEquals(cartCount, "3", 
            "Cart count incorrect after adding 3 products");

        System.out.println("✅ Multiple products added! Cart count: " + cartCount);
    }

    @Test(priority = 6, description = "Verify logout functionality")
    public void testLogout() {
        // Perform logout
        productsPage.logout();

        // Verify user is back on login page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("saucedemo.com") && 
                         !currentUrl.contains("inventory"), 
            "User not redirected to login page after logout");

        // Verify login page title
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Swag Labs", 
            "Page title incorrect after logout");

        System.out.println("✅ Logout successful!");
    }
}
