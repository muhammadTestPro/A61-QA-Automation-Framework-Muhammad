Feature: Login feature

  Scenario: Login scenario
    Given I open Login Page
    When I enter email "kristofer.juhasz@testpro.io"
    And I enter password "Logintest1!"
    And I submit
    Then I am logged in

  Scenario Outline: Negative Login scenario
    Given I open Login Page
    When I enter email "<email>"
    And I enter password "<password>"
    And I submit
    Then I am not logged in
    Examples:
      | email | password |
      | random@email.com           | Logintest1!       |
      | invalidemail@testpro.io    | invalidpass       |
      | kristofer.juhasz@testpro.io|                   |
      | kristofer.juhasz@testpro.io| wrongpassword     |
      |                            | Logintest1!       |