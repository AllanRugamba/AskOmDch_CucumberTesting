package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Map;

public class CheckoutSteps {
    private HomePage homePage;
    private final WebDriver driver = Hooks.driver;

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        homePage = new HomePage(Hooks.driver);
        homePage.open();
    }

    @Given("I have added an item to the cart")
    public void i_have_added_an_item_to_the_cart() {
        homePage.addProductToCart("Blue Shoes");
    }

    @When("I go to the cart page")
    public void i_go_to_the_cart_page() {
        homePage.openCart();
    }

    @When("I click on the checkout button")
    public void i_click_on_the_checkout_button() {

        driver.findElement(By.cssSelector("#post-1220 > div > div > div > div > div.cart-collaterals > div > div > a")).click();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @When("I fill in the checkout details")
    public void i_fill_in_the_checkout_details(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        
        driver.findElement(By.id("billing_first_name")).sendKeys(data.get("firstName"));
        driver.findElement(By.id("billing_last_name")).sendKeys(data.get("lastName"));
        driver.findElement(By.id("billing_address_1")).sendKeys(data.get("address"));
        driver.findElement(By.id("billing_city")).sendKeys(data.get("city"));
        driver.findElement(By.id("billing_postcode")).sendKeys(data.get("postalCode"));
        driver.findElement(By.id("billing_phone")).sendKeys(data.get("phone"));
        driver.findElement(By.id("billing_email")).sendKeys(data.get("email"));
    }
    @When("I select payment method {string}")
    public void i_select_payment_method(String paymentMethod) {
        driver.findElement(By.id("payment_method_cod")).click();
    }
    @When("I place the order")
    public void i_place_the_order() {
        driver.findElement(By.id("place_order")).click();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    @Then("I should see an order confirmation message")
    public void i_should_see_an_order_confirmation_message() {
        boolean hasConfirmation = driver.getPageSource().contains("Thank you") ||
                                driver.getPageSource().contains("Order received") ||
                                driver.getCurrentUrl().contains("order-received");
        
        Assert.assertTrue("No order confirmation found", hasConfirmation);
    }

    @Then("the order total should be correct")
    public void the_order_total_should_be_correct() {
        boolean hasTotal = driver.findElements(By.cssSelector(".order-total, .woocommerce-Price-amount")).size() > 0;
        Assert.assertTrue("Order total not found", hasTotal);
    }
}