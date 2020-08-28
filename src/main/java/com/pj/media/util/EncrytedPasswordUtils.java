/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Thanh_Long
 */
public class EncrytedPasswordUtils {
     // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
 
    public static void main(String[] args) {
        String password = "123456";
        String encrytedPassword = encrytePassword(password);
 
        System.out.println("Encryted Password: " + encrytedPassword);
    }
    
}
