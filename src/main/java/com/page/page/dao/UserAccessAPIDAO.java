package com.page.page.dao;

import com.page.page.util.DataUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAccessAPIDAO {
    List<DataUtil>  retrieveListUserAccessAPIByUserName(String userName );
}
