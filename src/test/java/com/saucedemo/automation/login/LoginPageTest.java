package com.saucedemo.automation.login;

import com.saucedemo.automation.SauceDemoTest;
import com.saucedemo.selenium.pom.login.LoginDto;
import com.saucedemo.selenium.pom.login.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

import static com.saucedemo.util.Globals.SAUCE_DEMO_PRODUCT_PAGE_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static com.saucedemo.automation.DataProviderUtils.getDataObjectArray;


public class LoginPageTest extends SauceDemoTest {
    private static String FILE_NAME = "login/login.csv";
    private LoginPage loginPage;

    @Override
    public void init() {
        loginPage = createPomInstance(LoginPage.class);
    }

    @DataProvider(name = "getLogin")
    public static Object[][] getLogin(Method testMethod) {
        String testCaseCode = testMethod.getAnnotation(Test.class).testName();

        return getDataObjectArray(LoginDto.class, FILE_NAME, testCaseCode);
    }

    public void goToLoginFormAndFillUp(LoginDto loginDto) {
        loginPage.open()
                 .setUserName(loginDto.getUserName())
                 .setPassword(loginDto.getPassword())
                 .login();
    }

    @Test(testName = "TC-1", description = "User Name cannot be empty", dataProvider = "getLogin")
    public void testUserNameNotEmpty(LoginDto loginDto) {
        goToLoginFormAndFillUp(loginDto);
        assertThat(loginPage.isErrorMsgExist(loginDto.getErrorMessage())).isTrue();
    }

    @Test(testName = "TC-2", description = "Password cannot be empty", dataProvider = "getLogin")
    public void testPasswordNotEmpty(LoginDto loginDto) {
        goToLoginFormAndFillUp(loginDto);
        assertThat(loginPage.isErrorMsgExist(loginDto.getErrorMessage())).isTrue();
    }

    @Test(testName = "TC-3", description = "User having User name: locked_out_user is not allowed to successfully login", dataProvider = "getLogin")
    public void testLockedOutUser(LoginDto loginDto) {
        goToLoginFormAndFillUp(loginDto);
        assertThat(loginPage.isErrorMsgExist(loginDto.getErrorMessage())).isTrue();
    }

    @Test(testName = "TC-4", description = "User Name is not one of the accepted user names of the site", dataProvider = "getLogin")
    public void testUserNameNotAccepted(LoginDto loginDto) {
        goToLoginFormAndFillUp(loginDto);
        assertThat(loginPage.isErrorMsgExist(loginDto.getErrorMessage())).isTrue();
    }

    @Test(testName = "TC-5", description = "Password is not one of the accepted passwords of the site", dataProvider = "getLogin")
    public void testPasswordNotAccepted(LoginDto loginDto) {
        goToLoginFormAndFillUp(loginDto);
        assertThat(loginPage.isErrorMsgExist(loginDto.getErrorMessage())).isTrue();
    }

    @Test(testName = "TC-6", description = "User Name and Password are accepted", dataProvider = "getLogin")
    public void testUserNameAndPasswordAccepted(LoginDto loginDto) {
        goToLoginFormAndFillUp(loginDto);
        assertThat(loginPage.getCurrentPageUrl().equals(SAUCE_DEMO_PRODUCT_PAGE_URL)).isTrue();
    }
}
