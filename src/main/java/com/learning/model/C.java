/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author thanhlong
 */
@Entity
@Table(name = "C")
public class C {

    private Long id;
    private String detail;
    private A a;

    @GenericGenerator(name = "generator", strategy = "foreign",
             parameters = @Parameter(name = "property", value = "a"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

     @Column(name = "DETAIL")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    
    

    @OneToOne()
    @PrimaryKeyJoinColumn
    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

}
