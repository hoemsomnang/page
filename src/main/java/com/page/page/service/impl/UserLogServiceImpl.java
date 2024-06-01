package com.page.page.service.impl;

import com.page.page.dao.UserLogInfoDAO;
import com.page.page.service.UserLogService;
import com.page.page.util.DataUtil;
import com.page.page.util.DateUtil;
import com.page.page.util.RequestHeader;
import com.page.page.util.ResponseHeader;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    UserLogInfoDAO userLogInfoDAO;
    @Override
    public DataUtil registerUserErrorLogInfo(RequestHeader header, ResponseHeader responseHeader, Exception e) {

        DataUtil registerUserLogInfo = new DataUtil();
        try {
            if (StringUtils.isBlank(header.getUserName()) || StringUtils.isEmpty(header.getUserName())){
                registerUserLogInfo.setString("userName","General".concat(DateUtil.getCurrentFormatDate(DateUtil.TIME)));
            } else {
                registerUserLogInfo.setString("userName",header.getUserName());
            }
            if (StringUtils.isBlank(header.getUserType()) || StringUtils.isEmpty(header.getUserType())){
                registerUserLogInfo.setString("userType","GN");
            } else {
                registerUserLogInfo.setString("userType",header.getUserType());
            }
            registerUserLogInfo.setString("registerDate",DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerUserLogInfo.setString("registerTime",DateUtil.getCurrentFormatDate(DateUtil.TIME));
            registerUserLogInfo.setString("url","");
            registerUserLogInfo.setString("ipAddress","");
            registerUserLogInfo.setString("deviceName","");
            registerUserLogInfo.setString("errorCode",responseHeader.getResultCode());
            registerUserLogInfo.setString("errorDescription",responseHeader.getResultMessage());
            registerUserLogInfo.setString("errorCause",ExceptionUtils.getStackTrace(e));
            userLogInfoDAO.registerUserErrorLogInfo(registerUserLogInfo);

        } catch ( Exception ex ) {
            System.out.println(e);
        }
        return registerUserLogInfo;
    }
}
