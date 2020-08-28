/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author thanhlong
 */

@Entity
@Table(name = "A")
public class A {

    private Long id;
    private String name;
    private Set<B> childLst;
    private C c;

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "a",cascade = CascadeType.ALL)
    public Set<B> getChildLst() {
        return childLst;
    }

    public void setChildLst(Set<B> childLst) {
        this.childLst = childLst;
    }

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "a")
    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }
    
    
    
    
    
    

}
