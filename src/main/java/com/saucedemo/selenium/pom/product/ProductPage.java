package com.saucedemo.selenium.pom.product;

import com.saucedemo.selenium.pom.SauceDemoPom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends SauceDemoPom {
    @FindBy(className = "product_sort_container")
    private WebElement productSortDropdown;

    @FindBy(className = "inventory_item")
    private List<WebElement> products;

    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCart;

    @FindBy(className = "bm-burger-button")
    private WebElement menuButton;

    private final double eps = 1e-6;

    private boolean isInAscending(Object prevElem, Object currElem) {
        if (prevElem instanceof String)
            return ((String)prevElem).compareTo((String)currElem) < 0;

        return (Double)prevElem < (Double)currElem;
    }

    public void setSortOrder(String sortOrderText) {
        Select productSortChoice = new Select(productSortDropdown);
        productSortChoice.selectByVisibleText(sortOrderText);
    }

    public boolean checkProductSortOrder(boolean isName, boolean isAsc) {
        boolean firstProduct = true;
        double prevPrice = 0;
        String prevProductName = "";

        for (WebElement elem: products) {
            if (isName) {
                String currentProductName = elem.findElement(By.className("inventory_item_name")).getText();
                if (!firstProduct) {
                    if (isAsc) {
                        if (!isInAscending(prevProductName, currentProductName) && !(prevProductName.equals(currentProductName)))
                            return false;
                    } else {
                        if (isInAscending(prevProductName, currentProductName) && !(prevProductName.equals(currentProductName)))
                            return false;
                    }
                }
                prevProductName = currentProductName;

            } else {
                String currentPriceStr = elem.findElement(By.className("inventory_item_price")).getText();
                double currentPrice = Double.parseDouble(currentPriceStr.substring(1));
                if (!firstProduct) {
                    if (isAsc) {
                        if (!isInAscending(prevPrice, currentPrice) && !(Math.abs(prevPrice - currentPrice) <= eps))
                            return false;
                    } else {
                        if (isInAscending(prevPrice, currentPrice) && !(Math.abs(prevPrice - currentPrice) <= eps))
                            return false;
                    }
                }
                prevPrice = currentPrice;
            }
            firstProduct = false;
        }

        return true;
    }

    public int countOfProducts() {
        return products.size();
    }

    public void removeProductAtIndexOf(int index, String removeText) {
        WebElement productButton = products.get(index).findElement(By.className("btn_inventory"));
        String label = productButton.getText().toLowerCase();

        if (label.equals(removeText.toLowerCase()))
            productButton.click();
    }

    public ProductPage removeAllProducts(String removeText) {
        int count = countOfProducts();

        for (int i = 0; i < count; i++) {
            removeProductAtIndexOf(i, removeText);
        }

        return this;
    }

    public void addProductAtIndexOf(int index, String addText) {
        WebElement productButton = products.get(index).findElement(By.className("btn_inventory"));
        String label = productButton.getText().toLowerCase();

        if (label.equals(addText.toLowerCase()))
            productButton.click();
    }

    public ProductPage addAllProducts(String addText) {
        int count = countOfProducts();

        for (int i = 0; i < count; i++) {
            addProductAtIndexOf(i, addText);
        }

        return this;
    }

    public int countOfProductsInShoppingCart() {
        List <WebElement> shoppingCartBadge = shoppingCart.findElements(By.className("shopping_cart_badge"));
        int size = shoppingCartBadge.size();

        if (size > 0){
            for (WebElement elem: shoppingCartBadge){
                return Integer.parseInt(elem.getText());
            }
        }

        return size;
    }

    public String getProductNameAtIndexOf(int index) {
        return products.get(index).findElement(By.className("inventory_item_name")).getText();
    }

    public String getProductDescriptionAtIndexOf(int index) {
        return products.get(index).findElement(By.className("inventory_item_desc")).getText();
    }

    public String getProductPriceAtIndexOf(int index) {
        return products.get(index).findElement(By.className("inventory_item_price")).getText();
    }

    public void goToProductDetailAtIndexOf(int index) {
        products.get(index).findElement(By.className("inventory_item_name")).click();
    }
}
