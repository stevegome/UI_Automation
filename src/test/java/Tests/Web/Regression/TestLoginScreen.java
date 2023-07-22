package Tests.Web.Regression;


import Setup.TestSetup;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static Interfaces.ClassObjects.login;
import static Interfaces.ClassObjects.dashboard;
import static Utilities.TestUtils.*;


public class TestLoginScreen extends TestSetup {

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() throws Exception {
        openUrl(getValueFromJson("URL.HMI_LoginURL"));
        if (!webDriver.getTitle().equals(getMessagesFromJson("ScreenTitles.LoginScreenTitle"))) {
            openUrl(getValueFromJson("URL.HMI_LoginURL"));
        }
    }

    @Test(description = "[Login] : Verify as an HMI user that I see an error message when login fails", groups = {"web", "HMI","HMI_regression"})
    public void verifyInvalidLogin() throws IOException {
        login.userLogin(getValueFromJson("HMI.UserName"), getValueFromJson("HMI.InvalidPassword"));
        login.loginFailedErrorMessageIsDisplayed();
    }

    @Test(description = "[Login] : Verify as an HMI user I am able to login successfully ", groups = {"web","HMI","HMI_regression"})
    public void verifyValidLogin() throws IOException {
        login.userLogin(getValueFromJson("HMI.UserName"), getValueFromJson("HMI.Password"));
        elementIsDisplayed(dashboard.hmiDashboard, "Dashboard Button");
    }

    @Test(description = "[Login] : Verify as an HMI user that I see an error message when input params are empty", groups = {"web","HMI","HMI_regression"})
    public void verifyEmptyLogin() throws IOException {
         login.userLogin(getValueFromJson("HMI.EmptyUserName"), getValueFromJson("HMI.EmptyUserName"));
         captureScreenshot();
    }
}
