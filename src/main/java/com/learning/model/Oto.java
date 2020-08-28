package com.learning.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.gson.Gson;


/**
*
* @author daint
*/

@Entity
@Table(name = "oto")

@SqlResultSetMappings({ 
	@SqlResultSetMapping(name = "otoMap01", classes = {
			@ConstructorResult(targetClass = Oto.class, columns = {
					@ColumnResult(name = "id", type = Long.class),
					@ColumnResult(name = "serial", type = Integer.class),
					@ColumnResult(name = "createTime", type = Date.class),
					@ColumnResult(name = "value", type = Long.class),
					@ColumnResult(name = "supplierName", type = String.class),}) }),
	@SqlResultSetMapping(name = "otoMap02", classes = {
			@ConstructorResult(targetClass = Oto.class, columns = {
					@ColumnResult(name = "id1", type = Long.class),
					@ColumnResult(name = "serial1", type = Integer.class),
					@ColumnResult(name = "createTime1", type = Date.class),
					@ColumnResult(name = "value1", type = Long.class),
					@ColumnResult(name = "supplierName1", type = String.class),}) })
})
public class Oto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long idSup;
	private Integer serial;
	private Date createTime;
	private Long value;
	private String supplierName;
	
	
	

	public Oto() {
		
	}
	
	public Oto(Long id, Integer serial, Date createTime, Long value, String supplierName) {
		this.id = id;
		this.serial = serial;
		this.createTime = createTime;
		this.value = value;
		this.supplierName = supplierName;
	}
	
	public Oto(Long id, Long idSup, Integer serial, Date createTime, Long value) {
		this.id = id;
		this.idSup = idSup;
		this.serial = serial;
		this.createTime = createTime;
		this.value = value;
	}
	
	
	@Id
	@Column(name = "idO", unique = true, nullable = false, precision = 22, scale = 0)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "idSup")
	public Long getIdSup() {
		return idSup;
	}
	public void setIdSup(Long idSup) {
		this.idSup = idSup;
	}
	
	@Column(name = "serial")
	public Integer getSerial() {
		return serial;
	}
	public void setSerial(Integer serial) {
		this.serial = serial;
	}
	
	@Column(name = "dateO")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "value")
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	
	@Transient
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	
}
