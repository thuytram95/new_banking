package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static Pages.Navigation.transfer;
import static Pages.Transfer.*;
import static Pages.Transfer.submitButtonElement;

public class Transfer {

    public static void clickTransfer(WebDriver driver) {
        driver.findElement(By.xpath(transfer)).click();
    }

    public static void enterDataTransfer(WebDriver driver) {
        driver.findElement(By.xpath(rootAccountElement)).click();
        driver.findElement(By.xpath(accountNumberHaveMoney)).click();
        driver.findElement(By.xpath(receiveAccountElement)).sendKeys("100001551");
        driver.findElement(By.xpath(amountOfMoneyElement)).sendKeys("100000");
        driver.findElement(By.xpath(messageElement)).sendKeys("Chuyen tien");
    }

    public static void clickConfirmButton(WebDriver driver) {
        driver.findElement(By.xpath(submitButtonElement)).click();
    }

    public static boolean checkStatusTransfer(WebDriver driver) {
        WebElement buttonConfirm = driver.findElement(By.xpath(confirmButtonElement));
        return buttonConfirm.isDisplayed();
    }

    public static void confirmTransfer(WebDriver driver) {
        driver.findElement(By.xpath(confirmButtonElement)).click();
    }

    public static void enterCodeAndSubmitForm(WebDriver driver, String code) {
        driver.findElement(By.xpath(codeTransfer)).sendKeys(code);
        driver.findElement(By.xpath(submitTransferElement)).click();
    }

    public static boolean checkStatusSuccessful(WebDriver driver) {
        return driver.findElement(By.xpath(transferSuccessElement)).isDisplayed();
    }

    public static void setValueForInput(WebDriver driver, String xpath, String value) {
        driver.findElement(By.xpath(xpath)).clear();

        if (value != "") {
            driver.findElement(By.xpath(xpath)).sendKeys(value);
        }
    }

    public static String getMessage(WebDriver driver) {
        return driver.findElement(By.xpath(amountOfMoneyMessage)).getText();
    }
}
