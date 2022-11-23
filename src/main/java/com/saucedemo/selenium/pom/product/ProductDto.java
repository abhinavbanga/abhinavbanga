package com.saucedemo.selenium.pom.product;

import com.saucedemo.selenium.pom.TestCaseDto;
import com.univocity.parsers.annotations.Parsed;

public class ProductDto extends TestCaseDto {
    @Parsed(field = "userName", defaultNullRead = "")
    private String userName;

    @Parsed(field = "password", defaultNullRead = "")
    private String password;

    @Parsed(field = "sortOrderText", defaultNullRead = "")
    private String sortOrderText;

    @Parsed(field = "isName", defaultNullRead = "")
    private String isName;

    @Parsed(field = "isAsc", defaultNullRead = "")
    private String isAsc;

    @Parsed(field = "labelForAdd", defaultNullRead = "")
    private String labelForAdd;

    @Parsed(field = "labelForRemove", defaultNullRead = "")
    private String labelForRemove;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getSortOrderText() {
        return sortOrderText;
    }

    public String getIsName() {
        return isName;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public String getLabelForAdd() {
        return labelForAdd;
    }

    public String getLabelForRemove() {
        return labelForRemove;
    }
}
