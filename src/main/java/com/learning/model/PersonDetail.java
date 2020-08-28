package com.learning.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "PersonDetail")

public class PersonDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long detail_ID;
	private String salary;
	
	public Person person;
	
	

	public PersonDetail() {
		
	}
	
	
	@Id
	@Column(name = "Detail_ID", unique = true, nullable = false, precision = 22, scale = 0)
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "foreign"
	, parameters = @Parameter(name = "property", value = "person"))
	public Long getDetail_ID() {
		return detail_ID;
	}

	public void setDetail_ID(Long detail_ID) {
		this.detail_ID = detail_ID;
	}
	
	
	@Column(name = "salary")
	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
