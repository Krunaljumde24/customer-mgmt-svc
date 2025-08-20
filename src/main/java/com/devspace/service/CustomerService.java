/**
 * 
 */
package com.devspace.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devspace.dto.CustomerReqDto;
import com.devspace.dto.CustomerRespDto;
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

	public ResponseEntity<CustomerRespDto> addCustomer(CustomerReqDto reqDto) {
		Customer savedCustomer = customerRepository.save(from(reqDto));
		CustomerRespDto resp = new CustomerRespDto();
		if (savedCustomer != null) {
			resp.setId(savedCustomer.getId());
			resp.setMessage("Customer details saved.");
		} else {
			resp.setId(null);
			resp.setMessage("Failed to save customer details.");
		}
		return ResponseEntity.ok(resp);
	}

	private Customer from(CustomerReqDto reqDto) {
		Customer customer = new Customer();
		customer.setAddress(reqDto.getAddress());
		customer.setEmail(reqDto.getEmail());
		customer.setId(generateUniqeCustNum());
		customer.setName(reqDto.getName());
		customer.setPhone(reqDto.getPhone());
		customer.setRegisteredAt(new Date());
		customer.setType(reqDto.getType());
		return customer;
	}

	public String generateUniqeCustNum() {
		DecimalFormat decimalFormat = new DecimalFormat("0000");
		long count = customerRepository.count();
		String custNum = "CUST" + decimalFormat.format(count + 1);
		return custNum;
	}

	public ResponseEntity<?> getCustomerById(String custmerId) {
		Optional<Customer> cust = customerRepository.findById(custmerId);
		if (cust.isPresent()) {
			return ResponseEntity.ok(cust.get());
		} else {
			CustomerRespDto resp = new CustomerRespDto();
			resp.setId(custmerId);
			resp.setMessage("Customer not found with provided ID");
			return new ResponseEntity<CustomerRespDto>(resp, HttpStatusCode.valueOf(404));
		}

	}

}
