package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BankLogin_Page {
    public static String userElement = "//input[@type = 'text' ]" ;
    public static String passwordElement = "//input[@type = 'password']";
    public static String loginButtonElement = "//input[@type = 'submit']";

   /* @FindBy(xpath = ".//input[@name='uid']" )
    static WebElement txtUsername;

    @FindBy(xpath = ".//input[@name='password']" )
    static WebElement txtPassword;*/

    /*public BankLogin_Page(WebDriver wd)
    {

    }*/

  /*  public static void enterUsernameAndPassword(WebDriver wd,String username, String password)
    {
        wd.findElement(By.xpath(txtUsername)).sendKeys(username);
        wd.findElement(By.xpath(txtPassword)).sendKeys(password);
    }

    public static void clickLoginButton(WebDriver wd)
    {
        wd.findElement(By.xpath(btnLogin)).click();
    }*/
}
