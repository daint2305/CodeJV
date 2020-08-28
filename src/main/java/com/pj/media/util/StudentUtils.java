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
public class StudentUtils {

    public static List<Student> buildStudents() {
        
        List<Student> list = new ArrayList<>();
        list.add(new Student(1L, "Trần văn","A",12));
        list.add(new Student(2L, "Vàng A","Mèo",13));
        
        return list; 

    }

}
