package Pages.HMIWebScreen;

import Setup.TestSetup;
import Utilities.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Reporting.ExtentManager.getTest;
import static Utilities.TestUtils.*;

public class DashboardScreen extends TestSetup {


    @FindBy(xpath = "//*[text()='Swag Labs']")
    public WebElement userDashboard;

    @FindBy(xpath = "//*[@class='bm-burger-button']")
    public WebElement hamburgerMenuButton;

    @FindBy(xpath = "//*[text()='Logout']")
    public WebElement logOutButton;


    public void clickLogout() {
        if (isElementPresent(hamburgerMenuButton, 10)) {
            clickHamburgerMenu();
            clickOnElement(logOutButton, "Logout");
        } else {
            Log.info("Unable to logout");
            getTest().log(LogStatus.FAIL, "Unable to Logout" + logOutButton,
                    getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }


    public void clickHamburgerMenu() {
        waitTillElementIsDisplayed(hamburgerMenuButton, 10);
        clickOnElement(hamburgerMenuButton, "setting button");
    }


    public void dashboardScreenTitleDisplayed() {
        waitTillElementIsDisplayed(userDashboard,20);
        elementIsDisplayed(userDashboard, "Sauce Lab Title");
    }
}
