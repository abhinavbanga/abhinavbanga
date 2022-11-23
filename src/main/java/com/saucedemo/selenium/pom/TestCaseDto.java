package com.saucedemo.selenium.pom;

import com.univocity.parsers.annotations.Parsed;

public class TestCaseDto {
    @Parsed(field = "testCaseCode")
    protected String testCaseCode;

    @Parsed(field = "testCaseDescription")
    private String testCaseDescription;

    public String getTestCaseCode() {
        return testCaseCode;
    }

    public String getTestCaseDescription() {
        return testCaseDescription;
    }
}
