package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css = "[routerlink*='orders']")
	WebElement orderHeader;

	public void waitForWebElementToAppear(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToAppear(By locator) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementToDisappear(WebElement element) {

		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void clickElement(WebElement element) {

		try {
			// Porta l'elemento al centro della viewport
			((JavascriptExecutor) driver)
					.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);

			// Aspetta che sia cliccabile
			wait.until(ExpectedConditions.elementToBeClickable(element));

			// Click normale Selenium
			element.click();

		} catch (Exception e) {

			// Se un overlay intercetta il click,
			// usa JavaScript come fallback
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}

	public CartPage goToCartPage() {

		waitForWebElementToAppear(cartHeader);

		clickElement(cartHeader);

		return new CartPage(driver);
	}

	public CartPage goToCartPageObject() {

		clickElement(cartHeader);

		return new CartPage(driver);
	}

	public OrderPage goToOrderPage() {

		clickElement(orderHeader);

		return new OrderPage(driver);
	}
}
