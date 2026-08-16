package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    By cartProductsBy = By.cssSelector(".cartSection h3");

    public Boolean VerifyProductDisplay(String productName) {

        waitForElementToAppear(cartProductsBy);

        return cartProducts
                .stream()
                .anyMatch(product ->
                        product.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout() {

        clickElement(checkoutEle);

        return new CheckoutPage(driver);
    }
}