package com.page.page.service.impl;

import com.page.page.dao.MainOwnerDAO;
import com.page.page.service.MainOwnerManagementService;
import com.page.page.util.DataUtil;
import com.page.page.util.DateUtil;
import com.page.page.util.ListDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainOwnerManagementServiceImpl implements MainOwnerManagementService {

    @Autowired
    private MainOwnerDAO mainOwnerDAO;

    @Override
    public ListDataUtil retrieveListMainOwnerInformation(DataUtil param) throws Exception {
        return mainOwnerDAO.retrieveListMainOwnerInfo(param);
    }

    @Override
    public DataUtil retrieveMainOwnerInformation(DataUtil param) throws Exception {
        return mainOwnerDAO.retrieveMainOwnerInformation(param);
    }

    @Override
    public long registerMainOwnerInformation(DataUtil param) throws Exception {

        try {
            DataUtil registerParam = new DataUtil();
            registerParam.setString("mainOwnerName", param.getString("mainOwnerName"));
            registerParam.setString("address", param.getString("address"));
            registerParam.setString("createBy", param.getString("userID"));
            registerParam.setString("createDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerParam.setString("CreateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            registerParam.setString("updateDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerParam.setString("updateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            registerParam.setString("updateBY", param.getString("userID"));
            return mainOwnerDAO.registerMainOwnerInformation( registerParam );

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public long updateMainOwnerInformation(DataUtil param) throws Exception {
       try {
           DataUtil updateParam = new DataUtil();
           updateParam.setLong("mainOwnerID", param.getLong("mainOwnerID"));
           updateParam.setString("mainOwnerName", param.getString("mainOwnerName"));
           updateParam.setString("address", param.getString("address"));
           updateParam.setString("updateDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
           updateParam.setString("updateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
           updateParam.setString("updateBY", param.getString("userID"));
           return mainOwnerDAO.updateMainOwnerInformation( updateParam );
       } catch ( Exception e ) {
           throw e;
       }
    }
}
