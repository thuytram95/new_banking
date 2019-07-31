package Tests;

import Actions.Login_Actions;
import Commons.LoadConfigFile;
import Commons.Export;
import Objects.Users;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import static Pages.CreateAccount.*;
import static Pages.CreateAccount.nonTermDepositsElement;
import static Pages.CreateAccount.savingAccountElement;

public class OpenAccount {
    Properties properties;
    String propertyFilePath = ".\\src\\test\\Resources\\Configs\\Config.properties";
    WebDriver driver;
    Users user = new Users();
    Actions.OpenAccount openAccountAction = new Actions.OpenAccount();
    int TimeOut = 20;

    @BeforeMethod
    public void init() throws InterruptedException {
        properties = LoadConfigFile.loadPropertiesFile(propertyFilePath);
        user.setUsername(properties.getProperty("ID"));
        user.setPassword(properties.getProperty("pass"));
        System.setProperty(properties.getProperty("ChromeWebDriver"), properties.getProperty("WebDriver_Resource"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        // Navigate to site
        driver.get(properties.getProperty("SiteURL"));
        System.out.println(driver.getTitle());
        Thread.sleep(1000);
        Login_Actions.enterUsernameAndPassword(driver, user.getUsername(), user.getPassword());
        Login_Actions.clickLoginButton(driver);
        Actions.OpenAccount.clickOpenAccountLink(driver);
    }

    @Test(description = "Verify user can open account with account type is 'Tài khoản tiết kiệm'.")
    public void savingAccount() throws IOException {
        openAccountAction.createAccountWithType(driver, savingAccountElement);
        int countRecord = openAccountAction.countElementByType(driver, savingAccountTdElement);

        if (countRecord > 1) {
            Export.ExcelFile("ResultDemo", "Result", "TC_OpenAccount_01", "Verify user can open account with account type is 'Tài khoản tiết kiệm'.", "Failed");
        } else {
            Export.ExcelFile("ResultDemo", "Result", "TC_OpenAccount_01", "Verify user can open account with account type is 'Tài khoản tiết kiệm'.", "Passed");
        }
    }

    @Test(description = "Verify user can open account with account type is 'Tài khoản không kỳ hạn'.")
    public void nonTermDeposits() throws IOException {
        openAccountAction.createAccountWithType(driver, nonTermDepositsElement);
        int countRecord = openAccountAction.countElementByType(driver, nonTermDepositsTdElement);
        if (countRecord > 1) {
            Export.ExcelFile("ResultDemo", "Result", "TC_OpenAccount_02", "Verify user can open account with account type is 'Tài khoản không kỳ hạn'.", "Failed");
        } else {
            Export.ExcelFile("ResultDemo", "Result", "TC_OpenAccount_02", "Verify user can open account with account type is 'Tài khoản không kỳ hạn'.", "Passed");
        }
    }

    @AfterTest
    public void end() {
        driver.quit();
    }
}
