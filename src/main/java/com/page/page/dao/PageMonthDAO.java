package com.page.page.dao;

import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PageMonthDAO {

    public ListDataUtil retrieveListMonth( DataUtil param ) throws Exception;
    public DataUtil retrieveMonthInfo ( DataUtil param ) throws Exception ;

}
