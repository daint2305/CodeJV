package com.pj.media.controller;

import com.learning.bean.TestBean;
import com.learning.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thanh
 */
@Controller
@RequestMapping("/B")
public class BController {
    
    @Autowired
    private TestBean testBean;
    
    @RequestMapping(value = {"/test"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String onSntart(Model model) {
        return "b";
    }
    
}
