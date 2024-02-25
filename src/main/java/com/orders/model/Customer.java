package com.orders.model;

public class Customer {
	private Integer customerId;
	private String cname;
	private Long phoneNumber;
	private String address;
	private String username;
	private String password;
//	private String orderhistory;
	
	public Customer(Integer customerId, String cname, Long phoneNumber, String address, String username,
			String password) {
		super();
		this.customerId = customerId;
		this.cname = cname;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.username = username;
		this.password = password;
	}
	public Customer() {
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", cname=" + cname + ", phoneNumber=" + phoneNumber + ", address="
				+ address + ", username=" + username + ", password=" + password + "]";
	}
}
