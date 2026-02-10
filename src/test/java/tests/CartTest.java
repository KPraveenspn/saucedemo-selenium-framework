package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

/**
 * CartTest - Test cases for Shopping Cart functionality
 */
public class CartTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setupTest() {
        // Login and navigate to products page
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        productsPage = new ProductsPage(driver);
    }

    @Test(priority = 1, description = "Verify cart page is accessible")
    public void testNavigateToCart() {
       
        System.out.println("Current URL before click: " + driver.getCurrentUrl());
        
        // Click on cart icon
        productsPage.clickShoppingCart();

        // Wait and check
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Current URL after click: " + driver.getCurrentUrl());
        System.out.println("Page source contains 'Your Cart': " + driver.getPageSource().contains("Your Cart"));
        
        // Initialize CartPage
        cartPage = new CartPage(driver);

        
        // Verify cart page is displayed
        boolean isDisplayed = cartPage.isCartPageDisplayed();
        System.out.println("isCartPageDisplayed returned: " + isDisplayed);
        
        Assert.assertTrue(isDisplayed, 
            "Cart page is not displayed. URL: " + driver.getCurrentUrl());

        String pageTitle = cartPage.getPageTitle();
        System.out.println("Page title: '" + pageTitle + "'");
        Assert.assertEquals(pageTitle, "Your Cart", 
            "Cart page title is incorrect");

        String currentUrl = cartPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("cart.html"), 
            "URL does not contain cart.html");

        System.out.println("✅ Cart page navigation successful!");
    }

    @Test(priority = 2, description = "Verify empty cart shows no items")
    public void testEmptyCart() {
        // Go to cart without adding products
        productsPage.clickShoppingCart();
        cartPage = new CartPage(driver);

        // Verify cart is empty
        Assert.assertTrue(cartPage.isCartEmpty(), 
            "Cart should be empty");

        int itemCount = cartPage.getCartItemCount();
        Assert.assertEquals(itemCount, 0, 
            "Cart item count should be 0");

        System.out.println("✅ Empty cart verified!");
    }

    @Test(priority = 3, description = "Verify product appears in cart after adding")
    public void testProductInCart() {
        // Add a product
        productsPage.addBackpackToCart();

        // Navigate to cart
        productsPage.clickShoppingCart();
        cartPage = new CartPage(driver);

        // Verify cart has 1 item
        int itemCount = cartPage.getCartItemCount();
        Assert.assertEquals(itemCount, 1, 
            "Cart should contain 1 item");

        // Verify specific product is in cart
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), 
            "Backpack not found in cart");

        System.out.println("✅ Product successfully added to cart!");
    }

    @Test(priority = 4, description = "Verify multiple products in cart")
    public void testMultipleProductsInCart() {
        // Add 3 products
        productsPage.addBackpackToCart();
        productsPage.addBikeLightToCart();
        productsPage.addBoltTshirtToCart();

        // Navigate to cart
        productsPage.clickShoppingCart();
        cartPage = new CartPage(driver);

        // Verify cart count
        int itemCount = cartPage.getCartItemCount();
        Assert.assertEquals(itemCount, 3, 
            "Cart should contain 3 items");

        // Verify all products are in cart
        List<String> cartItems = cartPage.getCartItemNames();
        Assert.assertTrue(cartItems.contains("Sauce Labs Backpack"), 
            "Backpack not in cart");
        Assert.assertTrue(cartItems.contains("Sauce Labs Bike Light"), 
            "Bike Light not in cart");
        Assert.assertTrue(cartItems.contains("Sauce Labs Bolt T-Shirt"), 
            "T-Shirt not in cart");

        System.out.println("✅ All 3 products verified in cart!");
        System.out.println("Cart items: " + cartItems);
    }

    @Test(priority = 5, description = "Verify Continue Shopping button")
    public void testContinueShopping() {
        
        // Go to cart
        productsPage.clickShoppingCart();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Current URL (should be cart): " + driver.getCurrentUrl());
        
        cartPage = new CartPage(driver);

        // Click Continue Shopping
        System.out.println("Clicking Continue Shopping button...");
        cartPage.clickContinueShopping();

        // Verify back on products page
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after Continue Shopping: " + currentUrl);
        System.out.println("URL contains 'inventory.html': " + currentUrl.contains("inventory.html"));
        
        Assert.assertTrue(currentUrl.contains("inventory.html"), 
            "Not redirected to products page. URL: " + currentUrl);

        System.out.println("✅ Continue Shopping works correctly!");
    }

    @Test(priority = 6, description = "Verify cart item details (names and prices)")
    public void testCartItemDetails() {
        // Add product
        productsPage.addBackpackToCart();

        // Go to cart
        productsPage.clickShoppingCart();
        cartPage = new CartPage(driver);

        // Get product names
        List<String> names = cartPage.getCartItemNames();
        Assert.assertFalse(names.isEmpty(), "Cart item names not found");

        // Get product prices
        List<String> prices = cartPage.getCartItemPrices();
        Assert.assertFalse(prices.isEmpty(), "Cart item prices not found");

        System.out.println("✅ Cart item details:");
        System.out.println("Names: " + names);
        System.out.println("Prices: " + prices);
    }
}
