package com.saucedemo.selenium.pom;

import com.saucedemo.selenium.SauceDemoAppBrowser;

public class SauceDemoPomFactory {
    public static <T extends SauceDemoPom> T createInstance (SauceDemoAppBrowser sauceDemoAppBrowser, Class<? extends SauceDemoPom> pomClass) {
        try{
            SauceDemoPom instance = pomClass.newInstance();
            instance.initElements(sauceDemoAppBrowser);
            return (T) pomClass.cast(instance);
        }catch (Exception e) {
            e.printStackTrace();
        }

        throw new NullPointerException("POM class creation failed.");
    }
}
