Feature: AskOmDch Homepage

  @Scenario1
  Scenario: Verify homepage title and product visibility
    Given I am on the AskOmDch home page
    Then The page title should contain "AskOmDch"
    And I should see the "Blue Shoes" product

  @Scenario2
  Scenario: Verify homepage title and product visibility
    Given I am on the AskOmDch Store page
    Then The page title should contain "AskOmDch"
    And I should see the "Blue Shoes" product