package com.page.page.controller.view;


import com.page.page.dao.MainOwnerDAO;
import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/main-owner")
public class ViewMainOwnerManagementController {

    @Autowired
    private MainOwnerDAO mainOwnerDAO;
    @RequestMapping({"/main-owner-list", ""})
    public String userIndex( Model model ) throws Exception {

        DataUtil param = new DataUtil();
        ListDataUtil list = mainOwnerDAO.retrieveListMainOwnerInfo( param );
        model.addAttribute("listData", list );
        // return page
        return "page/mainowner-list";
    }
}
