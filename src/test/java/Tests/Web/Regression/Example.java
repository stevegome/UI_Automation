package Tests.Web.Regression;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class Example {
    public static void main(String[] args) throws InterruptedException {
        String before_XPATH = "//*[@id='customers']/tbody/tr[";
        String aftertd_XPATH = "]/td[";
        String aftertr_XPATH = "]";

        String new_before_XPATH = "//*[@id=\"customers\"]/tbody/tr[";
        String new_after_XPATH = "]/td[2]";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/html/html_tables.asp");

        WebElement elementToAppear = driver.findElement(By.className("ws-table-all"));

        Thread.sleep(10000);

        List<WebElement> noOfRows = driver.findElements(By.xpath("//*[@id=\"customers\"]/tbody/tr"));
        List<WebElement> noOfColumns = driver.findElements(By.xpath("//*[@id=\"customers\"]/tbody/tr[2]/td"));

        boolean element_found = false;
        String search_text = "kanda";

        for (int i = 2; i <= noOfRows.size(); i++) {
            for (int j = 1; j <= noOfColumns.size(); j++) {
                String final_XPATH = before_XPATH + i + aftertd_XPATH + j + aftertr_XPATH;
                String new_final_XPATH = new_before_XPATH + i + new_after_XPATH;
                WebElement getNameOfCountry = driver.findElement(By.xpath(final_XPATH));
                WebElement getNameOfContact = driver.findElement(By.xpath(new_final_XPATH));

                if (getNameOfCountry.getText().equalsIgnoreCase(search_text)) {
                    System.out.println(
                            "Name of the Contact for corresponding Country : " + search_text +" is :" + getNameOfContact.getText());
                    Assert.assertTrue(getNameOfCountry.isDisplayed());
                    element_found = true;
                    break;
                }

            }

        }
        if (!element_found) {
            System.out.println("Names of the searched country "+ search_text +" does not exist in the table");
        }
        driver.close();
        driver.quit();

    }
}
