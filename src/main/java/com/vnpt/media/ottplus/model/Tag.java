/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.ottplus.model;

/**
 *
 * @author PC1
 */
public class Tag {

    private int id;
    private String tagName;
    private String img;

    //getter and setter methods
    public Tag(int id, String tagName,String img) {
        this.id = id;
        this.tagName = tagName;
        this.img =img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    

}
