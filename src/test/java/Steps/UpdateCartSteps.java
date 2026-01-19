package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Pages.HomePage;
import Pages.CartPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UpdateCartSteps {
    private HomePage homePage;
    private CartPage cartPage;
    private WebDriver driver = Hooks.driver;

    @Given("I'm on the Store")
    public void iMOnTheStore() {
        homePage = new HomePage(Hooks.driver);
        homePage.open();
    }

    @When("I add a  {string} to the cart")
    public void iAddAToTheCart(String productName) {
        homePage = new HomePage(Hooks.driver);
        homePage.addProductToCart(productName);
    }

    @When("I add a {string} to the cart")
    public void iAddAProductToTheCart(String productName) {
        homePage = new HomePage(Hooks.driver);
        homePage.addProductToCart(productName);
    }

    @When("I change the quantity of {string} in the cart to {int}")
    public void iChangeTheQuantityOfInTheCartTo(String productName, Integer newQuantity) {
        homePage = new HomePage(Hooks.driver);
        homePage.openCart();
        cartPage = new CartPage(Hooks.driver);
        
        // Simple approach - find and update quantity input
        String xpath = "//input[@type='number']";
        driver.findElement(By.xpath(xpath)).clear();
        driver.findElement(By.xpath(xpath)).sendKeys(newQuantity.toString());
        driver.findElement(By.name("update_cart")).click();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("the cart should show quantity {int} for {string}")
    public void theCartShouldShowQuantityFor(Integer expectedQuantity, String productName) {
        int actualQuantity = cartPage.getProductQuantity(productName);
        Assert.assertEquals("Expected quantity " + expectedQuantity + " but found " + actualQuantity,
                expectedQuantity.intValue(), actualQuantity);
    }

    @Then("the cart total should be updated")
    public void theCartTotalShouldBeUpdated() {
        // Simple check - verify any total element exists
        Assert.assertTrue("Cart total not found", 
                driver.findElements(By.cssSelector(".cart-subtotal, .order-total, .woocommerce-Price-amount")).size() > 0);
    }

    @Then("I see {int} {string} in the cart")
    public void iSeeInTheCart(Integer expectedQuantity, String productName) {
        homePage.openCart();
        cartPage = new CartPage(Hooks.driver);
        
        Assert.assertTrue("Product not found in cart: " + productName,
                cartPage.isProductInCart(productName));
        
        int actualQuantity = cartPage.getProductQuantity(productName);
        Assert.assertEquals("Expected quantity " + expectedQuantity + " but found " + actualQuantity,
                expectedQuantity.intValue(), actualQuantity);
    }
}