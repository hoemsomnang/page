package com.page.page.service;

import com.page.page.dao.ErrorMessageDAO;
import com.page.page.type.ResponseResultMessage;
import com.page.page.util.DataUtil;
import com.page.page.util.RequestHeader;
import com.page.page.util.ResponseHeader;
import com.page.page.util.ThreadLocalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseResultMessageService {

    @Autowired
    ErrorMessageDAO errorMessageDAO;
    @Autowired
    UserLogService userLogService;
    public ResponseHeader resultLanguageMessage(RequestHeader header, Exception e ) {

        ResponseHeader responseHeader = ResponseResultMessage.resultOutputMessage(e);
        DataUtil param = new DataUtil();
        param.setString("errorCode", responseHeader.getResultCode() );
        if ( ( header !=null )
                && ( "kh".equals( header.getLanguage()) ) ) {
            param.setString("errorLanguage","kh");
        } else {
            param.setString("errorLanguage","en");
        }
        DataUtil errorMessageInfo = errorMessageDAO.retrieveErrorMessageInfo( param );
        if ( errorMessageInfo !=  null ) {
            responseHeader.setSuccessYN("N");
            responseHeader.setResultCode( errorMessageInfo.getString("errorCode") );
            responseHeader.setResultMessage( errorMessageInfo.getString("errorDescription") );
        }

        /************************************************
         * Replace Error Parameter in case has parameter
         ************************************************/
        try {
            String errorParam =  ThreadLocalUtil.getErrorMessage();
            String errorName = ThreadLocalUtil.getErrorName();
            if ( ( StringUtils.isNoneBlank( errorParam) || StringUtils.isNoneEmpty( errorParam ) )
                    && ( StringUtils.isNoneBlank( errorName) || StringUtils.isNoneEmpty( errorName ) ) ) {
                String resultMessage = responseHeader.getResultMessage();
                if ( resultMessage.contains( errorName ) ) {
                    resultMessage = resultMessage.replace( errorName, errorParam );
                }
                // Remove thread local data
                ThreadLocalUtil.removeThreadLocalData( ThreadLocalUtil.ERROR_NAME);
                ThreadLocalUtil.removeThreadLocalData( ThreadLocalUtil.ERROR_MESSAGE);
                responseHeader.setResultMessage(resultMessage);
            }
        } catch ( Exception ex ) {
            // DO NOTHING
        }
        // Register Error Log
        userLogService.registerUserErrorLogInfo(header,responseHeader,e);
        return  responseHeader;
    }
}
