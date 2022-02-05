import org.testng.annotations.Test;
import pages.login.LoginModel;
import pages.login.LoginPage;
import pages.login.LoginType;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    LoginModel loginModel = new LoginModel();

    // Check the registration status just in test priority 1 & 6, because that should be positive flow

    @Test(priority = 1)
    public void loginByUsernameEmailWithValidData() {
        loginPage = new LoginPage(driver);
        loginPage.userLogin(LoginType.USERNAME_EMAIL, loginModel.validEmail, loginModel.password, null);
        loginPage.verifyUserRegistrationStatus();
    }

    @Test(priority = 2)
    public void loginByUsernameEmailWithInvalidData() {
        loginPage = new LoginPage(driver);
        loginPage.userLogin(LoginType.USERNAME_EMAIL, loginModel.invalidEmail, loginModel.password, null);
    }

    @Test(priority = 3)
    public void loginByUsernameEmailWithEmptyEmail() {
        loginPage = new LoginPage(driver);
        loginPage.userLogin(LoginType.USERNAME_EMAIL, "", loginModel.password, null);
    }

    @Test(priority = 4)
    public void loginByUsernameEmailWithEmptyPassword() {
        loginPage = new LoginPage(driver);
        loginPage.userLogin(LoginType.USERNAME_EMAIL, loginModel.validEmail, "", null);
    }

    @Test(priority = 5)
    public void loginByUsernameEmailWithAllFieldsEmpty() {
        loginPage = new LoginPage(driver);
        loginPage.userLogin(LoginType.USERNAME_EMAIL, "", "", null);
    }

    @Test(priority = 6)
    public void loginByPhoneNumberWithValidData() {
        loginPage = new LoginPage(driver);
        loginPage.userLogin(LoginType.PHONE_NUMBER, null, null, loginModel.validPhoneNumber);
        loginPage.verifyUserRegistrationStatus();
    }

    @Test(priority = 7)
    public void loginByPhoneNumberWithInvalidData() {
        loginPage = new LoginPage(driver);
        loginPage.userLogin(LoginType.PHONE_NUMBER, null, null, loginModel.invalidPhoneNumber);
    }

    @Test(priority = 8)
    public void loginByPhoneNumberWithEmptyField() {
        loginPage = new LoginPage(driver);
        loginPage.userLogin(LoginType.PHONE_NUMBER, null, null, "");
    }
}