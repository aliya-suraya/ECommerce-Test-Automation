Feature: Source Demo - Checkout Page

  Scenario: Verify Checkout Page and Process
    Given I am logged into the Swag Labs website with a product added to the cart and am on the cart page
    When I click the 'Checkout' button
    Then I will be redirect to checkout page

    When I enter my first name
    And I enter my last name
    And I enter my postal code
    And I click 'Continue' button
    Then I will be redirect to Checkout:Overview page
    And I should see the cart list and summary info

    When I click the 'Finish' button
    Then I should be redirect to the Checkout:Complete page
    And I should be presented with a successful order submission message

    When I click the 'Back Home' button
    Then I should be redirect to inventory page

