package com.framework.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage() {
        super();
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    /** Convenience method for the happy path: fills both fields, submits, and returns the next page. */
    public ProductsPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new ProductsPage();
    }

    public String getErrorText() {
        return getText(errorMessage).trim();
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }
}
