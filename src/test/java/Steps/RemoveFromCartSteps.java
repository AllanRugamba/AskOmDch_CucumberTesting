package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import Pages.HomePage;
import Pages.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RemoveFromCartSteps {
    private HomePage homePage;
    private CartPage cartPage;
    private WebDriver driver = Hooks.driver;

    @Given("I have added {string} to the cart")
    public void i_have_added_to_the_cart(String productName) {
        homePage = new HomePage(Hooks.driver);
        homePage.addProductToCart(productName);
    }

    @When("I remove {string} from the cart")
    public void i_remove_from_the_cart(String productName) {
        // Find and click remove button for the specific product
        try {
            // Try common remove button selectors
            driver.findElement(By.cssSelector("a.remove")).click();
        } catch (Exception e) {
            try {
                driver.findElement(By.xpath("//a[contains(@class,'remove')]")).click();
            } catch (Exception e2) {
                try {
                    driver.findElement(By.linkText("×")).click();
                } catch (Exception e3) {
                    // Try product-specific remove button
                    String xpath = "//td[contains(@class,'product-name')]//*[contains(text(),'" + productName + "')]/ancestor::tr//a[contains(@class,'remove')]";
                    driver.findElement(By.xpath(xpath)).click();
                }
            }
        }
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        boolean isEmpty = driver.getPageSource().contains("Your cart is currently empty") ||
                         driver.getPageSource().contains("cart is empty") ||
                         driver.findElements(By.cssSelector(".cart-empty")).size() > 0;
        
        Assert.assertTrue("Cart is not empty", isEmpty);
    }

    @Then("I should see {string} message")
    public void i_should_see_message(String expectedMessage) {
        boolean hasMessage = driver.getPageSource().contains(expectedMessage);
        Assert.assertTrue("Expected message not found: " + expectedMessage, hasMessage);
    }

    @Then("the cart should contain only {string}")
    public void the_cart_should_contain_only(String productName) {
        cartPage = new CartPage(Hooks.driver);
        boolean hasProduct = cartPage.isProductInCart(productName);
        Assert.assertTrue("Product not found in cart: " + productName, hasProduct);
        
        // Check that only one product row exists
        int productCount = driver.findElements(By.cssSelector("tr.cart_item")).size();
        Assert.assertEquals("Cart should contain only one item", 1, productCount);
    }
}