package com.orders.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Orders {
	private Integer orderId;
	private Integer customerId;
	private Integer productId;
	private Integer	quantity;
	private Double totalPrice;
	private String status;
	private String address;
	private Products product;
	private Customer customer;
//    @ManyToOne
//    @JoinColumn(name = "productId")
//    private Products product;
//    
//    @ManyToOne
//    @JoinColumn(name = "customerId")
//    private Customer customer;
	    
	public Orders() {
	}
public Orders(Integer orderId, Integer customerId, Integer productId, Integer quantity, Double totalPrice,
		String status, String address, Products product, Customer customer) {
	super();
	this.orderId = orderId;
	this.customerId = customerId;
	this.productId = productId;
	this.quantity = quantity;
	this.totalPrice = totalPrice;
	this.status = status;
	this.address = address;
	this.product = product;
	this.customer = customer;
}
public Integer getOrderId() {
	return orderId;
}
public void setOrderId(Integer orderId) {
	this.orderId = orderId;
}
public Integer getCustomerId() {
	return customerId;
}
public void setCustomerId(Integer customerId) {
	this.customerId = customerId;
}
public Integer getProductId() {
	return productId;
}
public void setProductId(Integer productId) {
	this.productId = productId;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
public Double getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(Double totalPrice) {
	this.totalPrice = totalPrice;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Products getProduct() {
	return product;
}
public void setProduct(Products product) {
	this.product = product;
}
public Customer getCustomer() {
	return customer;
}
public void setCustomer(Customer customer) {
	this.customer = customer;
}
@Override
public String toString() {
	return "Orders [orderId=" + orderId + ", customerId=" + customerId + ", productId=" + productId + ", quantity="
			+ quantity + ", totalPrice=" + totalPrice + ", status=" + status + ", address=" + address + ", product="
			+ product + ", customer=" + customer + "]";
}

	

	
}
