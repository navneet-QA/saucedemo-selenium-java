package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {

    private final By inventoryItems = By.className("inventory_item");
    private final By itemNames = By.className("inventory_item_name");
    private final By itemPrices = By.className("inventory_item_price");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By sortDropdown = By.className("product_sort_container");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By closeMenuButton = By.id("react-burger-cross-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By resetLink = By.id("reset_sidebar_link");
    private final By pageTitle = By.className("title");

    public ProductsPage() {
        super();
    }

    public boolean isLoaded() {
        return getText(pageTitle).equals("Products");
    }

    public int getItemCount() {
        return driver.findElements(inventoryItems).size();
    }

    private WebElement inventoryItemContaining(String productName) {
        return driver.findElements(inventoryItems).stream()
                .filter(item -> item.findElement(itemNames).getText().equals(productName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementFoundException(productName));
    }

    public void addProductToCart(String productName) {
        WebElement item = inventoryItemContaining(productName);
        item.findElement(By.tagName("button")).click();
    }

    public void removeProductFromInventoryPage(String productName) {
        WebElement item = inventoryItemContaining(productName);
        item.findElement(By.tagName("button")).click();
    }

    public int getCartCount() {
        if (!isPresent(cartBadge)) return 0;
        return Integer.parseInt(getText(cartBadge));
    }

    public void openCart() {
        click(cartLink);
    }

    public void sortBy(String visibleText) {
        selectByVisibleText(sortDropdown, visibleText);
    }

    public List<String> getDisplayedProductNames() {
        return getAllText(itemNames);
    }

    public List<Double> getDisplayedProductPrices() {
        return getAllText(itemPrices).stream()
                .map(p -> Double.parseDouble(p.replace("$", "")))
                .collect(Collectors.toList());
    }

    public void clickProductName(String productName) {
        driver.findElements(itemNames).stream()
                .filter(e -> e.getText().equals(productName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementFoundException(productName))
                .click();
    }

    public void openMenu() {
        click(menuButton);
    }

    public void logout() {
        openMenu();
        click(logoutLink);
    }

    public void resetAppState() {
        openMenu();
        click(resetLink);
        click(closeMenuButton);
    }

    /** Thin wrapper so a missing product name fails with a readable message. */
    static class NoSuchElementFoundException extends RuntimeException {
        NoSuchElementFoundException(String productName) {
            super("No inventory item found with name: " + productName);
        }
    }
}
