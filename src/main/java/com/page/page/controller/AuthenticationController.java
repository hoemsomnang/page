package com.page.page.controller;

import com.page.page.service.CustomUserDetailService;
import com.page.page.service.ResponseResultMessageService;
import com.page.page.type.ResponseResultMessage;
import com.page.page.util.ListDataUtil;
import com.page.page.util.ResponseData;
import com.page.page.util.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    ResponseResultMessageService responseResultMessageService;

    @GetMapping("/")
    public String tt(){
        return "hello";
    }
    @GetMapping("/login")
    public String login() {
       return "authentication/login";
    }

    @GetMapping("/user-access")
    public ResponseEntity<ResponseData> loginPage(@RequestParam(name = "userID") String userID, @RequestParam(name = "password") String password ) {

        ListDataUtil body = new ListDataUtil();
        ResponseHeader header = new ResponseHeader("Y", ResponseResultMessage.SUCCESS.getValue(), ResponseResultMessage.SUCCESS.getDescription() );

        try {
           // Assuming you have a service to validate and load the user by username
           UserDetails userDetails = customUserDetailService.loadUserByUsername( userID );

           UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( userID, password );
           Authentication authentication = new UsernamePasswordAuthenticationToken(
                   userDetails,
                   null,
                   userDetails.getAuthorities()
           );
           SecurityContext context = SecurityContextHolder.createEmptyContext();
           context.setAuthentication(authentication);
           SecurityContextHolder.setContext(context);

       } catch ( Exception e ){
            body = new ListDataUtil();
            header = new ResponseHeader("N", ResponseResultMessage.GENERAL_SYSTEM_ERROR.getValue(), ResponseResultMessage.GENERAL_SYSTEM_ERROR.getDescription() );
       }


        ResponseData<ListDataUtil> responseData = new ResponseData<>(header, body);
        return  ResponseEntity.ok( responseData );
    }
}
