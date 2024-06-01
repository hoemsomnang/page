package com.page.page.util;

public class RequestHeader {
    private String userName="";
    private String userType="";
    private String language ="";
    public RequestHeader( ) {
        super();
    }
    public RequestHeader(String userName, String userType, String language) {
        this.userName = userName;
        this.userType = userType;
        this.language = language;
    }

    @Override
    public String toString() {
        return "RequestHeader{" +
                "userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
