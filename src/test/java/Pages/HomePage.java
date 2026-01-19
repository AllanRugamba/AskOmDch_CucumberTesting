package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private String url = "https://askomdch.com/";
    private By blueShoesProduct = By.xpath("//h2[contains(@class, 'woocommerce-loop-product__title') and normalize-space()='Blue Shoes']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }
    public boolean isBlueShoesProductVisible(){
        List<WebElement> blueShoesProducts = driver.findElements(blueShoesProduct);
        return !blueShoesProducts.isEmpty();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void addProductToCart(String productName) {
        // Find the product card containing the given name
        String productXpath = "//h2[contains(@class,'woocommerce-loop-product__title') and normalize-space()='" 
                + productName + "']/ancestor::li//a[contains(@class,'add_to_cart_button')]";

        WebElement addToCartButton = driver.findElement(By.xpath(productXpath));
        addToCartButton.click();
        
        // Wait for the add to cart action to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public String getProductName() {
        if (isBlueShoesProductVisible()) {
            return driver.findElement(blueShoesProduct).getText();
        }
        return "";
    }

    public void openCart() {
        // Try multiple ways to access cart
        try {
            driver.findElement(By.linkText("Cart")).click();
        } catch (Exception e) {
            try {
                driver.findElement(By.partialLinkText("Cart")).click();
            } catch (Exception e2) {
                try {
                    driver.findElement(By.cssSelector("a[href*='cart']")).click();
                } catch (Exception e3) {
                    // Navigate directly to cart URL
                    driver.get(url + "cart/");
                }
            }
        }
        
        // Wait for cart page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}