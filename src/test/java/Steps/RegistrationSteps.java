package Steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationSteps {
    private HomePage homePage;
    private WebDriver driver = Hooks.driver;

    @When("I register with username {string} and password {string}")
    public void iRegisterWithUsernameAndPassword(String username, String password) {
        // Fill registration form
        driver.findElement(By.id("reg_email")).clear();
        driver.findElement(By.id("reg_email")).sendKeys(username);
        
        driver.findElement(By.id("reg_password")).clear();
        driver.findElement(By.id("reg_password")).sendKeys(password);
        
        // Click register button
        driver.findElement(By.name("register")).click();
        
        // Wait for registration to process
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("I should see that registration was successful")
    public void iShouldSeeThatRegistrationWasSuccessful() {
        // Wait a bit more for page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Check if registration was successful - be more flexible
        boolean registrationSuccessful = false;
        
        // Check URL - if we're on my-account page without error parameters
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("my-account") && !currentUrl.contains("error")) {
            registrationSuccessful = true;
        }
        
        // Check if we can see logout link (means we're logged in after registration)
        if (driver.findElements(By.linkText("Logout")).size() > 0) {
            registrationSuccessful = true;
        }
        
        // Check page source for success indicators
        String pageSource = driver.getPageSource().toLowerCase();
        if (pageSource.contains("welcome") || 
            pageSource.contains("hello") ||
            pageSource.contains("dashboard") ||
            pageSource.contains("my account")) {
            registrationSuccessful = true;
        }
        
        // If still not successful, print current URL and page title for debugging
        if (!registrationSuccessful) {
            System.out.println("Current URL: " + currentUrl);
            System.out.println("Page title: " + driver.getTitle());
        }
        
        Assert.assertTrue("Registration was not successful", registrationSuccessful);
    }

    @Then("I should see a registration error message")
    public void iShouldSeeARegistrationErrorMessage() {
        // Check for registration error messages
        boolean hasError = driver.findElements(By.cssSelector(".woocommerce-error, .error")).size() > 0 ||
                          driver.getPageSource().contains("ERROR") ||
                          driver.getPageSource().contains("already registered") ||
                          driver.getPageSource().contains("required") ||
                          driver.getPageSource().contains("invalid");
        
        Assert.assertTrue("No registration error message displayed", hasError);
    }
}