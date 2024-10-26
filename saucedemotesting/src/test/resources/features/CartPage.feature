Feature: Sauce Demo - Cart Page

  Scenario: Validating Cart Page Actions
    Given I am successfully logged into Swag Labs website
    When I add products to the cart
    And I click the cart button
    And I should be redirect to the cart page
    Then I should see list of products added

    When I clicked 'Remove' button on a product
    Then the product will be remove from the cart list

    When I click the 'Continue Shopping' button
    Then I should be redirect back to the inventory page

