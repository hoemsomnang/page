package com.page.page.service;

import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;

public interface MainOwnerManagementService {

    public ListDataUtil retrieveListMainOwnerInformation(DataUtil param ) throws Exception;
    public DataUtil retrieveMainOwnerInformation( DataUtil param ) throws  Exception;
    public long registerMainOwnerInformation( DataUtil param ) throws Exception;
    public long updateMainOwnerInformation ( DataUtil param ) throws Exception;
}
