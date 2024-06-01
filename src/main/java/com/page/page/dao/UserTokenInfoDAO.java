package com.page.page.dao;

import com.page.page.util.DataUtil;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenInfoDAO {
    DataUtil retrieveUserTokenInfo(DataUtil param) throws Exception;
    long registerUserTokenInfo (DataUtil param) throws Exception;
    long updateUserTokenInfo(DataUtil param) throws Exception;
    DataUtil retrieveUserTokenInfoByToken( DataUtil param ) throws Exception;

}
