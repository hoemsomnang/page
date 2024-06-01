package com.page.page.service;

import com.page.page.util.DataUtil;

public interface UserManagementService {

    long registerUserInfo( DataUtil param ) throws Exception;
    long updateUserInfo(DataUtil param ) throws Exception;
    DataUtil retrieveUserLogin( DataUtil param ) throws Exception;
    DataUtil registerUserInformation( DataUtil param ) throws Exception;
}
