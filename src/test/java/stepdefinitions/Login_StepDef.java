package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.SettingsPage;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class Login_StepDef {
    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage();
    SettingsPage settingsPage = new SettingsPage();
    @Given("User is navigated to crater.com")
    public void user_is_navigated_to_crater_com() {
        driver.get(ConfigurationReader.getPropertyValue("craterUrl"));

    }
    @When("User enters {string} in the username field")
    public void user_enters_in_the_username_field(String emailfield) {
        SeleniumUtils.sendkeysWithActionsClass(loginPage.emailInput, emailfield);


    }
    @And("User enters {string} in the password field")
    public void user_enters_in_the_password_field(String password) {
        SeleniumUtils.sendkeysWithActionsClass(loginPage.passwordInput, password);

    }
    @And("User clicks on Login Button")
    public void user_clicks_on_login_button() {
        loginPage.loginButton.click();

    }
    @Then("User is able to verify the {string} page is displayed")
    public void user_is_able_to_verify_the_page_is_displayed(String settingspage) {
        Assert.assertTrue(settingsPage.settingsLabel.isDisplayed());// to verify if expected result is passed


    }

}
