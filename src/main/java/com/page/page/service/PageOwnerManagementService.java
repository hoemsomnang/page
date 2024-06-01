package com.page.page.service;

import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;

public interface PageOwnerManagementService {

    public ListDataUtil retrieveListPageOwnerInformation(DataUtil param ) throws Exception;
    public DataUtil retrievePageOwnerInformation( DataUtil param ) throws  Exception;
    public long registerPageOwnerInformation( DataUtil param ) throws Exception;
    public long updatePageOwnerInformation ( DataUtil param ) throws Exception;
}
