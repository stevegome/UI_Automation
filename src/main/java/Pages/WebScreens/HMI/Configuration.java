package Pages.WebScreens.HMI;

import Setup.TestSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

import static Utilities.TestUtils.*;

public class Configuration extends TestSetup {

    @FindBy(xpath = "//*[text()='Local Account Management']")
    public WebElement localAccountManagement;

    @FindBy(xpath = "//*[text()='Operations']")
    public WebElement operations;

    @FindBy(xpath = "//*[text()='Server Configuration']")
    public WebElement serverConfiguration;

    @FindBy(xpath = "//*[text()='SLD']")
    public WebElement sld;

    @FindBy(xpath = "//*[text()='Log Out']")
    public WebElement logOutButton;

    @FindBy(xpath = "//span[normalize-space()='Global Developer']")
    public WebElement globalDeveloper;

    @FindBy(className = "all-patients-title col-xs-12 col-sm-3 ng-binding")
    public WebElement dashboardTitle;

    @FindBy(id = "btn-menu-account-profile")
    public WebElement settingButton;

    @FindBy(xpath = "//span[contains(text(),'Profile')]")
    public WebElement profileTab;

    @FindBy(xpath = "//span[contains(text(),'Notifications')]")
    public WebElement notificationTab;

    @FindBy(xpath = "//*[contains(text(), 'Users')]")
    public WebElement Users;

    @FindBy(xpath = "//*[contains(text(), 'Full Name')]")
    public WebElement fullName;

    @FindBy(xpath = "//*[contains(text(), 'Email')]")
    public WebElement Email;

    @FindBy(xpath = "//*[contains(text(), 'Group')]")
    public WebElement Group;

    @FindBy(xpath = "//*[contains(text(), 'Last Login')]")
    public WebElement LastLogin;

    @FindBy(xpath = "//span[normalize-space()='Add User']")
    public WebElement addUser;

    @FindBy(xpath = "//h2[text()='New User'] ")
    public WebElement NewUser;

    @FindBy(id = "email")
    public WebElement email_input;

    @FindBy(id = "firstName")
    public WebElement firstName_input;

    @FindBy(id = "lastName")
    public WebElement lastName_input;

    @FindBy(id = "phone")
    public WebElement phoneNo_input;

    @FindBy(id = "password")
    public WebElement password_input;

    @FindBy(id = "passwordConfirm")
    public WebElement confirmPassword_input;

    @FindBy(xpath = "//span[text()= 'Viewers']")
    public WebElement viewer_radio_box;

    @FindBy(xpath = "//span[text()= 'Save']")
    public WebElement save_button;

    @FindBy(xpath = "//span[text()= 'Administrators']")
    public WebElement administrators_radio_box;

    @FindBy(xpath = "//span[text()= 'Developers']")
    public WebElement developers_radio_box;

    @FindBy(xpath = "//span[text()= 'Operators']")
    public WebElement operators_radio_box;

    @FindBy(xpath = "//h2[text()='New User'] ")
    public WebElement NewUserText;

    @FindBy(xpath = "//input[@type='checkbox']")
    public WebElement site_timezone;

    @FindBy(xpath = "//div[@class='table-body']/a")
    public List<WebElement> nameRows;

    @FindBy(xpath = "//span[.='cancel']")
    public WebElement cancelButton;

    @FindBy(xpath = "//div[@role='alert'][text()= 'New user created'] ")
    public WebElement newUserCreatedText;

    @FindBy(xpath = "//span[@class='MuiButton-label'][text()= 'Delete User']")
    public WebElement deleteUserButton;

    @FindBy(xpath = "//div[@role='alert'][text()= 'User Deleted']")
    public WebElement userDeletedText;



    public void clickLocalAccountManagement() {
        clickOnElement(localAccountManagement, "Local Account Management");
    }

    public void verifyContentsUnderUsers() {
        elementIsDisplayed(Users, "Users");
        elementIsDisplayed(fullName, "Full Name");
        elementIsDisplayed(Email, "Email");
        elementIsDisplayed(Group, "Group");
        elementIsDisplayed(LastLogin, "Last Login");
    }

    public void clickAddUser() {
        isElementEnabled(addUser, "Add User", true);
        clickOnElement(addUser, "Add User");
    }

    public void verifyContentDisplayedUnderAddUserBox() throws IOException {
        textVerification(NewUserText, getMessagesFromJson("Texts.NewUser"));
        isElementEnabled(administrators_radio_box, "administrators", true);
        isElementEnabled(developers_radio_box, "developers", true);
        isElementEnabled(operators_radio_box, "operators", true);
        isElementEnabled(viewer_radio_box, "viewers", true);
        isElementSelected(site_timezone, "SiteTimezone", true);

    }

    public void clickNewlyCreatedUser() throws IOException {
        waitTillElementIsDisplayed(Users, 20);
        for (int i=1;i<=nameRows.size();i++) {
            if (webDriver.findElement(By.xpath("//div[@class='table-body']/a[" + i + "]/div[2]")).getText()
                    .equalsIgnoreCase(getValueFromJson("HMI.Email"))) {
                clickOnElement(webDriver.findElement(By.xpath("//div[@class='table-body']/a[" + i + "]/div[2]")),
                        "Name");
                break;
            }
        }

    }


}
