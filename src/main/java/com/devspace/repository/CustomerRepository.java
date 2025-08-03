/**
 * 
 */
package com.devspace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devspace.entity.Customer;

/**
 * 
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
