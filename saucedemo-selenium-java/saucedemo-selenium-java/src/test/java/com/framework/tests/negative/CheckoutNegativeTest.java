package com.framework.tests.negative;

import com.framework.data.TestData;
import com.framework.pages.CartPage;
import com.framework.pages.CheckoutInfoPage;
import com.framework.pages.ProductsPage;
import com.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutNegativeTest extends BaseTest {

    private CheckoutInfoPage checkoutInfoPage;

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void goToCheckoutInfoPage() {
        ProductsPage productsPage = loginAsStandardUser();
        productsPage.addProductToCart(TestData.BACKPACK);
        productsPage.openCart();

        CartPage cartPage = new CartPage();
        checkoutInfoPage = cartPage.checkout();
    }

    @Test(description = "TC_NEG_05: Checkout fails when First Name is left blank")
    public void testBlankFirstName() {
        checkoutInfoPage.fillInfo("", TestData.VALID_LAST_NAME, TestData.VALID_ZIP);
        checkoutInfoPage.clickContinue();

        Assert.assertTrue(checkoutInfoPage.getErrorText().contains(TestData.ERROR_FIRST_NAME_REQUIRED),
                "Expected the first-name-required error message");
    }

    @Test(description = "TC_NEG_06: Checkout fails when Last Name is left blank")
    public void testBlankLastName() {
        checkoutInfoPage.fillInfo(TestData.VALID_FIRST_NAME, "", TestData.VALID_ZIP);
        checkoutInfoPage.clickContinue();

        Assert.assertTrue(checkoutInfoPage.getErrorText().contains(TestData.ERROR_LAST_NAME_REQUIRED),
                "Expected the last-name-required error message");
    }

    @Test(description = "TC_NEG_07: Checkout fails when Zip/Postal Code is left blank")
    public void testBlankPostalCode() {
        checkoutInfoPage.fillInfo(TestData.VALID_FIRST_NAME, TestData.VALID_LAST_NAME, "");
        checkoutInfoPage.clickContinue();

        Assert.assertTrue(checkoutInfoPage.getErrorText().contains(TestData.ERROR_POSTAL_CODE_REQUIRED),
                "Expected the postal-code-required error message");
    }

    @Test(description = "TC_NEG_08: Checkout behavior with an empty cart does not break the Overview page")
    public void testEmptyCartCheckout() {
        // This class's setup adds 1 item - remove it to exercise the true empty-cart path,
        // then re-enter checkout from a genuinely empty cart.
        com.framework.base.DriverManager.getDriver().navigate().back(); // back to Cart page
        CartPage cartPage = new CartPage();
        cartPage.removeItem(TestData.BACKPACK);
        Assert.assertEquals(cartPage.getItemCount(), 0, "Cart should be empty before this scenario");

        CheckoutInfoPage infoPage = cartPage.checkout();
        infoPage.fillInfo(TestData.VALID_FIRST_NAME, TestData.VALID_LAST_NAME, TestData.VALID_ZIP);
        infoPage.clickContinue();

        Assert.assertTrue(com.framework.base.DriverManager.getDriver().getCurrentUrl().contains("checkout-step-two"),
                "Overview page should still load (not a broken/blank state) even with an empty cart");
    }
}
