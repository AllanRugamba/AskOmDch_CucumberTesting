Feature: Login functionality
  As a registered user
  I want to login to AskOmDch
  So that I can access my account

  Background:
    Given I am on the AskOmDch home page

    Scenario: Successful login with valid credentials
      When I navigate to my account page
      And login with username "tester@askomdch.com" and password "testkey1234"
      Then I should see that I am logged in

    Scenario: Failed login with invalid credentials
      When I navigate to my account page
      And login with username "invalid_user@example.com" and password "wrongPassword"
      Then I should see an error message

    Scenario: Failed login with blank credentials
      When I navigate to my account page
      And login with username "" and password ""
      Then I should see an error message

    Scenario: Failed login with blank username
      When I navigate to my account page
      And login with username "" and password "testpass123"
      Then I should see an error message

    Scenario: Failed login with blank password
      When I navigate to my account page
      And login with username "testuser@askomdch.com" and password ""
      Then I should see an error message