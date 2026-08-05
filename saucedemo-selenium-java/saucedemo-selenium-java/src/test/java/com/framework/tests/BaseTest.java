package com.framework.tests;

import com.framework.base.DriverManager;
import com.framework.config.ConfigReader;
import com.framework.data.TestData;
import com.framework.pages.LoginPage;
import com.framework.pages.ProductsPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/** All test classes extend this to get a fresh browser per test method. */
public class BaseTest {

    protected LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.initDriver();
        DriverManager.getDriver().get(ConfigReader.get("url"));
        loginPage = new LoginPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    /** Helper for tests that need to start already logged in. */
    protected ProductsPage loginAsStandardUser() {
        return loginPage.loginAs(TestData.STANDARD_USER, TestData.PASSWORD);
    }
}
