package com.saucedemo.automation.cart;

import com.saucedemo.automation.SauceDemoTest;
import com.saucedemo.selenium.pom.cart.CartDto;
import com.saucedemo.selenium.pom.cart.CartPage;
import com.saucedemo.selenium.pom.login.LoginPage;
import com.saucedemo.selenium.pom.product.ProductPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.saucedemo.automation.DataProviderUtils.getDataObjectArray;
import static com.saucedemo.util.Globals.ADD_TEXT;
import static com.saucedemo.util.Globals.REMOVE_TEXT;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPageTest extends SauceDemoTest {
    private static String FILE_NAME = "cart/cart.csv";
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @Override
    public void init() {
        loginPage = createPomInstance(LoginPage.class);
        productPage = createPomInstance(ProductPage.class);
        cartPage = createPomInstance(CartPage.class);
    }

    @DataProvider(name = "getCart")
    public static Object[][] getLogin(Method testMethod) {
        String testCaseCode = testMethod.getAnnotation(Test.class).testName();

        return getDataObjectArray(CartDto.class, FILE_NAME, testCaseCode);
    }

    public void goToProductForm(String name, String pass) {
        loginPage.open()
                .setUserName(name)
                .setPassword(pass)
                .login();
    }

    @Test(testName = "TC-1", description = "Product Name in both Products page and Cart page should be same", dataProvider = "getCart")
    public void testProductNameShouldBeSame(CartDto cartDto) {
        goToProductForm(cartDto.getUserName(), cartDto.getPassword());
        productPage.removeAllProducts(REMOVE_TEXT);
        int noOfProducts = productPage.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            String productName = productPage.getProductNameAtIndexOf(i);
            productPage.addProductAtIndexOf(i, ADD_TEXT);
            cartPage.open();
            assertThat(productName.equals(cartPage.productNameAtIndexOf(0))).isTrue();
            cartPage.removeProductFromCartAtIndexOf(0);
            cartPage.goBack();
        }
    }

    @Test(testName = "TC-2", description = "Product Description in both Products page and Cart page should be same", dataProvider = "getCart")
    public void testProductDescriptionShouldBeSame(CartDto cartDto) {
        goToProductForm(cartDto.getUserName(), cartDto.getPassword());
        productPage.removeAllProducts(REMOVE_TEXT);
        int noOfProducts = productPage.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            String productDescription = productPage.getProductDescriptionAtIndexOf(i);
            productPage.addProductAtIndexOf(i, ADD_TEXT);
            cartPage.open();
            assertThat(productDescription.equals(cartPage.productDescriptionAtIndexOf(0))).isTrue();
            cartPage.removeProductFromCartAtIndexOf(0);
            cartPage.goBack();
        }
    }

    @Test(testName = "TC-3", description = "Product Price in both Products page and Cart page should be same", dataProvider = "getCart")
    public void testProductPriceShouldBeSame(CartDto cartDto) {
        goToProductForm(cartDto.getUserName(), cartDto.getPassword());
        productPage.removeAllProducts(REMOVE_TEXT);
        int noOfProducts = productPage.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            String productPrice = productPage.getProductPriceAtIndexOf(i);
            productPage.addProductAtIndexOf(i, ADD_TEXT);
            cartPage.open();
            assertThat(productPrice.equals(cartPage.productPriceAtIndexOf(0))).isTrue();
            cartPage.removeProductFromCartAtIndexOf(0);
            cartPage.goBack();
        }
    }
}
