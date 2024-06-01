package com.page.page.type;

public enum UserStatusTypeCode {

    NORMAL( "00", "Normal"),
    LOCKED( "01", "Locked"),
    DELETED( "99", "Deleted");

    private String value;
    private String description;

    UserStatusTypeCode(String value, String description) {
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
