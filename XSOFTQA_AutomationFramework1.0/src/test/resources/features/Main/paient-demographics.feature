#Author: xsoftqa@gmail.com
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
## Patient Demographics
@patient-demographics
Feature: Patient Demographics
  As a hospital receptionist i want to be able to enter patients demographics.

  @SmokeTest @test-seq-1
  Scenario: Track patient demographics
    Given I login as a receptionist
    When i navigate to the patient demographics
    Then I should see the following fields to enter the patient information
      | Name           |
      | DOB            |
      | Sex            |
      | External ID    |
      | SS             |
      | Marital status |
      | License/ID     |
      | Billing Notes  |
