/**
 * 
 */
package com.devspace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devspace.dto.CustomerReqDto;
import com.devspace.dto.CustomerRespDto;
import com.devspace.entity.Customer;
import com.devspace.service.CustomerService;

import jakarta.validation.Valid;

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

	@PostMapping(value = "")
	public ResponseEntity<CustomerRespDto> addCustomer(@RequestBody @Valid final CustomerReqDto reqDto) {
		return customerService.addCustomer(reqDto);
	}

	@GetMapping(value = "/{custmerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable final String custmerId) {
		return customerService.getCustomerById(custmerId);
	}

}
