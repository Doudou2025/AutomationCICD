package rahulshettyacademy.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
}
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfermationPage () {
		return confirmationMessage.getText();
	}
	
	
}
