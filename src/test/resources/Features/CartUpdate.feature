Feature: Update cart quantity
  As a customer
  I want to change quantities of products in my cart
  So that I can buy the amount I need

  Scenario: Change quantity of Blue Shoes in the cart
    Given I am on the AskOmDch home page
    And I add the "Blue Shoes" product to the cart
    When I change the quantity of "Blue Shoes" in the cart to 2
    Then the cart should show quantity 2 for "Blue Shoes"
    And the cart total should be updated