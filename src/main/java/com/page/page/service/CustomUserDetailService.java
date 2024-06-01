package com.page.page.service;

import com.page.page.dao.UserInfoDAO;
import com.page.page.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService   {

    @Autowired
    UserInfoDAO userInfoDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DataUtil param = new DataUtil();
        param.setString("userID", username );
        DataUtil userInfo = null;
        try {
            userInfo = userInfoDAO.retrieveUserInfo( param );
        } catch (Exception e) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        if ( userInfo == null ) {
          /*  log.warn("user not found: {}", username);*/
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return User.withUsername(username).password(userInfo.getString("userPassword")).roles( userInfo.getString("roles") ).build();
    }
}
