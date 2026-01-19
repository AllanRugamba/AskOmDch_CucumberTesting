package Steps;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }


    @Before()
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After()
    public void quitBrowser(){
        if(driver != null){
            driver.quit();
        }
    }

//    @BeforeAll
//    public static void beforeClass(){
//        System.out.println("\nIN BEFORE CLASS\n");
//    }
//
//
//    @Before("@Scenario1")
//    public void beforeScenarioOrder1(Scenario scenario){
//        System.out.println("\nIN BEFORE SCENARIO ORDER 1\n");
//    }
//
//    @Before("@Scenario2")
//    public void beforeScenarioOrder2(Scenario scenario){
//        System.out.println("\nIN BEFORE SCENARIO ORDER 2\n");
//    }
//
//    @BeforeStep
//    public void beforeStep(Scenario scenario){
//        System.out.println("\nIN BEFORE STEP\n");
//    }
//
//    @AfterStep
//    public void afterStep(Scenario scenario){
//        System.out.println("\nIN AFTER STEP\n");
//    }
//
//    @After("@Scenario1")
//    public void afterScenarioOrder1(Scenario scenario){
//        System.out.println("\nIN AFTER SCENARIO ORDER 1\n");
//    }
//
//    @After("@Scenario2")
//    public void afterScenarioOrder2(Scenario scenario){
//        System.out.println("\nIN AFTER SCENARIO ORDER 2\n");
//    }
//
//    @AfterAll
//    public static void afterClass(){
//        System.out.println("\nIN AFTER CLASS\n");
//    }
}
