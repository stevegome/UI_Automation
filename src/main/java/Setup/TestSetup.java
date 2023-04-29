package Setup;

import Utilities.Log;
import Utilities.TestUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestSetup {

    public static WebDriver webDriver;
    public static String platform = System.getProperty("platform");
    public static String browser = System.getProperty("browser");
    public static String env = System.getProperty("env");
    public static File jsonTestData;
    public static File jsonExpectedResults;
    public static String sauceUsername = System.getenv("SAUCE_USERNAME");
    public static String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
    public String testDataType = System.getProperty("testDataType");
    public Assertion hardAssert = new Assertion();
    public SoftAssert softAssert = new SoftAssert();


    public TestSetup() {
        if (platform.equalsIgnoreCase("web")) {
            PageFactory.initElements(webDriver, this);
        } else if (platform.equalsIgnoreCase("API")) {
            Log.info("API Testcase");
        } else {
            Log.error("Please add platform as Web or Android or iOS");
            System.exit(1);
        }
    }

    public static Map<String, Integer> getScreenResolution() {
        Dimension dimension = webDriver.manage().window().getSize();
        int webScreenWidth = dimension.getWidth();
        int webScreenHeight = dimension.getHeight();
        Map<String, Integer> webScreenResolution = new HashMap<>();
        webScreenResolution.put("width", webScreenWidth);
        webScreenResolution.put("height", webScreenHeight);
        return webScreenResolution;
    }

    public static String getPlatformVersion() {
        Capabilities browserCaps = ((RemoteWebDriver) webDriver).getCapabilities();
        return browserCaps.getVersion();
    }

    public static String getEnvironment() throws Exception {
        if (System.getProperty("env") != null) {
            return System.getProperty("env");
        } else {
            Log.info("Please provide correct environment , accepted env values are : qa , sb, prod");
        }
        throw new RuntimeException("Please provide correct environment , accepted env values are : qa , sb, prod");
    }

    public static String getRunMode() throws Exception {
        if (System.getProperty("runMode") != null) {
            return System.getProperty("runMode");
        } else {
            return "local";
        }
    }

    public static String getSauceLabUserName() throws Exception {
        return sauceUsername;
    }

    public static String getSauceLabAccessKey() throws Exception {
        return sauceAccessKey;
    }

    public static String getTestJsonDataFile() throws Exception {
        if (System.getProperty("testDataJson") != null) {
            return System.getProperty("testDataJson");
        }
        return null;
    }

    private void initJsonTestData() throws Exception {
        jsonExpectedResults =
                new File(System.getProperty("user.dir") + "//src//test//java//Testdata//expectedResults.json");

        if (getTestJsonDataFile() != null) {
            jsonTestData = new File(getTestJsonDataFile());
        } else {
            if (getEnvironment().equalsIgnoreCase("QA")) {
                jsonTestData = new File(System.getProperty("user.dir") + "//src//test//java//Testdata//QA.json");
            } else if (getEnvironment().equalsIgnoreCase("PROD")) {
                jsonTestData = new File(System.getProperty("user.dir") + "//src//test//java//Testdata//PROD.json");
            } else if (getEnvironment().equalsIgnoreCase("SB")) {
                jsonTestData = new File(System.getProperty("user.dir") + "//src//test//java//Testdata//SB.json");
            }
        }
    }

    public void initiateDriver() throws Exception {
        Platforms.initializeDriver();
    }

    @BeforeSuite(alwaysRun = true)
    public void startServer() throws Exception {
        if (getRunMode().equalsIgnoreCase("local")) {
            if (platform == null) {
                Log.error("Please add platform as Web");
                System.exit(1);
            } else if (platform.equalsIgnoreCase("Web")) {
                Log.info("Web driver will be initialized");
            }
        }
        initJsonTestData();
        initiateDriver();
    }

    @BeforeClass(alwaysRun = true)
    public void initialize() throws Exception {
        Log.startLog(this.getClass().getSimpleName());
    }

    @AfterClass(alwaysRun = true)
    public void terminateApp() throws Exception {
        if (platform.equalsIgnoreCase("web") && webDriver != null) {
            //TestUtils.clearBrowserHistory();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void TeardownTest() throws Exception {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void CloseApp(ITestResult result) throws Exception {
        if (platform.equalsIgnoreCase("web")) {
            TestUtils.clearBrowserHistory();
        }

    }

    @BeforeMethod(alwaysRun = true)
    public void launchApp(Method method) throws Exception {
        Map<String, Integer> values = getScreenResolution();
        int width = values.get("width");
        int height = values.get("height");
        String screenResolution = width + " x " + height;

        Reporting.ExtentManager.startTest(method.getAnnotation(Test.class).description(), browser, platform,
                getPlatformVersion(), screenResolution);

    }

    public enum Platforms {

        Web {
            @Override
            void setUpDriver() throws Exception {
                BrowserSettings browserSettings = new BrowserSettings();
                browserSettings.setupWebDriver();
            }
        };

        static void initializeDriver() throws Exception {

            if (platform.equalsIgnoreCase("Web")) {
                Web.setUpDriver();
            } else {
                Log.error("Please add platform as Web");
            }
        }

        abstract void setUpDriver() throws Exception;
    }
}
