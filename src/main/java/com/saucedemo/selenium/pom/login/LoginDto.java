package com.saucedemo.selenium.pom.login;

import com.saucedemo.selenium.pom.TestCaseDto;
import com.univocity.parsers.annotations.Parsed;

public class LoginDto extends TestCaseDto {
    @Parsed(field = "userName", defaultNullRead = "")
    private String userName;

    @Parsed(field = "password", defaultNullRead = "")
    private String password;

    @Parsed(field = "errorMessage", defaultNullRead = "")
    private String errorMessage;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
