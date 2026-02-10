package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * CartPage - Page Object for SauceDemo Shopping Cart Page
 * Handles cart item verification and checkout flow
 */
public class CartPage {

    private WebDriver driver;

    // ===== Locators =====
    
    // Page identification
    private By pageTitle = By.className("title");
    private By cartContents = By.id("cart_contents_container");
    private By cartList = By.className("cart_list");
    
    // Cart items
    private By cartItems = By.className("cart_item");
    private By cartItemNames = By.className("inventory_item_name");
    private By cartItemPrices = By.className("inventory_item_price");
    private By removeButtons = By.xpath("//button[contains(@id,'remove')]");
    
    // Buttons
    private By continueShoppingButton = By.id("continue-shopping");
    private By checkoutButton = By.id("checkout");

    // ===== Constructor =====
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // ===== Page Verification Methods =====
    
    /**
     * Get page title (should be "Your Cart")
     * @return page title text
     */
    public String getPageTitle() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            return title.getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Check if cart page is displayed
     * Uses multiple strategies to verify cart page
     * @return true if cart page is visible
     */
    public boolean isCartPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            
            // Strategy 1: Check for cart_contents_container
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(cartContents));
                return true;
            } catch (Exception e1) {
                // Strategy 2: Check for cart_list
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(cartList));
                    return true;
                } catch (Exception e2) {
                    // Strategy 3: Check URL
                    try {
                        wait.until(ExpectedConditions.urlContains("cart.html"));
                        return true;
                    } catch (Exception e3) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get current URL
     * @return current page URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // ===== Cart Items Methods =====
    
    /**
     * Get number of items in cart
     * @return count of cart items
     */
    public int getCartItemCount() {
        try {
            List<WebElement> items = driver.findElements(cartItems);
            return items.size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    /**
     * Get all product names in cart
     * @return list of product names
     */
    public List<String> getCartItemNames() {
        List<WebElement> items = driver.findElements(cartItemNames);
        return items.stream()
                   .map(WebElement::getText)
                   .collect(Collectors.toList());
    }
    
    /**
     * Get all product prices in cart
     * @return list of prices
     */
    public List<String> getCartItemPrices() {
        List<WebElement> prices = driver.findElements(cartItemPrices);
        return prices.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
    }
    
    /**
     * Check if specific product exists in cart by name
     * @param productName - name of product to check
     * @return true if product found in cart
     */
    public boolean isProductInCart(String productName) {
        List<String> cartItems = getCartItemNames();
        return cartItems.contains(productName);
    }
    
    /**
     * Check if cart is empty
     * @return true if no items in cart
     */
    public boolean isCartEmpty() {
        return getCartItemCount() == 0;
    }

    // ===== Cart Actions =====
    
    /**
     * Remove first item from cart
     */
    public void removeFirstItem() {
        List<WebElement> buttons = driver.findElements(removeButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }
    
    /**
     * Click Continue Shopping button
     */
    public void clickContinueShopping() {
        try {
            // Wait for button to be present and clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
            
            // Scroll to button if needed (ensures it's in view)
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
            
            // Small pause after scroll
            Thread.sleep(300);
            
            // Click the button
            button.click();
            
            System.out.println("Continue Shopping button clicked");
            
            // Wait for URL to change to inventory page
            wait.until(ExpectedConditions.urlContains("inventory.html"));
            
            // Additional wait for page stability
            Thread.sleep(500);
            
            System.out.println("Successfully navigated to inventory page");
            
        } catch (Exception e) {
            System.out.println("Error in clickContinueShopping: " + e.getMessage());
            // Try alternative approach - JavaScript click
            try {
                WebElement button = driver.findElement(continueShoppingButton);
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
                Thread.sleep(2000);
            } catch (Exception e2) {
                System.out.println("JavaScript click also failed: " + e2.getMessage());
            }
        }
    }
    
    /**
     * Click Checkout button
     */
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}