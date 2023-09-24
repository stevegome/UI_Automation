package Utilities;

import Reporting.ExtentManager;
import Setup.TestSetup;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static Reporting.ExtentManager.getTest;

public class TestUtils extends TestSetup{

    /**
     * To validate element is displayed on the screen
     *
     * @param element
     * @param elementName
     */

    public static void elementIsDisplayed(WebElement element, String elementName) {
        waitTillElementIsDisplayed(element, 20);
        try {
            if (element.isDisplayed()) {
                Log.info(elementName + " is Displayed");
                getTest().log(LogStatus.PASS, elementName + " is Displayed",
                        getTest().addBase64ScreenShot(captureScreenshot()));
            }
        } catch (NoSuchElementException e) {
            Log.error(e.getMessage());
            getTest().log(LogStatus.FAIL, e.getMessage(), getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    /**
     * To validate element is not available on the screen
     *
     * @param element
     * @param elementName
     */
    public static void elementIsNotDisplayed(WebElement element, String elementName) {
        try {
            if (element.isDisplayed()) {
                getTest().log(LogStatus.FAIL, elementName + " is Displayed",
                        getTest().addBase64ScreenShot(captureScreenshot()));
                Log.info(elementName + " is Displayed");
            }
        } catch (Exception e) {
            Log.error("Element " + elementName + " is not displayed");
            getTest().log(LogStatus.PASS, "Element " + elementName + " is not displayed",
                    getTest().addBase64ScreenShot(captureScreenshot()));
            Log.info(elementName + " is not Displayed");
        }
    }

    /**
     * To verify button is in expected state or not
     *
     * @param element
     * @param buttonName
     * @param expectedBtnStatus
     */
    public static void verifyButtonStatus(WebElement element, String buttonName, boolean expectedBtnStatus) {
        waitTillElementIsDisplayed(element, 20);
        try {
            Boolean buttonStatus = element.isEnabled();
            String btnState;
            if (buttonStatus.equals(true)) {
                btnState = "enabled";
            } else {
                btnState = "disabled";
            }
            if (buttonStatus.equals(expectedBtnStatus)) {
                Log.info(buttonName + " is found to be " + btnState);
                getTest().log(LogStatus.PASS, buttonName + " is " + btnState,
                        getTest().addBase64ScreenShot(captureScreenshot()));
            } else {
                Log.info(buttonName + " is found to be " + btnState);
                getTest().log(LogStatus.FAIL, buttonName + " is " + btnState,
                        getTest().addBase64ScreenShot(captureScreenshot()));
            }
        } catch (Exception e) {
            getTest().log(LogStatus.FAIL, e.getMessage(), getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    /**
     * Verify the text on  element
     *
     * @param element
     * @param expectedText
     */

    public static void textVerification(WebElement element, String expectedText) {
        waitTillElementIsDisplayed(element, 20);
        try {
            if (element.isDisplayed()) {

                if (org.apache.commons.lang3.StringUtils.normalizeSpace(element.getText()).equals(expectedText)) {
                    Log.info("Element is displayed, \n Element Text is " + "'" + element.getText() + "'");
                    getTest().log(LogStatus.PASS,
                            "Element is displayed, \n Element Text is " + "'" + element.getText() + "'",
                            getTest().addBase64ScreenShot(captureScreenshot()));

                } else {
                    Log.error("Expected Text is " + "'" + expectedText + "'" + " but got " + "'" + element.getText()
                            + "'");
                    getTest().log(LogStatus.FAIL,
                            "Expected Text is " + "'" + expectedText + "'" + " " + "but got " + "'" + element.getText()
                                    + "'", getTest().addBase64ScreenShot(captureScreenshot()));
                }
            }
        } catch (NoSuchElementException e) {
            Log.error(e.getMessage());
            getTest().log(LogStatus.FAIL, e.getMessage(), getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    /**
     * Verify the text of given attribute, e.g value
     *
     * @param element
     * @param attribute
     * @param expectedValue
     */
    public static void textVerificationForAttribute(WebElement element, String attribute, String expectedValue) {
        waitTillElementIsDisplayed(element, 20);
        try {
            if (element.isDisplayed()) {
                if (org.apache.commons.lang3.StringUtils.normalizeSpace(element.getAttribute(attribute))
                        .equals(expectedValue)) {
                    Log.info("Element is displayed, \n Element Attribute :  " + attribute + " value is "
                            + element.getAttribute(attribute) + " which is expected");
                    getTest().log(LogStatus.PASS,
                            "Element is displayed, \n Element Attribute :  " + attribute + " value is "
                                    + element.getAttribute(attribute) + " which is expected",
                            getTest().addBase64ScreenShot(captureScreenshot()));

                } else {
                    Log.error(attribute + "  : expected attribute value is " + "'" + expectedValue + "'" + " but got "
                            + "'" + element.getAttribute(attribute) + "'");
                    getTest().log(LogStatus.FAIL,
                            attribute + "  : expected attribute value is " + "'" + expectedValue + "'" + " but got "
                                    + "'" + element.getAttribute(attribute) + "'",
                            getTest().addBase64ScreenShot(captureScreenshot()));
                }
            }
        } catch (NoSuchElementException e) {
            Log.error(e.getMessage());
            getTest().log(LogStatus.FAIL, e.getMessage(), getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    /**
     * Verify element list is >0
     *
     * @param element
     * @param elementName
     */

    public static void verifyElementListIsNotNull(List<WebElement> element, String elementName) {
        try {
            if (element.size() > 0) {
                getTest().log(LogStatus.PASS, elementName + " is displayed.",
                        getTest().addBase64ScreenShot(captureScreenshot()));
            }
        } catch (Exception e) {
            getTest().log(LogStatus.FAIL, elementName + " isn't displayed." + element,
                    getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    /**
     * Verify element text is present
     *
     * @param element
     * @param elementName
     */

    public static void verifyElementTextIsPresent(WebElement element, String elementName) {
        try {
            if (element.getText().length() > 0) {
                getTest().log(LogStatus.PASS,
                        elementName + " String is not empty, text present is: " + element.getText(),
                        getTest().addBase64ScreenShot(captureScreenshot()));
                Log.info(elementName + " String is not empty, text present is: " + element.getText());
            }
        } catch (Exception e) {
            getTest().log(LogStatus.FAIL, elementName + " string is empty.",
                    getTest().addBase64ScreenShot(captureScreenshot()));
            Log.info(elementName + " string is empty.");
        }
    }

    /**
     * Verify element list is == 0
     *
     * @param element
     * @param elementName
     */
    public static void VerifyElementListIsNull(List<WebElement> element, String elementName) {
        try {
            if (element.size() == 0) {
                getTest().log(LogStatus.PASS, elementName + " isn't displayed now.",
                        getTest().addBase64ScreenShot(captureScreenshot()));
            }
        } catch (Exception e) {
            getTest().log(LogStatus.FAIL, elementName + " is displayed." + element,
                    getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    /**
     * Click on element
     *
     * @param element
     * @param buttonName
     */
    public static void clickOnElement(WebElement element, String buttonName) {
        waitTillElementIsDisplayed(element, 20);
        try {
            element.click();
            Log.info("Tap on " + buttonName);
            getTest().log(LogStatus.PASS, "Tap on " + buttonName, getTest().addBase64ScreenShot(captureScreenshot()));
        } catch (Exception e) {
            try {
                if (webDriver != null) {
                    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", element);
                    Log.info("Tap on " + buttonName);
                    getTest().log(LogStatus.PASS, "Tap on " + buttonName,
                            getTest().addBase64ScreenShot(captureScreenshot()));
                }
            } catch (Exception e2) {
                Log.error(e2.getMessage());
                getTest().log(LogStatus.FAIL, e2.getMessage(), getTest().addBase64ScreenShot(captureScreenshot()));
            }
        }
    }

    /**
     * Cleart Textfield and enter text
     *
     * @param element
     * @param textToEnter
     * @param fieldName
     */
    public static void clearAndEnterText(WebElement element, String textToEnter, String fieldName) {
        waitTillElementIsDisplayed(element, 20);
        try {
            element.click();
            element.clear();
            Log.info(" Clearing text from " + fieldName);
            element.sendKeys(textToEnter);
            Log.info(" Entering text : " + textToEnter + " in " + fieldName);
            getTest().log(LogStatus.PASS, "Entered " + textToEnter + " in " + fieldName,
                    getTest().addBase64ScreenShot(captureScreenshot()));
        } catch (Exception e2) {
            Log.error(" Failed to enter text : " + textToEnter + " in " + fieldName);
            getTest().addBase64ScreenShot(captureScreenshot());
            getTest().log(LogStatus.FAIL, e2.getMessage(), getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    /**
     * Cleart Textfield
     *
     * @param element
     * @param fieldName
     */
    public static void clearText(WebElement element, String fieldName) {
        waitTillElementIsDisplayed(element, 20);
        try {
            Log.info(" Clearing text from " + fieldName);
            element.clear();
            getTest().addBase64ScreenShot(captureScreenshot());
            getTest().log(LogStatus.PASS, "Cleared text from : " + fieldName,
                    getTest().addBase64ScreenShot(captureScreenshot()));
        } catch (Exception e2) {
            Log.error(" Failed to clear text : " + " in " + fieldName);
            getTest().log(LogStatus.FAIL, e2.getMessage(), getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    /**
     * Enter Text in textfield
     *
     * @param element
     * @param textToEnter
     * @param fieldName
     */
    public static void enterText(WebElement element, String textToEnter, String fieldName) {
        waitTillElementIsDisplayed(element, 20);
        try {
            Log.info(" Entering text : " + textToEnter + " in " + fieldName);
            element.sendKeys(textToEnter);
            Log.info(textToEnter + " is entered in " + fieldName);
            getTest().log(LogStatus.PASS, "Entered " + textToEnter + " in " + fieldName,
                    getTest().addBase64ScreenShot(captureScreenshot()));

        } catch (Exception e2) {
            Log.error("Failed to enter text : " + textToEnter + " in " + fieldName);
            getTest().log(LogStatus.FAIL, e2.getMessage(), getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    /**
     * Check if element i selected or not
     *
     * @param element
     * @param fieldName
     * @param expectedStatus
     */
    public static void isElementSelected(WebElement element, String fieldName, Boolean expectedStatus) {
        Boolean elementStatus = element.isSelected();
        if (elementStatus.equals(expectedStatus)) {
            getTest().log(LogStatus.PASS, fieldName + "  selection Status is " + expectedStatus,
                    getTest().addBase64ScreenShot(captureScreenshot()));
        } else {
            getTest().log(LogStatus.FAIL, fieldName + " expected Status is " + expectedStatus + " ,But "
                    + "got " + elementStatus, getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    /**
     * Fluent wait
     *
     * @param timeout
     * @return
     */
    public static FluentWait<? extends WebDriver> fluentWait(int timeout) {
        FluentWait<? extends WebDriver> wait =
                new FluentWait<>(webDriver).withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
        return wait;
    }

    /**
     * Wait for absence
     *
     * @param element
     * @param timeout
     */
    public static void waitForAbsence(WebElement element, int timeout) {
        try {
            if (webDriver != null) {
                FluentWait<? extends WebDriver> wait =
                        new FluentWait<>(webDriver).withTimeout(Duration.ofSeconds(timeout))
                                .pollingEvery(Duration.ofSeconds(3));
                wait.until((Function<WebDriver, Boolean>) webDriver -> !element.isDisplayed());
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * fluent wait
     *
     * @param timeout
     * @return
     */

   /* public static FluentWait<? extends AppiumDriver<?>> fluentWait(int timeout) {
        FluentWait<? extends AppiumDriver<?>> wait =
                new FluentWait<>(mobileDriver).withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(3));
        return wait;
    }*/

    /**
     * Implicit wait
     *
     * @param timeout
     */
    public static void implicitWait(int timeout) {
        if (webDriver != null) {
            webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        }
    }


    /**
     * Capture Screenshot
     */
    public static void captureScreenshotInPrintLogs() {
        String base64Screenshot = null;
        if (webDriver != null) {
            base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).
                    getScreenshotAs(OutputType.BASE64);
        }
        ExtentManager.printLogs(getTest().addBase64ScreenShot(base64Screenshot));

    }

    public static String captureScreenshot() {
        String base64Screenshot = null;
        String base64 = null;
        FileInputStream fileInputStream = null;
        File source = null;
        if (webDriver != null) {
            source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

        }

        try {
            BufferedImage originalImage = ImageIO.read(source);
            BufferedImage newBuffImg =
                    new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            newBuffImg.createGraphics().drawImage(originalImage, 0, 0, Color.WHITE, null);
            originalImage.flush();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(newBuffImg, "jpg", baos);
            base64 = Base64.getEncoder().encodeToString(baos.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "data:image/png;base64," + base64;
    }

    /**
     * Get value from Testdata Json file , available in Testdata folder
     *
     * @param key
     * @return
     * @throws IOException
     */
    public static String getValueFromJson(String key) throws IOException {
        return com.jayway.jsonpath.JsonPath.read(TestSetup.jsonTestData, "$." + key);
    }

    /**
     * Get value from Expected Result Json file , available in Testdata folder
     *
     * @param key
     * @return
     * @throws IOException
     */
    public static String getMessagesFromJson(String key) throws IOException {
        return com.jayway.jsonpath.JsonPath.read(TestSetup.jsonExpectedResults, "$." + key);
    }

    /**
     * Explict wait till element is visible on the screen
     *
     * @param element
     * @param time
     */
    public static void waitTillElementIsDisplayed(WebElement element, int time) {
        if (webDriver != null) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }

    /**
     * Explict wait till element is clickable
     *
     * @param element
     * @param time
     */
    public static void waitTillElementIsClickable(WebElement element, int time) {
        if (webDriver != null) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    /**
     * Wait till Web Page To be loaded , WEB specific
     *
     * @param time
     */
    public static void waitTillPageToBeLoaded(int time) {
        if (webDriver != null) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(time));
            wait.until(driver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                    .equals("complete"));
        }
    }

    /**
     * Verify web page title
     *
     * @param expectedTitle
     */
    public static void verifyTitle(String expectedTitle) {
        String actualTitle = webDriver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        getTest().log(LogStatus.PASS, expectedTitle + " title is Displayed",
                getTest().addBase64ScreenShot(captureScreenshot()));
    }


    /**
     * Switch window context in web browser
     */

    public static void switchWindowVerifyTitleAndClose() throws InterruptedException {
        Thread.sleep(3000);
        String parentWindow = webDriver.getWindowHandle();
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
            captureScreenshotInPrintLogs();
            webDriver.getWindowHandle();
            System.out.println(webDriver.getTitle());
            ExtentManager.printLogs("Title is :" + webDriver.getTitle());
        }
        webDriver.close();
        webDriver.switchTo().window(parentWindow);
        captureScreenshotInPrintLogs();
    }

    public static void switchToNewWindow() throws InterruptedException {
        Thread.sleep(3000);
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1)); //switches to new tab
    }

    public static void switchToPreviousWindow() throws InterruptedException {
        Thread.sleep(3000);
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(0)); //switches to previous tab
    }


    /**
     * Java script executor to enter data in textfield
     *
     * @param value
     * @param webElement
     * @param elementName
     */
    public static void jsExecutorForInputBox(String value, WebElement webElement, String elementName) {
        try {
            TestUtils.elementIsDisplayed(webElement, elementName);
            String js = "arguments[0].setAttribute('value','" + value + "')";
            ((JavascriptExecutor) webDriver).executeScript(js, webElement);
            getTest().log(LogStatus.PASS, "Entered " + value + " in " + elementName,
                    getTest().addBase64ScreenShot(captureScreenshot()));
        } catch (Exception e) {
            getTest().addBase64ScreenShot(captureScreenshot());
            getTest().log(LogStatus.FAIL, e.getMessage(), getTest().addBase64ScreenShot(captureScreenshot()));
        }

    }


    /**
     * Clear text from textfield using BackSpace
     *
     * @param element
     */
    public static void clearTextFromFieldUsingBackSpace(WebElement element) {
        int charSize = element.getAttribute("value").length();
        for (int i = 0; i < charSize; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    /**
     * Return boolean value for element's visibilty on the screen
     *
     * @param element
     * @param time
     * @return
     */

    public static boolean isElementPresent(WebElement element, int time) {
        try {
            waitTillElementIsDisplayed(element, time);
            element.isDisplayed();
            Log.info(element + " is Displayed");
            getTest().log(LogStatus.INFO, element + " is Displayed",
                    getTest().addBase64ScreenShot(captureScreenshot()));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            Log.info(element + " is not Displayed");
            getTest().log(LogStatus.INFO, element + " is not Displayed",
                    getTest().addBase64ScreenShot(captureScreenshot()));
            return false;
        }
    }

    /**
     * Press Enter from Keyboard
     */

    public static void pressEnterKeyFromKeyboard() {
        Actions action = new Actions(webDriver);
        action.sendKeys(Keys.ENTER).build().perform();
    }

    /**
     * Clear Web Local Storage
     */
    public static void clearWebLocalStorage() {
        WebStorage webStorage = (WebStorage) webDriver;
        webStorage.getLocalStorage().clear();
        webStorage.getSessionStorage().clear();
    }

    /**
     * Check if element is enabled/disabled
     *
     * @param element
     * @param fieldName
     * @param expectedStatus
     */
    public static void isElementEnabled(WebElement element, String fieldName, Boolean expectedStatus) {
        waitTillElementIsDisplayed(element, 20);
        Boolean elementStatus = element.isEnabled();
        if (elementStatus.equals(expectedStatus)) {
            Log.info(fieldName + " is " + expectedStatus);
            getTest().log(LogStatus.PASS, fieldName + "  enable Status is " + expectedStatus,
                    getTest().addBase64ScreenShot(captureScreenshot()));
        } else {
            Log.error(fieldName + " is expected to be " + expectedStatus + " but found to be " + elementStatus);
            getTest().log(LogStatus.FAIL, fieldName + " expected Status is " + expectedStatus + " ,But "
                    + "got " + elementStatus, getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }


    /**
     * Open browser url
     *
     * @param url
     */
    public static void openUrl(String url) {
        webDriver.get(url);
        getTest().log(LogStatus.PASS, "Open URL :  " + url, getTest().addBase64ScreenShot(captureScreenshot()));

    }

    /**
     * scroll web page till element is visble
     *
     * @param element
     */
    public static void scrollWebPageTillElementFound(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * upload the image,file
     *
     * @param element
     * @param filePath
     * @param fieldName
     */
    public static void uploadFile(WebElement element, String filePath, String fieldName) {
        try {
            Log.info(" Entering text : " + filePath + " in " + fieldName);
            element.sendKeys(filePath);
            Log.info(filePath + " is entered in " + fieldName);
            getTest().log(LogStatus.PASS, "Entered " + filePath + " in " + fieldName,
                    getTest().addBase64ScreenShot(captureScreenshot()));

        } catch (Exception e2) {
            Log.error("Failed to enter text : " + filePath + " in " + fieldName);
            getTest().log(LogStatus.FAIL, e2.getMessage(), getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    /**
     * /**
     * Verify Webpage title
     *
     * @param screentitle
     */
    public static boolean verifyWebPageTitle(String screentitle) {
        if (webDriver.getTitle().equals(screentitle)) {
            Log.info("WebPage Title is : " + "'" + webDriver.getTitle() + "'");
            getTest().log(LogStatus.PASS,
                    "WebPage Title is : " + "'" + webDriver.getTitle() + "'",
                    getTest().addBase64ScreenShot(captureScreenshot()));

            return true;
        } else {
            Log.error("Expected WebPage Title is " + "'" + screentitle + "'" + " but got " + "'" + webDriver.getTitle()
                    + "'");
            getTest().log(LogStatus.FAIL,
                    "Expected WebPage Title is " + "'" + screentitle + "'" + " but got " + "'" + webDriver.getTitle()
                            + "'", getTest().addBase64ScreenShot(captureScreenshot()));
            return false;
        }
    }


    public static void verifyListIsSortedAlphabetically(List<WebElement> elements, String elementName) {
        List<String> elementText = new ArrayList<>();
        for (WebElement el : elements) {
            elementText.add(el.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : elementText) {
            sortedList.add(s);
        }
        Collections.sort(sortedList);

        if (elementText.equals(sortedList)) {
            Log.info(elementName + " is alphabetically sorted");
            getTest().log(LogStatus.PASS, elementName + " is alphabetically sorted",
                    getTest().addBase64ScreenShot(captureScreenshot()));
        } else {
            Log.error(elementName + " is not  alphabetically sorted");
            getTest().log(LogStatus.FAIL, elementName + " is not  alphabetically sorted",
                    getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    public static void verifyElementFocusStatus(WebElement element, String elementName, boolean expctedFocusState) {
        waitTillElementIsDisplayed(element, 20);
        try {
            String focusStatus = element.getAttribute("focused");

            if (focusStatus.equals(expctedFocusState)) {
                Log.info(elementName + " is focused");
                getTest().log(LogStatus.PASS, elementName + " is focused",
                        getTest().addBase64ScreenShot(captureScreenshot()));
            } else {
                Log.info(elementName + " is not focused");
                getTest().log(LogStatus.FAIL, elementName + " is not focused",
                        getTest().addBase64ScreenShot(captureScreenshot()));
            }
        } catch (Exception e) {
            getTest().log(LogStatus.FAIL, e.getMessage(), getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    public static void clearChromeHistory() throws Exception {
        webDriver.get("chrome://settings/clearBrowserData");
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebElement clearData = (WebElement) js.executeScript(
                "return document.querySelector('settings-ui').shadowRoot.querySelector('settings-main').shadowRoot.querySelector('settings-basic-page').shadowRoot.querySelector('settings-section > settings-privacy-page').shadowRoot.querySelector('settings-clear-browsing-data-dialog').shadowRoot.querySelector('#clearBrowsingDataDialog').querySelector('#clearBrowsingDataConfirm')");
        clearData.click();
        Thread.sleep(3000);
    }

    public static void clearEdgeHistory() throws Exception {
        webDriver.get("edge://settings/clearBrowserData");
        Thread.sleep(5000);
        webDriver.findElement(By.id("clear-now")).click();
        Thread.sleep(5000);
    }

    public static void clearBrowserHistory() throws Exception {
        if (browser.equalsIgnoreCase("chrome")) {
            clearChromeHistory();
        } else if (browser.equalsIgnoreCase("edge")) {
            clearEdgeHistory();
        }
    }

    public static void verifyURL(String expectedUrl) {
        String currentURL = webDriver.getCurrentUrl();
        if (currentURL.equalsIgnoreCase(expectedUrl)) {
            Log.info("current page url is : " + currentURL + "which matches with expected url : " + expectedUrl);
            getTest().log(LogStatus.PASS,
                    "current page url is : " + currentURL + "which matches with expected url : " + expectedUrl,
                    getTest().addBase64ScreenShot(captureScreenshot()));
        } else {
            getTest().addBase64ScreenShot(captureScreenshot());
            Log.info("current page url is : " + currentURL + "which doesn't match with expected url : " + expectedUrl);
            getTest().log(LogStatus.FAIL,
                    "current page url is : " + currentURL + "which doesn't match with expected url : " + expectedUrl,
                    getTest().addBase64ScreenShot(captureScreenshot()));
        }
    }

    public static boolean isElementPresentInListByText(List<WebElement> elements, String text) {
        for (WebElement ele : elements) {
            if (ele.getText().equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }


}




