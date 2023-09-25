package com.dizitiveit.hrms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dizitiveit.hrms.model.AdditionalDetails;
import com.dizitiveit.hrms.model.Employee;

public interface AdditionalDetailDAO extends JpaRepository<AdditionalDetails,Integer> {
	Optional<AdditionalDetails> findByadditionalId(Integer additionalId);	
	//Boolean existsByEmployee(Employee employee);
	AdditionalDetails findByEmployee(Employee employee);
	
	
	
//	 @Query(value = "select * FROM com.additional_details order by additional_details_id asc", nativeQuery = true)
//	 List<AdditionalDetails> findAll();
}
