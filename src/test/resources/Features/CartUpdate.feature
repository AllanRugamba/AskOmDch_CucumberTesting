Feature: Update cart quantity
  As a customer
  I want to update product quantities in my cart
  So that I can adjust my order

  Background:
    Given I am on the AskOmDch home page
    And I add the "Blue Shoes" product to the cart


  Scenario: Change quantity of Blue Shoes in the cart
    When I change the quantity of "Blue Shoes" in the cart to 2
    Then the cart should show quantity 2 for "Blue Shoes"
    And the cart total should be updated