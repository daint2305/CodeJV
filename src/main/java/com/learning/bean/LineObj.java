/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thanh_Long
 */
public class LineObj {
    
    private String name = "";
    private List<Double> data = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
    
    
    
    
}
