package com.saucedemo.automation;

import com.saucedemo.selenium.SauceDemoAppBrowser;
import com.saucedemo.selenium.pom.SauceDemoPom;
import com.saucedemo.selenium.pom.SauceDemoPomFactory;
import com.saucedemo.selenium.pom.TestCaseDto;
import com.saucedemo.util.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public abstract class SauceDemoTest {
    private SauceDemoAppBrowser sauceDemoAppBrowser;

    public abstract void init();

    protected <T extends SauceDemoPom> T createPomInstance(Class<? extends SauceDemoPom> pomClass) {
        return SauceDemoPomFactory.createInstance(sauceDemoAppBrowser, pomClass);
    }

    @BeforeClass
    public final void beforeClass() {
        sauceDemoAppBrowser = new SauceDemoAppBrowser();
        init();
    }

    @BeforeMethod
    public final void beforeMethod(Object[] testData, ITestContext ctx) {
        if (ArrayUtils.isEmpty(testData)) {
            return;
        }

        Object testDataRow = testData[0];

        if (testDataRow instanceof TestCaseDto) {
            TestCaseDto testCaseDto = (TestCaseDto) testDataRow;

            if (StringUtils.isNotEmpty(testCaseDto.getTestCaseCode())) {
                ctx.setAttribute("testName", testCaseDto.getTestCaseCode());
            }

            if (StringUtils.isNotEmpty(testCaseDto.getTestCaseDescription())) {
                ctx.setAttribute("description", testCaseDto.getTestCaseDescription());
            }
        }
    }

    @AfterTest
    public void AfterTest() {
        sauceDemoAppBrowser.closeBrowser();
    }

    public SauceDemoAppBrowser getSauceDemoAppBrowser() {
        return sauceDemoAppBrowser;
    }
}
