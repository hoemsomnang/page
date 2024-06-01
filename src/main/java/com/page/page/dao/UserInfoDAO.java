package com.page.page.dao;

import com.page.page.util.DataUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserInfoDAO {

    long registerUserInfo(DataUtil param ) throws Exception;
    long updateUserInfo(DataUtil param ) throws Exception;
    DataUtil retrieveUserInfo( DataUtil param ) throws  Exception;
    DataUtil retrieveUserInfoByUserNameForUpdate( DataUtil param ) throws Exception;
    List<DataUtil> retrieveListUserInfo( String status ) throws Exception;

    List<DataUtil> retrieveListUserInfoByAdmin( DataUtil param ) throws Exception;
}
