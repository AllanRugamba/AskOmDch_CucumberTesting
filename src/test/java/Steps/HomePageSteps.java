package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import Pages.HomePage;

public class HomePageSteps {
    private HomePage homePage;

    @Given("I am on the AskOmDch home page")
    public void iAmOnTheAskOmDchHomePage() {
        homePage = new HomePage(Hooks.driver);
        homePage.open();
    }

    @Given("I am on the AskOmDch Store page")
    public void iAmOnTheAskOmDchStorePage() {
        homePage = new HomePage(Hooks.driver);
        homePage.open();
    }

    @Then("The page title should contain {string}")
    public void thePageTitleShouldContain(String expectedTitle) {
        String actualTitle = homePage.getTitle();
        Assert.assertTrue("The title was: " + actualTitle, actualTitle.contains(expectedTitle));
    }

    @Then("I should see the {string} product")
    public void iShouldSeeTheProduct(String expectedProduct){
        Assert.assertTrue("Blue Shoes product is not visible on the page", homePage.isBlueShoesProductVisible());
        String actualProduct = homePage.getProductName();
        Assert.assertTrue("Expected product '" + expectedProduct + "' but found '" + actualProduct + "'", 
                         actualProduct.contains(expectedProduct));
    }
}