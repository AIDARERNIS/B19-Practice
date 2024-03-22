Feature: Sign Up new customer functionality
  Scenario: Positive scenario fill out all the info standard way
    Given user is ain page user clicks on create account button
    Then  user is on create account page and verifies title
     |title|Create New Customer Account|
    And user enters first name and last name
     |firstname|Kobe|
     |lastname |Bryan|
    When user enters email and password and confirms the password
     |email|bellinhg@test.com|
     |password|Abc2365764#% |
     |confirmPassword|Abc2365764#%|
    Then user clicks create account and verifies success message
     |successMessage|Thank you for registering with Main Website Store.|


