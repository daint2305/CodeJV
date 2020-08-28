/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author thanhlong
 */
@Entity
@Table(name = "BILL")
public class Bill implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long shopId;
    private Long staffId;
    private Long totalPrice;
    private Date createTime;
    
    private Set<Goods> goodsLst;
    private Set<BillDetail> billDetail;

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "SHOP_ID")
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Column(name = "STAFF_ID")
    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    @Column(name = "TOTAL_PRICE")
    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill",cascade = CascadeType.ALL)
    public Set<BillDetail> getBillDetail() {
        return billDetail;
    }

    public void setBillDetail(Set<BillDetail> billDetail) {
        this.billDetail = billDetail;
    }
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "BILL_DETAIL", 
        joinColumns = { @JoinColumn(name = "BILL_ID"  ) }, 
        inverseJoinColumns = { @JoinColumn(name = "GOODS_ID" )}
    )
    public Set<Goods> getGoodsLst() {
        return goodsLst;
    }

    public void setGoodsLst(Set<Goods> goodsLst) {
        this.goodsLst = goodsLst;
    }

    
   
    
    
    
    
    
    
    
}
