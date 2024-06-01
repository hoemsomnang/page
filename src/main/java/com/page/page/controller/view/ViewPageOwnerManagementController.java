package com.page.page.controller.view;


import com.page.page.service.PageOwnerManagementService;
import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/page-owner")
public class ViewPageOwnerManagementController {

    @Autowired
    private PageOwnerManagementService  pageOwnerManagementService;
    @RequestMapping({"/page-owner-list", ""})
    public String userIndex( Model model ) throws Exception {

        DataUtil param = new DataUtil();
        ListDataUtil list = pageOwnerManagementService.retrieveListPageOwnerInformation(param);
        model.addAttribute("listData", list );
        // return page
        return "page/pageowner-list";
    }
}
