Feature: Swag Labs - Login Page

  Scenario: Validate Successful Login
    Given I access the Swag Labs login page
    When I enter one of the username given
    And I enter a password
    And I click on the login button
    Then I should be successfully login and redirect to inventory page