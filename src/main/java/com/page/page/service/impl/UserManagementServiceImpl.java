package com.page.page.service.impl;


import com.page.page.dao.MenuManagementDAO;
import com.page.page.dao.UserInfoDAO;
import com.page.page.service.UserManagementService;
import com.page.page.type.ResponseResultMessage;
import com.page.page.util.DataUtil;
import com.page.page.util.DateUtil;
import com.page.page.util.GeneratePasswordUtil;
import com.page.page.util.ListDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;


@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserInfoDAO userInfoDAO;
    @Autowired
    PlatformTransactionManager txManager;
    @Autowired
    private MenuManagementDAO menuManagementDAO;
    private GeneratePasswordUtil generatePasswordUtil;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public long registerUserInfo(DataUtil param) throws Exception {
        try {

            // Check userID
            DataUtil userInfo = userInfoDAO.retrieveUserInfo( param ) ;
            if ( userInfo != null ) {
                throw new Exception( ResponseResultMessage.USER_NAME_ALREADY_EXISTING.getValue() );
            }


            DataUtil registerParam = new DataUtil();
            registerParam.setString("userID", param.getString("userID"));
            registerParam.setString("fullName", param.getString("fullName"));
            registerParam.setString("userPassword", passwordEncoder.encode(param.getString("password") ));
            registerParam.setString("roles",    param.getString("roles") );
            if ( "USER".equals(  param.getString("roles") )) {
                registerParam.setLong("mainOwnerID", 0L);
            } else {
                registerParam.setLong("mainOwnerID", param.getLong("mainOwnerID"));
            }
            registerParam.setString("createBy", param.getString("createID"));
            registerParam.setString("createDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerParam.setString("CreateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            registerParam.setString("updateDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerParam.setString("updateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            registerParam.setString("updateBY", param.getString("createID"));

            userInfoDAO.registerUserInfo( registerParam );

        } catch ( Exception e ) {
            throw e;
        }
        return 1;
    }

    @Override
    public long updateUserInfo(DataUtil param) throws Exception {
        try {

            // Check userID
            DataUtil userInfo = userInfoDAO.retrieveUserInfo( param ) ;
            if ( userInfo == null ) {
                throw new Exception( ResponseResultMessage.USER_NOT_FOUND.getValue() );
            }

            DataUtil updateParam = new DataUtil();
            updateParam.setString("userID", param.getString("userID"));
            updateParam.setString("fullName", param.getString("fullName"));
            if ( StringUtils.isNotBlank( param.getString("password") ) && StringUtils.isNotEmpty( param.getString("password") ) ) {
                updateParam.setString("userPassword", passwordEncoder.encode(param.getString("password") ));
            }
            updateParam.setString("roles",    param.getString("roles") );
            if ( "USER".equals(  param.getString("roles") )) {
                updateParam.setLong("mainOwnerID", 0L);
            } else {
                updateParam.setLong("mainOwnerID", param.getLong("mainOwnerID"));
            }
            updateParam.setString("updateDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            updateParam.setString("updateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            updateParam.setString("updateBY", param.getString("updateID"));

            userInfoDAO.updateUserInfo( updateParam );

        } catch ( Exception e ) {
            throw e;
        }
        return 1;
    }

    @Override
    public DataUtil retrieveUserLogin(DataUtil param) throws Exception {

        DataUtil outputData 	 = new DataUtil();
        try {

            this.validateUserLoginParam(param);
            generatePasswordUtil = new GeneratePasswordUtil();
            /*******************************
             *  Retrieve User Information
             *******************************/
            DataUtil userInfoParam = new DataUtil();
            userInfoParam.setString("userName", param.getString("userName"));
            DataUtil userInfo = userInfoDAO.retrieveUserInfo( userInfoParam );
            String password      = StringUtils.EMPTY;
            String passwordSH512 = StringUtils.EMPTY;
            if ( userInfo == null ) {
                throw new Exception( ResponseResultMessage.USER_NOT_FOUND.getValue() );
            } else {
                // Validate User Password
                password 			=  param.getString("password");
                String securityKey 	= userInfo.getString( "userName" );
                passwordSH512 		= generatePasswordUtil.generatePassword( password, securityKey );
                // In case incorrect password
                if ( !passwordSH512.equals( userInfo.getString( "password" ) ) ) {
                    throw new Exception( ResponseResultMessage.INVALID_PASSWORD.getValue() );
                } else{
                    outputData.setDataUtil("userInfo", userInfo );
                }
            }
            /*******************************
             *   Retrieve User Menu Info
             *******************************/
            ListDataUtil menuList = menuManagementDAO.retriveListUserAccessMenu( userInfoParam );
            outputData.setListDataUtil("menulist", menuList);

        } catch ( Exception e ) {
            throw e;
        }
        return outputData;
    }

    @Override
    public DataUtil registerUserInformation( DataUtil param) throws Exception {

        try {

            // Check userID
            DataUtil userInfo = userInfoDAO.retrieveUserInfo( param ) ;
            if ( userInfo != null ) {
                throw new Exception( ResponseResultMessage.USER_NAME_ALREADY_EXISTING.getValue() );
            }


            DataUtil registerParam = new DataUtil();
            registerParam.setString("userID", param.getString("userID"));
            registerParam.setString("fullname", param.getString("fullname"));
            registerParam.setString("userPassword", passwordEncoder.encode(param.getString("password") ));
            registerParam.setString("roles",    param.getString("roles") );
            registerParam.setString("createBy", param.getString("createID"));
            registerParam.setString("createDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerParam.setString("CreateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            registerParam.setString("updateDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerParam.setString("updateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            registerParam.setString("updateBY", param.getString("createID"));

            userInfoDAO.registerUserInfo( registerParam );

        } catch ( Exception e ) {
            throw e;
        }
        return null;
    }

    private void validateUserLoginParam( DataUtil param ) throws Exception {
        if (StringUtils.isBlank( param.getString("userName")) ) {
            throw new Exception(ResponseResultMessage.USER_NAME_EMPTY.getValue() );
        } else if ( StringUtils.isBlank( param.getString("password" ) ) ) {
            throw new Exception( ResponseResultMessage.PASSWORD_EMPTY.getValue() );
        } else if ( StringUtils.isBlank( param.getString("userType" ) ) ) {
            throw new Exception( ResponseResultMessage.USER_TYPE_EMPTY.getValue() );
        }
    }

    private void validateRegisterUserInfo( DataUtil param ) throws Exception {

        if (StringUtils.isBlank( param.getString("userName")) ) {
            throw new Exception(ResponseResultMessage.USER_NAME_EMPTY.getValue() );
        } else if ( StringUtils.isBlank( param.getString("password" ) ) ) {
            throw new Exception( ResponseResultMessage.PASSWORD_EMPTY.getValue() );
        } else if ( StringUtils.isBlank( param.getString("userType" ) ) ) {
            throw new Exception( ResponseResultMessage.USER_TYPE_EMPTY.getValue() );
        } else if ( StringUtils.isBlank(  param.getString("masterUserName")) ) {
            throw new Exception(ResponseResultMessage.USER_NAME_EMPTY.getValue() );
        }
    }
}
