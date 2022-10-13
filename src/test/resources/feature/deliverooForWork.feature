@flow
Feature: Deliveroo For Work Feature

  Scenario: Deliveroo For Work scenario with only mandatory fields
    Given user navigates to the homepage
    And clicks on 'Deliveroo for Work' card
    When the registration forms are filled with the valid data of 'user1'
    Then the request should be successfully completed

  Scenario: Deliveroo For Work scenario with all fields and all the employees are added manually
    Given user navigates to the homepage
    And clicks on 'Deliveroo for Work' card
    When the registration forms are filled with the valid data of 'user2'
    Then the request should be successfully completed