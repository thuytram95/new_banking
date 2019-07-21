package Tests;

import Actions.Login_Actions;
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

    @Before
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

    @Test
    public void savingAccount() throws IOException {
        openAccountAction.createAccountWithType(driver, savingAccountElement);
        int countRecord = openAccountAction.countElementByType(driver, savingAccountTdElement);

        if (countRecord > 1) {
            Export.ExcelFile("ResultDemo", "Result", "TC01", "Verify user can login with valid username and password", "Failed");
        } else {
            Export.ExcelFile("ResultDemo", "Result", "TC01", "Verify user can login with valid username and password", "Passed");
        }
    }

    @Test
    public void nonTermDeposits() throws IOException {
        openAccountAction.createAccountWithType(driver, nonTermDepositsElement);
        int countRecord = openAccountAction.countElementByType(driver, nonTermDepositsTdElement);
        if (countRecord > 1) {
            Export.ExcelFile("ResultDemo", "Result", "TC01", "Verify user can login with valid username and password", "Failed");
        } else {
            Export.ExcelFile("ResultDemo", "Result", "TC01", "Verify user can login with valid username and password", "Passed");
        }
    }

    @After
    public void end() {
        driver.quit();
    }
}
