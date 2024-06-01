package com.page.page.dao;

import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MainOwnerDAO {

    public ListDataUtil retrieveListMainOwnerInfo(DataUtil param ) throws Exception;
    public DataUtil retrieveMainOwnerInformation( DataUtil param ) throws  Exception ;
    public long registerMainOwnerInformation( DataUtil param ) throws Exception;
    public long updateMainOwnerInformation( DataUtil param ) throws Exception;


}
