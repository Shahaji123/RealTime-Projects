package com.dizitiveit.hrms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.Deduction;


public interface DeductionDAO extends JpaRepository<Deduction,Integer> {
	
	Boolean existsByDeductionName(String deductionName);
	Deduction findByDeductionName(String deductionName);
    public Optional<Deduction> findByDeductionId(Long deductionId);
   
	@Query(value="select deduction_name from com.deduction where status=true", nativeQuery = true)
	public List<String> findByDeductionName();
}
