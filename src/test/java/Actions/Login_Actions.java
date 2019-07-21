package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Pages.BankLogin_Page.*;
import static Pages.Navigation.logout;

public class Login_Actions {
    public static void enterUsernameAndPassword(WebDriver wd, String username, String password)
    {
        wd.findElement(By.xpath(userElement)).sendKeys(username);
        wd.findElement(By.xpath(passwordElement)).sendKeys(password);
    }

    public static void clickLoginButton(WebDriver wd)
    {
        wd.findElement(By.xpath(loginButtonElement)).click();
    }

    public static boolean checkLogin(WebDriver wd)
    {
        return wd.findElement(By.xpath(logout)).isDisplayed();
    }


}
