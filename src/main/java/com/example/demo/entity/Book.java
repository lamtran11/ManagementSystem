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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Book")
public class Book {
	
	// define the fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") // map to the db column named "id"
	private int id;
	
	
	@Column(name = "title")
    private String title;
	
	
	@ManyToOne( 
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						  CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "shop_id") 
//    @JsonBackReference // Prevents infinite loop during serialization
	private Shop shop;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id")  
	@JsonManagedReference // Prevents infinite loop during serialization
	private List<Review> reviews;
	
	
    @ManyToMany(fetch = FetchType.LAZY, 
    			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
    					   CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "customer_book", 
    		   joinColumns = @JoinColumn(name = "book_id"), 
               inverseJoinColumns = @JoinColumn(name = "customer_id"))
    @JsonIgnore // Prevents infinite loop during serialization
	private List<Customer> customers;
	
    
	// define constructors
    public Book() {
    }

    public Book(String title) {
    	
    	this.title = title;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
    
	@Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + "]";
    }

}






















