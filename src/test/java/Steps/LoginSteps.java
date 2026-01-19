package Steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private HomePage homePage;
    private WebDriver driver = Hooks.driver;

    @When("I navigate to my account page")
    public void iNavigateToMyAccountPage() {
        homePage = new HomePage(Hooks.driver);
        // Navigate to account page - try multiple selectors
        try {
            driver.findElement(By.linkText("My account")).click();
        } catch (Exception e) {
            try {
                driver.findElement(By.partialLinkText("Account")).click();
            } catch (Exception e2) {
                driver.get("https://askomdch.com/account/");
            }
        }
    }

    @When("login with username {string} and password {string}")
    public void loginWithUsernameAndPassword(String username, String password) {
        // Clear any existing text first
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("password")).clear();
        
        // Enter the credentials
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        
        // Click login button
        driver.findElement(By.name("login")).click();
        
        // Wait for page to process
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("I should see that I am logged in")
    public void iShouldSeeThatIAmLoggedIn() {
        // Wait a bit more for page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Look for signs that user is logged in
        boolean userIsLoggedIn = false;
        String currentUrl = driver.getCurrentUrl();
        String pageSource = driver.getPageSource();
        
        // Check if logout link exists
        if (driver.findElements(By.linkText("Logout")).size() > 0) {
            userIsLoggedIn = true;
        }
        
        // Check if we're on account dashboard (and not on login page)
        if (currentUrl.contains("my-account") && 
            !currentUrl.contains("lost-password") &&
            !pageSource.contains("Username or email")) {
            userIsLoggedIn = true;
        }
        
        // Check for welcome message or user name
        if (pageSource.contains("Hello") || 
            pageSource.contains("Welcome") ||
            pageSource.contains("Dashboard")) {
            userIsLoggedIn = true;
        }
        
        // Debug output if login failed
        if (!userIsLoggedIn) {
            System.out.println("Login failed - Current URL: " + currentUrl);
            System.out.println("Page title: " + driver.getTitle());
            System.out.println("Has logout link: " + (driver.findElements(By.linkText("Logout")).size() > 0));
        }
        
        Assert.assertTrue("Login was not successful", userIsLoggedIn);
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        // Check for error message indicators
        boolean hasError = driver.findElements(By.cssSelector("#post-1235 > div > div.wp-block-group.alignfull > div > div.woocommerce > div.woocommerce-notices-wrapper > ul > li > strong:nth-child(1)")).size() > 0 ||
                          driver.getPageSource().contains("ERROR") ||
                          driver.getPageSource().contains("Invalid username") ||
                          driver.getPageSource().contains("incorrect");
        
        Assert.assertTrue("No error message displayed", hasError);
    }
}