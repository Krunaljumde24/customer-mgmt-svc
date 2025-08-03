/**
 * 
 */
package com.devspace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devspace.entity.Customer;
import com.devspace.repository.CustomerRepository;

/**
 * 
 */
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return ResponseEntity.ok(customers);
	}

}
