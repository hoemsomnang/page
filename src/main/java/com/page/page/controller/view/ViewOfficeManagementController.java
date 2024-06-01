package com.page.page.controller.view;


import com.page.page.service.OfficeManagementService;
import com.page.page.util.DataUtil;
import com.page.page.util.ListDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/office")
public class ViewOfficeManagementController {

    @Autowired
    private OfficeManagementService officeManagementService;
    @RequestMapping({"/office-list", ""})
    public String userIndex( Model model ) throws Exception {

        DataUtil param = new DataUtil();
        ListDataUtil list = officeManagementService.retrieveListOfficeInformation( param );
        model.addAttribute("officeList", list );
        // return page
        return "page/office-list";
    }
}
