package com.page.page.dao;

import com.page.page.util.DataUtil;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLogInfoDAO {

    long registerUserErrorLogInfo(DataUtil param ) throws Exception;
}
