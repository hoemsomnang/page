package com.page.page.service;

import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;

public interface OfficeManagementService {

    public ListDataUtil retrieveListOfficeInformation(DataUtil param ) throws Exception;
    public DataUtil retrieveOfficeInformation( DataUtil param ) throws  Exception;
    public long registerOfficeInformation( DataUtil param ) throws Exception;
    public long updateOfficeInformation ( DataUtil param ) throws Exception;
}
