package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckoutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.OrderPage;
import rahulshettyacademy.PageObjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("italy");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfermationPage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	// test webhook
	// To verify ZARA COAT 3 is displaying in order page

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("mamadoujava@gmail.com", "Doudou26!");
		OrderPage ordersPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	//Prendere lo screenshoot quando il codice falice


	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap (System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
	
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "mamadoujava@gmail.com");
//	map.put("password", "Doudou26!");
//	map.put("product", "ZARA COAT 3");

//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "mamadousocio93@gmail.com");
//	map1.put("password", "Doudou26!!");
//	map1.put("product", "ADIDAS ORIGINAL");
}
