Feature: Sauce Demo - Product Filter

  Scenario: Validate Successful Filter Functionality
    Given I login into Swag Labs website successfully
    When I am on the Swag Labs inventory page
    And I click the filter menu
    And I select the filter option Name (A to Z)
    Then I should see the product list sorted in alphabetical order (A to Z)

    When I select the filter option Name (Z to A)
    Then I should see the product list sorted in reverse alphabetical order (Z to A)

    When I select the filter option Price (low to high)
    Then I should see the product list sorted by price from low to high

    When I select the filter option Price (high to low)
    Then I should see the product list sorted by price from high to low
