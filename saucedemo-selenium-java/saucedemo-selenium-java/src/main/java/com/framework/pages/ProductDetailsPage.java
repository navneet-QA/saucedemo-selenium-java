package com.framework.pages;

import org.openqa.selenium.By;

public class ProductDetailsPage extends BasePage {

    private final By detailsName = By.className("inventory_details_name");

    public ProductDetailsPage() {
        super();
    }

    public String getProductName() {
        return getText(detailsName);
    }
}
