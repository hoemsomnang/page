package com.page.page.service.impl;

import com.page.page.dao.OfficeDAO;
import com.page.page.service.OfficeManagementService;
import com.page.page.util.DataUtil;
import com.page.page.util.DateUtil;
import com.page.page.util.ListDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeManagementServiceImpl implements OfficeManagementService {

    @Autowired
    private OfficeDAO officeDAO;
    @Override
    public ListDataUtil retrieveListOfficeInformation(DataUtil param) throws Exception {

        ListDataUtil officeList = new ListDataUtil();

        try{
            officeList = officeDAO.retrieveListOfficeInfo( param );
        } catch ( Exception e ) {
            throw e;
        }
        return officeList;
    }

    @Override
    public DataUtil retrieveOfficeInformation(DataUtil param) throws Exception {
        return null;
    }

    @Override
    public long registerOfficeInformation(DataUtil param) throws Exception {
       try {

           DataUtil registerParam = new DataUtil();
           registerParam.setString("OfficeName", param.getString("OfficeName"));
           registerParam.setString("officeOwnerName", param.getString("officeOwnerName"));
           registerParam.setString("address", param.getString("address"));
           registerParam.setString("createBy", param.getString("userID"));
           registerParam.setString("createDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
           registerParam.setString("CreateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
           registerParam.setString("updateDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
           registerParam.setString("updateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
           registerParam.setString("updateBY", param.getString("userID"));
           return officeDAO.registerOfficeInformation( registerParam );

       } catch ( Exception e ) {
           throw e;
       }
    }

    @Override
    public long updateOfficeInformation(DataUtil param) throws Exception {

        try {
            DataUtil updateParam = new DataUtil();
            updateParam.setLong("OfficeID", param.getLong("OfficeID"));
            updateParam.setString("OfficeName", param.getString("OfficeName"));
            updateParam.setString("officeOwnerName", param.getString("officeOwnerName"));
            updateParam.setString("address", param.getString("address"));
            updateParam.setString("updateDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            updateParam.setString("updateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            updateParam.setString("updateBY", param.getString("userID"));

            return officeDAO.updateOfficeInformation(updateParam);
        } catch ( Exception e ) {
            throw e;
        }
    }
}
