package Tests;

import Actions.Login_Actions;
import Commons.LoadConfigFile;
import Commons.Export;
import Objects.Users;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
            Export.ExcelFile("ResultDemo", "Result", "TC01", "Verify user can login with valid username and password", "Passed");
        } else {
            Export.ExcelFile("ResultDemo", "Result", "TC01", "Verify user can login with valid username and password", "Failed");
        }

        driver.close();
    }

    //Verify that user cannot login with invalid username and password
    @Test(description = "Verify that user cannot login with invalid username and password")
    public void LoginTC2() throws IOException {
        try {
            Login_Actions.enterUsernameAndPassword(driver, user.getUsername(), user.getPassword() + "_wrong");
            Login_Actions.clickLoginButton(driver);
            Boolean checkLoginStatus = Login_Actions.checkLogin(driver);

            if (checkLoginStatus) {
                Export.ExcelFile("ResultDemo", "Result", "TC01", "Verify user can login with valid username and password", "Passed");
            } else {
                Export.ExcelFile("ResultDemo", "Result", "TC01", "Verify user can login with valid username and password", "Failed");
            }
        } catch (Exception ex) {
            Export.ExcelFile("ResultDemo", "Result", "TC01", "Verify user can login with valid username and password", "Failed");
        }
        driver.close();
    }

    @AfterTest
    public void end() {
        driver.quit();
    }
}
