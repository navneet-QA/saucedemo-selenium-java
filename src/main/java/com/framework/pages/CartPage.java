package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    private final By cartItems = By.className("cart_item");
    private final By itemNames = By.className("inventory_item_name");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage() {
        super();
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeItem(String productName) {
        WebElement item = driver.findElements(cartItems).stream()
                .filter(e -> e.findElement(itemNames).getText().equals(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No cart item found with name: " + productName));
        item.findElement(By.tagName("button")).click();
    }

    public CheckoutInfoPage checkout() {
        click(checkoutButton);
        return new CheckoutInfoPage();
    }

    public ProductsPage continueShopping() {
        click(continueShoppingButton);
        return new ProductsPage();
    }
}
