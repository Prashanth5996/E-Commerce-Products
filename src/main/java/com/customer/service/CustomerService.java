package com.customer.service;

import java.util.List;

import com.customer.model.Customer;

public interface CustomerService {
	public int saveCustomer(Customer customer);
	public int updateCustomer(Customer customer,int customerId);
	public int deleteCustomer(int customerId);
	public Customer getOneCustomer(int customerId);
	public List<Customer> getAllCustomer();
}
