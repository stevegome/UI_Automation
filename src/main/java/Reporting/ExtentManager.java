package Reporting;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ExtentManager {
    public static ExtentReports extent;
    public static ExtentTest test;

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

    public synchronized static ExtentReports getReporter() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        if (extent == null) {
            extent = new ExtentReports(System.getProperty("user.dir") + "/Reports/" + "GEMS_" + System.getProperty("platform")+"_HMI_Automation_"+System.getProperty("browser")+"_browser_"+ timeStamp + ".html");
        }
        return extent;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void endTest() {
        getReporter().endTest(extentTestMap.get((int) Thread.currentThread().getId()));
    }


    public static synchronized void startTest(String testName,String browserName, String platformName, String platformVersion, String screenResolution) {
        test = getReporter().startTest(testName);
        Map<String, String> sysInfo = new HashMap<>();
        sysInfo.put("Platform", platformName);
        sysInfo.put("Browser Name", browserName);
        sysInfo.put("Browser Version", platformVersion);
        sysInfo.put("Device Version", platformVersion);
        sysInfo.put("Screen Resolution", screenResolution);
        test.getTest().getDescription();
        extent.addSystemInfo(sysInfo);
        extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
        extentTestMap.put((int) Thread.currentThread().getId(), test);
    }

    public static synchronized void printLogs(String logs) {
        getTest().log(LogStatus.INFO, logs);
    }

}
