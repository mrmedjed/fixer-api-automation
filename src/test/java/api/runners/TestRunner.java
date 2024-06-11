package api.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/api/features",
        glue = "api/stepDefinitions",
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)
public class TestRunner {
}