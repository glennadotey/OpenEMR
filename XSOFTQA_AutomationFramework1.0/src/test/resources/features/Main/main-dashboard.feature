#Author: abdul.tahir-akinyele@xsoftqa.com
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
# Main Dashboard feature
@OpenEMR @main-dashboard
Feature: Main Dashboard feature
  As a user of the OpenEMR i want to be able to login and see the actions I can perform.

  @SmokeTest @end2end @test-seq-1
  Scenario: System Admin dashboard
    Given I am logged in as an Adminstrator
    When I logged in successfully as an Admin
    Then I can see my full name on the top right corner of the page
    And I can see the actions i can performed as follows as an Admin:
      | Calendar       |
      | Flow Board     |
      | Recall Board   |
      | Messages       |
      | Patient/Client |
      | Fees           |
      | Modules        |
      | Inventory      |
      | Procedures     |
      | Administration |
      | Reports        |
      | Miscellaneous  |
      | Popups         |
      | About          |
    And can see the months calender and today's date

  @SmokeTest @test-seq-2
  Scenario: Physician dashboard
    Given I am logged in as a Physician
    When I logged in successfully as a Physician
    Then I can see the actions i can performed as follows as a Physician:
      | Calendar       |
      | Flow Board     |
      | Recall Board   |
      | Messages       |
      | Patient/Client |
      | Fees           |
      | Inventory      |
      | Procedures     |
      | Reports        |
      | Miscellaneous  |
      | Popups         |
      | About          |
    And can not see the following menus as a Physician:
      | Modules        |
      | Administration |

  @SmokeTest @test-seq-3
  Scenario: Clinician dashboard
    Given I am logged in as an Clinician
    When I logged in successfully as a Clinician
    Then I can see the actions i can performed as follows as a Clinician:
      | Calendar       |
      | Flow Board     |
      | Recall Board   |
      | Messages       |
      | Patient/Client |
      | Fees           |
      | Inventory      |
      | Procedures     |
      | Reports        |
      | Miscellaneous  |
      | Popups         |
      | About          |
    And can not see the following menus as a Clinician:
      | Modules        |
      | Administration |

  @SmokeTest @test-seq-4
  Scenario: Accountant dashboard
    Given I am logged in as an Accountant
    When I logged in successfully as a Accountant
    Then I can see the actions i can performed as follows as a Accountant:
      | Calendar       |
      | Flow Board     |
      | Recall Board   |
      | Patient/Client |
      | Fees           |
      | Administration |
      | Reports        |
      | Popups         |
      | About          |
    And can not see the following menus as a Accountant:
      | Messages      |
      | Modules       |
      | Inventory     |
      | Procedures    |
      | Miscellaneous |

  @SmokeTest @test-seq-5
  Scenario: Receptionist dashboard
    Given I am logged in as a Receptionist
    When I logged in successfully as a Receptionist
    Then I can see the actions i can performed as follows as a Receptionist:
      | Calendar       |
      | Flow Board     |
      | Recall Board   |
      | Messages       |
      | Patient/Client |
      | Reports        |
      | Popups         |
      | About          |
    And can not see the following menus as a Receptionist:
      | Fees           |
      | Modules        |
      | Administration |
      | Inventory      |
      | Procedures     |
      | Miscellaneous  |

  @SmokeTest @test-seq-6
  Scenario Outline: This is just an example
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status    |
      | name1 |     5 | POSSITIVE |
      | name2 |     7 | NEGATIVE  |
