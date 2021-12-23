# Created by sarafuentes at 16/12/21
Feature:  Request example for Udemy

  Scenario: GET test to endpoint
    Given I send a GET request to the https://api.github.com URI
    Then I get a 200 status code

  @API
  Scenario: Validate the number of entries in the response
    Given I send a GET request to the https://jsonplaceholder.typicode.com URI
    Then I validate there are 10 items on the /users endpoint