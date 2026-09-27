package com.framework.tests.positive;

import com.framework.data.TestData;
import com.framework.pages.ProductsPage;
import com.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPositiveTest extends BaseTest {

    @Test(description = "TC_POS_01: Login with valid standard user credentials")
    public void testValidLogin() {
        ProductsPage productsPage = loginAsStandardUser();
        Assert.assertTrue(productsPage.isLoaded(), "Expected to land on the Products page after a valid login");
    }

    @Test(description = "TC_POS_02: Products page displays all 6 inventory items")
    public void testProductsPageShowsAllItems() {
        ProductsPage productsPage = loginAsStandardUser();
        Assert.assertEquals(productsPage.getItemCount(), 6, "Expected exactly 6 products on the inventory page");
    }

    @Test(description = "TC_POS_11: Logout successfully via hamburger menu")
    public void testLogout() {
        ProductsPage productsPage = loginAsStandardUser();
        productsPage.logout();

        Assert.assertTrue(driver().getCurrentUrl().matches(".*saucedemo\\.com/?$"),
                "Expected to be redirected back to the login page after logout");
    }

    private org.openqa.selenium.WebDriver driver() {
        return com.framework.base.DriverManager.getDriver();
    }
}
