package com.saucedemo.selenium.pom.login;

import com.saucedemo.selenium.pom.SauceDemoPom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.saucedemo.util.Globals.SAUCE_DEMO_URL;

public class LoginPage extends SauceDemoPom {
    @FindBy(id = "user-name")
    private WebElement userNameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(className = "btn_action")
    private WebElement loginButton;

    public LoginPage open() {
        driver.get(SAUCE_DEMO_URL);
        return this;
    }

    public String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }

    public LoginPage setUserName(String name) {
        userNameField.clear();
        userNameField.sendKeys(name);
        return this;
    }

    public LoginPage setPassword(String pass) {
        passwordField.clear();
        passwordField.sendKeys(pass);
        return this;
    }

    public void login() {
        loginButton.click();
    }

    public boolean isErrorMsgExist(String errorMessage) {
        List<WebElement> elements = driver.findElements(By.cssSelector("h3[data-test='error']"));

        for (WebElement elem : elements) {
            if (elem.getText().contains(errorMessage)) {
                return true;
            }
        }

        return false;
    }
}
