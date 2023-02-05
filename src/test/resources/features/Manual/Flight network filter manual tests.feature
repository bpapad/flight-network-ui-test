#@regression
Feature: Flight network filter manual tests

  @Manual
  @Manual:Passed
  Scenario Outline: The Results page filters are displayed correctly
    Given User starts scheduling a flight at the flight network webpage
    When User selects <trip> as tripType
    And User inputs  as origin Athens
    And User inputs as destination Amsterdam
    And User selects as departure date 1-04-2023
    And User selects as return date 1-05-2023
    And User presses Search flights button to get results
    Then The browser redirects the user to results page
    And The origin and destination are displayed correctly
    When User expands the filter section
    Then All the filters are displayed correctly


    Examples:
    | trip       |
    | Return     |
    | One-way    |
    | Multi-city |
