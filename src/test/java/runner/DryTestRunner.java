package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)//You telling junit to run the TestRunner class as a cucumber test
@CucumberOptions(features = "src/test/resources/features" ,
        glue = "stepdefinitions",
        plugin = {"pretty" , "html:target/AhmedCapstone-report.html"},
        tags = "@Crater",
        dryRun = true

)
public class DryTestRunner {

}

