/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.util;

import com.learning.model.TblAccount;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vnpt2
 */
public class Utils {

    public static boolean setUsersInSession(HttpServletRequest request,TblAccount account){
         request.getSession().setAttribute("Users", account);
         return true;
    }
    
    public static TblAccount getUsersInSession(HttpServletRequest request) {
        TblAccount users = (TblAccount) request.getSession().getAttribute("Users");
        return users;
    }

    public static void removeUsersInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("Users");
    }

}
