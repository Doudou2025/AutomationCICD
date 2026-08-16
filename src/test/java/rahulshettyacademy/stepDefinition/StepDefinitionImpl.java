package rahulshettyacademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckoutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
	    landingPage = launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password){
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When ("^I add product (.+) to Cart$")
	public void I_add_product_product_to_Cart(String productName) {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When ("^Checkout (.+) and sumit the ordre$")
	public void Checkout_sumit_the_ordre (String productName){
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("italy");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then ("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage (String string) {
		String confirmMessage = confirmationPage.getConfermationPage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	@Then ("^\"([^\"]*)\" message is displayed$") 
	public void something_message_is_displayed (String strArg1) throws Throwable {
		 Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		 driver.close();
	}
	
	

}
