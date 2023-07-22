package Pages.HMIWebScreen;

import Setup.TestSetup;
import Utilities.ApiCommonMethods;
import Utilities.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

import static Reporting.ExtentManager.getTest;
import static Utilities.TestUtils.*;


public class LoginScreen extends TestSetup {

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailAddressTextBox;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordTextBox;

    @FindBy(xpath = "//div[@class='bottom-spacing text-danger']")
    public WebElement loginFailedMessage;

    @FindBy(linkText = "Forgot your password?")
    public WebElement forgotPasswordLink;

    @FindBy(xpath = "//a[contains(text(),'Terms of Use')]")
    public WebElement termsOfUserLink;

    @FindBy(xpath = "//h2[contains(text(),'TERMS & CONDITIONS OF USE')]")
    public WebElement termsOfUserText;

    @FindBy(xpath = "//a[contains(text(),'Privacy Statement')]")
    public WebElement privacyStatementLink;

    @FindBy(xpath = "//h2[contains(text(),'HEALTHCARE PROVIDER PRIVACY NOTICE')]")
    public WebElement privacyStatementText;

    @FindBy(xpath = "//h2[contains(text(),'Access Forbidden')]")
    public WebElement accessForbiddenText;

    @FindBy(xpath = "//div[contains(text(),'This site is intended for US Healthcare profession')]")
    public WebElement siteIntendedText;

    @FindBy(xpath = "//a[contains(text(),'Back to Login')]")
    public WebElement backToLoginLink;

    @FindBy(xpath = "//*[@id='footer-wrapper']//p")
    public WebElement disclaimerText;

    @FindBy(xpath = "checkbox")
    public WebElement checkBox;

    public void enterEmail(String email) {
        enterText(emailAddressTextBox, email, "email text box");
    }

    public void enterPassword(String password) {
        enterText(passwordTextBox, password, "password text box");
    }

    public void clickSubmitButton() {
        clickOnElement(loginButton, "login button");
    }

    public void userLogin(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSubmitButton();
        String alarmDescription = ApiCommonMethods.getDescriptionOfFirstAlarm();
        Log.info("Alarm description is : " + alarmDescription);
        getTest().log(LogStatus.PASS,
                "Alarm description is : "+ alarmDescription,
                getTest().addBase64ScreenShot(captureScreenshot()));
    }

    public boolean isLoginScreen() throws IOException {
        return verifyWebPageTitle(getMessagesFromJson("ScreenTitles.LoginScreenTitle"));
    }

    public void loginFailedErrorMessageIsDisplayed() throws IOException {
        elementIsDisplayed(loginFailedMessage, "Login Failed Message");
        textVerification(loginFailedMessage, getMessagesFromJson("ErrorMessages.HMI_LoginFailed"));
    }


    public void clickForgotPasswordLink() {
        clickOnElement(forgotPasswordLink, "Forgot Password Link ");
    }

    public void clickTermsOfUserLink() {
        clickOnElement(termsOfUserLink, "Terms of Use");
    }

    public void clickPrivacyStatementLink() {
        clickOnElement(privacyStatementLink, "Privacy Statement link");
    }

}
