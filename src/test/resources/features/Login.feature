@Crater
Feature: Login feature
  As a user I should be able to verify that login should be working as expected
Scenario: User can login using valid username and password
  Given User is navigated to crater.com
  When User enters "entityadmin@primetechschool.com" in the username field
  And User enters "primetech@school" in the password field
  And User clicks on Login Button
  Then User is able to verify the "Setting" page is displayed