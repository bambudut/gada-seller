package pages.login;

import org.openqa.selenium.By;

public class LoginLocator {

    public By inputEmail = By.xpath("//input[@id='email']");
    public By inputPassword = By.xpath("//input[@id='password']");
    public By inputPhoneNumber = By.xpath("//input[@id='phoneNumber']");
    public By tabUsernameEmail = By.xpath("//span[.='Username/Email']");
    public By textErrorMessage = By.xpath("//p[@class='MuiFormHelperText-root Mui-error']");

    // TODO: Edit below 2 locators to a real or correct value
    public By welcomeMessage = By.xpath("//p[@id='welcome']");
    public By registrationBanner = By.xpath("//div[@id='bannerHome']");

    public By buttonLogin(int index) {
        return By.xpath("(//span[.='Masuk'])[" + index + "]");
    }
}