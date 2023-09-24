package Pages.HMIWebScreen;

import Setup.TestSetup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

import static Utilities.TestUtils.clickOnElement;
import static Utilities.TestUtils.enterText;


public class LoginScreen extends TestSetup {

    @FindBy(id = "user-name")
    public WebElement input_username;

    @FindBy(id = "password")
    public WebElement input_password;

    @FindBy(id = "login-button")
    public WebElement loginButton;





    public void enterUsername(String username) throws IOException {
        enterText(input_username, username, "Username");
    }

    public void enterPassword(String password) {
        enterText(input_password, password, "password text box");
    }

    public void clickLoginButton() {
        clickOnElement(loginButton, "login button");
    }

    public void userLogin(String username, String password) throws IOException {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }


}
