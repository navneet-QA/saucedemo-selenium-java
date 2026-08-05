package com.framework.tests.positive;

import com.framework.data.TestData;
import com.framework.pages.CartPage;
import com.framework.pages.CheckoutCompletePage;
import com.framework.pages.CheckoutInfoPage;
import com.framework.pages.CheckoutOverviewPage;
import com.framework.pages.ProductsPage;
import com.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutPositiveTest extends BaseTest {

    @Test(description = "TC_POS_09: Proceed to checkout with items in cart")
    public void testProceedToCheckout() {
        ProductsPage productsPage = loginAsStandardUser();
        productsPage.addProductToCart(TestData.BACKPACK);
        productsPage.openCart();

        CartPage cartPage = new CartPage();
        CheckoutInfoPage checkoutInfoPage = cartPage.checkout();

        Assert.assertTrue(com.framework.base.DriverManager.getDriver().getCurrentUrl().contains("checkout-step-one"),
                "Expected to land on the Checkout: Your Information page");
        Assert.assertNotNull(checkoutInfoPage);
    }

    @Test(description = "TC_POS_10: Complete order with valid checkout information")
    public void testCompleteOrderWithValidInfo() {
        ProductsPage productsPage = loginAsStandardUser();
        productsPage.addProductToCart(TestData.BACKPACK);
        productsPage.openCart();

        CartPage cartPage = new CartPage();
        CheckoutInfoPage checkoutInfoPage = cartPage.checkout();

        checkoutInfoPage.fillInfo(TestData.VALID_FIRST_NAME, TestData.VALID_LAST_NAME, TestData.VALID_ZIP);
        checkoutInfoPage.clickContinue();

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        CheckoutCompletePage completePage = overviewPage.finish();

        Assert.assertEquals(completePage.getConfirmationHeader(), "Thank you for your order!",
                "Expected the order confirmation header after finishing checkout");
    }
}
