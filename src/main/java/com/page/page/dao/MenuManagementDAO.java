package com.page.page.dao;

import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface  MenuManagementDAO {
    long registerUserMenuInfo( DataUtil inputData ) throws Exception;

    ListDataUtil retriveListUserAccessMenu(DataUtil inputData ) throws Exception;

    ListDataUtil retriveOnlyUseMenuForUserDetail ( DataUtil inputData ) throws Exception;

    ListDataUtil retrieveAllListMenu( DataUtil inputData ) throws Exception;

    ListDataUtil retrieveAllListUserMenu( DataUtil inputData ) throws Exception;

    long updateMenuDetail( DataUtil inputData ) throws Exception;

    DataUtil retrieveMenuDetailInfo ( DataUtil inputData ) throws Exception;
}
