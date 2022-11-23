package com.saucedemo.selenium;

import com.saucedemo.util.BrowserType;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class SauceDemoAppBrowser {
    public static final int PAGE_TIME_OUT = 10;
    public static final String PROPS_BROWSER_TYPE = "saucedemo.browser.type";

    private WebDriver driver;

    public SauceDemoAppBrowser() {
        this(PAGE_TIME_OUT);
    }

    public SauceDemoAppBrowser(int pageTimeOut) {
        String userDefinedBrowserType = System.getProperty(PROPS_BROWSER_TYPE);
        BrowserType browserType = BrowserType.getBrowserType(userDefinedBrowserType);

        if (browserType == null) {
            throw new RuntimeException("Invalid browser type [" + userDefinedBrowserType + "]");
        }

        if (SystemUtils.IS_OS_LINUX) {
            System.setProperty("webdriver.chrome.driver", "chrome-driver/linux/chromedriverGt65");
            System.setProperty("webdriver.gecko.driver", "gecko-driver/linux/geckodriver-v0.24.0-linux64");
        } else if (SystemUtils.IS_OS_MAC) {
            System.setProperty("webdriver.chrome.driver", "chrome-driver/mac/chromedriver");
            System.setProperty("webdriver.gecko.driver", "gecko-driver/mac/geckodriver-v0.24.0-macos");
        } else if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver", "chrome-driver\\windows\\chromedriver.exe");
            System.setProperty("webdriver.gecko.driver", "gecko-driver\\windows\\geckodriver-v0.24.0-win64.exe");
        }

        if (browserType.equals(BrowserType.CHROME)) {
            driver = new ChromeDriver();
        } else if (browserType.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browserType.equals(BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        }

        driver.manage().timeouts().implicitlyWait(pageTimeOut, TimeUnit.SECONDS);

        try {
            driver.manage().window().maximize();
        } catch (Exception e) {

        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeBrowser() {
        driver.close();
    }

    public static BrowserType getBrowserType() {
        return BrowserType.getBrowserType(PROPS_BROWSER_TYPE);
    }
}
