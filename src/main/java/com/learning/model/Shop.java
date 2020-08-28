/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author thanhlong
 */
@Entity
@Table(name = "SHOP")
public class Shop implements Serializable {

    private Long id;
    private String shopCode;
    private String shopName;
    private Long isbn;
    private String address;
    private Set<Staff> staffs;

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "SHOP_CODE")
    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    @Column(name = "SHOP_NAME")
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "isbn")
    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop",cascade = CascadeType.ALL)
    //@JoinColumn(name="SHOP_ID", referencedColumnName ="ID",nullable = true )
    //@JoinColumn(name = "isbn",referencedColumnName = "isbn")
    public Set<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<Staff> staffs) {
        this.staffs = staffs;
    }

    public static void main(String[] args) {
        
//        Set<Staff> set =new HashSet<>();
//
//        Staff f1 = new Staff();
//        f1.setId(1l);
//        f1.setStaffCode("S8888");
//        f1.setStaffName("Name Test");
//        set.add(f1);
//
//        Staff f2 = new Staff();
//        f2.setId(1l);
//        f2.setStaffCode("S8888");
//        f2.setStaffName("Name Test");
//        set.add(f2);
//        
//        Shop shop = new Shop();
//        shop.setId(2L);
//        shop.setShopName("shop A");
//        shop.setShopCode("001");
//        
//        shop.setStaffs(set);
//        
//         Gson gson = new Gson();
//        System.err.println(gson.toJson(shop));
//        
        
        

    }

}
