@regression
Feature: Flight network filter UI test

  Background:
    Given User navigates to flight-network webpage


  Scenario Outline: The Results page filters are displayed correctly
    When User selects <trip> trip type
    And User inputs Athens as origin
    And User inputs Amsterdam as destination
    And User selects default as departure date
    And User selects default as return date
    And User presses Search flights button
    Then User is redirected to results page
    And The selected origin and destination are displayed correctly
    When User selects to expand the filter section
    Then All filters are displayed correctly


    Examples:
    | trip       |
    | Return     |
    | One-way    |
    #todo: implement code for Multi-city trip | Multi-city |


  Scenario Outline: The 'Number of stops' filter works correctly for <stops> stops
    When User selects Return trip type
    When User inputs <origin> as origin
    And User inputs <destination> as destination
    And User selects <date> as departure date
    And User selects <date> as return date
    And User presses Search flights button
    Then User is redirected to results page
    And The selected origin and destination are displayed correctly
    When User selects to expand the filter section
    And User selects <stops> stops for the Number of stops filter
    Then Results displayed contain flights with <stops> stops only
    And Applied filters contain Stops

    Examples:
    | origin         | destination | date    | stops |
    | Athens         | Warsaw      | default | 0     |
    | Fort de France | Milan       | default | 1     |
    | Rome           | Berlin      | default | all   |


  Scenario Outline: The 'Price' filter works correctly for <price> prices
    When User selects Return trip type
    When User inputs <origin> as origin
    And User inputs <destination> as destination
    And User selects <date> as departure date
    And User selects <date> as return date
    And User presses Search flights button
    Then User is redirected to results page
    And The selected origin and destination are displayed correctly
    When User selects to expand the filter section
    And User selects <price> price for the Price filter
    Then Results displayed contain flights with <price> price only
    And Applied filters contain Price

    Examples:
      | origin         | destination | date    | price             |
      | Athens         | Warsaw      | default | increased lowest  |
      | Fort de France | Milan       | default | decreased highest |


  Scenario Outline: The 'Airlines' filter works correctly for <airlines> airlines
    When User selects Return trip type
    When User inputs <origin> as origin
    And User inputs <destination> as destination
    And User selects <date> as departure date
    And User selects <date> as return date
    And User presses Search flights button
    Then User is redirected to results page
    And The selected origin and destination are displayed correctly
    When User selects to expand the filter section
    And User selects <airlines> airlines for the Airlines filter
    Then Results displayed contain flights with <airlines> airlines
    And Results displayed do not contain flights without <airlines> airlines
    And Applied filters contain Airlines

    Examples:
      | origin         | destination | date    | airlines        |
      | Athens         | Warsaw      | default | Aegean          |
      | Athens         | Warsaw      | default | Ryanair         |
      | Athens         | Warsaw      | default | Aegean, Ryanair |


  Scenario: The 'Airlines' and 'Number of stops' filters work correctly for <airlines> and <stops> requests
    When User selects Return trip type
    When User inputs Athens as origin
    And User inputs Warsaw as destination
    And User selects default as departure date
    And User selects default as return date
    And User presses Search flights button
    Then User is redirected to results page
    And The selected origin and destination are displayed correctly
    When User selects to expand the filter section
    And User selects 1 stops for the Number of stops filter
    And User selects Aegean airlines for the Airlines filter
    Then Results displayed contain flights with <stops> stops only
    And Results displayed contain flights with <airlines> airlines
    And Results displayed do not contain flights without <airlines> airlines
    And Applied filters contain Airlines
    And Applied filters contain Stops


  Scenario: The Results page filters are reset correctly
      When User selects Return trip type
      When User inputs Athens as origin
      And User inputs Warsaw as destination
      And User selects default as departure date
      And User selects default as return date
      And User presses Search flights button
      Then User is redirected to results page
      And The selected origin and destination are displayed correctly
      When User selects to expand the filter section
      And User selects 1 stops for the Number of stops filter
      And User selects Aegean airlines for the Airlines filter
      Then Results displayed contain flights with <stops> stops only
      And Results displayed contain flights with <airlines> airlines
      And Results displayed do not contain flights without <airlines> airlines
      And Applied filters contain Airlines
      And Applied filters contain Stops
      When User selects to Reset all filters
      Then Applied filters do not contain Airlines
      And Applied filters do not contain Stops



  #todo: Implement code to test the following scenarios

#This scenario has test steps and assertions similar to Scenario Outline: The 'Price' filter works correctly for <price> prices
#  Scenario: The 'Trip Departure/Arrival' filter works correctly


#This scenario has test steps and assertions similar to Scenario Outline: The 'Price' filter works correctly for <price> prices
#  Scenario: The 'Travel time' filter works correctly
