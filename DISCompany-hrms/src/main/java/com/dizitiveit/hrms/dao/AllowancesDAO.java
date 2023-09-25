package com.dizitiveit.hrms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.Allowances;

public interface AllowancesDAO extends JpaRepository<Allowances,Integer> {

	Boolean existsByAllowanceName(String allowanceName);
	Allowances findByAllowanceName(String allowanceName);
	Optional<Allowances> findByAllowanceId(Long allowanceId);
	

	 @Query(value = "select allowance_name FROM allowances where status=true", nativeQuery = true)
	 List<String> findByallowanceName();
}
