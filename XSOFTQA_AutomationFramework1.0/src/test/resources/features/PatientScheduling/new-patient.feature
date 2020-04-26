#Author: glennadotey@hotmail.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Feature 52: Patient Scheduling
@OpenEMR @New-Patient
Feature: Patient Scheduling
  I want to use this template for my feature file
  
  ## User Story 62: New Patient
  @RegressionTest @SmokeTest @test-seq-1
  Scenario: Verify New Patient
    Given I am logged in as a receptionist
    When I navigate to the Flow Board
    And I select "New Patient" from the first drop down list
    Then I should only see the "New Patient" and the following columns:
      | PID            |
      | Patient        |
      | Encounter      |
      | Appt date      |
      | Appt time      |
      | Arrive Time    |
      | Appt Status    |
      | Current Status |
      | Visit Type     |
      | Provider       |
      | Total Time     |
      | Check Out Time |
      | Updated by     |

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
