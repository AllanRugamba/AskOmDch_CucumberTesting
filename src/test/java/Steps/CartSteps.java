package Steps;

import io.cucumber.java.en.When;
import Pages.HomePage;

public class CartSteps {

    @When("I add the {string} product to the cart")
    public void i_add_the_product_to_the_cart(String productName) {
        HomePage homePage = new HomePage(Hooks.driver);
        homePage.addProductToCart(productName);
    }

}
