Feature: Timeseries API

  Scenario Outline: Get timeseries data with valid parameters
    Given I use "valid" API key
    When I request timeseries data with start date <start_date>, end date <end_date>, base <base>, and symbols <symbols>
    Then the response status code should be 200
    And the response success should be "true"

    Examples:
      | start_date   | end_date     | base | symbols      |
      | "2022-01-01" | "2022-01-02" | "EUR"| "USD,GBP"    |
      | "2022-01-01" | "2022-01-02" | "EUR"| "USD"        |
      | "2022-01-01" | "2022-01-02" | "USD"| "USD,GBP,RSD"|
      | "2022-01-01" | "2022-01-02" |  ""  | "USD"        |
      | "2022-01-01" | "2022-01-02" | "EUR"|  ""          |
      | "2022-01-01" | "2022-01-02" |  ""  |  ""          |
      # Last one tests boundary value of 365 days
      | "2022-01-01" | "2023-01-01" | "EUR"| "USD"        |

  Scenario Outline: Get timeseries data with different types of API keys
    Given I use <api_key_type> API key
    When I request timeseries data with start date "2022-01-01", end date "2022-01-01", base "EUR", and symbols "USD,GBP"
    Then the response status code should be <status>

    Examples:
      | api_key_type  | status |
      | "valid"       |  200   |
      | "invalid"     |  401   |
      | "forbidden"   |  403   |
      | "rate_limited"|  429   |

  @Bug
  Scenario: Get timeseries data with non-existing endpoint
    Given I use "valid" API key
    When I request timeseries data with start date "2022-01-01", end date "2022-01-02", base "EUR", and symbols "USD,GBP"
    Then the response status code should be 404

  @Bug
  Scenario: Get timeseries data with http request (instead of https)
    Given I use "valid" API key
    When I request timeseries data with start date "2022-01-01", end date "2022-01-02", base "EUR", and symbols "USD,GBP"
    Then the response status code should be 400

  # For each of the tests below additional assertions could be performed (verifying error object), but for now I am just verifying success boolean
  Scenario: Get timeseries data with invalid date range (start date after end date)
    Given I use "valid" API key
    When I request timeseries data with start date "2022-01-31", end date "2022-01-01", base "EUR", and symbols "USD,GBP"
    Then the response status code should be 200
    And the response success should be "false"

  Scenario: Get timeseries data with invalid date format
    Given I use "valid" API key
    When I request timeseries data with start date "01-01-2022", end date "31-01-2022", base "EUR", and symbols "USD,GBP"
    Then the response status code should be 200
    And the response success should be "false"

  Scenario: Get timeseries data with unsupported base currency
    Given I use "valid" API key
    When I request timeseries data with start date "2022-01-01", end date "2022-01-31", base "XYZ", and symbols "USD,GBP"
    Then the response status code should be 200
    And the response success should be "false"

  Scenario: Get timeseries data with unsupported symbols
    Given I use "valid" API key
    When I request timeseries data with start date "2022-01-01", end date "2022-01-31", base "EUR", and symbols "XYZ,ABC"
    Then the response status code should be 200
    And the response success should be "false"

  Scenario: Get timeseries data with large date range
    Given I use "valid" API key
    When I request timeseries data with start date "2020-01-01", end date "2022-01-31", base "EUR", and symbols "USD,GBP"
    Then the response status code should be 200
    And the response success should be "false"

  Scenario: Get timeseries data with only start date
    Given I use "valid" API key
    When I request timeseries data with start date "2022-01-01", end date "", base "EUR", and symbols "USD,GBP"
    Then the response status code should be 200
    And the response success should be "false"

  Scenario: Get timeseries data with only end date
    Given I use "valid" API key
    When I request timeseries data with start date "", end date "2022-01-31", base "EUR", and symbols "USD,GBP"
    Then the response status code should be 200
    And the response success should be "false"
