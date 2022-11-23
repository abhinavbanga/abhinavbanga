package com.saucedemo.util;

import java.util.HashMap;
import java.util.Map;

public enum BrowserType {
    CHROME("Chrome"), FIREFOX("Firefox"), IE("IE");

    private static final Map<String, BrowserType> browserTypeMap = new HashMap<>(3);

    static {
        browserTypeMap.put(CHROME.getNameInUpperCase(), CHROME);
        browserTypeMap.put(FIREFOX.getNameInUpperCase(), FIREFOX);
        browserTypeMap.put(IE.getNameInUpperCase(), IE);
    }

    private String name;

    BrowserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNameInUpperCase() {
        return name.toUpperCase();
    }

    public static BrowserType getBrowserType(String browserType) {
        return StringUtils.isEmpty(browserType) ? CHROME: browserTypeMap.get(browserType.toUpperCase());
    }
}
