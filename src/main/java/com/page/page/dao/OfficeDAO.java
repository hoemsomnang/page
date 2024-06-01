package com.page.page.dao;

import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OfficeDAO {

    public ListDataUtil retrieveListOfficeInfo(DataUtil param ) throws Exception;
    public DataUtil retrieveOfficeInformation( DataUtil param ) throws  Exception ;
    public long registerOfficeInformation( DataUtil param ) throws Exception;
    public long updateOfficeInformation( DataUtil param ) throws Exception;


}
