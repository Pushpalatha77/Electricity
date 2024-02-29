package com.electricity.api.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electricity.api.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

	public List<Customer> findCustomerByMeter(Integer mid);
}
