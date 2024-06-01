package com.page.page.service.impl;

import com.page.page.dao.PageOwnerDAO;
import com.page.page.service.PageOwnerManagementService;
import com.page.page.util.DataUtil;
import com.page.page.util.DateUtil;
import com.page.page.util.ListDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageOwnerManagementServiceImpl implements PageOwnerManagementService {

    @Autowired
    private PageOwnerDAO PageOwnerDAO;

    @Override
    public ListDataUtil retrieveListPageOwnerInformation(DataUtil param) throws Exception {
        return PageOwnerDAO.retrieveListPageOwnerInfo(param);
    }

    @Override
    public DataUtil retrievePageOwnerInformation(DataUtil param) throws Exception {
        return PageOwnerDAO.retrievePageOwnerInformation(param);
    }

    @Override
    public long registerPageOwnerInformation(DataUtil param) throws Exception {

        try {
            DataUtil registerParam = new DataUtil();
            registerParam.setString("pageOwnerName", param.getString("pageOwnerName"));
            registerParam.setString("phone", param.getString("phone"));
            registerParam.setString("address", param.getString("address"));
            registerParam.setString("createBy", param.getString("userID"));
            registerParam.setString("createDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerParam.setString("CreateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            registerParam.setString("updateDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerParam.setString("updateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            registerParam.setString("updateBY", param.getString("userID"));
            return PageOwnerDAO.registerPageOwnerInformation( registerParam );

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public long updatePageOwnerInformation(DataUtil param) throws Exception {
       try {
           DataUtil updateParam = new DataUtil();
           updateParam.setLong("pageOwnerID", param.getLong("pageOwnerID"));
           updateParam.setString("pageOwnerName", param.getString("pageOwnerName"));
           updateParam.setString("phone", param.getString("phone"));
           updateParam.setString("address", param.getString("address"));
           updateParam.setString("updateDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
           updateParam.setString("updateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
           updateParam.setString("updateBY", param.getString("userID"));
           return PageOwnerDAO.updatePageOwnerInformation( updateParam );
       } catch ( Exception e ) {
           throw e;
       }
    }
}
