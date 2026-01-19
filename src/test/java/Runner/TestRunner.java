package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true,
        features = "src/test/resources/Features",
        glue = "Steps",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner  {

}
