package rahulshettyacademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {
//test ngrok today
//test ngrok today 2
    @Test(
        groups = {"ErrorHandling"},
        retryAnalyzer = Retry.class
    )
    public void LoginErrorValidation() throws IOException {

        landingPage.loginApplication(
            "mamadoujava@gmail.com",
            "PasswordSbagliata123!"
        );

        Assert.assertEquals(
            landingPage.getErrorMessage(),
            "Incorrect email or password."
        );
    }
}