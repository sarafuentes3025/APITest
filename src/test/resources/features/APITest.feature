# Created by sarafuentes at 16/12/21
Feature:  Request example for Udemy

  @API
  Scenario: GET test to endpoint
    Given I send a GET request to the endpoint
    Then I get a 200 status code