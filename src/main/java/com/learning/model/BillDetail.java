/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author thanhlong
 */

@Entity
@Table(name = "BILL_DETAIL")
public class BillDetail implements Serializable {

    private Long billDetailId;
    private Integer goodsCount;
    private Long priceDetail;
    private String goodsName;
    private String goodsCode;  
    private Bill bill;
    private Goods goods;
    
    @Id
    @Column(name = "BILL_DETAIL_ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Long billDetailId) {
        this.billDetailId = billDetailId;
    }

  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GOODS_ID",referencedColumnName = "ID",nullable = false)
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
    
    

    @Column(name = "GOODS_COUNT")
    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    @Column(name = "PRICE_DETAIL")
    public Long getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(Long priceDetail) {
        this.priceDetail = priceDetail;
    }

    @Transient
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Transient
    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BILL_ID",referencedColumnName = "ID",nullable = true)
    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
    
    

}
