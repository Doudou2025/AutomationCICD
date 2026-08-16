package rahulshettyacademy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = ".action__submit")
    WebElement submit;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    By results = By.cssSelector(".ta-item.list-group-item.ng-star-inserted");

    public void selectCountry(String countryName) {

        // Aspetta che il campo Country sia disponibile
        waitForWebElementToAppear(country);

        // Inserisce il nome del paese
        country.sendKeys(countryName);

        // Aspetta che compaia il risultato
        waitForElementToAppear(results);

        // Prende il primo risultato
        WebElement countryOption = driver.findElements(results).get(0);

        // Click centralizzato
        clickElement(countryOption);
    }

    public ConfirmationPage submitOrder() {

        // Aspetta che il pulsante sia disponibile
        waitForWebElementToAppear(submit);

        // Click centralizzato
        clickElement(submit);

        return new ConfirmationPage(driver);
    }
}
