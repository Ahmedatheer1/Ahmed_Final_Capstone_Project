package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    private static WebDriver driver;

    private Driver(){

    }

    /**
     * Static method to get the single instance of WebDriver
     */

    public static WebDriver getDriver(){
        //Read the browser type you want to launch from properties file
        String browserType = ConfigurationReader.getPropertyValue("browserType");

        if(driver == null){//if the driver is null, then create the driver
            //Create the driver based on the browser type
            switch (browserType.toLowerCase()){

                case "chrome"://if the browser type is chrome
                    driver = new ChromeDriver(); //Instantiate only once
                    break;
                case "firefox"://if the browser type is firefox
                    driver = new FirefoxDriver();
                    break;
                case "edge": //if the browser type is edge
                    driver = new EdgeDriver();
                    break;
                case "safari": //if the browser type is safari
                    driver = new SafariDriver();
                    break;
                case "chrome-headless":
                    System.out.println("Running in headless mode in chrome");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);//will create new instance of chromedriver in headless mode
                    break;
                case "firefox-headless":
                    System.out.println("Running in headless mode in firefox");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "sauce-labs":
                    ChromeOptions browserOptions = new ChromeOptions();
                    browserOptions.setPlatformName("Windows 11");
                    browserOptions.setBrowserVersion("latest");
                    Map<String, Object> sauceOptions = new HashMap<>();
                    sauceOptions.put("username", "oauth-ahmedatheer1-46634");
                    sauceOptions.put("accessKey", "d545f784-4d36-4c0a-96d0-e67f40bc197a");
                    sauceOptions.put("build", "selenium-build-RF1F7");
                    sauceOptions.put("name", "PrimeTech-Test");
                    browserOptions.setCapability("sauce:options", sauceOptions);

                    // start the session
                    URL url = null;
                    try {
                        url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    driver = new RemoteWebDriver(url, browserOptions);
                    break;
                default:
                    driver = new ChromeDriver(); //Instantiate only once
                    break;


            }

        }
        return driver;// this is an existing one that is not null ( driver this is alive)
    }

    /**
     * This method will quit the driver and set it to null
     */
    public static void closeDriver(){
        //We Check if the driver still alive then
        //quit the driver and set the driver object to null
        System.out.println("Closing the driver");
        if(driver != null){
            driver.quit();
            driver = null;
        }


    }



}
