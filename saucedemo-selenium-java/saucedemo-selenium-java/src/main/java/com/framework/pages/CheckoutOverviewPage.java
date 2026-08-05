package com.framework.pages;

import org.openqa.selenium.By;

/** "Checkout: Overview" — step two, shows the price breakdown before finishing. */
public class CheckoutOverviewPage extends BasePage {

    private final By itemTotalLabel = By.className("summary_subtotal_label");
    private final By taxLabel = By.className("summary_tax_label");
    private final By totalLabel = By.className("summary_total_label");
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage() {
        super();
    }

    private double parseCurrency(String label) {
        String digits = label.replaceAll("[^0-9.]", "");
        return Double.parseDouble(digits);
    }

    public double getItemTotal() {
        return parseCurrency(getText(itemTotalLabel));
    }

    public double getTax() {
        return parseCurrency(getText(taxLabel));
    }

    public double getTotal() {
        return parseCurrency(getText(totalLabel));
    }

    public CheckoutCompletePage finish() {
        click(finishButton);
        return new CheckoutCompletePage();
    }
}
