package Pages.HMIWebScreen;

import Setup.TestSetup;
import Utilities.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Reporting.ExtentManager.getTest;
import static Utilities.TestUtils.*;

public class DashboardScreen extends TestSetup {

    @FindBy(xpath = "//*[text()='dashboard']")
    public WebElement hmiDashboard;

    @FindBy(xpath = "//*[text()='Configuration']")
    public WebElement configuration;

    @FindBy(xpath = "//*[text()='reports']")
    public WebElement report;

    @FindBy(xpath = "//*[text()='SLD']")
    public WebElement sld;

    @FindBy(xpath = "//*[text()='Log Out']")
    public WebElement logOutButton;

    @FindBy(xpath = "//span[normalize-space()='Global Developer']")
    public WebElement globalDeveloper;

    @FindBy(className ="all-patients-titlecol-xs-12col-sm-3ng-binding")
    public WebElement dashboardTitle;

    @FindBy(id ="btn-menu-account-profile")
    public WebElement settingButton;

    @FindBy(xpath ="//span[contains(text(),'Profile')]")
    public WebElement profileTab;

    @FindBy(xpath ="//span[contains(text(),'Notifications')]")
    public WebElement notificationTab;

    public void logOut() throws InterruptedException {
        clickOnElement(logOutButton, "log  out button");
        Thread.sleep(5000);
        webDriver.navigate().refresh();
    }

    public void clickDashboard() {
        clickOnElement(hmiDashboard, "Dashboard");
    }

    public void clickConfiguration() {
        clickOnElement(configuration, "Configuration");
    }

    public void clickReports() {
        clickOnElement(report, "Reports");
    }

    public void clickSld() {
        clickOnElement(sld, "SLD");
    }


    public void clickLogout() {
        if (isElementPresent(globalDeveloper, 10)) {
            clickOnElement(globalDeveloper, "Global Developer");
            clickOnElement(logOutButton, "Logout");
        } else {
            Log.info("Unable to logout");
            getTest().log(LogStatus.FAIL, "Unable to Logout" + logOutButton,
                    getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }


    public void settingClick() {
        waitTillElementIsDisplayed(settingButton, 10);
        clickOnElement(settingButton, "setting button");
    }

    public void navigateToProfileTab() {
        waitTillElementIsDisplayed(profileTab, 10);
        clickOnElement(profileTab, "profile button");
    }

    public void navigateToNotificationTab() {
        waitTillElementIsDisplayed(profileTab, 10);
        clickOnElement(notificationTab, "Notifications button");
    }

    public void verifyUserIsOnDashboardScreen() {
        elementIsDisplayed(hmiDashboard, "Dashboard");
    }
}
