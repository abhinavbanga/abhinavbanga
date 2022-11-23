package com.saucedemo.automation.product;

import com.saucedemo.automation.SauceDemoTest;
import com.saucedemo.selenium.pom.login.LoginPage;
import com.saucedemo.selenium.pom.product.ProductDetailPage;
import com.saucedemo.selenium.pom.product.ProductDto;
import com.saucedemo.selenium.pom.product.ProductPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.saucedemo.automation.DataProviderUtils.getDataObjectArray;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductDetailPageTest extends SauceDemoTest {
    private static String FILE_NAME = "product/productDetail.csv";
    private LoginPage loginPage;
    private ProductPage productPage;
    private ProductDetailPage productDetailPage;

    @Override
    public void init() {
        loginPage = createPomInstance(LoginPage.class);
        productPage = createPomInstance(ProductPage.class);
        productDetailPage = createPomInstance(ProductDetailPage.class);
    }

    @DataProvider(name = "getProductDetail")
    public static Object[][] getProductDetail(Method testMethod) {
        String testCaseCode = testMethod.getAnnotation(Test.class).testName();

        return getDataObjectArray(ProductDto.class, FILE_NAME, testCaseCode);
    }

    public void goToProductForm(String name, String pass) {
        loginPage.open()
                .setUserName(name)
                .setPassword(pass)
                .login();
    }

    @Test(testName = "TC-1", description = "Product Name in both Product page and Product Detail page should be same", dataProvider = "getProductDetail")
    public void testProductNameShouldBeSame(ProductDto productDto) {
        goToProductForm(productDto.getUserName(), productDto.getPassword());
        int noOfProducts = productPage.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            String productName = productPage.getProductNameAtIndexOf(i);
            productPage.goToProductDetailAtIndexOf(i);
            assertThat(productName.equals(productDetailPage.getProductName())).isTrue();
            productDetailPage.goBack();
        }
    }

    @Test(testName = "TC-2", description = "Product Description in both Product page and Product Detail page should be same", dataProvider = "getProductDetail")
    public void testProductDescriptionShouldBeSame(ProductDto productDto) {
        goToProductForm(productDto.getUserName(), productDto.getPassword());
        int noOfProducts = productPage.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            String productDescription = productPage.getProductDescriptionAtIndexOf(i);
            productPage.goToProductDetailAtIndexOf(i);
            assertThat(productDescription.equals(productDetailPage.getProductDescription())).isTrue();
            productDetailPage.goBack();
        }
    }

    @Test(testName = "TC-3", description = "Product Price in both Product page and Product Detail page should be same", dataProvider = "getProductDetail")
    public void testProductPriceShouldBeSame(ProductDto productDto) {
        goToProductForm(productDto.getUserName(), productDto.getPassword());
        int noOfProducts = productPage.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            String productPrice = productPage.getProductPriceAtIndexOf(i);
            productPage.goToProductDetailAtIndexOf(i);
            assertThat(productPrice.equals(productDetailPage.getProductPrice())).isTrue();
            productDetailPage.goBack();
        }
    }

    @Test(testName = "TC-4", description = "Product is correctly removed from Product Detail page", dataProvider = "getProductDetail")
    public void testProductRemovedCorrectly(ProductDto productDto) {
        goToProductForm(productDto.getUserName(), productDto.getPassword());
        productPage.addAllProducts(productDto.getLabelForAdd());
        int noOfProducts = productPage.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            productPage.goToProductDetailAtIndexOf(i);

            productDetailPage.removeProduct(productDto.getLabelForRemove())
                              .goBack();

            assertThat(productPage.countOfProductsInShoppingCart() == (noOfProducts - i - 1)).isTrue();
        }
    }

    @Test(testName = "TC-5", description = "Product is correctly added from Product Detail page", dataProvider = "getProductDetail")
    public void testProductAddedCorrectly(ProductDto productDto) {
        goToProductForm(productDto.getUserName(), productDto.getPassword());
        productPage.removeAllProducts(productDto.getLabelForRemove());
        int noOfProducts = productPage.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            productPage.goToProductDetailAtIndexOf(i);

            productDetailPage.addProduct(productDto.getLabelForAdd())
                             .goBack();

            assertThat(productPage.countOfProductsInShoppingCart() == (i + 1)).isTrue();
        }
    }
}
