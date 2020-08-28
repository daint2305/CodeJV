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

import org.springframework.web.multipart.MultipartFile;


/**
*
* @author daint
*/

@Entity
@Table(name = "supplier")

@SqlResultSetMappings({ 
	
	@SqlResultSetMapping(name = "supplierMap01", classes = {
		@ConstructorResult(targetClass = Supplier.class, columns = {
				@ColumnResult(name = "id", type = Long.class),
				@ColumnResult(name = "name", type = String.class),
				@ColumnResult(name = "address", type = String.class) }) }),
	
	@SqlResultSetMapping(name = "supplierMap02", classes = {
			@ConstructorResult(targetClass = Supplier.class, columns = {
					@ColumnResult(name = "id2", type = Long.class),
					@ColumnResult(name = "name2", type = String.class),
					@ColumnResult(name = "address2", type = String.class) }) })

})


public class Supplier implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String address;
	private Date createTime;
	private MultipartFile uploadedFile;
	
	
	public Supplier(){
		
	}
	
	public Supplier(Long id, String name, String address, Date createTime) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.createTime = createTime;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idS", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	
	@Column(name = "nameS")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "addressS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "dateS")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Transient
	public MultipartFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(MultipartFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	

}
