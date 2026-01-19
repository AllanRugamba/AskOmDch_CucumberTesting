Feature: Adding a product to cart
  As a customer
  I want to add products to my cart
  So that I can purchase them

  Background:
    Given I'm on the Store

  Scenario: Add single product to cart
    When I add a "Blue Shoes" to the cart
    Then I see 1 "Blue Shoes" in the cart
    And the cart total should be updated

  Scenario: Add multiple different products to cart
    When I add a "Blue Shoes" to the cart
    And I add a "Anchor Bracelet" to the cart
    Then I see 1 "Blue Shoes" in the cart
    And I see 1 "Anchor Bracelet" in the cart
    And the cart total should be updated

  Scenario: Add same product multiple times
    When I add a "Blue Shoes" to the cart
    And I add a "Blue Shoes" to the cart
    Then I see 2 "Blue Shoes" in the cart
    And the cart total should be updated

  Scenario Outline: Add different products from examples
    When I add a "<product_name>" to the cart
    Then I see 1 "<product_name>" in the cart
    And the cart total should be updated

    Examples:
    | product_name    |
    | Blue Shoes      |
    | Anchor Bracelet |
    | Basic Blue Jeans|

