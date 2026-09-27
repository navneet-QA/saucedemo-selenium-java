package com.framework.tests.negative;

import com.framework.data.TestData;
import com.framework.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginNegativeTest extends BaseTest {

    @Test(description = "TC_NEG_01: Login fails with an invalid username")
    public void testInvalidUsername() {
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword(TestData.PASSWORD);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorText().contains(TestData.ERROR_INVALID_CREDENTIALS),
                "Expected the invalid-credentials error message");
    }

    @Test(description = "TC_NEG_02: Login fails with an invalid password")
    public void testInvalidPassword() {
        loginPage.enterUsername(TestData.STANDARD_USER);
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorText().contains(TestData.ERROR_INVALID_CREDENTIALS),
                "Expected the invalid-credentials error message");
    }

    @Test(description = "TC_NEG_03: Login fails with empty username and password")
    public void testEmptyCredentials() {
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorText().contains(TestData.ERROR_USERNAME_REQUIRED),
                "Expected the username-required error message");
    }

    @Test(description = "TC_NEG_04: locked_out_user cannot login")
    public void testLockedOutUser() {
        loginPage.enterUsername(TestData.LOCKED_OUT_USER);
        loginPage.enterPassword(TestData.PASSWORD);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorText().contains(TestData.ERROR_LOCKED_OUT),
                "Expected the locked-out user error message");
    }

    @Test(description = "TC_NEG_09: Login fields handle SQL-injection-style input safely")
    public void testSqlInjectionInput() {
        loginPage.enterUsername("' OR '1'='1");
        loginPage.enterPassword("' OR '1'='1");
        loginPage.clickLogin();

        // Should fail exactly like any other bad credential pair - no bypass, no raw DB/script error.
        Assert.assertTrue(loginPage.getErrorText().contains(TestData.ERROR_INVALID_CREDENTIALS),
                "Expected the standard invalid-credentials error message, not a bypass or a raw error");
    }

    @Test(description = "TC_NEG_10: Login fails when password is left empty")
    public void testEmptyPassword() {
        loginPage.enterUsername(TestData.STANDARD_USER);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorText().contains(TestData.ERROR_PASSWORD_REQUIRED),
                "Expected the password-required error message");
    }
}
