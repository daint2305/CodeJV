package com.learning.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.Gson;

@Entity
@Table(name = "PUBLISH")
@SqlResultSetMappings({ 
	
	@SqlResultSetMapping(name = "publishMap1", classes = {
		@ConstructorResult(targetClass = Publish.class, columns = {
				@ColumnResult(name = "bookcategory", type = String.class),
				@ColumnResult(name = "authorname", type = String.class),
				@ColumnResult(name = "numberpublish", type = Integer.class),
				@ColumnResult(name = "bookcost", type = Long.class),}) })

})


public class Publish implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Integer publish_number;
    private Long publish_value;
    private Long author_value;
    private Long book_value;
    private Date create_time;
    
    private Book book;
    
    private String authorName;
    
    private String category;
    
    
    
    public Publish() {
    	
    }

	public Publish(Integer publish_number, Long book_value, String authorName, String category) {
		this.publish_number = publish_number;
		this.book_value = book_value;
		this.authorName = authorName;
		this.category = category;
	}

	public Publish(String category, String authorName, Integer publish_number, Long book_value) {
		this.category = category;
		this.authorName = authorName;
		this.publish_number = publish_number;
		this.book_value = book_value;
		
		
	}






	@Id
	@Column(name = "PUBLISH_ID", unique = true, nullable = false, precision = 22, scale = 0)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "NUMBEROFPUBLISH")
	public Integer getPublish_number() {
		return publish_number;
	}
	public void setPublish_number(Integer publish_number) {
		this.publish_number = publish_number;
	}
	@Column(name = "PRODUCTION_COST")
	public Long getPublish_value() {
		return publish_value;
	}
	public void setPublish_value(Long publish_value) {
		this.publish_value = publish_value;
	}
	@Column(name = "AUTHOR_ROYALTY")
	public Long getAuthor_value() {
		return author_value;
	}
	public void setAuthor_value(Long author_value) {
		this.author_value = author_value;
	}
	@Column(name = "BOOK_COST")
	public Long getBook_value() {
		return book_value;
	}
	public void setBook_value(Long book_value) {
		this.book_value = book_value;
	}
	@Column(name = "DATE_PUBLISHED")
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID", nullable = true)
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	@Transient
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	@Transient
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
