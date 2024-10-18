package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name ="shop")
public class Shop {

	// define the fields
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") // map to the db column named "id"
	private int id;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shop_detail_id") 
    @JsonManagedReference // Prevents infinite loop during serialization
	private ShopDetail shopDetail;
	
	@OneToMany(mappedBy = "shop",
			   fetch = FetchType.LAZY,
               cascade = {CascadeType.PERSIST, CascadeType.MERGE, 
            		   	  CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore // This can remain if you do not want to serialize books
	private List<Book> books;
	
	// constructor
	public Shop() {
        
    }
	
	public Shop (String name, String email) {
		this.name = name;
        this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ShopDetail getShopDetail() {
		return shopDetail;
	}

	public void setShopDetail(ShopDetail shopDetail) {
		this.shopDetail = shopDetail;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + ", name=" + name + ", email=" 
						   + email + ", shopDetail=" + shopDetail + "]";
	}
	
	
	
	
}














