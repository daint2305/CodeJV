/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.serviceImpl;

import com.learning.model.TblAccount;
import com.learning.model.TblRole;
import com.pj.media.util.Utils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Thanh_Long
 */
@Repository
public class UserDetailsServiceImpl implements UserDetailsService {

    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private TblAccountServiceImpl accountServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            TblAccount account = accountServiceImpl.getAccountByUser(userName);
            if (account == null) {
                throw new UsernameNotFoundException("User " + userName + " was not found in the database");
            }
            Utils.setUsersInSession(request, account);

            List<TblRole> roleLst = accountServiceImpl.getRoleList(account.getGroupId());
            Set<GrantedAuthority> grantList = new HashSet<>();
            if (roleLst != null) {
                for (TblRole role : roleLst) {
                    // ROLE_USER, ROLE_ADMIN,..
                    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role.getRoleCode().trim().toUpperCase());
                    grantList.add(authority);
                }
            }
            UserDetails userDetails = (UserDetails) new User(account.getUsername(), account.getPassword(), grantList);
            return userDetails;

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
