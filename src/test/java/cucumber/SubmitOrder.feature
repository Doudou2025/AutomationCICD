@tag

Feature: Purchase the order from Ecommerce Website
	I want to use this template for my feature file

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submittting order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and sumit the ordre
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples:
      | name                  | password      | productName |
      | mamadoujava@gmail.com | Doudou26!     | ZARA COAT 3 |
      
      
      
