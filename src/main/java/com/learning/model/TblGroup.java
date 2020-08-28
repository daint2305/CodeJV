/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Thanh_Long
 */
@Entity
@Table(name = "tbl_group")
public class TblGroup implements Serializable {
    
    private Long groupId;
    private Long groupName;

    
    @Id
    @Column(name = "group_id", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Column(name = "group_name")
    public Long getGroupName() {
        return groupName;
    }

    public void setGroupName(Long groupName) {
        this.groupName = groupName;
    }
    
    
    
}
