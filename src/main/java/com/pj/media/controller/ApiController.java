 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.controller;

import com.learning.model.Person;
import com.learning.service.LanguageService;
import com.learning.service.PersonService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Admin
 */
//@Controller


@Controller
//@RestControllerEnableWebMvc
@RequestMapping("/api")
//@SessionScope
//@Scope("request")
//@Scope("session")
public class ApiController {



    @RequestMapping(value = {"/"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String onSntart(Model model) {    
        return "api/index";
    }
    
    
    @RequestMapping(value = {"/sendMessage"}, method = RequestMethod.GET,
             produces = "application/json; charset=utf-8")
    public String sendMessage(Model model, HttpServletRequest request,
            @RequestParam(value = "message", defaultValue = "") String message) {

         model.addAttribute("msg", message);

         return "api/index";
    }
    
    @RequestMapping(value = {"/sum"}, method = RequestMethod.POST,
             produces = "application/json; charset=utf-8")
    @ResponseBody
    public String sum(Model model, HttpServletRequest request,
            @RequestParam(value = "firstNumber", defaultValue = "0") Integer firstNumber,
            @RequestParam(value = "secondNumber", defaultValue = "0") Integer secondNumber) {

         return String.valueOf(firstNumber+secondNumber);
    }

   

}
