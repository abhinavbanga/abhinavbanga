package com.saucedemo.automation.product;

import com.saucedemo.automation.SauceDemoTest;
import com.saucedemo.selenium.pom.login.LoginPage;
import com.saucedemo.selenium.pom.product.ProductDto;
import com.saucedemo.selenium.pom.product.ProductPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.saucedemo.automation.DataProviderUtils.getDataObjectArray;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductPageTest extends SauceDemoTest {
    private static String FILE_NAME = "product/product.csv";
    private LoginPage loginPage;
    private ProductPage productsForm;

    @Override
    public void init() {
        loginPage = createPomInstance(LoginPage.class);
        productsForm = createPomInstance(ProductPage.class);
    }

    @DataProvider(name = "getProducts")
    public static Object[][] getProducts(Method testMethod) {
        String testCaseCode = testMethod.getAnnotation(Test.class).testName();

        return getDataObjectArray(ProductDto.class, FILE_NAME, testCaseCode);
    }

    public void goToProductForm(String name, String pass) {
        loginPage.open()
                .setUserName(name)
                .setPassword(pass)
                .login();
    }

    public void goToProductFormAndSetSortOrder(String name, String pass, String sortOrderText) {
        goToProductForm(name, pass);
        productsForm.setSortOrder(sortOrderText);
    }

    @Test(testName = "TC-1", description = "Products are sorted in lexicographically ascending order of product names", dataProvider = "getProducts")
    public void testLexicographicallyAscendingByProductNames(ProductDto productsDto) {
        goToProductFormAndSetSortOrder(productsDto.getUserName(), productsDto.getPassword(), productsDto.getSortOrderText());
        assertThat(productsForm.checkProductSortOrder(productsDto.getIsName().equals("Y"), productsDto.getIsAsc().equals("Y"))).isTrue();
    }

    @Test(testName = "TC-2", description = "Products are sorted in lexicographically descending order of product names", dataProvider = "getProducts")
    public void testLexicographicallyDescendingByProductNames(ProductDto productsDto) {
        goToProductFormAndSetSortOrder(productsDto.getUserName(), productsDto.getPassword(), productsDto.getSortOrderText());
        assertThat(productsForm.checkProductSortOrder(productsDto.getIsName().equals("Y"), productsDto.getIsAsc().equals("Y"))).isTrue();
    }

    @Test(testName = "TC-3", description = "Products are sorted in ascending order of product prices", dataProvider = "getProducts")
    public void testLexicographicallyAscendingByProductPrices(ProductDto productsDto) {
        goToProductFormAndSetSortOrder(productsDto.getUserName(), productsDto.getPassword(), productsDto.getSortOrderText());
        assertThat(productsForm.checkProductSortOrder(productsDto.getIsName().equals("Y"), productsDto.getIsAsc().equals("Y"))).isTrue();
    }

    @Test(testName = "TC-4", description = "Products are sorted in descending order of product prices", dataProvider = "getProducts")
    public void testLexicographicallyDescendingByProductPrices(ProductDto productsDto) {
        goToProductFormAndSetSortOrder(productsDto.getUserName(), productsDto.getPassword(), productsDto.getSortOrderText());
        assertThat(productsForm.checkProductSortOrder(productsDto.getIsName().equals("Y"), productsDto.getIsAsc().equals("Y"))).isTrue();
    }

    @Test(testName = "TC-5", description = "No product is added in the shopping cart", dataProvider = "getProducts")
    public void testNoProductInShoppingCart(ProductDto productsDto) {
        goToProductForm(productsDto.getUserName(), productsDto.getPassword());

        productsForm.addAllProducts(productsDto.getLabelForAdd())
                    .removeAllProducts(productsDto.getLabelForRemove());

        assertThat(productsForm.countOfProductsInShoppingCart() == 0).isTrue();
    }

    @Test(testName = "TC-6", description = "All product are added in the shopping cart", dataProvider = "getProducts")
    public void testAllProductsInShoppingCart(ProductDto productsDto) {
        goToProductForm(productsDto.getUserName(), productsDto.getPassword());

        productsForm.removeAllProducts(productsDto.getLabelForRemove())
                    .addAllProducts(productsDto.getLabelForAdd());

        assertThat(productsForm.countOfProductsInShoppingCart() == productsForm.countOfProducts()).isTrue();
    }
}
