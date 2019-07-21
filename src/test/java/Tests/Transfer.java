package Tests;

import Actions.Login_Actions;
import Commons.Email;
import Commons.LoadConfigFile;
import Commons.Export;
import Objects.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static Pages.Transfer.amountOfMoneyElement;

public class Transfer {
    Properties properties;
    String propertyFilePath = ".\\src\\test\\Resources\\Configs\\Config.properties";
    WebDriver driver;
    Users user1 = new Users();
    int TimeOut = 1000;

    @Before
    public void init() throws InterruptedException {
        properties = LoadConfigFile.loadPropertiesFile(propertyFilePath);
        user1.setUsername(properties.getProperty("ID"));
        user1.setPassword(properties.getProperty("pass"));
        System.setProperty(properties.getProperty("ChromeWebDriver"), properties.getProperty("WebDriver_Resource"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        // Navigate to site
        driver.get(properties.getProperty("SiteURL"));
        System.out.println(driver.getTitle());
        Thread.sleep(1000);
        Login_Actions.enterUsernameAndPassword(driver, user1.getUsername(), user1.getPassword());
        Login_Actions.clickLoginButton(driver);
        Actions.Transfer.clickTransfer(driver);
    }

    @Test
    public void transferSuccess() throws IOException, InterruptedException {
        Actions.Transfer.enterDataTransfer(driver);
        Actions.Transfer.clickConfirmButton(driver);
        Boolean isDisplay = Actions.Transfer.checkStatusTransfer(driver);

        if (isDisplay) {
            System.out.println("OK");
            Actions.Transfer.confirmTransfer(driver);
            String codeOTP = Email.getCodeFromEmail();
            Thread.sleep(60000);
            Actions.Transfer.enterCodeAndSubmitForm(driver, codeOTP);
            Boolean isDisplayTransferSuccessful = Actions.Transfer.checkStatusSuccessful(driver);

            if (isDisplayTransferSuccessful) {
                Export.ExcelFile("ResultDemo", "Result", "TC01", "", "Passed");
            }
        }

        System.out.println("NOT OK");
        Export.ExcelFile("ResultDemo", "Result", "TC01", "", "Failed");
    }

    @Test
    public void transferWithBlankMoney() throws IOException {
        Actions.Transfer.enterDataTransfer(driver);
        Actions.Transfer.setValueForInput(driver, amountOfMoneyElement, "");
        Actions.Transfer.clickConfirmButton(driver);
        String observerMessage = Actions.Transfer.getMessage(driver);
        String expectedMessage = "Vui lòng nhập số tiền";

        if (observerMessage == expectedMessage) {
            System.out.println("OK");
            Export.ExcelFile("ResultDemo", "Result", "TC01", "", "Passed");
        }

        System.out.println("Not OK");
        Export.ExcelFile("ResultDemo", "Result", "TC01", observerMessage, "Fail");
    }


    @Test
    public void transferWithMoneyIs0VND() throws IOException {
        Actions.Transfer.enterDataTransfer(driver);
        Actions.Transfer.setValueForInput(driver, amountOfMoneyElement, "0");
        Actions.Transfer.clickConfirmButton(driver);
        Boolean isDisplay = Actions.Transfer.checkStatusTransfer(driver);

        if (isDisplay) {
            System.out.println("Not OK");
            Export.ExcelFile("ResultDemo", "Result", "TC01", "", "Failed");
        }

        System.out.println("OK");
        Export.ExcelFile("ResultDemo", "Result", "TC01", "OK", "Passed");
    }

    @After
    public void end() {
        driver.quit();
    }
}
