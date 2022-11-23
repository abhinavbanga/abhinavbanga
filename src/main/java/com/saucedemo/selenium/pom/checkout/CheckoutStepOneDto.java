package com.saucedemo.selenium.pom.checkout;

import com.saucedemo.selenium.pom.TestCaseDto;
import com.univocity.parsers.annotations.Parsed;

public class CheckoutStepOneDto extends TestCaseDto {
    @Parsed(field = "userName", defaultNullRead = "")
    private String userName;

    @Parsed(field = "password", defaultNullRead = "")
    private String password;

    @Parsed(field = "firstName", defaultNullRead = "")
    private String firstName;

    @Parsed(field = "lastName", defaultNullRead = "")
    private String lastName;

    @Parsed(field = "postalCode", defaultNullRead = "")
    private String postalCode;

    @Parsed(field = "errorMessage", defaultNullRead = "")
    private String errorMessage;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getZipCode() {
        return postalCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
