package Pages;

public class Transfer {
    public static String rootAccountElement = "//label[text() = 'Chọn tài khoản']";
    public static String accountNumberHaveMoney = "//li[text() = '100001575']";
    public static String receiveAccountElement = "//form//div[2]//div[2]//table//input";
    public static String amountOfMoneyElement = "//form//div[3]//div[2]//table//tr[1]//input";
    public static String messageElement = "//form//div[3]//div[2]//table//tr[2]//input";
    public static String submitButtonElement = "//form//div[3]//div[2]//table//tr[3]//input[@type='submit']";
    public static String confirmButtonElement = "//input[@type='submit']";
    public static String codeTransfer = "//table//tr[10]//input";
    public static String submitTransferElement = "//table//tr[11]//input[@type='submit']";
    public static String transferSuccessElement = "//div[text() ='Chuyển tiền thành công']";


    public static String amountOfMoneyMessage = "//span[@class='ui-growl-title']";
}
