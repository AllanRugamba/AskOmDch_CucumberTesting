package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductInCart(String productName) {
        // Try multiple possible selectors for WooCommerce cart
        String[] xpaths = {
            "//table[contains(@class,'shop_table')]//td[contains(@class,'product-name')]//a[contains(text(),'" + productName + "')]",
            "//table[contains(@class,'cart')]//td[contains(@class,'product-name')]//a[contains(text(),'" + productName + "')]",
            "//td[contains(@class,'product-name')]//*[contains(text(),'" + productName + "')]",
            "//*[contains(@class,'cart-item')]//*[contains(text(),'" + productName + "')]"
        };
        
        for (String xpath : xpaths) {
            if (driver.findElements(By.xpath(xpath)).size() > 0) {
                return true;
            }
        }
        return false;
    }

    public int getProductQuantity(String productName) {
        // Try multiple selectors for quantity in WooCommerce cart
        String[] xpaths = {
            "//table[contains(@class,'shop_table')]//td[contains(@class,'product-name')]//a[contains(text(),'" + productName + "')]/ancestor::tr//input[@type='number']",
            "//table[contains(@class,'cart')]//td[contains(@class,'product-name')]//a[contains(text(),'" + productName + "')]/ancestor::tr//input[contains(@class,'qty')]",
            "//td[contains(@class,'product-name')]//*[contains(text(),'" + productName + "')]/ancestor::tr//input[@type='number']"
        };
        
        for (String xpath : xpaths) {
            try {
                String quantityStr = driver.findElement(By.xpath(xpath)).getAttribute("value");
                return Integer.parseInt(quantityStr);
            } catch (Exception e) {
                // Try next xpath
            }
        }
        return 0;
    }
}