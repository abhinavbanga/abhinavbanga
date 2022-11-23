package com.saucedemo.selenium.pom.cart;

import com.saucedemo.selenium.pom.SauceDemoPom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.saucedemo.util.Globals.SAUCE_DEMO_CART_PAGE_URL;

public class CartPage extends SauceDemoPom {
    @FindBy(className = "btn_secondary")
    private WebElement continueShoppingButton;

    @FindBy(className = "checkout_button")
    private WebElement checkoutButton;

    @FindBy(className = "cart_item")
    private List<WebElement> cartProductsList;

    public void open() {
        driver.get(SAUCE_DEMO_CART_PAGE_URL);
    }

    public void goBack() {
        continueShoppingButton.click();
    }

    public String productNameAtIndexOf(int index) {
        return cartProductsList.get(index).findElement(By.className("inventory_item_name")).getText();
    }

    public String productDescriptionAtIndexOf(int index) {
        return cartProductsList.get(index).findElement(By.className("inventory_item_desc")).getText();
    }

    public String productPriceAtIndexOf(int index) {
        return "$" + cartProductsList.get(index).findElement(By.className("item_pricebar")).getText();
    }

    public int productInCartAtIndexOf(String name, String description, String price) {
        int index = 0;
        for (WebElement elem: cartProductsList) {
            String productName = productNameAtIndexOf(index);
            String productDescription = productDescriptionAtIndexOf(index);
            String productPrice = productPriceAtIndexOf(index);

            if(productName.equals(name) && productDescription.equals(description) && productPrice.equals(price)){
                return index;
            }

            index++;
        }
        return -1;
    }

    public void removeProductFromCartAtIndexOf(int index) {
        cartProductsList.get(index).findElement(By.className("cart_button")).click();
    }
}
