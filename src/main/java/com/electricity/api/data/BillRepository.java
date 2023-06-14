package com.electricity.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electricity.api.model.Bill;

@Repository
public interface BillRepository  extends JpaRepository<Bill,Integer>{
	
}
