package com.saucedemo.selenium.pom;

import com.saucedemo.selenium.SauceDemoAppBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoPom {
    protected WebDriver driver;

    public final void initElements (SauceDemoAppBrowser sauceDemoAppBrowser) {
        this.driver = sauceDemoAppBrowser.getDriver();

        PageFactory.initElements(driver, this);
    }
}
