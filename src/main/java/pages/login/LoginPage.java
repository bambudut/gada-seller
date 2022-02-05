package pages.login;

import config.GlobalVariable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginPage {

    WebDriver driver;
    WebDriverWait driverWait;
    LoginModel loginModel = new LoginModel();
    LoginLocator loginLocator = new LoginLocator();
    GlobalVariable globalVariable = new GlobalVariable();

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, globalVariable.defaultTimeout);
    }

    public void userLogin(LoginType loginType, String email, String password, String phone) {
        switch (loginType.type) {
            case 1 -> {
                driver.findElement(loginLocator.inputPhoneNumber).sendKeys(phone);
                loginModel.setMainIdentifier(phone);
                if (!phone.isEmpty()) {
                    System.out.println(phone + " is try to login.");
                } else {
                    System.out.println("Empty phone number is try to login.");
                }
            }
            case 2 -> {
                driver.findElement(loginLocator.tabUsernameEmail).click();
                driver.findElement(loginLocator.inputEmail).sendKeys(email);
                driver.findElement(loginLocator.inputPassword).sendKeys(password);
                loginModel.setMainIdentifier(email);
                if (!email.isEmpty()) {
                    System.out.println(email + " is try to login.");
                } else {
                    System.out.println("Empty username/email is try to login.");
                }
            }
        }
        driver.findElement(loginLocator.buttonLogin(loginType.type)).click();
        verifyUserLogin();
    }

    public void verifyUserRegistrationStatus() {
        driver.manage().timeouts().implicitlyWait(globalVariable.shortDelay, TimeUnit.SECONDS);
        // This if check banner is shown, then return to verify status PENDING or FAILED
        if (driver.findElements(loginLocator.registrationBanner).size() > 0) {
            String getBannerStatus = driver.findElement(loginLocator.registrationBanner).getText();
            switch (getBannerStatus) {
                case "PENDING" -> System.out.println(loginModel.getMainIdentifier() + " status still in progress.");
                case "FAILED" -> System.out.println(loginModel.getMainIdentifier() + " status was failed.");
            }
        }
        // This else return to status VERIFIED, because no banner is displayed
        else {
            System.out.println(loginModel.getMainIdentifier() + " status is already verified.");
        }
    }

    private void verifyUserLogin() {
        driver.manage().timeouts().implicitlyWait(globalVariable.shortDelay, TimeUnit.SECONDS);
        if (driver.findElements(loginLocator.textErrorMessage).size() > 0) {
            List<WebElement> elements = driver.findElements(loginLocator.textErrorMessage);
            List<String> stringList = new ArrayList<>();
            for (WebElement webElement : elements) {
                if (!stringList.contains(webElement.getText())) {
                    stringList.add(webElement.getText());
                }
            }
            System.out.println("Login failed with text error: " + String.join(", ", stringList));
        } else {
            String getCurrentUrl = driver.getCurrentUrl();
            if (getCurrentUrl.equals(globalVariable.baseUrl + "home")) {
                System.out.println(loginModel.getMainIdentifier() + " has login successfully.");
                Assert.assertEquals(getCurrentUrl, globalVariable.baseUrl + "home");

                // This code below verify after login the page contains text "Welcome"
                 String welcomeMessage = driver.findElement(loginLocator.welcomeMessage).getText();
                 Assert.assertTrue(welcomeMessage.contains("Welcome"));
            } else {
                System.out.println(loginModel.getMainIdentifier() + " has failed to login.");
            }
        }
    }
}