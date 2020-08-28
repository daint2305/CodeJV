package com.learning.model;

import java.io.Serializable;
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

@Entity
@Table(name = "classroom")

public class Classroom implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String classname;
	private String code;
	
	private Set<Person> personset;
	
	
	
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 22, scale = 0)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "name")
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classroom", cascade = CascadeType.ALL)
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "classroom", cascade = CascadeType.ALL)
	public Set<Person> getPersonset() {
		return personset;
	}
	public void setPersonset(Set<Person> personset) {
		this.personset = personset;
	}
}
