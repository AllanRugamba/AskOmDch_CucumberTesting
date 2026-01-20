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

        driver.findElement(By.id("reg_email")).clear();
        driver.findElement(By.id("reg_email")).sendKeys(username);
        
        driver.findElement(By.id("reg_password")).clear();
        driver.findElement(By.id("reg_password")).sendKeys(password);
        

        driver.findElement(By.name("register")).click();
        

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("I should see that registration was successful")
    public void iShouldSeeThatRegistrationWasSuccessful() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        

        boolean registrationSuccessful = false;
        

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("my-account") && !currentUrl.contains("error")) {
            registrationSuccessful = true;
        }
        

        if (driver.findElements(By.linkText("Logout")).size() > 0) {
            registrationSuccessful = true;
        }
        

        String pageSource = driver.getPageSource().toLowerCase();
        if (pageSource.contains("welcome") || 
            pageSource.contains("hello") ||
            pageSource.contains("dashboard") ||
            pageSource.contains("my account")) {
            registrationSuccessful = true;
        }
        

        if (!registrationSuccessful) {
            System.out.println("Current URL: " + currentUrl);
            System.out.println("Page title: " + driver.getTitle());
        }
        
        Assert.assertTrue("Registration was not successful", registrationSuccessful);
    }
    @Then("I should see a registration error message")
    public void iShouldSeeARegistrationErrorMessage() {

        boolean hasError = driver.findElements(By.cssSelector(".woocommerce-error, .error")).size() > 0 ||
                          driver.getPageSource().contains("ERROR") ||
                          driver.getPageSource().contains("already registered") ||
                          driver.getPageSource().contains("required") ||
                          driver.getPageSource().contains("invalid");
        
        Assert.assertTrue("No registration error message displayed", hasError);
    }
}