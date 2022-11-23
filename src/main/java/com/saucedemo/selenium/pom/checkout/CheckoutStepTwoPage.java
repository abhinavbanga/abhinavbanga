package com.saucedemo.selenium.pom.checkout;

import com.saucedemo.selenium.pom.SauceDemoPom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.saucedemo.util.Globals.SAUCE_DEMO_CHECKOUT_STEP_TWO_PAGE_URL;

import java.util.List;

public class CheckoutStepTwoPage extends SauceDemoPom {
    @FindBy(className = "cart_item")
    private List<WebElement> cartProductsList;

    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    public void open() {
        driver.get(SAUCE_DEMO_CHECKOUT_STEP_TWO_PAGE_URL);
    }

    public double getSubtotal() {
        return Double.parseDouble(subtotalLabel.getText().substring(13));
    }

    public double getTax() {
        return Double.parseDouble(taxLabel.getText().substring(6));
    }

    public double getTotal() {
        return Double.parseDouble(totalLabel.getText().substring(8));
    }

    public double calculateTotal() {
        double totalPrice = 0.0;
        for (WebElement elem: cartProductsList) {
            double currentPrice = Double.parseDouble(elem.findElement(By.className("item_pricebar")).getText().substring(1));
            totalPrice += currentPrice;
        }
        return totalPrice;
    }
}
