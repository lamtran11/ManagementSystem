package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "shop_detail")
public class ShopDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "address")
    private String address;
	
	@Column(name = "branch_address_1")
	private String branch1;
	
	@Column(name = "branch_address_2")
	private String branch2;
	
	@OneToOne(mappedBy = "shopDetail",
              cascade = {CascadeType.DETACH, CascadeType.MERGE, 
            		   CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonBackReference // Prevents infinite loop during serialization
	private Shop shop;
	
	public ShopDetail() {
        
    }
	
    public ShopDetail(String address, String branch1, String branch2) {
    	this.address = address;
        this.branch1 = branch1;
        this.branch2 = branch2;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranch1() {
		return branch1;
	}

	public void setBranch1(String branch1) {
		this.branch1 = branch1;
	}

	public String getBranch2() {
		return branch2;
	}

	public void setBranch2(String branch2) {
		this.branch2 = branch2;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
    
	@Override
    public String toString() {
        return "ShopDetail [id=" + id + ", address=" + address + ", branch1=" + branch1 + ", branch2=" + branch2 + "]";
    }
    
    
}
