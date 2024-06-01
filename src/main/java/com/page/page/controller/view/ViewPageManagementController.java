package com.page.page.controller.view;

import com.page.page.dao.*;
import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/page")
public class ViewPageManagementController {

    @Autowired
    private PageOwnerDAO pageOwnerDAO;
    @Autowired
    private MainOwnerDAO mainOwnerDAO;
    @Autowired
    private PageMonthDAO pageMonthDAO;
    @Autowired
    private PageListDAO pageListDAO;
    @Autowired
    private UserInfoDAO userInfoDAO;
    @RequestMapping(value = {"/page-list", ""})
    public String pageList(Authentication authentication, Model model ) throws Exception {

        DataUtil param = new DataUtil();
        ListDataUtil listPageOwner = pageOwnerDAO.retrieveListPageOwnerInfo( param );
        ListDataUtil listMainOwner = mainOwnerDAO.retrieveListMainOwnerInfo( param );
        ListDataUtil listPageMonth = pageMonthDAO.retrieveListMonth( param );
        /*********************************
         * Retrieve List Page Base On User
         *********************************/
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userID = userDetails.getUsername();
        // Retrieve User Info
        DataUtil userParam = new DataUtil();
        userParam.setString("userID", userID );
        DataUtil userInfo = userInfoDAO.retrieveUserInfo( userParam );

        ListDataUtil pageList = new ListDataUtil();
        ListDataUtil listJanuary = new ListDataUtil();
        ListDataUtil listFebruary = new ListDataUtil();
        ListDataUtil listMarch = new ListDataUtil();
        ListDataUtil listApril = new ListDataUtil();
        ListDataUtil listMay = new ListDataUtil();
        ListDataUtil listJune = new ListDataUtil();
        ListDataUtil listJuly = new ListDataUtil();
        ListDataUtil listAugust = new ListDataUtil();
        ListDataUtil listSeptember = new ListDataUtil();
        ListDataUtil listOctober = new ListDataUtil();
        ListDataUtil listNovember = new ListDataUtil();
        ListDataUtil listDecember = new ListDataUtil();
        DataUtil pageParam = new DataUtil();
        pageParam.setString("userID", userInfo.getString("userID"));
        // ADMIN & USER
        if ( "ADMIN".equals( userInfo.getString("roles")) ) {
            // January
            pageParam.setLong("monthID" , 1L);
            listJanuary = pageListDAO.retrieveListPageInformationByAdmin( pageParam );
            // February
            pageParam.setLong("monthID" , 2L);
            listFebruary = pageListDAO.retrieveListPageInformationByAdmin( pageParam );
            // March
            pageParam.setLong("monthID" , 3L);
            listMarch = pageListDAO.retrieveListPageInformationByAdmin( pageParam );
            // April
            pageParam.setLong("monthID" , 4L);
            listApril = pageListDAO.retrieveListPageInformationByAdmin( pageParam );
            // May
            pageParam.setLong("monthID" , 5L);
            listMay = pageListDAO.retrieveListPageInformationByAdmin( pageParam );
            // June
            pageParam.setLong("monthID" , 6L);
            listJune = pageListDAO.retrieveListPageInformationByAdmin( pageParam );
            // July
            pageParam.setLong("monthID" , 7L);
            listJuly = pageListDAO.retrieveListPageInformationByAdmin( pageParam );
            // August
            pageParam.setLong("monthID" , 8L);
            listAugust = pageListDAO.retrieveListPageInformationByAdmin( pageParam );
            // September
            pageParam.setLong("monthID" , 9L);
            listSeptember = pageListDAO.retrieveListPageInformationByAdmin( pageParam );
            // October
            pageParam.setLong("monthID" , 10L);
            listOctober = pageListDAO.retrieveListPageInformationByAdmin( pageParam );
            // November
            pageParam.setLong("monthID" , 11L);
            listNovember = pageListDAO.retrieveListPageInformationByAdmin( pageParam );
            // December
            pageParam.setLong("monthID" , 12L);
            listDecember = pageListDAO.retrieveListPageInformationByAdmin( pageParam );
        } else {
            // January
            pageParam.setLong("monthID" , 1L);
            listJanuary = pageListDAO.retrieveListPageInformationByUser( pageParam );
            // February
            pageParam.setLong("monthID" , 2L);
            listFebruary = pageListDAO.retrieveListPageInformationByUser( pageParam );
            // March
            pageParam.setLong("monthID" , 3L);
            listMarch = pageListDAO.retrieveListPageInformationByUser( pageParam );
            // April
            pageParam.setLong("monthID" , 4L);
            listApril = pageListDAO.retrieveListPageInformationByUser( pageParam );
            // May
            pageParam.setLong("monthID" , 5L);
            listMay = pageListDAO.retrieveListPageInformationByUser( pageParam );
            // June
            pageParam.setLong("monthID" , 6L);
            listJune = pageListDAO.retrieveListPageInformationByUser( pageParam );
            // July
            pageParam.setLong("monthID" , 7L);
            listJuly = pageListDAO.retrieveListPageInformationByUser( pageParam );
            // August
            pageParam.setLong("monthID" , 8L);
            listAugust = pageListDAO.retrieveListPageInformationByUser( pageParam );
            // September
            pageParam.setLong("monthID" , 9L);
            listSeptember = pageListDAO.retrieveListPageInformationByUser( pageParam );
            // October
            pageParam.setLong("monthID" , 10L);
            listOctober = pageListDAO.retrieveListPageInformationByUser( pageParam );
            // November
            pageParam.setLong("monthID" , 11L);
            listNovember = pageListDAO.retrieveListPageInformationByUser( pageParam );
            // December
            pageParam.setLong("monthID" , 12L);
            listDecember = pageListDAO.retrieveListPageInformationByUser( pageParam );
        }

        model.addAttribute("listPageOwner", listPageOwner );
        model.addAttribute("listMainOwner", listMainOwner );
        model.addAttribute("listPageMonth", listPageMonth );
        model.addAttribute("pageList", pageList );
        model.addAttribute("listJanuary", listJanuary );
        model.addAttribute("listFebruary", listFebruary );
        model.addAttribute("listMarch", listMarch );
        model.addAttribute("listApril", listApril );
        model.addAttribute("listMay", listMay );
        model.addAttribute("listJune", listJune );
        model.addAttribute("listJuly", listJuly );
        model.addAttribute("listAugust", listAugust );
        model.addAttribute("listSeptember", listSeptember );
        model.addAttribute("listOctober", listOctober );
        model.addAttribute("listNovember", listNovember );
        model.addAttribute("listDecember", listDecember );
        // return page
        return "page/page-list";
    }

}
