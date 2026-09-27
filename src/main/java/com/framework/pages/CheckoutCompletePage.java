package com.framework.pages;

import org.openqa.selenium.By;

/** "Checkout: Complete!" — final confirmation page. */
public class CheckoutCompletePage extends BasePage {

    private final By completeHeader = By.className("complete-header");

    public CheckoutCompletePage() {
        super();
    }

    public String getConfirmationHeader() {
        return getText(completeHeader).trim();
    }
}
