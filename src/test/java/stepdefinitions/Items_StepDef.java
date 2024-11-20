package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ItemsPage;
import pages.SettingsPage;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.sql.*;
import java.time.Duration;


public class Items_StepDef {
    WebDriver driver = Driver.getDriver();
    SettingsPage settingsPage = new SettingsPage();
    ItemsPage itemsPage = new ItemsPage();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    @When("User clicks on the Items Menu Link")
    public void user_clicks_on_the_items_menu_link() {
        settingsPage.itemsButton.click();
    }
    @Then("User should be able to navigate to items page")
    public void user_should_be_able_to_navigate_to_items_page() {
        wait.until(ExpectedConditions.visibilityOf(itemsPage.itemsPageLabel));
        String itemsPageUrl = "http://crater.primetech-apps.com/admin/items";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(itemsPageUrl, currentUrl);
        Assert.assertTrue(itemsPage.itemsPageLabel.isDisplayed());
    }
    @When("User clicks on +Add item button")
    public void user_clicks_on_add_item_button() {
        wait.until(ExpectedConditions.visibilityOf(itemsPage.addItemsButton));
        itemsPage.addItemsButton.click();
    }
    @Then("User should be navigated to the new item page")
    public void user_should_be_navigated_to_the_new_item_page() {
        Assert.assertTrue(itemsPage.newItemsPageLabel.isDisplayed());
    }
    @When("User enters {string} in the Name input field")
    public void user_enters_in_the_name_input_field(String name) {
        SeleniumUtils.sendkeysWithActionsClass(itemsPage.nameInput,name);
    }
    @And("User enters {string} in the Price input field")
    public void user_enters_in_the_price_input_field(String price) {
        SeleniumUtils.sendkeysWithActionsClass(itemsPage.priceInput,price);
    }
    @And("User selects {string} as one of the unit")
    public void user_selects_as_one_of_the_unit(String unit) {
        SeleniumUtils.sendkeysWithActionsClass(itemsPage.unitInput,unit);
        itemsPage.unitInput.sendKeys(Keys.ENTER);
    }
    @And("User enters {string} in the description text field")
    public void user_enters_in_the_description_text_field(String description) {
        SeleniumUtils.sendkeysWithActionsClass(itemsPage.descriptionInput, description);
    }
    @When("User clicks on save item button")
    public void user_clicks_on_save_item_button() {
        itemsPage.saveItemButton.click();
    }
    @Then("User should see a flash message {string} with a close button to the right")
    public void user_should_see_a_flash_message_with_a_close_button_to_the_right(String string) {
        wait.until(ExpectedConditions.visibilityOf(itemsPage.flashMessage));
        System.out.println("The message is" + itemsPage.flashMessage.getText());
        Assert.assertTrue(itemsPage.flashMessage.isDisplayed());
    }
    @And("The flash message should disappear within {int} seconds or less")
    public void the_flash_message_should_disappear_within_seconds_or_less(Integer int1) {
        Boolean messageHasDisappeared = wait.until(ExpectedConditions.invisibilityOf(itemsPage.flashMessage));
        Assert.assertTrue(messageHasDisappeared);
    }
    @And("User should be navigated back to the items page")
    public void user_should_be_navigated_back_to_the_items_page() {

    }
    @And("User should be able to view new item displayed in the items table")
    public void user_should_be_able_to_view_new_item_displayed_in_the_items_table() {
        String expectedName = "Note book";
        for(WebElement item : itemsPage.itemsList){
            System.out.println(item.getText());
        }
        Assert.assertTrue(SeleniumUtils.isItemInTable(itemsPage.itemsList, expectedName));
    }
    @And("User should be able to see the entered information in the application database")
    public void user_should_be_able_to_see_the_entered_information_in_the_application_database() {
        String query = "SELECT name, price, description FROM CraterDBS.items ORDER BY created_at DESC;";

        String dbUrl = "jdbc:mysql://stack-overflow.cfse9bqqndon.us-east-1.rds.amazonaws.com/CraterDBS";
        String userName = "craterdbuser";
        String password = "ptschool2023";

        String expectedName = "Note book";
        String expectedPrice = "2500";
//String expectedUnit = "Dollars";
        String expectedDescription = "Color of note book";

//Creating a connection
        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password);
             //Creating a statement
             Statement statement = connection.createStatement();
             //Executing the query
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                String actualName = resultSet.getString("name");
                //String actualUnit = resultSet.getString("unit_id");
                String actualPrice = resultSet.getString("price");
                String actualDescription = resultSet.getString("description");

                Assert.assertEquals("Name mismatch", expectedName, actualName);
                //Assert.assertEquals("Unit mismatch", expectedUnit, actualUnit);
                Assert.assertEquals("Price mismatch", expectedPrice, actualPrice);
                Assert.assertEquals("Description mismatch", expectedDescription, actualDescription);

            } else {
                Assert.fail("No matching record found in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Database verification failed: " + e.getMessage());
        }

    }

}
