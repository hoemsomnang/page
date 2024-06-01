package com.page.page.controller.view;

import com.page.page.dao.MainOwnerDAO;
import com.page.page.dao.UserInfoDAO;
import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/view/user")
public class ViewUserManagementController {

    @Autowired
    private MainOwnerDAO mainOwnerDAO;
    @Autowired
    private UserInfoDAO userInfoDAO;
    @RequestMapping({"/user-list", ""})
    public String userIndex(Authentication authentication,  Model model ) throws Exception {
        try {

            // Set userID
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            DataUtil param = new DataUtil();
            ListDataUtil listMainOwner = mainOwnerDAO.retrieveListMainOwnerInfo( param );
            param.setString("userID", userDetails.getUsername() );

            List<DataUtil> list = userInfoDAO.retrieveListUserInfoByAdmin( param );
            model.addAttribute("list", list );
            model.addAttribute("listMainOwner", listMainOwner );
            // return page
            return "page/user-list";

        } catch ( Exception e ) {
            throw e;
        }
    }

    @RequestMapping({"/test", ""})
    public String test( Model model ) throws Exception {

        DataUtil param = new DataUtil();
        ListDataUtil list = new ListDataUtil();
        model.addAttribute("pageOwnerList", list );
        // return page
        return "page/menu-test";
    }
}
