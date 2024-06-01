package com.page.page.util;

public class RequestData<T> {
    private RequestHeader header;
    private T body;

    public RequestData(RequestHeader header, T body) {
        this.header = header;
        this.body = body;
    }
    public RequestData() {

    }

    public RequestHeader getHeader() {
        return header;
    }

    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
