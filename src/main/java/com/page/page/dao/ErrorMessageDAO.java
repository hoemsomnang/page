package com.page.page.dao;

import com.page.page.util.DataUtil;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ErrorMessageDAO {

    public DataUtil retrieveErrorMessageInfo(DataUtil param ) ;
}
