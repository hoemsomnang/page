package com.page.page.service;


import com.page.page.util.DataUtil;
import com.page.page.util.RequestHeader;
import com.page.page.util.ResponseHeader;

public interface UserLogService {
    DataUtil registerUserErrorLogInfo(RequestHeader header, ResponseHeader responseHeader, Exception e) ;
}
