package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * ProductsPage - Page Object for SauceDemo Products/Inventory Page
 * Handles product listing, add to cart, and logout functionality
 */
public class ProductsPage {

    private WebDriver driver;

    // ===== Locators =====
    
    // Page identification
    private By pageTitle = By.className("title");
    private By inventoryContainer = By.id("inventory_container");
    
    // Products and cart
    private By productItems = By.className("inventory_item");
    private By productTitles = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By addToCartButtons = By.xpath("//button[contains(@id,'add-to-cart')]");
    
    // Specific product add to cart buttons (by product name)
    private By addBackpackToCart = By.id("add-to-cart-sauce-labs-backpack");
    private By addBikeLightToCart = By.id("add-to-cart-sauce-labs-bike-light");
    private By addBoltTshirtToCart = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    
    // Remove buttons (appear after adding to cart)
    private By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    
    // Shopping cart
    private By shoppingCartBadge = By.className("shopping_cart_badge");
    private By shoppingCartLink = By.className("shopping_cart_link");
    
    // Menu and logout
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By menuCloseButton = By.id("react-burger-cross-btn");

    // ===== Constructor =====
    
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // ===== Page Verification Methods =====
    
    /**
     * Get page title text (should be "Products")
     * @return page title text
     */
    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }
    
    /**
     * Check if user is on products page
     * @return true if inventory container is displayed
     */
    public boolean isProductsPageDisplayed() {
        try {
            return driver.findElement(inventoryContainer).isDisplayed();
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

    // ===== Product Interaction Methods =====
    
    /**
     * Get count of products displayed on page
     * @return number of products
     */
    public int getProductCount() {
        List<WebElement> products = driver.findElements(productItems);
        return products.size();
    }
    
    /**
     * Get all product names
     * @return list of product names
     */
    public List<String> getAllProductNames() {
        List<WebElement> titles = driver.findElements(productTitles);
        return titles.stream()
                    .map(WebElement::getText)
                    .toList();
    }
    
    /**
     * Add Sauce Labs Backpack to cart
     */
    public void addBackpackToCart() {
        driver.findElement(addBackpackToCart).click();
        // Wait for cart badge to update
        waitForCartBadgeToAppear();
    }
    
    /**
     * Add Sauce Labs Bike Light to cart
     */
    public void addBikeLightToCart() {
        driver.findElement(addBikeLightToCart).click();
        waitForCartBadgeToAppear();
    }
    
    /**
     * Add Sauce Labs Bolt T-Shirt to cart
     */
    public void addBoltTshirtToCart() {
        driver.findElement(addBoltTshirtToCart).click();
        waitForCartBadgeToAppear();
    }
    
    /**
     * Wait for cart badge to appear after adding item
     * Helper method to ensure cart updates
     */
    private void waitForCartBadgeToAppear() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartBadge));
        } catch (Exception e) {
            // Badge might not appear if cart was already populated
            // This is acceptable, so we continue
        }
    }
    
    /**
     * Add multiple products to cart by index
     * Useful for adding first N products
     * @param count - number of products to add
     */
    public void addProductsToCart(int count) {
        List<WebElement> addButtons = driver.findElements(addToCartButtons);
        for (int i = 0; i < count && i < addButtons.size(); i++) {
            addButtons.get(i).click();
            waitForCartBadgeToAppear();
        }
    }

    // ===== Shopping Cart Methods =====
    
    /**
     * Get shopping cart badge count (number of items in cart)
     * @return cart item count as string, or "0" if badge not visible
     */
    public String getCartItemCount() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartBadge));
            return badge.getText();
        } catch (Exception e) {
            return "0"; // Badge not displayed when cart is empty
        }
    }
    
    /**
     * Check if cart badge is displayed
     * @return true if badge is visible
     */
    public boolean isCartBadgeDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartBadge));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Click on shopping cart to view cart
     */
    public void clickShoppingCart() {
        driver.findElement(shoppingCartLink).click();
        
        // Wait for cart page to load
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("cart.html"));
         // Small additional wait for page elements
            Thread.sleep(500);
            
        } catch (Exception e) {
            // Continue even if URL doesn't change immediately
        }
    }

    // ===== Logout Methods =====
    
    /**
     * Open hamburger menu with explicit wait
     */
    public void openMenu() {
        WebElement menu = driver.findElement(menuButton);
        menu.click();
        
        // Wait for logout link to be visible (confirms menu is fully open)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
    }
    
    /**
     * Click logout link (menu must be open first)
     */
    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logout.click();
    }
    
    /**
     * Complete logout flow (open menu + click logout)
     */
    public void logout() {
        openMenu();
        clickLogout();
        
        // Wait for navigation to login page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("saucedemo.com"));
    }
}