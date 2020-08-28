/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.serviceImpl;

import com.learning.service.LanguageService;

/**
 *
 * @author Admin
 */
public class EnglishLanguageImpl implements LanguageService{

    @Override
    public String sayHello() {
        return "Hello! How are you";
    }
    
}
