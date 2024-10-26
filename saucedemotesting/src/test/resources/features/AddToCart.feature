Feature: Sauce Demo - Add To Cart

  Scenario: Verify 'Add to cart' and 'Remove' button functionality
    Given I am logged into Swag Labs website successfully
    When I add product to the cart
    And the 'Add to cart' button should change to 'Remove' button
    Then number of product added will be shown on the cart

    When I remove the product from the cart
    Then the 'Remove' button should change back to an 'Add to cart' button
