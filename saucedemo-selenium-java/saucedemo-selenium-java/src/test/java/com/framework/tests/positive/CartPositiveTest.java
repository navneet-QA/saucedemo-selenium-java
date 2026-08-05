package com.framework.tests.positive;

import com.framework.data.TestData;
import com.framework.pages.CartPage;
import com.framework.pages.ProductsPage;
import com.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPositiveTest extends BaseTest {

    @Test(description = "TC_POS_03: Add a single product to the cart")
    public void testAddSingleProductToCart() {
        ProductsPage productsPage = loginAsStandardUser();

        productsPage.addProductToCart(TestData.BACKPACK);

        Assert.assertEquals(productsPage.getCartCount(), 1, "Cart badge should show 1 item");
    }

    @Test(description = "TC_POS_04: Cart badge count updates correctly for multiple products")
    public void testCartBadgeUpdatesForMultipleProducts() {
        ProductsPage productsPage = loginAsStandardUser();

        productsPage.addProductToCart(TestData.BACKPACK);
        productsPage.addProductToCart(TestData.BIKE_LIGHT);
        productsPage.addProductToCart(TestData.BOLT_TSHIRT);

        Assert.assertEquals(productsPage.getCartCount(), 3, "Cart badge should show 3 items");
    }

    @Test(description = "TC_POS_05: Remove a product from the Cart page")
    public void testRemoveProductFromCartPage() {
        ProductsPage productsPage = loginAsStandardUser();
        productsPage.addProductToCart(TestData.BACKPACK);
        productsPage.openCart();

        CartPage cartPage = new CartPage();
        Assert.assertEquals(cartPage.getItemCount(), 1, "Cart should have 1 item before removal");

        cartPage.removeItem(TestData.BACKPACK);

        Assert.assertEquals(cartPage.getItemCount(), 0, "Cart should be empty after removing the item");
    }

    @Test(description = "TC_POS_12: 'Reset App State' clears the cart")
    public void testResetAppStateClearsCart() {
        ProductsPage productsPage = loginAsStandardUser();
        productsPage.addProductToCart(TestData.BACKPACK);
        productsPage.addProductToCart(TestData.BIKE_LIGHT);
        Assert.assertEquals(productsPage.getCartCount(), 2, "Cart should have 2 items before reset");
        
        productsPage.resetAppState();

        Assert.assertEquals(productsPage.getCartCount(), 0, "Cart should be empty after Reset App State");
    }
}
