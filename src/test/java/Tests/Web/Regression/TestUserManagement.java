package Tests.Web.Regression;

import Setup.TestSetup;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static Interfaces.ClassObjects.*;
import static Utilities.TestUtils.*;

public class TestUserManagement extends TestSetup {
    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() throws Exception {
        openUrl(getValueFromJson("URL.HMI_LoginURL"));
        if (!webDriver.getTitle().equals(getMessagesFromJson("ScreenTitles.LoginScreenTitle"))) {
            clearBrowserHistory();
            openUrl(getValueFromJson("URL.HMI_LoginURL"));
        }
    }

    @Test(description = "[User Management] : Create and Delete User",groups = {"UserManagement","HMI1"})
    public void createAndDeleteUser() throws IOException, InterruptedException {
        login.login(getValueFromJson("HMI.UserName"), getValueFromJson("HMI.Password"));
        elementIsDisplayed(dashboard.dashboard, "Dashboard Button");
        dashboard.clickConfiguration();
        configuration.clickLocalAccountManagement();
        configuration.verifyContentsUnderUsers();
        configuration.clickAddUser();
        configuration.verifyContentDisplayedUnderAddUserBox();
        clearAndEnterText(configuration.email_input,getValueFromJson("HMI.Email"),"Email Field");
        clearAndEnterText(configuration.firstName_input,getValueFromJson("HMI.FirstName"),"FirstName");
        clearAndEnterText(configuration.lastName_input,getValueFromJson("HMI.LastName"),"LastName");
        clearAndEnterText(configuration.phoneNo_input,getValueFromJson("HMI.PhoneNumber"),"PhoneNumber");
        clearAndEnterText(configuration.password_input,getValueFromJson("HMI.NewPassword"),"Password");
        clearAndEnterText(configuration.confirmPassword_input,getValueFromJson("HMI.NewPassword"),"Confirm Password");
        clickOnElement(configuration.viewer_radio_box,"Viewers");
        clickOnElement(configuration.save_button,"Save");
        textVerification(configuration.newUserCreatedText, getMessagesFromJson("Alerts.UserCreationAlert"));
        webDriver.navigate().refresh();
        configuration.clickNewlyCreatedUser();
        isElementEnabled(configuration.deleteUserButton,"Delete User",true);
        clickOnElement(configuration.deleteUserButton,"Delete User");
        isElementEnabled(configuration.deleteUserButton,"Delete User",true);
        clickOnElement(configuration.deleteUserButton,"Delete User");
        textVerification(configuration.userDeletedText, getMessagesFromJson("Alerts.UserDeletedAlert"));


    }
}
