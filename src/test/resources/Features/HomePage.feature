Feature: Homepage functionality
  As a user
  I want to verify the homepage
  So that I can see products and navigate

  Background:
    Given I am on the AskOmDch home page

  @Scenario1
  Scenario: Verify homepage title and product visibility
    Then The page title should contain "AskOmDch"
    And I should see the "Blue Shoes" product

  @Scenario2
  Scenario: Verify homepage title and product visibility
    Given I am on the AskOmDch Store page
    Then The page title should contain "AskOmDch"
    And I should see the "Blue Shoes" product