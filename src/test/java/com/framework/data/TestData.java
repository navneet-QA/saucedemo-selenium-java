package com.framework.data;

/** Central place for saucedemo.com's known test accounts and shared test data. */
public class TestData {

    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_OUT_USER = "locked_out_user";
    public static final String PASSWORD = "secret_sauce";

    public static final String VALID_FIRST_NAME = "John";
    public static final String VALID_LAST_NAME = "Doe";
    public static final String VALID_ZIP = "411045";

    public static final String BACKPACK = "Sauce Labs Backpack";
    public static final String BIKE_LIGHT = "Sauce Labs Bike Light";
    public static final String BOLT_TSHIRT = "Sauce Labs Bolt T-Shirt";

    public static final String ERROR_INVALID_CREDENTIALS =
            "Epic sadface: Username and password do not match any user in this service";
    public static final String ERROR_USERNAME_REQUIRED = "Epic sadface: Username is required";
    public static final String ERROR_PASSWORD_REQUIRED = "Epic sadface: Password is required";
    public static final String ERROR_LOCKED_OUT = "Epic sadface: Sorry, this user has been locked out.";
    public static final String ERROR_FIRST_NAME_REQUIRED = "Error: First Name is required";
    public static final String ERROR_LAST_NAME_REQUIRED = "Error: Last Name is required";
    public static final String ERROR_POSTAL_CODE_REQUIRED = "Error: Postal Code is required";

    private TestData() {
    }
}
