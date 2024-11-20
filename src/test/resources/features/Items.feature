@Crater
Feature: Items feature
  As a user we can add and manage items
  Background:
  Given User is navigated to crater.com
  When User enters "entityadmin@primetechschool.com" in the username field
  And User enters "primetech@school" in the password field
  And User clicks on Login Button
  Then User is able to verify the "Setting" page is displayed
@Regression
  Scenario: User should be able to add and manage items
    When User clicks on the Items Menu Link
    Then User should be able to navigate to items page
    When User clicks on +Add item button
    Then User should be navigated to the new item page
    When User enters "Note book" in the Name input field
    And User enters "2500" in the Price input field
    And User selects "Dollars" as one of the unit
    And User enters "Color of note book" in the description text field
    When User clicks on save item button
    Then User should see a flash message "Success! Item created successfully" with a close button to the right
    And The flash message should disappear within 5 seconds or less
    And User should be navigated back to the items page
    And User should be able to view new item displayed in the items table
    And User should be able to see the entered information in the application database

