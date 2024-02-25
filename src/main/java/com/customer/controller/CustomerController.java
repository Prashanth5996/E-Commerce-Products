package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.model.Customer;
import com.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/save")
    public String saveCustomer(@RequestBody Customer customer) {
            return customerService.saveCustomer(customer)+" "+"Customer Data Saved Successfully";
    }

    @PatchMapping("/update/{customerId}")
    public String updateCustomer(@RequestBody Customer customer, @PathVariable Integer customerId) {
        return customerService.updateCustomer(customer, customerId)+" "+"Customer Data Updated Successfully";
    }

    @DeleteMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable Integer customerId) {
        return customerService.deleteCustomer(customerId)+" "+"Customer Data Deleted Successfully";
    }

    @GetMapping("/get/{customerId}")
    public Customer getOneCustomer(@PathVariable Integer customerId) {
        return customerService.getOneCustomer(customerId);
    }

    @GetMapping("/getAll")
    public List<Customer> getAllcustomerId() {
        return customerService.getAllCustomer();
    }
	}

