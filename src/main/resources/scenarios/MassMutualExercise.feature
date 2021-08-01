@AutomationProjectMassMutual
Feature: MassMutual Project Feature

  @ValidationOfCompleteProblem
  Scenario Outline: Validation of Right count of values on screen
    Given Open MassMutualProblem Application for "<ScenarioID>"
    Then Validate that right count of values appear on the Screen
    Examples:
      |ScenarioID|
      |TC001     |

  @ValidationOfCompleteProblem
  Scenario Outline: Validation if values on the Screen are greater than Zero
    Given Open MassMutualProblem Application for "<ScenarioID>"
    Then Validate that Values on the screen are greater than 0
    Examples:
      |ScenarioID|
      |TC002     |

  @ValidationOfCompleteProblem
  Scenario Outline: Validation of Total balance
    Given Open MassMutualProblem Application for "<ScenarioID>"
    Then Validate that Total balance is correct based on values listed on screen
    Examples:
      |ScenarioID|
      |TC003     |

  @ValidationOfCompleteProblem
  Scenario Outline: Validation of Values  formatted as Currencies
    Given Open MassMutualProblem Application for "<ScenarioID>"
    Then Validate that Values are formatted as Currencies
    Examples:
      |ScenarioID|
      |TC004     |

  @ValidationOfCompleteProblem
  Scenario Outline: Validation of Complete problem
    Given Open MassMutualProblem Application for "<ScenarioID>"
    Then Validate that right count of values appear on the Screen
    Then Validate that Values on the screen are greater than 0
    Then Validate that Total balance is correct based on values listed on screen
    Then Validate that Values are formatted as Currencies
    Examples:
    |ScenarioID|
    |TC005     |