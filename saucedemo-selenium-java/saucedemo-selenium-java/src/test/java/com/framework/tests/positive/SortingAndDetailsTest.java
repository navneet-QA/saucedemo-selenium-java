package com.framework.tests.positive;

import com.framework.data.TestData;
import com.framework.pages.ProductDetailsPage;
import com.framework.pages.ProductsPage;
import com.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingAndDetailsTest extends BaseTest {

    @Test(description = "TC_POS_06: Sort products by Name (A to Z)")
    public void testSortByNameAToZ() {
        ProductsPage productsPage = loginAsStandardUser();

        productsPage.sortBy("Name (A to Z)");
        List<String> names = productsPage.getDisplayedProductNames();

        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);

        Assert.assertEquals(names, sorted, "Products should be sorted alphabetically A to Z");
    }

    @Test(description = "TC_POS_07: Sort products by Price (low to high)")
    public void testSortByPriceLowToHigh() {
        ProductsPage productsPage = loginAsStandardUser();

        productsPage.sortBy("Price (low to high)");
        List<Double> prices = productsPage.getDisplayedProductPrices();

        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);

        Assert.assertEquals(prices, sorted, "Products should be sorted from lowest to highest price");
    }

    @Test(description = "TC_POS_08: View product details by clicking product name")
    public void testViewProductDetails() {
        ProductsPage productsPage = loginAsStandardUser();

        productsPage.clickProductName(TestData.BACKPACK);

        ProductDetailsPage detailsPage = new ProductDetailsPage();
        Assert.assertEquals(detailsPage.getProductName(), TestData.BACKPACK,
                "Product details page should show the selected product's name");
    }
}
