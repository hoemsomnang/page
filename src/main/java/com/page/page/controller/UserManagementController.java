package com.page.page.controller;

import com.page.page.service.ResponseResultMessageService;
import com.page.page.service.UserManagementService;
import com.page.page.type.ResponseResultMessage;
import com.page.page.util.DataUtil;
import com.page.page.util.RequestData;
import com.page.page.util.ResponseData;
import com.page.page.util.ResponseHeader;
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
@RequestMapping("/api/v1/user")
public class UserManagementController {
    @Autowired
    ResponseResultMessageService responseResultMessageService;
    @Autowired
    private UserManagementService userManagementService;
    @PostMapping("/login")
    public ResponseEntity<ResponseData> retrieveUserForLogin(@RequestBody RequestData<DataUtil> requestData ) throws Exception {

        DataUtil body = new DataUtil();
        ResponseHeader header = new ResponseHeader("Y", ResponseResultMessage.SUCCESS.getValue(), ResponseResultMessage.SUCCESS.getDescription() );

        try {
            body = userManagementService.retrieveUserLogin( requestData.getBody() );
        } catch ( Exception e ) {
            body = new DataUtil();
            header = responseResultMessageService.resultLanguageMessage(requestData.getHeader(),e);
        }
        ResponseData<DataUtil> responseData = new ResponseData<>(header, body);
        return  ResponseEntity.ok( responseData );
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseData> registerUserInfo(Authentication authentication, @RequestBody RequestData<DataUtil> requestData ) throws Exception {

        DataUtil body = new DataUtil();
        ResponseHeader header = new ResponseHeader("Y", ResponseResultMessage.SUCCESS.getValue(), ResponseResultMessage.SUCCESS.getDescription() );

        try {

            // Set userID
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            requestData.getBody().setString("createID", userDetails.getUsername() );
            userManagementService.registerUserInfo( requestData.getBody() );

        } catch ( Exception e ) {
            body = new DataUtil();
            header = responseResultMessageService.resultLanguageMessage(requestData.getHeader(),e);
        }
        ResponseData<DataUtil> responseData = new ResponseData<>(header, body);
        return  ResponseEntity.ok( responseData );
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseData> updateUserInfo(Authentication authentication, @RequestBody RequestData<DataUtil> requestData ) throws Exception {

        DataUtil body = new DataUtil();
        ResponseHeader header = new ResponseHeader("Y", ResponseResultMessage.SUCCESS.getValue(), ResponseResultMessage.SUCCESS.getDescription() );

        try {

            // Set userID
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            requestData.getBody().setString("updateID", userDetails.getUsername() );
            userManagementService.updateUserInfo( requestData.getBody() );

        } catch ( Exception e ) {
            body = new DataUtil();
            header = responseResultMessageService.resultLanguageMessage(requestData.getHeader(),e);
        }
        ResponseData<DataUtil> responseData = new ResponseData<>(header, body);
        return  ResponseEntity.ok( responseData );
    }
}
