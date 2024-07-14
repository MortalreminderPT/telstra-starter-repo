Feature: Can SIM card be activated?

  Scenario: functional sim can be activated
    Given a functional sim card
    When I request to activate the SIM card
    Then the sim card should be activated and its state is recorded to the database

  Scenario: broken sim can not be activated
    Given a broken sim card
    When I request to activate the SIM card
    Then the sim card should not be activated and its state is recorded to the database