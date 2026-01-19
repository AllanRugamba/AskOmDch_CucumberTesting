Feature: Remove item from cart
  As a customer
  I want to remove items from my cart
  So that I can manage my cart contents

  Background:
    Given I am on the home page
    And I have added "Blue Shoes" to the cart

  Scenario: Remove item from cart successfully
    When I go to the cart page
    And I remove "Blue Shoes" from the cart
    Then the cart should be empty
    And I should see "Your cart is currently empty" message

  Scenario: Remove one item when multiple items in cart
    And I have added "Anchor Bracelet" to the cart
    When I go to the cart page
    And I remove "Blue Shoes" from the cart
    Then the cart should contain only "Anchor Bracelet"
    And the cart total should be updated