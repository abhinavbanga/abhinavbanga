package com.saucedemo.automation.checkout;

import com.saucedemo.automation.SauceDemoTest;
import com.saucedemo.selenium.pom.checkout.CheckoutStepOneDto;
import com.saucedemo.selenium.pom.checkout.CheckoutStepOnePage;
import com.saucedemo.selenium.pom.login.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.saucedemo.automation.DataProviderUtils.getDataObjectArray;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutStepOnePageTest extends SauceDemoTest {
    private static String FILE_NAME = "checkout/checkoutStepOne.csv";
    private LoginPage loginPage;
    private CheckoutStepOnePage checkoutStepOnePage;

    @Override
    public void init() {
        loginPage = createPomInstance(LoginPage.class);
        checkoutStepOnePage = createPomInstance(CheckoutStepOnePage.class);
    }

    @DataProvider(name = "getCheckoutStepOne")
    public static Object[][] getCheckoutStepOne(Method testMethod) {
        String testCaseCode = testMethod.getAnnotation(Test.class).testName();

        return getDataObjectArray(CheckoutStepOneDto.class, FILE_NAME, testCaseCode);
    }

    public void goToCheckoutStepOneFormAndFillUp(CheckoutStepOneDto checkoutStepOneDto) {
        loginPage.open()
                .setUserName(checkoutStepOneDto.getUserName())
                .setPassword(checkoutStepOneDto.getPassword())
                .login();

        checkoutStepOnePage.open()
                           .setFirstName(checkoutStepOneDto.getFirstName())
                           .setLastName(checkoutStepOneDto.getLastName())
                           .setZipCode(checkoutStepOneDto.getZipCode())
                           .goNext();
    }

    @Test(testName = "TC-1", description = "First Name is required", dataProvider = "getCheckoutStepOne")
    public void testFirstNameNotEmpty(CheckoutStepOneDto checkoutStepOneDto) {
        goToCheckoutStepOneFormAndFillUp(checkoutStepOneDto);
        assertThat(checkoutStepOnePage.isErrorMsgExist(checkoutStepOneDto.getErrorMessage())).isTrue();
    }

    @Test(testName = "TC-2", description = "Last Name is required", dataProvider = "getCheckoutStepOne")
    public void testLastNameNotEmpty(CheckoutStepOneDto checkoutStepOneDto) {
        goToCheckoutStepOneFormAndFillUp(checkoutStepOneDto);
        assertThat(checkoutStepOnePage.isErrorMsgExist(checkoutStepOneDto.getErrorMessage())).isTrue();
    }

    @Test(testName = "TC-3", description = "Postal Code is required", dataProvider = "getCheckoutStepOne")
    public void testPostalCodeNotEmpty(CheckoutStepOneDto checkoutStepOneDto) {
        goToCheckoutStepOneFormAndFillUp(checkoutStepOneDto);
        assertThat(checkoutStepOnePage.isErrorMsgExist(checkoutStepOneDto.getErrorMessage())).isTrue();
    }
}
