Feature: Checkout
  As a customer
  I want to checkout my cart
  So that I can place an order

  Background:
    Given I am on the home page
    And I have added an item to the cart


  Scenario: Proceed to checkout and place order successfully
    When I go to the cart page
    And I click on the checkout button
    And I fill in the checkout details
      | field         | value              |
      | firstName     | Allan              |
      | lastName      | Rugamba            |
      | address       | Kigali, Rwanda     |
      | city          | Kigali             |
      | postalCode    | 00000              |
      | phone         | 123456789          |
      | email         | test@example.com   |
    And I select payment method "Credit Card"
    And I place the order
    Then I should see an order confirmation message
    And the order total should be correct