/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.service;

import com.learning.model.TblAccount;
import com.learning.model.TblRole;
import java.util.List;

/**
 *
 * @author Thanh_Long
 */
public interface TblAccountService {
    
    public TblAccount getAccountByUser(String username);
    public List<TblRole> getRoleList(Long accountId);
    
    
}
