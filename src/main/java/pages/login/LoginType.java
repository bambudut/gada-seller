package pages.login;

public enum LoginType {
    PHONE_NUMBER(1),
    USERNAME_EMAIL(2);

    public int type;

    LoginType(int type) {
        this.type = type;
    }
}