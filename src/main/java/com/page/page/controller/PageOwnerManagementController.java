package com.page.page.controller;



import com.page.page.service.PageOwnerManagementService;
import com.page.page.service.ResponseResultMessageService;
import com.page.page.type.ResponseResultMessage;
import com.page.page.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/page-owner")
public class PageOwnerManagementController {

    @Autowired
    ResponseResultMessageService responseResultMessageService;
    @Autowired
    private PageOwnerManagementService pageOwnerManagementService;
    @PostMapping("/update")
    public ResponseEntity<ResponseData> updatePageOwnerInformation(Authentication authentication, @RequestBody RequestData<DataUtil> requestData ) throws Exception {

        ListDataUtil body = new ListDataUtil();
        ResponseHeader header = new ResponseHeader("Y", ResponseResultMessage.SUCCESS.getValue(), ResponseResultMessage.SUCCESS.getDescription() );

        try {
            // Set userID
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            requestData.getBody().setString("userID", userDetails.getUsername() );
            pageOwnerManagementService.updatePageOwnerInformation(requestData.getBody());

        } catch ( Exception e ) {
            body = new ListDataUtil();
            header = responseResultMessageService.resultLanguageMessage(requestData.getHeader(),e);
        }
        ResponseData<ListDataUtil> responseData = new ResponseData<>(header, body);
        return  ResponseEntity.ok( responseData );
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseData> registrerOfficeInfo( Authentication authentication, @RequestBody RequestData<DataUtil> requestData ) throws Exception {

        ListDataUtil body = new ListDataUtil();
        ResponseHeader header = new ResponseHeader("Y", ResponseResultMessage.SUCCESS.getValue(), ResponseResultMessage.SUCCESS.getDescription() );

        try {
            // Set userID
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            requestData.getBody().setString("userID", userDetails.getUsername() );
            pageOwnerManagementService.registerPageOwnerInformation(requestData.getBody());

        } catch ( Exception e ) {
            body = new ListDataUtil();
            header = responseResultMessageService.resultLanguageMessage(requestData.getHeader(),e);
        }
        ResponseData<ListDataUtil> responseData = new ResponseData<>(header, body);
        return  ResponseEntity.ok( responseData );
    }
}
