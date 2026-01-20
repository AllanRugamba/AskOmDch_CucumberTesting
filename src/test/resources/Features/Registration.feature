Feature: Registration functionality
  As a new user
  I want to register on AskOmDch
  So that I can create an account


  Background:
    Given I am on the AskOmDch home page

  Scenario: Successful registration with valid details
    When I navigate to my account page
    And I register with username "tester@askomdch.com" and password "testkey1234"
    Then I should see that registration was successful

  Scenario: Failed registration with existing email
    When I navigate to my account page
    And I register with username "testuser@askomdch.com" and password "testpass123"
    Then I should see a registration error message

  Scenario: Failed registration with blank email
    When I navigate to my account page
    And I register with username "" and password "testpass123"
    Then I should see a registration error message

  Scenario: Failed registration with blank password
    When I navigate to my account page
    And I register with username "newuser@askomdch.com" and password ""
    Then I should see a registration error message

  Scenario: Failed registration with weak password
    When I navigate to my account page
    And I register with username "newuser2@askomdch.com" and password "123"
    Then I should see a registration error message