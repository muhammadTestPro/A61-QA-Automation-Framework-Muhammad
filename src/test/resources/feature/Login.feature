Feature: Login feature

  Scenario: Login Scenario
    Given I open Login Page
    When I enter email "marcello.ferraz.vieira@testpro.io"
    And I enter Password "TestPro@123"
    And I submit
    Then I am logged in

  Scenario Outline: Invalid Login Scenario
    Given I open Login Page
    When I enter email "<email>"
    And I enter Password "<password>"
    And I submit
    Then I am not logged in
    Examples:
      | email                   | password      |
      | wrongemail@testpro.io   | te$tStudent   |
      | marcello.vieira@testpro.io  | TestPro@1234 |
      |                                    |             |

