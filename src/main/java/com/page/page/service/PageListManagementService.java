package com.page.page.service;

import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;

public interface PageListManagementService {

    public ListDataUtil retrieveListPageInformation( DataUtil param ) throws Exception ;
    public DataUtil retrievePageInformation ( DataUtil param ) throws Exception;
    public long registerPageInformation( DataUtil param ) throws Exception ;
    public long updatePageInformation( DataUtil param ) throws  Exception;
}
