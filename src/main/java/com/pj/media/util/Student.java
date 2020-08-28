/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC1
 */
public class Student {
    
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    
    public Student(){
        
    }
    
    public Student(Long id,String firstName,String lastName, int age){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

  
    public static void main(String[] args) {
        List<Student> lst= new ArrayList<>();
        
        Student a =new Student(1L, "vang a", "meo", 1);
        lst.add(a);
        
        Student b =new Student(2L, "tao a", "cho", 13);
        lst.add(b);
        
        Student c= lst.get(0);
        System.err.println("Age "+c.getAge());
        
        for(Student o : lst){
            o.setAge(o.getAge()+50);
        }
        
        
         System.err.println("ket qua  "+c.getAge());
        
        
        
        
        
    }
    
    
}
