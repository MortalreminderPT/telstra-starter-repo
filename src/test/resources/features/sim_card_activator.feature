Feature: Can SIM card be activated?

  Scenario: 1255789453849037777 can be activated
    Given I have a new SIM card with iccid "1255789453849037777" and email "test@gmail.com"
    When I request to activate the SIM card
    Then the sim card should be activated

  Scenario: 8944500102198304826 can not be activated
    Given I have a new SIM card with iccid "8944500102198304826" and email "test@gmail.com"
    When I request to activate the SIM card
    Then the sim card should not be activated