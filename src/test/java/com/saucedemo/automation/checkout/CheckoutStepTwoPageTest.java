package com.saucedemo.automation.checkout;

import com.saucedemo.automation.SauceDemoTest;
import com.saucedemo.selenium.pom.checkout.CheckoutStepTwoPage;
import com.saucedemo.selenium.pom.login.LoginPage;
import com.saucedemo.selenium.pom.product.ProductPage;

public class CheckoutStepTwoPageTest extends SauceDemoTest {
    private static String FILE_NAME = "checkout/checkoutStepTwo.csv";
    private LoginPage loginPage;
    private ProductPage productPage;
    private CheckoutStepTwoPage checkoutStepTwoPage;

    @Override
    public void init() {
        loginPage = createPomInstance(LoginPage.class);
        productPage = createPomInstance(ProductPage.class);
        checkoutStepTwoPage = createPomInstance(CheckoutStepTwoPage.class);
    }
}
