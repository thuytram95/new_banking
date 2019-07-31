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

import static Pages.BankLogin_Page.passwordElement;

public class LoginTC {
    Properties properties;
    String propertyFilePath = ".\\src\\test\\Resources\\Configs\\Config.properties";
    WebDriver driver;
    Users user = new Users();
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
        driver.get(properties.getProperty("SiteURL"));
        System.out.println(driver.getTitle());
        Thread.sleep(1000);
    }

    @Test(description = "Verify that user can login with valid username and password")
    public void LoginTC1() throws IOException {
        Login_Actions.enterUsernameAndPassword(driver, user.getUsername(), user.getPassword());
        Login_Actions.clickLoginButton(driver);
        Boolean checkLoginStatus = Login_Actions.checkLogin(driver);

        if (checkLoginStatus) {
            Export.ExcelFile("ResultDemo", "Result", "TC_Login_01", "Verify user can login with valid username and password", "Passed");
        } else {
            Export.ExcelFile("ResultDemo", "Result", "TC_Login_01", "Verify user can login with valid username and password", "Failed");
        }

        driver.close();
    }

    @Test(description = "Verify that user cannot login with invalid username and password")
    public void LoginTC2() throws IOException {
        try {
            Login_Actions.enterUsernameAndPassword(driver, user.getUsername(), user.getPassword() + "_wrong");
            Login_Actions.clickLoginButton(driver);
            Boolean checkLoginStatus = Login_Actions.checkLogin(driver);

            if (checkLoginStatus) {
                Export.ExcelFile("ResultDemo", "Result", "TC_Login_02", "Verify user cannot login with valid username and password", "Failed");
            } else {
                Export.ExcelFile("ResultDemo", "Result", "TC_Login_02", "Verify user cannot login with valid username and password", "Passed");
            }
        } catch (Exception ex) {
            Export.ExcelFile("ResultDemo", "Result", "TC_Login_02", "Verify user cannot login with valid username and password", "Passed");
        }
        driver.close();
    }

    @Test(description = "Verify that the error message 'Bạn chưa nhập mật khẩu' displays when user leave 'Mật khẩu' field blank.")
    public void LoginTC3() throws IOException {
        Login_Actions.enterUsernameAndPassword(driver, user.getUsername(), user.getPassword());
        Actions.Transfer.setValueForInput(driver, passwordElement, "");
        Login_Actions.clickLoginButton(driver);
        String observerMessage = Actions.Transfer.getMessage(driver);
        String expectedMessage = "Bạn chưa nhập mật khẩu";
        System.out.println(observerMessage);
        System.out.println(expectedMessage);

        if (observerMessage == expectedMessage) {
            System.out.println("OK");
            Export.ExcelFile("ResultDemo", "Result", "TC_Login_03", "Verify that the error message 'Bạn chưa nhập mật khẩu' when user leave 'Mật khẩu' field blank.", "Passed");
        }
        System.out.println("NO OK");
        Export.ExcelFile("ResultDemo", "Result", "TC_Login_03", "Verify that the error message 'Bạn chưa nhập mật khẩu' when user leave 'Mật khẩu' field blank.", "Failed");
    }

    @Test(description = "Verify that the message 'Sai tài khoản hoặc mật khẩu' displays when user enter invalid data into 'Mật Khẩu' field.")
    public void LoginTC4() throws IOException {

        Login_Actions.enterUsernameAndPassword(driver, user.getUsername(), user.getPassword() + "_wrong");
        Login_Actions.clickLoginButton(driver);
        String observerMessage = Actions.Transfer.getMessage(driver);
        String expectedMessage = "Sai tài khoản hoặc mật khẩu";
        System.out.println(observerMessage);
        System.out.println(expectedMessage);

        if (observerMessage == expectedMessage) {
            System.out.println("OK");
            Export.ExcelFile("ResultDemo", "Result", "TC_Login_04", "Verify that the message 'Sai tài khoản hoặc mật khẩu' displays when user enter invalid data into 'Mật Khẩu' field.", "Passed");
        }
        System.out.println("NO OK");
        Export.ExcelFile("ResultDemo", "Result", "TC_Login_04", "Verify that the message 'Sai tài khoản hoặc mật khẩu' displays when user enter invalid data into 'Mật Khẩu' field", "Failed");

    }

    @AfterTest
    public void end() {
        driver.quit();
    }

}
