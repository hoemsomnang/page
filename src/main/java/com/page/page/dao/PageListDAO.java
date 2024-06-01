package com.page.page.dao;

import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PageListDAO {



    public ListDataUtil retrieveListPageInformationByAdmin( DataUtil param ) throws Exception ;
    public ListDataUtil retrieveListPageInformationByUser( DataUtil param ) throws Exception ;
    public DataUtil retrievePageInformation ( DataUtil param ) throws Exception;
    public long registerPageInformation( DataUtil param ) throws Exception ;
    public long updatePageInformation( DataUtil param ) throws  Exception;


}
