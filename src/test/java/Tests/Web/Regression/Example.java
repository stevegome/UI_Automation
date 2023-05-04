//package Tests.Web.Regression;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.util.List;
//
//public class Example {
//    public static void main (String [] args)
//    {
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://www.w3schools.com/html/html_tables.asp");
//
//        List<WebElement> countryName = driver.findElements(By.xpath("//div[@class=\"w3-white w3-padding notranslate w3-padding-16\"]/table[@class=\"ws-table-all\"]//tr/td[3]"));
//        List<WebElement> contactName = driver.findElements(By.xpath("//div[@class=\"w3-white w3-padding notranslate w3-padding-16\"]/table[@class=\"ws-table-all\"]//tr/td[2]"));
//
////        for(int i=0;)
////        {
////            if()
////            {
////                System.out.println(contactName[i]);
////            }
////            else
////            {
////            }
//        }
//
//    }
//}
