@flow
Feature: Careers Feature

  Scenario: Apply for a career and fill the application form with only mandatory fields
    Given user navigates to the homepage
    And clicks on the 'Careers' link at the footer
    When user clicks on career card with 'Lead Network Engineer' at 'London, London' in 'Operations Team'
    And fills the application form with 'user1' data
    Then verify that the application has been successfully submitted

  Scenario: Apply for a career and fill the application form with all fields and attached CV and cover letter
    Given user navigates to the homepage
    And clicks on the 'Careers' link at the footer
    When user clicks on career card with 'Lead Network Engineer' at 'London, London' in 'Operations Team'
    And fills the application form with 'user2' data
    Then verify that the application has been successfully submitted

  Scenario: Apply for a career and fill the application form with all fields and written CV and cover letter
    Given user navigates to the homepage
    And clicks on the 'Careers' link at the footer
    When user clicks on career card with 'Lead Network Engineer' at 'London, London' in 'Operations Team'
    And fills the application form with 'user3' data
    Then verify that the application has been successfully submitted