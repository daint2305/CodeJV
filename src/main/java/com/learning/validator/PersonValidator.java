/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.validator;

import com.pj.media.util.*;
import com.learning.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author thanhlong
 */
@Component
public class PersonValidator implements Validator {
    
    @Autowired 
    ValidateUtil validateUtil;

    @Override
    public boolean supports(Class<?> type) {
        return Person.class.equals(type) ||  com.learning.bean.Page.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors err) {
        
        if( ! (o instanceof Person) ){
            return;
        }
        
        
        Person person = (Person) o;
           
        if (!validateUtil.stringValiedate(person.getName())) {
            err.rejectValue("name", "negativeValue", new Object[]{"'name'"}, "Name can't be empty");
        }
        
    }

}
