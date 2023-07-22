package Tests.Web.Regression;

import Utilities.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class HandleMultipleWindows {


    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Launching the site.
        driver.get("https://demo.guru99.com/popup.php");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();

        // print the current window handle
        String MainWindow = driver.getWindowHandle();
        Log.info("Print the Main Window Handle : " + MainWindow);


        // To handle all new opened window.
        Set<String> s1 = driver.getWindowHandles();
        System.out.println("Print all the window handles : " + s1);
        Log.info("Print all the window handles : " + s1);
        Iterator<String> i1 = s1.iterator();

        while(i1.hasNext()) {
        //for(String ChildWindow : s1 ){
            String ChildWindow = i1.next();

           if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
              //if(ChildWindow.equalsIgnoreCase(MainWindow)){
                // Switching to Child window
                driver.switchTo().window(ChildWindow);
                driver.findElement(By.name("emailid")).sendKeys("stevegomester@gmail.com");
                driver.findElement(By.name("btnLogin")).click();

                // Closing the Child Window.
                driver.close();
                Thread.sleep(3000);
            }
        }
        // Switching to Parent window i.e Main Window.
        driver.switchTo().window(MainWindow);
        driver.close();
        driver.quit();
    }
}
