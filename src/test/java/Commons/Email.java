package Commons;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Email {
    static WebDriver driver;
    public static String getCodeFromEmail() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // Navigate to site
        driver.get("https://mail.google.com");
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("testst24.01@gmail.com");
        driver.findElement(By.xpath("//span[text() = 'Tiếp theo']")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("tannguyen060895");
        driver.findElement(By.xpath("//span[text() = 'Tiếp theo']")).click();
        Thread.sleep(8000);
        String firstElement = "//div[@class='UI']//table//tbody//tr[1]//span[contains(text(),'OTP')]";
        String code = driver.findElement(By.xpath(firstElement)).getText();
        System.out.println(code);
        driver.quit();

        return code.substring(code.length() - 10);
    }
}
