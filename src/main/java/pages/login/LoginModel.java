package pages.login;

import com.github.javafaker.Faker;

import java.util.Locale;

public class LoginModel {

    Faker faker = new Faker(new Locale("in-ID"));

    private String mainIdentifier;

    public String password = "Sell3rM0ck!?";
    public String validEmail = "qe@gudangada.com";
    public String validPhoneNumber = "081238409052";
    public String invalidEmail = faker.internet().emailAddress();
    public String invalidPhoneNumber = "0888" + faker.number().digits(8);

    public String getMainIdentifier() {
        return mainIdentifier;
    }

    public void setMainIdentifier(String mainIdentifier) {
        this.mainIdentifier = mainIdentifier;
    }
}