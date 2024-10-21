package com.example.demo.dto;

public class CustomerBookDTO {
	
	private int customerId;
	private String customerFirstName;
	private String customerLastName;
	private String email;
	private Long bookId;
	
	public CustomerBookDTO(int customerId, String customerFirstName, String customerLastName, String email, Long booksBought) {
		this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.email = email;
        this.bookId = booksBought;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	
	
	@Override
    public String toString() {
        return "CustomerBook [customerId=" + customerId + ", customerFirstName=" 
        		+ customerFirstName + ", customerLastName=" + customerLastName + ", email=" 
        		+ email + ", bookId=" + bookId + "]";
    }
	
}
