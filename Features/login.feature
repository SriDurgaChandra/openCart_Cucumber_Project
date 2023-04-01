Feature: Login test with valid credentials

  @sanity
  Scenario: Login with valid credentials
    Given User launches browser window
    And opens url "http://localhost/opencart/upload/index.php?route=common/home"
    When User navigates to my account menu
    And clicks on login
    And enters email "pavanonlinetraining@gmail.com" and password "test@123"
    And clicks login button
    Then user nagivates in My account page

