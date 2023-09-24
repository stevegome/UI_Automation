package Setup;

import Utilities.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URL;
import java.util.logging.Level;

import static Setup.TestSetup.*;

public class BrowserSettings {

    public WebDriver setupWebDriver() throws Exception {
        if (browser == null) {
            Log.error("Please add browser as Chrome or Firefox or Safari or Edge");
            System.exit(1);
        }
        switch (browser.toLowerCase()) {
            case "chrome":
                return chromeDriver();
            case "firefox":
                return firefoxDriver();
            case "safari":
                return safariDriver();
            case "edge":
                return edgeDriver();
            default:
                Log.error("Please provide valid browser name");
        }
        return null;
    }

    public WebDriver chromeDriver() throws Exception {
        if (getRunMode().equalsIgnoreCase("saucelab")) {
            MutableCapabilities sauceOptions = new MutableCapabilities();
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setCapability("screenResolution", "1280x1024");
            browserOptions.setCapability("maxDuration", 3600);
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
            browserOptions.setCapability("goog:loggingPrefs", logPrefs);
            String serverUrl =
                    "https://" + sauceUsername + ":" + sauceAccessKey + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
            webDriver = new RemoteWebDriver(new URL(serverUrl), browserOptions);
            ((RemoteWebDriver) webDriver).setFileDetector(new LocalFileDetector());
            webDriver.manage().window().maximize();
        } else if (getRunMode().equalsIgnoreCase("local")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions browserOptions = new ChromeOptions();
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
            browserOptions.setCapability("goog:loggingPrefs", logPrefs);
            webDriver = new ChromeDriver(browserOptions);
            webDriver.manage().window().maximize();

        }
        return webDriver;
    }

    public WebDriver firefoxDriver() throws Exception {
        if (getRunMode().equalsIgnoreCase("saucelab")) {
            MutableCapabilities sauceOptions = new MutableCapabilities();
            FirefoxOptions browserOptions = new FirefoxOptions();
            browserOptions.setCapability("screenResolution", "1280x1024");
            browserOptions.setCapability("maxDuration", 3600);
            String serverUrl =
                    "https://" + sauceUsername + ":" + sauceAccessKey + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
            webDriver = new RemoteWebDriver(new URL(serverUrl), browserOptions);
            ((RemoteWebDriver) webDriver).setFileDetector(new LocalFileDetector());
            webDriver.manage().window().maximize();
        } else if (getRunMode().equalsIgnoreCase("local")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
            webDriver.manage().window().maximize();
        }
        return webDriver;
    }

    public WebDriver safariDriver() throws Exception {
        if (getRunMode().equalsIgnoreCase("saucelab")) {
            MutableCapabilities sauceOptions = new MutableCapabilities();
            SafariOptions browserOptions = new SafariOptions();
            browserOptions.setCapability("screenResolution", "1280x1024");
            browserOptions.setCapability("maxDuration", 3600);
            String serverUrl =
                    "https://" + sauceUsername + ":" + sauceAccessKey + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
            webDriver = new RemoteWebDriver(new URL(serverUrl), browserOptions);
            ((RemoteWebDriver) webDriver).setFileDetector(new LocalFileDetector());
            webDriver.manage().window().maximize();
        } else if (getRunMode().equalsIgnoreCase("local")) {
            Runtime.getRuntime().exec("safaridriver --enable");
            webDriver = new SafariDriver();
            webDriver.manage().window().maximize();
        }
        return webDriver;
    }

    public WebDriver edgeDriver() throws Exception {
        if (getRunMode().equalsIgnoreCase("saucelab")) {
            MutableCapabilities sauceOptions = new MutableCapabilities();
            EdgeOptions browserOptions = new EdgeOptions();
            browserOptions.setCapability("screenResolution", "1280x1024");
            browserOptions.setCapability("maxDuration", 3600);
            String serverUrl =
                    "https://" + sauceUsername + ":" + sauceAccessKey + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
            webDriver = new RemoteWebDriver(new URL(serverUrl), browserOptions);
            ((RemoteWebDriver) webDriver).setFileDetector(new LocalFileDetector());
            webDriver.manage().window().maximize();
        } else if (getRunMode().equalsIgnoreCase("local")) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
            webDriver.manage().window().maximize();
        }
        return webDriver;
    }

}
