package com.framework.pages;

import org.openqa.selenium.By;

/** "Checkout: Your Information" — step one of checkout. */
public class CheckoutInfoPage extends BasePage {

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutInfoPage() {
        super();
    }

    public void fillInfo(String firstName, String lastName, String postalCode) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postalCodeField, postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public String getErrorText() {
        return getText(errorMessage).trim();
    }
}
