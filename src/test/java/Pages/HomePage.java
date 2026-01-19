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

        String productXpath = "//h2[contains(@class,'woocommerce-loop-product__title') and normalize-space()='" 
                + productName + "']/ancestor::li//a[contains(@class,'add_to_cart_button')]";

        WebElement addToCartButton = driver.findElement(By.xpath(productXpath));
        addToCartButton.click();
        

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
        driver.findElement(By.cssSelector("#ast-site-header-cart > div.ast-site-header-cart-li.current-menu-item > a > div")).click();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}