package Tests.Web.Regression;

import Setup.TestSetup;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static Interfaces.ClassObjects.dashboard;
import static Interfaces.ClassObjects.login;
import static Utilities.TestUtils.*;


public class TestLoginScreen extends TestSetup {

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() throws Exception {
        openUrl(getValueFromJson("URL.LoginURL"));
        if (!webDriver.getTitle().equals(getMessagesFromJson("ScreenTitles.LoginScreenTitle"))) {
            openUrl(getValueFromJson("URL.LoginURL"));
        }
    }

    @Test(description = "[Login] : Verify user is able to logout successfully from SauceLab", groups = {"SauceLab"})
    public void verifyLogin() throws IOException {
        login.userLogin(getValueFromJson("SauceLab.UserName"), getValueFromJson("SauceLab.Password"));
        dashboard.dashboardScreenTitleDisplayed();
        dashboard.clickLogout();
    }


}
