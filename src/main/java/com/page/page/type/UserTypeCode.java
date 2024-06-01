package com.page.page.type;

public enum UserTypeCode {
    MOBILE( "01", "Mobile User"),
    WEB("02", "Web User");

    private String value;
    private String description;

    UserTypeCode(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
