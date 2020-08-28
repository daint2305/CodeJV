/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author thanhlong
 */
@Entity
@Table(name = "STAFF")
public class Staff implements Serializable {

    private Long id;
    private String staffCode;
    private String staffName;
    private Shop shop;
    private StaffDetail detail;

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "STAFF_CODE")
    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    @Column(name = "STAFF_NAME")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

  
    
    

    
    //@ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOP_ID",referencedColumnName = "ID",nullable = true)
    //@JoinColumn(name = "isbn",referencedColumnName = "isbn")
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    
    //@OneToOne
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "staff")
    public StaffDetail getDetail() {
        return detail;
    }

    public void setDetail(StaffDetail detail) {
        this.detail = detail;
    }
    
    
    
    

}
