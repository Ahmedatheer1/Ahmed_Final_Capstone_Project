package hooks;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;

import java.time.Duration;

public class Hooks {

    @Before()
    public void beforeScenario() {
        System.out.println("We are running before each scenario");
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }


    @After()
    public void afterScenario(Scenario scenario) throws InterruptedException {
        System.out.println("We are running after each scenario");

        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Thread.sleep(3000);
        //Driver.closeDriver();
    }

        @BeforeStep
        public void beforeStep(){
        //System.out.println("This line will get printed before each step");
         }
    @AfterStep()
    public void afterStep()  {
       // System.out.println("This line will get printed after each step");
       //Thread.sleep(1000);
    }
}
//()Parameters can be changed according to different conditions. Ex: (order = 1), ("@smoke").
