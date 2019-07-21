package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Pages.CreateAccount.*;
import static Pages.Navigation.openAccountElement;

public class OpenAccount {
    public static void clickOpenAccountLink(WebDriver driver) {
        driver.findElement(By.xpath(openAccountElement)).click();
    }

    public void createAccountWithType(WebDriver driver, String typeElement) {
        driver.findElement(By.xpath(dropdownElement)).click();
        driver.findElement(By.xpath(typeElement)).click();
        driver.findElement(By.xpath(submitButtonElement)).click();
        driver.findElement(By.xpath(closeNotification)).click();
    }

    public int countElementByType(WebDriver driver, String typeElement) {
        return driver.findElements(By.xpath(typeElement)).size();
    }
}
