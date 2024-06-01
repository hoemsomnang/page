package com.page.page.dao;

import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PageOwnerDAO {

    public ListDataUtil retrieveListPageOwnerInfo(DataUtil param ) throws Exception;
    public DataUtil retrievePageOwnerInformation( DataUtil param ) throws  Exception ;
    public long registerPageOwnerInformation( DataUtil param ) throws Exception;
    public long updatePageOwnerInformation( DataUtil param ) throws Exception;


}
