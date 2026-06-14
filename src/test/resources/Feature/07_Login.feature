Feature: Login Functionality

  @Smoke @Login
  Scenario: Login with valid credentials
    When User login with "upendra" and "upendra23"
    Then User should be navigated to "Dashboard" page

  # @Login
  # Scenario Outline: Login with valid credentials
  #   When User login with <username> and <psw>
  #   Then User should be navigated to "Dashboard" page

  #   Examples:
  #     | username  | psw   |
  #     | "upendra" | "abc" |
  #     | "upendra" | "abc" |
  #     | "upendra" | "abc" |
  #     | "upendra" | "abc" |
  #     | "upendra" | "abc" |
  #     | "upendra" | "abc" |

  @Login
  Scenario Outline: Search with valid keywords
    When User Search with <product>


    Examples:
      | product  |
      | "Mobiles" | 
      | "Powerbanks" | 
      | "Tvs" | 
      | "Ac" | 
      | "Cooler" | 
      | "Table fan" | 
