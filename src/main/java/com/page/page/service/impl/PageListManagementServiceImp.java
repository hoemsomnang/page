package com.page.page.service.impl;

import com.page.page.dao.PageListDAO;
import com.page.page.dao.PageMonthDAO;
import com.page.page.service.PageListManagementService;
import com.page.page.service.TelegramNotificationService;
import com.page.page.type.ResponseResultMessage;
import com.page.page.util.DataUtil;
import com.page.page.util.DateUtil;
import com.page.page.util.ListDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Service
public class PageListManagementServiceImp implements PageListManagementService {


    @Autowired
    private PageListDAO pageListDAO;
    @Autowired
    private PageMonthDAO pageMonthDAO;
   @Autowired
   private TelegramNotificationService telegramNotificationService;

    @Override
    public ListDataUtil retrieveListPageInformation(DataUtil param) throws Exception {
        return null;
    }

    @Override
    public DataUtil retrievePageInformation(DataUtil param) throws Exception {
        return null;
    }

    @Override
    public long registerPageInformation(DataUtil param) throws Exception {

        try {

            DataUtil registerParam = new DataUtil();

            /***********************************
             *  Check duplicate Page Information
             ***********************************/
            DataUtil monthParam  = new DataUtil();
            monthParam.setLong("monthID", param.getLong("pageMonthID") );
            DataUtil monthInfo = pageMonthDAO.retrieveMonthInfo( monthParam );
            registerParam.setLong("monthID", param.getLong("pageMonthID"));

            DataUtil pageParam = new DataUtil();
            pageParam.setString("pageID", param.getString("pageID") );
            pageParam.setLong("monthID", param.getLong("pageMonthID") );
            pageParam.setString("years", monthInfo.getString("pageYear") );
            DataUtil pageInfo = pageListDAO.retrievePageInformation( pageParam );
            if ( pageInfo != null ) {
                throw new Exception(ResponseResultMessage.PAGE_ALREADY_EXISTING.getValue() );
            }

            registerParam.setString("pageID", param.getString("pageID"));
            registerParam.setLong("officeID", param.getLong("officeID"));
            registerParam.setLong("pageOwnerID", param.getLong("pageOwnerID"));
            registerParam.setLong("mainOwnerID", param.getLong("mainOwnerID"));
            // Page Month Information
            registerParam.setString("years", monthInfo.getString("pageYear"));
            registerParam.setString("monthName", monthInfo.getString("pageMonth"));
            registerParam.setString("pageName", param.getString("pageName"));
            // override amount in case client side send value empty
            if (StringUtils.isEmpty( param.getString("pageEarn") ) ) {
                param.setBigDecimal("pageEarn", BigDecimal.ZERO );
            }
            if (StringUtils.isEmpty( param.getString("ownerPercent") ) ) {
                param.setLong("ownerPercent", 0L );
            }
            if (StringUtils.isEmpty( param.getString("officePercent") ) ) {
                param.setLong("officePercent", 0L );
            }

            BigDecimal officePercent = param.getBigDecimal("officePercent");
            BigDecimal ownerPercent = param.getBigDecimal("ownerPercent");
            BigDecimal pageEarn = param.getBigDecimal("pageEarn");
            BigDecimal officeAmount = BigDecimal.ZERO;
            BigDecimal ownerAmount = BigDecimal.ZERO;
            if (  pageEarn.compareTo(BigDecimal.ZERO) > 0
                 && officePercent.compareTo(BigDecimal.ZERO) > 0 &&  ownerPercent.compareTo(BigDecimal.ZERO) > 0  ) {
                // Office
                officePercent = officePercent.divide( BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP ) ;
                officeAmount = pageEarn.multiply( officePercent ).setScale(2, RoundingMode.HALF_UP);
                // Owner
                ownerPercent = ownerPercent.divide( BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP ) ;;
                ownerAmount = pageEarn.multiply( ownerPercent ).setScale(2, RoundingMode.HALF_UP);
            }

            registerParam.setBigDecimal("pageEarn",pageEarn);
            registerParam.setLong("officePercent", param.getLong("officePercent") );
            registerParam.setBigDecimal("officeAmount", officeAmount);
            registerParam.setLong("ownerPercent", param.getLong("ownerPercent") );
            registerParam.setBigDecimal("ownerAmount", ownerAmount);
            registerParam.setString("pageStatus", param.getString("pageStatus"));
            if ( StringUtils.isNotEmpty(param.getString("pageLink")) && StringUtils.isNotBlank(param.getString("pageLink")) ) {
                registerParam.setString("pageLink", param.getString("pageLink"));
            } else {
                registerParam.setString("pageLink", "");
            }
            registerParam.setString("createBy", param.getString("userID"));
            registerParam.setString("createDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerParam.setString("CreateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            registerParam.setString("updateDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerParam.setString("updateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            registerParam.setString("updateBY", param.getString("userID"));

            // Register page information
            pageListDAO.registerPageInformation( registerParam );

            /*************************************************
             * Process send notification to telegram for owner
             *************************************************/
            DataUtil telegramParam = new DataUtil();
            telegramParam.setString("botToken", "7110183929:AAGfwipdWWs_eMKtMZYKttWk8bqcdFwxFfI");
            telegramParam.setString("groupID", "@vandygroup01");
            DecimalFormat moneyFormat = new DecimalFormat("$0.00");
            String page_earn = moneyFormat.format( pageEarn );
            String office_amount = moneyFormat.format( officeAmount );
            String owner_amount = moneyFormat.format( ownerAmount );
            String message = String.format("New page has been registered\nPage Name: %s\nPage Earn: %s\nOffice Amount: %s\nOffice Percent: %s\nOwner Amount: %s\nOwner Percent: %s\nMonth: %s\nRegister By: %s"
                    , param.getString("pageName")
                    , page_earn
                    , office_amount
                    , String.valueOf(  param.getLong("officePercent") ).concat("%")
                    , owner_amount
                    , String.valueOf(  param.getLong("ownerPercent") ).concat("%")
                    , monthInfo.getString("pageMonth").concat( "-" + monthInfo.getString("pageYear") )
                    , param.getString("userID") );

            telegramParam.setString("message", message);
            telegramNotificationService.sendTelegramNotificationToAdmin( telegramParam );
            return 1;
        } catch ( Exception e ) {
            throw e;
        }

    }

    @Override
    public long updatePageInformation( DataUtil param ) throws Exception {

        try {

            DataUtil updateParam = new DataUtil();

            /***********************************
             *  Check duplicate Page Information
             ***********************************/
            DataUtil pageParam = new DataUtil();
            pageParam.setString("pageID", param.getString("pageID"));
            pageParam.setLong("monthID", param.getLong("monthID"));
            pageParam.setString("years", param.getString("years"));
            DataUtil pageInfo = pageListDAO.retrievePageInformation( pageParam );
            if ( pageInfo == null ) {
                throw new Exception(ResponseResultMessage.PAGE_NOT_FOUND.getValue() );
            }

            updateParam.setString("pageID", param.getString("pageID"));
            updateParam.setLong("officeID", param.getLong("officeID"));
            updateParam.setLong("pageOwnerID", param.getLong("pageOwnerID"));
            updateParam.setLong("mainOwnerID", param.getLong("mainOwnerID"));
            // Page Month Information
            DataUtil monthParam  = new DataUtil();
            monthParam.setLong("monthID", param.getLong("pageMonthID") );
            DataUtil monthInfo = pageMonthDAO.retrieveMonthInfo( monthParam );
            // Old Info
            updateParam.setLong("monthID", param.getLong("monthID"));
            updateParam.setString("years", param.getString("years"));
            // new Info
            updateParam.setLong("monthIDUpdate", param.getLong("pageMonthID"));
            updateParam.setString("yearsUpdate", monthInfo.getString("pageYear"));
            updateParam.setString("monthName", monthInfo.getString("pageMonth"));
            updateParam.setString("pageName", param.getString("pageName"));
            // override amount in case client side send value empty
            if (StringUtils.isEmpty( param.getString("pageEarn") ) ) {
                param.setBigDecimal("pageEarn", BigDecimal.ZERO );
            }
            if (StringUtils.isEmpty( param.getString("ownerPercent") ) ) {
                param.setLong("ownerPercent", 0L );
            }
            if (StringUtils.isEmpty( param.getString("officePercent") ) ) {
                param.setLong("officePercent", 0L );
            }

            BigDecimal officePercent = param.getBigDecimal("officePercent");
            BigDecimal ownerPercent = param.getBigDecimal("ownerPercent");
            BigDecimal pageEarn = param.getBigDecimal("pageEarn");
            BigDecimal officeAmount = BigDecimal.ZERO;
            BigDecimal ownerAmount = BigDecimal.ZERO;
            if (  pageEarn.compareTo(BigDecimal.ZERO) > 0
                    && officePercent.compareTo(BigDecimal.ZERO) > 0 &&  ownerPercent.compareTo(BigDecimal.ZERO) > 0  ) {
                // Office
                officePercent = officePercent.divide( BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP ) ;
                officeAmount = pageEarn.multiply( officePercent ).setScale(2, RoundingMode.HALF_UP);
                // Owner
                ownerPercent = ownerPercent.divide( BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP ) ;;
                ownerAmount = pageEarn.multiply( ownerPercent ).setScale(2, RoundingMode.HALF_UP);
            }

            updateParam.setBigDecimal("pageEarn",pageEarn);
            updateParam.setLong("officePercent", param.getLong("officePercent") );
            updateParam.setBigDecimal("officeAmount", officeAmount);
            updateParam.setLong("ownerPercent", param.getLong("ownerPercent") );
            updateParam.setBigDecimal("ownerAmount", ownerAmount);
            updateParam.setString("pageStatus", param.getString("pageStatus"));
            if ( StringUtils.isNotEmpty(param.getString("pageLink")) && StringUtils.isNotBlank(param.getString("pageLink")) ) {
                updateParam.setString("pageLink", param.getString("pageLink"));
            } else {
                updateParam.setString("pageLink", "");
            }
            updateParam.setString("updateDate", DateUtil.getCurrentFormatDate(DateUtil.DATE));
            updateParam.setString("updateTime", DateUtil.getCurrentFormatDate(DateUtil.FORMAT_TIME));
            updateParam.setString("updateBY", param.getString("userID"));

            // Register page information
            pageListDAO.updatePageInformation( updateParam );

            /*************************************************
             * Process send notification to telegram for owner
             *************************************************/
            DataUtil telegramParam = new DataUtil();
            telegramParam.setString("botToken", "7110183929:AAGfwipdWWs_eMKtMZYKttWk8bqcdFwxFfI");
            telegramParam.setString("groupID", "@vandygroup01");
            DecimalFormat moneyFormat = new DecimalFormat("$0.00");
            String page_earn = moneyFormat.format( pageEarn );
            String office_amount = moneyFormat.format( officeAmount );
            String owner_amount = moneyFormat.format( ownerAmount );
            String message = String.format("Page has been updated\nPage Name: %s\nPage Earn: %s\nOffice Amount: %s\nOffice Percent: %s\nOwner Amount: %s\nOwner Percent: %s\nMonth: %s\nUpdated By: %s"
                    , param.getString("pageName")
                    , page_earn
                    , office_amount
                    , String.valueOf(  param.getLong("officePercent") ).concat("%")
                    , owner_amount
                    , String.valueOf(  param.getLong("ownerPercent") ).concat("%")
                    , monthInfo.getString("pageMonth").concat( "-" + monthInfo.getString("pageYear") )
                    , param.getString("userID") );

            telegramParam.setString("message", message);
            telegramNotificationService.sendTelegramNotificationToAdmin( telegramParam );
            return 1;

        } catch ( Exception e ) {
             throw e;
        }
    }
}
