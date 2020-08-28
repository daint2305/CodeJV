/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.util;

import org.springframework.stereotype.Service;

/**
 *
 * @author thanhlong
 */

@Service
public class ValidateUtil {
    
    public boolean stringValiedate(String txt){
        
        return !( txt==null || txt.trim().isEmpty() );
        
    }
    
    public boolean longValiedate(Object o){
        
        if(o==null){
            return false;
        }
        
        try {
            Long.parseLong(o.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
        
    }
    
    public Long objectToLong(Object o){
        
        if(o==null){
            return null;
        }     
        try {
            return Long.parseLong(o.toString());          
        } catch (NumberFormatException e) {
            return null;
        }
        
    }
    
}
