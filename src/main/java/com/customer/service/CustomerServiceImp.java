package com.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.customer.model.Customer;

@Service
public class CustomerServiceImp implements CustomerService {
	@Autowired
    public JdbcTemplate jt;
	
	private final String SQL_FIND_CUSTOMER = "SELECT * FROM customer WHERE customerId=?";
    private final String SQL_DELETE_CUSTOMER = "DELETE FROM customer WHERE customerId=?";
    private final String SQL_UPDATE_CUSTOMERS = "UPDATE customer SET cname=?, phoneNumber=?, address=?,username=?,password=? WHERE customerId=?";	
    private final String SQL_GET_ALL = "SELECT * FROM customer";
    private final String SQL_INSERT_CUSTOMERS = "INSERT INTO customer(customerId,cname,phoneNumber,address,username,password) VALUES (?,?,?,?,?,?)";
    
    @Override
    public int saveCustomer(Customer customer) {
    	try {
    		 return jt.update(SQL_INSERT_CUSTOMERS,customer.getCustomerId(),customer.getCname(),customer.getPhoneNumber(),
    	        		customer.getAddress(),customer.getUsername(),customer.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
	    	   return 0;
		}
       
    }

    @Override
    public int updateCustomer(Customer customer,int customerId) {
    	try {
    		 return jt.update(SQL_UPDATE_CUSTOMERS,customer.getCname(),customer.getPhoneNumber(),customer.getAddress(),
    	        		customer.getUsername(),customer.getPassword(),customerId);
		} catch (Exception e) {
			e.printStackTrace();
	    	   return 0;
		}
       
    }

    @Override
    public int deleteCustomer(int customerId) {
    	try {
    		 return jt.update(SQL_UPDATE_CUSTOMERS,customerId);
		} catch (Exception e) {
			e.printStackTrace();
	    	   return 0;
		}
    }

    @Override
    public Customer getOneCustomer(int customerId) {
    	try {
    		return jt.queryForObject(SQL_FIND_CUSTOMER,new BeanPropertyRowMapper<>(Customer.class), customerId);
		} catch (Exception e) {
			e.printStackTrace();
	    	   return null;
		}
    }

    @Override
    public List<Customer> getAllCustomer() {
    	try {
    		 return jt.query(SQL_GET_ALL,new BeanPropertyRowMapper<>(Customer.class));
		} catch (Exception e) {
			e.printStackTrace();
	    	   return new ArrayList<>();
		}
    }
}
