package rahulshettyacademy.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement passwordEle;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String email, String password) {

        waitForWebElementToAppear(userEmail);

        userEmail.clear();
        userEmail.sendKeys(email);

        passwordEle.clear();
        passwordEle.sendKeys(password);

        clickElement(submit);

        return new ProductCatalogue(driver);
    }

    public String getErrorMessage() {

        waitForWebElementToAppear(errorMessage);

        return errorMessage.getText();
    }

    public void goTo() {

        driver.get("https://rahulshettyacademy.com/client");
    }
}