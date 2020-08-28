/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;

/**
 *
 * @author Admin
 */

@Entity
@Table(name = "person")

@SqlResultSetMappings({ 
	
	@SqlResultSetMapping(name = "personMap01", classes = {
		@ConstructorResult(targetClass = Person.class, columns = {
				@ColumnResult(name = "A", type = Long.class),
				@ColumnResult(name = "B", type = String.class),
				@ColumnResult(name = "C", type = Integer.class) }) }),
	
	@SqlResultSetMapping(name = "personMap02", classes = {
			@ConstructorResult(targetClass = Person.class, columns = {
					@ColumnResult(name = "id2", type = Long.class),
					@ColumnResult(name = "name2", type = String.class),
					@ColumnResult(name = "age2", type = Integer.class) }) }),
	@SqlResultSetMapping(name = "personMap03", classes = {
			@ConstructorResult(targetClass = Person.class, columns = {
					@ColumnResult(name = "id3", type = Long.class),
					@ColumnResult(name = "name3", type = String.class),
					@ColumnResult(name = "age3", type = Integer.class),
					@ColumnResult(name = "address3", type = String.class)}) })
	
	
	

})

public class Person implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Integer age;
	private String address;
	private Integer sex;
	private String country;
	private Date createTime;
	private String img;
	private MultipartFile uploadedFile;
	
	private Classroom classroom;
	
	public PersonDetail person_D;
	
	

	public Person() {
		
	}

	public Person(Long id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	public Person(Long id, String name, Integer age, String address) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public Person(Long id, String name, Integer age, String address, Integer sex, String country, Date createTime,
			String img) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.sex = sex;
		this.country = country;
		this.createTime = createTime;
		this.img = img;
	}

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

	@Column(name = "AGE")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "SEX")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "CREATE_TIME")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "IMG")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Transient
	public MultipartFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(MultipartFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "class_id", nullable = true)
	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
	public PersonDetail getPerson_D() {
		return person_D;
	}

	public void setPerson_D(PersonDetail person_D) {
		this.person_D = person_D;
	}

}
