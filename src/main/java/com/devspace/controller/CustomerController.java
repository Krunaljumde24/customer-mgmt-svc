/**
 * 
 */
package com.devspace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devspace.entity.Customer;
import com.devspace.service.CustomerService;

/**
 * 
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "")
	public ResponseEntity<List<Customer>> getCustomer() {
		return customerService.getCustomers();
	}

}
